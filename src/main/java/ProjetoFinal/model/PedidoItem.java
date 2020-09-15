package ProjetoFinal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PedidoItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Double valor;
	private Float quantidade;
	
	@ManyToOne
	@JoinColumn(name="id_produto")
	private Produto produto;
	
	
	@ManyToOne
	@JoinColumn(name="id_pedido")
	@JsonIgnore
	private Pedido pedido;
	
	
	
	
	public PedidoItem() {
		
	}
	
	

	public PedidoItem(Integer id, Double valor, Float quantidade, Produto produto, Pedido pedido) {
		super();
		this.id = id;
		this.valor = valor;
		this.quantidade = quantidade;
		this.produto = produto;
		this.pedido = pedido;
	}

	public Double getSubtotal() {
		
		return this.valor * this.quantidade;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Float quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
}
