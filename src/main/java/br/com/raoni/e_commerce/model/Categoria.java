package br.com.raoni.e_commerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @NotBlank
    @Size(min = 1, max = 200)
    private String categoria;

    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos;

    public Categoria(String descricao, String categoria, List<Produto> produtos) {

        this.descricao = descricao;
        this.categoria = categoria;
        this.produtos = new ArrayList<Produto>();

        if (produtos != null && !produtos.isEmpty()) {
            this.produtos.addAll(produtos);
        }
    }
}
