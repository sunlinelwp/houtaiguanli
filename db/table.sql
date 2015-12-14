
set feedback off
set define off
prompt Dropping AP_SEQUENCE_NO...
drop table AP_SEQUENCE_NO cascade constraints;
prompt Dropping AP_SYS_COLUMN...
drop table AP_SYS_COLUMN cascade constraints;
prompt Dropping AP_SYS_DICT...
drop table AP_SYS_DICT cascade constraints;
prompt Dropping AP_SYS_ENCAP...
drop table AP_SYS_ENCAP cascade constraints;
prompt Dropping AP_SYS_MSG...
drop table AP_SYS_MSG cascade constraints;
prompt Dropping AP_SYS_SERVS...
drop table AP_SYS_SERVS cascade constraints;
prompt Dropping AP_SYS_TRANS...
drop table AP_SYS_TRANS cascade constraints;
prompt Dropping FUND_BUY_CHECK...
drop table FUND_BUY_CHECK cascade constraints;
prompt Dropping FUND_SETLLE...
drop table FUND_SETLLE cascade constraints;
prompt Dropping INSU_SETLLE...
drop table INSU_SETLLE cascade constraints;
prompt Dropping SIF_SYS_AUTH...
drop table SIF_SYS_AUTH cascade constraints;
prompt Dropping SIF_SYS_BAL...
drop table SIF_SYS_BAL cascade constraints;
prompt Dropping SIF_SYS_BRCH...
drop table SIF_SYS_BRCH cascade constraints;
prompt Dropping SIF_SYS_BRCH_QRY...
drop table SIF_SYS_BRCH_QRY cascade constraints;
prompt Dropping SIF_SYS_BUSINESS...
drop table SIF_SYS_BUSINESS cascade constraints;
prompt Dropping SIF_SYS_CCY...
drop table SIF_SYS_CCY cascade constraints;
prompt Dropping SIF_SYS_CHANNEL...
drop table SIF_SYS_CHANNEL cascade constraints;
prompt Dropping SIF_SYS_DICT...
drop table SIF_SYS_DICT cascade constraints;
prompt Dropping SIF_SYS_PARA...
drop table SIF_SYS_PARA cascade constraints;
prompt Dropping SIF_SYS_ROLE...
drop table SIF_SYS_ROLE cascade constraints;
prompt Dropping SIF_SYS_ROLE_AUTH...
drop table SIF_SYS_ROLE_AUTH cascade constraints;
prompt Dropping SIF_SYS_ROLE_USER...
drop table SIF_SYS_ROLE_USER cascade constraints;
prompt Dropping SIF_SYS_TEAM...
drop table SIF_SYS_TEAM cascade constraints;
prompt Dropping SIF_SYS_TEAM_MEMBER...
drop table SIF_SYS_TEAM_MEMBER cascade constraints;
prompt Dropping SIF_SYS_TRANS...
drop table SIF_SYS_TRANS cascade constraints;
prompt Dropping SIF_SYS_TRANS_LOG...
drop table SIF_SYS_TRANS_LOG cascade constraints;
prompt Dropping SIF_SYS_USER...
drop table SIF_SYS_USER cascade constraints;
prompt Dropping SIF_SYS_USER_QRY...
drop table SIF_SYS_USER_QRY cascade constraints;
prompt Dropping TMP_AINPAY_PAY_GATE_CHECK...
drop table TMP_AINPAY_PAY_GATE_CHECK cascade constraints;
prompt Dropping TMP_AINPAY_PAY_GATE_HEAD_CHECK...
drop table TMP_AINPAY_PAY_GATE_HEAD_CHECK cascade constraints;
prompt Dropping TMP_ALLINPAY_CLTN_CHECK...
drop table TMP_ALLINPAY_CLTN_CHECK cascade constraints;
prompt Dropping TMP_ALLINPAY_CLTN_HEAD_CHECK...
drop table TMP_ALLINPAY_CLTN_HEAD_CHECK cascade constraints;
prompt Dropping TMP_ALLINPAY_PAY_CHECK...
drop table TMP_ALLINPAY_PAY_CHECK cascade constraints;
prompt Dropping TMP_ALLINPAY_PAY_HEAD_CHECK...
drop table TMP_ALLINPAY_PAY_HEAD_CHECK cascade constraints;
prompt Dropping TMP_BACKINSU_BILL...
drop table TMP_BACKINSU_BILL cascade constraints;
prompt Dropping TMP_CHINAPAY_CLTN_CHECK...
drop table TMP_CHINAPAY_CLTN_CHECK cascade constraints;
prompt Dropping TMP_CHINAPAY_CLTN_HEAD_CHECK...
drop table TMP_CHINAPAY_CLTN_HEAD_CHECK cascade constraints;
prompt Dropping TMP_CHINAPAY_PAY_CHECK...
drop table TMP_CHINAPAY_PAY_CHECK cascade constraints;
prompt Dropping TMP_CHINAPAY_PAY_HEAD_CHECK...
drop table TMP_CHINAPAY_PAY_HEAD_CHECK cascade constraints;
prompt Dropping TMP_FUND_PROFIT...
drop table TMP_FUND_PROFIT cascade constraints;
prompt Dropping TMP_LIQUIDATION_ERR_CHECK...
drop table TMP_LIQUIDATION_ERR_CHECK cascade constraints;
prompt Dropping TMP_LIQUIDATION_ERR_HEAD_CHECK...
drop table TMP_LIQUIDATION_ERR_HEAD_CHECK cascade constraints;
prompt Dropping TMP_LIQUIDATION_SUB_CHECK...
drop table TMP_LIQUIDATION_SUB_CHECK cascade constraints;
prompt Dropping TMP_LIQUIDATION_SUB_HEAD_CHECK...
drop table TMP_LIQUIDATION_SUB_HEAD_CHECK cascade constraints;
prompt Dropping TMP_LIQUIDATION_TAIL_CHECK...
drop table TMP_LIQUIDATION_TAIL_CHECK cascade constraints;
prompt Dropping TMP_LIQUIDATION_TAL_HEAD_CHECK...
drop table TMP_LIQUIDATION_TAL_HEAD_CHECK cascade constraints;
prompt Dropping TMP_UNIONPAY_CLTN_CHECK...
drop table TMP_UNIONPAY_CLTN_CHECK cascade constraints;
prompt Dropping TMP_UNIONPAY_CLTN_HEAD_CHECK...
drop table TMP_UNIONPAY_CLTN_HEAD_CHECK cascade constraints;
prompt Dropping TMP_UNIONPAY_PAY_CHECK...
drop table TMP_UNIONPAY_PAY_CHECK cascade constraints;
prompt Dropping TMP_UNIONPAY_PAY_HEAD_CHECK...
drop table TMP_UNIONPAY_PAY_HEAD_CHECK cascade constraints;
prompt Dropping TMP_YQRX_AMOU...
drop table TMP_YQRX_AMOU cascade constraints;
prompt Creating AP_SEQUENCE_NO...
create table AP_SEQUENCE_NO
(
  name    VARCHAR2(19) not null,
  nextval NUMBER(21) not null
)
;
comment on table AP_SEQUENCE_NO
  is '���������';
comment on column AP_SEQUENCE_NO.name
  is '�ؼ���';
comment on column AP_SEQUENCE_NO.nextval
  is '˳���';
alter table AP_SEQUENCE_NO
  add constraint PK_AP_SEQUENCE_NO primary key (NAME);

prompt Creating AP_SYS_COLUMN...
create table AP_SYS_COLUMN
(
  column_cd      VARCHAR2(50) not null,
  column_name    VARCHAR2(50) not null,
  column_length  INTEGER,
  column_mapping VARCHAR2(50),
  column_type    CHAR(1),
  value_digit    CHAR(1),
  date_pattern   CHAR(1),
  default_value  VARCHAR2(50),
  cycling_flag   CHAR(1) not null,
  sort_seq       INTEGER not null,
  cycling_column VARCHAR2(50),
  polish_type    CHAR(1),
  polish_char    VARCHAR2(10),
  msg_cd         VARCHAR2(19) not null
)
;
comment on table AP_SYS_COLUMN
  is '�����ֶ�����';
comment on column AP_SYS_COLUMN.column_cd
  is '�ֶκ�';
comment on column AP_SYS_COLUMN.column_name
  is '�ֶ�����';
comment on column AP_SYS_COLUMN.column_length
  is '�ֶγ���
��������ʹ��';
comment on column AP_SYS_COLUMN.column_mapping
  is '�ֶ�ƥ���
�Էָ��/xml/json�ȸ�ʽʹ��';
comment on column AP_SYS_COLUMN.column_type
  is '�ֶ�����
F-������
Z-����
S-�ַ�
D-����';
comment on column AP_SYS_COLUMN.value_digit
  is '��ֵ����
B-������
D-ʮ����
O-�˽���
H-ʮ������';
comment on column AP_SYS_COLUMN.date_pattern
  is '���ڸ�ʽ
