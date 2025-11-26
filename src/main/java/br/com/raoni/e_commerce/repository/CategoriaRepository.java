package br.com.raoni.e_commerce.repository;

import br.com.raoni.e_commerce.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByCategoria(String categoria);

    boolean existsByCategoriaIgnoreCase(String nome);
}
