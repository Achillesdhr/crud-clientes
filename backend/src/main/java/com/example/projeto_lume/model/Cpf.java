package com.example.projeto_lume.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Cpf {
    String numeroCpf;

    public Cpf(){

    }

    public Cpf(String numero){
        validarCpf(numero);
        this.numeroCpf = numero;
    }

    private void validarCpf(String numero){

        String cpf =  limparCpf(numero);



        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = cpf.charAt(i) - '0';
        }

        // calcula primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += digits[i] * (10 - i);
        }
        int rem = sum % 11;
        int dv1 = (rem < 2) ? 0 : 11 - rem;
        if (dv1 != digits[9]) {
            throw new IllegalArgumentException("CPF inválido: dígito verificador 1 incorreto");
        }

        // calcula segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += digits[i] * (11 - i);
        }
        rem = sum % 11;
        int dv2 = (rem < 2) ? 0 : 11 - rem;
        if (dv2 != digits[10]) {
            throw new IllegalArgumentException("CPF inválido: dígito verificador 2 incorreto");
        }
    }

    public String getNumeroCpf() {
        return numeroCpf;
    }

    public void setNumeroCpf(String numero) {
        validarCpf(numero);
        this.numeroCpf = limparCpf(numero);
    }

    public static String limparCpf(String numero) {
        if (numero == null) {
           throw new IllegalArgumentException("CPF não pode ser nulo");
        }
        if (numero.length() != 11) {
            throw new IllegalArgumentException("CPF inválido: comprimento incorreto");
        }
        // rejeita CPFs com todos os dígitos iguais (ex: 00000000000, 11111111111, ...)
        if (numero.matches("(\\d)\\1{10}")) {
            throw new IllegalArgumentException("CPF inválido: sequência repetida");
        }
        return numero.replaceAll("\\D+", "");
    }
}
