package br.com.raoni.e_commerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class ItemPedido implements Serializable {

    // O ID não é um Long, é a classe composta
    @EmbeddedId
    private ItemPedidoPk id = new ItemPedidoPk();

    //O "MapsId"
    // "O atributo 'pedidoId' lá da chave (PK) corresponde a este objeto Pedido aqui"
    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id")
    @JsonIgnore
    private Pedido pedido;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id")
    private Produto produto;


    private Integer quantidade;

    private BigDecimal precoNoMomento;


}
