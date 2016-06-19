-- Benutzerart
create table `Benutzerarten` (
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
	`GueltiBis`     date,
	`Benutzerart`	int not null, /* FK zu Benutzerart */
	`Telefon`       varchar(20),
	`Mail`          varchar(100) not null
);

-- Adresse
create table `Adressen` (
	`AdresseId`         int primary key auto_increment,
	`Strasse`           varchar(50) not null,
	`Hausnummer`        int not null,
	`HausnummerZusatz`  varchar(10),
	`Postleitzahl`      int not null,
	`Ort`               varchar(50) not null,
	`Land`              varchar(50) not null
);

-- Kunden
create table `Kunden` (
	`KundeId`         int primary key auto_increment,
	`Kundennummer`    varchar(20) not null unique key,
	`Bezeichnung`     varchar(20) not null,
	`Adresse`         int not null, /* FK zu Adressen */
	`KontaktPerson`   int, /* FK zu Benutzer */
	`Mail`            varchar(50)
);

-- AnforderungsArten
create table AnforderungsArten (
	`AnforderungsartId`      int primary key auto_increment,
	`Bezeichnung`            varchar(100) not null
);

-- Anforderungen
create table `Anforderungen` (
	`AnforderungId`         int primary key auto_increment,
	`Titel`                 varchar(2000) not null,
	`Beschreibung`          text not null,
	`AngelegtVon`           int not null, /* FK zu Benutzer */
	`ErfassungsDatum`       date not null,
	`AnsprechPerson`        int, /* FK zu Benutzer */
	`Kunde`                 int not null, /* FK zu Kunden */
	`AnforderungsArt`       int not null, /* FK zu AnforderungsArten */
	`Prioritaet`            int not null,
	`Status`                int not null, /* FK zu Status */
	`ZugewiesenAn`          int not null, /* FK zu Benutzer */
	`Modul`                 int, /* FK zu Modul */
	`Version`               varchar(10),
	`HelpdeskNummer`        varchar(10),
	`AufwandGeschaetzt`     float not null,
	`FertigstellungGeplant` date not null,
	`FertigstellungIst`     date,
	`Auftragsnummer`        varchar(10),
	`SchluesselBegriffe`    varchar(32000)
);

-- Anhaenge
create table `Anhaenge` (
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

-- Beziehungstabelle "verwandteAnforderungen"
create table `VerwandteAnforderungen` (
	`MasterAnforderungId` int, /* FK zu Anforderungen */
	`AnforderungId`       int, /* FK zu Anforderungen */
	primary key (`MasterAnforderungId`,`AnforderungId`)
);

-- Beziehungstabelle "AnforderungAnhaenge"
create table `AnforderungAnhaenge` (
	`AnforderungId` int, /* FK zu Anforderungen */
	`AnhangId`      int, /* FK zu Anhaenge */
	primary key (`AnforderungId`,`AnhangId`)
);

-- Kommentar
create table `Kommentare` (
	`KommentarId`      int primary key auto_increment,
	`Anforderung`      int not null, /* FK zu Anforderungen */
	`BearbeitetVon`    date not null,
	`BearbeitetBis`    date not null,
	`KommentarText`    text not null,
	`ExternSichtbar`   boolean default false,
	`Benutzer`         int not null, /* FK zu Benutzer */
	`HinzugefuegtAm`   date not null
);

-- Beziehungstabelle "KommentarAnhaenge"
create table `KommentarAnhaenge` (
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
create table `Module` (
	`ModulId`       int primary key auto_increment,
	`Bezeichnung`   varchar(100) not null
);
