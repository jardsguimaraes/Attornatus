package br.com.jards.attornatus.teste.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoDadosCadastro(
        @NotNull Long idPessoa,
        @NotBlank String logradouro,
        @NotBlank String cep,
        int numero,
        @NotBlank String cidade,
        @NotNull Boolean enderecoPrincipal) {
}
