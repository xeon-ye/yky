--------------------------------------------------------
--  文件已创建 - 星期四-二月-14-2019
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table T_BASE_USER
--------------------------------------------------------

  CREATE TABLE "T_BASE_USER" ("ID" NUMBER(20,0), "USER_ID" NUMBER(20,0), "IDENTITY_CARD" VARCHAR2(64), "ADDRESS" VARCHAR2(128), "BIRTHDAY" DATE, "PASSPORT_NO" VARCHAR2(64), "COMPANY" VARCHAR2(128), "POSITION" VARCHAR2(64), "EDUCATION" NVARCHAR2(32), "GENDER" VARCHAR2(32), "REMARK" VARCHAR2(255), "RESERVE" VARCHAR2(64), "VERSION" VARCHAR2(64), "CREATE_TIME" TIMESTAMP (6) DEFAULT CURRENT_TIMESTAMP, "CREATE_BY" NUMBER(20,0), "UPDATE_TIME" TIMESTAMP (6), "UPDATE_BY" NUMBER(20,0))

   COMMENT ON COLUMN "T_BASE_USER"."ID" IS '主键'
   COMMENT ON COLUMN "T_BASE_USER"."USER_ID" IS '用户ID'
   COMMENT ON COLUMN "T_BASE_USER"."IDENTITY_CARD" IS '身份证'
   COMMENT ON COLUMN "T_BASE_USER"."ADDRESS" IS '地址'
   COMMENT ON COLUMN "T_BASE_USER"."BIRTHDAY" IS '生日'
   COMMENT ON COLUMN "T_BASE_USER"."PASSPORT_NO" IS '护照号'
   COMMENT ON COLUMN "T_BASE_USER"."COMPANY" IS '工作单位'
   COMMENT ON COLUMN "T_BASE_USER"."POSITION" IS '岗位职务'
   COMMENT ON COLUMN "T_BASE_USER"."EDUCATION" IS '学历'
   COMMENT ON COLUMN "T_BASE_USER"."GENDER" IS '性别'
   COMMENT ON COLUMN "T_BASE_USER"."REMARK" IS '备注'
   COMMENT ON COLUMN "T_BASE_USER"."RESERVE" IS '备用字段1'
   COMMENT ON COLUMN "T_BASE_USER"."VERSION" IS '备用字段2'
   COMMENT ON COLUMN "T_BASE_USER"."CREATE_TIME" IS '创建时间'
   COMMENT ON COLUMN "T_BASE_USER"."CREATE_BY" IS '创建人ID'
   COMMENT ON COLUMN "T_BASE_USER"."UPDATE_TIME" IS '更新时间'
   COMMENT ON COLUMN "T_BASE_USER"."UPDATE_BY" IS '更新人ID'
   COMMENT ON TABLE "T_BASE_USER"  IS '用户基础信息表'
--------------------------------------------------------
--  DDL for Table T_DEPUTY_ACCOUNT
--------------------------------------------------------

  CREATE TABLE "T_DEPUTY_ACCOUNT" ("ID" NUMBER(20,0), "USER_ID" NUMBER(20,0), "ORG_ID" NUMBER(20,0), "NAME" VARCHAR2(128), "SORT" NUMBER(11,0) DEFAULT 0, "ORG_SORT" NUMBER(11,0) DEFAULT 0, "STATE" VARCHAR2(32) DEFAULT 0, "OPEN_TIME" TIMESTAMP (6) DEFAULT CURRENT_TIMESTAMP, "CLOSE_TIME" TIMESTAMP (6) DEFAULT '', "REMARK" VARCHAR2(255), "RESERVE" VARCHAR2(64), "VERSION" VARCHAR2(64), "CREATE_TIME" TIMESTAMP (6) DEFAULT CURRENT_TIMESTAMP, "CREATE_BY" NUMBER(20,0), "UPDATE_TIME" TIMESTAMP (6), "UPDATE_BY" NUMBER(20,0))

   COMMENT ON COLUMN "T_DEPUTY_ACCOUNT"."ID" IS '主键'
   COMMENT ON COLUMN "T_DEPUTY_ACCOUNT"."USER_ID" IS '用户ID'
   COMMENT ON COLUMN "T_DEPUTY_ACCOUNT"."ORG_ID" IS '组织架构ID'
   COMMENT ON COLUMN "T_DEPUTY_ACCOUNT"."NAME" IS '名称'
   COMMENT ON COLUMN "T_DEPUTY_ACCOUNT"."SORT" IS '个人账号排序'
   COMMENT ON COLUMN "T_DEPUTY_ACCOUNT"."ORG_SORT" IS '组织内排序'
   COMMENT ON COLUMN "T_DEPUTY_ACCOUNT"."STATE" IS '状态'
   COMMENT ON COLUMN "T_DEPUTY_ACCOUNT"."OPEN_TIME" IS '启用时间'
   COMMENT ON COLUMN "T_DEPUTY_ACCOUNT"."CLOSE_TIME" IS '停用时间'
   COMMENT ON COLUMN "T_DEPUTY_ACCOUNT"."REMARK" IS '备注'
   COMMENT ON COLUMN "T_DEPUTY_ACCOUNT"."RESERVE" IS '备用字段1'
   COMMENT ON COLUMN "T_DEPUTY_ACCOUNT"."VERSION" IS '备用字段2'
   COMMENT ON COLUMN "T_DEPUTY_ACCOUNT"."CREATE_TIME" IS '创建时间'
   COMMENT ON COLUMN "T_DEPUTY_ACCOUNT"."CREATE_BY" IS '创建人ID'
   COMMENT ON COLUMN "T_DEPUTY_ACCOUNT"."UPDATE_TIME" IS '更新时间'
   COMMENT ON COLUMN "T_DEPUTY_ACCOUNT"."UPDATE_BY" IS '更新人ID'
   COMMENT ON TABLE "T_DEPUTY_ACCOUNT"  IS '副账号'
