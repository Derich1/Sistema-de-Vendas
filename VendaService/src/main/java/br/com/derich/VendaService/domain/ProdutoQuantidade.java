package br.com.derich.VendaService.domain;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;

public class ProdutoQuantidade {

	@NotNull
	private Produto produto;

	@NotNull
	private Integer quantidade;

	private BigDecimal valorTotal;

	// Construtor sem argumentos
	public ProdutoQuantidade() {
		this.quantidade = 0;
		this.valorTotal = BigDecimal.ZERO;
	}

	// Construtor com Produto, quantidade e valor total
	public ProdutoQuantidade(@NotNull Produto produto, @NotNull Integer quantidade, BigDecimal valorTotal) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorTotal = valorTotal; // Valor total pode ser calculado aqui ou ajustado posteriormente
	}

	public void adicionar(Integer quantidade) {
		this.quantidade += quantidade;
		BigDecimal novoValor = this.produto.getValor().multiply(BigDecimal.valueOf(quantidade));
		this.valorTotal = this.valorTotal.add(novoValor);
	}

	public void remover(Integer quantidade) {
		if (quantidade > this.quantidade) {
			throw new IllegalArgumentException("Quantidade a remover é maior que a quantidade disponível.");
		}
		this.quantidade -= quantidade;
		BigDecimal novoValor = this.produto.getValor().multiply(BigDecimal.valueOf(quantidade));
		this.valorTotal = this.valorTotal.subtract(novoValor);
	}

	// Getters e Setters
	public @NotNull Produto getProduto() {
		return produto;
	}

	public void setProduto(@NotNull Produto produto) {
		this.produto = produto;
	}

	public @NotNull Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(@NotNull Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
}
