package com.example.Responable.Service;

import java.util.List;

import com.example.Responable.Dominio.Responsable;


public interface IResponsableService {

	public Responsable guardar(Responsable r);
	
	public List<Responsable> listar();
	
	public Responsable buscar(int idResponsable);
		
	public void eliminar(int idResponsable);
	
}
