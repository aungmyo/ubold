
drop table if exists users;
drop table if exists authorities;
drop table if exists group_authorities;
drop table if exists group_members;
drop table if exists groups;


-- JDBC Authentication
create table users (
	username varchar(50) not null primary key,
  	password varchar(500) not null,
  	enabled boolean
);

create table authorities (
	username varchar(50) not null,
  	authority varchar(50) not null,
  	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);


-- Group Based Authentication
create table groups (
	id serial PRIMARY KEY,
	group_name varchar(50) not null
);

create table group_authorities (
	group_id serial not null,
	authority varchar(50) not null,
	constraint fk_group_authorities_group foreign key(group_id) references groups(id)
);

create table group_members (
	id bigserial PRIMARY KEY,
	username varchar(50) not null,
	group_id bigint not null,
	constraint fk_group_members_group foreign key(group_id) references groups(id)
);