package br.com.jards.attornatus.teste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jards.attornatus.teste.dtos.PessoaDadosCadastro;
import br.com.jards.attornatus.teste.dtos.PessoaDadosDetalhamento;
import br.com.jards.attornatus.teste.models.Pessoa;
import br.com.jards.attornatus.teste.repositories.PessoaRepository;
import jakarta.validation.Valid;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public ResponseEntity<PessoaDadosDetalhamento> cadastrar(@Valid PessoaDadosCadastro dados,
            UriComponentsBuilder uriBuilder) {
        var pessoa = new Pessoa(dados);
        repository.save(pessoa);

        var uri = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();

        return ResponseEntity.created(uri).body(new PessoaDadosDetalhamento(pessoa));
    }

    public ResponseEntity<PessoaDadosDetalhamento> listarPessoaId(Long id) {
        var pessoa = repository.getReferenceById(id);
        return ResponseEntity.ok(new PessoaDadosDetalhamento(pessoa));
    }

    public ResponseEntity<Page<PessoaDadosDetalhamento>> listarPessoas(Pageable paginacao) {
        var page = repository.findAll(paginacao).map(PessoaDadosDetalhamento::new);
        return ResponseEntity.ok(page);
    }

    public ResponseEntity<PessoaDadosDetalhamento> atualizar(Long id, @Valid PessoaDadosCadastro dados) {
        System.out.println(id);
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        
        var pessoa = new Pessoa(dados);
        pessoa.setId(id);
        repository.save(pessoa);
        return ResponseEntity.ok(new PessoaDadosDetalhamento(pessoa));
    }

}
