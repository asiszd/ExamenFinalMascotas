package com.example.Responable.Dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Responsable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="responsable_seq")
	@SequenceGenerator(name="responsable_seq", sequenceName = "S_ID_RESPONSABLE", allocationSize = 1)
	private int idResponsable;

	@Column
	private String nombre;
	
	@Column
	private String contacto;
	
	@Column
	private int veterinariaId;
}
