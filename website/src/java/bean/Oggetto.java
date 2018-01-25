package bean;

public class Oggetto {

    private final String $class = "it.unisa.Oggetto";
    private String idOggetto;
    private String nomeOggetto;
    private String descrizione;
    private String proprietario;

    public Oggetto() {
    }

    public Oggetto(String nomeOggetto, String descrizione) {
        this.nomeOggetto = nomeOggetto;
        this.descrizione = descrizione;
    }

    public String getIdOggetto() {
        return idOggetto;
    }

    public void setIdOggetto(String idOggetto) {
        this.idOggetto = idOggetto;
    }

    public String getNomeOggetto() {
        return nomeOggetto;
    }

    public void setNomeOggetto(String nomeOggetto) {
        this.nomeOggetto = nomeOggetto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

}
