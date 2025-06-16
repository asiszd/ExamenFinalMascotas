import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButton, MatFabButton } from '@angular/material/button';
import {
  MatCard,
  MatCardHeader,
  MatCardTitle,
  MatCardContent,
  MatCardActions,
} from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIcon } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { Router } from '@angular/router';
import { Ws } from '../../service/ws';
import { Responsable } from '../../../Entidad/Responsable';
import { Veterinaria } from '../../../Entidad/Veterinaria';
import { Cliente } from '../../../Entidad/Cliente';
import { Mascota } from '../../../Entidad/Mascota';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-mascotas-editar',
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatIcon,
    MatTabsModule,
    MatCard,
    MatCardHeader,
    MatCardTitle,
    MatButton,
    MatCardContent,
    FormsModule,
    CommonModule,
    MatFormFieldModule,
    MatCardActions,
    MatFabButton,
  ],
  templateUrl: './mascotas-editar.html',
  styleUrl: './mascotas-editar.css',
})
export class MascotasEditar implements OnInit {
  mascota = new Mascota();
  clientes: Cliente[] = [];
  veterinarias: Veterinaria[] = [];
  responsables: Responsable[] = [];
  filtroResp: Responsable[] = [];
  veterinariaSeleccionadaId: number | null = null;
  //respAux!: Number;

  constructor(private router: Router, private service: Ws) {}
  ngOnInit(): void {
    this.obtenerDatosMascota();
    this.getClientes();
    this.getResponsables();
    this.getVeterinarias();
    setTimeout(() => {
      this.onVeterinariaChange(this.mascota.veterinariaId.valueOf());
    }, 900);
  }

  obtenerDatosMascota() {
    this.mascota.idMascota = Number(localStorage.getItem('idMascota'));
    this.service.buscarMascota(this.mascota).subscribe((data) => {
      this.mascota = data;
    });
  }

  regresar() {
    this.router.navigate(['']);
  }

  getClientes() {
    this.service.listarCliente().subscribe((data) => {
      this.clientes = data;
    });
  }

  getVeterinarias() {
    this.service.listarVeterinaria().subscribe((data) => {
      this.veterinarias = data;
    });
  }

  getResponsables() {
    this.service.listarResponsable().subscribe((data) => {
      this.responsables = data;
    });
  }

  onVeterinariaChange(id: number): void {
    this.veterinariaSeleccionadaId = id;
    this.filtroResp = this.responsables.filter((r) => r.veterinariaId == id);
  }

  validaNumeros(event: KeyboardEvent): void {
    const allowedKeys = [
      'Backspace',
      'Tab',
      'ArrowLeft',
      'ArrowRight',
      'Delete', // navegación y punto
    ];

    // Permitir números
    if (
      (event.key >= '0' && event.key <= '9') ||
      allowedKeys.includes(event.key)
    ) {
      return;
    } else {
      event.preventDefault();
    }
  }

  guardar() {
    this.service.actualizarMascota(this.mascota).subscribe(
      (data) => {
        Swal.fire({
          title: 'Guardado!',
          icon: 'success',
          text: '¡Guardado!',
          showConfirmButton: false,
          timer: 3500,
        });
        this.router.navigate(['']);
      },
      (error) => {
        Swal.fire({
          title: 'Ocurrió un error!',
          icon: 'error',
          text: JSON.stringify(error.error.mensaje),
          showConfirmButton: false,
          timer: 3500,
        });
      }
    );
  }
}
