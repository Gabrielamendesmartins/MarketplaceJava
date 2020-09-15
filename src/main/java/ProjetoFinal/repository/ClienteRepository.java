package ProjetoFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ProjetoFinal.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
