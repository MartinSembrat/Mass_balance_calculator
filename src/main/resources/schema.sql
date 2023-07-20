CREATE TABLE IF NOT EXISTS SALES (
id INT AUTO_INCREMENT PRIMARY KEY,
nr_Mag INT,
idPlatnika INT,
platnik_Nazwa VARCHAR(400),
idOdbiorcy INT,
odbiorca_Nazwa VARCHAR(400),
dataWystawienia DATE,
wk INT,
indeks VARCHAR(50) NOT NULL,
nazwa VARCHAR(400),
jm VARCHAR(30),
ilosc bigint NOT NULL,
iloscKG BIGINT,
wartoscNetto DECIMAL
);
