package ejb;

import bean.Oggetto;
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
public class OggettoEJB {

    public final static URI URI_QUERY = UriBuilder.fromUri("http://localhost:3000/api/queries").build();
    public final static URI URI_BASE = UriBuilder.fromUri("http://localhost:3000/api").build();
    private final static Client CLIENT = ClientBuilder.newClient();

    @Inject
    private IdGenerator idGen;

    private final Gson gson = new Gson();

    public Oggetto creaOggetto(Oggetto oggetto) {

        Response response = CLIENT.target(URI_QUERY + "/findByNomeOggetto")
                .queryParam("param", oggetto.getNomeOggetto())
                .request()
                .get();

        String body = response.readEntity(String.class);

        Oggetto[] result = gson.fromJson(body, Oggetto[].class);

        if (result.length == 0) {
            oggetto.setIdOggetto(idGen.nextId());

            String json = gson.toJson(oggetto);

            response = CLIENT.target(URI_BASE + "/Oggetto")
                    .request()
                    .post(Entity.entity(result, MediaType.WILDCARD_TYPE).entity(json, MediaType.APPLICATION_JSON));
            return oggetto;
        }
        return null;
    }

    public List<Oggetto> cercaOggettiNonRegistrati() {
        Response response = CLIENT.target(URI_QUERY + "/findObjectNotRegistred")
                .request()
                .get();

        String body = response.readEntity(String.class);

        Oggetto[] result = gson.fromJson(body, Oggetto[].class);

        return Arrays.asList(result);

    }

    public void confermaOggetto(String idOggetto) {
        String json = "{\"oggetto\": \"resource:it.unisa.Oggetto#" + idOggetto + "\"}";

        Response response = CLIENT.target(URI_BASE + "/ConfermaRegistrazioneOggetto")
                .request()
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));

    }

    public List<Oggetto> cercaTuttiGliOggetti(String idEvento, String idUtente) {

        Response response = CLIENT.target(URI_BASE + "/Oggetto")
                .request()
                .get();

        String body = response.readEntity(String.class);

        Oggetto[] oggetti = gson.fromJson(body, Oggetto[].class);

        List<Oggetto> oggettiUtente = new ArrayList<>();

        for (Oggetto o : oggetti) {
            if (o.getProprietario().contains(idUtente)) {
                oggettiUtente.add(o);
            }
        }

        response = CLIENT.target(URI_BASE + "/Partecipazione")
                .request()
                .get();

        body = response.readEntity(String.class);

        Partecipazione[] partecipazioni = gson.fromJson(body, Partecipazione[].class);

        List<Partecipazione> partecipazioniEvento = new ArrayList<>();

        for (Partecipazione o : partecipazioni) {
            if (o.getIdEvento().contains(idEvento)) {
                partecipazioniEvento.add(o);
            }
        }

        List<Oggetto> lista = new ArrayList<>();

        Boolean trovato;

        for (Oggetto o : oggettiUtente) {
            trovato = false;

            for (Partecipazione p : partecipazioniEvento) {
                if (p.getIdOggetto().contains(o.getIdOggetto())) {
                    trovato = true;
                }
            }

            if (!trovato) {
                lista.add(o);
            }
        }

        return lista;
    }

}
