show processlist;

select * from mysql.user;

select * from Emp;
desc Emp;
select * from Dept;

alter table Emp add column auty tinyint(1) not null default 9 comment '권한(0:sys , 1:super, ..., 9:guest)' after dept;

desc Emp;

alter table Dept add column captain int unsigned null comment '부서장';
alter table Dept add constraint foreign key fk_Dept_captain_Emp(captain) 
             references Emp(id) on DELETE set null on UPDATE cascade;

create table EmailLog (
	id int unsigned not null auto_increment,
    sender int unsigned not null comment '발신자 id',
    reciver varchar(255) not null comment '수신자 + 참조',
    subject varchar(255) not null default '내용없음' comment '제목',
    body text null comment '내용 및 첨부파일',
    PRIMARY KEY pk_EmailLog(id),
    constraint foreign key fk_EmailLog_sender_Emp(sender)
		references Emp(id) on DELETE no action on UPDATE cascade
);

select @@autocommit;

show create table Dept;
show index from Dept;
show table status;
show index from Emp;

select last_insert_id();
insert into Dept(pid, dname) values(2, '퍼블리셔팀');

select * from Student;
desc Student;