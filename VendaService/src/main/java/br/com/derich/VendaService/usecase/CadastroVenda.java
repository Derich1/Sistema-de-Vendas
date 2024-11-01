/**
 * 
 */
package br.com.derich.VendaService.usecase;

import java.math.BigDecimal;
import java.util.HashSet;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.derich.VendaService.domain.Produto;
import br.com.derich.VendaService.domain.Venda;
import br.com.derich.VendaService.domain.Venda.Status;
import br.com.derich.VendaService.dto.VendaDTO;
import br.com.derich.VendaService.exception.EntityNotFoundException;
import br.com.derich.VendaService.repository.IVendaRepository;
import br.com.derich.VendaService.service.ClienteService;
import br.com.derich.VendaService.service.IProdutoService;

@Service
public class CadastroVenda {
	
	private IVendaRepository vendaRepository;
	
	private IProdutoService produtoService;
	
	private ClienteService clienteService;
	
	@Autowired
	public CadastroVenda(IVendaRepository produtoRepository,
			IProdutoService produtoService,
			ClienteService clienteService) {
		this.vendaRepository = produtoRepository;
		this.produtoService = produtoService;
		this.clienteService = clienteService;
	}
	
	public Venda cadastrar(@Valid VendaDTO vendaDTO) {
		Venda venda = convertToDomain(vendaDTO, Status.INICIADA);
		validarCliente(venda.getClienteId());
		venda.recalcularValorTotalVenda();
		return this.vendaRepository.insert(venda);
	}

	private void validarCliente(String clienteId) {
		Boolean isCadastrado = 
				this.clienteService.isClienteCadastrado(clienteId);
		if (!isCadastrado) {
			new EntityNotFoundException(Venda.class, "clienteId", clienteId);
		}
	}

	private Venda convertToDomain(@Valid VendaDTO vendaDTO, Status status) {
		Venda venda = new Venda(
				null, // id pode ser null se você usar autoincremento
				vendaDTO.getCodigo(),
				vendaDTO.getClienteId(),
				new HashSet<>(), // Inicia com um conjunto vazio
				BigDecimal.ZERO, // Inicializa com 0
				vendaDTO.getDataVenda(),
				status // Usar o status passado como parâmetro
		);

		return venda; // Retorne a venda criada
	}


	public Venda atualizar(@Valid Venda venda) {
		return this.vendaRepository.save(venda);
	}

	public Venda finalizar(String id) {
		Venda venda = buscarVenda(id);
		venda.validarStatus();
		venda.setStatus(Status.CONCLUIDA);
		return this.vendaRepository.save(venda);
	}
	
	public Venda cancelar(String id) {
		Venda venda = buscarVenda(id);
		venda.validarStatus();
		venda.setStatus(Status.CANCELADA);
		return this.vendaRepository.save(venda);
	}

	public Venda adicionarProduto(String id, String codigoProduto, Integer quantidade) {
		Venda venda = buscarVenda(id);
		Produto produto = buscarProduto(codigoProduto);
		venda.validarStatus();
		venda.adicionarProduto(produto, quantidade);
		return this.vendaRepository.save(venda);
	}
	
	public Venda removerProduto(String id, String codigoProduto, Integer quantidade) {
		Venda venda = buscarVenda(id);
		Produto produto = buscarProduto(codigoProduto);
		venda.validarStatus();
		venda.removerProduto(produto, quantidade);
		return this.vendaRepository.save(venda);
	}
	
	private Venda buscarVenda(String id) {
		return vendaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Venda.class, "id", id));
		
	}
	
	private Produto buscarProduto(String codigoProduto) {
		Produto prod = produtoService.buscarProduto(codigoProduto);
		if (prod == null) {
			throw new EntityNotFoundException(Produto.class, "codigo", codigoProduto);
		}
		return prod;
	}

}
