-- Inserimento di 3 account admin
INSERT INTO Utente (nome, cognome, ruolo, username, password, stato, email, telefono, dataDiNascita, luogoDiNascita)
VALUES 
('Daniel', 'Battaglia', 'admin', 'daniel.battaglia', SHA1('d'), 'offline', 'daniel.battaglia@example.com', '1234567890', '1990-01-01', 'Napoli'),
('Ruben', 'Gigante', 'admin', 'ruben.gigante', SHA1('r'), 'offline', 'ruben.gigante@example.com', '0987654321', '1988-05-15', 'Caserta'),
('Francesco', 'Vaiano', 'admin', 'francesco.vaiano', SHA1('f'), 'offline', 'francesco.vaiano@example.com', '1122334455', '1992-09-21', 'Bari');

-- Inserimento di 2 magazzinieri
INSERT INTO Utente (nome, cognome, ruolo, username, password, stato, email, telefono, dataDiNascita, luogoDiNascita)
VALUES 
('Antonio', 'Prete', 'magazziniere', 'antonio.prete', SHA1('d'), 'offline', 'antonio.prete@example.com', '3344556677', '1995-03-10', 'Roma'),
('Giuseppe', 'Rossi', 'magazziniere', 'giuseppe.rossi', SHA1('d'), 'offline', 'giuseppe.rossi@example.com', '4455667788', '1987-11-30', 'Milano');

-- Inserimento di 3 categorie
INSERT INTO Categoria (nome, descrizione, note)
VALUES 
('Elettronica', 'Prodotti elettronici di consumo', 'Include telefoni, computer e accessori.'),
('Arredamento', 'Articoli per la casa e arredamento', 'Include mobili e complementi d’arredo.'),
('Abbigliamento', 'Vestiti e accessori per uomo, donna e bambino', 'Include calzature e accessori moda.');

-- Inserimento di 10 prodotti associati alle categorie
INSERT INTO Prodotto (IDcategoria, codice, stato, nome, descrizione, dataArrivo, noteArrivo, partenza, dataSpedizione, noteSpedizione, destinazione, noteGenerali)
VALUES
(1, 'ELEC001', 'in arrivo', 'Smartphone XYZ', 'Smartphone di ultima generazione', '2024-12-01', 'Arrivo previsto alle 10:00', '2024-12-05', NULL, NULL, 'Magazzino centrale', 'Nessuna nota.'),
(1, 'ELEC002', 'in magazzino', 'Laptop ABC', 'Laptop con prestazioni elevate', '2024-11-25', 'Consegna puntuale', '2024-12-07', '2024-12-08', 'Consegna prevista entro 24 ore', 'Cliente finale Milano', 'Confezione protettiva inclusa.'),
(1, 'ELEC003', 'in spedizione', 'Tablet 123', 'Tablet leggero e portatile', '2024-11-28', 'Consegna anticipata', '2024-12-01', '2024-12-02', 'In attesa del corriere', 'Negozio Roma', 'Includere caricatore.'),
(2, 'ARR001', 'non disponibile', 'Sedia Ergonomica', 'Sedia comoda per ufficio', '2024-11-15', 'Richiesto riordino', '2024-12-10', NULL, NULL, NULL, 'Prodotto in attesa di restock.'),
(2, 'ARR002', 'in arrivo', 'Tavolo in legno', 'Tavolo da pranzo in legno massello', '2024-12-03', 'Arrivo programmato con trasportatore', '2024-12-15', NULL, NULL, 'Magazzino Torino', 'Imballaggio speciale richiesto.'),
(2, 'ARR003', 'in magazzino', 'Libreria moderna', 'Libreria con design contemporaneo', '2024-11-20', 'Arrivo confermato', '2024-12-04', '2024-12-06', NULL, 'Cliente Napoli', 'Assemblaggio richiesto.'),
(3, 'ABB001', 'in spedizione', 'Giacca Invernale', 'Giacca impermeabile per uomo', '2024-11-30', NULL, '2024-12-01', '2024-12-02', 'In transito', 'Cliente Torino', 'Assicurarsi di includere tutte le taglie richieste.'),
(3, 'ABB002', 'in magazzino', 'Scarpe Running', 'Scarpe sportive leggere', '2024-11-25', 'Consegna diretta', '2024-12-03', NULL, NULL, 'Cliente Firenze', 'Confezionate singolarmente.'),
(3, 'ABB003', 'in arrivo', 'Cappello Estivo', 'Cappello leggero per protezione dal sole', '2024-12-02', 'Trasporto via aerea', '2024-12-10', NULL, NULL, 'Magazzino Bologna', 'Materiale fragile.'),
(3, 'ABB004', 'non disponibile', 'Zaino Casual', 'Zaino per uso quotidiano', '2024-11-15', 'Prodotto non più disponibile', '2024-12-01', NULL, NULL, NULL, 'Sostituzione in corso.');


