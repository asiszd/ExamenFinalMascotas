import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MascotasListar } from './mascotas-listar';

describe('MascotasListar', () => {
  let component: MascotasListar;
  let fixture: ComponentFixture<MascotasListar>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MascotasListar]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MascotasListar);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
