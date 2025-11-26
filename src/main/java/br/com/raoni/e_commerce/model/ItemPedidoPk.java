package br.com.raoni.e_commerce.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable // uma chave composta
@Data //equals e hashcode (obrigatório para chaves)

public class ItemPedidoPk implements Serializable {
    private Long pedidoId;
    private Long produtoId;
}
