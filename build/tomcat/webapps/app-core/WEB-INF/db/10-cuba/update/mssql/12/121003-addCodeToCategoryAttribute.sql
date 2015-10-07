--$Id: 121003-addCodeToCategoryAttribute.sql 12382 2013-08-02 10:55:43Z saiyan $
-- Description: add code field to CategoryAttribute

alter table SYS_CATEGORY_ATTR add CODE varchar(50);