use sys;
create table User(
id int not null auto_increment,
userName char(50) not null,
avatar char(255),
age int,
tel char(20) not null,
primary key(id)
);
insert into user(userName,avatar,age,tel) values (
"cyq",
"http://img.jrjimg.cn/2019/07/20190730115116381.jpg",
20,
"18880345672");
select * from user;

create table admin(
id int not null auto_increment,
name char(50) not null,
pwd char(20) not null,
primary key(id)
);

insert into admin(name,pwd) values ("admin","amm123");