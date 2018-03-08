package ejb;

import bean.Oggetto;
import bean.Partecipazione;
import com.google.gson.Gson;
import static ejb.VotoEJB.URI_BASE;

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

    public List<Oggetto> cercaOggettiEvento(String idEvento) {
        Response response = CLIENT.target(URI_BASE + "/Partecipazione")
                .request()
                .get();

        String body = response.readEntity(String.class);

        Partecipazione[] partecipazioni = gson.fromJson(body, Partecipazione[].class);

        List<Oggetto> lista = new ArrayList<>();

        for (Partecipazione p : partecipazioni) {

            if (p.getIdEvento().contains(idEvento)) {
                String idOggetto = p.getIdOggetto().substring(26, 34);

                response = CLIENT.target(URI_BASE + "/Oggetto/" + idOggetto)
                        .request()
                        .get();

                body = response.readEntity(String.class);

                Oggetto oggetto = gson.fromJson(body, Oggetto.class);

                lista.add(oggetto);
            }
        }

        return lista;
    }

    public Partecipazione cercaPartecipazione(String idEvento, String idOggetto) {
        Response response = CLIENT.target(URI_BASE + "/Partecipazione")
                .request()
                .get();

        String body = response.readEntity(String.class);

        Partecipazione[] partecipazioni = gson.fromJson(body, Partecipazione[].class);

        for (Partecipazione p : partecipazioni) {
            if (p.getIdEvento().contains(idEvento) && p.getIdOggetto().contains(idOggetto)) {
                return p;
            }
        }

        return null;
    }
    
    public List<Partecipazione> cercaPartecipazioniByIdEvento(String idEvento) {
        Response response = CLIENT.target(URI_BASE + "/Partecipazione")
                .request()
                .get();

        String body = response.readEntity(String.class);
        
        Partecipazione[] partecipazioni = gson.fromJson(body, Partecipazione[].class);
        List<Partecipazione> listaPartecipazioni = new ArrayList<>();
        for (Partecipazione p : partecipazioni) {
            if(p.getIdEvento().contains(idEvento)){
               listaPartecipazioni.add(p);
            }
        }

        return listaPartecipazioni;

    }

}