--------------------------------------------------------
--  DDL for Table T_DEPUTY_ACCOUNT_ROLE
--------------------------------------------------------

  CREATE TABLE "T_DEPUTY_ACCOUNT_ROLE" ("ID" NUMBER(20,0), "DEPUTY_ACCOUNT_ID" NUMBER(20,0), "ROLE_ID" NUMBER(20,0))

   COMMENT ON COLUMN "T_DEPUTY_ACCOUNT_ROLE"."ID" IS '主键'
   COMMENT ON COLUMN "T_DEPUTY_ACCOUNT_ROLE"."DEPUTY_ACCOUNT_ID" IS '副账号ID'
   COMMENT ON COLUMN "T_DEPUTY_ACCOUNT_ROLE"."ROLE_ID" IS '角色ID'
   COMMENT ON TABLE "T_DEPUTY_ACCOUNT_ROLE"  IS '副账号-角色 关联表'
--------------------------------------------------------
--  DDL for Table T_DICT
--------------------------------------------------------

  CREATE TABLE "T_DICT" ("ID" NUMBER(20,0), "NAME" VARCHAR2(32), "CODE" VARCHAR2(32), "SORT" NUMBER(11,0) DEFAULT 0, "PARENT_ID" NUMBER(20,0) DEFAULT 0, "STATE" VARCHAR2(32) DEFAULT 0, "REMARK" VARCHAR2(255), "RESERVE" VARCHAR2(64), "VERSION" VARCHAR2(64), "CREATE_TIME" TIMESTAMP (6) DEFAULT CURRENT_TIMESTAMP, "CREATE_BY" NUMBER(20,0), "UPDATE_TIME" TIMESTAMP (6), "UPDATE_BY" NUMBER(20,0))

   COMMENT ON COLUMN "T_DICT"."ID" IS '主键'
   COMMENT ON COLUMN "T_DICT"."NAME" IS '名称'
   COMMENT ON COLUMN "T_DICT"."CODE" IS '编码'
   COMMENT ON COLUMN "T_DICT"."SORT" IS '排序'
   COMMENT ON COLUMN "T_DICT"."PARENT_ID" IS '上级ID'
   COMMENT ON COLUMN "T_DICT"."STATE" IS '状态'
   COMMENT ON COLUMN "T_DICT"."REMARK" IS '备注说明'
   COMMENT ON COLUMN "T_DICT"."RESERVE" IS '备用字段1'
   COMMENT ON COLUMN "T_DICT"."VERSION" IS '备用字段2'
   COMMENT ON COLUMN "T_DICT"."CREATE_TIME" IS '创建时间'
   COMMENT ON COLUMN "T_DICT"."CREATE_BY" IS '创建人ID'
   COMMENT ON COLUMN "T_DICT"."UPDATE_TIME" IS '更新时间'
   COMMENT ON COLUMN "T_DICT"."UPDATE_BY" IS '更新人ID'
   COMMENT ON TABLE "T_DICT"  IS '字典表'
