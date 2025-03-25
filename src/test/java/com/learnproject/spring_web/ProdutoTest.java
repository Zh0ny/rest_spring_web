package com.learnproject.spring_web;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.learnproject.spring_web.entity.Produto;
import com.learnproject.spring_web.exception.ProductPriceException;
import com.learnproject.spring_web.service.ProdutoService;

@SpringBootTest
public class ProdutoTest {
    
    @Autowired
    private ProdutoService produtoService;

    @Test
    public void verificarValorNegativoNoProduto() throws Exception {
        Produto produto = new Produto();

        produto.setNome("Produto Teste");
        produto.setPreco(-10.0);

        assertThrows(ProductPriceException.class, () -> produtoService.save(produto));

    }
}
