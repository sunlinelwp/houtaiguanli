

环境搭建

Oracle数据：
	1.创建表空间
		drop tablespace sunline_dat including contents and datafiles cascade constraints;
		create tablespace sunline_dat datafile 'D:\oraclexe\app\oracle\oradata\XE\SUNLINEDAT01.DBF' size 2048m;
		drop tablespace sunline_idx including contents and datafiles cascade constraints;
		create tablespace sunline_idx datafile 'D:\oraclexe\app\oracle\oradata\XE\SUNLINEIDX01.DBF' size 1024m;
	2.创建用户
		drop user sunline cascade;
		create user sunline identified by sunline default tablespace sunline_dat;
		grant create session to sunline;
		grant create table to sunline;
		grant create view to sunline;
		grant create procedure to sunline;
		grant create sequence to sunline;
		grant create rollback segment to sunline;
		grant alter rollback segment to sunline;
		grant drop rollback segment to sunline;
		grant unlimited tablespace to sunline;
		grant debug connect session to sunline;