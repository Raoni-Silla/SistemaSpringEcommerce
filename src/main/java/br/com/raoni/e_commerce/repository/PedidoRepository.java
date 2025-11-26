package br.com.raoni.e_commerce.repository;

import br.com.raoni.e_commerce.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
