/**
 * @author romeramatias
 */

package edu.cap.biblioteca.service;

import java.util.List;

import edu.cap.biblioteca.model.Copia;
import edu.cap.biblioteca.model.Libro;
import edu.cap.biblioteca.model.Prestamo;
import edu.cap.biblioteca.model.Usuario;

public interface PrestamoService {

	List<Prestamo> listarPrestamos();

	List<Prestamo> prestamosPorUsuario(Long idUsuario);

	List<Prestamo> prestamosActivos();
	
	Prestamo buscarPrestamo(Prestamo prestamo);
	
	void guardarPrestamo(Libro libro, Copia copia, Usuario usuario);

	void devolucion(Prestamo prestamo);

	List<Prestamo> prestamosActivosPorUsuario(Long idUsuario);

}
