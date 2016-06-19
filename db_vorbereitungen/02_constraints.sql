-- Benutzer
alter table Benutzer add constraint BenutzerBenutzerart_FK
	foreign key (Benutzerart) references Benutzerarten(BenutzerartId);

-- Kunden
alter table Kunden add constraint KundeAdresse_FK
	foreign key (Adresse) references Adressen(AdresseId);

alter table Kunden add constraint KundeKontaktperson_FK
	foreign key (KontaktPerson) references Benutzer(BenutzerId);

-- Anforderungen
alter table Anforderungen add constraint AnforderungAngelegtVon_FK
	foreign key (AngelegtVon) references Benutzer(BenutzerId);

alter table Anforderungen add constraint AnforderungAnsprechPerson_FK
	foreign key (AnsprechPerson) references Benutzer(BenutzerId);

alter table Anforderungen add constraint AnforderungKunde_FK
	foreign key (Kunde) references Kunden(KundeId);

alter table Anforderungen add constraint AnforderungAnforderungsArt_FK
	foreign key (AnforderungsArt) references AnforderungsArten(AnforderungsArtId);

alter table Anforderungen add constraint AnforderungStatus_FK
	foreign key (Status) references Status(StatusId);

alter table Anforderungen add constraint AnforderungZugewiesenAn_FK
	foreign key (ZugewiesenAn) references Benutzer(BenutzerId);

alter table Anforderungen add constraint AnforderungModul_FK
	foreign key (Modul) references Module(ModulId);

-- KopieAn
alter table KopieAn add constraint KopieAnAnforderung_FK
	foreign key (AnforderungId) references Anforderungen(AnforderungId);
	
alter table KopieAn add constraint KopieAnBenutzer_FK
	foreign key (BenutzerId) references Benutzer(BenutzerId);

-- VerwandteAorderungen
alter table VerwandteAnforderungen add constraint VerwandteAnfMasterAnf_FK
	foreign key (MasterAnforderungId) references Anforderungen(AnforderungId);
	
alter table VerwandteAnforderungen add constraint VerwandteAnfSlaveAnf_FK
	foreign key (AnforderungId) references Anforderungen(AnforderungId);

-- AnforderungAnhaenge
alter table AnforderungAnhaenge add constraint AnfAnhangAnforderung_FK
	foreign key (AnforderungId) references Anforderungen(AnforderungId);

alter table AnforderungAnhaenge add constraint AnfAnhangAnhang_FK
	foreign key (AnhangId) references Anhaenge(AnhangId);

-- Kommentare
alter table Kommentare add constraint KommentarAnforderung_FK
	foreign key (Anforderung) references Anforderungen(AnforderungId);

alter table Kommentare add constraint KommentarBenutzer_FK
	foreign key (Benutzer) references Benutzer(BenutzerId);

-- KommentarAnhaenge
alter table KommentarAnhaenge add constraint KommentarAnhangKommentar_FK
	foreign key (KommentarId) references Kommentare(KommentarId);

alter table KommentarAnhaenge add constraint KommentarAnhangAnhang_FK
	foreign key (AnhangId) references Anhaenge(AnhangId);
