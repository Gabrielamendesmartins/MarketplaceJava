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
import ProjetoFinal.model.Endereco;
import ProjetoFinal.service.EnderecoService;

@RestController
public class EnderecoController {

	@Autowired
	EnderecoService service;

	@GetMapping("/endereco")
	public ResponseEntity<List<Endereco>> retornaLista(@RequestParam(defaultValue = "Id") String atributo) {
		return ResponseEntity.ok().body(service.listaTodos(atributo));
	}

	@GetMapping("/endereco/{id}")
	public ResponseEntity<Endereco> retornaPorId(@PathVariable Integer id, Endereco endereco)
			throws RecursoNaoEncontradoException {
		return ResponseEntity.ok().body(service.pegaPorId(id, endereco));
	}

	@PostMapping("/endereco")
	public ResponseEntity<Endereco> criaEndereco(@Valid @RequestBody Endereco endereco) {
		return ResponseEntity.ok().body(service.criarEndereco(endereco));
	}

	@PutMapping("/endereco/{id}")
	public Endereco editaEndereco(@Valid @RequestBody Endereco endereco, @PathVariable Integer id)
			throws RecursoNaoEncontradoException {

		return service.editarEndereco(endereco, id);
	}

	@DeleteMapping("/endereco/{id}")
	public void excluiEndereco(@PathVariable Integer id) throws RecursoNaoEncontradoException, NaoPermitidoException {

		service.excluirEndereco(id);
	}
}
