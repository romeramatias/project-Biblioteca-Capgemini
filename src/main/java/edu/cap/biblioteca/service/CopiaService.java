/**
 * @author romeramatias
 */

package edu.cap.biblioteca.service;

import java.util.List;

import edu.cap.biblioteca.model.Copia;

public interface CopiaService {

	List<Copia> listarCopiasPorLibro(Long idLibro);

	void eliminarCopiasPorLibro(Long idLibro);

	void guardarCopia(Copia copia);

	void eliminar(Copia copia);

	Copia buscarCopia(Copia copia);
}
