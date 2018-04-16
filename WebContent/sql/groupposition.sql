DROP table groupposition PURGE ;
CREATE table groupposition (
  studynum int PRIMARY KEY ,
  groupposition VARCHAR2(100) NOT NULL
);
SELECT *
FROM groupposition;
