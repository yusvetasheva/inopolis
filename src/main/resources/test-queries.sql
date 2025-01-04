--select

select * from product where description LIKE '%model%';

select fio from saller where salary < 50000;

select * from shop s
join address a
on s.address_id = a.id;

--update
update product set description = 'trololo' where procuct_name = 'Laptop';

--delete
delete from product where description = 'Laptop';