1-yyyyMMdd
2-yyyy-MM-dd';
comment on column AP_SYS_COLUMN.default_value
  is 'Ĭ��ֵ
';
comment on column AP_SYS_COLUMN.cycling_flag
  is 'ѭ���ڵ�
Y-��
N-��';
comment on column AP_SYS_COLUMN.sort_seq
  is '����';
comment on column AP_SYS_COLUMN.cycling_column
  is 'ѭ���ֶ�';
comment on column AP_SYS_COLUMN.polish_type
  is '���뷽ʽ
1-����
2-�Ҳ���';
comment on column AP_SYS_COLUMN.polish_char
  is '��������ַ�';
comment on column AP_SYS_COLUMN.msg_cd
  is '���ĸ�ʽ���';
create index IDX_IRP_SYS_COLUMN_MSGCD on AP_SYS_COLUMN (MSG_CD, CYCLING_COLUMN);
alter table AP_SYS_COLUMN
  add constraint PK_AP_SYS_COLUMN primary key (MSG_CD, COLUMN_CD);

prompt Creating AP_SYS_DICT...
create table AP_SYS_DICT
(
  dict_type        VARCHAR2(19) not null,
  dict_id          VARCHAR2(19) not null,
  dict_name        VARCHAR2(50),
  parent_dict_type VARCHAR2(19),
  parent_dict_id   VARCHAR2(19),
  status           CHAR(1),
  sort_no          NUMBER(2)
)
;
comment on table AP_SYS_DICT
  is 'ҵ���ֵ�';
comment on column AP_SYS_DICT.dict_type
  is '�ֵ����';
comment on column AP_SYS_DICT.dict_id
  is '�ֵ���';
comment on column AP_SYS_DICT.dict_name
  is '�ֵ�����';
comment on column AP_SYS_DICT.parent_dict_type
  is '��������';
comment on column AP_SYS_DICT.parent_dict_id
  is '�����ֶ���';
comment on column AP_SYS_DICT.status
  is '��Ч״̬';
comment on column AP_SYS_DICT.sort_no
  is '����';
create index AP_SYS_DICT_IDX1 on AP_SYS_DICT (DICT_TYPE, STATUS);
alter table AP_SYS_DICT
  add constraint PK_AP_SYS_DICT primary key (DICT_TYPE, DICT_ID);

prompt Creating AP_SYS_ENCAP...
create table AP_SYS_ENCAP
(
  encap_cd     VARCHAR2(10) not null,
  encap_remark VARCHAR2(100),
  req_msg      VARCHAR2(19) not null,
  rsp_msg      VARCHAR2(19) not null
)
;
comment on table AP_SYS_ENCAP
  is '���ķ�װ����';
comment on column AP_SYS_ENCAP.encap_cd
  is '���ķ�װ���';
comment on column AP_SYS_ENCAP.encap_remark
  is '��װ˵��';
comment on column AP_SYS_ENCAP.req_msg
  is '������';
comment on column AP_SYS_ENCAP.rsp_msg
  is '��������';
alter table AP_SYS_ENCAP
  add constraint PK_AP_SYS_ENCAP primary key (ENCAP_CD);

prompt Creating AP_SYS_MSG...
create table AP_SYS_MSG
(
  msg_cd      VARCHAR2(19) not null,
  msg_remark  VARCHAR2(100),
  msg_define  CHAR(1) not null,
  split_char  VARCHAR2(5),
  msg_len     CHAR(1),
  head_length INTEGER,
  head_msg    VARCHAR2(19),
  xml_coding  VARCHAR2(10)
)
;
comment on table AP_SYS_MSG
  is '���ĸ�ʽ����';
comment on column AP_SYS_MSG.msg_cd
  is '���ĸ�ʽ���';
comment on column AP_SYS_MSG.msg_remark
  is '���ĸ�ʽ˵��';
comment on column AP_SYS_MSG.msg_define
  is '��ʽ����
S-�ָ��
D-����
X-XML
J-json
';
comment on column AP_SYS_MSG.split_char
  is '�ָ���';
comment on column AP_SYS_MSG.msg_len
  is '���ĳ��ȶ���
1-������
2-������+����ͷ';
comment on column AP_SYS_MSG.head_length
  is '����ͷ����';
comment on column AP_SYS_MSG.head_msg
  is '����ͷ��ʽ����';
comment on column AP_SYS_MSG.xml_coding
  is 'XML�ַ���';
alter table AP_SYS_MSG
  add constraint PK_AP_SYS_MSG primary key (MSG_CD);

prompt Creating AP_SYS_SERVS...
create table AP_SYS_SERVS
(
  service_cd   VARCHAR2(10) not null,
  service_name VARCHAR2(50) not null,
  service_type CHAR(1) not null,
  service_ip   VARCHAR2(20) not null,
  service_port INTEGER not null,
  encode       VARCHAR2(10),
  file_path    VARCHAR2(500),
  login_user   VARCHAR2(20),
  login_passwd VARCHAR2(50),
  file_suffix  VARCHAR2(20)
)
;
comment on table AP_SYS_SERVS
  is '�ӿڷ�������';
comment on column AP_SYS_SERVS.service_cd
  is '������';
comment on column AP_SYS_SERVS.service_name
  is '��������';
comment on column AP_SYS_SERVS.service_type
  is '��������
1-socket';
comment on column AP_SYS_SERVS.service_ip
  is '����IP';
comment on column AP_SYS_SERVS.service_port
  is '����˿�';
comment on column AP_SYS_SERVS.encode
  is '�ַ���';
comment on column AP_SYS_SERVS.file_path
  is '����·��';
comment on column AP_SYS_SERVS.login_user
  is '��½�û���';
comment on column AP_SYS_SERVS.login_passwd
  is '��½����';
alter table AP_SYS_SERVS
  add constraint PK_AP_SYS_SERVS primary key (SERVICE_CD);

prompt Creating AP_SYS_TRANS...
create table AP_SYS_TRANS
(
  trans_cd     VARCHAR2(50) not null,
  trans_name   VARCHAR2(50) not null,
  service_cd   VARCHAR2(10) not null,
  encap_cd     VARCHAR2(10),
  trans_status CHAR(1),
  file_path    VARCHAR2(500),
  file_type    VARCHAR2(10),
  file_prefix  VARCHAR2(20),
  file_split   VARCHAR2(5),
  file_suffix  VARCHAR2(20),
  deal_cnt     NUMBER(5),
  cal_flag     CHAR(1),
  cal_char_at  NUMBER(10),
  begin_line   NUMBER(10)
)
;
comment on table AP_SYS_TRANS
  is '��������';
comment on column AP_SYS_TRANS.trans_cd
  is '���ױ�ʶ��';
comment on column AP_SYS_TRANS.trans_name
  is '��������';
comment on column AP_SYS_TRANS.service_cd
  is '���ͷ����';
comment on column AP_SYS_TRANS.encap_cd
  is '���ķ�װ���';
comment on column AP_SYS_TRANS.trans_status
  is '����״̬
Y-��
N-��';
comment on column AP_SYS_TRANS.file_path
  is '�����ļ�·��';
comment on column AP_SYS_TRANS.file_type
  is '�ļ�����
';
comment on column AP_SYS_TRANS.file_prefix
  is '�ļ�ǰ׺';
comment on column AP_SYS_TRANS.file_split
  is '�ļ��ָ���';
comment on column AP_SYS_TRANS.file_suffix
  is '�ļ���׺';
comment on column AP_SYS_TRANS.deal_cnt
  is 'һ�δ������ݱ���';
comment on column AP_SYS_TRANS.cal_flag
  is '�����Ƿ�Ϊͳ�Ʊ���';
comment on column AP_SYS_TRANS.cal_char_at
  is 'ͳ�Ʊ�������λ�� ��Ŵ�0��ʼ';
comment on column AP_SYS_TRANS.begin_line
  is '��ϸ��ʼ���� ��0��ʼ';
alter table AP_SYS_TRANS
  add constraint PK_AP_SYS_TRANS primary key (TRANS_CD);

prompt Creating FUND_BUY_CHECK...
create table FUND_BUY_CHECK
(
  fundno VARCHAR2(32) not null,
  trantp VARCHAR2(2),
  custac VARCHAR2(30),
  fundst VARCHAR2(10),
  tranam NUMBER(21,2),
  trantm VARCHAR2(14),
  trandt VARCHAR2(8) not null,
  biust  VARCHAR2(255)
)
;
alter table FUND_BUY_CHECK
  add constraint PK_FUND_BUY_CHECK primary key (FUNDNO, TRANDT);

prompt Creating FUND_SETLLE...
create table FUND_SETLLE
(
  dealdt  VARCHAR2(8) not null,
  totlam  NUMBER(21,2) not null,
  trantp  CHAR(1) not null,
  status  CHAR(1) not null,
  remark1 VARCHAR2(50),
  remark2 VARCHAR2(50)
)
;
comment on table FUND_SETLLE
  is '������������';
