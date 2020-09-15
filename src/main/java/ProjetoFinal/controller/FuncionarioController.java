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
import ProjetoFinal.model.Funcionario;
import ProjetoFinal.service.FuncionarioService;

@RestController
public class FuncionarioController {

	@Autowired
	FuncionarioService service;

	@GetMapping("/funcionario")
	public ResponseEntity<List<Funcionario>> retornaLista(@RequestParam(defaultValue = "Id") String atributo) {
		return ResponseEntity.ok().body(service.listaTodos(atributo));
	}

	@GetMapping("/funcionario/{id}")
	public ResponseEntity<Funcionario> retornaPorId(@PathVariable Integer id, Funcionario funcionario)
			throws RecursoNaoEncontradoException {
		return ResponseEntity.ok().body(service.pegaPorId(id, funcionario));
	}

	@PostMapping("/funcionario")
	public ResponseEntity<Funcionario> criaFuncionario(@Valid @RequestBody Funcionario funcionario) {
		return ResponseEntity.ok().body(service.criarFuncionario(funcionario));
	}

	@PutMapping("/funcionario/{id}")
	public Funcionario editaFuncionario(@Valid @RequestBody Funcionario funcionario, @PathVariable Integer id)
			throws RecursoNaoEncontradoException {

		return service.editarFuncionario(funcionario, id);
	}

	@DeleteMapping("/funcionario/{id}")
	public void excluiFuncionario(@PathVariable Integer id)
			throws RecursoNaoEncontradoException, NaoPermitidoException {

		service.excluirFuncionario(id);
	}
}
