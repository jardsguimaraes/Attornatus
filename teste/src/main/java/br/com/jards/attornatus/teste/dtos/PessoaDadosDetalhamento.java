package br.com.jards.attornatus.teste.dtos;

import java.time.LocalDate;
import java.util.List;

import br.com.jards.attornatus.teste.models.Pessoa;

public record PessoaDadosDetalhamento(Long id, String nome, LocalDate nascimento, List<?> endereco) {

    public PessoaDadosDetalhamento(Pessoa pessoa) {
        this(pessoa.getId(), pessoa.getNome(), pessoa.getNascimento(), pessoa.getEndereco());
    }
}
