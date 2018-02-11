create or replace TRIGGER pracownicy_insert 
BEFORE INSERT ON PRACOWNICY 
FOR EACH ROW

BEGIN
  SELECT seq_PRACOWNICY.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
/
------------------

create or replace TRIGGER bilans_finansowy_insert
BEFORE INSERT ON Bilans_finansowy
FOR EACH ROW

BEGIN
    SELECT SEQ_BILANS_FINANSOWY.NEXTVAL
    INTO :new.id
    FROM dual;
END;
/
------------------

create or replace TRIGGER klienci_insert
BEFORE INSERT ON Klienci
FOR EACH ROW

BEGIN
    SELECT SEQ_KLIENCI.NEXTVAL
    INTO :new.id
    FROM dual;
END;
/
---------------------

create or replace TRIGGER koszty_insert
BEFORE INSERT ON Koszty
FOR EACH ROW

BEGIN
    SELECT SEQ_KOSZTY.NEXTVAL
    INTO :new.id
    FROM dual;
END;
/
--------------------

create or replace TRIGGER koszty_jednorazowe_insert
AFTER INSERT ON koszty_jednorazowe
FOR EACH ROW
Declare
currentBilansID number;
BEGIN
 SELECT ID INTO currentBilansID FROM BILANS_FINANSOWY WHERE TO_CHAR(DATA , 'MMYYYY') = TO_CHAR(SYSDATE, 'MMYYYY');
 
 UPDATE BILANS_FINANSOWY
 SET SUMA_KOSZTÓW = SUMA_KOSZTÓW + :new.cena
 WHERE ID = currentBilansID;
 
 UPDATE BILANS_FINANSOWY
 SET SALDO = SALDO - :new.cena
 WHERE ID = currentBilansID;

 INSERT INTO KOSZTY("Koszty_jednorazowe ID",DATA,"Bilans_finansowy ID")
 VALUES(:new.ID, SYSDATE, currentBilansID);

END;
/
---------------


create or replace TRIGGER koszty_jednorazowe_insertROW
BEFORE INSERT ON KOSZTY_JEDNORAZOWE
FOR EACH ROW

BEGIN
    SELECT SEQ_KOSZTY_JEDNORAZOWE.NEXTVAL
    INTO :new.id
    FROM dual;
END;
/
---------------


create or replace TRIGGER koszty_stale_insert
BEFORE INSERT ON Koszty_stale
FOR EACH ROW

BEGIN
    SELECT SEQ_KOSZTY_STALE.NEXTVAL
    INTO :new.id
    FROM dual;
END;
/

------------------

create or replace TRIGGER sprzedaze_insert
BEFORE INSERT ON Sprzedaze
FOR EACH ROW

BEGIN
    SELECT SYSDATE
    INTO :new.DATA_SPRZEDAZY
    FROM DUAL;

    SELECT ID  INTO :new."Bilans_finansowy ID"  
    FROM BILANS_FINANSOWY 
    WHERE TO_CHAR(DATA , 'MMYYYY') = TO_CHAR(SYSDATE, 'MMYYYY');

    
    
    SELECT SEQ_SPRZEDAZE.NEXTVAL
    INTO :new.id
    FROM dual;
    
END;
/

-----------------------

create or replace TRIGGER towary_insert
BEFORE INSERT ON Towary
FOR EACH ROW

BEGIN
    SELECT SEQ_TOWARY.NEXTVAL
    INTO :new.id
    FROM dual;
END;
/
----------------------
create or replace TRIGGER ksiegowy_logon_schema_chaner
AFTER LOGON ON KSIEGOWY.SCHEMA

BEGIN
execute immediate 'alter session set current_schema = SKLEP';
END;
/
----------------------

create or replace TRIGGER magazynier_logon_schema_chaner
AFTER LOGON ON magazynier.SCHEMA

BEGIN
execute immediate 'alter session set current_schema = SKLEP';
END;
/
----------------------

create or replace TRIGGER sprzedawca_logon_schema_chaner
AFTER LOGON ON sprzedawca.SCHEMA

BEGIN
execute immediate 'alter session set current_schema = SKLEP';
END;
/
----------------------


create or replace TRIGGER sprzedaze_towary_insert 
AFTER INSERT ON SPRZEDAZE_TOWARY
FOR EACH ROW

Declare
currentBilansID number;
BEGIN
 SELECT ID INTO currentBilansID FROM BILANS_FINANSOWY WHERE TO_CHAR(DATA , 'MMYYYY') = TO_CHAR(SYSDATE, 'MMYYYY');
 
 UPDATE BILANS_FINANSOWY
 SET "SUMA_PRZYCHODÓW" = SUMA_PRZYCHODÓW + :new.Calkowity_kosz
 WHERE ID = currentBilansID;
 
 UPDATE BILANS_FINANSOWY
 SET SALDO = SALDO + :new.Calkowity_kosz
 WHERE ID = currentBilansID;


 UPDATE TOWARY
 SET LICZBA = LICZBA - :new.ILOSC
 WHERE ID = :new."Towary ID";
END;
/
----------------------

create or replace TRIGGER wlasciciel_logon_schema_chaner
AFTER LOGON ON WLASCICIEL.SCHEMA

BEGIN
execute immediate 'alter session set current_schema = SKLEP';
END;
/


----------------------