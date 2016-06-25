-- Foreign Keys droppen bevor man sie neu erstellt:
alter table Benutzer drop foreign key BenutzerBenutzerart_FK;
alter table Kunde drop foreign key KundeAdresse_FK;
alter table Kunde drop foreign key KundeKontaktperson_FK;
alter table Anforderung drop foreign key AnforderungAngelegtVon_FK;
alter table Anforderung drop foreign key AnforderungAnsprechPerson_FK;
alter table Anforderung drop foreign key AnforderungKunde_FK;
alter table Anforderung drop foreign key AnforderungAnforderungsArt_FK;
alter table Anforderung drop foreign key AnforderungStatus_FK;
alter table Anforderung drop foreign key AnforderungZugewiesenAn_FK;
alter table Anforderung drop foreign key AnforderungModul_FK;
alter table Anforderung drop foreign key AnforderungPrio_FK;	
alter table Anforderung drop foreign key AnforderungVersion_FK;
alter table KopieAn drop foreign key KopieAnAnforderung_FK;	
alter table KopieAn drop foreign key KopieAnBenutzer_FK;
alter table VerwandteAnforderung drop foreign key VerwandteAnfMasterAnf_FK;	
alter table VerwandteAnforderung drop foreign key VerwandteAnfSlaveAnf_FK;
-- alter table AnforderungAnhang drop foreign key AnfAnhangAnforderung_FK;
-- alter table AnforderungAnhang drop foreign key AnfAnhangAnhang_FK;
alter table Kommentar drop foreign key KommentarAnforderung_FK;
alter table Kommentar drop foreign key KommentarBenutzer_FK;
-- alter table KommentarAnhang drop foreign key KommentarAnhangKommentar_FK;
-- alter table KommentarAnhang drop foreign key KommentarAnhangAnhang_FK;
alter table Anhang drop foreign key AnhangAnforderung_FK;
alter table Anhang drop foreign key AnhangKommentar_FK;


-- Foreign Keys neu erstellen:
-- Benutzer
alter table Benutzer add constraint BenutzerBenutzerart_FK
	foreign key (Benutzerart) references Benutzerart(BenutzerartId);

-- Kunde
alter table Kunde add constraint KundeAdresse_FK
	foreign key (Adresse) references Adresse(AdresseId);

alter table Kunde add constraint KundeKontaktperson_FK
	foreign key (KontaktPerson) references Benutzer(BenutzerId);

-- Anforderung
alter table Anforderung add constraint AnforderungAngelegtVon_FK
	foreign key (AngelegtVon) references Benutzer(BenutzerId);

alter table Anforderung add constraint AnforderungAnsprechPerson_FK
	foreign key (AnsprechPerson) references Benutzer(BenutzerId);

alter table Anforderung add constraint AnforderungKunde_FK
	foreign key (Kunde) references Kunde(KundeId);

alter table Anforderung add constraint AnforderungAnforderungsArt_FK
	foreign key (AnforderungsArt) references AnforderungsArt(AnforderungsArtId);

alter table Anforderung add constraint AnforderungStatus_FK
	foreign key (Status) references Status(StatusId);

alter table Anforderung add constraint AnforderungZugewiesenAn_FK
	foreign key (ZugewiesenAn) references Benutzer(BenutzerId);

alter table Anforderung add constraint AnforderungModul_FK
	foreign key (Modul) references Modul(ModulId);

alter table Anforderung add constraint AnforderungPrio_FK
	foreign key (Prioritaet) references Prioritaet(PrioritaetId);
	
alter table Anforderung add constraint AnforderungVersion_FK
	foreign key (Version) references Version(VersionId);

-- KopieAn
alter table KopieAn add constraint KopieAnAnforderung_FK
	foreign key (AnforderungId) references Anforderung(AnforderungId);
	
alter table KopieAn add constraint KopieAnBenutzer_FK
	foreign key (BenutzerId) references Benutzer(BenutzerId);

-- VerwandteAorderungen
alter table VerwandteAnforderung add constraint VerwandteAnfMasterAnf_FK
	foreign key (MasterAnforderungId) references Anforderung(AnforderungId);
	
alter table VerwandteAnforderung add constraint VerwandteAnfSlaveAnf_FK
	foreign key (AnforderungId) references Anforderung(AnforderungId);

-- -- AnforderungAnhang
-- alter table AnforderungAnhang add constraint AnfAnhangAnforderung_FK
-- 	foreign key (AnforderungId) references Anforderung(AnforderungId);

-- alter table AnforderungAnhang add constraint AnfAnhangAnhang_FK
-- 	foreign key (AnhangId) references Anhang(AnhangId);


-- Anhang     
alter table Anhang add constraint AnhangAnforderung_FK
	foreign key (AnforderungId) references Anforderung(AnforderungId);
    
alter table Anhang add constraint AnhangKommentar_FK
	foreign key (AnforderungId) references Kommentar(KommentarId); 
	
	
-- Kommentar
alter table Kommentar add constraint KommentarAnforderung_FK
	foreign key (Anforderung) references Anforderung(AnforderungId);

alter table Kommentar add constraint KommentarBenutzer_FK
	foreign key (Benutzer) references Benutzer(BenutzerId);

-- -- KommentarAnhang
-- alter table KommentarAnhang add constraint KommentarAnhangKommentar_FK
-- 	foreign key (KommentarId) references Kommentar(KommentarId);

-- alter table KommentarAnhang add constraint KommentarAnhangAnhang_FK
-- 	foreign key (AnhangId) references Anhang(AnhangId);
