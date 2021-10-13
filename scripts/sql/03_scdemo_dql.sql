
--查询产品
select * from scdemo.t_product;

select*
from scdemo.t_account where acct_code = 'javadaily';

select * from scdemo.t_account;

update scdemo.t_account
set acct_name = 'nightData',
		amount    = 200.0
where acct_code='demoData';

select *from scdemo.t_order;