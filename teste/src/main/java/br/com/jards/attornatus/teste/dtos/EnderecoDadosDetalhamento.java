package br.com.jards.attornatus.teste.dtos;

import br.com.jards.attornatus.teste.models.Endereco;

public record EnderecoDadosDetalhamento(
        Long idEndereco,
        Long idPessoa,
        String logradouro,
        String cep,
        int numero,
        String cidade,
        Boolean enderecoPrincipal) {

    public EnderecoDadosDetalhamento(Endereco endereco) {
        this(endereco.getId(), endereco.getPessoa().getId(), endereco.getLogradouro(), endereco.getCidade(), endereco.getNumero(), endereco.getCep(), endereco.getEnderecoPrincipal());
    }

}
