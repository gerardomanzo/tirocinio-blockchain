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
public class PartecipazioneEJB {

    public final static URI URI_QUERY = UriBuilder.fromUri("http://localhost:3000/api/queries").build();
    public final static URI URI_BASE = UriBuilder.fromUri("http://localhost:3000/api").build();
    private final static Client CLIENT = ClientBuilder.newClient();

    @Inject
    private IdGenerator idGen;

    private final Gson gson = new Gson();

    public Partecipazione creaPartecipazione(Partecipazione partecipazione) {

        partecipazione.setIdPartecipazione(idGen.nextId());

        String json = gson.toJson(partecipazione);

        Response response = CLIENT.target(URI_BASE + "/Partecipazione")
                .request()
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));

        return partecipazione;
    }

    public List<Partecipazione> cercaNonRegistrati() {
        Response response = CLIENT.target(URI_QUERY + "/findNotRegistredPartecipation")
                .request()
                .get();

        String body = response.readEntity(String.class);

        Partecipazione[] result = gson.fromJson(body, Partecipazione[].class);

        return Arrays.asList(result);
    }

    public void confermaPartecipazione(String idPartecipazione) {
        String json = "{\"partecipazione\": \"resource:it.unisa.Partecipazione#" + idPartecipazione + "\"}";

        Response response = CLIENT.target(URI_BASE + "/ConfermaRegistrazionePartecipazione")
                .request()
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));
    }

    public List<Candidatura> cercaCandidatureVotazione(String idVotazione) {
        Response response = CLIENT.target(URI_BASE + "/Partecipazione")
                .request()
                .get();

        String body = response.readEntity(String.class);

        Partecipazione[] partecipazioni = gson.fromJson(body, Partecipazione[].class);

        List<Candidatura> lista = new ArrayList<>();

        for (Partecipazione p : partecipazioni) {

            if (p.getIdVotazione().contains(idVotazione)) {
                String idCandidatura = p.getIdCandidatura().substring(30, 38);

                response = CLIENT.target(URI_BASE + "/Candidatura/" + idCandidatura)
                        .request()
                        .get();

                body = response.readEntity(String.class);

                Candidatura candidatura = gson.fromJson(body, Candidatura.class);

                lista.add(candidatura);
            }
        }

        return lista;
    }

    public Partecipazione cercaPartecipazione(String idVotazione, String idCandidatura) {
        Response response = CLIENT.target(URI_BASE + "/Partecipazione")
                .request()
                .get();

        String body = response.readEntity(String.class);

        Partecipazione[] partecipazioni = gson.fromJson(body, Partecipazione[].class);

        for (Partecipazione p : partecipazioni) {
            if (p.getIdVotazione().contains(idVotazione) && p.getIdCandidatura().contains(idCandidatura)) {
                return p;
            }
        }

        return null;
    }
    
    public List<Partecipazione> cercaPartecipazioniByIdVotazione(String idVotazione) {
        Response response = CLIENT.target(URI_BASE + "/Partecipazione")
                .request()
                .get();

        String body = response.readEntity(String.class);
        
        Partecipazione[] partecipazioni = gson.fromJson(body, Partecipazione[].class);
        List<Partecipazione> listaPartecipazioni = new ArrayList<>();
        for (Partecipazione p : partecipazioni) {
            if(p.getIdVotazione().contains(idVotazione)){
               listaPartecipazioni.add(p);
            }
        }

        return listaPartecipazioni;

    }

}
