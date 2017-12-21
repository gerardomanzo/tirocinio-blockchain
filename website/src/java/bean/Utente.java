package bean;

public class Utente {

	private final String $class = "it.unisa.Utente";
	private String idUtente;
	private String nomeUtente;
	private String email;
	private String password;
	private Boolean statoRegistrazione;

	public Utente() {
	}

	public Utente(String nomeUtente, String email, String password) {
		this.nomeUtente = nomeUtente;
		this.email = email;
		this.password = password;
		this.statoRegistrazione = false;
	}

	public String getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
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

	public Boolean getStatoRegistrazione() {
		return statoRegistrazione;
	}

	public void setStatoRegistrazione(Boolean statoRegistrazione) {
		this.statoRegistrazione = statoRegistrazione;
	}
}
