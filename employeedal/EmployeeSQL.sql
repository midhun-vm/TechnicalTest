create database mydb

use mydb

create table employee(
id int PRIMARY KEY AUTO_INCREMENT,
fname varchar(20),
sname varchar(30)
)

select * from employee