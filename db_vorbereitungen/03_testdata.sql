-- Testdaten fuer Status --
INSERT INTO status VALUES(1,'Offen');
INSERT INTO status VALUES(2,'Analyse');
INSERT INTO status VALUES(3,'Programmierung genehmigt');
INSERT INTO status VALUES(4,'In Programmierung');
INSERT INTO status VALUES(5,'Programmierung erledigt');
INSERT INTO status VALUES(6,'in Test');
INSERT INTO status VALUES(7,'Test erledigt');
INSERT INTO status VALUES(8,'Abgeschlossen');
INSERT INTO status VALUES(9,'Abgelehnt');

-- Testdaten fuer AnforderungsArt--
INSERT INTO AnforderungsArt VALUES(1,'Anforderung Software');
INSERT INTO AnforderungsArt VALUES(2,'Anforderung Projekt');
INSERT INTO AnforderungsArt VALUES(3,'Change Request');
INSERT INTO AnforderungsArt VALUES(4,'Info');

-- Testdaten fuer Prioritaet--
INSERT INTO Prioritaet VALUES(1,'Sehr Hoch');
INSERT INTO Prioritaet VALUES(2,'Hoch');
INSERT INTO Prioritaet VALUES(3,'Normal');
INSERT INTO Prioritaet VALUES(4,'Niedrig');
INSERT INTO Prioritaet VALUES(5,'Sehr niedrig');

-- Testdaten fuer Modul--
INSERT INTO Modul VALUES(1,'Basis');
INSERT INTO Modul VALUES(2,'CRM');
INSERT INTO Modul VALUES(3,'Fahrzeughandel');
INSERT INTO Modul VALUES(4,'Service');
INSERT INTO Modul VALUES(5,'Zeiterfassung');
INSERT INTO Modul VALUES(6,'Teilehandel');
INSERT INTO Modul VALUES(7,'Finanz');
INSERT INTO Modul VALUES(8,'Kommunikation');

-- Testdaten fuer Version--
INSERT INTO Version VALUES(1,'V3.1');
INSERT INTO Version VALUES(2,'VSR2.1');
INSERT INTO Version VALUES(3,'VSR2.2');
INSERT INTO Version VALUES(4,'VSR3.0');
INSERT INTO Version VALUES(5,'VSR3.2');
INSERT INTO Version VALUES(6,'VSR4.0');
INSERT INTO Version VALUES(7,'VSR4.2');
INSERT INTO Version VALUES(8,'VSR5.0');
INSERT INTO Version VALUES(9,'VSR5.1');
INSERT INTO Version VALUES(10,'V6R0.0');
INSERT INTO Version VALUES(11,'V6R1.0');
INSERT INTO Version VALUES(12,'V6R2.0');
INSERT INTO Version VALUES(13,'V6R3.0');
INSERT INTO Version VALUES(14,'V6R4.0');
INSERT INTO Version VALUES(15,'V7R0.0');
INSERT INTO Version VALUES(16,'V7R1.0');

-- Testdaten fuer Adresse
INSERT INTO `adresse` (`AdresseId`, `Strasse`, `Hausnummer`, `HausnummerZusatz`, `Postleitzahl`, `Ort`, `Land`) VALUES
(1, 'Langer Weg', 12, '', 6020, 'Innsbruck', 'Österreich'),
(2, 'Alpenstraße', 122, '', 5020, 'Salzburg', 'Österreich'),
(3, 'Gewerbepark Nord', 1, '', 8431, 'Gralla', 'Österreich'),
(4, 'Erdbergstraße', 189, '', 1030, 'Wien', 'Österreich'),
(5, 'Raiffeisenstraße', 200, '', 8041, 'Graz', 'Österreich');

-- Testdaten fuer Benutzerart
INSERT INTO `benutzerart` (`BenutzerartId`, `Bezeichnung`, `SchreibRecht`) VALUES
(1, 'MD-Mitarbeiter', 1),
(2, 'Kunde', 0);

-- Testdaten fuer Benutzer
INSERT INTO `benutzer` (`BenutzerId`, `Vorname`, `Nachname`, `Benutzername`, `Passwort`, `AnlegeDatum`, `GueltigBis`, `Benutzerart`, `Telefon`, `Mail`) VALUES
(1, 'Andreas', 'Tominc', 'atominc', 'atominc', '2016-06-20', '2016-06-20', 1, '+436641234567', 'andreas.tominc@edu.campus02.at'),
(2, 'Philipp', 'Hiezer', 'phierzer', 'phierzer', '2016-06-20', '2016-06-20', 1, '+436641234568', 'philipp.hierzer@edu.campus02.at'),
(3, 'Julian', 'Neumeister', 'jneumeister', 'jneumeister', '2016-06-20', '2016-06-20', 1, '+436641234569', 'julian.neumeister@edu.campus02.at'),
(4, 'Michael', 'Hödl', 'mhoedl', 'mhoedl', '2016-06-20', '2016-06-20', 1, '+436641234511', 'michael.hoedl@edu.campus02.at'),
(5, 'Markus', 'Singer', 'msinger', 'msinger', '2016-06-20', '2016-06-20', 1, '+436641234512', 'markus.singer@edu.campus02.at'),
(6, 'Bernhard', 'Gangl', 'bgangl', 'bgangl', '2016-06-20', '2016-06-20', 1, '+436641234513', 'bernhard.gangl@motiondata.at'),
(7, 'Daniel', 'Kumpitsch', 'dkumpitsch', 'dkumpitsch', '2016-06-20', '2016-06-20', 1, '+436641234514', 'daniel.kumpitsch@motiondata.at'),
(8, 'Marko', 'Schuster', 'mschuster', 'mschuster', '2016-06-20', '2016-06-20', 2, '+436641234515', 'marko.schuster@autopark.at'),
(9, 'Astrid', 'Schmidt', 'aschmidt', 'aschmidt', '2016-06-20', '2016-06-20', 1, '+436641234516', 'astrid.schmidt@auto-schmidt.at');

