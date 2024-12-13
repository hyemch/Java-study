select * from Prof;
desc Prof;
insert into Prof(name) values('김교수');
insert into Prof(name) values('박교수');
insert into Prof(name) values('최교수');
insert into Prof(name) values('홍교수');

select * from Subject;
insert into Subject(name, prof) 
select concat(p.name, '과목'), p.id from Prof p;

desc Subject;
show index from Subject;

select * from Enroll;
desc Enroll;

insert into Enroll(subject, student) values (1,1), (2,15), (3,19), (4,20);

select * from Student; -- 1,15,19,20

-- 수강신청한 과목 명 함께 출력
select e.*, sub.name as subjectName 
	from Enroll e inner join Subject sub on e.subject = sub.id;

-- 수강신청한 과목 명, 학생 명 함께 출력
select e.*, sub.name as subjectName, stu.name studentName
	from Enroll e inner join Subject sub on e.subject = sub.id
				  inner join Student stu on e.student = stu.id;
		
-- 수강신청한 학생의 과까지 출력
select e.*, sub.name as subjectName, stu.name studentName, m.name as studentMajor
	from Enroll e inner join Subject sub on e.subject = sub.id
				  inner join Student stu on e.student = stu.id
                  left outer join Major m on stu.major = m.id; -- 없으면 null
                  
-- where e.subject *= sub.id 이런식으로  left outer join 사용하기도 함.
show processlist;
 
 -- != <> : not equal
select * from Student where major is null;
select * from Student where major = (select max(major) from Student);
select * from Student where (select max(major) from Student) = major;

explain select *, (select name from Major where id = Student.major)
	from Student;

-- join을 쓸 수있으면 최대한 join을 쓰자. 
explain select *, (select name from Major where id = Student.major)
	from Student;

-- good
select s.*, m.name
	from Student s inner join Major m on S.major = m.id
	where m.id <= 3;
    
-- bad
select *, (select name from Major where id = Student.major) 
  from Student;
  
-- good
select s.*, m.name from Student s left join Major m on s.major = m.id;

-- bad
select s.*, sub.name
  from Student s inner join (select * from Major where id <= 3) sub
        on s.major = sub.id;

-- not bad
select s.*, m.name
  from Student s inner join Major m on s.major = m.id and m.id <= 3;
        
-- good
select s.*, m.name
  from Student s inner join Major m on s.major = m.id
 where m.id <= 3;
    
-- 평균 급여보다 높은 부서명과 부서 최고의 연봉자 구하기

 -- 1 group
select * from Emp;
select avg(salary) from Emp;
select dept, avg(salary) from Emp group by dept;

select dept, avg(salary) avgsal, max(ename) from Emp
 	group by dept having avg(salary) > (select avg(salary) from Emp);

select sub.* 
	from (select dept, avg(salary) avgsal, max(salary) maxsal from Emp
	group by dept having avg(salary) > (select avg(salary) from Emp)) sub;

-- min :name -> captain
-- 1. 부서별 이름이 가장 빠른 직원
select dept, min(ename), min(id) from Emp group by dept;

select * from Emp where ename in (select min(ename) from Emp group by dept);
show index from Emp;

select * from Emp where dept = 1 and ename = '김나나';

select e1.*, e2.id
	from Emp e1 left join Emp e2 on e1.dept = e2.dept and e1.ename > e2.ename
    where e2.id is null;
    
-- 위 원칙을 captain으로
update Dept d 
	inner join (select e1.dept, e1.id 
					from Emp e1 left join Emp e2 
                    on e1.dept = e2.dept and e1.ename > e2.ename
				where e2.id is null) 
			e on d.id = e.dept set d.captain = e.id where d.id > 0;

select * from Dept;
select d.*, e.dept, e.ename from Dept d inner join Emp e on d.captain = e.id;