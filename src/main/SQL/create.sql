DROP DATABASE IF EXISTS Magazon;
CREATE DATABASE Magazon;
USE Magazon;

-- Creazione della tabella Categoria
CREATE TABLE Categoria (
    ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nome VARCHAR(255) NOT NULL,
    descrizione TEXT NOT NULL,
    note TEXT DEFAULT NULL
);

-- Creazione della tabella Prodotto
CREATE TABLE Prodotto (
    ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    IDcategoria INT NOT NULL,
    codice VARCHAR(255) NOT NULL UNIQUE,
    stato ENUM('in arrivo', 'in magazzino', 'in spedizione', 'non disponibile') NOT NULL,
    nome VARCHAR(255) NOT NULL,
    descrizione TEXT NOT NULL,
    dataArrivo DATE NOT NULL,
    noteArrivo TEXT DEFAULT NULL,
    partenza VARCHAR(255) NOT NULL,
    dataSpedizione DATE DEFAULT NULL,
    noteSpedizione TEXT DEFAULT NULL,
    destinazione VARCHAR(255) DEFAULT NULL,
    noteGenerali TEXT DEFAULT NULL,
    FOREIGN KEY (IDcategoria) REFERENCES Categoria(ID) ON DELETE CASCADE
);

-- Creazione della tabella Arrivo
CREATE TABLE Arrivo (
    ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    IDprodotto INT NOT NULL,
    note TEXT DEFAULT NULL,
    FOREIGN KEY (IDprodotto) REFERENCES Prodotto(ID) ON DELETE CASCADE
);

-- Creazione della tabella Spedizione
CREATE TABLE Spedizione (
    ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    IDprodotto INT NOT NULL,
    note TEXT DEFAULT NULL,
    FOREIGN KEY (IDprodotto) REFERENCES Prodotto(ID) ON DELETE CASCADE
);

-- Creazione della tabella Utente
CREATE TABLE Utente (
    ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nome VARCHAR(255) NOT NULL,
    cognome VARCHAR(255) NOT NULL,
    ruolo ENUM('magazziniere', 'admin') NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL, -- Verrà crittografata
    stato ENUM('online', 'offline') DEFAULT 'offline',
    email VARCHAR(255) NOT NULL UNIQUE,
    telefono VARCHAR(20) NOT NULL,
    dataDiNascita DATE NOT NULL,
    luogoDiNascita VARCHAR(255) NOT NULL
);

-- Creazione della tabella Notifica
CREATE TABLE Notifica (
    ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    IDutente INT NOT NULL,
    oggetto VARCHAR(255) NOT NULL,
    messaggio TEXT NOT NULL,
    dataDiInvio DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (IDutente) REFERENCES Utente(ID) ON DELETE CASCADE
);

-- Creazione della tabella StatoNotifica
CREATE TABLE StatoNotifica (
    ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    IDnotifica INT NOT NULL,
    IDutente INT NOT NULL,
    stato ENUM("letto", "non letto") DEFAULT "non letto",
    FOREIGN KEY (IDnotifica) REFERENCES Notifica(ID) ON DELETE CASCADE,
    FOREIGN KEY (IDutente) REFERENCES Utente(ID) ON DELETE CASCADE
);

-- Creazione della tabella Lista
CREATE TABLE Lista (
    ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nomeFile VARCHAR(255) NOT NULL,
    note TEXT DEFAULT NULL,
    dataInvio DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);