--------------------------------------------------------
--  DDL for Table T_ORGANIZATION
--------------------------------------------------------

  CREATE TABLE "T_ORGANIZATION" ("ID" NUMBER(20,0), "NAME" VARCHAR2(64), "PARENT_ID" NUMBER(20,0) DEFAULT 0, "TYPE" NVARCHAR2(32) DEFAULT 0, "STATE" NVARCHAR2(32) DEFAULT 0, "SORT" NUMBER(11,0) DEFAULT 0, "REMARK" VARCHAR2(255), "RESERVE" VARCHAR2(64), "VERSION" VARCHAR2(64), "CREATE_TIME" TIMESTAMP (6) DEFAULT CURRENT_TIMESTAMP, "CREATE_BY" NUMBER(20,0), "UPDATE_TIME" TIMESTAMP (6), "UPDATE_BY" NUMBER(20,0))

   COMMENT ON COLUMN "T_ORGANIZATION"."ID" IS '主键'
   COMMENT ON COLUMN "T_ORGANIZATION"."NAME" IS '名称'
   COMMENT ON COLUMN "T_ORGANIZATION"."PARENT_ID" IS '上级ID'
   COMMENT ON COLUMN "T_ORGANIZATION"."TYPE" IS '类型'
   COMMENT ON COLUMN "T_ORGANIZATION"."STATE" IS '状态'
   COMMENT ON COLUMN "T_ORGANIZATION"."SORT" IS '排序'
   COMMENT ON COLUMN "T_ORGANIZATION"."REMARK" IS '备注'
   COMMENT ON COLUMN "T_ORGANIZATION"."RESERVE" IS '预留字段1'
   COMMENT ON COLUMN "T_ORGANIZATION"."VERSION" IS '预留字段2'
   COMMENT ON COLUMN "T_ORGANIZATION"."CREATE_TIME" IS '创建时间'
   COMMENT ON COLUMN "T_ORGANIZATION"."CREATE_BY" IS '创建人ID'
   COMMENT ON COLUMN "T_ORGANIZATION"."UPDATE_TIME" IS '更新时间'
   COMMENT ON COLUMN "T_ORGANIZATION"."UPDATE_BY" IS '更新人ID'
   COMMENT ON TABLE "T_ORGANIZATION"  IS '组织架构表'
--------------------------------------------------------
--  DDL for Table T_ORG_ROLE
--------------------------------------------------------

  CREATE TABLE "T_ORG_ROLE" ("ID" NUMBER(20,0), "ORG_ID" NUMBER(20,0), "ROLE_ID" NUMBER(20,0))

   COMMENT ON COLUMN "T_ORG_ROLE"."ID" IS '主键'
   COMMENT ON COLUMN "T_ORG_ROLE"."ORG_ID" IS '组织架构ID'
   COMMENT ON COLUMN "T_ORG_ROLE"."ROLE_ID" IS '角色ID'
   COMMENT ON TABLE "T_ORG_ROLE"  IS '组织-角色'
