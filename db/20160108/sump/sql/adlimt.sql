--------------------------------------------删除报文配置---------------------------------------------
DELETE FROM AP_SYS_TRANS WHERE trans_cd = 'adlimt';
DELETE FROM AP_SYS_ENCAP WHERE encap_cd = 'adlimt';
DELETE FROM AP_SYS_MSG WHERE msg_cd = 'adlimtreq';
DELETE FROM AP_SYS_MSG WHERE msg_cd = 'adlimtrsp';
DELETE FROM ap_sys_column WHERE msg_cd = 'adlimtreq';
DELETE FROM ap_sys_column WHERE msg_cd = 'adlimtrsp';
--------------------------------------------报文配置---------------------------------------------
insert into AP_SYS_TRANS (trans_cd, trans_name, service_cd, encap_cd, trans_status, file_path, file_type, file_prefix, file_split, file_suffix, deal_cnt, cal_flag)
values ('adlimt', '限额信息新增', 'sunflow', 'adlimt', 'Y', null, null, null, null, null, null, null);
insert into AP_SYS_ENCAP (encap_cd, encap_remark, req_msg, rsp_msg)
values ('adlimt', '限额信息新增', 'adlimtreq', 'adlimtrsp');
insert into AP_SYS_MSG (msg_cd, msg_remark, msg_define, split_char, msg_len, head_length, head_msg, xml_coding)
values ('adlimtreq', '限额信息新增报文请求报文', 'J', null, '1', 6, 'sunflowhead', null);
insert into AP_SYS_MSG (msg_cd, msg_remark, msg_define, split_char, msg_len, head_length, head_msg, xml_coding)
values ('adlimtrsp', '限额信息新增报文反馈报文', 'J', null, '1', 6, 'sunflowhead', null);

insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('mntrsq','主交易流水','transq','S','N',1,'comm_req','adlimtreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('corpno','法人号','corpno','S','N',2,'comm_req','adlimtreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('tranus','交易柜员','userid','S','N',3,'comm_req','adlimtreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('tranbr','交易机构','brchcd','S','N',4,'comm_req','adlimtreq') ; 

insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('prcscd','交易处理码','prcscd','S','N',1,'sys','adlimtreq') ; 
--公共请求req
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('input','请求输入域',null,null,'O',1,null,'adlimtreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('comm_req','请求公共域',null,null,'O',2,null,'adlimtreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('sys','请求系统域',null,null,'O',3,null,'adlimtreq') ;
--
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('chnlcd','渠道编码','chnlcd','S','N',1,'input','adlimtreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('chnlnm','渠道名称','chnlnm','S','N',2,'input','adlimtreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('ftbkcd','前置规定银行代码','ftbkcd','S','N',3,'input','adlimtreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('bankna','银行名称','bankna','S','N',4,'input','adlimtreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('bankcd','渠道规定银行代码','bankcd','S','N',5,'input','adlimtreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('pypslt','对个人单笔限额','pypslt','S','N',6,'input','adlimtreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('pypdlt','对个人日累限额','pypdlt','S','N',7,'input','adlimtreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('pycslt','对公司单笔限额','pycslt','S','N',8,'input','adlimtreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('pycdlt','对公司日累限额','pycdlt','S','N',9,'input','adlimtreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('pytype','支付方式','pytype','S','N',11,'input','adlimtreq') ; 
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('target','标识','target','S','N',10,'input','adlimtreq') ;
insert into ap_sys_column a (a.column_cd,a.column_name,a.column_mapping,a.column_type,a.cycling_flag,a.sort_seq,a.cycling_column,a.msg_cd)
values('transt','状态','transt','S','N',11,'input','adlimtreq') ; 

--add公共rsp
insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('output', '反馈输入域', null, '', '', '', '', '', 'O', 1, 'content', '', '', 'adlimtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('content', '反馈报文内容域', null, '', '', '', '', '', 'O', 1, '', '', '', 'adlimtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('retCode', '错误码', null, 'retCode', 'S', '', '', '', 'N', 2, '', '', '', 'adlimtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('retMsg', '错误信息', null, 'retMsg', 'S', '', '', '', 'N', 3, '', '', '', 'adlimtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('rqId', '请求ID', null, 'rqId', 'S', '', '', '', 'N', 4, '', '', '', 'adlimtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('status', '状态', null, 'status', 'S', '', '', '', 'N', 5, '', '', '', 'adlimtrsp');

commit;