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

-- AnforderungAnhang
alter table AnforderungAnhang add constraint AnfAnhangAnforderung_FK
	foreign key (AnforderungId) references Anforderung(AnforderungId);

alter table AnforderungAnhang add constraint AnfAnhangAnhang_FK
	foreign key (AnhangId) references Anhang(AnhangId);

-- Kommentar
alter table Kommentar add constraint KommentarAnforderung_FK
	foreign key (Anforderung) references Anforderung(AnforderungId);

alter table Kommentar add constraint KommentarBenutzer_FK
	foreign key (Benutzer) references Benutzer(BenutzerId);

-- KommentarAnhang
alter table KommentarAnhang add constraint KommentarAnhangKommentar_FK
	foreign key (KommentarId) references Kommentar(KommentarId);

alter table KommentarAnhang add constraint KommentarAnhangAnhang_FK
	foreign key (AnhangId) references Anhang(AnhangId);