--------------------------------------------------------
--  DDL for Table T_RESOURCE
--------------------------------------------------------

  CREATE TABLE "T_RESOURCE" ("ID" NUMBER(20,0), "ICON" VARCHAR2(128), "NAME" VARCHAR2(50), "CODE" VARCHAR2(20), "URI" VARCHAR2(128), "PERMS" VARCHAR2(64), "PARENT_ID" NUMBER(20,0) DEFAULT 0, "LEVELS" NVARCHAR2(32) DEFAULT 0, "LEAF" NUMBER(1,0) DEFAULT 1, "OPEN" NUMBER(1,0) DEFAULT 0, "TYPE" NVARCHAR2(32) DEFAULT 0, "SORT" NUMBER(11,0) DEFAULT 0, "STATE" VARCHAR2(32) DEFAULT 0, "REMARK" VARCHAR2(255), "RESERVE" VARCHAR2(64), "VERSION" VARCHAR2(64), "CREATE_TIME" TIMESTAMP (6) DEFAULT CURRENT_TIMESTAMP, "CREATE_BY" NUMBER(20,0), "UPDATE_TIME" TIMESTAMP (6), "UPDATE_BY" NUMBER(20,0))

   COMMENT ON COLUMN "T_RESOURCE"."ID" IS '主键'
   COMMENT ON COLUMN "T_RESOURCE"."ICON" IS '图标'
   COMMENT ON COLUMN "T_RESOURCE"."NAME" IS '名称'
   COMMENT ON COLUMN "T_RESOURCE"."CODE" IS '编号'
   COMMENT ON COLUMN "T_RESOURCE"."URI" IS '跳转链接'
   COMMENT ON COLUMN "T_RESOURCE"."PERMS" IS '权限字符串'
   COMMENT ON COLUMN "T_RESOURCE"."PARENT_ID" IS '上级ID'
   COMMENT ON COLUMN "T_RESOURCE"."LEVELS" IS '菜单层级'
   COMMENT ON COLUMN "T_RESOURCE"."LEAF" IS '是否是叶子'
   COMMENT ON COLUMN "T_RESOURCE"."OPEN" IS '是否打开'
   COMMENT ON COLUMN "T_RESOURCE"."TYPE" IS '类型'
   COMMENT ON COLUMN "T_RESOURCE"."SORT" IS '排序'
   COMMENT ON COLUMN "T_RESOURCE"."STATE" IS '状态'
   COMMENT ON COLUMN "T_RESOURCE"."REMARK" IS '备注'
   COMMENT ON COLUMN "T_RESOURCE"."RESERVE" IS '备用字段1'
   COMMENT ON COLUMN "T_RESOURCE"."VERSION" IS '备用字段2'
   COMMENT ON COLUMN "T_RESOURCE"."CREATE_TIME" IS '创建时间'
   COMMENT ON COLUMN "T_RESOURCE"."CREATE_BY" IS '创建人ID'
   COMMENT ON COLUMN "T_RESOURCE"."UPDATE_TIME" IS '更新时间'
   COMMENT ON COLUMN "T_RESOURCE"."UPDATE_BY" IS '更新人ID'
   COMMENT ON TABLE "T_RESOURCE"  IS '资源表（模块，菜单，按钮，数据）'
--------------------------------------------------------
--  DDL for Table T_ROLE
--------------------------------------------------------

  CREATE TABLE "T_ROLE" ("ID" NUMBER(20,0), "NAME" VARCHAR2(32), "TYPE" NVARCHAR2(32) DEFAULT 0, "SORT" NUMBER(11,0) DEFAULT 0, "PARENT_ID" NUMBER(20,0) DEFAULT 0, "REMARK" VARCHAR2(255), "RESERVE" VARCHAR2(64), "VERSION" VARCHAR2(64), "CREATE_TIME" TIMESTAMP (6) DEFAULT CURRENT_TIMESTAMP, "CREATE_BY" NUMBER(20,0), "UPDATE_TIME" TIMESTAMP (6), "UPDATE_BY" NUMBER(20,0))

   COMMENT ON COLUMN "T_ROLE"."ID" IS '主键'
   COMMENT ON COLUMN "T_ROLE"."NAME" IS '名称'
   COMMENT ON COLUMN "T_ROLE"."TYPE" IS '类型(分组，功能，数据)'
   COMMENT ON COLUMN "T_ROLE"."SORT" IS '排序'
   COMMENT ON COLUMN "T_ROLE"."PARENT_ID" IS '上级ID'
   COMMENT ON COLUMN "T_ROLE"."REMARK" IS '备注'
   COMMENT ON COLUMN "T_ROLE"."RESERVE" IS '备用字段1'
   COMMENT ON COLUMN "T_ROLE"."VERSION" IS '备用字段2'
   COMMENT ON COLUMN "T_ROLE"."CREATE_TIME" IS '创建时间'
   COMMENT ON COLUMN "T_ROLE"."CREATE_BY" IS '创建人ID'
   COMMENT ON COLUMN "T_ROLE"."UPDATE_TIME" IS '更新时间'
   COMMENT ON COLUMN "T_ROLE"."UPDATE_BY" IS '更新人ID'
   COMMENT ON TABLE "T_ROLE"  IS '角色表'
--------------------------------------------------------
--  DDL for Table T_ROLE_RESOURCE
--------------------------------------------------------

  CREATE TABLE "T_ROLE_RESOURCE" ("ID" NUMBER(20,0), "ROLE_ID" NUMBER(20,0), "RESOURCE_ID" NUMBER(20,0))

   COMMENT ON COLUMN "T_ROLE_RESOURCE"."ID" IS '主键'
   COMMENT ON COLUMN "T_ROLE_RESOURCE"."ROLE_ID" IS '角色ID'
   COMMENT ON COLUMN "T_ROLE_RESOURCE"."RESOURCE_ID" IS '资源ID'
   COMMENT ON TABLE "T_ROLE_RESOURCE"  IS '角色资源表'
