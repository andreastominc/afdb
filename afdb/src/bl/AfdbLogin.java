package bl;

import dal.QueryHelper;
import data.Benutzer;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AfdbLogin {
	
	
	public Boolean checkUser(String benutzer)
	{
		return QueryHelper.checkUser(benutzer);
	}
	
	public Boolean checkPasswort(String passwort) throws NoSuchAlgorithmException
	{	
        StringBuffer hexString = encrypt(passwort);		
		return QueryHelper.checkPasswort(hexString.toString());
	}
	
	/**
	 * 
	 * @param user
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public Benutzer authUser(String user, String password) throws NoSuchAlgorithmException{
		StringBuffer hexString = encrypt(password);	
		return QueryHelper.authenticateUser(user, hexString.toString());
	}
	

	private StringBuffer encrypt(String passwort) throws NoSuchAlgorithmException {
		
		MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(passwort.getBytes());
        byte[] digest = m.digest();

        StringBuffer hexString = new StringBuffer();
        for (int i=0; i<digest.length; i++) {
        	if(digest[i] <= 15 && digest[i] >= 0){
        		hexString.append("0");
        	}
        	hexString.append(Integer.toHexString(0xFF & digest[i]));
        }
		return hexString;
	}
	

}

