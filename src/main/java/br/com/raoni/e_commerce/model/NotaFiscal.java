package br.com.raoni.e_commerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    @JsonIgnore
    Pedido pedido;

    LocalDate dataDeEmissao;

    public NotaFiscal(Pedido pedido, LocalDate dataDeEmissao){
        this.pedido = pedido;
        this.dataDeEmissao = dataDeEmissao;
    }

}
