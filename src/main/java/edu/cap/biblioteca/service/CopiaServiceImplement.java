/**
 * @author romeramatias
 */

package edu.cap.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cap.biblioteca.model.Copia;
import edu.cap.biblioteca.model.EstadoCopia;
import edu.cap.biblioteca.repository.ICopiaRepository;

@Service
public class CopiaServiceImplement implements CopiaService {

	@Autowired
	ICopiaRepository copiasRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Copia> listarCopiasPorLibro(Long idLibro) {
		var allCopias = this.copiasRepository.findAll();
		List<Copia> copias = new ArrayList<>();

		for (Copia copia : allCopias) {
			if (copia.getIdLibro() == idLibro) {
				copias.add(copia);
			}
		}

		return copias;
	}

	@Override
	@Transactional
	public void eliminar(Copia copia) {
		this.copiasRepository.delete(copia);
	}

	@Override
	@Transactional(readOnly = true)
	public Copia buscarCopia(Copia copia) {
		return this.copiasRepository.findById(copia.getIdCopia()).orElse(null);
	}

	@Override
	@Transactional
	public void guardarCopia(Copia copia) {
		copia.setEstado(EstadoCopia.BIBLIOTECA);
		this.copiasRepository.save(copia);
	}

	@Override
	@Transactional
	public void eliminarCopiasPorLibro(Long idLibro) {
		var copiasLibro = listarCopiasPorLibro(idLibro);

		for (Copia copia : copiasLibro) {
			this.eliminar(copia);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Copia> listarCopias() {
		return this.copiasRepository.findAll();
	}

}
