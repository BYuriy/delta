--$Id: 02-140-entityLogChanges.sql 13792 2013-10-17 11:10:31Z artamonov $
-- Description: add SEC_ENTITY_LOG.CHANGES

alter table SEC_ENTITY_LOG add CHANGES varchar(max);
