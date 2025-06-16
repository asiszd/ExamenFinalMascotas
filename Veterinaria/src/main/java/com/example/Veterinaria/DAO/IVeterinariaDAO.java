package com.example.Veterinaria.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Veterinaria.Dominio.Veterinaria;

public interface IVeterinariaDAO extends JpaRepository<Veterinaria, Integer>{

}
