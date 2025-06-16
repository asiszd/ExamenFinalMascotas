import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Mascota } from '../../Entidad/Mascota';
import { Cliente } from '../../Entidad/Cliente';
import { Responsable } from '../../Entidad/Responsable';
import { Veterinaria } from '../../Entidad/Veterinaria';

@Injectable({
  providedIn: 'root',
})
export class Ws {
  constructor(private http: HttpClient) {}

  urlMascota = 'http://localhost:9000/mascota';
  urlCliente = 'http://localhost:9000/cliente';
  urlResponsable = 'http://localhost:9000/responsable';
  urlVeterinaria = 'http://localhost:9000/veterinaria';

  // CRUD DE MASCOTAS ------------------------------------------------------
  listarMascota() {
    return this.http.get<Mascota[]>(this.urlMascota + '/listar');
  }

  guardarMascota(mascota: Mascota) {
    return this.http.post<String>(this.urlMascota + '/guardar', mascota);
  }

  actualizarMascota(mascota: Mascota) {
    return this.http.put<String>(this.urlMascota + '/editar', mascota);
  }

  eliminarMascota(idMascota: Number) {
    return this.http.delete<void>(this.urlMascota + '/eliminar/' + idMascota);
  }

  buscarMascota(mascota: Mascota) {
    return this.http.post<Mascota>(this.urlMascota + '/buscar', mascota);
  }

  buscarMascotaPorIdCliente(idCliente: Number) {
    return this.http.get<Mascota[]>(this.urlMascota + '/cliente/' + idCliente);
  }

  buscarMascotaPorIdResponsable(idResponsable: Number) {
    return this.http.get<Mascota[]>(
      this.urlMascota + '/responsable/' + idResponsable
    );
  }

  buscarMascotaPorIdVeterinaria(idVeterinaria: Number) {
    return this.http.get<Mascota[]>(
      this.urlMascota + '/veterinaria/' + idVeterinaria
    );
  }

  //CRUD DE CLIENTES ------------------------------------------------------
  listarCliente() {
    return this.http.get<Cliente[]>(this.urlCliente + '/listar');
  }

  guardarCliente(cliente: Cliente) {
    return this.http.post<String>(this.urlCliente + '/guardar', cliente);
  }

  actualizarCliente(cliente: Cliente) {
    return this.http.put<String>(this.urlCliente + '/editar', cliente);
  }

  eliminarCliente(idCliente: Number) {
    return this.http.delete<void>(this.urlCliente + '/eliminar/' + idCliente);
  }

  buscarCliente(cliente: Cliente) {
    return this.http.post<Cliente>(this.urlCliente + '/buscar', cliente);
  }

  //OBTIENE LISTA DE MASCOTAS POR CLIENTE
  mascotasPorCliente(idCliente: Number) {
    return this.http.get<Mascota[]>(
      this.urlCliente + '/mascotas?idCliente=' + idCliente
    );
  }

  //CRUD DE RESPONSABLES ------------------------------------------------------
  listarResponsable() {
    return this.http.get<Responsable[]>(this.urlResponsable + '/listar');
  }

  guardarResponsable(responsable: Responsable) {
    return this.http.post<String>(
      this.urlResponsable + '/guardar',
      responsable
    );
  }

  actualizarResponsable(responsable: Responsable) {
    return this.http.put<String>(this.urlResponsable + '/editar', responsable);
  }

  eliminarResponsable(idResponsable: Number) {
    return this.http.delete<void>(
      this.urlResponsable + '/eliminar/' + idResponsable
    );
  }

  buscarResponsable(responsable: Responsable) {
    return this.http.post<Responsable>(
      this.urlResponsable + '/buscar',
      responsable
    );
  }

  //OBTIENE LISTA DE RESPONSABLES POR ID DE VETERINARIA
  responsablesPorVeterinaria(idVeterinaria: Number) {
    return this.http.get<Responsable[]>(
      this.urlResponsable + '/veterinaria/' + idVeterinaria
    );
  }

  //OBTIENE LISTA DE MASCOTAS POR RESPONSABLE
  mascotasPorResponsable(idResponsable: Number) {
    return this.http.get<Mascota[]>(
      this.urlResponsable + '/mascotas?idResponsable=' + idResponsable
    );
  }

  // CRUD DE VETERINARIA ------------------------------------------------------
  listarVeterinaria() {
    return this.http.get<Veterinaria[]>(this.urlVeterinaria + '/listar');
  }

  guardarVeterinaria(veterinaria: Veterinaria) {
    return this.http.post<String>(
      this.urlVeterinaria + '/guardar',
      veterinaria
    );
  }

  actualizarVeterinaria(veterinaria: Veterinaria) {
    return this.http.put<String>(this.urlVeterinaria + '/editar', veterinaria);
  }

  eliminarVeterinaria(idVeterinaria: Number) {
    return this.http.delete<void>(
      this.urlVeterinaria + '/eliminar/' + idVeterinaria
    );
  }

  buscarVeterinaria(veterinaria: Veterinaria) {
    return this.http.post<Veterinaria>(
      this.urlVeterinaria + '/buscar',
      veterinaria
    );
  }

  //OBTIENE LISTA DE VETERINARIAS POR ID DE VETERINARIA
  veterinariasPorVeterinaria(idVeterinaria: Number) {
    return this.http.get<Veterinaria[]>(
      this.urlVeterinaria + '/veterinaria/' + idVeterinaria
    );
  }

  //OBTIENE LISTA DE MASCOTAS POR VETERINARIA
  mascotasPorVeterinaria(idVeterinaria: Number) {
    return this.http.get<Mascota[]>(
      this.urlVeterinaria + '/mascotas?idVeterinaria=' + idVeterinaria
    );
  }
}
