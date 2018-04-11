package ejb;

import bean.Criterio;
import bean.Votazione;
import bean.Candidatura;
import bean.Partecipazione;
import bean.Utente;
import bean.Voto;
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

        response = CLIENT.target(URI_BASE + "/Candidatura")
                .request()
                .get();

        body = response.readEntity(String.class);
        result = gson.fromJson(body, Candidatura[].class);

        for (Object item : result) {
            keys.add(((Candidatura) item).getIdCandidatura());
        }

        response = CLIENT.target(URI_BASE + "/Votazione")
                .request()
                .get();

        body = response.readEntity(String.class);
        result = gson.fromJson(body, Votazione[].class);

        for (Object item : result) {
            keys.add(((Votazione) item).getIdVotazione());
        }

        response = CLIENT.target(URI_BASE + "/Partecipazione")
                .request()
                .get();

        body = response.readEntity(String.class);
        result = gson.fromJson(body, Partecipazione[].class);

        for (Object item : result) {
            keys.add(((Partecipazione) item).getIdVotazione());
        }

        response = CLIENT.target(URI_BASE + "/Voto")
                .request()
                .get();

        body = response.readEntity(String.class);
        result = gson.fromJson(body, Voto[].class);

        for (Object item : result) {
            keys.add(((Voto) item).getIdVoto());
        }
    }

    public String nextId() {

        String id;
        do {
            id = Integer.toString(Math.abs(rndGen.nextInt()), 16);
        } while (keys.contains(id));

        if (id.length() < 8) {
            int n = id.length();

            for (int i = 0; i < 8 - n; i++) {
                id = "0" + id;
            }
        }
        keys.add(id);

        return id;
    }
}
