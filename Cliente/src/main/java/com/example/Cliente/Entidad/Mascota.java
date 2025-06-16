package com.example.Cliente.Entidad;

import lombok.Data;

@Data
public class Mascota {

private int idMascota;
	
	private String nombre;
	private String raza;
	private int edad;
	private String razonCita;
	private int clienteId;
	private int responsableId;
	private int veterinariaId;
	
}