-- Testdaten fuer Kunde
INSERT INTO `kunde` (`KundeId`, `Kundennummer`, `Bezeichnung`, `Adresse`, `KontaktPerson`, `Mail`) VALUES
(1, '11012', 'Autopark GmbH', 1, 8, 'office@autopark.at'),
(2, '10427', 'Oskar Schmidt GmbH ', 2, 9, 'office@auto-schmidt.at'),
(3, '11557', 'Hirschmugl GmbH & Co KG', 3, 7, 'office@hirschmugl.eu'),
(4, '21092', 'Wolfgang Denzel Auto AG', 4, 7, 'office@denzel.at'),
(5, '17199 ', 'Jagersberger Service und Werkstätten GmbH', 5, 7, 'office@jagersberger-automobil.at');

-- Testdaten fuer Anforderungen
INSERT INTO `anforderung` (`AnforderungId`, `Titel`, `Beschreibung`, `AngelegtVon`, `ErfassungsDatum`, `AnsprechPerson`, `Kunde`, `AnforderungsArt`, `Prioritaet`, `Status`, `ZugewiesenAn`, `Modul`, `Version`, `HelpdeskNummer`, `AufwandGeschaetzt`, `FertigstellungGeplant`, `FertigstellungIst`, `VerwAnforderungen`, `SchluesselBegriffe`) VALUES
(1, 'Buchungsart im Kassenbuch ersichtlich machen', 'Nach dem die Kassenbuchung nicht mehr in die Bearbeitungsleiste geladen werden kann (Registrierkassenverordnung), geht die Information über die Buchungsart verloren.\nBitte im Browser des Kassenbuchen eine eigene Spalte für die Buchungsart aufnehmen. ', 2, '2016-07-05', 8, 1, 1, 2, 1, 6, 7, 15, '102580', 0, '2016-07-31', NULL, '', 'Kassa, Kassenbuch, Buchungsart'),
(2, 'Mischpreisaufrollung bei Einkaufspreis 0', 'Bei Fa. Schmidt kommt es vor das Lieferanten Teile mit 100% Rabatt liefern. Bei der Einbuchung über den Wareneingang wird dieses Teil mit 100% Rabatt erfasst -> jedoch wird hierfür aktuell die Mischpreisaufrollung nicht angestoßen.\n\nFa. Schmidt würde dies jedoch benötigen -> angefügt Mailverlauf mit Fr. Schmidt.', 2, '2016-07-05', 9, 2, 1, 4, 4, 3, 6, 16, '102598', 0, '2016-12-31', NULL, '', ''),
(3, 'Nova-Kalkulator für MD CRM Angebotswesen', 'IST-Zustannd\nDer bestehende MD DMS NoVA-Kalkulator besitzt die Funktionen der retrograden NoVA-Berechnungen für PKW, Motorräder und Wohnmobile.\n\nDiese Funktionen fehlen im aktuellen Web NoVA-Rechner.\n\n------------------------------------------------\n\nSOLL-Zustand\nDie Logik des DMS NoVA-Kalkulators soll auch für das Cloud CRM zur Verfügung stehen. Dabei soll über eine Parameterliste aus dem Cloud CRM ein Webservice angesprochen werden,dass die Berechnung der NoVA\n\n- für PKW\n- für Motorräder\n- für Wohnmobile\n- auch retrograd durchführen kann.', 2, '2016-07-05', 7, 4, 1, 3, 3, 1, 3, 15, '102591', 0, '2016-10-31', NULL, '', ''),
(4, ' Volvo Customer Data Change Interface', 'Das Volvo Customer Data Change Interface verwendet denselben Satzaufbau wie das Owner Change Interface, nur dass hier noch weitere Felder befüllt werden, die beim Owner Change nicht befüllt werden.\nEs muss hier also eine weitere Task Controller Komponente geschaffen werden, durch die die Kundenänderungen gemeldet werden können.\nDetails zum Satzaufbau bzw. zum Ablauf wurden von Volvo gefunden und angehängt bzw. unter "S:\\Produkte\\MOTIONDATA\\Doku\\Marken\\Volvo\\Kundenänderungs-Interface" abgelegt.', 2, '2016-07-05', 8, 1, 3, 3, 1, 6, 3, 15, '102597', 0, '2016-09-30', NULL, '', 'Volvo, Volvo Customer'),
(5, 'Änderung Erstzlassung - Prüftermin automat. berechnen', 'Früher ist bei der Änderung des Erstzulassungsdatums, eine Meldung gekommen -> "Sollen die Prüftermine neu berechnet werden?", gleich wie bei der Bauartänderung!\nSo ist es natürlich problematisch, auch wenn der Button direkt neben dem Erstzulassungsdatum ist aber, ob man da jetzt manuell drauf klickt, oder die Daten manuell einträgt, ist dann auch nicht mehr so viel um, und von der Usability auch eher schwach, daher bitte die Meldung auch bei Erstzulassungsdatum bereitstellen...', 2, '2016-07-05', 7, 5, 3, 2, 7, 3, 4, 15, '102593', 0, '2016-07-08', NULL, '', 'Erstzulassung, Erstzulassungsdatum, Prüftermin'),
(6, 'Tagesprotokoll Summenblatt', 'Firma Schmidt hätte gerne einen zweiten Report für das Tagesprotokoll zur Auswahl bei dem mehrere MA auf einer Seite zusammengefasst werden, theoretisch würden sich bis zu 3 MA auf einer Seite ausgehen.\n\nAllerdings hab ich versucht den Report (ZETAGPRO.RPT) genauso anzupassen allerdings ohne Erfolg weil anscheinend pro Seite ein MA ausgegeben wird, sprich wie auf meinen Screenshot ersichtlich hätte ich den gleichen MA 2x auf einer Seite statt den Nächsten.\n\nBitte um Info ob es vielleicht schon einen Report dafür gibt und wenn nicht ob es möglich ist diese Anpassung vorzunehmen.', 2, '2016-07-05', 9, 2, 3, 2, 2, 2, 5, 1, '102595', 6, '2016-07-20', NULL, '', 'Zeiterfassung, Tagesprotokoll, Summenblatt'),
(7, 'TOY Monatsstatistik - Teile-Barverkäufe über Service-Aufträge', 'Hr. Englich möchte, dass fakturierte Service-Aufträge mit ausgewählter Abteilung "ETH" unter Teile Lager - Barverkäufe in der Monatsstatistik aufscheinen und nicht im Service-Bereich.\nGrund ist lt. Herrn Spatt der, dass er Barverkäufe auch anhand vom hinterlegten FZG (daher Service-Auftrag) finden will...\n\nNach Abklärung mit WKA ist dies aktuell nicht einstellbar, da bei richtigen Barverkäufen (Teilehandels-Auftrag) im Programm fix auf den Abteilungscode (abtl_cd) "ET" abgefragt wird.\nIm Anhang der Mailverkehr mit WKA, KUM mit detaillierteren Infos.\n\nBitte um Analyse und Info, ob hier eine Änderung möglich ist.\nEs hat mir keiner sagen können, warum hier eig. auf das Feld abtl_cd abgefragt wird, und nicht auf das Feld abteilung, welche bei den Aufträgen hinterlegt ist.', 2, '2016-07-05', 7, 3, 1, 4, 7, 2, 6, 1, '102599', 0, '2016-07-01', NULL, '2', 'Toyota, Monatsstatistik'),
(8, 'Liste Offene Rechnungen mit Sortierung nach Kundenname', 'Hr. Hettrich gibt die Liste für Offene Rechnungen in der OP-Verwaltung mit Sortierung nach Kundennamen aus. \nDabei ist aufgefallen, dass hier Kunden mit gleichen Nachnamen zusammengefasst werden. Bitte um Änderung.\n\nZusätzlich wenn möglich umsetzen:\n- Eventuell kann man in den MD-Betriebsdaten unter Textcode diesen Text anlegen/warten, und dieser wird dann auf der Liste Offene Rechnungen angedruckt\n- Oder man macht eine Reportauswahl, wo man einen angepassten Report auswählen kann. Wenn über eigenen Report, sollte dieser aber als "Standard" definiert werden können, und nicht immer manuell ausgewählt werden müssen.', 2, '2016-07-05', 7, 5, 3, 4, 3, 2, 7, 1, '102601', 0, '2016-08-12', NULL, '1', 'OP, Liste OP');

commit;

-- Passwoerter MD5 verschluesseln:
update Benutzer set passwort = '40747cf43bd98b345006ba683fadbe63' where passwort = 'atominc';
update Benutzer set passwort = 'a21c21e40c01ff8bb48c8ac8c049df5c' where passwort = 'phierzer';
update Benutzer set passwort = '145791fba13d4acb36a6dd930e6e5079' where passwort = 'jneumeister';
update Benutzer set passwort = '39d2a08723625c2654c88ca75ec677a5' where passwort = 'mhoedl';
update Benutzer set passwort = '16f3f36cbb67e88ee9a025eac72bae53' where passwort = 'msinger';
update Benutzer set passwort = 'f2c16fe5d29c4d13d9d6e2412f69c97b' where passwort = 'mschuster';
commit;