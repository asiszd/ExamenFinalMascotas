package com.example.Cliente.Service;

import java.util.List;

import com.example.Cliente.Dominio.Cliente;

public interface IClienteService {
	
	public Cliente guardar(Cliente c);
	
	public List<Cliente> listar();
	
	public Cliente buscar(int idCliente);
		
	public void eliminar(int idCliente);

}
