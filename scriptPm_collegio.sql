CREATE DATABASE PM_COLLEGIO;

use pm_collegio;
drop TABLE IF EXISTS Indirizzo;
drop TABLE IF EXISTS Mansione;

CREATE TABLE Indirizzo (
    indirizzo_id INT PRIMARY KEY AUTO_INCREMENT,
    paese VARCHAR(50) NOT NULL,
    provincia VARCHAR(50),
    citta VARCHAR(100) NOT NULL,
    via VARCHAR(100) NOT NULL,
    -- numero_civico VARCHAR(10),
    cap VARCHAR(10)
);

CREATE TABLE Mansione (
	role_id INT PRIMARY KEY AUTO_INCREMENT,
	role_type VARCHAR(10),
    role_nome VARCHAR(100) NOT NULL
   
    -- ('admin_sistema', 'admin_committente', 'receptionist', 'cliente')
);

-- Inserimento di Tuple
INSERT INTO Mansione VALUE(1,'AS','Admin_Sistema');
INSERT INTO Mansione VALUE(2,'AC','Admin_Committente');
INSERT INTO Mansione VALUE(3,'AR','Receptionist');
INSERT INTO Mansione VALUE(4,'U','Cliente');

-- Inserimento di Tuple Pavia
INSERT INTO Indirizzo VALUE(1,'ITALIA','PV','PAVIA','GOLGI1',27100);
INSERT INTO Indirizzo VALUE(2,'ITALIA','PV','PAVIA','GOLGI2',27100);
INSERT INTO Indirizzo VALUE(3,'ITALIA','PV','PAVIA','CARDANO',27100);
INSERT INTO Indirizzo VALUE(4,'ITALIA','PV','PAVIA','VOLTA',27100);
INSERT INTO Indirizzo VALUE(5,'ITALIA','PV','PAVIA','GHISLIERI',27100);
INSERT INTO Indirizzo VALUE(6,'ITALIA','PV','PAVIA','MAINO',27100);
INSERT INTO Indirizzo VALUE(7,'ITALIA','PV','PAVIA','CAMPUS',27100);
INSERT INTO Indirizzo VALUE(8,'ITALIA','PV','PAVIA','BORROMEO',27100);
INSERT INTO Indirizzo VALUE(9,'ITALIA','PV','PAVIA','CAIROLI',27100);
INSERT INTO Indirizzo VALUE(10,'ITALIA','PV','PAVIA','SPALLA',27100);
INSERT INTO Indirizzo VALUE(11,'ITALIA','PV','PAVIA','DON BOSCO',27100);
INSERT INTO Indirizzo VALUE(12,'ITALIA','PV','PAVIA','FRACCARO',27100);
-- Inserimento di Tuple MILANO
INSERT INTO Indirizzo VALUE(13,'ITALIA','MI','MILANO','SAN SIRO',20019);
INSERT INTO Indirizzo VALUE(14,'ITALIA','MI','MILANO','ROGEREDO',20019);
INSERT INTO Indirizzo VALUE(15,'ITALIA','MI','MILANO','MALPENXA',20019);
-- Inserimento di Tuple ROMA
INSERT INTO Indirizzo VALUE(16,'ITALIA','RM','ROMA','COLOSSEO',00042);
INSERT INTO Indirizzo VALUE(17,'ITALIA','RM','ROMA','TERMINE',00042);
-- Inserimento di Tuple GENOVA
INSERT INTO Indirizzo VALUE(18,'ITALIA','GE','GENOVA','GASTALDI',16100);
-- Inserimento di Tuple VENEZIA
INSERT INTO Indirizzo VALUE(19,'ITALIA','VE','VENIZIA','MESTRE',30100);

INSERT INTO Indirizzo VALUE(20,'GERMANY','EI','BERLINO','EI (DE)',0049);
INSERT INTO Indirizzo VALUE(21,'FRANCE','EI','PARIS','EI (FR)',0033);
INSERT INTO Indirizzo VALUE(22,'BELGIO','EI','BRUXELLES','EI (BE)',0032);
INSERT INTO Indirizzo VALUE(23,'SPAGNA','EI','MADRID','EI (ES)',0034);
INSERT INTO Indirizzo VALUE(24,'PORTOGALLO','EI','LISBON','EI (PRT)',00351);
INSERT INTO Indirizzo VALUE(25,'INGHILTERRA','EI','LONDON','EI (GB)',0044);

