import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MascotasCrear } from './mascotas-crear';

describe('MascotasCrear', () => {
  let component: MascotasCrear;
  let fixture: ComponentFixture<MascotasCrear>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MascotasCrear]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MascotasCrear);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
