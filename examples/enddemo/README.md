oracle数据库

创建测试数据库表
-- Create table customer
drop table customer;
create table customer
(
  cust_id   NUMBER(10) not null,
  cust_name VARCHAR2(200),
  sex       CHAR(1),
  age       NUMBER(5)
);
alter table customer add constraint pk_customer primary key (CUST_ID);
-- Create table tbl_user
drop table tbl_user;
create table tbl_user
(
  uuid   NUMBER(10) not null,
  name VARCHAR2(200),
  age       NUMBER(5)
);
alter table tbl_user add constraint pk_tbl_user primary key (uuid);
-- Create table tbl_dep
drop table tbl_dep;
create table tbl_dep
(
  uuid   NUMBER(10) not null,
  name VARCHAR2(200)
);
alter table tbl_dep add constraint pk_tbl_dep primary key (uuid);
