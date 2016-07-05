package jUnitTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import bl.AfdbSuchen;
import data.Anforderung;

public class suchenTest {

	@Test
	public void testSuchen() {
		try {
			AfdbSuchen blSuchen = new AfdbSuchen();
			List<Anforderung> anforderungen = blSuchen.suchen(0,"","","","","","");
			
			if (anforderungen.size() == 0) {
				fail("Keine Anforderung gefunden.");	
			}
		} catch (Exception e) {
			fail("Fehler bei Suche aufgetreten");
		}
	}

}
