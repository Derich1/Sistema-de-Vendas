package br.com.derich.VendaService.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "venda")
public class Venda {

	public enum Status {
		INICIADA, CONCLUIDA, CANCELADA;

		public static Status getByName(String value) {
			for (Status status : Status.values()) {
				if (status.name().equals(value)) {
					return status;
				}
			}
			return null;
		}
	}

	@Id
	private String id;

	@NotNull
	@Size(min = 2, max = 10)
	@Indexed(unique = true, background = true)
	private String codigo;

	@NotNull
	private String clienteId;

	private Set<ProdutoQuantidade> produtos;

	private BigDecimal valorTotal;

	@NotNull
	private Instant dataVenda;

	@NotNull
	private Status status;

	// Construtor padrão
	public Venda() {
		produtos = new HashSet<>();
	}

	// Construtor com todos os campos
	public Venda(String id, String codigo, String clienteId, Set<ProdutoQuantidade> produtos,
				 BigDecimal valorTotal, Instant dataVenda, Status status) {
		this.id = id;
		this.codigo = codigo;
		this.clienteId = clienteId;
		this.produtos = produtos != null ? produtos : new HashSet<>();
		this.valorTotal = valorTotal;
		this.dataVenda = dataVenda;
		this.status = status;
	}

	public void adicionarProduto(Produto produto, Integer quantidade) {
		validarStatus();
		Optional<ProdutoQuantidade> op =
				produtos.stream().filter(filter -> filter.getProduto().getCodigo().equals(produto.getCodigo())).findAny();
		if (op.isPresent()) {
			ProdutoQuantidade produtpQtd = op.get();
			produtpQtd.adicionar(quantidade);
		} else {
			ProdutoQuantidade prod =
					new ProdutoQuantidade(produto, 0, BigDecimal.ZERO);
			prod.adicionar(quantidade);
			produtos.add(prod);
		}
		recalcularValorTotalVenda();
	}

	public void validarStatus() {
		if (this.status == Status.CONCLUIDA || this.status == Status.CANCELADA) {
			throw new UnsupportedOperationException("IMPOSSÍVEL ALTERAR VENDA FINALIZADA OU CANCELADA");
		}
	}

	public void removerProduto(Produto produto, Integer quantidade) {
		validarStatus();
		Optional<ProdutoQuantidade> op =
				produtos.stream().filter(filter -> filter.getProduto().getCodigo().equals(produto.getCodigo())).findAny();

		if (op.isPresent()) {
			ProdutoQuantidade produtpQtd = op.get();
			if (produtpQtd.getQuantidade() > quantidade) {
				produtpQtd.remover(quantidade);
				recalcularValorTotalVenda();
			} else {
				produtos.remove(op.get());
				recalcularValorTotalVenda();
			}
		}
	}

	public void removerTodosProdutos() {
		validarStatus();
		produtos.clear();
		valorTotal = BigDecimal.ZERO;
	}

	public Integer getQuantidadeTotalProdutos() {
		return produtos.stream()
				.reduce(0, (partialCountResult, prod) -> partialCountResult + prod.getQuantidade(), Integer::sum);
	}

	public void recalcularValorTotalVenda() {
		BigDecimal valorTotal = BigDecimal.ZERO;
		for (ProdutoQuantidade prod : this.produtos) {
			valorTotal = valorTotal.add(prod.getValorTotal());
		}
		this.valorTotal = valorTotal;
	}

	// Getters e Setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public @NotNull @Size(min = 2, max = 10) String getCodigo() {
		return codigo;
	}

	public void setCodigo(@NotNull @Size(min = 2, max = 10) String codigo) {
		this.codigo = codigo;
	}

	public @NotNull String getClienteId() {
		return clienteId;
	}

	public void setClienteId(@NotNull String clienteId) {
		this.clienteId = clienteId;
	}

	public Set<ProdutoQuantidade> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<ProdutoQuantidade> produtos) {
		this.produtos = produtos;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public @NotNull Instant getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(@NotNull Instant dataVenda) {
		this.dataVenda = dataVenda;
	}

	public @NotNull Status getStatus() {
		return status;
	}

	public void setStatus(@NotNull Status status) {
		this.status = status;
	}
}
