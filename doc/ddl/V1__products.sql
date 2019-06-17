-- Create table
create table demo_product
(
  id           number(10) primary key,
  name         varchar2(200) not null,
  description  varchar2(500),
  created_time timestamp not null,
  updated_time timestamp not null,
  created_by   varchar2(100) not null,
  updated_by   varchar2(100) not null
)
