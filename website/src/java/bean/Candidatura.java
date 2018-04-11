package bean;

public class Candidatura {

    private String idCandidatura;
    private String nomeCandidatura;
    private String descrizione;
    private String proprietario;

    public Candidatura() {
    }

    public Candidatura(String nomeCandidatura, String descrizione) {
        this.nomeCandidatura = nomeCandidatura;
        this.descrizione = descrizione;
    }

    public String getIdCandidatura() {
        return idCandidatura;
    }

    public void setIdCandidatura(String idCandidatura) {
        this.idCandidatura = idCandidatura;
    }

    public String getNomeCandidatura() {
        return nomeCandidatura;
    }

    public void setNomeCandidatura(String nomeCandidatura) {
        this.nomeCandidatura = nomeCandidatura;
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
