/**
 * 
 */
package br.com.derich.VendaService.domain;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto {
	
	private String id;

	@NotNull
	@Size(min = 2, max = 10)
	private String codigo;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String nome;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String descricao;
	
	@NotNull
	private BigDecimal valor;

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

	public @NotNull @Size(min = 1, max = 50) String getNome() {
		return nome;
	}

	public void setNome(@NotNull @Size(min = 1, max = 50) String nome) {
		this.nome = nome;
	}

	public @NotNull @Size(min = 1, max = 50) String getDescricao() {
		return descricao;
	}

	public void setDescricao(@NotNull @Size(min = 1, max = 50) String descricao) {
		this.descricao = descricao;
	}

	public @NotNull BigDecimal getValor() {
		return valor;
	}

	public void setValor(@NotNull BigDecimal valor) {
		this.valor = valor;
	}
}
