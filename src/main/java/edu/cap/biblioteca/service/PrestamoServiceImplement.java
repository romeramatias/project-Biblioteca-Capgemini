/**
 * @author romeramatias
 */

package edu.cap.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cap.biblioteca.model.Prestamo;
import edu.cap.biblioteca.repository.IPrestamoRepository;

@Service
public class PrestamoServiceImplement implements PrestamoService {

	@Autowired
	IPrestamoRepository prestamosRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Prestamo> listarPrestamos() {
		return this.prestamosRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Prestamo> prestamosPorUsuario() {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Prestamo> prestamosActivos() {
		var allPrestamos = this.listarPrestamos();
		List<Prestamo> prestamos = new ArrayList<>();
		
		for (Prestamo prestamo : allPrestamos) {
			if (prestamo.getFechaFin() == null) {
				prestamos.add(prestamo);
			}
		}
		
		return prestamos;
	}

}
