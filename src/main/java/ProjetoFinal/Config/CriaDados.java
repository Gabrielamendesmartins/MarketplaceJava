package ProjetoFinal.Config;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import ProjetoFinal.model.Categoria;
import ProjetoFinal.model.Cliente;
import ProjetoFinal.model.Endereco;
import ProjetoFinal.model.Funcionario;
import ProjetoFinal.model.FuncionarioProduto;
import ProjetoFinal.model.Pedido;
import ProjetoFinal.model.PedidoItem;
import ProjetoFinal.model.Produto;
import ProjetoFinal.repository.CategoriaRepository;
import ProjetoFinal.repository.ClienteRepository;
import ProjetoFinal.repository.FuncionarioProdutoRepository;
import ProjetoFinal.repository.FuncionarioRepository;
import ProjetoFinal.repository.PedidoItemRepository;
import ProjetoFinal.repository.PedidoRepository;
import ProjetoFinal.repository.ProdutoRepository;

@Configuration
public class CriaDados implements CommandLineRunner {

	
	@Autowired
	CategoriaRepository repositorioCat;
	
	@Autowired
	ProdutoRepository repositorioProd;
	
	@Autowired
	PedidoRepository repositorioPed;
	
	@Autowired
	ClienteRepository repositorioCli;
	
	@Autowired
	FuncionarioRepository repositorioFunc;
	
	@Autowired
	PedidoItemRepository repositorioPedidoItem;
	
	@Autowired
	FuncionarioProdutoRepository repositorioFuncionarioProduto;

	@Override
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria(null, "Eletrônicos", "Aparelhos de tecnologia");
		Categoria c2 = new Categoria(null, "Vestuário", "Roupas, sapatos e acessórios");
		
		
		Endereco e1= new Endereco(null, "Estrada Uniao e Industria", 900, "itaipava","casa", "25750-123", "Petropolis", "RJ");
		Endereco e2= new Endereco(null, "Estrada Uniao e Industria", 950, "itaipava","casa", "25750-222", "Petropolis", "RJ");
		Endereco e3= new Endereco(null, "Estrada Uniao e Industria", 800, "itaipava","casa", "25750-282", "Petropolis", "RJ");
		
		Funcionario f1 = new Funcionario(null, "Kleber", "789247157-60", "555", e2);
		
		
		Produto p1 = new Produto(null, "Casaco", "Macio e elegante", new Date(), 25,
				150.0 , c2, f1);
		Produto p2 = new Produto(null, "Tênis", "Confortável e anatômico", new Date(), 50,
				75.0 , c2, f1);
		
		FuncionarioProduto fp1 = new FuncionarioProduto(null, "Registrou", new Date(),f1,p1 );
		FuncionarioProduto fp2 = new FuncionarioProduto(null, "Registrou", new Date(),f1,p2 );
		
		
		
		Cliente cli1 = new Cliente(null, "Gabriela",e1 ,"gabi", "gabimm@gmail.com", "123456789-10", "123", new Date());
		Cliente cli2 = new Cliente(null, "Diogo",e1, "diogo", "diogomm@gmail.com", "123456789-11", "321", new Date());
		
		Pedido ped1 = new Pedido(null, new Date(), cli1);
		
		PedidoItem pi1 = new PedidoItem(null, p1.getValor_unitario(), 2F, p1, ped1);
		PedidoItem pi2 = new PedidoItem(null, p2.getValor_unitario(), 3F, p2, ped1);
		
		
		repositorioCat.saveAll(Arrays.asList(c1, c2));
		repositorioCli.saveAll(Arrays.asList(cli1, cli2));
		repositorioPed.save(ped1);
		repositorioFunc.save(f1);
		repositorioProd.saveAll(Arrays.asList(p1, p2));
		repositorioPedidoItem.saveAll(Arrays.asList(pi1, pi2));
		repositorioFuncionarioProduto.saveAll(Arrays.asList(fp1,fp2));
	}
	
	
	
}
