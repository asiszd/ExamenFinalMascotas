package com.example.Veterinaria.FeignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.Veterinaria.Entidad.Responsable;

@FeignClient(name="Responsable", url="http://localhost:9000", path="/responsable")
public interface IResponsableFeign {

	@GetMapping("/veterinaria/{veterinariaId}")
	public List<Responsable> buscarPorVeterinariaId(@PathVariable int veterinariaId);
	
}
