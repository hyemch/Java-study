select * from Major;
desc Major;
insert into Major(name) values ('철학과');

insert into Major(name)
	values ('컴퓨터공학과'), ('소프트웨어공학과');

insert into Major(name) select '산업공학과' from dual;

insert into Major set name = '경제학과';
insert into Major set name = '경영학과';

select 1 + 2 from dual;

select * from Student;
desc Student;

insert into Student(name, birthdt, major, mobile, email)
			values('Hong', '990102', 1, '010-2323-4545', 'hong@gmail.com'); 
            
insert into Student(name, birthdt, major, mobile, email)
			values('Kim', '980102', 5, '010-2323-7878', 'kim@gmail.com'); 
            
insert into Student(name, birthdt, major, mobile, email)
             values('Lee', '980302', 5, '010-2323-7878', 'lee@gmail.com');
             
insert into Student(name, birthdt, major, mobile, email)
             values('Choi', '970302', null, '010-2323-9898', 'choi@gmail.com');

             
select * from Student where name = 'Kim';
select * from Student where gender = 0;
select * from Student where birthdt like '98%';
select * from Student where birthdt between '980101' and '981231';
select * from Student where birthdt >= '980101' and birthdt <= '981231';
select * from Student where major in (5, 6);
select * from Student where major in (5, 6) order by name desc;
select * from Student where major = 5 or major = 6;
select * from Student where major in (select major from Student where major >= 5);
select * from Student where major in (select distinct major from Student where major >= 5);
select * from Student where major = (select min(major) from Student);
select * from Student where major > ANY(select major from Student);
select * from Student where major > SOME(select major from Student);
select * from Student where major < ALL(select major from Student);
select * from Student order by rand();
select * from Student order by id desc limit 2;
select major, count(*) cnt from Student where id > 0 group by major having count(*) > 1;
select major, count(*) as cnt from Student where id > 0 group by major having cnt > 1;

select major from Student where major >= 5;
select * from Student;
select distinct major from Student where major >= 5;
select max(major), min(birthdt) from Student;
select max(major), min(major) from Student;

select * from Student inner join Major on Student.major = Major.id where Student.id >= 2;

select * from Student s inner join Major m on s.major = m.id where s.id >= 2;

select * from Student s left outer join Major m on s.major = m.id;

select * from Student s right outer join Major m on s.major = m.id;

select * from Major where id <= 3
UNION
select * from Major where id >= 3;

select * from Student s left outer join Major m on s.major = m.id
UNION ALL
select * from Student s right outer join Major m on s.major = m.id;

-- select * from Student s full outer join Major m on s.major = m.id;


select * from Student inner join Major on Student.major = Major.id where Student.id >= 2;


select * from Student;

select * from Major;

select s.*, m.name from Student s inner join Major m on s.major = m.id;

