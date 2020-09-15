package ProjetoFinal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ProjetoFinal.Exceptions.NaoPermitidoException;
import ProjetoFinal.Exceptions.RecursoNaoEncontradoException;
import ProjetoFinal.model.Cliente;
import ProjetoFinal.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repositorio;

	public List<Cliente> listaTodos(String atributo) {
		return repositorio.findAll(Sort.by(atributo));
	}

	public Cliente pegaPorId(Integer id, Cliente Cliente) throws RecursoNaoEncontradoException {

		Optional<Cliente> clienteNoBanco = repositorio.findById(id);
		if (clienteNoBanco.isPresent()) {

			return clienteNoBanco.get();

		} else {

			throw new RecursoNaoEncontradoException(id);

		}

	}

	public Cliente criarCliente(Cliente Cliente) {

		return repositorio.save(Cliente);

	}

	public Cliente editarCliente(Cliente Cliente, Integer id) throws RecursoNaoEncontradoException {

		Optional<Cliente> clienteNoBanco = repositorio.findById(id);
		if (clienteNoBanco.isPresent()) {
			clienteNoBanco.get().setNome_completo(Cliente.getNome_completo());
			clienteNoBanco.get().setEndereco(Cliente.getEndereco());
			clienteNoBanco.get().setNome_usuario(Cliente.getNome_usuario());
			clienteNoBanco.get().setEmail(Cliente.getEmail());
			clienteNoBanco.get().setCpf(Cliente.getCpf());
			clienteNoBanco.get().setSenha(Cliente.getSenha());
			clienteNoBanco.get().setData_nascimento(Cliente.getData_nascimento());
			return repositorio.save(clienteNoBanco.get());
		}
		throw new RecursoNaoEncontradoException(id);
	}

	public void excluirCliente(Integer id) throws RecursoNaoEncontradoException, NaoPermitidoException {

		Optional<Cliente> clienteNoBanco = repositorio.findById(id);
		if (clienteNoBanco.isPresent()) {
			try {
				repositorio.delete(clienteNoBanco.get());
			} catch (Exception e) {
				throw new NaoPermitidoException(id);
			}

		} else {

			throw new RecursoNaoEncontradoException(id);
		}

	}
}
