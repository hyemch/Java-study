select * from Dept;

create table Cust(
	id int unsigned not null auto_increment,
    name varchar(31) not null default '' comment '고객명',
	tel varchar(15) not null default '' comment '전화번호',
    email varchar(255) null comment '이메일주소',
    Primary Key(id)
);

select * from Cust;