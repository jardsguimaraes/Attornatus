package br.com.jards.attornatus.teste.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jards.attornatus.teste.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
