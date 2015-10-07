-- $Id: 150310-alterFilterXml.sql 20260 2015-03-10 09:24:04Z krivopustov $
-- Change SEC_FILTER.XML to Lob

alter table SEC_FILTER alter XML type text;
