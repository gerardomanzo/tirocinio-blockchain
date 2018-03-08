package bean;

public class Voto {

    private String idVoto;
    private String partecipazione;
    private String criterio;
    private String utente;
    private Integer punteggio;

    public Voto(String partecipazione, String criterio, String utente) {
        this.partecipazione = partecipazione;
        this.criterio = criterio;
        this.utente = utente;
    }

    public String getIdVoto() {
        return idVoto;
    }

    public void setIdVoto(String idVoto) {
        this.idVoto = idVoto;
    }

    public String getPartecipazione() {
        return partecipazione;
    }

    public void setPartecipazione(String partecipazione) {
        this.partecipazione = partecipazione;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public Integer getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(Integer punteggio) {
        this.punteggio = punteggio;
    }

}
