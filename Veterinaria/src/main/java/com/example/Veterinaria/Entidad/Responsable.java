package com.example.Veterinaria.Entidad;

import lombok.Data;

@Data
public class Responsable {
	
	private int idResponsable;
	private String nombre;
	private String contacto;
	private int veterinariaId;

}
