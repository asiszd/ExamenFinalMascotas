package com.example.Responable.Service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Responable.DAO.IResponsdableDAO;
import com.example.Responable.Dominio.Responsable;
import com.example.Responable.Entidad.Mascota;
import com.example.Responable.FeignClients.IMascotaFeign;


@Service
public class ResponsableServiceImp implements IResponsableService{

	@Autowired
	private IResponsdableDAO dao;
	
	@Autowired
	private IMascotaFeign mascotaFC;

	@Override
	public Responsable guardar(Responsable r) {
		return dao.save(r);
	}

	@Override
	public List<Responsable> listar() {
		return dao.findAll(Sort.by(Sort.Direction.ASC, "idResponsable"));
	}

	@Override
	public Responsable buscar(int idResponsable) {
		return dao.findById(idResponsable).orElse(null);
	}

	@Override
	public void eliminar(int idResponsable) {
		dao.deleteById(idResponsable);
		
	}
	
	public List<Responsable> porVeterinariaId(int veterinariaId){
		return dao.findAllByVeterinariaId(veterinariaId);
	}
	
	public HashMap<String, Object> listaMascotas(int responsableId){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		Responsable existe = dao.findById(responsableId).orElse(null);
		if(existe != null) {
			hash.put("responsable", existe);
			List<Mascota> mascotas = mascotaFC.buscarPorResponsableId(responsableId);
			if(mascotas.isEmpty()) {
				hash.put("mensaje", "No tiene mascotas registradas");
			} else {
				hash.put("mascotas", mascotas);
			}
		}
		return hash;
	}
	
	
	
}
