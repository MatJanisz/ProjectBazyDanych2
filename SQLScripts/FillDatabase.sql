INSERT INTO PRACOWNICY
VALUES(0,'Waldemar','Dmyszewicz','wlasciciel',SYSDATE,6000);

INSERT INTO PRACOWNICY
VALUES(0,'Leokadia','Liczydlo','ksiegowy',SYSDATE,2500);

INSERT INTO PRACOWNICY
VALUES(0,'Amadeusz','Piekny','sprzedawca',SYSDATE,2137);

INSERT INTO PRACOWNICY
VALUES(0,'Sebastian','Wiesolowski','magazynier',SYSDATE,1300);

INSERT INTO DANE_LOGOWANIA
VALUES(1,'wlasciciel','wlasciciel');

INSERT INTO DANE_LOGOWANIA
VALUES(2,'ksiegowy','ksiegowy');

INSERT INTO DANE_LOGOWANIA
VALUES(3,'sprzedawca','sprzedawca');

INSERT INTO DANE_LOGOWANIA
VALUES(4,'magazynier','magazynier');

INSERT INTO KOSZTY_STALE
VALUES(0,'Pensje pracownikow',5937);

INSERT INTO KOSZTY_STALE
VALUES(0,'Pensja wlasciciela',6000);

INSERT INTO TOWARY
VALUES(0,'PUSTAK',10000,3,SYSDATE,NULL,'Sztuka');

INSERT INTO TOWARY
VALUES(0,'DACHOWKA',1000,200,SYSDATE,NULL,'m^2');

INSERT INTO TOWARY
VALUES(0,'FARBA BIALA DELUX',100,50,SYSDATE,NULL,'Puszka 5L');