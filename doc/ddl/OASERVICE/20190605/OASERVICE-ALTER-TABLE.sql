--prompt
--prompt ALTER table 
--prompt =======================
--prompt

alter table OA_NOTICE add (DEPT_PER VARCHAR2(10) DEFAULT 'inter');
comment on column OA_NOTICE.DEPT_PER is '单位权限: inter 内部单位, outer 外部单位';

alter table OA_NOTICE_OTHER add (DEPT_PER VARCHAR2(10) DEFAULT 'inter');
comment on column OA_NOTICE_OTHER.DEPT_PER is '单位权限: inter 内部单位, outer 外部单位';

alter table OA_RULE_SYSTEM add (DEPT_PER VARCHAR2(10) DEFAULT 'inter');
comment on column OA_RULE_SYSTEM.DEPT_PER is '单位权限: inter 内部单位, outer 外部单位';

--prompt
--prompt Creating table OA_NOTICE_PERMISSION
--prompt ==============================
--prompt

create table OA_NOTICE_PERMISSION
(
  id          NUMBER(20) not null,
  type        VARCHAR2(100),
  object_id   NUMBER(20),
  org_code    VARCHAR2(100),
  dept_code   VARCHAR2(100),
  create_time DATE default SYSDATE
);

comment on table OA_NOTICE_PERMISSION
  is '公告规章制度权限';
comment on column OA_NOTICE_PERMISSION.id
  is '权限ID';
comment on column OA_NOTICE_PERMISSION.type
  is '业务类型';
comment on column OA_NOTICE_PERMISSION.org_code
  is '部门CODE';
comment on column OA_NOTICE_PERMISSION.dept_code
  is '单位CODE';
comment on column OA_NOTICE_PERMISSION.create_time
  is '创建时间';