comment on column FUND_SETLLE.dealdt
  is '��������';
comment on column FUND_SETLLE.totlam
  is '�ܽ��';
comment on column FUND_SETLLE.trantp
  is '�������� 0-�깺 1-���';
comment on column FUND_SETLLE.status
  is '״̬ 0-δ���� 1-������';
comment on column FUND_SETLLE.remark1
  is 'Ԥ���ֶ�1';
comment on column FUND_SETLLE.remark2
  is 'Ԥ���ֶ�2';
alter table FUND_SETLLE
  add constraint PK_FUND_SETLLE primary key (DEALDT, TRANTP);

prompt Creating INSU_SETLLE...
create table INSU_SETLLE
(
  dealdt  VARCHAR2(8) not null,
  totlam  NUMBER(21,2) not null,
  trantp  CHAR(1) not null,
  status  CHAR(1) not null,
  remark1 VARCHAR2(50),
  remark2 VARCHAR2(50)
)
;
comment on table INSU_SETLLE
  is '������������';
comment on column INSU_SETLLE.dealdt
  is '��������';
comment on column INSU_SETLLE.totlam
  is '�ܽ��';
comment on column INSU_SETLLE.trantp
  is '�������� 0-�깺 1-���';
comment on column INSU_SETLLE.status
  is '״̬ 0-δ���� 1-������';
comment on column INSU_SETLLE.remark1
  is 'Ԥ���ֶ�1';
comment on column INSU_SETLLE.remark2
  is 'Ԥ���ֶ�2';
alter table INSU_SETLLE
  add constraint PK_INSU_SETLLE primary key (DEALDT, TRANTP);

prompt Creating SIF_SYS_AUTH...
create table SIF_SYS_AUTH
(
  auth_cd        VARCHAR2(255) not null,
  auth_type      CHAR(1) not null,
  register_cd    VARCHAR2(255) not null,
  auth_url       VARCHAR2(255),
  menu_name      VARCHAR2(255),
  parent_auth_cd VARCHAR2(255),
  rank           NUMBER(10),
  sortno         NUMBER(10),
  iconfg         VARCHAR2(20),
  target_flag    CHAR(1)
)
;
alter table SIF_SYS_AUTH
  add primary key (AUTH_CD, AUTH_TYPE, REGISTER_CD);

prompt Creating SIF_SYS_BAL...
create table SIF_SYS_BAL
(
  balcompt_cd        VARCHAR2(19) not null,
  balcompt_name      VARCHAR2(60) not null,
  parent_balcompt_cd VARCHAR2(19)
)
;
comment on table SIF_SYS_BAL
  is '������';
comment on column SIF_SYS_BAL.balcompt_cd
  is '������ID';
comment on column SIF_SYS_BAL.balcompt_name
  is '����������';
comment on column SIF_SYS_BAL.parent_balcompt_cd
  is '����������';
alter table SIF_SYS_BAL
  add constraint PK_SIF_SYS_BAL primary key (BALCOMPT_CD);

prompt Creating SIF_SYS_BRCH...
create table SIF_SYS_BRCH
(
  branch_cd        VARCHAR2(19) not null,
  register_cd      VARCHAR2(19) not null,
  branch_addr      VARCHAR2(100),
  branch_name      VARCHAR2(50) not null,
  branch_type      VARCHAR2(1),
  branch_zipcode   VARCHAR2(6),
  parent_branch_cd VARCHAR2(19),
  rank             NUMBER(2),
  sortno           NUMBER(2),
  status           VARCHAR2(1)
)
;
alter table SIF_SYS_BRCH
  add primary key (BRANCH_CD, REGISTER_CD);

prompt Creating SIF_SYS_BRCH_QRY...
create table SIF_SYS_BRCH_QRY
(
  branch_cd       VARCHAR2(19) not null,
  query_branch_cd VARCHAR2(19) not null,
  register_cd     VARCHAR2(19) not null
)
;
alter table SIF_SYS_BRCH_QRY
  add primary key (BRANCH_CD, QUERY_BRANCH_CD, REGISTER_CD);

prompt Creating SIF_SYS_BUSINESS...
create table SIF_SYS_BUSINESS
(
  busi_cd      VARCHAR2(19) not null,
  register_cd  VARCHAR2(19) not null,
  account_rule VARCHAR2(1) not null,
  busi_name    VARCHAR2(200) not null,
  halt_flag    VARCHAR2(1) not null,
  prod_rule    VARCHAR2(1) not null
)
;
alter table SIF_SYS_BUSINESS
  add primary key (BUSI_CD, REGISTER_CD);

prompt Creating SIF_SYS_CCY...
create table SIF_SYS_CCY
(
  ccy_cd        VARCHAR2(3) not null,
  ccy_name      VARCHAR2(50) not null,
  exchange_rate NUMBER(12,4) not null,
  ver           INTEGER,
  founder       VARCHAR2(64),
  found_time    TIMESTAMP(6),
  modifier      VARCHAR2(64),
  modify_time   TIMESTAMP(6)
)
;
comment on table SIF_SYS_CCY
  is '����';
comment on column SIF_SYS_CCY.ccy_cd
  is '����';
comment on column SIF_SYS_CCY.ccy_name
  is '��������';
comment on column SIF_SYS_CCY.exchange_rate
  is '����';
comment on column SIF_SYS_CCY.ver
  is '�汾��';
comment on column SIF_SYS_CCY.founder
  is '������';
comment on column SIF_SYS_CCY.found_time
  is '����ʱ��';
comment on column SIF_SYS_CCY.modifier
  is '�޸���';
comment on column SIF_SYS_CCY.modify_time
  is '�޸�ʱ��';
alter table SIF_SYS_CCY
  add constraint PK_SIF_SYS_CCY primary key (CCY_CD);

prompt Creating SIF_SYS_CHANNEL...
create table SIF_SYS_CHANNEL
(
  channel_cd   VARCHAR2(19) not null,
  channel_name CHAR(10) not null
)
;
comment on table SIF_SYS_CHANNEL
  is '��������';
comment on column SIF_SYS_CHANNEL.channel_cd
  is '�������';
comment on column SIF_SYS_CHANNEL.channel_name
  is '��������';
alter table SIF_SYS_CHANNEL
  add constraint PK_SIF_SYS_CHANNEL primary key (CHANNEL_CD);

prompt Creating SIF_SYS_DICT...
create table SIF_SYS_DICT
(
  dict_type        VARCHAR2(19) not null,
  dict_key         VARCHAR2(19) not null,
  dict_value       VARCHAR2(19),
  parent_dict_type VARCHAR2(19),
  parent_dict_key  VARCHAR2(19),
  status           CHAR(1),
  sortno           NUMBER(2)
)
;
comment on table SIF_SYS_DICT
  is 'ҵ���ֵ�';
comment on column SIF_SYS_DICT.dict_type
  is '�ֵ����';
comment on column SIF_SYS_DICT.dict_key
  is '�ֵ�ֵ';
comment on column SIF_SYS_DICT.dict_value
  is '�ֵ�����';
comment on column SIF_SYS_DICT.parent_dict_type
  is '��������';
comment on column SIF_SYS_DICT.parent_dict_key
  is '��������';
comment on column SIF_SYS_DICT.status
  is '��Ч״̬';
comment on column SIF_SYS_DICT.sortno
  is '����';
alter table SIF_SYS_DICT
  add constraint PK_SIF_SYS_DICT primary key (DICT_TYPE, DICT_KEY);

prompt Creating SIF_SYS_PARA...
create table SIF_SYS_PARA
(
  register_cd        VARCHAR2(19) not null,
  register_name      VARCHAR2(200) not null,
  sys_dt             DATE not null,
  last_sys_dt        DATE not null,
  password_error     NUMBER(1),
  freezen_password   NUMBER(1),
  year_days          NUMBER(3),
  month_days         NUMBER(2),
  oper_auth_default  CHAR(1),
  query_auth_default CHAR(1)
)
;
comment on table SIF_SYS_PARA
  is 'ȫ�ֿ��Ʊ�';
comment on column SIF_SYS_PARA.register_cd
  is 'ע�������';
comment on column SIF_SYS_PARA.register_name
  is '���ڻ�������';
comment on column SIF_SYS_PARA.sys_dt
  is 'ϵͳ��ǰ����';
comment on column SIF_SYS_PARA.last_sys_dt
  is '����ϵͳ����';
comment on column SIF_SYS_PARA.password_error
  is '��������������';
comment on column SIF_SYS_PARA.freezen_password
  is '��������������';
comment on column SIF_SYS_PARA.year_days
  is 'ȫ������';
comment on column SIF_SYS_PARA.month_days
  is '������';
comment on column SIF_SYS_PARA.oper_auth_default
  is '����Ȩ��Ĭ��ֵ
1-ǿ�Ƽ��
2-�����';
comment on column SIF_SYS_PARA.query_auth_default
  is '��ѯȨ��Ĭ��ֵ
