package ejb;

import bean.Candidatura;
import bean.Partecipazione;
import com.google.gson.Gson;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Stateless
@LocalBean
public class CandidaturaEJB {

    public final static URI URI_QUERY = UriBuilder.fromUri("http://localhost:3000/api/queries").build();
    public final static URI URI_BASE = UriBuilder.fromUri("http://localhost:3000/api").build();
    private final static Client CLIENT = ClientBuilder.newClient();

    @Inject
    private IdGenerator idGen;

    private final Gson gson = new Gson();

    public Candidatura creaCandidatura(Candidatura candidatura) {

        Response response = CLIENT.target(URI_QUERY + "/findByNomeCandidatura")
                .queryParam("param", candidatura.getNomeCandidatura())
                .request()
                .get();

        String body = response.readEntity(String.class);

        Candidatura[] result = gson.fromJson(body, Candidatura[].class);

        if (result.length == 0) {
            candidatura.setIdCandidatura(idGen.nextId());

            String json = gson.toJson(candidatura);

            response = CLIENT.target(URI_BASE + "/Candidatura")
                    .request()
                    .post(Entity.entity(json, MediaType.APPLICATION_JSON));
            return candidatura;
        }
        return null;
    }

    public List<Candidatura> cercaCandidatureNonRegistrati() {
        Response response = CLIENT.target(URI_QUERY + "/findObjectNotRegistred")
                .request()
                .get();

        String body = response.readEntity(String.class);

        Candidatura[] result = gson.fromJson(body, Candidatura[].class);

        return Arrays.asList(result);

    }

    public void confermaCandidatura(String idCandidatura) {
        String json = "{\"candidatura\": \"resource:it.unisa.Candidatura#" + idCandidatura + "\"}";

        Response response = CLIENT.target(URI_BASE + "/ConfermaRegistrazioneCandidatura")
                .request()
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));

    }

    public List<Candidatura> cercaTutteLeCandidature(String idVotazione, String idUtente) {

        Response response = CLIENT.target(URI_BASE + "/Candidatura")
                .request()
                .get();

        String body = response.readEntity(String.class);

        Candidatura[] candidature = gson.fromJson(body, Candidatura[].class);

        List<Candidatura> candidatureUtente = new ArrayList<>();

        for (Candidatura o : candidature) {
            if (o.getProprietario().contains(idUtente)) {
                candidatureUtente.add(o);
            }
        }

        response = CLIENT.target(URI_BASE + "/Partecipazione")
                .request()
                .get();

        body = response.readEntity(String.class);

        Partecipazione[] partecipazioni = gson.fromJson(body, Partecipazione[].class);

        List<Partecipazione> partecipazioniVotazione = new ArrayList<>();

        for (Partecipazione o : partecipazioni) {
            if (o.getIdVotazione().contains(idVotazione)) {
                partecipazioniVotazione.add(o);
            }
        }

        List<Candidatura> lista = new ArrayList<>();

        Boolean trovato;

        for (Candidatura o : candidatureUtente) {
            trovato = false;

            for (Partecipazione p : partecipazioniVotazione) {
                if (p.getIdCandidatura().contains(o.getIdCandidatura())) {
                    trovato = true;
                }
            }

            if (!trovato) {
                lista.add(o);
            }
        }

        return lista;
    }
    
    public List<Candidatura> cercaGliCandidature() {
        Response response = CLIENT.target(URI_BASE + "/Candidatura")
                .request()
                .get();

        String body = response.readEntity(String.class);
        Candidatura[] result = gson.fromJson(body, Candidatura[].class);

        return Arrays.asList(result);
    }

}
