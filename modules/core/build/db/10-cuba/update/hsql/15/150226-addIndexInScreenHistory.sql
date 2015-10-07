-- $Id: 150226-addIndexInScreenHistory.sql 21985 2015-07-02 10:00:56Z artamonov $
-- Description:

create index IDX_SEC_SCREEN_HIST_SUB_USER on SEC_SCREEN_HISTORY (SUBSTITUTED_USER_ID);