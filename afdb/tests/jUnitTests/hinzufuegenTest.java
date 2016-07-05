package jUnitTests;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Date;

import bl.AfdbHinzufuegen;
import data.Adresse;
import data.AnforderungsArt;
import data.Benutzer;
import data.Benutzerart;
import data.Kunde;
import data.Modul;
import data.Prioritaet;
import data.Status;
import data.Version;

public class hinzufuegenTest {
	
	@Test
	public void testHinzu() {
		try {
			AfdbHinzufuegen hinzu = new AfdbHinzufuegen();
		
			String titel = "TestTitel1";
			String beschreibung = "Dies ist ein Test";
			Benutzer zugBenutzer = new Benutzer(
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
			Date erfDatum = new Date();
			Benutzer ansprPers = zugBenutzer;
			Kunde kd = new Kunde(
					1,
					"4711",
					"Autohaus Huber",
					new Adresse(1,"Hauptstraﬂe",1,"a",8430,"Leibnitz","÷sterreich"),
					zugBenutzer,
					"office@autohaus-huber.at");
			AnforderungsArt anfArt = new AnforderungsArt(1,"Anforderung Software");
			Prioritaet prio = new Prioritaet(3, "Normal");
			Status status = new Status(1,"Offen");
			Benutzer anlegeBenutzer = zugBenutzer;
			Modul modul = new Modul(1,"Basis");
			Version version = new Version(1,"V3.1");
			String hdNr = "0";
			String aufwand = "0";
			float aufwandGesch = 0;
			Date fertigStellGepl = new Date();
			Date fertigStellIst = null;
			String verwAnforderungen = "";
			String schluesselBegriffe = "test";
			
			boolean erfolg = hinzu.createAfdb(titel, beschreibung, anlegeBenutzer, erfDatum, ansprPers, kd, anfArt, prio, status, zugBenutzer, modul, version, hdNr,
					aufwandGesch, fertigStellGepl, fertigStellIst, verwAnforderungen, schluesselBegriffe); 
			
			assertEquals("speichern nicht erfolgreich", true, erfolg);
		} catch (Exception e) {
			fail("Fehler aufgetreten");
		}
	}
}
