package br.com.jards.attornatus.teste.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import br.com.jards.attornatus.teste.dtos.EnderecoDadosCadastro;
import br.com.jards.attornatus.teste.dtos.PessoaDadosCadastro;
import br.com.jards.attornatus.teste.models.Endereco;
import br.com.jards.attornatus.teste.models.Pessoa;

@DataJpaTest
public class EnderecoRepositoryTest {

    @Autowired
    private EnderecoRepository EnderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private TestEntityManager em;

    private void cadastrarEndereco(Long idPessoa, String logradouro, String cep, int numero, String cidade,
            boolean enderecoPrincipal) {
        var pessoa = pessoaRepository.getReferenceById(idPessoa);
        var endereco = new Endereco(dadosEndereco(idPessoa, logradouro, cep, numero, cidade, enderecoPrincipal));
        endereco.setPessoa(pessoa);
        em.persist(endereco);
    }

    private EnderecoDadosCadastro dadosEndereco(Long idPessoa, String logradouro, String cep, int numero,
            String cidade, boolean enderecoPrincipal) {
        return new EnderecoDadosCadastro(idPessoa, logradouro, cep, numero, cidade, enderecoPrincipal);
    }

    private Pessoa cadastrarPessoa(String nome, LocalDate nascimento) {
        var pessoa = new Pessoa(dadosCadastro(nome, nascimento));
        em.persist(pessoa);
        return pessoa;
    }

    private PessoaDadosCadastro dadosCadastro(String nome, LocalDate nascimento) {
        return new PessoaDadosCadastro(nome, nascimento);
    }

    @Test
    @DisplayName("Deveria devolver lista vazia quando estiver uma Pessoa que não possui nenhum endereço cadastrado")
    void testFindListEnderecoByPessoaByIdCenario1() {
        var pessoa = cadastrarPessoa("Pessoa Teste", LocalDate.of(1982, 05, 30));

        List<Endereco> pesquisarEndereco = new ArrayList<>(EnderecoRepository.findListEnderecoByPessoaById(pessoa));

        assertThat(pesquisarEndereco).hasSize(0);
    }

    @Test
    @DisplayName("Deveria devolver lista com todos endereços quando estiver uma Pessoa que possui um ou vários endereço cadastrado(s)")
    void testFindListEnderecoByPessoaByIdCenario2() {
        
        var pessoa = cadastrarPessoa("Pessoa Teste", LocalDate.of(1982, 05, 30));
        
        cadastrarEndereco(pessoa.getId(), "Rua A", "57035-000", 15, "cidade", true);
        var endereco = (EnderecoRepository.findListEnderecoByPessoaById(pessoa));
        
        assertThat(endereco).hasSizeGreaterThanOrEqualTo(1);
    }
}
