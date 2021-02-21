/**
 * @author romeramatias
 */

package edu.cap.biblioteca.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "prestamos")
public class Prestamo implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int LIMITE_PRESTAMOS = 3;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPrestamo;

	@NotNull
	private LocalDate fechaInicio;

	private LocalDate fechaFin;

	@NotNull
	@OneToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@NotNull
	@OneToOne
	@JoinColumn(name = "id_libro")
	private Libro libro;

	@NotNull
	@OneToOne
	@JoinColumn(name = "id_copia")
	private Copia copia;
}
