--------------------------------------------删除报文配置---------------------------------------------
DELETE FROM AP_SYS_TRANS WHERE trans_cd = 'rmchge';
DELETE FROM AP_SYS_ENCAP WHERE encap_cd = 'rmchge';
DELETE FROM AP_SYS_MSG WHERE msg_cd = 'rmchgereq';
DELETE FROM AP_SYS_MSG WHERE msg_cd = 'rmchgersp';
DELETE FROM ap_sys_column WHERE msg_cd = 'rmchgereq';
DELETE FROM ap_sys_column WHERE msg_cd = 'rmchgersp';
--------------------------------------------报文配置---------------------------------------------
insert into AP_SYS_TRANS (trans_cd, trans_name, service_cd, encap_cd, trans_status, file_path, file_type, file_prefix, file_split, file_suffix, deal_cnt, cal_flag)
values ('rmchge', '手续费信息删除', 'sunflow', 'rmchge', 'Y', null, null, null, null, null, null, null);
insert into AP_SYS_ENCAP (encap_cd, encap_remark, req_msg, rsp_msg)
values ('rmchge', '手续费信息删除', 'rmchgereq', 'rmchgersp');
insert into AP_SYS_MSG (msg_cd, msg_remark, msg_define, split_char, msg_len, head_length, head_msg, xml_coding)
values ('rmchgereq', '手续费信息删除报文请求报文', 'J', null, '1', 6, 'sunflowhead', null);
insert into AP_SYS_MSG (msg_cd, msg_remark, msg_define, split_char, msg_len, head_length, head_msg, xml_coding)
values ('rmchgersp', '手续费信息删除报文反馈报文', 'J', null, '1', 6, 'sunflowhead', null);

insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('mntrsq','主交易流水','transq','S','N',1,'comm_req','rmchgereq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('corpno','法人号','corpno','S','N',2,'comm_req','rmchgereq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('tranus','交易柜员','userid','S','N',3,'comm_req','rmchgereq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('tranbr','交易机构','brchcd','S','N',4,'comm_req','rmchgereq') ; 

insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('prcscd','交易处理码','prcscd','S','N',1,'sys','rmchgereq') ; 
--公共请求req
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('input','请求输入域',null,null,'O',1,null,'rmchgereq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('comm_req','请求公共域',null,null,'O',2,null,'rmchgereq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('sys','请求系统域',null,null,'O',3,null,'rmchgereq') ;
--
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('orfbdt','前置日期','orfbdt','S','N',1,'input','rmchgereq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('target','标记','target','S','N',2,'input','rmchgereq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('orfbsq','前置流水','orfbsq','S','N',3,'input','rmchgereq') ;

--add公共rsp
insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('output', '反馈输入域', null, '', '', '', '', '', 'O', 1, 'content', '', '', 'rmchgersp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('content', '反馈报文内容域', null, '', '', '', '', '', 'O', 1, '', '', '', 'rmchgersp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('retCode', '错误码', null, 'retCode', 'S', '', '', '', 'N', 2, '', '', '', 'rmchgersp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('retMsg', '错误信息', null, 'retMsg', 'S', '', '', '', 'N', 3, '', '', '', 'rmchgersp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('rqId', '请求ID', null, 'rqId', 'S', '', '', '', 'N', 4, '', '', '', 'rmchgersp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('status', '状态', null, 'status', 'S', '', '', '', 'N', 5, '', '', '', 'rmchgersp');

commit;


