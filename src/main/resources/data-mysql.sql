
--insert into users(username, password, enabled) values('pivotal', '12dbf20dd93b4557039f570774641afb602bf836f73794b22396fd3fb47ca7cbd67e9a577e1df1b4', true);
--insert into users(username, password, enabled) values('spring', 'b8ff599d21dd1f4f4631172a1fd2c561ccd254128208e88576aec785a0af3697015182dfd6020edc', true);

-- insert into authorities(username, authority) values('spring', 'ROLE_USER');
-- insert into authorities(username, authority) values('pivotal', 'ROLE_ADMIN');
-- insert into authorities(username, authority) values('pivotal', 'ROLE_USER');

insert into groups(id, group_name) values(1, 'Admins');

insert into group_authorities(group_id, authority) values(1, 'ROLE_USER');
insert into group_authorities(group_id, authority) values(1, 'ROLE_ADMIN');

insert into group_members(id, username, group_id) values(1, 'pivotal', 1);
insert into group_members(id, username, group_id) values(2, 'spring', 1);