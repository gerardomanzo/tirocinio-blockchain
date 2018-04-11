package ejb;

import bean.Criterio;
import bean.Votazione;
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
public class VotazioneEJB {

    public final static URI URI_QUERY = UriBuilder.fromUri("http://localhost:3000/api/queries").build();
    public final static URI URI_BASE = UriBuilder.fromUri("http://localhost:3000/api").build();
    private final static Client CLIENT = ClientBuilder.newClient();

    @Inject
    private IdGenerator idGen;

    private final Gson gson = new Gson();

    public Votazione creaVotazione(Votazione votazione) {

        Response response = CLIENT.target(URI_QUERY + "/findByNomeVotazione")
                .queryParam("param", votazione.getNomeVotazione())
                .request()
                .get();

        String body = response.readEntity(String.class);

        Votazione[] result = gson.fromJson(body, Votazione[].class);

        if (result.length == 0) {
            votazione.setIdVotazione(idGen.nextId());

            String json = gson.toJson(votazione);

            response = CLIENT.target(URI_BASE + "/Votazione")
                    .request()
                    .post(Entity.entity(json, MediaType.APPLICATION_JSON));
            return votazione;
        }
        return null;

    }

    public List<Votazione> cercaVotazioni() {
        Response response = CLIENT.target(URI_BASE + "/Votazione")
                .request()
                .get();

        String body = response.readEntity(String.class);

        Votazione[] result = gson.fromJson(body, Votazione[].class);

        return Arrays.asList(result);
    }

    public List<Criterio> cercaTuttiCriteri(String idVotazione) {
        Response response = CLIENT.target(URI_BASE + "/Votazione/" + idVotazione)
                .request()
                .get();

        String body = response.readEntity(String.class);

        Votazione votazione = gson.fromJson(body, Votazione.class);

        response = CLIENT.target(URI_BASE + "/Criterio")
                .request()
                .get();

        body = response.readEntity(String.class);

        Criterio[] criteri = gson.fromJson(body, Criterio[].class);

        List<String> listaCriteri = votazione.getCriteri();

        if (listaCriteri != null) {

            List<Criterio> lista = new ArrayList<>();
            Boolean trovato;

            for (Criterio c1 : criteri) {
                trovato = false;

                for (String c2 : listaCriteri) {
                    if (c2.contains(c1.getIdCriterio())) {
                        trovato = true;
                    }
                }

                if (!trovato) {
                    lista.add(c1);
                }
            }

            return lista;
        }
        return Arrays.asList(criteri);
    }

    public void aggiungiCriterio(String idVotazione, String idCriterio) {
        String json = "{\"votazione\": \"resource:it.unisa.Votazione#" + idVotazione + "\", \"criterio\": \"resource:it.unisa.Criterio#" + idCriterio + "\"}";

        Response response = CLIENT.target(URI_BASE + "/AggiungiCriterio")
                .request()
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));
    }

    public List<Criterio> cercaCriteriVotazione(String idVotazione) {
        Response response = CLIENT.target(URI_BASE + "/Votazione/" + idVotazione)
                .request()
                .get();

        String body = response.readEntity(String.class);

        Votazione votazione = gson.fromJson(body, Votazione.class);

        List<String> lista = votazione.getCriteri();

        List<Criterio> listaCriterio = new ArrayList<>();

        Criterio criterio;

        for (String idCriterio : lista) {

            idCriterio = idCriterio.substring(27, 35);

            response = CLIENT.target(URI_BASE + "/Criterio/" + idCriterio)
                    .request()
                    .get();

            body = response.readEntity(String.class);

            criterio = gson.fromJson(body, Criterio.class);

            listaCriterio.add(criterio);
        }

        return listaCriterio;
    }

}
