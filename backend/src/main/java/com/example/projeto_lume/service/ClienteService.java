package com.example.projeto_lume.service;

import com.example.projeto_lume.dto.ClienteDTO;
import com.example.projeto_lume.model.Cliente;
import com.example.projeto_lume.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(clienteDTO);
        return clienteRepository.save(cliente);
    }

    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente findClienteById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente atualizarCliente(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            Cliente clienteAtualizado = new Cliente(clienteDTO);
            clienteAtualizado.setId(cliente.getId());
            return clienteRepository.save(clienteAtualizado);
        } else {
            return null;
        }
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }


}
