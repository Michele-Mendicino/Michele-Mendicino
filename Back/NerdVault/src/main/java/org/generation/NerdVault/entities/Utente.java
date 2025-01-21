package org.generation.NerdVault.entities;

//import java.sql.Date;
import java.time.LocalDate;

import org.generation.NerdVault.enums.UtenteRuolo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //Indica che la classe è una entità JPA e corrisponde a una tabella nel database. Il nome della tabella è specificato con @Table(name = "utente").
@Table(name = "utente") //Specifica il nome della tabella nel database. È utile se il nome della classe (Utente) e il nome della tabella (utente) non coincidono.
public class Utente {
	
	@Id //Indica che il campo utenteId è la chiave primaria della tabella.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Specifica che il valore della chiave primaria sarà generato automaticamente dal database. La strategia IDENTITY è utile per database come MySQL, dove la chiave primaria viene generata come un valore incrementale
	private int utenteId;
	
	@Column(length = 75, nullable = true) //configurazione delle proprietà della singola colonna 
	private String nome;

	@Column(length = 75, nullable = true)
	private String cognome;

	@Column(nullable = true)
	private LocalDate dataNascita;

	@Column(length = 50, nullable = false, unique = true)
	private String email;
	
	@JsonIgnore // Questa annotazione indica che il campo password non deve essere incluso nella risposta JSON quando l'entità viene serializzata (es. in un'API REST)
	@Column(length = 20, nullable = false)
	private String password;
	
	@Column(columnDefinition = "ENUM('ADMIN', 'UTENTE')", nullable = false) //Serve a definire esplicitamente che il tipo della colonna è un ENUM. Non è sempre necessario, ma è utile per garantire compatibilità con il database.

	@Enumerated(EnumType.STRING) // Indica che il campo ruolo è un enum e che il valore sarà rappresentato comunque come una stringa
	private UtenteRuolo ruolo;

	/*------------------ Getters / Setters ------------------*/
	
	public int getUtenteId() {
		return utenteId;
	}

	public void setUtenteId(int utenteId) {
		this.utenteId = utenteId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UtenteRuolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(UtenteRuolo ruolo) {
		this.ruolo = ruolo;
	}

}
