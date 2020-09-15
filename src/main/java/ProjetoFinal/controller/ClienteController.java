package ProjetoFinal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ProjetoFinal.Exceptions.NaoPermitidoException;
import ProjetoFinal.Exceptions.RecursoNaoEncontradoException;
import ProjetoFinal.model.Cliente;
import ProjetoFinal.service.ClienteService;

@RestController
public class ClienteController {

	@Autowired
	ClienteService service;

	@GetMapping("/cliente")
	public ResponseEntity<List<Cliente>> retornaLista(@RequestParam(defaultValue = "Id") String atributo) {
		return ResponseEntity.ok().body(service.listaTodos(atributo));
	}

	@GetMapping("/cliente/{id}")
	public ResponseEntity<Cliente> retornaPorId(@PathVariable Integer id, Cliente cliente)
			throws RecursoNaoEncontradoException {
		return ResponseEntity.ok().body(service.pegaPorId(id, cliente));
	}

	@PostMapping("/cliente")
	public ResponseEntity<Cliente> criaCliente(@Valid @RequestBody Cliente cliente) {
		return ResponseEntity.ok().body(service.criarCliente(cliente));
	}

	@PutMapping("/cliente/{id}")
	public Cliente editaCliente(@Valid @RequestBody Cliente cliente, @PathVariable Integer id)
			throws RecursoNaoEncontradoException {

		return service.editarCliente(cliente, id);
	}

	@DeleteMapping("/cliente/{id}")
	public void excluiCliente(@PathVariable Integer id) throws RecursoNaoEncontradoException, NaoPermitidoException {

		service.excluirCliente(id);
	}
}
