package ejb;

import bean.Oggetto;
import com.google.gson.Gson;
import java.net.URI;
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
                    .post(Entity.entity(json, MediaType.APPLICATION_JSON));
            return oggetto;
        }
        return null;

    }

}
