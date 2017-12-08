package ejb;

import bean.Utente;
import java.net.URI;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Stateless
public class UtenteEJB {

	private final static String UTENTE_CLASS = "it.unisa.Utente";
	private final static URI URI = UriBuilder.fromUri("http://169.51.23.2:31090/api/Utente").port(31090).build();
	private final static Client CLIENT = ClientBuilder.newClient();

	@Inject
	private IdGenerator idGen;

	public void creaUtente(Utente utente) {
		JsonObject obj = Json.createObjectBuilder()
				.add("$class", UTENTE_CLASS)
				.add("idUtente", idGen.nextId())
				.add("nomeUtente", utente.getNomeUtente())
				.add("email", utente.getEmail())
				.add("password", utente.getPassword())
				.build();

		Response response = CLIENT.target(URI).request().post(Entity.entity(obj, MediaType.APPLICATION_JSON));
	}
}