INSERT INTO Indirizzo VALUE(26,'STATI UNITI','EE','WASHINGTON','EE (USA)',001);
INSERT INTO Indirizzo VALUE(27,'CANADA','EE','OTTAWA','EE (CAN)',001);
INSERT INTO Indirizzo VALUE(28,'MEXIQUE','EE','MEXICO','EE (MEX)',0052);
INSERT INTO Indirizzo VALUE(29,'BRASIL','EE','RIO DE JANEIRO','EE (BR)',0055);
INSERT INTO Indirizzo VALUE(30,'ARGENTINA','EE','BUENOS AIRES','EE (ARG)',0054);

INSERT INTO Indirizzo VALUE(31,'CINA','EE','PEKIN','EE (CN)',0086);
INSERT INTO Indirizzo VALUE(32,'JAPON','EE','TOKYO','EE (JPN)',0081);
INSERT INTO Indirizzo VALUE(33,'COREA','EE','SEOUL','EE (KOR)',0082);
INSERT INTO Indirizzo VALUE(34,'INDIA','EE','DEHLI','EE (IND)',0091);

INSERT INTO Indirizzo VALUE(35,'MAROC','EE','RABAT','EE (MA)',00212);
INSERT INTO Indirizzo VALUE(36,'CAMEROUN','EE','YAOUNDE','EE (CMR)',00237);
INSERT INTO Indirizzo VALUE(37,'SENEGAL','EE','DAKAR','EE (SEN)',00221);
INSERT INTO Indirizzo VALUE(38,'KENYA','EE','NAIROBI','EE (KEN)',00254);
INSERT INTO Indirizzo VALUE(39,'SUD AFRICA','EE','PRETORIA','EE (ZA)',0027);


CREATE TABLE Committente (
    codCommittente INT PRIMARY KEY AUTO_INCREMENT,
    ragione_Sociale VARCHAR(100) NOT NULL,
    gestore VARCHAR(100) ,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    indirizzo_id INT,
    FOREIGN KEY (indirizzo_id) REFERENCES Indirizzo(indirizzo_id) ON DELETE SET NULL
    -- FOREIGN KEY (gestore) REFERENCES User(user_counter) ON DELETE SET NULL
);

-- Inserimento di Tuple  (da modificare)
INSERT INTO Committente VALUE(1,'COLLEGIO GOLGI','XXXADMINCOM1','golgi@mail.it','3273665761',2);
INSERT INTO Committente VALUE(2,'COLLEGIO CARDANO','XXXADMINCOM2','cardano@mail.it','3273665762',3);
INSERT INTO Committente VALUE(3,'COLLEGIO VOLTA','XXXADMINCOM3','volta@mail.it','3273665763',4);
INSERT INTO Committente VALUE(4,'COLLEGIO GHISLIERI','XXXADMINCOM4','ghislieri@mail.it','3273665764',5);
INSERT INTO Committente VALUE(5,'COLLEGIO MAINO','XXXADMINCOM5','maino@mail.it','3273665765',6);
INSERT INTO Committente VALUE(6,'COLLEGIO CAMPUS','XXXADMINCOM6','campus@mail.it','3273665766',7);
INSERT INTO Committente VALUE(7,'COLLEGIO BORROMEO','XXXADMINCOM7','borromeo@mail.it','3273665767',8);
INSERT INTO Committente VALUE(8,'COLLEGIO CAIROLI','XXXADMINCOM8','cairoli@mail.it','3273665768',9);
INSERT INTO Committente VALUE(9,'COLLEGIO SPALLA','XXXADMINCOM9','spalla@mail.it','3273665769',10);
INSERT INTO Committente VALUE(10,'COLLEGIO DON BOSCO','XXXADMINCOM10','donbosco@mail.it','3273665760',11);
INSERT INTO Committente VALUE(11,'COLLEGIO FRACCARO','XXXADMINCOM11','fraccaro@mail.it','3273665752',12);

drop TABLE IF EXISTS User;

