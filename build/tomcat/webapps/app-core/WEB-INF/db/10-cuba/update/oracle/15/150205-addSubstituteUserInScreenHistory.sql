-- $Id: 150205-addSubstituteUserInScreenHistory.sql 21985 2015-07-02 10:00:56Z artamonov $
-- Description:

alter table sec_screen_history add substituted_user_id varchar2(32)^
create index IDX_SEC_SCREEN_HIST_SUB_USER on SEC_SCREEN_HISTORY(SUBSTITUTED_USER_ID)^