--------------------------------------------------------
--  DDL for Table T_SUBORDINATE_ACCOUNT
--------------------------------------------------------

  CREATE TABLE "T_SUBORDINATE_ACCOUNT" ("ID" NUMBER(20,0), "USER_ID" NUMBER(20,0), "ACCOUNT" VARCHAR2(64), "SYS_CODE" VARCHAR2(32), "REMARK" VARCHAR2(255), "RESERVE" VARCHAR2(64), "VERSION" VARCHAR2(64), "CREATE_TIME" TIMESTAMP (6) DEFAULT CURRENT_TIMESTAMP, "CREATE_BY" NUMBER(20,0), "UPDATE_TIME" TIMESTAMP (6), "UPDATE_BY" NUMBER(20,0))

   COMMENT ON COLUMN "T_SUBORDINATE_ACCOUNT"."ID" IS '主键'
   COMMENT ON COLUMN "T_SUBORDINATE_ACCOUNT"."USER_ID" IS '用户ID'
   COMMENT ON COLUMN "T_SUBORDINATE_ACCOUNT"."ACCOUNT" IS '账号'
   COMMENT ON COLUMN "T_SUBORDINATE_ACCOUNT"."SYS_CODE" IS '系统编码'
   COMMENT ON COLUMN "T_SUBORDINATE_ACCOUNT"."REMARK" IS '备注'
   COMMENT ON COLUMN "T_SUBORDINATE_ACCOUNT"."RESERVE" IS '备用字段1'
   COMMENT ON COLUMN "T_SUBORDINATE_ACCOUNT"."VERSION" IS '备用字段2'
   COMMENT ON COLUMN "T_SUBORDINATE_ACCOUNT"."CREATE_TIME" IS '创建时间'
   COMMENT ON COLUMN "T_SUBORDINATE_ACCOUNT"."CREATE_BY" IS '创建人ID'
   COMMENT ON COLUMN "T_SUBORDINATE_ACCOUNT"."UPDATE_TIME" IS '更新时间'
   COMMENT ON COLUMN "T_SUBORDINATE_ACCOUNT"."UPDATE_BY" IS '更新人ID'
   COMMENT ON TABLE "T_SUBORDINATE_ACCOUNT"  IS '从账号'
--------------------------------------------------------
--  DDL for Table T_USER
--------------------------------------------------------

  CREATE TABLE "T_USER" ("ID" NUMBER(20,0), "NAME" VARCHAR2(64), "EMP_NO" VARCHAR2(32), "PHONE" VARCHAR2(32), "EMAIL" VARCHAR2(128), "PASSWORD" VARCHAR2(64), "SALT" VARCHAR2(64), "AVATAR" VARCHAR2(128), "HONOR" VARCHAR2(128), "TYPE" NVARCHAR2(32) DEFAULT 0, "STATE" NVARCHAR2(32) DEFAULT 0, "CREATE_BY" NUMBER(20,0), "CREATE_TIME" TIMESTAMP (6) DEFAULT CURRENT_TIMESTAMP, "REMARK" VARCHAR2(512), "RESERVE" VARCHAR2(64), "VERSION" VARCHAR2(64), "UPDATE_TIME" TIMESTAMP (6), "UPDATE_BY" NUMBER(20,0))

   COMMENT ON COLUMN "T_USER"."ID" IS '主键'
   COMMENT ON COLUMN "T_USER"."NAME" IS '名称'
   COMMENT ON COLUMN "T_USER"."EMP_NO" IS '员工号'
   COMMENT ON COLUMN "T_USER"."PHONE" IS '电话号码'
   COMMENT ON COLUMN "T_USER"."EMAIL" IS '邮件'
   COMMENT ON COLUMN "T_USER"."PASSWORD" IS '密码'
   COMMENT ON COLUMN "T_USER"."SALT" IS '密码盐'
   COMMENT ON COLUMN "T_USER"."AVATAR" IS '头像'
   COMMENT ON COLUMN "T_USER"."HONOR" IS '头衔'
   COMMENT ON COLUMN "T_USER"."TYPE" IS '类型'
   COMMENT ON COLUMN "T_USER"."STATE" IS '状态'
   COMMENT ON COLUMN "T_USER"."CREATE_BY" IS '创建人ID'
   COMMENT ON COLUMN "T_USER"."CREATE_TIME" IS '创建时间'
   COMMENT ON COLUMN "T_USER"."REMARK" IS '备注'
   COMMENT ON COLUMN "T_USER"."RESERVE" IS '备用字段1'
   COMMENT ON COLUMN "T_USER"."VERSION" IS '备用字段2'
   COMMENT ON COLUMN "T_USER"."UPDATE_TIME" IS '更新时间'
   COMMENT ON COLUMN "T_USER"."UPDATE_BY" IS '更新人ID'
   COMMENT ON TABLE "T_USER"  IS '用户表'