CREATE TABLE User (
    user_counter VARCHAR(100) PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    access VARCHAR(255) NOT NULL,
    ruolo iNT,
    committente_id INT NULL, -- Solo per utenti legati a una struttura
    stato ENUM('attivo', 'disattivato','attesa') DEFAULT 'attesa',
    telefono VARCHAR(20),
    indirizzo_id INT NULL,
    -- via  VARCHAR(100),
    recupero  VARCHAR(100),
    response  VARCHAR(100),
    genere ENUM('Maschio', 'Femmina','Altro') DEFAULT 'Altro',
    
    FOREIGN KEY (indirizzo_id) REFERENCES Indirizzo(indirizzo_id) ON DELETE SET NULL,
    FOREIGN KEY (committente_id) REFERENCES Committente(codCommittente) ON DELETE SET NULL,
    FOREIGN KEY (ruolo) REFERENCES Mansione(role_id) ON DELETE SET NULL
    );
    
INSERT INTO User VALUE('XXXADMIN','Admin','Sistema','admin@mail.it','Admin@1',1,1,'attivo','3273665761',14,'giocatore preferito?', 'CRonaldo','Maschio');
INSERT INTO User VALUE('XXXADMINCOM1','Admin','Committente1','adminC1@mail.it','AdminC1@1',2,1,'attivo','3273665760',13,'giocatore preferito?', 'Modric','Maschio');
INSERT INTO User VALUE('XXXADMINCOM2','Admin','Committente2','adminC2@mail.it','AdminC2@2',2,2,'attivo','3273665763',1,'giocatore preferito?', 'Neymar','Maschio');
INSERT INTO User VALUE('XXXADMINCOM3','Admin','Committente3','adminC3@mail.it','AdminC3@3',2,3,'attivo','3273665764',13,'giocatore preferito?', 'Modric','Maschio');
INSERT INTO User VALUE('XXXADMINCOM4','Admin','Committente4','adminC4@mail.it','AdminC4@4',2,4,'attivo','3273665765',1,'giocatore preferito?', 'Neymar','Maschio');
INSERT INTO User VALUE('XXXADMINCOM5','Admin','Committente5','adminC5@mail.it','AdminC5@5',2,5,'attivo','3273665766',13,'giocatore preferito?', 'Modric','Maschio');
INSERT INTO User VALUE('XXXADMINCOM6','Admin','Committente6','adminC6@mail.it','AdminC6@6',2,6,'attivo','3273665767',1,'giocatore preferito?', 'Neymar','Maschio');
INSERT INTO User VALUE('XXXADMINCOM7','Admin','Committente7','adminC7@mail.it','AdminC7@7',2,7,'attivo','3273665768',13,'giocatore preferito?', 'Modric','Maschio');
INSERT INTO User VALUE('XXXADMINCOM8','Admin','Committente8','adminC8@mail.it','AdminC8@8',2,8,'attivo','3273665768',1,'giocatore preferito?', 'Neymar','Maschio');
INSERT INTO User VALUE('XXXADMINCOM9','Admin','Committente9','adminC9@mail.it','AdminC9@9',2,9,'attivo','3273665750',13,'giocatore preferito?', 'Modric','Maschio');
INSERT INTO User VALUE('XXXADMINCOM10','Admin','Committente10','adminC10@mail.it','AdminC10@10',2,10,'attivo','3273665752',1,'giocatore preferito?', 'Neymar','Maschio');
INSERT INTO User VALUE('XXXADMINCOM11','Admin','Committente11','adminC11@mail.it','AdminC11@11',2,11,'attivo','3273665753',13,'giocatore preferito?', 'Modric','Maschio');
INSERT INTO User VALUE('XXXRECEP1','Sofia','Rossi','sofia@mail.it','Sofia@1',3,1,'attivo','3273665711',19,'disciplina preferita?', 'Corsa','Femmina');
INSERT INTO User VALUE('XXXPROVA','Prova','Test','prova@mail.it','Prova@1',4,1,'attivo','3273665721',18,'Cantante preferito?', 'Pausini','Femmina');

-- per integrita referenziale creo uno user poi aggiungo il vincolo alla tabella committente 
ALTER TABLE Committente ADD FOREIGN KEY (gestore) REFERENCES User(user_counter) ON DELETE SET NULL;

drop TABLE IF EXISTS Room;