1-ȫ��
2-����';
alter table SIF_SYS_PARA
  add constraint PK_SIF_SYS_PARA primary key (REGISTER_CD);

prompt Creating SIF_SYS_ROLE...
create table SIF_SYS_ROLE
(
  auth_type   CHAR(1) not null,
  register_cd VARCHAR2(255) not null,
  role_cd     VARCHAR2(255) not null,
  query_auth  CHAR(1),
  role_name   VARCHAR2(50) not null
)
;
alter table SIF_SYS_ROLE
  add primary key (AUTH_TYPE, REGISTER_CD, ROLE_CD);

prompt Creating SIF_SYS_ROLE_AUTH...
create table SIF_SYS_ROLE_AUTH
(
  auth_cd     VARCHAR2(255) not null,
  auth_type   CHAR(1) not null,
  register_cd VARCHAR2(255) not null,
  role_cd     VARCHAR2(255) not null
)
;
alter table SIF_SYS_ROLE_AUTH
  add primary key (AUTH_CD, AUTH_TYPE, REGISTER_CD, ROLE_CD);

prompt Creating SIF_SYS_ROLE_USER...
create table SIF_SYS_ROLE_USER
(
  user_cd     VARCHAR2(255) not null,
  role_cd     VARCHAR2(255) not null,
  register_cd VARCHAR2(255) not null,
  auth_type   CHAR(1) not null
)
;
alter table SIF_SYS_ROLE_USER
  add primary key (USER_CD, ROLE_CD, REGISTER_CD, AUTH_TYPE);

prompt Creating SIF_SYS_TEAM...
create table SIF_SYS_TEAM
(
  register_cd VARCHAR2(19) not null,
  team_cd     VARCHAR2(19) not null,
  branch_cd   VARCHAR2(19) not null,
  team_name   VARCHAR2(100) not null
)
;
alter table SIF_SYS_TEAM
  add primary key (REGISTER_CD, TEAM_CD);

prompt Creating SIF_SYS_TEAM_MEMBER...
create table SIF_SYS_TEAM_MEMBER
(
  register_cd VARCHAR2(19) not null,
  team_cd     VARCHAR2(19) not null,
  user_cd     VARCHAR2(19) not null
)
;
alter table SIF_SYS_TEAM_MEMBER
  add primary key (REGISTER_CD, TEAM_CD, USER_CD);

prompt Creating SIF_SYS_TRANS...
create table SIF_SYS_TRANS
(
  trans_cd   VARCHAR2(19) not null,
  trans_name VARCHAR2(50) not null,
  trans_type CHAR(1) not null,
  mode_cd    VARCHAR2(10),
  pkt_def    VARCHAR2(50),
  status     CHAR(1)
)
;
comment on table SIF_SYS_TRANS
  is '��������';
comment on column SIF_SYS_TRANS.trans_cd
  is '���ױ��';
comment on column SIF_SYS_TRANS.trans_name
  is '��������';
comment on column SIF_SYS_TRANS.trans_type
  is '��������
1-ϵͳ�ڲ�����
2-���ս���
3-������
';
comment on column SIF_SYS_TRANS.mode_cd
  is 'account-�����˻�
product-��Ʒ
integral-����';
comment on column SIF_SYS_TRANS.pkt_def
  is '���Ľӿڶ���';
comment on column SIF_SYS_TRANS.status
  is '����
Y-��
N-��';
alter table SIF_SYS_TRANS
  add constraint PK_SIF_SYS_TRANS primary key (TRANS_CD);

prompt Creating SIF_SYS_TRANS_LOG...
create table SIF_SYS_TRANS_LOG
(
  trans_no     VARCHAR2(40) not null,
  rec_trans_no VARCHAR2(40),
  server_cd    CHAR(2),
  trans_cd     VARCHAR2(19),
  trans_dt     DATE,
  pkg          VARCHAR2(4000),
  reverse_flag CHAR(1),
  account_no   VARCHAR2(32),
  acct_date    DATE
)
;
comment on table SIF_SYS_TRANS_LOG
  is '��ˮ��־';
comment on column SIF_SYS_TRANS_LOG.trans_no
  is '������ˮ��';
comment on column SIF_SYS_TRANS_LOG.rec_trans_no
  is '������ˮ��';
comment on column SIF_SYS_TRANS_LOG.server_cd
  is '����
10-����
20-ֱ��ƽ̨
50-����';
comment on column SIF_SYS_TRANS_LOG.trans_cd
  is '���ױ��';
comment on column SIF_SYS_TRANS_LOG.trans_dt
  is '��������';
comment on column SIF_SYS_TRANS_LOG.pkg
  is '��������';
comment on column SIF_SYS_TRANS_LOG.reverse_flag
  is '�Ƿ����
Y-��������
N-ԭ����';
comment on column SIF_SYS_TRANS_LOG.account_no
  is '�����˺�';
comment on column SIF_SYS_TRANS_LOG.acct_date
  is '��������';
alter table SIF_SYS_TRANS_LOG
  add constraint PK_SIF_SYS_TRANS_LOG primary key (TRANS_NO);

prompt Creating SIF_SYS_USER...
create table SIF_SYS_USER
(
  register_cd   VARCHAR2(19) not null,
  user_cd       VARCHAR2(19) not null,
  branch_cd     VARCHAR2(19),
  status        VARCHAR2(1),
  user_certno   VARCHAR2(20),
  user_email    VARCHAR2(50),
  user_name     VARCHAR2(20) not null,
  user_password VARCHAR2(10) not null,
  user_telno    VARCHAR2(20)
)
;
alter table SIF_SYS_USER
  add primary key (REGISTER_CD, USER_CD);

prompt Creating SIF_SYS_USER_QRY...
create table SIF_SYS_USER_QRY
(
  query_user_cd VARCHAR2(19) not null,
  register_cd   VARCHAR2(19) not null,
  user_cd       VARCHAR2(19) not null
)
;
alter table SIF_SYS_USER_QRY
  add primary key (QUERY_USER_CD, REGISTER_CD, USER_CD);

prompt Creating TMP_AINPAY_PAY_GATE_CHECK...
create table TMP_AINPAY_PAY_GATE_CHECK
(
  check_date   VARCHAR2(8) not null,
  merchant_dt  VARCHAR2(8) not null,
  billno       VARCHAR2(60) not null,
  acctno       VARCHAR2(32),
  trans_amt    NUMBER(19,2),
  fee_amt      NUMBER(19,2),
  sign_status  VARCHAR2(1),
  chk_status   VARCHAR2(1),
  core_date    VARCHAR2(255),
  core_seqno   VARCHAR2(255),
  check_status VARCHAR2(1) not null,
  tl_serial_no VARCHAR2(42),
  acout_status VARCHAR2(1),
  trade_status VARCHAR2(1),
  clear_amt    NUMBER(19,2)
)
;
comment on table TMP_AINPAY_PAY_GATE_CHECK
  is 'ͨ���������˶�';
comment on column TMP_AINPAY_PAY_GATE_CHECK.check_date
  is '�˶�����';
comment on column TMP_AINPAY_PAY_GATE_CHECK.merchant_dt
  is '�̻�����';
comment on column TMP_AINPAY_PAY_GATE_CHECK.billno
  is '������';
comment on column TMP_AINPAY_PAY_GATE_CHECK.acctno
  is '�տ��˺�';
comment on column TMP_AINPAY_PAY_GATE_CHECK.trans_amt
  is '���۽��';
comment on column TMP_AINPAY_PAY_GATE_CHECK.fee_amt
  is '������';
comment on column TMP_AINPAY_PAY_GATE_CHECK.sign_status
  is '��ǩ���';
comment on column TMP_AINPAY_PAY_GATE_CHECK.chk_status
  is '���˽��';
comment on column TMP_AINPAY_PAY_GATE_CHECK.core_date
  is '��������';
comment on column TMP_AINPAY_PAY_GATE_CHECK.core_seqno
  is '������ˮ';
comment on column TMP_AINPAY_PAY_GATE_CHECK.check_status
  is '����״̬';
comment on column TMP_AINPAY_PAY_GATE_CHECK.tl_serial_no
  is 'ͨ����ˮ��';
comment on column TMP_AINPAY_PAY_GATE_CHECK.acout_status
  is '��������״̬';
comment on column TMP_AINPAY_PAY_GATE_CHECK.trade_status
  is '���н���״̬';
comment on column TMP_AINPAY_PAY_GATE_CHECK.clear_amt
  is '������';

prompt Creating TMP_AINPAY_PAY_GATE_HEAD_CHECK...
create table TMP_AINPAY_PAY_GATE_HEAD_CHECK
(
  check_date   VARCHAR2(8) not null,
  fee          NUMBER(21,2),
  amount       NUMBER(21,2),
  check_status CHAR(1),
  trade_count  VARCHAR2(10),
  succes_count VARCHAR2(10),
  refund       VARCHAR2(10),
  ref_amt      NUMBER(19,2),
  clear_amt    NUMBER(19,2),
  clear_date   VARCHAR2(8),
  check_seqno  VARCHAR2(42)
)
;
comment on table TMP_AINPAY_PAY_GATE_HEAD_CHECK
  is 'ͨ���������ͷ��Ϣ�˶�';