-- Inserimento delle 10 notifiche
INSERT INTO Notifica (IDutente, oggetto, messaggio)
VALUES
(1, 'Nuova disponibilità prodotto', 'Il prodotto Smartphone XYZ è arrivato in magazzino e pronto per la spedizione.'),
(1, 'Aggiornamento ordine', 'Il Laptop ABC è stato spedito.'),
(1, 'Offerta speciale', 'Sconto del 20% su tutti i prodotti elettronici.'),
(1, 'Disponibilità limitata', 'La Sedia Ergonomica è in attesa di restock, ordina subito per garantirla.'),
(2, 'Nuovo arrivo', 'Il Tavolo in legno è arrivato e pronto per essere posizionato in magazzino.'),
(2, 'Conferma spedizione', 'La Libreria moderna è stata spedita al cliente di Napoli.'),
(2, 'Ritorno prodotto', 'Il prodotto Giacca Invernale è stato restituito dal cliente.'),
(2, 'Problema con spedizione', 'Il Zaino Casual è fuori stock e la sostituzione è in corso.'),
(2, 'Aggiornamento inventario', 'Le Scarpe Running sono ora in magazzino e pronte per la vendita.'),
(2, 'Ritardo spedizione', 'Il Cappello Estivo ha subito un ritardo nella spedizione, ci scusiamo per l’inconveniente.');



-- Inserimento manuale dello stato delle notifiche per tutti gli utenti (tranne colui che ha inviato la notifica)
-- Notifica 1 (Invio da Admin1, IDutente = 1)
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (1, 2, 'non letto'); -- Admin2
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (1, 3, 'non letto'); -- Magazziniere1
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (1, 4, 'non letto'); -- Magazziniere2

-- Notifica 2 (Invio da Admin1, IDutente = 1)
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (2, 2, 'non letto'); -- Admin2
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (2, 3, 'non letto'); -- Magazziniere1
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (2, 4, 'non letto'); -- Magazziniere2

-- Notifica 3 (Invio da Admin1, IDutente = 1)
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (3, 2, 'non letto'); -- Admin2
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (3, 3, 'non letto'); -- Magazziniere1
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (3, 4, 'non letto'); -- Magazziniere2

-- Notifica 4 (Invio da Admin1, IDutente = 1)
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (4, 2, 'non letto'); -- Admin2
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (4, 3, 'non letto'); -- Magazziniere1
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (4, 4, 'non letto'); -- Magazziniere2

-- Notifica 5 (Invio da Admin2, IDutente = 2)
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (5, 1, 'non letto'); -- Admin1
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (5, 3, 'non letto'); -- Magazziniere1
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (5, 4, 'non letto'); -- Magazziniere2

-- Notifica 6 (Invio da Admin2, IDutente = 2)
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (6, 1, 'non letto'); -- Admin1
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (6, 3, 'non letto'); -- Magazziniere1
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (6, 4, 'non letto'); -- Magazziniere2

-- Notifica 7 (Invio da Admin2, IDutente = 2)
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (7, 1, 'non letto'); -- Admin1
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (7, 3, 'non letto'); -- Magazziniere1
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (7, 4, 'non letto'); -- Magazziniere2

-- Notifica 8 (Invio da Admin2, IDutente = 2)
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (8, 1, 'non letto'); -- Admin1
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (8, 3, 'non letto'); -- Magazziniere1
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (8, 4, 'non letto'); -- Magazziniere2

-- Notifica 9 (Invio da Admin2, IDutente = 2)
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (9, 1, 'non letto'); -- Admin1
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (9, 3, 'non letto'); -- Magazziniere1
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (9, 4, 'non letto'); -- Magazziniere2

-- Notifica 10 (Invio da Admin2, IDutente = 2)
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (10, 1, 'non letto'); -- Admin1
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (10, 3, 'non letto'); -- Magazziniere1
INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (10, 4, 'non letto'); -- Magazziniere2

-- Creazione manuale dei record per la tabella Arrivo
INSERT INTO Arrivo (IDprodotto, note)
VALUES
(1, 'Arrivo previsto alle 10:00'), -- Smartphone XYZ
(5, 'Arrivo programmato con trasportatore'), -- Tavolo in legno
(9, 'Trasporto via aerea'); -- Cappello Estivo

-- Creazione manuale dei record per la tabella Spedizione
INSERT INTO Spedizione (IDprodotto, note)
VALUES
(3, 'In attesa del corriere'), -- Tablet 123
(7, 'In transito'); -- Giacca Invernale