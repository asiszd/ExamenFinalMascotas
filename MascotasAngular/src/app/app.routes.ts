import { Routes } from '@angular/router';
import { MascotasListar } from './components/mascotas-listar/mascotas-listar';
import { MascotasCrear } from './components/mascotas-crear/mascotas-crear';
import { MascotasEditar } from './components/mascotas-editar/mascotas-editar';

export const routes: Routes = [
  {
    path: '',
    component: MascotasListar,
  },
  {
    path: 'crearMascota',
    component: MascotasCrear,
  },
  {
    path: 'editarMascota',
    component: MascotasEditar,
  },
];
