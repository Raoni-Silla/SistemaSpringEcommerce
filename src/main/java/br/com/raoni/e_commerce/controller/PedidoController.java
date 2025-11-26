package br.com.raoni.e_commerce.controller;

import br.com.raoni.e_commerce.model.Pedido;
import br.com.raoni.e_commerce.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/pedidos")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> save(@RequestBody Pedido pedido) {

        Pedido pedidoSalvo = pedidoService.salvar(pedido);

        return ResponseEntity.ok(pedidoSalvo);

    }

    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pedidoService.deletar(id);

        // Retorna Status 204, não tem conteudo pra mostrar porque apagou oque foi pedido
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {

        List<Pedido> lista = pedidoService.listar();


        return ResponseEntity.ok(lista);
    }
}
