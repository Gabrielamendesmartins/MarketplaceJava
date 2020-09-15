package ProjetoFinal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ProjetoFinal.Exceptions.NaoPermitidoException;
import ProjetoFinal.Exceptions.RecursoNaoEncontradoException;
import ProjetoFinal.model.Categoria;
import ProjetoFinal.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository repositorio;

	public List<Categoria> listaTodos(String atributo) {
		return repositorio.findAll(Sort.by(atributo));
	}

	public Categoria pegaPorId(Integer id) throws RecursoNaoEncontradoException {

		Optional<Categoria> categoriaNoBanco = repositorio.findById(id);
		if (categoriaNoBanco.isPresent()) {

			return categoriaNoBanco.get();
		}

		throw new RecursoNaoEncontradoException(id);

	}

	public Categoria criarCategoria(Categoria categoria) {

		return repositorio.save(categoria);

	}

	public Categoria editarCategoria(Categoria categoria, Integer id) throws RecursoNaoEncontradoException {

		Optional<Categoria> categoriaNoBanco = repositorio.findById(id);
		if (categoriaNoBanco.isPresent()) {
			categoriaNoBanco.get().setNome(categoria.getNome());
			categoriaNoBanco.get().setDescricao(categoria.getDescricao());

			return repositorio.save(categoriaNoBanco.get());
		}
		throw new RecursoNaoEncontradoException(id);
	}

	public void excluirCategoria(Integer id) throws RecursoNaoEncontradoException, NaoPermitidoException {

		Optional<Categoria> categoriaNoBanco = repositorio.findById(id);
		if (categoriaNoBanco.isPresent()) {
			try {
				repositorio.delete(categoriaNoBanco.get());
			} catch (Exception e) {

				throw new NaoPermitidoException(id);
			}

		} else {
			throw new RecursoNaoEncontradoException(id);
		}

	}
}
