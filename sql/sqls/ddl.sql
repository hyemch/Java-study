create table Student (
	id mediumint unsigned not null auto_increment comment '학번',
	createdate timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
    updatedate timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
    name varchar(31) not null comment '이름',
    birthdt varchar(6) not null comment '생년월일',
    major tinyint unsigned not null comment '학과코드',
    mobile varchar(15) not null comment '전화번호',
    email varchar(150) not null comment '이메일주소',
    gender boolean not null default 0 comment '성별(0:여성, 1:남성)',
	graduatedt date null comment '졸업일',
    PRIMARY KEY(id),
    UNIQUE KEY uniq_Student_email(email),
    UNIQUE KEY uniq_Student_name_mobile(name, mobile)
);

create table Major (
	id tinyint unsigned not null auto_increment primary key comment '학과코드',
	name varchar(20) not null comment '학과명'
);

create table Prof (
	id smallint unsigned not null auto_increment comment '교수id',
    name varchar(31) not null comment '이름',
    likecnt int default 0,
    PRIMARY KEY (id)
);

-- modify name var..
-- change name namex var..

alter table Prof add column createdtate timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시' after id;
alter table Prof add column updatedate timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시' after createdtate;
alter table Prof change column createdtate createdate timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시';

create table Subject (
	id smallint unsigned not null auto_increment comment '과목번호',
	createdate timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
	updatedate timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
    name varchar(45) not null comment '과목명',
    prof smallint unsigned null comment '담당교수',
    PRIMARY KEY (id),
    constraint Foreign Key fk_Subject_prof_Porf (prof)
		References Prof(id) on Delete set null on Update cascade,
    -- constraint foreign key fk_prof_id(prof) references Prof(id) on DELETE set null on UPDATE cascade,
    UNIQUE KEY uniq_Subject_name(name)
);

create table Enroll (
	id smallint unsigned not null auto_increment comment '수강신청번호',
	createdate timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '신청일시',
	updatedate timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
    subject smallint unsigned not null comment '과목번호',
    student mediumint unsigned not null comment '학번',
    Primary Key (id),
    constraint foreign key fk_Endroll_subject_Subject(subject) 
    references Subject(id) on DELETE no action on UPDATE cascade,
    constraint foreign key fk_Endroll_student_Student(student)
    references Student(id) on DELETE cascade on UPDATE cascade
    );

select * from Student;

alter table Student modify column name varchar(25) not null default '' comment '학생명';

alter table Student modify column major tinyint unsigned null comment '학과코드';

alter table Student add constraint foreign key fk_Student_major_Major(major)

	references Major(id) on DELETE set null on UPDATE cascade;

desc Student;

