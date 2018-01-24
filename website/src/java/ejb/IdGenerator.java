package ejb;

import bean.Criterio;
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
    private TreeSet<String> keys;

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

        for (int i = 0; i < result.length; i++) {
            keys.add(((Utente) result[i]).getIdUtente());
        }

        response = CLIENT.target(URI_BASE + "/Criterio")
                .request()
                .get();

        body = response.readEntity(String.class);
        result = gson.fromJson(body, Criterio[].class);

        for (int i = 0; i < result.length; i++) {
            keys.add(((Criterio) result[i]).getIdCriterio());
        }
    }

    public String nextId() {

        String id;
        do {
            id = Integer.toString(Math.abs(rndGen.nextInt()) % 5, 16);
            System.out.println("id = " + id);
        } while (keys.contains(id));

        keys.add(id);
        
        return id;
    }
}
