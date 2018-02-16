package ejb;

import bean.Criterio;
import bean.Evento;
import bean.Oggetto;
import bean.Partecipazione;
import bean.Utente;
import com.google.gson.Gson;
import java.net.URI;
import java.security.SecureRandom;
import java.util.TreeSet;
import javax.ejb.Singleton;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Singleton
public class IdGenerator {

    public final static URI URI_BASE = UriBuilder.fromUri("http://localhost:3000/api").build();
    private final static Client CLIENT = ClientBuilder.newClient();

    private final SecureRandom rndGen;
    private final TreeSet<String> keys;

    private final Gson gson = new Gson();

    public IdGenerator() {
        this.rndGen = new SecureRandom();

        keys = new TreeSet<>();

        String body;
        Object[] result;
        Response response;

        response = CLIENT.target(URI_BASE + "/Utente")
                .request()
                .get();

        body = response.readEntity(String.class);
        result = gson.fromJson(body, Utente[].class);

        for (Object item : result) {
            keys.add(((Utente) item).getIdUtente());
        }

        response = CLIENT.target(URI_BASE + "/Criterio")
                .request()
                .get();

        body = response.readEntity(String.class);
        result = gson.fromJson(body, Criterio[].class);

        for (Object item : result) {
            keys.add(((Criterio) item).getIdCriterio());
        }

        response = CLIENT.target(URI_BASE + "/Oggetto")
                .request()
                .get();

        body = response.readEntity(String.class);
        result = gson.fromJson(body, Oggetto[].class);

        for (Object item : result) {
            keys.add(((Oggetto) item).getIdOggetto());
        }

        response = CLIENT.target(URI_BASE + "/Evento")
                .request()
                .get();

        body = response.readEntity(String.class);
        result = gson.fromJson(body, Evento[].class);

        for (Object item : result) {
            keys.add(((Evento) item).getIdEvento());
        }
        
        response = CLIENT.target(URI_BASE + "/Partecipazione")
                .request()
                .get();

        body = response.readEntity(String.class);
        result = gson.fromJson(body, Partecipazione[].class);

        for (Object item : result) {
            keys.add(((Partecipazione) item).getIdEvento());
        }
    }

    public String nextId() {

        String id;
        do {
            id = Integer.toString(Math.abs(rndGen.nextInt()), 16);
        } while (keys.contains(id));

        keys.add(id);

        return id;
    }
}
