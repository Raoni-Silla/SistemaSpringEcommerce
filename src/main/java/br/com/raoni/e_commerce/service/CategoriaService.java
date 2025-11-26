package br.com.raoni.e_commerce.service;

import br.com.raoni.e_commerce.model.Categoria;
import br.com.raoni.e_commerce.repository.CategoriaRepository;
import br.com.raoni.e_commerce.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Categoria save(Categoria categoria) {

        String nomeNormalizado = categoria.getCategoria().trim().toUpperCase();

        if (categoriaRepository.existsByCategoriaIgnoreCase(nomeNormalizado)) {
            throw new RuntimeException("Erro: Categoria '" + nomeNormalizado + "' já existe.");
        }

        categoria.setCategoria(nomeNormalizado);

        return categoriaRepository.save(categoria);
    }

    public Categoria buscarCategoriaPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + id));
    }

    @Transactional
    public void delete(Long id) {

        Categoria categoria = buscarCategoriaPorId(id);

        if (produtoRepository.existsByCategorias(categoria)) {
            throw new RuntimeException("Não é possível excluir: Existem produtos nesta categoria.");
        }

        categoriaRepository.delete(categoria);
    }


    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll();
    }
}
