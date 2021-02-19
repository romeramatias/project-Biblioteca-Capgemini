/**
 * @author romeramatias
 */

package edu.cap.biblioteca.service;

import java.util.List;

import edu.cap.biblioteca.model.Prestamo;

public interface PrestamoService {

	List<Prestamo> listarPrestamos();
	
	List<Prestamo> prestamosPorUsuario();
	
	
}
