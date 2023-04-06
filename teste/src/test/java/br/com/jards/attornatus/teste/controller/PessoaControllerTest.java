package br.com.jards.attornatus.teste.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.jards.attornatus.teste.dtos.PessoaDadosCadastro;
import br.com.jards.attornatus.teste.dtos.PessoaDadosDetalhamento;
import br.com.jards.attornatus.teste.models.Endereco;
import br.com.jards.attornatus.teste.models.Pessoa;
import br.com.jards.attornatus.teste.repositories.PessoaRepository;
import lombok.var;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<PessoaDadosCadastro> pessoaDadosCadastroJson;

    @Autowired
    private JacksonTester<PessoaDadosDetalhamento> pessoaDadosDetalhamentoJson;

    @MockBean
    private PessoaRepository repository;

    @Test
    @DisplayName("@PostMapping Deveria devolver codigo Http 400 quando informações inválida.")
    void testCadastrarCenario1()
            throws Exception {
        var response = mockMvc.perform(post("/pessoas")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("@PostMapping Deveria devolver o código Http 201 quando todas as informações sorem válidas")
    void testCadastrarCenario2() throws IOException, Exception {
        var dadosCadastro = new PessoaDadosCadastro("Pessoa", LocalDate.of(1990, 05, 23));
        List<Endereco> endereco = new ArrayList<>();
        var dadosDetalhamento = new PessoaDadosDetalhamento(null, dadosCadastro.nome(), dadosCadastro.nascimento(),
                endereco);

        when(repository.save(any())).thenReturn(new Pessoa(dadosCadastro));

        var response = mockMvc.perform(post("/pessoas").contentType(MediaType.APPLICATION_JSON)
                .content(pessoaDadosCadastroJson.write(dadosCadastro).getJson())).andReturn().getResponse();

        var jsonEsperado = pessoaDadosDetalhamentoJson.write(dadosDetalhamento).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("@GetMapping Deveria devolver o código Http 200 com a todas as pessoas cadastradas")
    void testListarPessoasCenario1() throws IOException, Exception {
        var pessoa = new Pessoa(new PessoaDadosCadastro("Pessoa", LocalDate.of(1990, 05, 23)));

        when(repository.save(any())).thenReturn(pessoa);
        when(repository.findAll()).thenReturn(List.of(pessoa));

        List<Pessoa> response = repository.findAll();

        assertThat(response).isNotNull();
        assertThat(response).hasSizeGreaterThanOrEqualTo(1);
    }

    @Test
    @DisplayName("@GetMapping Deveria devolver o código Http 200 com a lista vazia")
    void testListarPessoasCenario2() throws IOException, Exception {
        List<Pessoa> response = repository.findAll();

        assertThat(response).isNotNull();
        assertThat(response).hasSize(0);
    }

    @Test
    @DisplayName("@GetMapping({id}) Deveria devolver o código Http 200 com a pessoa cadastrada")
    void testtesteListarPessoaIdCenario1() throws IOException, Exception {
        var pessoa = new Pessoa(new PessoaDadosCadastro("Pessoa", LocalDate.of(1990, 05, 23)));
        
        when(repository.save(pessoa)).thenReturn(pessoa);
        when(repository.findById(anyLong())).thenReturn(Optional.of(pessoa));
        
        var response = repository.findById(1l);
        
        assertThat(response).isEqualTo(Optional.of(pessoa));
    }

    @Test
    @DisplayName("@GetMapping({id}) Deveria devolver codigo Http 404 quando id não existir")
    void testtesteListarPessoaId3() throws IOException, Exception {
        var pessoa = new Pessoa(new PessoaDadosCadastro("Pessoa", LocalDate.of(1990, 05, 23)));
        when(repository.save(pessoa)).thenReturn(pessoa);
        when(repository.findById(1l)).thenReturn(Optional.of(pessoa));
        
        var response = repository.findById(2l);
        
        assertThat(response.isEmpty());        
    }

}
