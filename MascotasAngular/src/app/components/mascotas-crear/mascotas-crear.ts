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
import { Mascota } from '../../../Entidad/Mascota';
import { Router } from '@angular/router';
import { Ws } from '../../service/ws';
import Swal from 'sweetalert2';
import { Cliente } from '../../../Entidad/Cliente';
import { Veterinaria } from '../../../Entidad/Veterinaria';
import { Responsable } from '../../../Entidad/Responsable';

@Component({
  selector: 'app-mascotas-crear',
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
  templateUrl: './mascotas-crear.html',
  styleUrl: './mascotas-crear.css',
})
export class MascotasCrear implements OnInit {
  mascota = new Mascota();
  clientes: Cliente[] = [];
  veterinarias: Veterinaria[] = [];
  responsables: Responsable[] = [];
  filtroResp: Responsable[] = [];
  veterinariaSeleccionadaId: number | null = null;

  constructor(private router: Router, private service: Ws) {}

  ngOnInit(): void {
    this.getClientes();
    this.getResponsables();
    this.getVeterinarias();
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

    // Limpiar responsable seleccionado si ya no coincide
    this.mascota.responsableId = 0;
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
    this.service.guardarMascota(this.mascota).subscribe(
      (data) => {
        Swal.fire({
          title: 'Guardado!',
          icon: 'success',
          text:
            'Se ha dado de alta correctamente a la mascota: ' +
            this.mascota.nombre +
            '!',
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
