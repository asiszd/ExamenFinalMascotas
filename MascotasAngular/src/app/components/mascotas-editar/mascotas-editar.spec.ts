import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MascotasEditar } from './mascotas-editar';

describe('MascotasEditar', () => {
  let component: MascotasEditar;
  let fixture: ComponentFixture<MascotasEditar>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MascotasEditar]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MascotasEditar);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
