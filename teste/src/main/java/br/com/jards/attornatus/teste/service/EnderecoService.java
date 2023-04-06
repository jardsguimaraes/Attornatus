package br.com.jards.attornatus.teste.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jards.attornatus.teste.dtos.EnderecoDadosCadastro;
import br.com.jards.attornatus.teste.dtos.EnderecoDadosDetalhamento;
import br.com.jards.attornatus.teste.models.Endereco;
import br.com.jards.attornatus.teste.models.Pessoa;
import br.com.jards.attornatus.teste.repositories.EnderecoRepository;
import br.com.jards.attornatus.teste.repositories.PessoaRepository;
import jakarta.validation.Valid;
import lombok.var;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public ResponseEntity<EnderecoDadosDetalhamento> cadastrar(@Valid EnderecoDadosCadastro dados) {
        var pessoa = pessoaRepository.getReferenceById(dados.idPessoa());
        var endereco = new Endereco(dados);
        endereco.setPessoa(pessoa);

        validaEnderecoPrincipal(endereco, pessoa, dados);

        return new ResponseEntity<EnderecoDadosDetalhamento>(new EnderecoDadosDetalhamento(endereco),
                HttpStatus.CREATED);
    }

    private void validaEnderecoPrincipal(Endereco endereco, Pessoa pessoa, EnderecoDadosCadastro dados) {
        
        if ((enderecoRepository.existsByEndereco(pessoa) == 0)) {
            endereco.setEnderecoPrincipal(true);
        }

        if ((dados.enderecoPrincipal().equals(true)) && (enderecoRepository.existsByEndereco(pessoa) >= 1)) {
            List<Endereco> listaEnderecos = new ArrayList<>(enderecoRepository.findListEnderecoByPessoaById(pessoa));
            for (Endereco enderec : listaEnderecos) {
                enderec.setEnderecoPrincipal(false);               
            }           
        }

        enderecoRepository.save(endereco);
    }

}
