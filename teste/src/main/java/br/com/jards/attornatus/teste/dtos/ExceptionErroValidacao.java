package br.com.jards.attornatus.teste.dtos;

import org.springframework.validation.FieldError;

public record ExceptionErroValidacao(String campo, String menssagem) {
    
    public ExceptionErroValidacao(FieldError erro) {
        this(erro.getField(), erro.getDefaultMessage());
    }
}
