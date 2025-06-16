package com.example.Responable.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Responable.Dominio.Responsable;
import java.util.List;


public interface IResponsdableDAO extends JpaRepository<Responsable, Integer>{

	public List<Responsable> findAllByVeterinariaId(int veterinariaId);
}
