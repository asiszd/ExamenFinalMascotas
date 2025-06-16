package com.example.Mascota.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Mascota.DAO.IMascotaDAO;
import com.example.Mascota.Dominio.Mascota;

@Service
public class MascotaServiceImp implements IMascotaService{

	@Autowired
	private IMascotaDAO dao;

	@Override
	public Mascota guardar(Mascota m) {
		return dao.save(m);
	}

	@Override
	public List<Mascota> listar() {
		return dao.findAll(Sort.by(Sort.Direction.ASC, "idMascota"));
	}

	@Override
	public Mascota buscar(int idMascota) {
		return dao.findById(idMascota).orElse(null);
	}

	@Override
	public void eliminar(int idMascota) {
		dao.deleteById(idMascota);
	}
	
	
	public List<Mascota> porClienteId(int clienteId){
		return dao.findAllByClienteId(clienteId);
	}
	
	public List<Mascota> porReponsableId(int responsableId){
		return dao.findAllByResponsableId(responsableId);
	}
	
	public List<Mascota> porVeterinariaId(int veterinariaId){
		return dao.findAllByVeterinariaId(veterinariaId);
	}
	
}
