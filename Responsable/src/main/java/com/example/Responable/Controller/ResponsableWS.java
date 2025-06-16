package com.example.Responable.Controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Responable.Dominio.Responsable;
import com.example.Responable.Service.ResponsableServiceImp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path="/responsable")
@Tag(name="RESPONSABLE", description="Micro Servicio de responsables para sistema de mascotas.")
public class ResponsableWS {
	
	@Autowired
	private ResponsableServiceImp service;
	
	@GetMapping("/listar")
	@Operation(summary = "LISTAR RESPONSABLES", description = "Trae un listado de los responsables desde la base de datos")
	public ResponseEntity<?> listar(){
		List<Responsable> responsables = service.listar();
		return responsables.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(responsables);
	}
	
	
	@PostMapping("/guardar")
	@Operation(summary = "GUARDAR RESPONSABLE", description = "Recibe como parametro un responsable para almacenarla en la BD")
	public ResponseEntity<?> guardar(@RequestBody Responsable r){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(r));
				
	}
	
	@PutMapping("/editar")
	@Operation(summary = "EDITAR RESPONSABLE", description = "Recibe como parametro un responsable para sobreescribirla en la base de datos")
	public ResponseEntity<?> editar(@RequestBody Responsable r){
		Responsable existe = service.buscar(r.getIdResponsable());
		return (existe != null) ? ResponseEntity.ok(service.guardar(r))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"mensaje\":\"No existe el responsable con el ID: " + r.getIdResponsable() + ".\"}");			
	}
	
	@PostMapping("/buscar")
	@Operation(summary = "BUSCAR RESPONSABLE POR ID", description = "Toma como parámetro un ID por medio de la url como idResponsable.")
	public ResponseEntity<?> buscar(@RequestBody Responsable responsable){
		return ResponseEntity.ok(service.buscar(responsable.getIdResponsable()));
	}
	
	@DeleteMapping("/eliminar/{idResponsable}")
	@Operation(summary = "ELIMINAR RESPONSABLE POR ID", description = "Toma como parámetro un id por medio de la url para eliminar el responsable de la base de datos.")
	public ResponseEntity<?> eliminar(@PathVariable int idResponsable){
		service.eliminar(idResponsable);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/veterinaria/{veterinariaId}")
	@Operation(summary="BUSCAR RESPONSABLES POR ID DE LA VETERINARIA", description="Realiza una busqueda de responsables por medio del id de la veterinaria como un @PathVariable")
	public ResponseEntity<?> buscarPorVeterinariaId(@PathVariable int veterinariaId){
		return ResponseEntity.ok(service.porVeterinariaId(veterinariaId));
	}
	
	@GetMapping("mascotas")
	@Operation(summary = "LISTA DE MASCOTAS POR ID DEL RESPONSABLE", description = "Recibe como parámetro el id de un responsable y devuelve una lista de mascotas.")
	public ResponseEntity<?> mascotas(@RequestParam int idResponsable){
		HashMap<String, Object> hash = service.listaMascotas(idResponsable);
		return ResponseEntity.ok(hash);
	}

}
