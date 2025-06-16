package com.example.Mascota.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Mascota.Dominio.Mascota;
import com.example.Mascota.Service.MascotaServiceImp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path="/mascota")
@Tag(name="MASCOTA", description="Micro Servicio de mascotas para sistema de mascotas.")
public class MascotaWS {
	
	@Autowired
	private MascotaServiceImp service;
	
	@GetMapping("/listar")
	@Operation(summary = "LISTAR MASCOTAS", description = "Trae un listado de las mascotas desde la base de datos")
	public ResponseEntity<?> listar(){
		List<Mascota> mascotas = service.listar();
		return mascotas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(mascotas);
	}
	
	
	@PostMapping("/guardar")
	@Operation(summary = "GUARDAR MASCOTA", description = "Recibe como parametro una mascota para almacenarla en la BD")
	public ResponseEntity<?> guardar(@RequestBody Mascota m){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(m));
				
	}
	
	@PutMapping("/editar")
	@Operation(summary = "EDITAR MASCOTA", description = "Recibe como parametro una mascota para sobreescribirla en la base de datos")
	public ResponseEntity<?> editar(@RequestBody Mascota m){
		Mascota existe = service.buscar(m.getIdMascota());
		return (existe != null) ? ResponseEntity.ok(service.guardar(m))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"mensaje\":\"No existe la mascota con el ID: " + m.getIdMascota() + ".\"}");			
	}
	
	@PostMapping("/buscar")
	@Operation(summary = "BUSCAR MASCOTA POR ID", description = "Toma como parámetro un ID por medio de la url como idMascota.")
	public ResponseEntity<?> buscar(@RequestBody Mascota mascota){
		return ResponseEntity.ok(service.buscar(mascota.getIdMascota()));
	}
	
	@DeleteMapping("/eliminar/{idMascota}")
	@Operation(summary = "ELIMINAR MASCOTA POR ID", description = "Toma como parámetro un id por medio de la url para eliminar la mascota de la base de datos.")
	public ResponseEntity<?> eliminar(@PathVariable int idMascota){
		service.eliminar(idMascota);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/cliente/{clienteId}")
	@Operation(summary="BUSCAR MASCOTAS POR ID DEL CLIENTE", description="Realiza una busqueda por medio del id del cliente como un @PathVariable")
	public ResponseEntity<?> buscarPorClienteId(@PathVariable int clienteId){
		return ResponseEntity.ok(service.porClienteId(clienteId));
	}
	
	@GetMapping("/responsable/{responsableId}")
	@Operation(summary="BUSCAR MASCOTAS POR ID DEL RESPONSABLE", description="Realiza una busqueda por medio del id del responsable como un @PathVariable")
	public ResponseEntity<?> buscarPorResponsableId(@PathVariable int responsableId){
		return ResponseEntity.ok(service.porReponsableId(responsableId));
	}
	
	@GetMapping("/veterinaria/{veterinariaId}")
	@Operation(summary="BUSCAR MASCOTAS POR ID DE LA VETERINARIA", description="Realiza una busqueda por medio del id de la veterinaria como un @PathVariable")
	public ResponseEntity<?> buscarPorVeterinariaId(@PathVariable int veterinariaId){
		return ResponseEntity.ok(service.porVeterinariaId(veterinariaId));
	}
}
