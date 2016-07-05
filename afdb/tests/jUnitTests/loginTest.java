package jUnitTests;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import org.junit.Test;

import bl.AfdbLogin;
import data.Benutzer;

public class loginTest {

	@Test
	public void testLogin() {
		AfdbLogin blLogin = new AfdbLogin();
		Benutzer authBenutzer = null;
		
		try {
			authBenutzer = blLogin.authUser("mmustermann", "mmustermann");
		} catch (NoSuchAlgorithmException e) {
			fail("\"NoSuchAlgorithmException\"-Fehler in authUser");
		} catch (Exception e) {
			fail("Fehler in authUser");
		}
		
		if (authBenutzer == null) {
			fail("Login nicht erfolgreich");
		}
	}
	
	@Test
	public void testLoginWrongPwd() {
		AfdbLogin blLogin = new AfdbLogin();
		Benutzer authBenutzer = null;
		
		try {
			authBenutzer = blLogin.authUser("mmustermann", "falschespwd");
		} catch (NoSuchAlgorithmException e) {
			fail("\"NoSuchAlgorithmException\"-Fehler in authUser");
		} catch (Exception e) {
			fail("Fehler in authUser");
		}
		
		if (authBenutzer != null) {
			fail("Login fälschlicherweise erfolgreich");
		}
	}
}
