alter table sensor add column in_tag BOOLEAN;

update sensor set in_tag = true where id in(123, 124, 125, 126);

commit;