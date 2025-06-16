package com.example.Cliente.FeignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Cliente.Entidad.Mascota;


@FeignClient(name="Mascota", url="http://localhost:8013", path="/mascota")
public interface IMascotaFeign {

	@GetMapping("/cliente/{clienteId}")
	public List<Mascota> buscarPorClienteId(@PathVariable int clienteId);
}
