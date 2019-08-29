use sys;
create table User(
id int not null auto_increment,
userName char(50) not null,
avatar char(50),
age int,
tel char(20) not null,
primary key(id)
);