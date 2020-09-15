package ProjetoFinal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ProjetoFinal.Exceptions.NaoPermitidoException;
import ProjetoFinal.Exceptions.RecursoNaoEncontradoException;
import ProjetoFinal.model.Pedido;
import ProjetoFinal.model.PedidoItem;
import ProjetoFinal.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository repositorio;

	@Autowired
	PedidoItemService pedidoItemService;

	public List<Pedido> listaTodos(String atributo) {
		return repositorio.findAll(Sort.by(atributo));
	}

	public Pedido pegaPorId(Integer id, Pedido pedido) throws RecursoNaoEncontradoException {

		Optional<Pedido> pedidoNoBanco = repositorio.findById(id);
		if (pedidoNoBanco.isPresent()) {

			return pedidoNoBanco.get();

		} else {

			throw new RecursoNaoEncontradoException(id);
		}

	}

	public Pedido criarPedido(Pedido pedido) {

		Pedido novoPedido = repositorio.save(pedido);

		for (PedidoItem item : pedido.getItens()) {
			item.setPedido(novoPedido);
			pedidoItemService.criarPedidoItem(item);
		}
		return novoPedido;

	}

	public Pedido editarPedido(Pedido pedido, Integer id) throws RecursoNaoEncontradoException {

		Optional<Pedido> pedidoNoBanco = repositorio.findById(id);

		if (pedidoNoBanco.isPresent()) {
			pedidoNoBanco.get().setCliente(pedido.getCliente());
			pedidoNoBanco.get().setData_pedido(pedido.getData_pedido());

			return repositorio.save(pedidoNoBanco.get());
		}
		throw new RecursoNaoEncontradoException(id);
	}

	public void excluirPedido(Integer id) throws RecursoNaoEncontradoException, NaoPermitidoException {

		Optional<Pedido> pedidoNoBanco = repositorio.findById(id);
		if (pedidoNoBanco.isPresent()) {
			try {
				repositorio.delete(pedidoNoBanco.get());
			} catch (Exception e) {
				throw new NaoPermitidoException(id);
			}
		} else {

			throw new RecursoNaoEncontradoException(id);
		}

	}

	public void excluirItem(Integer id, PedidoItem pedidoItem) throws RecursoNaoEncontradoException {

		Optional<Pedido> pedidoNoBanco = repositorio.findById(id);
		if (pedidoNoBanco.isPresent()) {
			PedidoItem itemNoBanco = pedidoItemService.pegaPorId(id, pedidoItem);
			if (itemNoBanco.getPedido().getId().equals(id)) {

				pedidoItemService.excluirPedidoItem(pedidoItem.getId());

			}

		}
	}

}