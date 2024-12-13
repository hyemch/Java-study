# # truncate table DemoUser;
# insert into DemoUser(username, age)
# values ('A', 1);
# insert into DemoUser(username, age)
# values ('B', 2);
# # insert into DemoUser(username, age)
# # values ('C', 3);
# select username 'C', age = 3
# from DemoUser
# where not exists(select * from DemoUser where username = 'C');