CREATE TABLE Room (
    room_id INT PRIMARY KEY AUTO_INCREMENT,
    committente_id INT NOT NULL,
    numero_stanza VARCHAR(10) NOT NULL,
    tipo ENUM('singola', 'doppia', 'suite') NOT NULL,
    prezzo DECIMAL(10,2) NOT NULL,
    letto_tipo ENUM('matrimoniale', 'singolo', 'king-size') NOT NULL,
    stato ENUM('disponibile', 'occupata', 'manutenzione') NOT NULL DEFAULT 'disponibile',
    FOREIGN KEY (committente_id) REFERENCES Committente(codCommittente) ON DELETE CASCADE
    
);

drop TABLE IF EXISTS Reservation;
drop TABLE IF EXISTS Fattura;
drop TABLE IF EXISTS MetodoPagamento;
drop TABLE IF EXISTS Pagamento;
drop TABLE IF EXISTS AccessLog;
drop TABLE IF EXISTS StoricoPrenotazioni;

CREATE TABLE Reservation (
    reservation_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(100) NOT NULL,
    committente_id INT NOT NULL,
    room_id INT NOT NULL,
    check_in DATE NOT NULL,
    check_out DATE  NULL,
	`status` ENUM('attiva', 'cancellata', 'completata') DEFAULT 'attiva', -- `status`
    note TEXT,
    giorni INT DEFAULT 0 ,
   
    
    FOREIGN KEY (user_id) REFERENCES User(user_counter) ON DELETE CASCADE,
    FOREIGN KEY (committente_id) REFERENCES Committente(CodCommittente) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES Room(room_id) ON DELETE CASCADE
    
);

CREATE TABLE Fattura (
    fattura_id INT PRIMARY KEY AUTO_INCREMENT,
    reservation_id INT NOT NULL,
    importo DECIMAL(10,2) NOT NULL,
    data_emissione DATE NOT NULL,
    stato ENUM('pagato', 'non pagato', 'in attesa') DEFAULT 'non pagato',
    FOREIGN KEY (reservation_id) REFERENCES Reservation(reservation_id) ON DELETE CASCADE
    
);


CREATE TABLE MetodoPagamento (
    metodo_id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL UNIQUE,
    descrizione TEXT
    
);


CREATE TABLE Pagamento (
    pagamento_id INT PRIMARY KEY AUTO_INCREMENT,
    fattura_id INT NOT NULL,
    metodo_id INT NOT NULL,
    importo DECIMAL(10,2) NOT NULL,
    data_pagamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fattura_id) REFERENCES Fattura(fattura_id) ON DELETE CASCADE,
    FOREIGN KEY (metodo_id) REFERENCES MetodoPagamento(metodo_id) ON DELETE CASCADE
    
);


CREATE TABLE AccessLog (
    log_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(100) NOT NULL,
    ip_address VARCHAR(45) DEFAULT NULL,
    note TEXT,
	login_time datetime DEFAULT CURRENT_TIMESTAMP,
    logout_time datetime DEFAULT NULL,
    FOREIGN KEY (user_id) REFERENCES User(user_counter) ON DELETE CASCADE
    
);


CREATE TABLE StoricoPrenotazioni (
    storico_id INT PRIMARY KEY AUTO_INCREMENT,
    reservation_id INT NOT NULL,
    user_id VARCHAR(100) NOT NULL,
    check_in_precedente DATE,
    check_out_precedente DATE,
    nuovo_check_in DATE NOT NULL,
    nuovo_check_out DATE NOT NULL,
    data_modifica TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (reservation_id) REFERENCES Reservation(reservation_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES User(user_counter) ON DELETE CASCADE
    
);

DROP TABLE IF EXISTS `sessions`;


CREATE TABLE sessions (
  session_id INT PRIMARY KEY AUTO_INCREMENT,
  user_id varchar(100) NOT NULL,
  committente_id int NOT NULL,
  role_id int NOT NULL,
  token char(64) NOT NULL UNIQUE,
  expires datetime NOT NULL,
  created_at datetime DEFAULT CURRENT_TIMESTAMP,
  KEY `expires` (`expires`), -- indice sul campo expires
  FOREIGN KEY (user_id) REFERENCES User (user_counter) ON DELETE CASCADE,
  FOREIGN KEY (committente_id) REFERENCES Committente (codCommittente),
  FOREIGN KEY (role_id) REFERENCES Mansione (role_id)
);

