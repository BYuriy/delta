--$Id: 130311-renameColumnsForOracle.sql 10979 2013-03-28 14:26:58Z artamonov $--

alter table SEC_USER drop column TYPE;

{call sp_rename 'SYS_FILE.SIZE', 'FILE_SIZE', 'column'};

{call sp_rename 'SYS_FOLDER.TYPE', 'FOLDER_TYPE', 'column'};

{call sp_rename 'SEC_ENTITY_LOG.TYPE', 'CHANGE_TYPE', 'column'};

{call sp_rename 'SEC_GROUP_HIERARCHY.LEVEL', 'HIERARCHY_LEVEL', 'column'};

{call sp_rename 'SEC_PERMISSION.TYPE', 'PERMISSION_TYPE', 'column'};

{call sp_rename 'SEC_ROLE.TYPE', 'ROLE_TYPE', 'column'};
