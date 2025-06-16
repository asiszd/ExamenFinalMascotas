package com.example.Mascota.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Mascota.Dominio.Mascota;

public interface IMascotaDAO extends JpaRepository<Mascota, Integer>{
	
	public List<Mascota> findAllByClienteId(int clienteId);
	public List<Mascota> findAllByResponsableId(int responsableId);
	public List<Mascota> findAllByVeterinariaId(int veterinariaId);


}
