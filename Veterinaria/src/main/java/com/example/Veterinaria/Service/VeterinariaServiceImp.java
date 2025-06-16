package com.example.Veterinaria.Service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Veterinaria.DAO.IVeterinariaDAO;
import com.example.Veterinaria.Dominio.Veterinaria;
import com.example.Veterinaria.Entidad.Mascota;
import com.example.Veterinaria.Entidad.Responsable;
import com.example.Veterinaria.FeignClients.IMascotaFeign;
import com.example.Veterinaria.FeignClients.IResponsableFeign;

@Service
public class VeterinariaServiceImp implements IVeterinariaService{
	
	@Autowired
	private IVeterinariaDAO dao;
	
	@Autowired
	private IMascotaFeign mascotaFC;
	
	@Autowired
	private IResponsableFeign responsableFC;

	@Override
	public Veterinaria guardar(Veterinaria v) {
		
		return dao.save(v);
	}

	@Override
	public List<Veterinaria> listar() {
		
		return dao.findAll(Sort.by(Sort.Direction.ASC, "idVeterinaria"));
	}

	@Override
	public Veterinaria buscar(int idVeterinaria) {
		return dao.findById(idVeterinaria).orElse(null);
	}

	@Override
	public void eliminar(int idVeterinaria) {
		dao.deleteById(idVeterinaria);	
	}
	
	public HashMap<String, Object> listaMascotas(int veterinariaId){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		Veterinaria existe = dao.findById(veterinariaId).orElse(null);
		if(existe != null) {
			hash.put("veterinaria", existe);
			List<Mascota> mascotas = mascotaFC.buscarPorVeterinariaId(veterinariaId);
			if(mascotas.isEmpty()) {
				hash.put("mensaje", "No hay mascotas registradas");
			} else {
				hash.put("mascotas", mascotas);
			}
		}
		return hash;
	}
	
	
	public HashMap<String, Object> listaResponsables(int veterinariaId){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		Veterinaria existe = dao.findById(veterinariaId).orElse(null);
		if(existe != null) {
			hash.put("veterinaria", existe);
			List<Responsable> responables = responsableFC.buscarPorVeterinariaId(veterinariaId);
			if(responables.isEmpty()) {
				hash.put("mensaje", "No hay responsables registrados");
			} else {
				hash.put("responsables", responables);
			}
		}
		return hash;
	}

}
