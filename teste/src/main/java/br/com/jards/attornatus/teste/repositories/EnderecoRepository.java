package br.com.jards.attornatus.teste.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jards.attornatus.teste.models.Endereco;
import br.com.jards.attornatus.teste.models.Pessoa;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

        @Query("""
                        select e
                        from Endereco e
                        where e.pessoa = :pessoa
                        """)
        Endereco findEnderecoByPessoaById(Pessoa pessoa);

        @Query("""
                        select e
                        from Endereco e
                        where e.pessoa = :pessoa
                        """)
        List<Endereco> findListEnderecoByPessoaById(Pessoa pessoa);

        @Query("""
                        select count(e)
                        from Endereco e
                        where e.pessoa = :pessoa
                        """)
        int existsByEndereco(Pessoa pessoa);

}
