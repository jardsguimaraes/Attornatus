package br.com.jards.attornatus.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jards.attornatus.teste.dtos.PessoaDadosCadastro;
import br.com.jards.attornatus.teste.dtos.PessoaDadosDetalhamento;
import br.com.jards.attornatus.teste.service.PessoaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @PostMapping
    @Transactional
    public ResponseEntity<PessoaDadosDetalhamento> cadastrar(@RequestBody @Valid PessoaDadosCadastro dados,
            UriComponentsBuilder uriBuilder) {
        return service.cadastrar(dados, uriBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<PessoaDadosDetalhamento>> listarPessoas(
            @PageableDefault(size = 5, sort = { "nome" }) Pageable paginacao) {
        return service.listarPessoas(paginacao);
    }

    @GetMapping("{id}")
    public ResponseEntity<PessoaDadosDetalhamento> listarPessoaId(@PathVariable Long id) {
        return service.listarPessoaId(id);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<PessoaDadosDetalhamento> atualizar(@PathVariable Long id,
            @RequestBody @Valid PessoaDadosCadastro dados) {
        return service.atualizar(id, dados);
    }

}
