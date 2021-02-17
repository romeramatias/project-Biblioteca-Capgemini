/**
 * @author romeramatias
 */

package edu.cap.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cap.biblioteca.model.Copia;

public interface ICopiaRepository extends JpaRepository<Copia, Long> {

}
