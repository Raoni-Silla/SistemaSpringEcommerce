package br.com.raoni.e_commerce.service;

import br.com.raoni.e_commerce.enums.StatusPedido;
import br.com.raoni.e_commerce.model.Cliente;
import br.com.raoni.e_commerce.model.Pedido;
import br.com.raoni.e_commerce.model.Produto;
import br.com.raoni.e_commerce.repository.ClienteRepository;
import br.com.raoni.e_commerce.repository.PedidoRepository;
import br.com.raoni.e_commerce.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ProdutoRepository produtoRepository;

    @Transactional
    public Pedido salvar(Pedido pedido) {

        //cliente esta nulo?
        Cliente cliente = pedido.getCliente();

        if (pedido.getCliente() == null){
            throw new RuntimeException("O cliente associado ao pedido não existe");
        }

        //cliente existe no banco de dados?
        Optional <Cliente> clienteProcurado = clienteRepository.findByCpf(cliente.getCpf());

        if (clienteProcurado.isEmpty()){
            throw new RuntimeException("O cliente associado ao pedido não existe no nosso banco de dados");
        }


        pedido.setCliente(clienteProcurado.get());
        pedido.setDataDoPedido(LocalDate.now());
        pedido.setValorTotal(BigDecimal.ZERO);

        pedido.getItens().forEach(item -> {

            Optional <Produto> prodEncontrado = produtoRepository.findById(item.getId().getProdutoId());

            if (prodEncontrado.isEmpty()){
                throw new RuntimeException("produto não encontrado na nossa base de dados");
            }

            Integer quantidadeProdutosPedidos = item.getQuantidade();

            prodEncontrado.ifPresent(produto -> {

               Long estoqueProduto = produto.getQntdEstoque();

               if (estoqueProduto < quantidadeProdutosPedidos){
                   throw new RuntimeException("Sem" + produto.getNome() + " em estoque");
               }

               Long quantidadeEmEstoqueAtualizada = estoqueProduto - quantidadeProdutosPedidos;

               produto.setQntdEstoque(quantidadeEmEstoqueAtualizada);

               produtoRepository.save(produto);
            });

            BigDecimal precoProduto = prodEncontrado.get().getPreco();
            item.setPrecoNoMomento(precoProduto);
            item.setPedido(pedido);
            item.setProduto(prodEncontrado.orElse(null));
            pedido.setStatusPedido(StatusPedido.AGUARDANDO_PAGAMENTO);

            BigDecimal resultado = precoProduto.multiply(new BigDecimal(quantidadeProdutosPedidos));

            pedido.setValorTotal(pedido.getValorTotal().add(resultado));




        });

        return pedidoRepository.save(pedido);


    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }


    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com ID: " + id));
    }

    public void deletar(Long id) {

        Pedido pedido = buscarPorId(id);
        pedidoRepository.delete(pedido);
    }
}
