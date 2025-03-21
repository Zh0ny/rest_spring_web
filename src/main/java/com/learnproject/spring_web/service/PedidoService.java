package com.learnproject.spring_web.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnproject.spring_web.repository.PedidoRepository;
import com.learnproject.spring_web.repository.ProdutoRepository;
import com.learnproject.spring_web.entity.Pedido;
import com.learnproject.spring_web.entity.Produto;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public Pedido save(Pedido pedido) {
		Set<Produto> produtos = new HashSet<>();

		pedido.setDataPedido(LocalDateTime.now());
		pedido.getProdutos().forEach(produto -> {
			produtoRepository.findById(produto.getId()).ifPresent(produtos::add);
		});
		pedido.setProdutos(produtos);

		return repository.save(pedido);
	}

	public Pedido findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public List<Pedido> findAll() {
		return repository.findAll();
	}
}