comment on column TMP_AINPAY_PAY_GATE_HEAD_CHECK.check_date
  is '�˶�����';
comment on column TMP_AINPAY_PAY_GATE_HEAD_CHECK.fee
  is '������';
comment on column TMP_AINPAY_PAY_GATE_HEAD_CHECK.amount
  is '���׽��';
comment on column TMP_AINPAY_PAY_GATE_HEAD_CHECK.check_status
  is '״̬';
comment on column TMP_AINPAY_PAY_GATE_HEAD_CHECK.trade_count
  is '�����ܱ���';
comment on column TMP_AINPAY_PAY_GATE_HEAD_CHECK.succes_count
  is '�ɹ�����';
comment on column TMP_AINPAY_PAY_GATE_HEAD_CHECK.refund
  is '�˿����';
comment on column TMP_AINPAY_PAY_GATE_HEAD_CHECK.ref_amt
  is '�˿���';
comment on column TMP_AINPAY_PAY_GATE_HEAD_CHECK.clear_amt
  is '������';
comment on column TMP_AINPAY_PAY_GATE_HEAD_CHECK.clear_date
  is '��������';
comment on column TMP_AINPAY_PAY_GATE_HEAD_CHECK.check_seqno
  is '������ˮ';

prompt Creating TMP_ALLINPAY_CLTN_CHECK...
create table TMP_ALLINPAY_CLTN_CHECK
(
  check_date   VARCHAR2(8) not null,
  merchant_cd  VARCHAR2(15),
  merchant_dt  VARCHAR2(40) not null,
  billno       VARCHAR2(60) not null,
  acctno       VARCHAR2(32),
  trans_amt    NUMBER(19,2),
  sign_status  VARCHAR2(1),
  check_status VARCHAR2(1) not null,
  core_date    VARCHAR2(255),
  core_seqno   VARCHAR2(255),
  chk_status   VARCHAR2(255),
  fee_amt      NUMBER(19,2),
  billid       VARCHAR2(32),
  acout_status VARCHAR2(1),
  trade_status VARCHAR2(1),
  tl_status    VARCHAR2(4),
  key_element  VARCHAR2(1)
)
;
comment on table TMP_ALLINPAY_CLTN_CHECK
  is 'ͨ�����۲��˶�';
comment on column TMP_ALLINPAY_CLTN_CHECK.check_date
  is '�˶�����';
comment on column TMP_ALLINPAY_CLTN_CHECK.merchant_cd
  is '�̻���';
comment on column TMP_ALLINPAY_CLTN_CHECK.merchant_dt
  is '�̻�����';
comment on column TMP_ALLINPAY_CLTN_CHECK.billno
  is '������';
comment on column TMP_ALLINPAY_CLTN_CHECK.acctno
  is '�����˺�';
comment on column TMP_ALLINPAY_CLTN_CHECK.trans_amt
  is '���۽��';
comment on column TMP_ALLINPAY_CLTN_CHECK.sign_status
  is 'CP״̬';
comment on column TMP_ALLINPAY_CLTN_CHECK.check_status
  is '��ǩ���';
comment on column TMP_ALLINPAY_CLTN_CHECK.core_date
  is '����';
comment on column TMP_ALLINPAY_CLTN_CHECK.core_seqno
  is '���˽��';
comment on column TMP_ALLINPAY_CLTN_CHECK.billid
  is '�������';
comment on column TMP_ALLINPAY_CLTN_CHECK.acout_status
  is '��������״̬';
comment on column TMP_ALLINPAY_CLTN_CHECK.trade_status
  is '���н���״̬';
comment on column TMP_ALLINPAY_CLTN_CHECK.tl_status
  is 'ͨ��״̬';
comment on column TMP_ALLINPAY_CLTN_CHECK.key_element
  is '�ؼ�Ҫ��';

prompt Creating TMP_ALLINPAY_CLTN_HEAD_CHECK...
create table TMP_ALLINPAY_CLTN_HEAD_CHECK
(
  check_date   VARCHAR2(8) not null,
  fee          NUMBER(21,2),
  amount       NUMBER(21,2),
  check_status VARCHAR2(1)
)
;
comment on table TMP_ALLINPAY_CLTN_HEAD_CHECK
  is 'ͨ�����۲��ͷ��Ϣ�˶�';
comment on column TMP_ALLINPAY_CLTN_HEAD_CHECK.check_date
  is '�˶�����';
comment on column TMP_ALLINPAY_CLTN_HEAD_CHECK.fee
  is '������';

prompt Creating TMP_ALLINPAY_PAY_CHECK...
create table TMP_ALLINPAY_PAY_CHECK
(
  check_date   VARCHAR2(8) not null,
  merchant_cd  VARCHAR2(15),
  merchant_dt  VARCHAR2(40) not null,
  billno       VARCHAR2(60) not null,
  acctno       VARCHAR2(32),
  trans_amt    NUMBER(19,2),
  fee_amt      NUMBER(19,2),
  sign_status  VARCHAR2(1),
  chk_status   VARCHAR2(1),
  core_date    VARCHAR2(255),
  core_seqno   VARCHAR2(255),
  check_status VARCHAR2(1) not null,
  billid       VARCHAR2(32),
  acout_status VARCHAR2(1),
  trade_status VARCHAR2(1),
  tl_status    VARCHAR2(4),
  key_element  VARCHAR2(1)
)
;
comment on table TMP_ALLINPAY_PAY_CHECK
  is 'ͨ���������˶�';
comment on column TMP_ALLINPAY_PAY_CHECK.check_date
  is '�˶�����';
comment on column TMP_ALLINPAY_PAY_CHECK.merchant_cd
  is '�̻���';
comment on column TMP_ALLINPAY_PAY_CHECK.merchant_dt
  is '�̻�����';
comment on column TMP_ALLINPAY_PAY_CHECK.billno
  is '������';
comment on column TMP_ALLINPAY_PAY_CHECK.acctno
  is '�տ��˺�';
comment on column TMP_ALLINPAY_PAY_CHECK.trans_amt
  is '���۽��';
comment on column TMP_ALLINPAY_PAY_CHECK.fee_amt
  is '������';
comment on column TMP_ALLINPAY_PAY_CHECK.sign_status
  is '��ǩ���';
comment on column TMP_ALLINPAY_PAY_CHECK.chk_status
  is '���˽��';
comment on column TMP_ALLINPAY_PAY_CHECK.core_date
  is '��������';
comment on column TMP_ALLINPAY_PAY_CHECK.core_seqno
  is '������ˮ';
comment on column TMP_ALLINPAY_PAY_CHECK.check_status
  is '����״̬';
comment on column TMP_ALLINPAY_PAY_CHECK.billid
  is '�������';
comment on column TMP_ALLINPAY_PAY_CHECK.acout_status
  is '��������״̬';
comment on column TMP_ALLINPAY_PAY_CHECK.trade_status
  is '���н���״̬';
comment on column TMP_ALLINPAY_PAY_CHECK.tl_status
  is 'ͨ��״̬';
comment on column TMP_ALLINPAY_PAY_CHECK.key_element
  is '�ؼ�Ҫ��';

prompt Creating TMP_ALLINPAY_PAY_HEAD_CHECK...
create table TMP_ALLINPAY_PAY_HEAD_CHECK
(
  check_date   VARCHAR2(8) not null,
  fee          NUMBER(21,2),
  amount       NUMBER(21,2),
  check_status CHAR(1)
)
;
comment on table TMP_ALLINPAY_PAY_HEAD_CHECK
  is 'ͨ���������ͷ��Ϣ�˶�';
comment on column TMP_ALLINPAY_PAY_HEAD_CHECK.check_date
  is '�˶�����';
comment on column TMP_ALLINPAY_PAY_HEAD_CHECK.fee
  is '������';

prompt Creating TMP_BACKINSU_BILL...
create table TMP_BACKINSU_BILL
(
  dealdt       VARCHAR2(8),
  procna       VARCHAR2(80),
  transq       VARCHAR2(32),
  polino       VARCHAR2(40) not null,
  custna       VARCHAR2(50),
  tranam       NUMBER(19,2),
  chk_status   CHAR(1),
  passtm       VARCHAR2(50),
  check_status CHAR(1) not null,
  msg          VARCHAR2(2000)
)
;
comment on table TMP_BACKINSU_BILL
  is '�������˱���¼';
comment on column TMP_BACKINSU_BILL.dealdt
  is '��������';
comment on column TMP_BACKINSU_BILL.procna
  is '��Ʒ����';
comment on column TMP_BACKINSU_BILL.transq
  is '������ˮ��';
comment on column TMP_BACKINSU_BILL.polino
  is '������';
comment on column TMP_BACKINSU_BILL.custna
  is '�˱�������';
