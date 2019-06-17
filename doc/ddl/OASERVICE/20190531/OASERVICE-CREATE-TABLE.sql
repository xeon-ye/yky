create table  oa_menu_favorites(
       id number primary key,
       menu_code varchar2(200),
       menu_name varchar2(200),
       menu_url varchar2(2000),
       parent_id varchar2(60),
       module_code varchar2(80),
       module_name varchar2(80),
       icon      varchar2(300),
       system_source    varchar2(60),
       order_sort       number,
       create_by        VARCHAR2(300),
       create_time      TIMESTAMP(6),
       update_by        VARCHAR2(300),
       update_time      TIMESTAMP(6),
       ext1             VARCHAR2(300),
       ext2             VARCHAR2(300),
       ext3             VARCHAR2(300),
       ext4             VARCHAR2(300),
       ext5             VARCHAR2(300)
);

-- Add comments to the columns
comment on column OA_MENU_FAVORITES.id
  is '主键';
comment on column OA_MENU_FAVORITES.menu_code
  is '菜单编码';
comment on column OA_MENU_FAVORITES.menu_name
  is '菜单名称';
comment on column OA_MENU_FAVORITES.menu_url
  is '菜单地址';
comment on column OA_MENU_FAVORITES.parent_id
  is '父菜单id';
comment on column OA_MENU_FAVORITES.module_code
  is '所属模块编码';
comment on column OA_MENU_FAVORITES.module_name
  is '所属模块名称';
comment on column OA_MENU_FAVORITES.icon
  is '菜单图标';
comment on column OA_MENU_FAVORITES.system_source
  is '所属系统';
comment on column OA_MENU_FAVORITES.order_sort
  is '排序号';
comment on column OA_MENU_FAVORITES.create_by
  is '创建人';
comment on column OA_MENU_FAVORITES.create_time
  is '创建时间';
comment on column OA_MENU_FAVORITES.update_by
  is '最后更新人';
comment on column OA_MENU_FAVORITES.update_time
  is '最后更新时间';
comment on column OA_MENU_FAVORITES.ext1
  is '备用字段';
comment on column OA_MENU_FAVORITES.ext2
  is '备用字段';
comment on column OA_MENU_FAVORITES.ext3
  is '备用字段';
comment on column OA_MENU_FAVORITES.ext4
  is '备用字段';
comment on column OA_MENU_FAVORITES.ext5
  is '备用字段';
