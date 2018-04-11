package bean;

import java.util.List;

public class Votazione {

    private String idVotazione;
    private String nomeVotazione;
    private String descrizione;
    private String dataInizio;
    private String dataFine;
    private List<String> criteri;

    public Votazione(String nomeVotazione, String descrizione, String dataInizio, String dataFine) {
        this.nomeVotazione = nomeVotazione;
        this.descrizione = descrizione;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public String getIdVotazione() {
        return idVotazione;
    }

    public void setIdVotazione(String idVotazione) {
        this.idVotazione = idVotazione;
    }

    public String getNomeVotazione() {
        return nomeVotazione;
    }

    public void setNomeVotazione(String nomeVotazione) {
        this.nomeVotazione = nomeVotazione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    public String getDataFine() {
        return dataInizio;
    }

    public void setDataFine(String dataFine) {
        this.dataFine = dataFine;
    }

    public void addCritereio(String criterio) {
        criteri.add(criterio);
    }

    public List<String> getCriteri() {
        return criteri;
    }

}
