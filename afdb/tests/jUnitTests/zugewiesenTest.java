package jUnitTests;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import bl.AfdbSuchen;
import data.Anforderung;
import data.Benutzer;
import data.Benutzerart;

public class zugewiesenTest {

	@Test
	public void testGetZugewiesen() {
		try {
			AfdbSuchen blSuchen = new AfdbSuchen();
			Benutzer eingeloggterUser = new Benutzer(
					6,
					"Max",
					"Mustermann",
					"mmustermann",
					"mmustermann",
					new Date(),
					new Date(2016,06,20),
					new Benutzerart(2,"Kunde",false),
					"+436641234572",
					"max.mustermann@autohaus-mustermann.at");
			List<Anforderung> anforderungen = blSuchen.suchen(0,"","","",eingeloggterUser.getBenutzername(),"","");
			
			if (anforderungen.isEmpty()) {
				fail("Keine Anforderung gefunden.");
			}
		} catch (Exception e) {
			fail("Fehler aufgetreten");
		}
	}

}
