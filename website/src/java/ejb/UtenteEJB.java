package ejb;

import bean.Utente;
import com.google.gson.Gson;
import java.net.URI;
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
public class UtenteEJB {

    public final static URI URI_QUERY = UriBuilder.fromUri("http://localhost:3000/api/queries").build();
    public final static URI URI_BASE = UriBuilder.fromUri("http://localhost:3000/api").build();
    private final static Client CLIENT = ClientBuilder.newClient();

    @Inject
    private IdGenerator idGen;

    private final Gson gson = new Gson();

    public Utente creaUtente(Utente utente) {

        Response response = CLIENT.target(URI_QUERY + "/findByEmail")
                .queryParam("param", utente.getEmail())
                .request()
                .get();

        String body = response.readEntity(String.class);

        Utente[] result = gson.fromJson(body, Utente[].class);

        if (result.length == 0) {
            response = CLIENT.target(URI_QUERY + "/findByUsername")
                    .queryParam("param", utente.getNomeUtente())
                    .request()
                    .get();

            body = response.readEntity(String.class);

            result = gson.fromJson(body, Utente[].class);

            if (result.length == 0) {
                utente.setIdUtente(idGen.nextId());

                String json = gson.toJson(utente);

                response = CLIENT.target(URI_BASE + "/Utente")
                        .request()
                        .post(Entity.entity(json, MediaType.APPLICATION_JSON));
                return utente;
            }
            return null;
        }
        return null;
    }

    public Utente cercaUtente(Utente utente) {

        Response response = CLIENT.target(URI_QUERY + "/findByEmailPassword")
                .queryParam("email", utente.getEmail())
                .queryParam("password", utente.getPassword())
                .request()
                .get();

        String body = response.readEntity(String.class);

        Utente[] result = gson.fromJson(body, Utente[].class);

        if (result.length > 0) {
            return result[0];
        }
        return null;
    }

    public List<Utente> cercaNonRegistrati() {
        Response response = CLIENT.target(URI_QUERY + "/findNotRegistered")
                .request()
                .get();

        String body = response.readEntity(String.class);

        Utente[] result = gson.fromJson(body, Utente[].class);

        return Arrays.asList(result);
    }

    public void confermaUtente(String idUtente) {
        String json = "{\"utente\": \"resource:it.unisa.Utente#" + idUtente + "\"}";

        Response response = CLIENT.target(URI_BASE + "/ConfermaRegistrazioneUtente")
                .request()
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));
    }

    public List<Utente> cercaTuttiGliUtenti() {
        Response response = CLIENT.target(URI_BASE + "/Utente")
                .request()
                .get();

        String body = response.readEntity(String.class);
        Utente[] result = gson.fromJson(body, Utente[].class);

        return Arrays.asList(result);
    }

}
