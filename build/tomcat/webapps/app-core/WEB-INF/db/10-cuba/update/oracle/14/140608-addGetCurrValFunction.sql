--$Id: 140608-addGetCurrValFunction.sql 17384 2014-06-08 08:46:36Z saiyan $
--Description: create procedure to select current value of a sequence

create or replace function GET_SEQ_VAL(seqname varchar2) return NUMBER
as
    ln number;
    ib number;
begin
    select LAST_NUMBER, INCREMENT_BY
    into ln, ib
    from USER_SEQUENCES
    where SEQUENCE_NAME = upper(seqname);

    return ln - ib;
end;
^