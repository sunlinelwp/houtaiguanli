prompt Importing table sif_sys_auth...
set feedback off
set define off
insert into sif_sys_auth (AUTH_CD, AUTH_TYPE, REGISTER_CD, AUTH_URL, MENU_NAME, PARENT_AUTH_CD, RANK, SORTNO, ICONFG, TARGET_FLAG)
values ('10000044', '2', '001', '/paychannel/counterfeement', '�����ѹ���', '1000004', 3, null, 'fa fa-remove', null);

insert into sif_sys_auth (AUTH_CD, AUTH_TYPE, REGISTER_CD, AUTH_URL, MENU_NAME, PARENT_AUTH_CD, RANK, SORTNO, ICONFG, TARGET_FLAG)
values ('10000046', '2', '001', '/paychannel/accessinformament', '����������Ϣ��ѯ', '1000004', 3, null, 'fa fa-remove', null);

insert into sif_sys_auth (AUTH_CD, AUTH_TYPE, REGISTER_CD, AUTH_URL, MENU_NAME, PARENT_AUTH_CD, RANK, SORTNO, ICONFG, TARGET_FLAG)
values ('10000045', '2', '001', '/paychannel/accessconfigment', '��������������Ϣ��ѯ', '1000004', 3, null, 'fa fa-remove', null);

insert into sif_sys_auth (AUTH_CD, AUTH_TYPE, REGISTER_CD, AUTH_URL, MENU_NAME, PARENT_AUTH_CD, RANK, SORTNO, ICONFG, TARGET_FLAG)
values ('10000041', '2', '001', '/paychannel/paychannelment', '֧����������', '1000004', 3, null, 'fa fa-remove', null);

insert into sif_sys_auth (AUTH_CD, AUTH_TYPE, REGISTER_CD, AUTH_URL, MENU_NAME, PARENT_AUTH_CD, RANK, SORTNO, ICONFG, TARGET_FLAG)
values ('10000042', '2', '001', '/paychannel/bankment', '���й���', '1000004', 3, null, 'fa fa-remove', null);

insert into sif_sys_auth (AUTH_CD, AUTH_TYPE, REGISTER_CD, AUTH_URL, MENU_NAME, PARENT_AUTH_CD, RANK, SORTNO, ICONFG, TARGET_FLAG)
values ('10000043', '2', '001', '/paychannel/quotament', '�޶����', '1000004', 3, null, 'fa fa-remove', null);

insert into sif_sys_auth (AUTH_CD, AUTH_TYPE, REGISTER_CD, AUTH_URL, MENU_NAME, PARENT_AUTH_CD, RANK, SORTNO, ICONFG, TARGET_FLAG)
values ('1000004', '2', '001', null, '֧������', '100000', 2, null, null, null);

prompt Done.

commit;
