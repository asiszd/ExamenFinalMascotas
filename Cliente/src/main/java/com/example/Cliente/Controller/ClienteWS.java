package com.example.Cliente.Controller;

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

import com.example.Cliente.Dominio.Cliente;
import com.example.Cliente.Service.ClienteServiceImp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path="/cliente")
@Tag(name="CLIENTE", description="Micro Servicio de clientes para sistema de mascotas.")
public class ClienteWS {

	
	@Autowired
	private ClienteServiceImp service;
	
	@GetMapping("/listar")
	@Operation(summary = "LISTAR CLIENTES", description = "Trae un listado de las clientes desde la base de datos")
	public ResponseEntity<?> listar(){
		List<Cliente> clientes = service.listar();
		return clientes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(clientes);
	}
	
	
	@PostMapping("/guardar")
	@Operation(summary = "GUARDAR CLIENTE", description = "Recibe como parametro una cliente para almacenarla en la BD")
	public ResponseEntity<?> guardar(@RequestBody Cliente c){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(c));
				
	}
	
	@PutMapping("/editar")
	@Operation(summary = "EDITAR CLIENTE", description = "Recibe como parametro una cliente para sobreescribirla en la base de datos")
	public ResponseEntity<?> editar(@RequestBody Cliente c){
		Cliente existe = service.buscar(c.getIdCliente());
		return (existe != null) ? ResponseEntity.ok(service.guardar(c))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"mensaje\":\"No existe el cliente con el ID: " + c.getIdCliente() + ".\"}");			
	}
	
	@PostMapping("/buscar")
	@Operation(summary = "BUSCAR CLIENTE POR ID", description = "Toma como parámetro un ID por medio de la url como idCliente.")
	public ResponseEntity<?> buscar(@RequestBody Cliente cliente){
		return ResponseEntity.ok(service.buscar(cliente.getIdCliente()));
	}
	
	@DeleteMapping("/eliminar/{idCliente}")
	@Operation(summary = "ELIMINAR CLIENTE POR ID", description = "Toma como parámetro un id por medio de la url para eliminar la cliente de la base de datos.")
	public ResponseEntity<?> eliminar(@PathVariable int idCliente){
		service.eliminar(idCliente);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("mascotas")
	@Operation(summary = "LISTA DE MASCOTAS POR ID DEL CLIENTE", description = "Recibe como parámetro el id de un cliente y devuelve una lista de mascotas ")
	public ResponseEntity<?> mascotas(@RequestParam int idCliente){
		HashMap<String, Object> hash = service.listaMascotas(idCliente);
		return ResponseEntity.ok(hash);
	}
	
}
