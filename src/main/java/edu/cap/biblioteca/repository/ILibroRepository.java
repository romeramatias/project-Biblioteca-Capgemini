/**
 * @author romeramatias
 */

package edu.cap.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cap.biblioteca.model.Libro;

public interface ILibroRepository extends JpaRepository<Libro, Long> {

}
