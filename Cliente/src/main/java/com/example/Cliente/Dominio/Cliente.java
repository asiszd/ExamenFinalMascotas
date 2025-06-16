package com.example.Cliente.Dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cliente {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idCliente;
	private String nombre;
	private String direccion;
	private String contacto;
	
}
