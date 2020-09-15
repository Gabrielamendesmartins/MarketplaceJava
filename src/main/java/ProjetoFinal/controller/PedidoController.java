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
import ProjetoFinal.model.Pedido;
import ProjetoFinal.model.PedidoItem;
import ProjetoFinal.service.PedidoService;

@RestController
public class PedidoController {

	@Autowired
	PedidoService service;

	@GetMapping("/pedido")
	public ResponseEntity<List<Pedido>> retornaLista(@RequestParam(defaultValue = "Id") String atributo) {
		return ResponseEntity.ok().body(service.listaTodos(atributo));
	}

	@GetMapping("/pedido/{id}")
	public ResponseEntity<Pedido> retornaPorId(@PathVariable Integer id, Pedido pedido)
			throws RecursoNaoEncontradoException {
		return ResponseEntity.ok().body(service.pegaPorId(id, pedido));
	}

	@PostMapping("/pedido")
	public ResponseEntity<Pedido> criaPedido(@Valid @RequestBody Pedido pedido) {
		return ResponseEntity.ok().body(service.criarPedido(pedido));
	}

	@PutMapping("/pedido/{id}")
	public Pedido editaPedido(@Valid @RequestBody Pedido pedido, @PathVariable Integer id)
			throws RecursoNaoEncontradoException {

		return service.editarPedido(pedido, id);
	}

	@DeleteMapping("/pedido/{id}")
	public void excluiPedido(@PathVariable Integer id) throws RecursoNaoEncontradoException, NaoPermitidoException {

		service.excluirPedido(id);
	}

	@DeleteMapping("/pedido/item/{id}")
	public void excluiPedidoItem(@PathVariable Integer id, @RequestBody PedidoItem PedidoItem)
			throws RecursoNaoEncontradoException {

		service.excluirItem(id, PedidoItem);
	}

}
