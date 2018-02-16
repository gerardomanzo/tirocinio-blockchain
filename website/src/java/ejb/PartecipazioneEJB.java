package ejb;

import bean.Partecipazione;
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

}
