package ProjetoFinal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ProjetoFinal.Exceptions.RecursoNaoEncontradoException;
import ProjetoFinal.model.PedidoItem;
import ProjetoFinal.repository.PedidoItemRepository;

@Service
public class PedidoItemService {


	@Autowired
	PedidoItemRepository repositorio;
	
	public List<PedidoItem> listaTodos(String atributo){
		return repositorio.findAll(Sort.by(atributo));
	}

	public PedidoItem pegaPorId(Integer id, PedidoItem PedidoItem) {
		
		Optional<PedidoItem> PedidoItemNoBanco = repositorio.findById(id);
		if(PedidoItemNoBanco.isPresent()) {
			
			return PedidoItemNoBanco.get();
		}
		
		return null;
		
	}

	public PedidoItem criarPedidoItem(PedidoItem PedidoItem) {
		
		return repositorio.save(PedidoItem);
		
		
	}

	public PedidoItem editarPedidoItem(PedidoItem pedidoItem, Integer id) throws RecursoNaoEncontradoException {
		
		Optional<PedidoItem> pedidoItemNoBanco = repositorio.findById(id);
		if(pedidoItemNoBanco.isPresent()) {
			pedidoItemNoBanco.get().setQuantidade(pedidoItem.getQuantidade());
			pedidoItemNoBanco.get().setValor(pedidoItem.getValor());			
			pedidoItemNoBanco.get().setProduto(pedidoItem.getProduto());			
			pedidoItemNoBanco.get().setPedido(pedidoItem.getPedido());			
			return repositorio.save(pedidoItemNoBanco.get());	
		}
		throw new RecursoNaoEncontradoException(id);
	}

	public void excluirPedidoItem(Integer id) throws RecursoNaoEncontradoException{

		Optional<PedidoItem> PedidoItemNoBanco = repositorio.findById(id);
		if(PedidoItemNoBanco.isPresent()) {
			repositorio.delete(PedidoItemNoBanco.get());
	}else {
		
		throw new RecursoNaoEncontradoException(id);
	}
		

	}



}
