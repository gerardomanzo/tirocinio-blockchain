package ejb;

import bean.Criterio;
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
public class CriterioEJB {

    public final static URI URI_QUERY = UriBuilder.fromUri("http://localhost:3000/api/queries").build();
    public final static URI URI_BASE = UriBuilder.fromUri("http://localhost:3000/api").build();
    private final static Client CLIENT = ClientBuilder.newClient();

    @Inject
    private IdGenerator idGen;

    private final Gson gson = new Gson();

    public Criterio creaCriterio(Criterio criterio) {

        String body;
        Criterio[] result;

        Response responseNomeUtente = CLIENT.target(URI_QUERY + "/findByNomeCriterio")
                .queryParam("param", criterio.getNomeCriterio())
                .request()
                .get();

        body = responseNomeUtente.readEntity(String.class);

        result = gson.fromJson(body, Criterio[].class);

        if (result.length == 0) {
            criterio.setIdCriterio(idGen.nextId());

            String json = gson.toJson(criterio);

            Response response = CLIENT.target(URI_BASE + "/Criterio")
                    .request()
                    .post(Entity.entity(json, MediaType.APPLICATION_JSON));
            return criterio;
        }
        return null;

    }

}