comment on column TMP_BACKINSU_BILL.tranam
  is 'ʵ���˱����';
comment on column TMP_BACKINSU_BILL.chk_status
  is '�˱����״̬';
comment on column TMP_BACKINSU_BILL.passtm
  is '�˱����ͨ��ʱ��';
comment on column TMP_BACKINSU_BILL.check_status
  is '����״̬
0-δ����
1-����ʧ��
2-���˳ɹ�����ʧ��
3-�ɹ�';
comment on column TMP_BACKINSU_BILL.msg
  is '������Ϣ';
create index TMP_BACKINSU_BILL_IDX1 on TMP_BACKINSU_BILL (DEALDT);
alter table TMP_BACKINSU_BILL
  add constraint PK_TMP_BACKINSU_BILL primary key (POLINO);

prompt Creating TMP_CHINAPAY_CLTN_CHECK...
create table TMP_CHINAPAY_CLTN_CHECK
(
  check_date   VARCHAR2(8) not null,
  merchant_cd  VARCHAR2(15),
  merchant_dt  VARCHAR2(40) not null,
  billno       VARCHAR2(16) not null,
  acctno       VARCHAR2(32),
  trans_amt    NUMBER(19,2),
  cp_status    VARCHAR2(4),
  chk_status   CHAR(1),
  fee_amt      NUMBER(19,2),
  sign_status  CHAR(1),
  check_status CHAR(1) not null,
  core_date    VARCHAR2(255),
  core_seqno   VARCHAR2(255),
  trans_no     VARCHAR2(255)
)
;
comment on table TMP_CHINAPAY_CLTN_CHECK
  is 'ChinaPay���۲��˶�';
comment on column TMP_CHINAPAY_CLTN_CHECK.check_date
  is '�˶�����';
comment on column TMP_CHINAPAY_CLTN_CHECK.merchant_cd
  is '�̻���';
comment on column TMP_CHINAPAY_CLTN_CHECK.merchant_dt
  is '�̻�����';
comment on column TMP_CHINAPAY_CLTN_CHECK.billno
  is '������';
comment on column TMP_CHINAPAY_CLTN_CHECK.acctno
  is '�����˺�';
comment on column TMP_CHINAPAY_CLTN_CHECK.trans_amt
  is '���۽��';
comment on column TMP_CHINAPAY_CLTN_CHECK.cp_status
  is 'CP״̬';
comment on column TMP_CHINAPAY_CLTN_CHECK.chk_status
  is '���˽��
1-ƽ
2-���ж�
3-������';
comment on column TMP_CHINAPAY_CLTN_CHECK.fee_amt
  is '����';
comment on column TMP_CHINAPAY_CLTN_CHECK.sign_status
  is '��ǩ���
0-ͨ��
1-��ͨ��';
comment on column TMP_CHINAPAY_CLTN_CHECK.check_status
  is '����״̬
Y-�Ѵ���
N-δ����';
create index TMP_CHINAPAY_CLTN_CHECK_IDX1 on TMP_CHINAPAY_CLTN_CHECK (CHECK_DATE);
alter table TMP_CHINAPAY_CLTN_CHECK
  add constraint PK_TMP_CHINAPAY_CLTN_CHECK primary key (CHECK_DATE, MERCHANT_DT, BILLNO);

prompt Creating TMP_CHINAPAY_CLTN_HEAD_CHECK...
create table TMP_CHINAPAY_CLTN_HEAD_CHECK
(
  check_date   VARCHAR2(8) not null,
  fee          NUMBER(21,2),
  amount       NUMBER(21,2),
  check_status CHAR(1)
)
;
comment on table TMP_CHINAPAY_CLTN_HEAD_CHECK
  is 'ChinaPay���۲��ͷ��Ϣ�˶�';
comment on column TMP_CHINAPAY_CLTN_HEAD_CHECK.check_date
  is '�˶�����';
comment on column TMP_CHINAPAY_CLTN_HEAD_CHECK.fee
  is '������';
alter table TMP_CHINAPAY_CLTN_HEAD_CHECK
  add constraint PK_TMP_CHINAPAY_CLTN_HEAD_CHEC primary key (CHECK_DATE);

prompt Creating TMP_CHINAPAY_PAY_CHECK...
create table TMP_CHINAPAY_PAY_CHECK
(
  check_date   VARCHAR2(8) not null,
  merchant_cd  VARCHAR2(15),
  merchant_dt  VARCHAR2(8) not null,
  cp_seqno     VARCHAR2(32) not null,
  cp_dt        VARCHAR2(8),
  acctno       VARCHAR2(32),
  acct_name    VARCHAR2(200),
  trans_amt    NUMBER(19,2),
  fee_amt      NUMBER(19,2),
  status       CHAR(1),
  core_date    VARCHAR2(10),
  core_seqno   VARCHAR2(40),
  chk_status   CHAR(1),
  sign_status  CHAR(1),
  check_status CHAR(1) not null
)
;
comment on table TMP_CHINAPAY_PAY_CHECK
  is 'ChinaPay�������˶�';
comment on column TMP_CHINAPAY_PAY_CHECK.check_date
  is '�˶�����';
comment on column TMP_CHINAPAY_PAY_CHECK.merchant_cd
  is '�̻���';
comment on column TMP_CHINAPAY_PAY_CHECK.merchant_dt
  is '�̻�����';
comment on column TMP_CHINAPAY_PAY_CHECK.cp_seqno
  is '�̻���ˮ';
comment on column TMP_CHINAPAY_PAY_CHECK.cp_dt
  is 'cp����';
comment on column TMP_CHINAPAY_PAY_CHECK.acctno
  is '�տ��˺�';
comment on column TMP_CHINAPAY_PAY_CHECK.acct_name
  is '�տ�������';
comment on column TMP_CHINAPAY_PAY_CHECK.trans_amt
  is '���';
comment on column TMP_CHINAPAY_PAY_CHECK.fee_amt
  is '������';
comment on column TMP_CHINAPAY_PAY_CHECK.status
  is '״̬
s-�ɹ� ���׳ɹ�
2-������	�����ѽ���
3-������	������ȷ��
4-������	��������
5-������	�ѷ�������
6-ʧ��	�������˵�
7-������	�ػ����ύ
8-������	�ػ��ѷ���
9-ʧ��	�ػ����˵�';
comment on column TMP_CHINAPAY_PAY_CHECK.core_date
  is '��������';
comment on column TMP_CHINAPAY_PAY_CHECK.core_seqno
  is '������ˮ';
comment on column TMP_CHINAPAY_PAY_CHECK.chk_status
  is '���˽��
1-ƽ
2-���ж�
3-������';
comment on column TMP_CHINAPAY_PAY_CHECK.sign_status
  is '��ǩ���
0-ͨ��
1-��ͨ��';
comment on column TMP_CHINAPAY_PAY_CHECK.check_status
  is '����״̬
Y-�Ѵ���
N-δ����';
create index TMP_CHINAPAY_PAY_CHECK_IDX1 on TMP_CHINAPAY_PAY_CHECK (CHECK_DATE);
alter table TMP_CHINAPAY_PAY_CHECK
  add constraint PK_TMP_CHINAPAY_PAY_CHECK primary key (CHECK_DATE, MERCHANT_DT, CP_SEQNO);

prompt Creating TMP_CHINAPAY_PAY_HEAD_CHECK...
create table TMP_CHINAPAY_PAY_HEAD_CHECK
(
  check_date   VARCHAR2(8) not null,
  fee          NUMBER(21,2),
  amount       NUMBER(21,2),
  check_status CHAR(1)
)
;
comment on table TMP_CHINAPAY_PAY_HEAD_CHECK
  is 'ChinaPay�������ͷ��Ϣ�˶�';
comment on column TMP_CHINAPAY_PAY_HEAD_CHECK.check_date
  is '�˶�����';
comment on column TMP_CHINAPAY_PAY_HEAD_CHECK.fee
  is '������';
alter table TMP_CHINAPAY_PAY_HEAD_CHECK
  add constraint PK_TMP_CHINAPAY_PAY_HEAD_CHECK primary key (CHECK_DATE);

prompt Creating TMP_FUND_PROFIT...
create table TMP_FUND_PROFIT
(
  dealdt VARCHAR2(8) not null,
  prftam NUMBER(21,2),
  chekst CHAR(1)
)
;
alter table TMP_FUND_PROFIT
  add constraint PK_TMP_FUND_PROFIT_PK1 primary key (DEALDT);

prompt Creating TMP_LIQUIDATION_ERR_CHECK...
create table TMP_LIQUIDATION_ERR_CHECK
(
  check_date         VARCHAR2(8) not null,
  accounting_date    VARCHAR2(8) not null,
  traffic_flow       VARCHAR2(32) not null,
  lending_direction  VARCHAR2(1),
  transaction_amount NUMBER(16,2),
  account_number     VARCHAR2(32),
  reconciliation     VARCHAR2(1),
  syndicated_number  VARCHAR2(8),
  bank_number        VARCHAR2(10),
  mechanism_number   VARCHAR2(12),
  iou_number         VARCHAR2(20),
  loan_status        VARCHAR2(1)
)
;
comment on table TMP_LIQUIDATION_ERR_CHECK
  is '����ƽ̨����ļ�';
