import { CurrencyPipe, CommonModule } from '@angular/common';
import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatFabButton, MatButton } from '@angular/material/button';
import {
  MatCard,
  MatCardHeader,
  MatCardTitle,
  MatCardContent,
} from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIcon } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { Mascota } from '../../../Entidad/Mascota';
import { Ws } from '../../service/ws';
import { Router } from '@angular/router';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { Cliente } from '../../../Entidad/Cliente';
import { Responsable } from '../../../Entidad/Responsable';
import { Veterinaria } from '../../../Entidad/Veterinaria';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-mascotas-listar',
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatIcon,
    MatFabButton,
    MatTabsModule,
    MatCard,
    MatCardHeader,
    MatCardTitle,
    MatButton,
    MatCardContent,
    CommonModule,
  ],
  templateUrl: './mascotas-listar.html',
  styleUrl: './mascotas-listar.css',
})
export class MascotasListar implements AfterViewInit {
  displayedColumns: string[] = [
    'idMascota',
    'mascota',
    'cliente',
    'responsable',
    'veterinaria',
    'editar',
    'eliminar',
  ];

  mascotas: Mascota[] = [];
  dataSource!: MatTableDataSource<Mascota>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  isModalOpen = false;
  cliente = new Cliente();
  responsable = new Responsable();
  veterinaria = new Veterinaria();

  clientesMap: { [id: number]: string } = {};
  responsablesMap: { [id: number]: string } = {};
  veterinariasMap: { [id: number]: string } = {};

  constructor(
    private service: Ws,
    private router: Router,
    private sanitizer: DomSanitizer
  ) {}

  ngAfterViewInit(): void {
    this.listarMascotas();
  }

  listarMascotas() {
    this.service.listarMascota().subscribe((data) => {
      this.mascotas = data;
      //guarda listas de los ids que se van a buscar
      const clienteIds = new Set<number>();
      const responsableIds = new Set<number>();
      const veterinariaIds = new Set<number>();

      this.mascotas.forEach((m) => {
        if (m.clienteId) clienteIds.add(m.clienteId.valueOf());
        if (m.responsableId) responsableIds.add(m.responsableId.valueOf());
        if (m.veterinariaId) veterinariaIds.add(m.veterinariaId.valueOf());
      });

      // Recorre cada lista para precargar los datos de cliente, vet y responsable
      clienteIds.forEach((id) => {
        this.cliente.idCliente = id;
        this.service.buscarCliente(this.cliente).subscribe((data) => {
          this.clientesMap[id] = `${data.nombre} <br> ${data.contacto}`;
        });
      });

      responsableIds.forEach((id) => {
        this.responsable.idResponsable = id;
        this.service.buscarResponsable(this.responsable).subscribe((data) => {
          this.responsablesMap[id] = `${data.nombre}`;
        });
      });

      veterinariaIds.forEach((id) => {
        this.veterinaria.idVeterinaria = id;
        this.service.buscarVeterinaria(this.veterinaria).subscribe((data) => {
          this.veterinariasMap[id] = `${data.nombre}`;
        });
      });

      this.dataSource = new MatTableDataSource(this.mascotas);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  getCliente(idCliente: number): String {
    return this.clientesMap[idCliente];
  }

  getResponsable(id: number): string {
    return this.responsablesMap[id];
  }

  getVeterinaria(id: number): string {
    return this.veterinariasMap[id];
  }

  filtroNombre(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  editar(mascota: Mascota) {
    localStorage.setItem('idMascota', mascota.idMascota.toString());
    this.router.navigate(['editarMascota']);
  }

  crear() {
    this.router.navigate(['crearMascota']);
  }

  eliminar(mascota: Mascota) {
    Swal.fire({
      title:
        '¿Desea eliminar el registro de la mascota <b>' +
        mascota.nombre +
        '</b>?',
      text: 'Esta acción no se podrá revertir',
      icon: 'warning',
      showCancelButton: true,
      cancelButtonText: 'Cancelar',
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Eliminar',
    }).then((result) => {
      if (result.isConfirmed) {
        this.service.eliminarMascota(mascota.idMascota).subscribe(
          (data) => {
            Swal.fire({
              title:
                'Se ha eliminado el registro de la mascota ' +
                mascota.nombre +
                ' correctamente!',
              icon: 'success',
              timer: 3500,
            });
            this.listarMascotas();
          },
          (error) => {
            Swal.fire({
              title: 'Ocurrió un error!',
              icon: 'error',
              text:
                'No se ha podido eliminar el registro de la mascora ' +
                mascota.nombre +
                ', por favor contacte al administrador.',
              showConfirmButton: false,
              timer: 4500,
            });
          }
        );
      }
    });
  }
}
