package br.com.derich.VendaService.dto;

import java.time.Instant;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class VendaDTO {

	@NotNull
	@Size(min = 2, max = 10)
	private String codigo;

	@NotNull
	private String clienteId;

	@NotNull
	private Instant dataVenda;

	// Construtor
	public VendaDTO(@NotNull @Size(min = 2, max = 10) String codigo,
					@NotNull String clienteId,
					@NotNull Instant dataVenda) {
		this.codigo = codigo;
		this.clienteId = clienteId;
		this.dataVenda = dataVenda;
	}

	// Getters
	public String getCodigo() {
		return codigo;
	}

	public String getClienteId() {
		return clienteId;
	}

	public Instant getDataVenda() {
		return dataVenda;
	}

	// Setters
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}

	public void setDataVenda(Instant dataVenda) {
		this.dataVenda = dataVenda;
	}
}
