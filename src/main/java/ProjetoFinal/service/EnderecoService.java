package ProjetoFinal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ProjetoFinal.Exceptions.NaoPermitidoException;
import ProjetoFinal.Exceptions.RecursoNaoEncontradoException;
import ProjetoFinal.model.Endereco;
import ProjetoFinal.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository repositorio;

	public List<Endereco> listaTodos(String atributo) {
		return repositorio.findAll(Sort.by(atributo));
	}

	public Endereco pegaPorId(Integer id, Endereco endereco) throws RecursoNaoEncontradoException {

		Optional<Endereco> enderecoNoBanco = repositorio.findById(id);
		if (enderecoNoBanco.isPresent()) {

			return enderecoNoBanco.get();
		}

		throw new RecursoNaoEncontradoException(id);

	}

	public Endereco criarEndereco(Endereco endereco) {

		return repositorio.save(endereco);

	}

	public Endereco editarEndereco(Endereco endereco, Integer id) throws RecursoNaoEncontradoException {

		Optional<Endereco> enderecoNoBanco = repositorio.findById(id);
		if (enderecoNoBanco.isPresent()) {
			enderecoNoBanco.get().setRua(endereco.getRua());
			enderecoNoBanco.get().setNumero(endereco.getNumero());
			enderecoNoBanco.get().setComplemento(endereco.getComplemento());
			enderecoNoBanco.get().setBairro(endereco.getBairro());
			enderecoNoBanco.get().setCep(endereco.getCep());
			enderecoNoBanco.get().setCidade(endereco.getCidade());
			enderecoNoBanco.get().setEstado(endereco.getEstado());
			return repositorio.save(enderecoNoBanco.get());
		}
		throw new RecursoNaoEncontradoException(id);
	}

	public void excluirEndereco(Integer id) throws RecursoNaoEncontradoException, NaoPermitidoException {

		Optional<Endereco> enderecoNoBanco = repositorio.findById(id);
		if (enderecoNoBanco.isPresent()) {
			try {

				repositorio.delete(enderecoNoBanco.get());
			} catch (Exception e) {

				throw new NaoPermitidoException(id);
			}

		} else {

			throw new RecursoNaoEncontradoException(id);
		}

	}
}
