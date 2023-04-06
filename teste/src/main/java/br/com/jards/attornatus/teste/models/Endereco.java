package br.com.jards.attornatus.teste.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.jards.attornatus.teste.dtos.EnderecoDadosCadastro;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "is_endereco")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pessoa", foreignKey = @ForeignKey(name = "fk_endereco_pessoa"))
    @JsonBackReference
    private Pessoa pessoa;

    private String logradouro;
    private String cep;
    private int numero;
    private String cidade;
    private Boolean enderecoPrincipal;

    public Endereco(@Valid EnderecoDadosCadastro dados) {
        this.logradouro = dados.logradouro();
        this.cep = dados.cep();
        this.numero = dados.numero();
        this.cidade = dados.cidade();
        this.enderecoPrincipal = dados.enderecoPrincipal();
    }
    
}
