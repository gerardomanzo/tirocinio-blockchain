package ejb;

import java.security.SecureRandom;
import javax.ejb.Singleton;

@Singleton
public class IdGenerator {

	private final SecureRandom rndGen;

	public IdGenerator() {
		this.rndGen = new SecureRandom();
	}

	public String nextId() {
		return Integer.toString(Math.abs(rndGen.nextInt()), 16);
	}
}
