package com.example.projeto_lume.model;

import com.example.projeto_lume.dto.ClienteDTO;
import jakarta.persistence.*;


@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @Embedded
    private Cpf cpf;
    @Embedded
    private Endereco endereco;

    public Cliente(){

    }

    public Cliente(ClienteDTO clienteDTO){
        this.nome = clienteDTO.getNome();
        this.cpf = new Cpf(clienteDTO.getCpf());
        this.endereco = new Endereco(
            clienteDTO.getLogradouro(),
            clienteDTO.getBairro(),
            clienteDTO.getCidade(),
            clienteDTO.getEstado(),
            clienteDTO.getCep()
        );
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Cpf getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = new Cpf(cpf);
    }



}
