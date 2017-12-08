package ejb;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;

@Stateless
public class PasswordHash {

	private final MessageDigest crypt;

	public PasswordHash() throws NoSuchAlgorithmException {
		crypt = MessageDigest.getInstance("SHA-256");
	}

	public String hash(String string) throws UnsupportedEncodingException {
		crypt.reset();
		crypt.update(string.getBytes("UTF-8"));
		return new BigInteger(1, crypt.digest()).toString(16);
	}
}
