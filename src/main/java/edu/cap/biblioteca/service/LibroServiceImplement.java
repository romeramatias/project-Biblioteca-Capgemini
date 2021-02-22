/**
 * @author romeramatias
 */

package edu.cap.biblioteca.service;

import java.util.ArrayList;
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

	@Transactional(readOnly = true)
	@Override
	public List<Libro> buscarLibros(String nombre) {
		var allLibros = this.listarLibros();
		List<Libro> libros = new ArrayList<>();

		if (nombre.isBlank()) {
			return allLibros;
		}
		
		for (Libro libro : allLibros) {
			if (libro.toString().toUpperCase().contains(nombre.toUpperCase())) {
//			if (libro.getTitulo().toUpperCase().contains(nombre.toUpperCase())) {
				libros.add(libro);
			}
		}

		return libros;
	}
}
