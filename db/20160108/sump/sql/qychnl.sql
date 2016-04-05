--------------------------------------------ɾ����������---------------------------------------------
DELETE FROM AP_SYS_TRANS WHERE trans_cd = 'qychnl';
DELETE FROM AP_SYS_ENCAP WHERE encap_cd = 'qychnl';
DELETE FROM AP_SYS_MSG WHERE msg_cd = 'qychnlreq';
DELETE FROM AP_SYS_MSG WHERE msg_cd = 'qychnlrsp';
DELETE FROM ap_sys_column WHERE msg_cd = 'qychnlreq';
DELETE FROM ap_sys_column WHERE msg_cd = 'qychnlrsp';
--------------------------------------------��������---------------------------------------------
insert into ap_sys_encap (ENCAP_CD, ENCAP_REMARK, REQ_MSG, RSP_MSG)
values ('qychnl', '֧����������', 'qychnlreq', 'qychnlrsp');

insert into ap_sys_msg (MSG_CD, MSG_REMARK, MSG_DEFINE, SPLIT_CHAR, MSG_LEN, HEAD_LENGTH, HEAD_MSG, XML_CODING)
values ('qychnlrsp', '֧�����������ķ�������', 'J', null, '1', 6, 'sunflowhead', null);

insert into ap_sys_msg (MSG_CD, MSG_REMARK, MSG_DEFINE, SPLIT_CHAR, MSG_LEN, HEAD_LENGTH, HEAD_MSG, XML_CODING)
values ('qychnlreq', '֧������������������', 'J', null, '1', 6, 'sunflowhead', null);

insert into ap_sys_trans (TRANS_CD, TRANS_NAME, SERVICE_CD, ENCAP_CD, TRANS_STATUS, FILE_PATH, FILE_TYPE, FILE_PREFIX, FILE_SPLIT, FILE_SUFFIX, DEAL_CNT, CAL_FLAG, CAL_CHAR_AT, BEGIN_LINE)
values ('qychnl', '֧����������', 'sunflow', 'qychnl', 'Y', null, null, null, null, null, null, null, null, null);

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('rcrdnm', 'ÿҳ��¼��', null, 'rcrdnm', 'S', null, null, null, 'N', 3, 'input', null, null, 'qychnlreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('chnlnm', '��������', null, 'chnlnm', 'S', null, null, null, 'N', 2, 'input', null, null, 'qychnlreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('status', '����״̬', null, 'status', 'S', null, null, null, 'N', 1, 'input', null, null, 'qychnlreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('pagenm', '��ǰҳ��', null, 'pagenm', 'S', null, null, null, 'N', 4, 'input', null, null, 'qychnlreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('target', '��ʶ', null, 'target', 'S', null, null, null, 'N', 5, 'input', null, null, 'qychnlreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('prcscd', '���״�����', null, 'prcscd', 'S', null, null, null, 'N', 1, 'sys', null, null, 'qychnlreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('sys', '����ϵͳ��', null, null, null, null, null, null, 'O', 3, null, null, null, 'qychnlreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('comm_req', '���󹫹���', null, null, null, null, null, null, 'O', 2, null, null, null, 'qychnlreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('input', '����������', null, null, null, null, null, null, 'O', 1, null, null, null, 'qychnlreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('totanm', '������Ϣ������', null, 'totanm', 'Z', '', '', '', 'N', 1, 'output', '', '', 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('listnm', '������Ϣ����', null, 'listnm', 'S', null, null, null, 'Y', 2, 'output', null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('chnlcd', '��������', null, 'chnlcd', 'S', null, null, null, 'N', 1, 'listnm', null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('chnlnm', '��������', null, 'chnlnm', 'S', null, null, null, 'N', 2, 'listnm', null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('mercid', '�̻���', null, 'mercid', 'S', null, null, null, 'N', 3, 'listnm', null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('status', '״̬', null, 'status', 'S', null, null, null, 'N', 4, 'listnm', null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('isdefl', '�Ƿ�Ĭ��', null, 'isdefl', 'S', null, null, null, 'N', 5, 'listnm', null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('prioty', '���ȼ�', null, 'prioty', 'S', null, null, null, 'N', 6, 'listnm', null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('remark', '��ע', null, 'remark', 'S', null, null, null, 'N', 7, 'listnm', null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('creatm', '����ʱ��', null, 'creatm', 'S', null, null, null, 'N', 8, 'listnm', null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('mduser', '�޸���', null, 'mduser', 'S', null, null, null, 'N', 9, 'listnm', null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('modydt', '�޸�ʱ��', null, 'modydt', 'S', null, null, null, 'N', 10, 'listnm', null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('content', '��������������', null, null, null, null, null, null, 'O', 1, null, null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('rqId', '����ID', null, 'rqId', 'S', null, null, null, 'N', 4, null, null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('retCode', '������', null, 'retCode', 'S', null, null, null, 'N', 2, null, null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('retMsg', '������Ϣ', null, 'retMsg', 'S', null, null, null, 'N', 3, null, null, null, 'qychnlrsp');

--��������rsp
insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('output', '����������', null, '', '', '', '', '', 'O', 1, 'content', '', '', 'qychnlrsp');

commit;
