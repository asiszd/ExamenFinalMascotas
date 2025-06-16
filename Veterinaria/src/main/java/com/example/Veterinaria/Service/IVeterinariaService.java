package com.example.Veterinaria.Service;

import java.util.List;

import com.example.Veterinaria.Dominio.Veterinaria;


public interface IVeterinariaService {
	
	public Veterinaria guardar(Veterinaria v);
	
	public List<Veterinaria> listar();
	
	public Veterinaria buscar(int idVeterinaria);
		
	public void eliminar(int idVeterinaria);

}
