package org.generation.NerdVault.repositories;

import org.generation.NerdVault.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Integer>{

	Utente findByEmail(String email);

}
