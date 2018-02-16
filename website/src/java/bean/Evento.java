package bean;

import java.util.List;

public class Evento {

    private final String $class = "it.unisa.Evento";
    private String idEvento;
    private String nomeEvento;
    private String descrizione;
    private String dataInizio;
    private String dataFine;
    private List<String> criteri;

    public Evento(String nomeEvento, String descrizione, String dataInizio, String dataFine) {
        this.nomeEvento = nomeEvento;
        this.descrizione = descrizione;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
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
