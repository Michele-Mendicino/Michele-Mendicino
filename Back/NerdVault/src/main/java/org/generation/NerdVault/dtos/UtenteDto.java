// Un DTO è usato per trasferire dati tra i vari livelli dell'applicazione, ad esempio tra il controller e il service o tra il service e il frontend.
// A differenza delle entità, un DTO dovrebbe contenere solo i dati necessari per il trasferimento, escludendo logiche aggiuntive o informazioni sensibili (es. password).

package org.generation.NerdVault.dtos;

//import java.sql.Date; // è specifico per JDBC e database relazionali, nel contesto di un DTO (che non mappa direttamente il database) potrebbe essere sostituito con un tipo di data più generico come LocalDate
import java.time.LocalDate;

import org.generation.NerdVault.entities.Utente;
import org.generation.NerdVault.enums.UtenteRuolo;


// Il costruttore vuoto (default constructor) è essenziale per molte librerie che deserializzano oggetti JSON.
// Il costruttore parametrizzato è utile per creare un DTO direttamente con tutti i campi impostati.
public class UtenteDto {
	
	private int utenteId;
	private String nome;
	private String cognome;
	private LocalDate dataNascita;
	private String email;
	private UtenteRuolo ruolo;

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
	
	public UtenteRuolo getRuolo() {
		return ruolo;
	}
	
	public void setRuolo(UtenteRuolo ruolo) {
		this.ruolo = ruolo;
	}
	
	public UtenteDto() {
	}
	
	// perché id nel costruttore?
	public UtenteDto(int utenteId, String nome, String cognome, LocalDate dataNascita, String email,
			UtenteRuolo ruolo) {
		this.utenteId = utenteId;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.email = email;
		this.ruolo = ruolo;
	}
	
	public UtenteDto(Utente utente) {
		this.utenteId = utente.getUtenteId();
		this.nome = utente.getNome();
		this.cognome = utente.getCognome();
		this.dataNascita = utente.getDataNascita();
		this.email = utente.getEmail();
		this.ruolo = utente.getRuolo();
	}
	
}
