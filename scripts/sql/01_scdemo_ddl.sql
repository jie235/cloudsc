drop table if EXISTS scdemo.mytable;

select * from pg_tables where schemaname ='scdemo';


--订单表
drop table scdemo.t_order;

create table scdemo.t_order(
id serial,
order_no varchar(255) default null,
acct_code varchar(255) default null,
prod_code varchar(255) default null,
cnt int default 0,
amount decimal(10,2) default 0.00,
primary key(id));


--产品表
drop table scdemo.t_product;

create table scdemo.t_product(
id serial,
prod_code varchar(255) default null UNIQUE,
prod_name varchar(255) default null,
amount int default 0,
price decimal(10,2) default 0.00,
primary key (id)
);

comment on column scdemo.t_product is '库存数量';


--账户表
drop table scdemo.t_account;

create table scdemo.t_account(
id serial,
acct_code varchar(255) default null UNIQUE,
acct_name varchar(255) default null,
amount decimal(10,2) default 0.00,
primary key (id)
);