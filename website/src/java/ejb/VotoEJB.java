package ejb;

import bean.Partecipazione;
import bean.Votazione;
import bean.Voto;
import com.google.gson.Gson;
import java.net.URI;
import java.util.ArrayList;
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
public class VotoEJB {

    public final static URI URI_QUERY = UriBuilder.fromUri("http://localhost:3000/api/queries").build();
    public final static URI URI_BASE = UriBuilder.fromUri("http://localhost:3000/api").build();
    private final static Client CLIENT = ClientBuilder.newClient();

    @Inject
    private IdGenerator idGen;

    private final Gson gson = new Gson();

    public List<Votazione> cercaVotazioniVoto(String idUtente) {
        Response response = CLIENT.target(URI_BASE + "/Votazione")
                .request()
                .get();

        String body = response.readEntity(String.class);

        Votazione[] votazioni = gson.fromJson(body, Votazione[].class);

        response = CLIENT.target(URI_BASE + "/Partecipazione")
                .request()
                .get();

        body = response.readEntity(String.class);

        Partecipazione[] partecipazioni = gson.fromJson(body, Partecipazione[].class);

        response = CLIENT.target(URI_BASE + "/Voto")
                .request()
                .get();

        body = response.readEntity(String.class);

        Voto[] voti = gson.fromJson(body, Voto[].class);

        Boolean trovato;

        List<Votazione> lista = new ArrayList<>();

        for (Votazione e : votazioni) {
            trovato = false;
            Partecipazione p = null;

            for (int i = 0; i < partecipazioni.length && !trovato; i++) {
                if (partecipazioni[i].getIdVotazione().contains(e.getIdVotazione())) {
                    trovato = true;
                    p = partecipazioni[i];
                }
            }

            if (trovato) {
                trovato = false;

                for (int j = 0; j < voti.length && !trovato; j++) {

                    if (voti[j].getPartecipazione().contains(p.getIdPartecipazione()) && voti[j].getUtente().contains(idUtente)) {
                        trovato = true;
                    }
                }

                if (!trovato) {
                    lista.add(e);
                }
            }
        }

        return lista;
    }

    public void creaVoto(Voto v) {
        v.setIdVoto(idGen.nextId());

        String json = gson.toJson(v);

        Response response = CLIENT.target(URI_BASE + "/Voto")
                .request()
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));
    }

    public List<Voto> cercaVotoByIdPartecipation(List<Partecipazione> partecipazioni) {
        Response response = CLIENT.target(URI_BASE + "/Voto")
                .request()
                .get();

        String body = response.readEntity(String.class);

        Voto[] voti = gson.fromJson(body, Voto[].class);
        List<Voto> votiTrovati = new ArrayList<>();
        partecipazioni.forEach((p) -> {
            for (Voto v : voti) {
                if (v.getPartecipazione().contains(p.getIdPartecipazione())) {
                    votiTrovati.add(v);
                }
            }
        });

        return votiTrovati;

    }

}
