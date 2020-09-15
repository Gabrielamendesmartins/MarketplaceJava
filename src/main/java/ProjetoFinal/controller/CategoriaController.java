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
import ProjetoFinal.model.Categoria;
import ProjetoFinal.service.CategoriaService;


@RestController
public class CategoriaController {

	@Autowired
	CategoriaService service;
	
	@GetMapping("/categoria")
	public ResponseEntity<List<Categoria>> retornaLista(@RequestParam (defaultValue = "Id") String atributo){
		return ResponseEntity.ok().body(service.listaTodos(atributo));
	}
	
	@GetMapping("/categoria/{id}")
	public ResponseEntity<Categoria> retornaPorId(@PathVariable Integer id) throws RecursoNaoEncontradoException{
		return ResponseEntity.ok().body(service.pegaPorId(id));
	}
	
	@PostMapping("/categoria")
	public ResponseEntity<Categoria> criaCategoria(@Valid @RequestBody  Categoria categoria){
		return ResponseEntity.ok().body(service.criarCategoria(categoria));
	}
	
	@PutMapping("/categoria/{id}")
	public Categoria editaCategoria(@Valid @RequestBody Categoria categoria, @PathVariable Integer id) throws RecursoNaoEncontradoException {
		
		return service.editarCategoria(categoria, id);
	}
	
	
	@DeleteMapping("/categoria/{id}")
	public void excluiCategoria(@PathVariable Integer id) throws RecursoNaoEncontradoException, NaoPermitidoException {
		
		service.excluirCategoria(id);
	}
}

