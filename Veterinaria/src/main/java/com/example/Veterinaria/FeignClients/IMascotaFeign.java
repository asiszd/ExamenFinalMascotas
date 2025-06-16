package com.example.Veterinaria.FeignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Veterinaria.Entidad.Mascota;

@FeignClient(name="Mascota", url="http://localhost:9000", path="/mascota")
public interface IMascotaFeign {

	@GetMapping("/veterinaria/{veterinariaId}")
	public List<Mascota> buscarPorVeterinariaId(@PathVariable int veterinariaId);
}
