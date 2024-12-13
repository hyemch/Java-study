WITH 
  AvgSal AS (
    select d.id, d.dname, avg(e.salary) avgsal
      from Dept d inner join Emp e on d.id = e.dept
     group by d.id
  ),
  MaxAvgSal AS (
    select * from AvgSal order by avgsal desc limit 1
  ),
  MinAvgSal AS (
    select * from AvgSal order by avgsal limit 1
  ),
  SumUp AS (
    select '최고' as gb, mx.* from MaxAvgSal mx
    UNION ALL
    select '최저' as gb, mn.* from MinAvgSal mn
  )
select  gb as '구분', dname as '부서명', format(avgsal * 10000, 0) '평균급여' from SumUp
UNION ALL
select '', '평균 급여 차액', format((max(avgsal) - min(avgsal)) * 10000, 0) from SumUp;

WITH RECURSIVE fibonacci(n, fib_n, fib_next_n) AS (
  select 1, 0, 1
  UNION ALL
  select n + 1, fib_next_n, fib_n + fib_next_n 
    from fibonacci where n < 10
) select * from fibonacci;

insert into Dept(pid, dname) values(6, '인프라셀');
insert into Dept(pid, dname) values(6, 'DB셀');
insert into Dept(pid, dname) values(7, '모바일셀');

select * from Dept;

WITH RECURSIVE CteDept(id, pid, dname, depth, h) AS (
  select id, pid, dname, 0, cast(id as char(10)) from Dept where pid = 0
  UNION ALL
  select d.id, d.pid, d.dname, depth + 1, concat(c.h, '-', d.id)
    from CteDept c inner join Dept d on c.id = d.pidauty
) 
select /*+ SET_VAR(cte_max_recursion_depth = 20) */ 
       concat(repeat('↳', c.depth), ' ', c.dname), c.depth, c.h
       from CteDept c order by c.h;

show variables like '%cte_max_recursion_depth';
show variables like 'max_execution_time';

select * from Emp where auth=5;
update Emp set auth = 5 order by rand() limit 10;
show create table Emp;