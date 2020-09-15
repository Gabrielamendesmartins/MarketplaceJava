package ProjetoFinal.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ProjetoFinal.Exceptions.RecursoNaoEncontradoException;
import ProjetoFinal.model.FuncionarioProduto;
import ProjetoFinal.repository.FuncionarioProdutoRepository;

@Service
public class FuncionarioProdutoService {
	@Autowired
	FuncionarioProdutoRepository repositorio;
	
	public List<FuncionarioProduto> listaTodos(String atributo){
		return repositorio.findAll(Sort.by(atributo));
	}

	public FuncionarioProduto pegaPorId(Integer id, FuncionarioProduto FuncionarioProduto) throws RecursoNaoEncontradoException {
		
		Optional<FuncionarioProduto> funcionarioProdutoNoBanco = repositorio.findById(id);
		if(funcionarioProdutoNoBanco.isPresent()) {
			
			return funcionarioProdutoNoBanco.get();
		} else {
			
			throw new RecursoNaoEncontradoException(id);
		}
		
		
		
	}

	public FuncionarioProduto criarFuncionarioProduto(FuncionarioProduto funcionarioProduto) {
		
		return repositorio.save(funcionarioProduto);
		
		
	}

	public FuncionarioProduto editarFuncionarioProduto(FuncionarioProduto funcionarioProduto, Integer id) throws RecursoNaoEncontradoException {
		
		Optional<FuncionarioProduto> funcionarioProdutoNoBanco = repositorio.findById(id);
		if(funcionarioProdutoNoBanco.isPresent()) {
			funcionarioProdutoNoBanco.get().setAcao(funcionarioProduto.getAcao());
			funcionarioProdutoNoBanco.get().setData_acao(funcionarioProduto.getData_acao());
			funcionarioProdutoNoBanco.get().setFuncionario(funcionarioProduto.getFuncionario());
			funcionarioProdutoNoBanco.get().setProduto(funcionarioProduto.getProduto());
			
			
			return repositorio.save(funcionarioProdutoNoBanco.get());	
		}
		throw new RecursoNaoEncontradoException(id);
	}

	public void excluirFuncionarioProduto(Integer id) throws RecursoNaoEncontradoException{

		Optional<FuncionarioProduto> FuncionarioProdutoNoBanco = repositorio.findById(id);
		if(FuncionarioProdutoNoBanco.isPresent()) {
			repositorio.delete(FuncionarioProdutoNoBanco.get());
	}
		throw new RecursoNaoEncontradoException(id);

	}

}
