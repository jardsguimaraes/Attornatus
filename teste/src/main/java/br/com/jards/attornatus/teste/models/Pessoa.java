package br.com.jards.attornatus.teste.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.jards.attornatus.teste.dtos.PessoaDadosCadastro;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Pessoa")
@Table(name = "pessoas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    private Long id;

    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate nascimento;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_pessoa", foreignKey = @ForeignKey(name = "fk_endereco_pessoa"))
    @JsonManagedReference
    private List<Endereco> endereco = new ArrayList<>();

    public Pessoa(PessoaDadosCadastro dados) {
        this.nome = dados.nome();
        this.nascimento = dados.nascimento();
    }

    public void addEndereco(Endereco endereco) {
        this.endereco.add(endereco);
    }

    public void removerEndereco(Endereco endereco) {
        this.endereco.remove(endereco);
    }

}
