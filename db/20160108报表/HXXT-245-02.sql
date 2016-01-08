--����
insert into ap_sys_trans (TRANS_CD, TRANS_NAME, SERVICE_CD, ENCAP_CD, TRANS_STATUS, FILE_PATH, FILE_TYPE, FILE_PREFIX, FILE_SPLIT, FILE_SUFFIX, DEAL_CNT, CAL_FLAG, CAL_CHAR_AT, BEGIN_LINE)
values ('fczrdt', '��ѯת����Ŀ��ϸ', 'sunflow', 'fczrdt', 'Y', null, null, null, null, null, null, null, null, null);

--���ķ�װ
insert into ap_sys_encap (ENCAP_CD, ENCAP_REMARK, REQ_MSG, RSP_MSG)
values ('fczrdt', '��ѯת����Ŀ��ϸ', 'fczrdtreq', 'fczrdtrsp');


--���ĸ�ʽ��װ
insert into ap_sys_msg (MSG_CD, MSG_REMARK, MSG_DEFINE, SPLIT_CHAR, MSG_LEN, HEAD_LENGTH, HEAD_MSG, XML_CODING)
values ('fczrdtreq', '��ѯת����Ŀ��������', 'J', null, '1', 6, 'sunflowhead', null);

insert into ap_sys_msg (MSG_CD, MSG_REMARK, MSG_DEFINE, SPLIT_CHAR, MSG_LEN, HEAD_LENGTH, HEAD_MSG, XML_CODING)
values ('fczrdtrsp', '��ѯת����Ŀ����������', 'J', null, '1', 6, 'sunflowhead', null); 
insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('mntrsq', '��������ˮ', null, 'transq', 'S', null, null, null, 'N', 1, 'comm_req', null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('corpno', '���˺�', null, 'corpno', 'S', null, null, null, 'N', 2, 'comm_req', null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('tranus', '���׹�Ա', null, 'userid', 'S', null, null, null, 'N', 3, 'comm_req', null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('tranbr', '���׻���', null, 'brchcd', 'S', null, null, null, 'N', 4, 'comm_req', null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('custac', '�����˺�', null, 'custac', 'S', null, null, null, 'N', 1, 'input', null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('debtcd', 'ת�ñ�ı��', null, 'debtcd', 'S', null, null, null, 'N', 2, 'input', null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('pageno', 'ҳ��', null, 'pageno', 'Z', null, null, null, 'N', 3, 'input', null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('record', 'ÿҳ��¼��', null, 'record', 'Z', null, null, null, 'N', 4, 'input', null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('sequen', '˳���', null, 'sequen', 'S', null, null, null, 'N', 5, 'input', null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('prcscd', '���״�����', null, 'prcscd', 'S', null, null, null, 'N', 1, 'sys', null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('input', '����������', null, null, null, null, null, null, 'O', 1, null, null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('comm_req', '���󹫹���', null, null, null, null, null, null, 'O', 2, null, null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('sys', '����ϵͳ��', null, null, null, null, null, null, 'O', 3, null, null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('output', '����������', null, null, null, null, null, null, 'O', 1, 'content', null, null, 'fczrdtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('tranif', 'ת����Ŀ��ϸ', null, 'tranif', 'S', null, null, null, 'Y', 1, 'output', null, null, 'fczrdtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('pageno', 'ҳ��', null, 'pageno', 'S', null, null, null, 'N', 2, 'output', null, null, 'fczrdtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('counts', '������', null, 'counts', 'S', null, null, null, 'N', 3, 'output', null, null, 'fczrdtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('loantm', '�׷��û���', null, 'loantm', 'S', null, null, null, 'N', 1, 'tranif', null, null, 'fczrdtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('invtam', '�׷����ý��', null, 'invtam', 'F', null, null, null, 'N', 2, 'tranif', null, null, 'fczrdtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('trandt', '�ʲ�ת����Ч��', null, 'trandt', 'S', null, null, null, 'N', 3, 'tranif', null, null, 'fczrdtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('content', '��������������', null, null, null, null, null, null, 'O', 1, null, null, null, 'fczrdtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('retCode', '������', null, 'retCode', 'S', null, null, null, 'N', 2, null, null, null, 'fczrdtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('retMsg', '������Ϣ', null, 'retMsg', 'S', null, null, null, 'N', 3, null, null, null, 'fczrdtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('rqId', '����ID', null, 'rqId', 'S', null, null, null, 'N', 4, null, null, null, 'fczrdtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('status', '״̬', null, 'status', 'S', null, null, null, 'N', 5, null, null, null, 'fczrdtrsp');

 