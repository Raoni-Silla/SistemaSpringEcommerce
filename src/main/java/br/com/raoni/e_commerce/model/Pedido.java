package br.com.raoni.e_commerce.model;

import br.com.raoni.e_commerce.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataDoPedido;

    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING) // Salva "PAGO" em vez de "1"
    private StatusPedido statusPedido;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    @OneToOne (mappedBy = "pedido")
    private NotaFiscal notaFiscal;


    public Pedido(BigDecimal valorTotal){
        this.dataDoPedido = LocalDate.now();
        this.valorTotal = valorTotal;
        this.statusPedido = StatusPedido.AGUARDANDO_PAGAMENTO;
    }

}
