package ProjetoFinal.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ProjetoFinal.Exceptions.NaoPermitidoException;
import ProjetoFinal.Exceptions.RecursoNaoEncontradoException;
import ProjetoFinal.model.FuncionarioProduto;
import ProjetoFinal.model.Produto;
import ProjetoFinal.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository repositorio;
	
	@Autowired
	FuncionarioProdutoService funProdService;

	ProdutoService(){
	
	}
	
	public List<Produto> listaTodos(String atributo) {
		return repositorio.findAll(Sort.by(atributo));
	}

	public Produto pegaPorId(Integer id, Produto produto) throws RecursoNaoEncontradoException {

		Optional<Produto> produtoNoBanco = repositorio.findById(id);
		if (produtoNoBanco.isPresent()) {

			return produtoNoBanco.get();
			
		} else {
			
		throw new RecursoNaoEncontradoException(id);	
		}

		

	}
    
	public Produto criarProduto(Produto produto) {
		Produto novoProduto = repositorio.save(produto);
		FuncionarioProduto fp1 = new FuncionarioProduto(null, "Criou", new Date(),produto.getFuncionario(),novoProduto );
		funProdService.criarFuncionarioProduto(fp1);
		return novoProduto;

	}

	public Produto editarProduto(Produto produto, Integer id) throws RecursoNaoEncontradoException {
		
		
		Optional<Produto> produtoNoBanco = repositorio.findById(id);
		if (produtoNoBanco.isPresent()) {
			produtoNoBanco.get().setNome(produto.getNome());
			produtoNoBanco.get().setDescricao(produto.getDescricao());
			produtoNoBanco.get().setData_fabricacao(produto.getData_fabricacao());
			produtoNoBanco.get().setQuantidade_estoque(produto.getQuantidade_estoque());
			produtoNoBanco.get().setValor_unitario(produto.getValor_unitario());
			produtoNoBanco.get().setCategoria(produto.getCategoria());
			
			Produto novoProduto = repositorio.save(produtoNoBanco.get());
			FuncionarioProduto fp1 = new FuncionarioProduto(null, "Editou", new Date(),produto.getFuncionario(),novoProduto );
			funProdService.criarFuncionarioProduto(fp1);
			
			return novoProduto;
			
		}
		throw new RecursoNaoEncontradoException(id);
	}

	public void excluirProduto(Integer id) throws RecursoNaoEncontradoException, NaoPermitidoException {

		Optional<Produto> produtoNoBanco = repositorio.findById(id);
		if (produtoNoBanco.isPresent()) {
			try {
			repositorio.delete(produtoNoBanco.get());
			}catch (Exception e ) {
				
				throw new NaoPermitidoException(id);
			}
		} else {
			
		
		throw new RecursoNaoEncontradoException(id);
		}
	}
}
