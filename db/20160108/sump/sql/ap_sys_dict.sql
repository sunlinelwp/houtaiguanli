prompt Importing table ap_sys_dict...
set feedback off
set define off

DELETE FROM ap_sys_dict WHERE dict_type = 'PayChannel';
DELETE FROM ap_sys_dict WHERE dict_type = 'E_PYTYPE';
DELETE FROM ap_sys_dict WHERE dict_type = 'E_ACCTPP';
DELETE FROM ap_sys_dict WHERE dict_type = 'E_CHGETP';
DELETE FROM ap_sys_dict WHERE dict_type = 'E_TRANST';

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('PayChannel', '00', '������', null, null, 'Y', 1);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('PayChannel', '01', '����', null, null, 'Y', 2);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_PYTYPE', '4', '����', null, null, 'Y', 4);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_PYTYPE', '3', '���', null, null, 'Y', 3);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_PYTYPE', '2', '��֤', null, null, 'Y', 2);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_PYTYPE', '1', '����', null, null, 'Y', 1);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_ACCTPP', '0', '����', null, null, 'Y', 1);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_ACCTPP', '1', '��˾', null, null, 'Y', 2);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_ACCTPP', '2', '���˺͹�˾', null, null, 'Y', 3);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_CHGETP', 'B', '�������շ�', null, null, 'Y', 1);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_CHGETP', 'P', '�������շ�', null, null, 'Y', 2);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_TRANST', '01', '�޸ĵ�����¼', null, null, 'Y', 1);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_TRANST', '02', '�޸��������д����¼', null, null, 'Y', 2);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, PARENT_DICT_TYPE, PARENT_DICT_ID, STATUS, SORT_NO)
values ('E_TRANST', '03', '�޸�����֧����ʽ��¼', null, null, 'Y', 3);

insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, Parent_Dict_Type, Parent_Dict_Id, Status, Sort_No)  values ('F_ADDRUL', '0', '������', null, null, 'Y', '1'); 
insert into ap_sys_dict (DICT_TYPE, DICT_ID, DICT_NAME, Parent_Dict_Type, Parent_Dict_Id, Status, Sort_No)  values ('F_ADDRUL', '1', '�������', null, null, 'Y', '2');

prompt Done.
commit;
