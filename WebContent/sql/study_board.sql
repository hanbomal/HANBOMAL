DROP table studyboard PURGE ;
CREATE table studyboard (
  num INT,
  boardid VARCHAR2(100) PRIMARY KEY ,
  writer VARCHAR2(100) not null,
  subject VARCHAR2(100) not null,
  passwd VARCHAR2(100) not null,
  reg_date DATE,
  readcount INT,
  content VARCHAR2(100) not null,
  filename VARCHAR2(100),
  filesize INT
);
SELECT *
FROM studyboard;