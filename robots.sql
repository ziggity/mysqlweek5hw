create database if not exists robots;

use robots;

drop table if exists robots;
drop table if exists users;

create table robots(
		id int(10) not null auto_increment,
		name varchar(50) not null,
		primary key(id)
);

create table users(
	id int(10) not null auto_increment,
	first_name varchar(25) not null, 
	last_name varchar(25) not null,
	robot_id int(10) not null,
	primary key(id),
	foreign key(robot_id) references robots(id)
);
