--交易
insert into ap_sys_trans (TRANS_CD, TRANS_NAME, SERVICE_CD, ENCAP_CD, TRANS_STATUS, FILE_PATH, FILE_TYPE, FILE_PREFIX, FILE_SPLIT, FILE_SUFFIX, DEAL_CNT, CAL_FLAG, CAL_CHAR_AT, BEGIN_LINE)
values ('fczrdt', '查询转让项目明细', 'sunflow', 'fczrdt', 'Y', null, null, null, null, null, null, null, null, null);

--报文封装
insert into ap_sys_encap (ENCAP_CD, ENCAP_REMARK, REQ_MSG, RSP_MSG)
values ('fczrdt', '查询转让项目明细', 'fczrdtreq', 'fczrdtrsp');


--报文格式封装
insert into ap_sys_msg (MSG_CD, MSG_REMARK, MSG_DEFINE, SPLIT_CHAR, MSG_LEN, HEAD_LENGTH, HEAD_MSG, XML_CODING)
values ('fczrdtreq', '查询转让项目明请求报文', 'J', null, '1', 6, 'sunflowhead', null);

insert into ap_sys_msg (MSG_CD, MSG_REMARK, MSG_DEFINE, SPLIT_CHAR, MSG_LEN, HEAD_LENGTH, HEAD_MSG, XML_CODING)
values ('fczrdtrsp', '查询转让项目明反馈报文', 'J', null, '1', 6, 'sunflowhead', null); 
insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('mntrsq', '主交易流水', null, 'transq', 'S', null, null, null, 'N', 1, 'comm_req', null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('corpno', '法人号', null, 'corpno', 'S', null, null, null, 'N', 2, 'comm_req', null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('tranus', '交易柜员', null, 'userid', 'S', null, null, null, 'N', 3, 'comm_req', null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('tranbr', '交易机构', null, 'brchcd', 'S', null, null, null, 'N', 4, 'comm_req', null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('custac', '电子账号', null, 'custac', 'S', null, null, null, 'N', 1, 'input', null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('debtcd', '转让标的编号', null, 'debtcd', 'S', null, null, null, 'N', 2, 'input', null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('pageno', '页数', null, 'pageno', 'Z', null, null, null, 'N', 3, 'input', null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('record', '每页记录数', null, 'record', 'Z', null, null, null, 'N', 4, 'input', null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('sequen', '顺序号', null, 'sequen', 'S', null, null, null, 'N', 5, 'input', null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('prcscd', '交易处理码', null, 'prcscd', 'S', null, null, null, 'N', 1, 'sys', null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('input', '请求输入域', null, null, null, null, null, null, 'O', 1, null, null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('comm_req', '请求公共域', null, null, null, null, null, null, 'O', 2, null, null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('sys', '请求系统域', null, null, null, null, null, null, 'O', 3, null, null, null, 'fczrdtreq');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('output', '反馈输入域', null, null, null, null, null, null, 'O', 1, 'content', null, null, 'fczrdtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('tranif', '转让项目明细', null, 'tranif', 'S', null, null, null, 'Y', 1, 'output', null, null, 'fczrdtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('pageno', '页码', null, 'pageno', 'S', null, null, null, 'N', 2, 'output', null, null, 'fczrdtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('counts', '总条数', null, 'counts', 'S', null, null, null, 'N', 3, 'output', null, null, 'fczrdtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('loantm', '甲方用户名', null, 'loantm', 'S', null, null, null, 'N', 1, 'tranif', null, null, 'fczrdtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('invtam', '甲方受让金额', null, 'invtam', 'F', null, null, null, 'N', 2, 'tranif', null, null, 'fczrdtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('trandt', '资产转让生效日', null, 'trandt', 'S', null, null, null, 'N', 3, 'tranif', null, null, 'fczrdtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('content', '反馈报文内容域', null, null, null, null, null, null, 'O', 1, null, null, null, 'fczrdtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('retCode', '错误码', null, 'retCode', 'S', null, null, null, 'N', 2, null, null, null, 'fczrdtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('retMsg', '错误信息', null, 'retMsg', 'S', null, null, null, 'N', 3, null, null, null, 'fczrdtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('rqId', '请求ID', null, 'rqId', 'S', null, null, null, 'N', 4, null, null, null, 'fczrdtrsp');

insert into ap_sys_column (COLUMN_CD, COLUMN_NAME, COLUMN_LENGTH, COLUMN_MAPPING, COLUMN_TYPE, VALUE_DIGIT, DATE_PATTERN, DEFAULT_VALUE, CYCLING_FLAG, SORT_SEQ, CYCLING_COLUMN, POLISH_TYPE, POLISH_CHAR, MSG_CD)
values ('status', '状态', null, 'status', 'S', null, null, null, 'N', 5, null, null, null, 'fczrdtrsp');

 