--------------------------------------------删除报文配置---------------------------------------------
DELETE FROM AP_SYS_TRANS WHERE trans_cd = 'qychnl';
DELETE FROM AP_SYS_ENCAP WHERE encap_cd = 'qychnl';
DELETE FROM AP_SYS_MSG WHERE msg_cd = 'qychnlreq';
DELETE FROM AP_SYS_MSG WHERE msg_cd = 'qychnlrsp';
DELETE FROM ap_sys_column WHERE msg_cd = 'qychnlreq';
DELETE FROM ap_sys_column WHERE msg_cd = 'qychnlrsp';
--------------------------------------------报文配置---------------------------------------------
insert into ap_sys_encap (ENCAP_CD, ENCAP_REMARK, REQ_MSG, RSP_MSG)
values ('qychnl', '支付渠道管理', 'qychnlreq', 'qychnlrsp');

insert into ap_sys_msg (MSG_CD, MSG_REMARK, MSG_DEFINE, SPLIT_CHAR, MSG_LEN, HEAD_LENGTH, HEAD_MSG, XML_CODING)
values ('qychnlrsp', '支付渠道管理报文反馈报文', 'J', null, '1', 6, 'sunflowhead', null);

insert into ap_sys_msg (MSG_CD, MSG_REMARK, MSG_DEFINE, SPLIT_CHAR, MSG_LEN, HEAD_LENGTH, HEAD_MSG, XML_CODING)
values ('qychnlreq', '支付渠道管理报文请求报文', 'J', null, '1', 6, 'sunflowhead', null);

insert into ap_sys_trans (TRANS_CD, TRANS_NAME, SERVICE_CD, ENCAP_CD, TRANS_STATUS, FILE_PATH, FILE_TYPE, FILE_PREFIX, FILE_SPLIT, FILE_SUFFIX, DEAL_CNT, CAL_FLAG, CAL_CHAR_AT, BEGIN_LINE)
values ('qychnl', '支付渠道管理', 'sunflow', 'qychnl', 'Y', null, null, null, null, null, null, null, null, null);

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('rcrdnm', '每页记录数', null, 'rcrdnm', 'S', null, null, null, 'N', 3, 'input', null, null, 'qychnlreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('chnlnm', '渠道名称', null, 'chnlnm', 'S', null, null, null, 'N', 2, 'input', null, null, 'qychnlreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('status', '渠道状态', null, 'status', 'S', null, null, null, 'N', 1, 'input', null, null, 'qychnlreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('pagenm', '当前页数', null, 'pagenm', 'S', null, null, null, 'N', 4, 'input', null, null, 'qychnlreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('target', '标识', null, 'target', 'S', null, null, null, 'N', 5, 'input', null, null, 'qychnlreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('prcscd', '交易处理码', null, 'prcscd', 'S', null, null, null, 'N', 1, 'sys', null, null, 'qychnlreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('sys', '请求系统域', null, null, null, null, null, null, 'O', 3, null, null, null, 'qychnlreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('comm_req', '请求公共域', null, null, null, null, null, null, 'O', 2, null, null, null, 'qychnlreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('input', '请求输入域', null, null, null, null, null, null, 'O', 1, null, null, null, 'qychnlreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('totanm', '反馈信息总条数', null, 'totanm', 'Z', '', '', '', 'N', 1, 'output', '', '', 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('listnm', '反馈信息数据', null, 'listnm', 'S', null, null, null, 'Y', 2, 'output', null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('chnlcd', '渠道编码', null, 'chnlcd', 'S', null, null, null, 'N', 1, 'listnm', null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('chnlnm', '渠道名称', null, 'chnlnm', 'S', null, null, null, 'N', 2, 'listnm', null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('mercid', '商户号', null, 'mercid', 'S', null, null, null, 'N', 3, 'listnm', null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('status', '状态', null, 'status', 'S', null, null, null, 'N', 4, 'listnm', null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('isdefl', '是否默认', null, 'isdefl', 'S', null, null, null, 'N', 5, 'listnm', null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('prioty', '优先级', null, 'prioty', 'S', null, null, null, 'N', 6, 'listnm', null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('remark', '备注', null, 'remark', 'S', null, null, null, 'N', 7, 'listnm', null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('creatm', '创建时间', null, 'creatm', 'S', null, null, null, 'N', 8, 'listnm', null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('mduser', '修改人', null, 'mduser', 'S', null, null, null, 'N', 9, 'listnm', null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('modydt', '修改时间', null, 'modydt', 'S', null, null, null, 'N', 10, 'listnm', null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('content', '反馈报文内容域', null, null, null, null, null, null, 'O', 1, null, null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('rqId', '请求ID', null, 'rqId', 'S', null, null, null, 'N', 4, null, null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('retCode', '错误码', null, 'retCode', 'S', null, null, null, 'N', 2, null, null, null, 'qychnlrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('retMsg', '错误信息', null, 'retMsg', 'S', null, null, null, 'N', 3, null, null, null, 'qychnlrsp');

--公共反馈rsp
insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('output', '反馈输入域', null, '', '', '', '', '', 'O', 1, 'content', '', '', 'qychnlrsp');

commit;
