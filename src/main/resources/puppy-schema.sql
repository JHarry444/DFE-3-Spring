drop table if exists `puppy` CASCADE; 
create table 
	`puppy` 
(
	id integer AUTO_INCREMENT, 
	breed varchar(255), 
	height integer not null, 
	name varchar(255), 
	primary key (id)
);