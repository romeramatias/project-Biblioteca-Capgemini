/**
 * @author romeramatias
 */

package edu.cap.biblioteca.service;

import java.util.List;

import edu.cap.biblioteca.model.Libro;

public interface LibroService {

	List<Libro> listarLibros();

	void guardarLibro(Libro libro);

	void eliminar(Libro libro);

	Libro buscarLibro(Libro libro);

	List<Libro> buscarLibros(String nombre);
}
