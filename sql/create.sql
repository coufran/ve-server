/** 用户 */
drop table if exists user;

create table user(
  id int primary key auto_increment comment 'ID',
  username varchar(10) not null unique comment '用户名',
  password char(32) not null comment '密码'
) character set = utf8;

/** Token 权限 */
drop table if exists token;

create table token(
  token char(32) primary key comment 'Token值',
  user_id int not null comment '用户ID',
  type enum('ACCESS', 'REFRESH') not null comment 'Token类型',
  expire long not null comment '过期时间'
) character set = utf8;

/** 收支记录 */
drop table if exists record;

create table record(
  id int primary key auto_increment comment 'ID',
  amount int not null comment '金额',
  debit_id int not null comment '借方',
  credit_id int not null comment '贷方',
  group_id int not null comment '联合ID',
  time datetime not null comment '时间',
  remark varchar(100) comment '备注'
) character set = utf8;

/** 账户 */
drop table if exists account;

create table account(
  id int primary key auto_increment comment 'ID',
  name varchar(20) not null comment '名称',
  amount int not null comment '余额',
  title_id int not null comment '所属科目'
) character set = utf8;

/** 科目 */
drop table if exists title;

create table title(
  id int primary key auto_increment comment 'ID',
  name varchar(20) not null comment '名称',
  kind enum('ASSETS', 'LIABILITY', 'PROFIT', 'LOSS') not null comment '分类'
) character set = utf8;