comment on column TMP_LIQUIDATION_ERR_CHECK.check_date
  is '�������� ����';
comment on column TMP_LIQUIDATION_ERR_CHECK.accounting_date
  is '�������� ����';
comment on column TMP_LIQUIDATION_ERR_CHECK.traffic_flow
  is 'ҵ����ˮ ����';
comment on column TMP_LIQUIDATION_ERR_CHECK.lending_direction
  is '������� D���� C����';
comment on column TMP_LIQUIDATION_ERR_CHECK.transaction_amount
  is '���׽�� ��λ��Ԫ����λС����';
comment on column TMP_LIQUIDATION_ERR_CHECK.account_number
  is '�˻����';
comment on column TMP_LIQUIDATION_ERR_CHECK.reconciliation
  is '���˽�� 1�� ƽ
2�� cost��
3.�� cost��';
comment on column TMP_LIQUIDATION_ERR_CHECK.syndicated_number
  is '���ű�� cost��ʱ��Ϊ��';
comment on column TMP_LIQUIDATION_ERR_CHECK.bank_number
  is '���к� cost��ʱ��Ϊ��';
comment on column TMP_LIQUIDATION_ERR_CHECK.mechanism_number
  is '������ Cost��ʱ��Ϊ��';
comment on column TMP_LIQUIDATION_ERR_CHECK.iou_number
  is '��ݺ�';
comment on column TMP_LIQUIDATION_ERR_CHECK.loan_status
  is '����״̬ I ע�ᵫδ��A �״̬activeR ����չ��T ��ֹterminateF ���finishS ����systoleC ����cancel';

prompt Creating TMP_LIQUIDATION_ERR_HEAD_CHECK...
create table TMP_LIQUIDATION_ERR_HEAD_CHECK
(
  check_date     VARCHAR2(8) not null,
  operation_flow VARCHAR2(12),
  total_debit    NUMBER(21,2),
  total_credit   NUMBER(21,2),
  total_number   NUMBER(5)
)
;
comment on table TMP_LIQUIDATION_ERR_HEAD_CHECK
  is '����ƽ̨β������ļ�ͷ��Ϣ';
comment on column TMP_LIQUIDATION_ERR_HEAD_CHECK.check_date
  is '��������';
comment on column TMP_LIQUIDATION_ERR_HEAD_CHECK.operation_flow
  is '������ˮ';
comment on column TMP_LIQUIDATION_ERR_HEAD_CHECK.total_debit
  is '�ۼ��ܶ�';
comment on column TMP_LIQUIDATION_ERR_HEAD_CHECK.total_credit
  is '�������ܶ� ��λ��Ԫ����λС����';
comment on column TMP_LIQUIDATION_ERR_HEAD_CHECK.total_number
  is '������';

prompt Creating TMP_LIQUIDATION_SUB_CHECK...
create table TMP_LIQUIDATION_SUB_CHECK
(
  check_date              VARCHAR2(8) not null,
  accounting_date         VARCHAR2(8) not null,
  package_subject         VARCHAR2(20) not null,
  micro_subjects          VARCHAR2(60),
  lending_direction       VARCHAR2(1) not null,
  daily_occurrence        NUMBER(16,2),
  transitional_amount     NUMBER(16,2),
  flow_occurrence_amount  NUMBER(16,2),
  micro_balance           NUMBER(16,2),
  micro_balance_direction VARCHAR2(1),
  day_balance             NUMBER(16,2),
  day_balance_direction   VARCHAR2(1),
  core_balance            NUMBER(16,2),
  check_ledger            VARCHAR2(1),
  balance_results         VARCHAR2(1),
  difference              NUMBER(16,2),
  cost_occurrence_amount  NUMBER(19,2),
  credit_balance          NUMBER(19,2),
  debit_balance           NUMBER(19,2)
)
;
comment on table TMP_LIQUIDATION_SUB_CHECK
  is '����ƽ̨β������ļ�';
comment on column TMP_LIQUIDATION_SUB_CHECK.check_date
  is '��������';
comment on column TMP_LIQUIDATION_SUB_CHECK.accounting_date
  is '��������';
comment on column TMP_LIQUIDATION_SUB_CHECK.package_subject
  is '���̿�Ŀ';
comment on column TMP_LIQUIDATION_SUB_CHECK.micro_subjects
  is '΢�ڿ�Ŀ';
comment on column TMP_LIQUIDATION_SUB_CHECK.lending_direction
  is '������� D����C����';
comment on column TMP_LIQUIDATION_SUB_CHECK.daily_occurrence
  is '�ձ������Ԫ����λС����';
comment on column TMP_LIQUIDATION_SUB_CHECK.transitional_amount
  is '�����˽�Ԫ����λС����';
comment on column TMP_LIQUIDATION_SUB_CHECK.flow_occurrence_amount
  is '���˷����Ԫ����λС����';
comment on column TMP_LIQUIDATION_SUB_CHECK.micro_balance
  is '΢����Ԫ����λС����';
comment on column TMP_LIQUIDATION_SUB_CHECK.micro_balance_direction
  is '΢������ D����C����';
comment on column TMP_LIQUIDATION_SUB_CHECK.day_balance
  is '����������Ԫ����λС����';
comment on column TMP_LIQUIDATION_SUB_CHECK.day_balance_direction
  is '��������D����C����';
comment on column TMP_LIQUIDATION_SUB_CHECK.core_balance
  is '����Ԫ����λС����';
comment on column TMP_LIQUIDATION_SUB_CHECK.check_ledger
  is '������˶Խ��1 ƽ2 ���˶�3 ������';
comment on column TMP_LIQUIDATION_SUB_CHECK.balance_results
  is '�����˽��1 ƽ2 ���Ķ�3 ������';
comment on column TMP_LIQUIDATION_SUB_CHECK.difference
  is '�������Ԫ����λС���������Ƿ�ƽ���ˡ�Ϊ1ʱΪ����';

prompt Creating TMP_LIQUIDATION_SUB_HEAD_CHECK...
create table TMP_LIQUIDATION_SUB_HEAD_CHECK
(
  check_date                VARCHAR2(8) not null,
  operation_flow            VARCHAR2(12),
  total_number              NUMBER(5),
  successful_identification NUMBER(1)
)
;
comment on column TMP_LIQUIDATION_SUB_HEAD_CHECK.check_date
  is '��������';
comment on column TMP_LIQUIDATION_SUB_HEAD_CHECK.operation_flow
  is '������ˮ';
comment on column TMP_LIQUIDATION_SUB_HEAD_CHECK.total_number
  is '�ܼ�¼��';
comment on column TMP_LIQUIDATION_SUB_HEAD_CHECK.successful_identification
  is '���˳ɹ���ʶ1 �ɹ� 0ʧ��';

prompt Creating TMP_LIQUIDATION_TAIL_CHECK...
create table TMP_LIQUIDATION_TAIL_CHECK
(
  syndicated_number      VARCHAR2(8),
  check_date             VARCHAR2(8) not null,
  accounting_date        VARCHAR2(8) not null,
  traffic_flow           VARCHAR2(32) not null,
  lending_direction      VARCHAR2(1),
  cost_occurrence_amount NUMBER(16,2),
  flow_occurrence_amount NUMBER(16,2),
  difference             NUMBER(8,2)
)
;
comment on column TMP_LIQUIDATION_TAIL_CHECK.syndicated_number
  is '���ű��';
comment on column TMP_LIQUIDATION_TAIL_CHECK.check_date
  is '��������';
comment on column TMP_LIQUIDATION_TAIL_CHECK.accounting_date
  is '��������';
comment on column TMP_LIQUIDATION_TAIL_CHECK.traffic_flow
  is 'ҵ����ˮ';
comment on column TMP_LIQUIDATION_TAIL_CHECK.lending_direction
  is '�������';
comment on column TMP_LIQUIDATION_TAIL_CHECK.cost_occurrence_amount
  is '��ַ�����';
comment on column TMP_LIQUIDATION_TAIL_CHECK.flow_occurrence_amount
  is '��Ʒ�¼������';
comment on column TMP_LIQUIDATION_TAIL_CHECK.difference
  is '���';

prompt Creating TMP_LIQUIDATION_TAL_HEAD_CHECK...
create table TMP_LIQUIDATION_TAL_HEAD_CHECK
(
  check_date     VARCHAR2(8) not null,
  operation_flow VARCHAR2(12),
  total_number   VARCHAR2(5)
)
;
comment on table TMP_LIQUIDATION_TAL_HEAD_CHECK
  is '����ƽ̨β������ļ�ͷ��Ϣ';
comment on column TMP_LIQUIDATION_TAL_HEAD_CHECK.check_date
  is '��������';
