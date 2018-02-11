create or replace procedure balans_finansowy_row_inserter
is

begin
INSERT INTO BILANS_FINANSOWY(SUMA_KOSZTÓW,SUMA_PRZYCHODÓW,SALDO,DATA)
VALUES(0,0,0,SYSDATE);
end;
/

create or replace procedure copy_koszty_stale  is

currentBilansID number;
begin
SELECT ID INTO currentBilansID FROM BILANS_FINANSOWY WHERE TO_CHAR(DATA , 'MMYYYY') = TO_CHAR(SYSDATE, 'MMYYYY');
for eRow in (select ID,WYSOKOSC_KOSZTU from koszty_stale)
loop


 UPDATE BILANS_FINANSOWY
 SET SUMA_KOSZTÓW = SUMA_KOSZTÓW + erow.WYSOKOSC_KOSZTU
 WHERE ID = currentBilansID;
 
 UPDATE BILANS_FINANSOWY
 SET SALDO = SALDO - erow.WYSOKOSC_KOSZTU
 WHERE ID = currentBilansID;
 
INSERT INTO KOSZTY("Koszty_stale ID",DATA,"Bilans_finansowy ID")
VALUES(erow.ID,SYSDATE,SEQ_BILANS_FINANSOWY.CURRVAL);

end loop;
end;
/