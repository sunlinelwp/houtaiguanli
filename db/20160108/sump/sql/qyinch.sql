--------------------------------------------ɾ����������---------------------------------------------
DELETE FROM AP_SYS_TRANS WHERE trans_cd = 'qyinch';
DELETE FROM AP_SYS_ENCAP WHERE encap_cd = 'qyinch';
DELETE FROM AP_SYS_MSG WHERE msg_cd = 'qyinchreq';
DELETE FROM AP_SYS_MSG WHERE msg_cd = 'qyinchrsp';
DELETE FROM ap_sys_column WHERE msg_cd = 'qyinchreq';
DELETE FROM ap_sys_column WHERE msg_cd = 'qyinchrsp';
--------------------------------------------��������---------------------------------------------
insert into AP_SYS_TRANS (trans_cd, trans_name, service_cd, encap_cd, trans_status, file_path, file_type, file_prefix, file_split, file_suffix, deal_cnt, cal_flag)
values ('qyinch', '����������Ϣ��ѯ', 'sunflow', 'qyinch', 'Y', null, null, null, null, null, null, null);
insert into AP_SYS_ENCAP (encap_cd, encap_remark, req_msg, rsp_msg)
values ('qyinch', '����������Ϣ��ѯ', 'qyinchreq', 'qyinchrsp');
insert into AP_SYS_MSG (msg_cd, msg_remark, msg_define, split_char, msg_len, head_length, head_msg, xml_coding)
values ('qyinchreq', '����������Ϣ��ѯ����������', 'J', null, '1', 6, 'sunflowhead', null);
insert into AP_SYS_MSG (msg_cd, msg_remark, msg_define, split_char, msg_len, head_length, head_msg, xml_coding)
values ('qyinchrsp', '����������Ϣ��ѯ���ķ�������', 'J', null, '1', 6, 'sunflowhead', null);

insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('mntrsq','��������ˮ','transq','S','N',1,'comm_req','qyinchreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('corpno','���˺�','corpno','S','N',2,'comm_req','qyinchreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('tranus','���׹�Ա','userid','S','N',3,'comm_req','qyinchreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('tranbr','���׻���','brchcd','S','N',4,'comm_req','qyinchreq') ; 

insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('prcscd','���״�����','prcscd','S','N',1,'sys','qyinchreq') ; 
--��������req
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('input','����������',null,null,'O',1,null,'qyinchreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('comm_req','���󹫹���',null,null,'O',2,null,'qyinchreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('sys','����ϵͳ��',null,null,'O',3,null,'qyinchreq') ;
 

--����
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('chnlnm','��������','chnlnm','S','N',1,'input','qyinchreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('pagenm','��ǰҳ��','pagenm','S','N',2,'input','qyinchreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('rcrdnm','ÿҳ��¼��','rcrdnm','S','N',3,'input','qyinchreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('target', '��ʶ', 'target', 'S', 'N', 4, 'input','qyinchreq');

--����
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('chnlcd','֧����������','chnlcd','S','N',1,'listnm','qyinchrsp') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('chnlnm','֧����������','chnlnm','S','N',2,'listnm','qyinchrsp') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('cruser','������','cruser','S','N',3,'listnm','qyinchrsp') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('status','����״̬','status','S','N',4,'listnm','qyinchrsp') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('remark','��ע','remark','S','N',5,'listnm','qyinchrsp') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('creatm','����ʱ��','creatm','S','N',6,'listnm','qyinchrsp') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('modydt','�޸�����','modydt','S','N',7,'listnm','qyinchrsp') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('mduser','�޸���','mduser','S','N',8,'listnm','qyinchrsp') ; 


--��������rsp
insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('output', '����������', null, '', '', '', '', '', 'O', 1, 'content', '', '', 'qyinchrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('totanm', '������Ϣ������', null, 'totanm', 'Z', '', '', '', 'N', 2, 'output', '', '', 'qyinchrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('listnm', '������Ϣ����', null, 'listnm', 'S', '', '', '', 'Y', 1, 'output', '', '', 'qyinchrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('content', '��������������', null, '', '', '', '', '', 'O', 1, '', '', '', 'qyinchrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('retCode', '������', null, 'retCode', 'S', '', '', '', 'N', 2, '', '', '', 'qyinchrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('retMsg', '������Ϣ', null, 'retMsg', 'S', '', '', '', 'N', 3, '', '', '', 'qyinchrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('rqId', '����ID', null, 'rqId', 'S', '', '', '', 'N', 4, '', '', '', 'qyinchrsp');

commit;
