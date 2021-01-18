insert into user values(1, 'coufran', '6eed84a9f80ad64ec927ec0012e2602a');

insert into title values(1, '现金', 'ASSETS');
insert into title values(2, '银行卡', 'ASSETS');
insert into title values(3, '信用卡', 'LIABILITY');
insert into title values(4, '收入', 'PROFIT');
insert into title values(5, '支出', 'LOSS');

insert into account values(1, '钱包', 0, 1);
insert into account values(2, '浦发银行', 1000000, 2);
insert into account values(3, '招商银行信用卡', -11000, 3);
insert into account values(4, '晋商银行信用卡', 0, 3);
insert into account values(5, '工资', 0, 4);
insert into account values(6, '餐饮', 0, 5);
insert into account values(7, '交通', 0, 5);
insert into account values(8, '购物', 0, 5);
insert into account values(9, '娱乐', 0, 5);

insert into record values(1, 1000000, 1, 5, 1, now(), '1月工资');
insert into record values(2, 1000, 6, 3, 2, now(), '午饭');
insert into record values(3, 10000, 7, 3, 3, now(), '加油');