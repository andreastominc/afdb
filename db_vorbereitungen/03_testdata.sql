-- Testdaten fuer Status --
INSERT INTO status VALUES(1,'Offen');
INSERT INTO status VALUES(2,'Programmierung genehmigt');
INSERT INTO status VALUES(3,'In Programmierung');
INSERT INTO status VALUES(4,'Programmierung erledigt');
INSERT INTO status VALUES(5,'in Test');
INSERT INTO status VALUES(6,'Abgeschlossen');
INSERT INTO status VALUES(7,'Abgelehnt');

-- Testdaten fuer AnforderungsArt--
INSERT INTO AnforderungsArt VALUES(1,'Anforderung Software');
INSERT INTO AnforderungsArt VALUES(2,'Anforderung Hardware');
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
(1, 'Hauptstra�e', 1, 'a', 8430, 'Leibnitz', '�sterreich'),
(2, 'Conrad von H�tzendorf ', 10, '', 8010, 'Graz', '�sterreich'),
(3, 'Feldweg', 5, '/5', 80331, 'M�nchen', 'Deutschland');

-- Testdaten fuer Benutzerart
INSERT INTO `benutzerart` (`BenutzerartId`, `Bezeichnung`, `SchreibRecht`) VALUES
(1, 'MD-Mitarbeiter', 1),
(2, 'Kunde', 0);

-- Testdaten fuer Benutzer
INSERT INTO `benutzer` (`BenutzerId`, `Vorname`, `Nachname`, `Benutzername`, `Passwort`, `AnlegeDatum`, `GueltigBis`, `Benutzerart`, `Telefon`, `Mail`) VALUES
(1, 'Andreas', 'Tominc', 'atominc', 'atominc', '2016-06-20', '2016-06-20', 1, '+436641234567', 'andreas.tominc@edu.campus02.at'),
(2, 'Philipp', 'Hiezer', 'phierzer', 'phierzer', '2016-06-20', '2016-06-20', 1, '+436641234568', 'philipp.hierzer@edu.campus02.at'),
(3, 'Thomas', 'Muster', 'tmuster', 'tmuster', '2016-06-20', '2016-06-20', 2, '+436641234569', 'max.mustermann@edu.campus02.at'),
(4, 'Franz', 'Huber', 'fhuber', 'fhuber', '2016-06-21', '2016-06-21', 2, '+436641234570', 'franz.huber@authaus-huber.at'),
(5, 'Josef', 'Max', 'jmax', 'jmax', '2016-06-21', '2016-06-21', 2, '+436641234571', 'josef.max@autohaus-max.at'),
(6, 'Max', 'Mustermann', 'mmustermann', 'mmustermann', '2016-06-20', '2016-06-20', 2, '+436641234572', 'max.mustermann@autohaus-mustermann.at');

-- Testdaten fuer Kunde
INSERT INTO `kunde` (`KundeId`, `Kundennummer`, `Bezeichnung`, `Adresse`, `KontaktPerson`, `Mail`) VALUES
(1, '4711', 'Autohaus Huber', 1, 4, 'office@autohaus-huber.at'),
(2, '4712', 'Autohaus Mustermann', 2, 6, 'office@autohaus-mustermann.at'),
(3, '4713', 'Autohaus Max', 3, 5, 'office@autohaus-max.at');