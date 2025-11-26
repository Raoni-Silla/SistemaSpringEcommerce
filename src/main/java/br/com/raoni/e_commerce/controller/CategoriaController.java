package br.com.raoni.e_commerce.controller;

import br.com.raoni.e_commerce.model.Categoria;
import br.com.raoni.e_commerce.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity <List<Categoria>> listar() {
        List <Categoria> categorias = categoriaService.listarTodos();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Long id){
        Categoria categoria = categoriaService.buscarCategoriaPorId(id);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<Categoria> inserirCategoria(@RequestBody Categoria categoria){
        Categoria categoriaSalva = categoriaService.save(categoria); // Captura o retorno
        return ResponseEntity.ok(categoriaSalva); // Retorna o objeto com ID
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id){
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