--------------------------------------------------------
--  Constraints for Table T_RESOURCE
--------------------------------------------------------

  ALTER TABLE "T_RESOURCE" MODIFY ("ID" NOT NULL ENABLE)
  ALTER TABLE "T_RESOURCE" ADD PRIMARY KEY ("ID") USING INDEX  ENABLE
--------------------------------------------------------
--  Constraints for Table T_DEPUTY_ACCOUNT_ROLE
--------------------------------------------------------

  ALTER TABLE "T_DEPUTY_ACCOUNT_ROLE" MODIFY ("ID" NOT NULL ENABLE)
  ALTER TABLE "T_DEPUTY_ACCOUNT_ROLE" ADD PRIMARY KEY ("ID") USING INDEX  ENABLE
--------------------------------------------------------
--  Constraints for Table T_USER
--------------------------------------------------------

  ALTER TABLE "T_USER" MODIFY ("ID" NOT NULL ENABLE)
  ALTER TABLE "T_USER" ADD PRIMARY KEY ("ID") USING INDEX  ENABLE
--------------------------------------------------------
--  Constraints for Table T_DEPUTY_ACCOUNT
--------------------------------------------------------

  ALTER TABLE "T_DEPUTY_ACCOUNT" MODIFY ("ID" NOT NULL ENABLE)
  ALTER TABLE "T_DEPUTY_ACCOUNT" ADD PRIMARY KEY ("ID") USING INDEX  ENABLE
--------------------------------------------------------
--  Constraints for Table T_ROLE
--------------------------------------------------------

  ALTER TABLE "T_ROLE" MODIFY ("ID" NOT NULL ENABLE)
  ALTER TABLE "T_ROLE" ADD PRIMARY KEY ("ID") USING INDEX  ENABLE
--------------------------------------------------------
--  Constraints for Table T_BASE_USER
--------------------------------------------------------

  ALTER TABLE "T_BASE_USER" MODIFY ("ID" NOT NULL ENABLE)
  ALTER TABLE "T_BASE_USER" ADD PRIMARY KEY ("ID") USING INDEX  ENABLE
  ALTER TABLE "T_BASE_USER" MODIFY ("GENDER" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table T_SUBORDINATE_ACCOUNT
--------------------------------------------------------

  ALTER TABLE "T_SUBORDINATE_ACCOUNT" MODIFY ("ID" NOT NULL ENABLE)
  ALTER TABLE "T_SUBORDINATE_ACCOUNT" ADD PRIMARY KEY ("ID") USING INDEX  ENABLE
--------------------------------------------------------
--  Constraints for Table T_DICT
--------------------------------------------------------

  ALTER TABLE "T_DICT" MODIFY ("ID" NOT NULL ENABLE)
  ALTER TABLE "T_DICT" ADD PRIMARY KEY ("ID") USING INDEX  ENABLE
--------------------------------------------------------
--  Constraints for Table T_ORGANIZATION
--------------------------------------------------------

  ALTER TABLE "T_ORGANIZATION" MODIFY ("ID" NOT NULL ENABLE)
  ALTER TABLE "T_ORGANIZATION" ADD PRIMARY KEY ("ID") USING INDEX  ENABLE
  ALTER TABLE "T_ORGANIZATION" MODIFY ("PARENT_ID" NOT NULL ENABLE)
  ALTER TABLE "T_ORGANIZATION" MODIFY ("TYPE" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table T_ORG_ROLE
--------------------------------------------------------

  ALTER TABLE "T_ORG_ROLE" MODIFY ("ID" NOT NULL ENABLE)
  ALTER TABLE "T_ORG_ROLE" ADD PRIMARY KEY ("ID") USING INDEX  ENABLE
--------------------------------------------------------
--  Constraints for Table T_ROLE_RESOURCE
--------------------------------------------------------

  ALTER TABLE "T_ROLE_RESOURCE" MODIFY ("ID" NOT NULL ENABLE)
  ALTER TABLE "T_ROLE_RESOURCE" ADD PRIMARY KEY ("ID") USING INDEX  ENABLE
