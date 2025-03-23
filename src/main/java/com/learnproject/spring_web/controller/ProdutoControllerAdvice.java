package com.learnproject.spring_web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.learnproject.spring_web.exception.ProductNullException;
import com.learnproject.spring_web.exception.ProductPriceException;

@ControllerAdvice
public class ProdutoControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> CapturaError(Exception e) {

        Map<String, Object> body = new HashMap<String, Object>();

        body.put("message", "Erro ao processar a requisição");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(ProductNullException.class)
    public ResponseEntity<Object> CapturaErrorNull(ProductNullException e) {

        Map<String, Object> body = new HashMap<String, Object>();

        body.put("message", "Verifique os campos do produto");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(ProductPriceException.class)
    public ResponseEntity<Object> CapturaErrorPrice(ProductPriceException e) {

        Map<String, Object> body = new HashMap<String, Object>();

        body.put("message", "O preço do produto não pode ser negativo");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

}
