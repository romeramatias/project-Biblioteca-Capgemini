/**
 * @author romeramatias
 */

package edu.cap.biblioteca.service;

import java.util.List;

import edu.cap.biblioteca.model.Copia;
import edu.cap.biblioteca.model.EstadoCopia;

public interface CopiaService {

	List<Copia> listarCopias();
	
	List<Copia> listarCopiasPorLibro(Long idLibro);
	
	List<Copia> listarCopiasPorEstado(Long idLibro, EstadoCopia estado);

	void eliminarCopiasPorLibro(Long idLibro);

	void guardarCopia(Copia copia);

	void eliminar(Copia copia);

	Copia buscarCopia(Copia copia);
}
