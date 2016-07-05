package jUnitTests;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.Test;

import bl.AfdbBearbeiten;
import bl.AfdbSuchen;
import data.Anforderung;
import data.AnforderungsArt;
import data.Benutzer;
import data.Benutzerart;
import data.Kunde;
import data.Modul;
import data.Prioritaet;
import data.Status;
import data.Version;

public class bearbeitenTest {

	@Test
	public void testBearbeiten() {
		Anforderung zuBearbeiten = null;
		Anforderung nachBearbeiten = null;
		
		// Anforderung mit Nr 1 suchen
		try {
			AfdbSuchen blSuchen = new AfdbSuchen();
			List<Anforderung> anforderungen = blSuchen.suchen(1,"","","","","","");
			
			if(anforderungen.size() == 0) {
				fail("Keine Anforderung gefunden.");
			}
			
			zuBearbeiten = anforderungen.get(0);
		} catch (Exception e) {
			fail("Fehler bei Suche aufgetreten");
		}
		
		String beschrVorTest = zuBearbeiten.getBeschreibung();
		
		// Anforderung mit Nr 1 bearbeiten
		try {
			String titel = zuBearbeiten.getTitel();
			String beschreibung = beschrVorTest + " test";
			Benutzer angelegtVon = zuBearbeiten.getAngelegtVon();
			Date erfDatum = zuBearbeiten.getErfassungsDatum();
			Benutzer ansprPers = zuBearbeiten.getAnsprechPerson();
			Kunde kd = zuBearbeiten.getKunde();
			AnforderungsArt anfArt = zuBearbeiten.getAnfArt();
			Prioritaet prio = zuBearbeiten.getPrio();
			Status status = zuBearbeiten.getStatus();
			Benutzer zugewiesen = zuBearbeiten.getZugewiesenAn();
			Modul modul = zuBearbeiten.getModul();
			Version version = zuBearbeiten.getVersion();
			String hdNr = zuBearbeiten.getHdNummer();
			float aufwandGesch = zuBearbeiten.getAufwandGeschaetzt();
			Date fertigStellGepl = zuBearbeiten.getFertiggeplant();
			Date fertigStellIst = zuBearbeiten.getFertigIst();
			String verwAnforderungen = zuBearbeiten.getVerwAnforderungen();
			String schluesselBegriffe = zuBearbeiten.getSchluesselBegriffe();
			
			AfdbBearbeiten blBearbeiten = new AfdbBearbeiten();
			blBearbeiten.persistAfdb(zuBearbeiten, titel, beschreibung, zugewiesen, erfDatum, ansprPers, kd, anfArt, prio,
					status, zugewiesen, modul, version, hdNr, aufwandGesch, fertigStellGepl, fertigStellIst,
					verwAnforderungen, schluesselBegriffe);
			
		} catch (Exception e) {
			fail("Fehler bei Bearbeiten aufgetreten");
		}
		
		// Anforderung mit Nr 2 erneut suchen
		try {
			AfdbSuchen blSuchen = new AfdbSuchen();
			List<Anforderung> anfNachBearbeiten = blSuchen.suchen(1,"","","","","","");
			
			if (anfNachBearbeiten.size() == 0) {
				fail("Keine Anforderung gefunden.");
			}
			
			nachBearbeiten = anfNachBearbeiten.get(0);
		} catch (Exception e) {
			fail("Fehler bei Suche aufgetreten");
		}
		
		if(beschrVorTest.equals(nachBearbeiten.getBeschreibung())){
			fail("Beschreibung hat sich nicht geändert");
		}
	}

}
