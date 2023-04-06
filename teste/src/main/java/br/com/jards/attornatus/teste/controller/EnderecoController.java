package br.com.jards.attornatus.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jards.attornatus.teste.dtos.EnderecoDadosCadastro;
import br.com.jards.attornatus.teste.dtos.EnderecoDadosDetalhamento;
import br.com.jards.attornatus.teste.dtos.PessoaDadosDetalhamento;
import br.com.jards.attornatus.teste.service.EnderecoService;
import br.com.jards.attornatus.teste.service.PessoaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService serviceEndereco;

    @Autowired
    private PessoaService servicePessoa;
    @PostMapping
    @Transactional
    public ResponseEntity<EnderecoDadosDetalhamento> cadastrar(@RequestBody @Valid EnderecoDadosCadastro dados) {
        return serviceEndereco.cadastrar(dados);
    }

    @GetMapping("{id}")
    public ResponseEntity<PessoaDadosDetalhamento> listarEnderecosDaPessoa(@PathVariable Long id) {
        return servicePessoa.listarPessoaId(id);
    }
}
