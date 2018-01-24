package bean;

public class Criterio {

    private final String $class = "it.unisa.Criterio";
    private String idCriterio;
    private String nomeCriterio;
    private Integer maxPunteggio;

    public Criterio(String nomeCriterio, Integer maxPunteggio) {
        this.nomeCriterio = nomeCriterio;
        this.maxPunteggio = maxPunteggio;
    }

    public String getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(String idCriterio) {
        this.idCriterio = idCriterio;
    }

    public String getNomeCriterio() {
        return nomeCriterio;
    }

    public void setNomeCriterio(String nomeCriterio) {
        this.nomeCriterio = nomeCriterio;
    }

    public Integer getMaxPunteggio() {
        return maxPunteggio;
    }

    public void setMaxPunteggio(Integer maxPunteggio) {
        this.maxPunteggio = maxPunteggio;
    }

}
