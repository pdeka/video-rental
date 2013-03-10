drop table movie;

create table movies(
  id NUMBER(10) primary key,
  title VARCHAR2(50) not null
);

insert into movies values (1, "Shawshank Redemption");