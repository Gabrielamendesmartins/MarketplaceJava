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
import ProjetoFinal.model.Produto;
import ProjetoFinal.service.ProdutoService;

@RestController
public class ProdutoController {

	@Autowired
	ProdutoService service;

	@GetMapping("/produto")
	public ResponseEntity<List<Produto>> retornaLista(@RequestParam(defaultValue = "Id") String atributo) {
		return ResponseEntity.ok().body(service.listaTodos(atributo));
	}

	@GetMapping("/produto/{id}")
	public ResponseEntity<Produto> retornaPorId(@RequestBody Produto produto, @PathVariable Integer id)
			throws RecursoNaoEncontradoException {
		return ResponseEntity.ok().body(service.pegaPorId(id, produto));
	}

	@PostMapping("/produto")
	public ResponseEntity<Produto> criaProduto(@Valid @RequestBody Produto produto) {
		return ResponseEntity.ok().body(service.criarProduto(produto));
	}

	@PutMapping("/produto/{id}")
	public Produto editaProduto(@Valid @RequestBody Produto produto, @PathVariable Integer id)
			throws RecursoNaoEncontradoException {

		return service.editarProduto(produto, id);
	}

	@DeleteMapping("/produto/{id}")
	public void excluiProduto(@PathVariable Integer id) throws RecursoNaoEncontradoException, NaoPermitidoException {

		service.excluirProduto(id);
	}

}
