package ProjetoFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ProjetoFinal.model.PedidoItem;

public interface PedidoItemRepository extends JpaRepository<PedidoItem, Integer> {

}
