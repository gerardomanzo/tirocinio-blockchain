package bean;

public class Partecipazione {

    private String idPartecipazione;
    private String idEvento;
    private String idOggetto;
    private boolean statoRegistrazione;

    public Partecipazione(String idEvento, String idOggetto) {
        this.idEvento = idEvento;
        this.idOggetto = idOggetto;
        statoRegistrazione = false;
    }

    public String getIdPartecipazione() {
        return idPartecipazione;
    }

    public void setIdPartecipazione(String idPartecipazione) {
        this.idPartecipazione = idPartecipazione;
    }

    public boolean isStatoRegistrazione() {
        return statoRegistrazione;
    }

    public void setStatoRegistrazione(boolean statoRegistrazione) {
        this.statoRegistrazione = statoRegistrazione;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getIdOggetto() {
        return idOggetto;
    }

    public void setIdOggetto(String idOggetto) {
        this.idOggetto = idOggetto;
    }

}
