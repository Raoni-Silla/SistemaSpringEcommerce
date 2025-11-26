package br.com.raoni.e_commerce.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor

public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    private Uf uf;

    public Cidade (String nome, Uf uf) {
        this.nome = nome;
        this.uf = uf;
    }
}
