drop table MOVIES;

create table MOVIES(
  id NUMBER(10) primary key,
  title VARCHAR2(50) not null
);

insert into MOVIES values (1, "Shawshank Redemption");