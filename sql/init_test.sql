insert into user values(1, 'coufran', '6eed84a9f80ad64ec927ec0012e2602a');

insert into title values(1, '现金', 'ASSETS');
insert into title values(2, '信用卡', 'LIABILITY');
insert into title values(3, '收入', 'PROFIT');
insert into title values(4, '支出', 'LOSS');

insert into account values(1, '钱包', 10000, 1);
insert into account values(2, '招商银行信用卡', 50000, 2);
insert into account values(3, '晋商银行信用卡', 30000, 2);
insert into account values(4, '工资', 0, 3);
insert into account values(5, '餐饮', 15000, 4);
insert into account values(6, '交通', 0, 4);
insert into account values(7, '购物', 0, 4);
insert into account values(8, '娱乐', 0, 4);

insert into record values(1, 1000000, 1, 4, 1, now(), '1月工资');
insert into record values(1, 1100, 5, 1, 2, now(), '午饭');
insert into record values(1, 10000, 1, 2, 3, now(), '还款');