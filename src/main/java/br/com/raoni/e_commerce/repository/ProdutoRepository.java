package br.com.raoni.e_commerce.repository;

import br.com.raoni.e_commerce.model.Categoria;
import br.com.raoni.e_commerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

     Optional<Produto> findByNome(String nome);

     boolean existsByCategorias(Categoria categoria);

     // 4. findByOr (Buscar por nome OU descrição)
     // O Spring entende: WHERE upper(nome) LIKE upper(%?%) OR upper(descricao) LIKE upper(%?%)

     List<Produto> findByNomeContainingIgnoreCaseOrDescricaoContainingIgnoreCase(String nome, String descricao);

     // 10. findByLessThan (Preço menor que X)
     // O Spring entende: WHERE preco < ?
     List<Produto> findByPrecoLessThan(BigDecimal preco);

     // 6. findByOrderByDesc (Ordenar todos por preço decrescente)
     // O Spring entende: ORDER BY preco DESC
     List<Produto> findByOrderByPrecoDesc();





}
