package ProjetoFinal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ProjetoFinal.Exceptions.NaoPermitidoException;
import ProjetoFinal.Exceptions.RecursoNaoEncontradoException;
import ProjetoFinal.model.Funcionario;
import ProjetoFinal.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	FuncionarioRepository repositorio;

	public List<Funcionario> listaTodos(String atributo) {
		return repositorio.findAll(Sort.by(atributo));
	}

	public Funcionario pegaPorId(Integer id, Funcionario funcionario) throws RecursoNaoEncontradoException {

		Optional<Funcionario> funcionarioNoBanco = repositorio.findById(id);
		if (funcionarioNoBanco.isPresent()) {

			return funcionarioNoBanco.get();
		}

		throw new RecursoNaoEncontradoException(id);

	}

	public Funcionario criarFuncionario(Funcionario funcionario) {

		return repositorio.save(funcionario);

	}

	public Funcionario editarFuncionario(Funcionario funcionario, Integer id) throws RecursoNaoEncontradoException {

		Optional<Funcionario> funcionarioNoBanco = repositorio.findById(id);
		if (funcionarioNoBanco.isPresent()) {
			funcionarioNoBanco.get().setNome(funcionario.getNome());
			funcionarioNoBanco.get().setCpf(funcionario.getCpf());
			funcionarioNoBanco.get().setSenha(funcionario.getSenha());
			funcionarioNoBanco.get().setEndereco(funcionario.getEndereco());

			return repositorio.save(funcionarioNoBanco.get());
		}
		throw new RecursoNaoEncontradoException(id);
	}

	public void excluirFuncionario(Integer id) throws RecursoNaoEncontradoException, NaoPermitidoException {

		Optional<Funcionario> funcionarioNoBanco = repositorio.findById(id);
		if (funcionarioNoBanco.isPresent()) {
			try {
				repositorio.delete(funcionarioNoBanco.get());
			} catch (Exception e) {

				throw new NaoPermitidoException(id);
			}
		} else {

			throw new RecursoNaoEncontradoException(id);

		}

	}
}