comment on column TMP_LIQUIDATION_TAL_HEAD_CHECK.operation_flow
  is '������ˮ';
comment on column TMP_LIQUIDATION_TAL_HEAD_CHECK.total_number
  is '�ܼ�¼��';

prompt Creating TMP_UNIONPAY_CLTN_CHECK...
create table TMP_UNIONPAY_CLTN_CHECK
(
  check_date   VARCHAR2(8) not null,
  merchant_cd  VARCHAR2(20),
  merchant_dt  VARCHAR2(40) not null,
  billno       VARCHAR2(40) not null,
  acctno       VARCHAR2(32),
  trans_amt    NUMBER(19,2),
  chk_status   CHAR(1),
  fee_amt      NUMBER(19,2),
  sign_status  CHAR(1),
  check_status CHAR(1) not null
)
;
comment on table TMP_UNIONPAY_CLTN_CHECK
  is 'UnionPay���۲��˶�';
comment on column TMP_UNIONPAY_CLTN_CHECK.check_date
  is '�˶�����';
comment on column TMP_UNIONPAY_CLTN_CHECK.merchant_cd
  is '�̻���';
comment on column TMP_UNIONPAY_CLTN_CHECK.merchant_dt
  is '�̻�����';
comment on column TMP_UNIONPAY_CLTN_CHECK.billno
  is '������';
comment on column TMP_UNIONPAY_CLTN_CHECK.acctno
  is '�����˺�';
comment on column TMP_UNIONPAY_CLTN_CHECK.trans_amt
  is '���۽��';
comment on column TMP_UNIONPAY_CLTN_CHECK.chk_status
  is '���˽��
1-ƽ
2-���ж�
3-������';
create index TMP_UNIONPAY_CLTN_CHECK_IDX1 on TMP_UNIONPAY_CLTN_CHECK (CHECK_DATE);
alter table TMP_UNIONPAY_CLTN_CHECK
  add constraint PK_TMP_UNIONPAY_CLTN_CHECK primary key (CHECK_DATE, MERCHANT_DT, BILLNO);

prompt Creating TMP_UNIONPAY_CLTN_HEAD_CHECK...
create table TMP_UNIONPAY_CLTN_HEAD_CHECK
(
  check_date   VARCHAR2(8) not null,
  fee          NUMBER(21,2),
  amount       NUMBER(21,2),
  check_status CHAR(1)
)
;
comment on table TMP_UNIONPAY_CLTN_HEAD_CHECK
  is 'ChinaPay���۲��ͷ��Ϣ�˶�';
comment on column TMP_UNIONPAY_CLTN_HEAD_CHECK.check_date
  is '�˶�����';
comment on column TMP_UNIONPAY_CLTN_HEAD_CHECK.fee
  is '������';
alter table TMP_UNIONPAY_CLTN_HEAD_CHECK
  add constraint PK_TMP_UNIONPAY_CLTN primary key (CHECK_DATE);

prompt Creating TMP_UNIONPAY_PAY_CHECK...
create table TMP_UNIONPAY_PAY_CHECK
(
  check_date   VARCHAR2(8) not null,
  merchant_cd  VARCHAR2(20),
  merchant_dt  VARCHAR2(8) not null,
  billno       VARCHAR2(40) not null,
  acctno       VARCHAR2(32),
  trans_amt    NUMBER(19,2),
  fee_amt      NUMBER(19,2),
  core_date    VARCHAR2(10),
  core_seqno   VARCHAR2(40),
  chk_status   CHAR(1),
  sign_status  CHAR(1),
  check_status CHAR(1) not null
)
;
comment on table TMP_UNIONPAY_PAY_CHECK
  is 'UnionPay�������˶�';
comment on column TMP_UNIONPAY_PAY_CHECK.check_date
  is '�˶�����';
comment on column TMP_UNIONPAY_PAY_CHECK.merchant_cd
  is '�̻���';
comment on column TMP_UNIONPAY_PAY_CHECK.merchant_dt
  is '�̻�����';
comment on column TMP_UNIONPAY_PAY_CHECK.billno
  is '������';
comment on column TMP_UNIONPAY_PAY_CHECK.acctno
  is '�տ��˺�';
comment on column TMP_UNIONPAY_PAY_CHECK.trans_amt
  is '���';
comment on column TMP_UNIONPAY_PAY_CHECK.fee_amt
  is '������';
comment on column TMP_UNIONPAY_PAY_CHECK.core_date
  is '��������';
comment on column TMP_UNIONPAY_PAY_CHECK.core_seqno
  is '������ˮ';
comment on column TMP_UNIONPAY_PAY_CHECK.chk_status
  is '���˽��
1-ƽ
2-���ж�
3-������';
comment on column TMP_UNIONPAY_PAY_CHECK.sign_status
  is '��ǩ���
0-ͨ��
1-��ͨ��';
comment on column TMP_UNIONPAY_PAY_CHECK.check_status
  is '����״̬
Y-�Ѵ���
N-δ����';
create index TMP_UNIONPAY_PAY_CHECK_IDX1 on TMP_UNIONPAY_PAY_CHECK (CHECK_DATE);
alter table TMP_UNIONPAY_PAY_CHECK
  add constraint PK_TMP_UNIONPAY_PAY_CHECK primary key (CHECK_DATE, MERCHANT_DT, BILLNO);

prompt Creating TMP_UNIONPAY_PAY_HEAD_CHECK...
create table TMP_UNIONPAY_PAY_HEAD_CHECK
(
  check_date   VARCHAR2(8) not null,
  fee          NUMBER(21,2),
  amount       NUMBER(21,2),
  check_status CHAR(1)
)
;
comment on table TMP_UNIONPAY_PAY_HEAD_CHECK
  is 'ChinaPay�������ͷ��Ϣ�˶�';
comment on column TMP_UNIONPAY_PAY_HEAD_CHECK.check_date
  is '�˶�����';
comment on column TMP_UNIONPAY_PAY_HEAD_CHECK.fee
  is '������';
alter table TMP_UNIONPAY_PAY_HEAD_CHECK
  add constraint PK_TMP_UNIONPAY_PAY_HEAD_CHECK primary key (CHECK_DATE);

prompt Creating TMP_YQRX_AMOU...
create table TMP_YQRX_AMOU
(
  amouid VARCHAR2(32),
  acctno VARCHAR2(16),
  payeac VARCHAR2(32),
  payena VARCHAR2(120),
  tranam VARCHAR2(12),
  crcycd VARCHAR2(2),
  chgeam VARCHAR2(20),
  pswdtp VARCHAR2(12),
  pwflag VARCHAR2(2),
  tranpw VARCHAR2(20),
  remark VARCHAR2(64),
  banknm VARCHAR2(120),
  provic VARCHAR2(20),
  garden VARCHAR2(20),
  ftbkcd VARCHAR2(10),
  acctpr VARCHAR2(5),
  chnlcd VARCHAR2(15),
  pytype VARCHAR2(64),
  cometp VARCHAR2(2),
  states VARCHAR2(2),
  amoudt VARCHAR2(8)
)
;
comment on table TMP_YQRX_AMOU
  is '��Ǯ���ų�����ʱ��';
comment on column TMP_YQRX_AMOU.amouid
  is '���к�';
comment on column TMP_YQRX_AMOU.acctno
  is '�����˺�';
comment on column TMP_YQRX_AMOU.payeac
  is '�տ����˺�';
comment on column TMP_YQRX_AMOU.payena
  is '�տ����˻�����';
comment on column TMP_YQRX_AMOU.tranam
  is 'ת�˽��';
comment on column TMP_YQRX_AMOU.crcycd
  is '����';
comment on column TMP_YQRX_AMOU.chgeam
  is '������';
comment on column TMP_YQRX_AMOU.pswdtp
  is '���ܷ�ʽ';
comment on column TMP_YQRX_AMOU.pwflag
  is '�Ƿ����';
comment on column TMP_YQRX_AMOU.tranpw
  is '��������';
comment on column TMP_YQRX_AMOU.remark
  is '��ע��Ϣ';
comment on column TMP_YQRX_AMOU.banknm
  is '�տ��˿�������';
comment on column TMP_YQRX_AMOU.provic
  is 'ʡ��';
comment on column TMP_YQRX_AMOU.garden
  is '����';
comment on column TMP_YQRX_AMOU.ftbkcd
  is '���д���';
comment on column TMP_YQRX_AMOU.acctpr
  is '�˻�����';
comment on column TMP_YQRX_AMOU.chnlcd
  is '��������';
comment on column TMP_YQRX_AMOU.pytype
  is '֧����ʽ';
comment on column TMP_YQRX_AMOU.cometp
  is '��Դ����1-ʧ�ܽ���2-�ؿ�3-CP�ļ�';
comment on column TMP_YQRX_AMOU.states
  is '����״̬0-�Ѵ���1-δ����';
comment on column TMP_YQRX_AMOU.amoudt
  is '����';
