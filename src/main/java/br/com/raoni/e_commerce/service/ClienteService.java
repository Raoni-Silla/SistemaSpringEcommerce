package br.com.raoni.e_commerce.service;

import br.com.raoni.e_commerce.model.Cliente;
import br.com.raoni.e_commerce.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvarCliente(Cliente cliente) {


        cliente.setCpf(cliente.getCpf().trim());
        cliente.setNome(cliente.getNome().trim().toUpperCase());

        if (clienteRepository.findByCpf(cliente.getCpf()).isPresent()) {
            throw new RuntimeException("Erro: CPF já existente na base de dados.");
        }

        return clienteRepository.save(cliente);
    }

    public Cliente buscarClientePorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Erro: CPF não encontrado: " + cpf));
    }

    @Transactional
    public void deleteCliente(String cpf) {

        Cliente clienteEncontrado = buscarClientePorCpf(cpf);
        clienteRepository.delete(clienteEncontrado);

    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
}
