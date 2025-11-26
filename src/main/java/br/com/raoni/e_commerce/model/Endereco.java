package br.com.raoni.e_commerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logradouro;

    private String complemento;

    private String bairro;

    @ManyToOne
    private Cidade cidade;

    private String cep;

    @ManyToOne
    private Cliente cliente;
}
