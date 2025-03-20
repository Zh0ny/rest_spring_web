package com.learnproject.spring_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.learnproject.spring_web.entity.Produto;
import com.learnproject.spring_web.repository.ProdutoRepository;
import com.learnproject.spring_web.exception.ProductNullException;
import com.learnproject.spring_web.exception.ProductPriceException;

@Service
public class ProdutoService {

    @Autowired
	private ProdutoRepository repository;

	public Produto save(@RequestBody Produto produto) throws Exception {
		if(produto.getNome() == null || produto.getNome().isEmpty() || 
			produto.getPreco() == null || produto.getPreco() == 0.0) {
			throw new ProductNullException();
		}
		if (produto.getPreco() < 0) {
			throw new ProductPriceException();
			
		}
		return repository.save(produto);
	}

	public Produto findById(@PathVariable Long id) {
		return repository.findById(id).orElse(null);
	}

	public List<Produto> findAll() {
		return repository.findAll();
	}
}
