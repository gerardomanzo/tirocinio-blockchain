package bean;

public class Partecipazione {

    private String idPartecipazione;
    private String idVotazione;
    private String idCandidatura;
    private Boolean statoRegistrazione;

    public Partecipazione(String idVotazione, String idCandidatura) {
        this.idVotazione = idVotazione;
        this.idCandidatura = idCandidatura;
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

    public String getIdVotazione() {
        return idVotazione;
    }

    public void setIdVotazione(String idVotazione) {
        this.idVotazione = idVotazione;
    }

    public String getIdCandidatura() {
        return idCandidatura;
    }

    public void setIdCandidatura(String idCandidatura) {
        this.idCandidatura = idCandidatura;
    }

}
