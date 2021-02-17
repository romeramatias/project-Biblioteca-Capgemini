/**
 * @author romeramatias
 */

package edu.cap.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cap.biblioteca.model.Libro;
import edu.cap.biblioteca.repository.ILibroRepository;

@Service
public class LibroServiceImplement implements LibroService {

	@Autowired
	private ILibroRepository librosRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Libro> listarLibros() {
		return this.librosRepository.findAll();
	}

	@Override
	@Transactional
	public void guardarLibro(Libro libro) {
		this.librosRepository.save(libro);
	}

	@Override
	@Transactional
	public void eliminar(Libro libro) {
		this.librosRepository.delete(libro);
	}

	@Override
	@Transactional(readOnly = true)
	public Libro buscarLibro(Libro libro) {
		return this.librosRepository.findById(libro.getIdLibro()).orElse(null);
	}
}
