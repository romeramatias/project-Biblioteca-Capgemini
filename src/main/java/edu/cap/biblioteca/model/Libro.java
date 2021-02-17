/**
 * @author romeramatias
 */

package edu.cap.biblioteca.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "libros")
public class Libro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLibro;
	
	@NotEmpty
	private String titulo;
	
	@NotEmpty
	private String editorial;
	
	@NotNull
	private int year;

	@NotNull
	@OneToOne
	@JoinColumn(name = "id_autor")
	private Autor autor;
		
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoLibro tipo;
}
