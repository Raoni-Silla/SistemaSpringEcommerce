package br.com.raoni.e_commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> tratadorDeErro(Exception e){

        String mensagem = e.getMessage();

        return ResponseEntity.status(400).body(mensagem);

    }

}
