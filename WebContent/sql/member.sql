

drop table member

create table member(

  num int not null ,
  listid varchar(20),
  memberId VARCHAR2(100) PRIMARY KEY  not null,
  passwd VARCHAR2(100) not null,
  nickName VARCHAR2(100) not null,
  passwdq VARCHAR2(100) not null,
  passwdkey VARCHAR2(100) not null,
  joinDate DATE NOT NULL ,
  lastDate DATE NOT NULL )

 



drop sequence memberSer

  CREATE SEQUENCE memberSer
  START WITH 1
  INCREMENT BY 1
  NOMAXVALUE ;
  
  create sequence memberSer
  start with 1
  increment by 1
  NOMAXVALUE
  NOCYCLE ;
  

COMMIT;

select * from member