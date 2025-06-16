package com.example.Responable.FeignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Responable.Entidad.Mascota;



@FeignClient(name="Mascota", url="http://localhost:8013", path="/mascota")
public interface IMascotaFeign {

	@GetMapping("/responsable/{responsableId}")
	public List<Mascota> buscarPorResponsableId(@PathVariable int responsableId);
}
