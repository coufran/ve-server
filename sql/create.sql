/** 用户 */
drop table if exists user;

create table user(
  id int primary key auto_increment comment 'ID',
  username varchar(10) not null unique comment '用户名',
  password char(32) not null comment '密码'
);

/** Token 权限 */
drop table if exists token;

create table token(
  token char(32) primary key comment 'Token值',
  user_id int not null comment '用户ID',
  expire long not null comment '过期时间'
);

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
);