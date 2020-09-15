package ProjetoFinal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private String nome;
	@NotNull
	private String descricao;
	@NotNull
	private Date data_fabricacao;
	@NotNull
	private Integer quantidade_estoque;
	@NotNull
	private Double valor_unitario;
	

	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.REMOVE)
	private List<FuncionarioProduto> historico = new ArrayList<FuncionarioProduto>();

	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;
	
	
	
	public Produto() {

	}

	public Produto(Integer id, String nome, String descricao, Date data_fabricacao, Integer quantidade_estoque,
			Double valor_unitario, Categoria categoria, Funcionario funcionario) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.data_fabricacao = data_fabricacao;
		this.quantidade_estoque = quantidade_estoque;
		this.valor_unitario = valor_unitario;
		this.categoria = categoria;
		this.funcionario = funcionario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	
	public List<FuncionarioProduto> getHistorico() {
		return historico;
	}

	public void setHistorico(List<FuncionarioProduto> historico) {
		this.historico = historico;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData_fabricacao() {
		return data_fabricacao;
	}

	public void setData_fabricacao(Date data_fabricacao) {
		this.data_fabricacao = data_fabricacao;
	}

	public Integer getQuantidade_estoque() {
		return quantidade_estoque;
	}

	public void setQuantidade_estoque(Integer quantidade_estoque) {
		this.quantidade_estoque = quantidade_estoque;
	}

	public Double getValor_unitario() {
		return valor_unitario;
	}

	public void setValor_unitario(Double valor_unitario) {
		this.valor_unitario = valor_unitario;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	

}
