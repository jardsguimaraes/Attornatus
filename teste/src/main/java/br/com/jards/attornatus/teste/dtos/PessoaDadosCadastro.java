package br.com.jards.attornatus.teste.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PessoaDadosCadastro(
        @NotBlank String nome,
        @NotNull LocalDate nascimento) {
}
