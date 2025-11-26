package br.com.raoni.e_commerce.controller;

import br.com.raoni.e_commerce.model.Cliente;
import br.com.raoni.e_commerce.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
        Cliente c = clienteService.salvarCliente(cliente);
        return ResponseEntity.ok().body(c);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        List<Cliente> list = clienteService.listarClientes();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping(path = "/{cpf}")
    public ResponseEntity<Void> delete(@PathVariable String cpf) {
        clienteService.deleteCliente(cpf);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{cpf}")
    public ResponseEntity<Cliente> obterClientePorId(@PathVariable String cpf) {
       Cliente c = clienteService.buscarClientePorCpf(cpf);
       return ResponseEntity.ok(c);
    }


}
