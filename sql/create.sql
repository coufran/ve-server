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