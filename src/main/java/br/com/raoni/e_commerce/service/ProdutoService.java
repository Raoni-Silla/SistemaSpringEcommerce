package br.com.raoni.e_commerce.service;

import br.com.raoni.e_commerce.model.Produto;
import br.com.raoni.e_commerce.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;

    public Produto buscarPorId(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Transactional
    public Produto salvarProduto(Produto produto){

        if(repository.findByNome(produto.getNome()).isPresent()){
            throw new RuntimeException("Produto existente com mesmo nome");
        }

        return repository.save(produto);
    }

    @Transactional
    public List<Produto> listarProdutos(){
        return repository.findAll();
    }

    @Transactional
    public void deletarProduto(Long id){

        Produto produto = buscarPorId(id);

        if (produto.getQntdEstoque() > 0) {
            throw new RuntimeException("Não pode deletar produto com estoque!");
        }

        repository.delete(produto);
    }

}
