package org.generation.NerdVault.dtos;

//import java.sql.Date; // è specifico per JDBC e database relazionali, nel contesto di un DTO (che non mappa direttamente il database) potrebbe essere sostituito con un tipo di data più generico come LocalDate
import java.time.LocalDate;

import org.generation.NerdVault.enums.ProdottoCategoria;


// Il costruttore vuoto (default constructor) è essenziale per molte librerie che deserializzano oggetti JSON.
// Il costruttore parametrizzato è utile per creare un DTO direttamente con tutti i campi impostati.
public class ProdottoDto {
	
	private int prodottoId;
	private String nome;
	private String descrizione;
	private ProdottoCategoria categoria;
	private double prezzo;
	private int rimanenza;
	private int copieVendute;
	private boolean abilitato;
	private boolean visibile;
	private String immagine;
	private String imgUrl;
	private LocalDate inizioPrevendita;
	private LocalDate dataUscita;
	private double scontoPrevendita;
	
	
	/*------------------ Getters / Setters ------------------*/
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getProdottoId() {
		return prodottoId;
	}

	public void setProdottoId(int prodottoId) {
		this.prodottoId = prodottoId;
	}

	public ProdottoCategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(ProdottoCategoria categoria) {
		this.categoria = categoria;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getRimanenza() {
		return rimanenza;
	}

	public void setRimanenza(int rimanenza) {
		this.rimanenza = rimanenza;
	}

	public boolean isAbilitato() {
		return abilitato;
	}

	public void setAbilitato(boolean abilitato) {
		this.abilitato = abilitato;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public LocalDate getInizioPrevendita() {
		return inizioPrevendita;
	}

	public void setInizioPrevendita(LocalDate inizioPrevendita) {
		this.inizioPrevendita = inizioPrevendita;
	}

	public LocalDate getDataUscita() {
		return dataUscita;
	}

	public void setDataUscita(LocalDate dataUscita) {
		this.dataUscita = dataUscita;
	}

	public double getScontoPrevendita() {
		return scontoPrevendita;
	}

	public void setScontoPrevendita(double scontoPrevendita) {
		this.scontoPrevendita = scontoPrevendita;
	}
	
	public boolean isVisibile() {
		return visibile;
	}
	
	public void setVisibile(boolean visibile) {
		this.visibile = visibile;
	}
	
	public int getCopieVendute() {
		return copieVendute;
	}

	public void setCopieVendute(int copieVendute) {
		this.copieVendute = copieVendute;
	}

	public ProdottoDto() {
	}

	public ProdottoDto(int prodottoId, String nome, String descrizione, ProdottoCategoria categoria, double prezzo,
			int rimanenza, int copieVendute, boolean abilitato, boolean visibile, String immagine,
			LocalDate inizioPrevendita, LocalDate dataUscita, double scontoPrevendita) {
		this.prodottoId = prodottoId;
		this.nome = nome;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.prezzo = prezzo;
		this.rimanenza = rimanenza;
		this.copieVendute = copieVendute;
		this.abilitato = abilitato;
		this.visibile = visibile;
		this.immagine = immagine;
		this.inizioPrevendita = inizioPrevendita;
		this.dataUscita = dataUscita;
		this.scontoPrevendita = scontoPrevendita;
	}
	
}