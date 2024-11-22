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
            