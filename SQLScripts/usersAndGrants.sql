create user wlasciciel identified by pitokWaldek;
/
grant create session to wlasciciel;
grant insert,update,delete,select on towary to wlasciciel;
grant insert,update,delete,select on pracownicy to wlasciciel;
grant insert,update,delete,select on klienci to wlasciciel;
grant insert,update,delete,select on sprzedaze_towary to wlasciciel;
grant insert,update,delete,select on sprzedaze to wlasciciel;
grant insert,update,select,delete on dane_logowania to wlasciciel;
grant select on bilans_finansowy to wlasciciel;
/


create user KSIEGOWY identified by ksiegowytest;
/
grant create session to ksiegowy;
grant insert,update,delete,select on koszty_jednorazowe to ksiegowy;
grant insert,update,delete,select on koszty_stale to ksiegowy;
grant select on bilans_finansowy to ksiegowy;
grant select on koszty to ksiegowy;
/

create user magazynier identified by magazyniertest;
/
grant create session to magazynier;
grant insert,update,select on TOWARY to magazynier;
/

create user sprzedawca identified by sprzedawcatest;
/
grant create session to sprzedawca;
grant insert,update,select on SPRZEDAZE to sprzedawca;
grant insert,update,select on KLIENCI to sprzedawca;
grant insert,update,select on SPRZEDAZE_TOWARY to sprzedawca;
/
