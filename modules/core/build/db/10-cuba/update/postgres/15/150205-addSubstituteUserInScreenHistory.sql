-- $Id: 150205-addSubstituteUserInScreenHistory.sql 21985 2015-07-02 10:00:56Z artamonov $
-- Description:

alter table sec_screen_history add substituted_user_id uuid;
alter table sec_screen_history add constraint FK_SEC_HISTORY_SUBSTITUTED_USER foreign key (substituted_user_id) references sec_user (id);