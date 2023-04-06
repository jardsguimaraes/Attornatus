package br.com.jards.attornatus.teste.infra.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import br.com.jards.attornatus.teste.dtos.ExceptionErroValidacao;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratamentoDeErros {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream().map(ExceptionErroValidacao::new).toList());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> tratarErroResponseStatus(ResponseStatusException ex) {
        if (ex.getStatusCode().value() == 404) {
            return ResponseEntity.notFound().build();
        }
        return null;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> tratarDataFormatada(HttpMessageNotReadableException ex) {
        return new ResponseEntity<String>("Dados informados inválido! Verificar se os dados informados já foram cadastrados.", HttpStatus.BAD_REQUEST);
    }
}
