CREATE SEQUENCE seq_Pracownicy;
CREATE SEQUENCE seq_Towary;
CREATE SEQUENCE seq_Sprzedaze;
CREATE SEQUENCE seq_Klienci;
CREATE SEQUENCE seq_Bilans_finansowy;
CREATE SEQUENCE seq_Koszty_stale;
CREATE SEQUENCE seq_Koszty_jednorazowe;
CREATE SEQUENCE seq_Koszty;

CREATE TABLE Pracownicy (ID number(2) NOT NULL,
 Imie varchar2(30) NOT NULL,
 Nazwisko varchar2(30) NOT NULL,
 Stanowisko varchar2(20) NOT NULL,
 Data_zatrudnienia date NOT NULL,
 Wynagrodzenie number(10) NOT NULL,
 PRIMARY KEY (ID));
 
CREATE TABLE Towary (ID number(10) NOT NULL,
 Nazwa varchar2(40) NOT NULL,
 Liczba number(10) NOT NULL,
 Cena number(5) NOT NULL,
 Data_dodania date NOT NULL,
 KODKRESKOWY char(13),
 JEDNOSTKASPRZEDAZY varchar2(20) NOT NULL,
 PRIMARY KEY (ID));
 
CREATE TABLE Sprzedaze (ID number(10) NOT NULL,
 KLIENCIID number(10) NOT NULL,
 Data_sprzedazy date NOT NULL,
 PRACOWNICYID number(2) NOT NULL,
 "Bilans_finansowy ID" number(10) NOT NULL,
 PRIMARY KEY (ID));
 
CREATE TABLE Klienci (ID number(10) NOT NULL,
 Imie varchar2(30) NOT NULL,
 Nazwisko varchar2(30) NOT NULL, PRIMARY KEY (ID));
 
CREATE TABLE Bilans_finansowy (ID number(10) NOT NULL,
 Suma_kosztów number(10) NOT NULL,
 Suma_przychodów number(10) NOT NULL,
 Saldo number(10) NOT NULL,
 Data date NOT NULL,
 PRIMARY KEY (ID));
 
CREATE TABLE Koszty_stale (ID number(10) NOT NULL,
 Kategoria varchar2(30) NOT NULL,
 Wysokosc_kosztu number(5) NOT NULL,
 PRIMARY KEY (ID));
 
CREATE TABLE Koszty_jednorazowe (ID number(10) NOT NULL,
 Nazwa varchar2(30) NOT NULL, Liczba number(5),
 Data_zlecenia date NOT NULL, Nazwa_towaru varchar2(40),
 Cena number(5) NOT NULL, PRIMARY KEY (ID));
 
CREATE TABLE Sprzedaze_Towary ("Sprzedaze ID" number(10) NOT NULL,
 "Towary ID" number(10) NOT NULL,
 Ilosc number(8) NOT NULL,
 Calkowity_kosz number(8) NOT NULL,
 PRIMARY KEY ("Sprzedaze ID", "Towary ID"));
 
CREATE TABLE Dane_logowania (PRACOWNICYID number(2) NOT NULL,
 Login varchar2(255) NOT NULL UNIQUE,
 Haslo varchar2(30) NOT NULL);
 
CREATE TABLE Koszty(ID number(10) NOT NULL,
 "Koszty_stale ID" number(10),
 "Koszty_jednorazowe ID" number(10),
 Data date NOT NULL,
 "Bilans_finansowy ID" number(10) NOT NULL,
 PRIMARY KEY (ID));
 
ALTER TABLE Sprzedaze_Towary ADD FOREIGN KEY ("Sprzedaze ID") REFERENCES Sprzedaze(ID);
ALTER TABLE Sprzedaze_Towary ADD FOREIGN KEY ("Towary ID") REFERENCES Towary(ID);
ALTER TABLE Dane_logowania ADD FOREIGN KEY (PRACOWNICYID) REFERENCES Pracownicy(ID);
ALTER TABLE Sprzedaze ADD  FOREIGN KEY (PRACOWNICYID) REFERENCES Pracownicy(ID);
ALTER TABLE Sprzedaze ADD FOREIGN KEY ("Bilans_finansowy ID") REFERENCES Bilans_finansowy(ID);
ALTER TABLE Koszty ADD FOREIGN KEY ("Koszty_stale ID") REFERENCES Koszty_stale(ID);
ALTER TABLE Koszty ADD FOREIGN KEY ("Koszty_jednorazowe ID") REFERENCES Koszty_jednorazowe(ID);
ALTER TABLE Koszty ADD FOREIGN KEY ("Bilans_finansowy ID") REFERENCES Bilans_finansowy(ID);
