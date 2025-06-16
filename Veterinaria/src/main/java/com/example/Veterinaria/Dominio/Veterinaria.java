package com.example.Veterinaria.Dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Veterinaria {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idVeterinaria;
	private String nombre;
	private String direccion;
	private String telefono;
	
}
