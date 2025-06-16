package com.example.Cliente.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Cliente.Dominio.Cliente;

public interface IClienteDAO  extends JpaRepository<Cliente, Integer>{
	

}
