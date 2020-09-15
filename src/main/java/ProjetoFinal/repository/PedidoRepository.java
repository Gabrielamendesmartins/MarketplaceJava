package ProjetoFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ProjetoFinal.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
