prompt Importing table ap_sys_dict...
set feedback off
set define off

DELETE FROM ap_sys_dict WHERE dict_type = 'PayChannel';
DELETE FROM ap_sys_dict WHERE dict_type = 'E_PYTYPE';
DELETE FROM ap_sys_dict WHERE dict_type = 'E_ACCTPP';
DELETE FROM ap_sys_dict WHERE dict_type = 'E_CHGETP';
DELETE FROM ap_sys_dict WHERE dict_type = 'E_TRANST';

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('PayChannel', '00', '不可用', null, null, 'Y', 1);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('PayChannel', '01', '可用', null, null, 'Y', 2);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_PYTYPE', '4', '代付', null, null, 'Y', 4);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_PYTYPE', '3', '快捷', null, null, 'Y', 3);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_PYTYPE', '2', '认证', null, null, 'Y', 2);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_PYTYPE', '1', '网关', null, null, 'Y', 1);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_ACCTPP', '0', '个人', null, null, 'Y', 1);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_ACCTPP', '1', '公司', null, null, 'Y', 2);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_ACCTPP', '2', '个人和公司', null, null, 'Y', 3);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_CHGETP', 'B', '按单比收费', null, null, 'Y', 1);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_CHGETP', 'P', '按比例收费', null, null, 'Y', 2);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_TRANST', '01', '修改单条记录', null, null, 'Y', 1);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_TRANST', '02', '修改所以银行代码记录', null, null, 'Y', 2);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_TRANST', '03', '修改所以支付方式记录', null, null, 'Y', 3);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, Parent_Dict_Type, Parent_Dict_Id, Status, Sort_No)  values ('F_ADDRUL', '0', '金额不限制', null, null, 'Y', '1'); 
insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, Parent_Dict_Type, Parent_Dict_Id, Status, Sort_No)  values ('F_ADDRUL', '1', '金额限制', null, null, 'Y', '2');

prompt Done.
commit;
