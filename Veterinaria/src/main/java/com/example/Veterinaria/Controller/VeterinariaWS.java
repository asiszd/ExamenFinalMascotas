package com.example.Veterinaria.Controller;

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

import com.example.Veterinaria.Dominio.Veterinaria;
import com.example.Veterinaria.Service.VeterinariaServiceImp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping(path="/veterinaria")
@Tag(name="VETERINARIA", description="Micro Servicio de veterinarias para sistema de mascotas.")
public class VeterinariaWS {
	
	@Autowired
	private VeterinariaServiceImp service;
	
	@GetMapping("/listar")
	@Operation(summary = "LISTAR VETERINARIAS", description = "Trae un listado de las veterinarias desde la base de datos")
	public ResponseEntity<?> listar(){
		List<Veterinaria> veterinarias = service.listar();
		return veterinarias.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(veterinarias);
	}
	
	
	@PostMapping("/guardar")
	@Operation(summary = "GUARDAR VETERINARIA", description = "Recibe como parametro una veterinaria para almacenarla en la BD")
	public ResponseEntity<?> guardar(@RequestBody Veterinaria c){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(c));
				
	}
	
	@PutMapping("/editar")
	@Operation(summary = "EDITAR VETERINARIA", description = "Recibe como parametro una veterinaria para sobreescribirla en la base de datos")
	public ResponseEntity<?> editar(@RequestBody Veterinaria c){
		Veterinaria existe = service.buscar(c.getIdVeterinaria());
		return (existe != null) ? ResponseEntity.ok(service.guardar(c))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"mensaje\":\"No existe el veterinaria con el ID: " + c.getIdVeterinaria() + ".\"}");			
	}
	
	@PostMapping("/buscar")
	@Operation(summary = "BUSCAR VETERINARIA POR ID", description = "Toma como parámetro un ID por medio de la url como idVeterinaria.")
	public ResponseEntity<?> buscar(@RequestBody Veterinaria veterinaria){
		return ResponseEntity.ok(service.buscar(veterinaria.getIdVeterinaria()));
	}
	
	@DeleteMapping("/eliminar/{idVeterinaria}")
	@Operation(summary = "ELIMINAR VETERINARIA POR ID", description = "Toma como parámetro un id por medio de la url para eliminar la veterinaria de la base de datos.")
	public ResponseEntity<?> eliminar(@PathVariable int idVeterinaria){
		service.eliminar(idVeterinaria);
		return ResponseEntity.noContent().build();
	}
	

	@GetMapping("mascotas")
	@Operation(summary = "LISTA DE MASCOTAS POR ID DE LA VETERINARIA", description = "Recibe como parámetro el id de una veterinaria y devuelve una lista de mascotas.")
	public ResponseEntity<?> mascotas(@RequestParam int idVeterinaria){
		HashMap<String, Object> hash = service.listaMascotas(idVeterinaria);
		return ResponseEntity.ok(hash);
	}
	
	
	@GetMapping("responsables")
	@Operation(summary = "LISTA DE RESPONABLES POR ID DE LA VETERINARIA", description = "Recibe como parámetro el id de una veterinaria y devuelve una lista de los responsables asignados.")
	public ResponseEntity<?> responsables(@RequestParam int idVeterinaria){
		HashMap<String, Object> hash = service.listaResponsables(idVeterinaria);
		return ResponseEntity.ok(hash);
	}

}
