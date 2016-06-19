-- Benutzerart
create table `Benutzerarten` (
	`BenutzerartId`   int primary key,
	`Bezeichnung`     varchar(50) not null,
	`SchreibRecht`    boolean default false
);

-- Benutzer
create table `Benutzer` (
	`BenutzerId`    int primary key,
	`Vorname`       varchar(50) not null,
	`Nachname`      varchar(50) not null,
	`Benutzername`  varchar(50) not null,
	`Passwort`      varchar(32) not null, /* encrypted pwd */
	`AnlegeDatum`   date not null,
	`GültiBis`      date,
	`Benutzerart`	int not null, /* FK zu Benutzerart */
	`Telefon`       varchar(20),
	`Mail`          varchar(100) not null
);

-- Adresse
create table `Adressen` (
	`AdresseId`         int primary key,
	`Straße`            varchar(50) not null,
	`Hausnummer`        int not null,
	`HausnummerZusatz`  varchar(10),
	`Postleitzahl`      int not null,
	`Ort`               varchar(50) not null,
	`Land`              varchar(50) not null
);

-- Kunden
create table `Kunden` (
	`KundeId`         int primary key,
	`Kundennummer`    varchar(20),
	`Bezeichnung`     varchar(20) not null,
	`Adresse`         int not null, /* FK zu Adressen */
	`KontaktPerson`   int, /* FK zu Benutzer */
	`Mail`            varchar(50)
);

-- AnforderungsArten
create table AnforderungsArten (
	`AnforderungsartId`      int primary key,
	`Bezeichnung`            varchar(100) not null
);

-- Anforderungen
create table `Anforderungen` (
	`AnforderungId`         int primary key,
	`Titel`                 varchar(2000) not null,
	`Beschreibung`          text not null,
	`AngelegtVon`           int not null, /* FK zu Benutzer */
	`ErfassungsDatum`       date not null,
	`AnsprechPerson`        int, /* FK zu Benutzer */
	`Kunde`                 int not null, /* FK zu Kunden */
	`AnforderungsArt`       int not null, /* FK zu AnforderungsArten */
	`Priorität`             int not null,
	`Status`                int not null, /* FK zu Status */
	`ZugewiesenAn`          int not null, /* FK zu Benutzer */
	`Modul`                 int, /* FK zu Modul */
	`Version`               varchar(10),
	`HelpdeskNummer`        varchar(10),
	`AufwandGeschätzt`      float not null,
	`FertigstellungGeplant` date not null,
	`FertigstellungIst`     date,
	`Auftragsnummer`        varchar(10),
	`SchlüsselBegriffe`     varchar(32000)
);

-- Anhänge
create table `Anhänge` (
	`AnhangId`       int primary key,
	`Datei`          blob not null,
	`Name`           varchar(2000) not null,
	`HinzugefügtAm`  date not null
);

-- Beziehungstabelle "KopieAn"
create table `KopieAn` (
	`AnforderungId`    int, /* FK zu Anforderungen */
	`BenutzerId`       int, /* FK zu Benutzer*/
	primary key (`AnforderungId`,`BenutzerId`)
);

-- Beziehungstabelle "verwandteAnforderungen"
create table `VerwandteAnforderungen` (
	`MasterAnforderungId` int, /* FK zu Anforderungen */
	`AnforderungId`       int, /* FK zu Anforderungen */
	primary key (`MasterAnforderungId`,`AnforderungId`)
);

-- Beziehungstabelle "AnforderungAnhänge"
create table `AnforderungAnhänge` (
	`AnforderungId` int, /* FK zu Anforderungen */
	`AnhangId`      int, /* FK zu Anhänge */
	primary key (`AnforderungId`,`AnhangId`)
);

-- Kommentar
create table `Kommentare` (
	`KommentarId`      int primary key,
	`Anforderung`      int not null, /* FK zu Anforderungen */
	`BearbeitetVon`    date not null,
	`BearbeitetBis`    date not null,
	`KommentarText`    text not null,
	`ExternSichtbar`   boolean default false,
	`Benutzer`         int not null, /* FK zu Benutzer */
	`HinzugefügtAm`    date not null
);

-- Beziehungstabelle "KommentarAnhänge"
create table `KommentarAnhänge` (
	`KommentarId`      int, /* FK zu Kommentare */
	`AnhangId`         int,  /* FK zu Anhänge */
	primary key (`KommentarId`,`AnhangId`)
);

-- Status
create table `Status` (
	`StatusId`      int primary key,
	`Bezeichnung`   varchar(100) not null
);

-- Modul
create table `Module` (
	`ModulId`       int primary key,
	`Bezeichnung`   varchar(100) not null
);
