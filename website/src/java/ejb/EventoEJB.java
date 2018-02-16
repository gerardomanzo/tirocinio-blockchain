package ejb;

import bean.Criterio;
import bean.Evento;
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
public class EventoEJB {

    public final static URI URI_QUERY = UriBuilder.fromUri("http://localhost:3000/api/queries").build();
    public final static URI URI_BASE = UriBuilder.fromUri("http://localhost:3000/api").build();
    private final static Client CLIENT = ClientBuilder.newClient();

    @Inject
    private IdGenerator idGen;

    private final Gson gson = new Gson();

    public Evento creaEvento(Evento evento) {

        Response response = CLIENT.target(URI_QUERY + "/findByNomeEvento")
                .queryParam("param", evento.getNomeEvento())
                .request()
                .get();

        String body = response.readEntity(String.class);

        Evento[] result = gson.fromJson(body, Evento[].class);

        if (result.length == 0) {
            evento.setIdEvento(idGen.nextId());

            String json = gson.toJson(evento);

            System.out.println("json=" + json);
            response = CLIENT.target(URI_BASE + "/Evento")
                    .request()
                    .post(Entity.entity(json, MediaType.APPLICATION_JSON));
            return evento;
        }
        return null;

    }

    public List<Evento> cercaEventi() {
        Response response = CLIENT.target(URI_BASE + "/Evento")
                .request()
                .get();

        String body = response.readEntity(String.class);

        Evento[] result = gson.fromJson(body, Evento[].class);

        return Arrays.asList(result);
    }

    public List<Criterio> cercaTuttiCriteri(String idEvento) {
        Response response = CLIENT.target(URI_BASE + "/Evento/" + idEvento)
                .request()
                .get();

        String body = response.readEntity(String.class);

        Evento evento = gson.fromJson(body, Evento.class);

        response = CLIENT.target(URI_BASE + "/Criterio")
                .request()
                .get();

        body = response.readEntity(String.class);

        Criterio[] criteri = gson.fromJson(body, Criterio[].class);

        List<String> listaCriteri = evento.getCriteri();

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

    public void aggiungiCriterio(String idEvento, String idCriterio) {
        String json = "{\"evento\": \"resource:it.unisa.Evento#" + idEvento + "\", \"criterio\": \"resource:it.unisa.Criterio#" + idCriterio + "\"}";

        Response response = CLIENT.target(URI_BASE + "/AggiungiCriterio")
                .request()
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));
    }

}
