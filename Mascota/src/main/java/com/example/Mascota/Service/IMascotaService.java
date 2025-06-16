package com.example.Mascota.Service;

import java.util.List;

import com.example.Mascota.Dominio.Mascota;

public interface IMascotaService {
	
	public Mascota guardar(Mascota m);
	
	public List<Mascota> listar();
	
	public Mascota buscar(int idMascota);
		
	public void eliminar(int idMascota);
	

}
