package br.com.raoni.e_commerce.controller;

import br.com.raoni.e_commerce.model.Produto;
import br.com.raoni.e_commerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;


    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody Produto produto){
        produtoService.salvarProduto(produto);
        return ResponseEntity.ok(produto);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos(){
        List <Produto> lista = produtoService.listarProdutos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id){
        Produto produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarProdutoPorId(@RequestBody Long id){
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
