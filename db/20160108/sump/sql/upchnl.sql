--------------------------------------------ɾ����������---------------------------------------------
DELETE FROM AP_SYS_TRANS WHERE trans_cd = 'upchnl';
DELETE FROM AP_SYS_ENCAP WHERE encap_cd = 'upchnl';
DELETE FROM AP_SYS_MSG WHERE msg_cd = 'upchnlreq';
DELETE FROM AP_SYS_MSG WHERE msg_cd = 'upchnlrsp';
DELETE FROM ap_sys_column WHERE msg_cd = 'upchnlreq';
DELETE FROM ap_sys_column WHERE msg_cd = 'upchnlrsp';
--------------------------------------------��������---------------------------------------------
insert into AP_SYS_TRANS (trans_cd, trans_name, service_cd, encap_cd, trans_status, file_path, file_type, file_prefix, file_split, file_suffix, deal_cnt, cal_flag)
values ('upchnl', '֧���������', 'sunflow', 'upchnl', 'Y', null, null, null, null, null, null, null);
insert into AP_SYS_ENCAP (encap_cd, encap_remark, req_msg, rsp_msg)
values ('upchnl', '֧���������', 'upchnlreq', 'upchnlrsp');
insert into AP_SYS_MSG (msg_cd, msg_remark, msg_define, split_char, msg_len, head_length, head_msg, xml_coding)
values ('upchnlreq', '֧��������ӱ���������', 'J', null, '1', 6, 'sunflowhead', null);
insert into AP_SYS_MSG (msg_cd, msg_remark, msg_define, split_char, msg_len, head_length, head_msg, xml_coding)
values ('upchnlrsp', '֧��������ӱ��ķ�������', 'J', null, '1', 6, 'sunflowhead', null);

insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('mntrsq','��������ˮ','transq','S','N',1,'comm_req','upchnlreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('corpno','���˺�','corpno','S','N',2,'comm_req','upchnlreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('tranus','���׹�Ա','userid','S','N',3,'comm_req','upchnlreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('tranbr','���׻���','brchcd','S','N',4,'comm_req','upchnlreq') ; 

insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('prcscd','���״�����','prcscd','S','N',1,'sys','upchnlreq') ; 
--��������req
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('input','����������',null,null,'O',1,null,'upchnlreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('comm_req','���󹫹���',null,null,'O',2,null,'upchnlreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('sys','����ϵͳ��',null,null,'O',3,null,'upchnlreq') ;
--
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('chnlcd','��������','chnlcd','S','N',1,'input','upchnlreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('chnlnm','��������','chnlnm','S','N',2,'input','upchnlreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('mercid','�̻���','mercid','S','N',3,'input','upchnlreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('status','״̬','status','S','N',4,'input','upchnlreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('isdefl','�Ƿ�Ĭ��','isdefl','S','N',5,'input','upchnlreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('prioty','���ȼ�','prioty','S','N',6,'input','upchnlreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('remark','��ע','remark','S','N',7,'input','upchnlreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('target','���','target','S','N',8,'input','upchnlreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('mduser','�޸���','mduser','S','N',9,'input','upchnlreq') ; 


--add����rsp
insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('output', '����������', null, '', '', '', '', '', 'O', 1, 'content', '', '', 'upchnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('content', '��������������', null, '', '', '', '', '', 'O', 1, '', '', '', 'upchnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('retCode', '������', null, 'retCode', 'S', '', '', '', 'N', 2, '', '', '', 'upchnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('retMsg', '������Ϣ', null, 'retMsg', 'S', '', '', '', 'N', 3, '', '', '', 'upchnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('rqId', '����ID', null, 'rqId', 'S', '', '', '', 'N', 4, '', '', '', 'upchnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('status', '״̬', null, 'status', 'S', '', '', '', 'N', 5, '', '', '', 'upchnlrsp');

commit;


