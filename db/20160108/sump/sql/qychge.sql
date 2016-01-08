--------------------------------------------ɾ����������---------------------------------------------
DELETE FROM AP_SYS_TRANS WHERE trans_cd = 'qychge';
DELETE FROM AP_SYS_ENCAP WHERE encap_cd = 'qychge';
DELETE FROM AP_SYS_MSG WHERE msg_cd = 'qychgereq';
DELETE FROM AP_SYS_MSG WHERE msg_cd = 'qychgersp';
DELETE FROM ap_sys_column WHERE msg_cd = 'qychgereq';
DELETE FROM ap_sys_column WHERE msg_cd = 'qychgersp';
--------------------------------------------��������---------------------------------------------
insert into AP_SYS_TRANS (trans_cd, trans_name, service_cd, encap_cd, trans_status, file_path, file_type, file_prefix, file_split, file_suffix, deal_cnt, cal_flag)
values ('qychge', '��������Ϣ��ѯ', 'sunflow', 'qychge', 'Y', null, null, null, null, null, null, null);
insert into AP_SYS_ENCAP (encap_cd, encap_remark, req_msg, rsp_msg)
values ('qychge', '��������Ϣ��ѯ', 'qychgereq', 'qychgersp');
insert into AP_SYS_MSG (msg_cd, msg_remark, msg_define, split_char, msg_len, head_length, head_msg, xml_coding)
values ('qychgereq', '��������Ϣ��ѯ����������', 'J', null, '1', 6, 'sunflowhead', null);
insert into AP_SYS_MSG (msg_cd, msg_remark, msg_define, split_char, msg_len, head_length, head_msg, xml_coding)
values ('qychgersp', '��������Ϣ��ѯ���ķ�������', 'J', null, '1', 6, 'sunflowhead', null);

insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('mntrsq','��������ˮ','transq','S','N',1,'comm_req','qychgereq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('corpno','���˺�','corpno','S','N',2,'comm_req','qychgereq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('tranus','���׹�Ա','userid','S','N',3,'comm_req','qychgereq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('tranbr','���׻���','brchcd','S','N',4,'comm_req','qychgereq') ; 

insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('prcscd','���״�����','prcscd','S','N',1,'sys','qychgereq') ; 
--��������req
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('input','����������',null,null,'O',1,null,'qychgereq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('comm_req','���󹫹���',null,null,'O',2,null,'qychgereq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('sys','����ϵͳ��',null,null,'O',3,null,'qychgereq') ;
 

--����
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('chnlnm','��������','chnlnm','S','N',1,'input','qychgereq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('pytype','֧����ʽ','pytype','S','N',2,'input','qychgereq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('pagenm','��ǰҳ��','pagenm','S','N',3,'input','qychgereq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('rcrdnm','ÿҳ��¼��','rcrdnm','S','N',4,'input','qychgereq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('target', '��ʶ', 'target', 'S', 'N', 5, 'input','qychgereq');

--����
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('chnlcd','��������','chnlcd','S','N',1,'listnm','qychgersp') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('efctdt','��Ч����','efctdt','S','N',2,'listnm','qychgersp') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('acctpp','�˻�����','acctpp','S','N',3,'listnm','qychgersp') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('chgetp','�շ�����','chgetp','S','N',4,'listnm','qychgersp') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('staram','�Ʒ���ʼ���','staram','S','N',5,'listnm','qychgersp') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('termam','�Ʒ������','termam','S','N',6,'listnm','qychgersp') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('lowfee','��ͷ��ã������շѷ��ã�','lowfee','S','N',7,'listnm','qychgersp') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('higfee','��߷���','higfee','S','N',8,'listnm','qychgersp') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('rateit','�շѱ���','rateit','S','N',9,'listnm','qychgersp') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('pytype','֧����ʽ','pytype','S','N',10,'listnm','qychgersp') ;
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('target','��ʶ','target','S','N',11,'listnm','qychgersp') ;
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('chnlnm','��������','chnlnm','S','N',12,'listnm','qychgersp') ;
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('orfbdt','ǰ������','orfbdt','S','N',13,'listnm','qychgersp') ;
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('orfbsq','ǰ����ˮ','orfbsq','S','N',14,'listnm','qychgersp') ;


--��������rsp
insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('output', '����������', null, '', '', '', '', '', 'O', 1, 'content', '', '', 'qychgersp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('totanm', '������Ϣ������', null, 'totanm', 'Z', '', '', '', 'N', 2, 'output', '', '', 'qychgersp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('listnm', '������Ϣ����', null, 'listnm', 'S', '', '', '', 'Y', 1, 'output', '', '', 'qychgersp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('content', '��������������', null, '', '', '', '', '', 'O', 1, '', '', '', 'qychgersp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('retCode', '������', null, 'retCode', 'S', '', '', '', 'N', 2, '', '', '', 'qychgersp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('retMsg', '������Ϣ', null, 'retMsg', 'S', '', '', '', 'N', 3, '', '', '', 'qychgersp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('rqId', '����ID', null, 'rqId', 'S', '', '', '', 'N', 4, '', '', '', 'qychgersp');

commit;
