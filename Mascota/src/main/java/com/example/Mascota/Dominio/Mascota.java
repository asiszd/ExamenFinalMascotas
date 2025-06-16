package com.example.Mascota.Dominio;
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
public class Mascota {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="mascota_seq")
	@SequenceGenerator(name="mascota_seq", sequenceName = "S_ID_MASCOTA", allocationSize = 1)
	private int idMascota;
	
	@Column
	private String nombre;
	
	@Column
	private String raza;
	
	@Column
	private int edad;
	
	@Column
	private String razonCita;
	
	@Column
	private int clienteId;
	
	@Column
	private int responsableId;
	
	@Column
	private int veterinariaId;
}
