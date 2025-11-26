package br.com.raoni.e_commerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    private String descricao;

    @NotNull
    @Min(0)
    private BigDecimal preco;

    @NotNull
    @Min(0)
    private Long qntdEstoque;

    private LocalDateTime dataCadastro;

    @ManyToMany
    @JoinTable(
            name = "produto_categoria", // Nome da tabela do meio que será criada
            joinColumns = @JoinColumn(name = "produto_id"), // Lado "Deste" objeto (Produto)
            inverseJoinColumns = @JoinColumn(name = "categoria_id") // Lado do "Outro" (Categoria)
    )
    private List<Categoria> categorias;

    public Produto(String nome, String descricao, BigDecimal preco, Long qntdEstoque,List<Categoria> categorias) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.qntdEstoque = qntdEstoque;
        this.dataCadastro = LocalDateTime.now();
        this.categorias = new ArrayList<>();

        if (categorias != null && !categorias.isEmpty()) {
            this.categorias.addAll(categorias);
        }

    }

}
