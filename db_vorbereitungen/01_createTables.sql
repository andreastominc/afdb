-- Benutzerart
create table `Benutzerart` (
	`BenutzerartId`   int primary key auto_increment,
	`Bezeichnung`     varchar(50) not null,
	`SchreibRecht`    boolean default false
);

-- Benutzer
create table `Benutzer` (
	`BenutzerId`    int primary key auto_increment,
	`Vorname`       varchar(50) not null,
	`Nachname`      varchar(50) not null,
	`Benutzername`  varchar(50) not null unique key,
	`Passwort`      varchar(32) not null, /* encrypted pwd */
	`AnlegeDatum`   date not null,
	`GueltigBis`     date,
	`Benutzerart`	int not null, /* FK zu Benutzerart */
	`Telefon`       varchar(20),
	`Mail`          varchar(100) not null
);

-- Adresse
create table `Adresse` (
	`AdresseId`         int primary key auto_increment,
	`Strasse`           varchar(50) not null,
	`Hausnummer`        int not null,
	`HausnummerZusatz`  varchar(10),
	`Postleitzahl`      int not null,
	`Ort`               varchar(50) not null,
	`Land`              varchar(50) not null
);

-- Kunde
create table `Kunde` (
	`KundeId`         int primary key auto_increment,
	`Kundennummer`    varchar(20) not null unique key,
	`Bezeichnung`     varchar(20) not null unique key,
	`Adresse`         int not null, /* FK zu Adressen */
	`KontaktPerson`   int, /* FK zu Benutzer */
	`Mail`            varchar(50)
);

-- AnforderungsArt
create table AnforderungsArt (
	`AnforderungsartId`      int primary key auto_increment,
	`Bezeichnung`            varchar(100) not null
);

-- Anforderung
create table `Anforderung` (
	`AnforderungId`         int primary key auto_increment,
	`Titel`                 varchar(2000) not null,
	`Beschreibung`          text not null,
	`AngelegtVon`           int not null, /* FK zu Benutzer */
	`ErfassungsDatum`       date not null,
	`AnsprechPerson`        int, /* FK zu Benutzer */
	`Kunde`                 int not null, /* FK zu Kunden */
	`AnforderungsArt`       int not null, /* FK zu AnforderungsArten */
	`Prioritaet`            int not null, /* FK zu Prioritaet */
	`Status`                int not null, /* FK zu Status */
	`ZugewiesenAn`          int not null, /* FK zu Benutzer */
	`Modul`                 int not null, /* FK zu Modul */
	`Version`               int not null, /* FK zu Version */
	`HelpdeskNummer`        varchar(10),
	`AufwandGeschaetzt`     float,
	`FertigstellungGeplant` date,
	`FertigstellungIst`     date,
	`SchluesselBegriffe`    varchar(32000)
);

-- Anhang
create table `Anhang` (
	`AnhangId`       int primary key auto_increment,
	`Datei`          blob not null,
	`Name`           varchar(2000) not null,
	`HinzugefuegtAm` date not null
);

-- Beziehungstabelle "KopieAn"
create table `KopieAn` (
	`AnforderungId`    int, /* FK zu Anforderungen */
	`BenutzerId`       int, /* FK zu Benutzer*/
	primary key (`AnforderungId`,`BenutzerId`)
);

-- Beziehungstabelle "verwandteAnforderung"
create table `VerwandteAnforderung` (
	`MasterAnforderungId` int, /* FK zu Anforderungen */
	`AnforderungId`       int, /* FK zu Anforderungen */
	primary key (`MasterAnforderungId`,`AnforderungId`)
);

-- Beziehungstabelle "AnforderungAnhang"
create table `AnforderungAnhang` (
	`AnforderungId` int, /* FK zu Anforderungen */
	`AnhangId`      int, /* FK zu Anhaenge */
	primary key (`AnforderungId`,`AnhangId`)
);

-- Kommentar
create table `Kommentar` (
	`KommentarId`      int primary key auto_increment,
	`Anforderung`      int not null, /* FK zu Anforderungen */
	`BearbeitetVon`    date not null,
	`BearbeitetBis`    date not null,
	`KommentarText`    text not null,
	`ExternSichtbar`   boolean default false,
	`Benutzer`         int not null, /* FK zu Benutzer */
	`HinzugefuegtAm`   date not null
);

-- Beziehungstabelle "KommentarAnhang"
create table `KommentarAnhang` (
	`KommentarId`      int, /* FK zu Kommentare */
	`AnhangId`         int,  /* FK zu Anhaenge */
	primary key (`KommentarId`,`AnhangId`)
);

-- Status
create table `Status` (
	`StatusId`      int primary key auto_increment,
	`Bezeichnung`   varchar(100) not null
);

-- Modul
create table `Modul` (
	`ModulId`       int primary key auto_increment,
	`Bezeichnung`   varchar(100) not null
);

-- Priorität
create table Prioritaet (
	`PrioritaetId`      int primary key auto_increment,
	`Bezeichnung`       varchar(100) not null
);

-- Version
create table Version (
	`VersionId`      int primary key auto_increment,
	`Bezeichnung`    varchar(100) not null
);

