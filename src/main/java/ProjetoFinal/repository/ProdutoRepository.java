package ProjetoFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ProjetoFinal.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>  {

	
	
}
