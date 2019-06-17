--------------------------------------------
-- Export file for user OA_SELF@YKY_TEST  --
-- Created by qifu on 2019/5/23, 18:05:28 --
--------------------------------------------

--set define off
--spool oa_self建表语句.log

--prompt
--prompt Creating table FAST_WAY
--prompt =======================
--prompt
create table FAST_WAY
(
  id               NUMBER(20) not null,
  pic_url          VARCHAR2(200),
  apply_name       VARCHAR2(300),
  apply_sort       VARCHAR2(1),
  apply_datetime   DATE default SYSDATE,
  apply_updatetime DATE default SYSDATE,
  apply_url        VARCHAR2(255)
)
;
comment on table FAST_WAY
  is '快速通道表';
comment on column FAST_WAY.id
  is '自增长id';
comment on column FAST_WAY.pic_url
  is '图片URL';
comment on column FAST_WAY.apply_name
  is '应用名称';
comment on column FAST_WAY.apply_sort
  is '应用排序';
comment on column FAST_WAY.apply_datetime
  is '发布时间，默认系统时间';
comment on column FAST_WAY.apply_updatetime
  is '更新时间，默认系统时间';
comment on column FAST_WAY.apply_url
  is '跳转url';
alter table FAST_WAY
  add primary key (ID);

--prompt
--prompt Creating table OA_APPLY_CENTER
--prompt ==============================
--prompt
create table OA_APPLY_CENTER
(
  apply_id         NUMBER(20) not null,
  pic_url          VARCHAR2(200),
  apply_name       VARCHAR2(300),
  apply_sort       VARCHAR2(30),
  is_visiable      VARCHAR2(1),
  apply_datetime   DATE default SYSDATE,
  apply_updatetime DATE default SYSDATE,
  type_name        VARCHAR2(300),
  del_flag         VARCHAR2(1) default 0,
  apply_url        VARCHAR2(255),
  apply_code       VARCHAR2(255)
)
;
comment on table OA_APPLY_CENTER
  is '应用中心表';
comment on column OA_APPLY_CENTER.apply_id
  is '自增长id';
comment on column OA_APPLY_CENTER.pic_url
  is '图片URL，常用流程中该字段为空';
comment on column OA_APPLY_CENTER.apply_name
  is '应用名称';
comment on column OA_APPLY_CENTER.apply_sort
  is '应用排序';
comment on column OA_APPLY_CENTER.is_visiable
  is '是否可见，1表示可见，0表示不可见';
comment on column OA_APPLY_CENTER.apply_datetime
  is '发布时间，默认系统时间';
comment on column OA_APPLY_CENTER.apply_updatetime
  is '更新时间，默认系统时间';
comment on column OA_APPLY_CENTER.type_name
  is '类型，应用中心或常用流程';
comment on column OA_APPLY_CENTER.del_flag
  is '删除状态，默认0，表示未删除';
comment on column OA_APPLY_CENTER.apply_url
  is '跳转到其他系统的url';
comment on column OA_APPLY_CENTER.apply_code
  is '对应applyName，字典表中查询';
alter table OA_APPLY_CENTER
  add primary key (APPLY_ID);

--prompt
--prompt Creating table OA_ASSISTANT_MAPPING
--prompt ===================================
--prompt
create table OA_ASSISTANT_MAPPING
(
  id               NUMBER not null,
  user_id          VARCHAR2(60),
  user_name        VARCHAR2(60),
  leader_id        VARCHAR2(60),
  leader_name      VARCHAR2(60),
  leader_dept_id   VARCHAR2(60),
  leader_dept_name VARCHAR2(60),
  enable_flag      VARCHAR2(60),
  create_by        VARCHAR2(300),
  create_time      TIMESTAMP(6),
  update_by        VARCHAR2(300),
  update_time      TIMESTAMP(6),
  ext1             VARCHAR2(300),
  ext2             VARCHAR2(300),
  ext3             VARCHAR2(300),
  ext4             VARCHAR2(300),
  ext5             VARCHAR2(300)
)
;
comment on column OA_ASSISTANT_MAPPING.id
  is '主键';
comment on column OA_ASSISTANT_MAPPING.user_id
  is '秘书ID';
comment on column OA_ASSISTANT_MAPPING.user_name
  is '秘书名称';
comment on column OA_ASSISTANT_MAPPING.leader_id
  is '领导ID';
comment on column OA_ASSISTANT_MAPPING.leader_name
  is '领导名称';
comment on column OA_ASSISTANT_MAPPING.leader_dept_id
  is '领导所属部门ID';
comment on column OA_ASSISTANT_MAPPING.leader_dept_name
  is '领导所属部门名称';
comment on column OA_ASSISTANT_MAPPING.enable_flag
  is '是否有效';
comment on column OA_ASSISTANT_MAPPING.create_by
  is '创建人';
comment on column OA_ASSISTANT_MAPPING.create_time
  is '创建时间';
comment on column OA_ASSISTANT_MAPPING.update_by
  is '最后更新人';
comment on column OA_ASSISTANT_MAPPING.update_time
  is '最后更新时间';
comment on column OA_ASSISTANT_MAPPING.ext1
  is '备用字段';
comment on column OA_ASSISTANT_MAPPING.ext2
  is '备用字段';
comment on column OA_ASSISTANT_MAPPING.ext3
  is '备用字段';
comment on column OA_ASSISTANT_MAPPING.ext4
  is '备用字段';
comment on column OA_ASSISTANT_MAPPING.ext5
  is '备用字段';
alter table OA_ASSISTANT_MAPPING
  add primary key (ID);

--prompt
--prompt Creating table OA_ATTACHMENT
--prompt ============================
--prompt
create table OA_ATTACHMENT
(
  id             NUMBER(20) not null,
  busicess_id    VARCHAR2(20),
  busicess_name  VARCHAR2(100),
  attach_name    VARCHAR2(300),
  attach_url     VARCHAR2(1000),
  del_flag       VARCHAR2(1) default 0,
  attach_path    VARCHAR2(255),
  apply_datetime DATE default sysdate,
  file_id        VARCHAR2(50)
)
;
comment on table OA_ATTACHMENT
  is '附件表';
comment on column OA_ATTACHMENT.id
  is '附件表id';
comment on column OA_ATTACHMENT.busicess_id
  is '业务ID，例如新闻id, 公告id';
comment on column OA_ATTACHMENT.busicess_name
  is '业务类型名称，例如新闻、公告';
comment on column OA_ATTACHMENT.attach_name
  is '附件名';
comment on column OA_ATTACHMENT.attach_url
  is '附件URL';
comment on column OA_ATTACHMENT.del_flag
  is '是否删除 默认0，表示未删除';
comment on column OA_ATTACHMENT.attach_path
  is '附件路径';
comment on column OA_ATTACHMENT.apply_datetime
  is '附件日期';
comment on column OA_ATTACHMENT.file_id
  is '文件服务器存储ID';
alter table OA_ATTACHMENT
  add primary key (ID);

--prompt
--prompt Creating table OA_CALENDER
--prompt ==========================
--prompt
create table OA_CALENDER
(
  id               NUMBER(20) not null,
  title            VARCHAR2(50),
  apply_dept_id    VARCHAR2(50),
  apply_dept_name  VARCHAR2(50),
  apply_user_id    VARCHAR2(50),
  apply_user_name  VARCHAR2(50),
  apply_datetime   DATE default sysdate,
  urgent_level     VARCHAR2(50),
  del_flag         NUMBER(1) default 0,
  approval_status  VARCHAR2(50) default 'draft',
  isneed_bussiness NUMBER(1) default 0,
  update_datetime  DATE,
  update_user_name VARCHAR2(50),
  update_user_id   VARCHAR2(50),
  order_num        VARCHAR2(50)
)
;
comment on table OA_CALENDER
  is '校历';
comment on column OA_CALENDER.id
  is '校历ID，主键';
comment on column OA_CALENDER.title
  is '校历标题';
comment on column OA_CALENDER.apply_dept_id
  is '申请部门id';
comment on column OA_CALENDER.apply_dept_name
  is '申请部门名称';
comment on column OA_CALENDER.apply_user_id
  is '申请人员id';
comment on column OA_CALENDER.apply_user_name
  is '申请人员名称';
comment on column OA_CALENDER.apply_datetime
  is '申请日期，默认当前日期';
comment on column OA_CALENDER.urgent_level
  is '紧急程度，默认0，一般';
comment on column OA_CALENDER.del_flag
  is '是否删除，默认0否，1，是，0，否';
comment on column OA_CALENDER.approval_status
  is '审批状态，默认0，0，审判中，1，审批完成';
comment on column OA_CALENDER.isneed_bussiness
  is '是否需要业务负责人审批，默认0否，1，是，0，否';
comment on column OA_CALENDER.update_datetime
  is '更新时间';
comment on column OA_CALENDER.update_user_name
  is '更新人姓名';
comment on column OA_CALENDER.update_user_id
  is '更新人id';
comment on column OA_CALENDER.order_num
  is '流水号';
alter table OA_CALENDER
  add primary key (ID);

--prompt
--prompt Creating table OA_CRON
--prompt ======================
--prompt
create table OA_CRON
(
  id          NUMBER not null,
  time_stamp  VARCHAR2(50),
  mss_id      VARCHAR2(30),
  cron        VARCHAR2(30),
  create_time TIMESTAMP(6),
  create_by   VARCHAR2(300)
)
;
alter table OA_CRON
  add primary key (ID);

--prompt
--prompt Creating table OA_DZGG_INTERFACE_TEMP
--prompt =====================================
--prompt
create table OA_DZGG_INTERFACE_TEMP
(
  request_id       NUMBER(20) not null,
  notice_no        VARCHAR2(50),
  apply_date       DATE default SYSDATE,
  type_name        VARCHAR2(50),
  sort_name        VARCHAR2(50),
  is_read          NUMBER(1) default 0,
  notice_title     VARCHAR2(50),
  notice_content   CLOB,
  notice_src       VARCHAR2(255),
  apply_org_code   VARCHAR2(255),
  apply_org_name   VARCHAR2(255),
  apply_user_name  VARCHAR2(255),
  apply_user_empno VARCHAR2(255)
)
;
comment on table OA_DZGG_INTERFACE_TEMP
  is '通知公告类流程对接新门户接口表';
comment on column OA_DZGG_INTERFACE_TEMP.request_id
  is '流程实例id';
comment on column OA_DZGG_INTERFACE_TEMP.notice_no
  is '通知公告编号，规则：DZGG+年月+3位流水';
comment on column OA_DZGG_INTERFACE_TEMP.apply_date
  is '申请日期';
comment on column OA_DZGG_INTERFACE_TEMP.type_name
  is '类型，可选：通知公告、规章制度';
comment on column OA_DZGG_INTERFACE_TEMP.sort_name
  is '分类，当“通知公告”时可选择：行政通知、人事任免、会议通知、规章制度通知、其他通知公告；当“规章制度”时可选择：办公制度、财务制度、人事制度、科技管理制度、其他制度';
comment on column OA_DZGG_INTERFACE_TEMP.is_read
  is '读取状态，默认0，表示未读；新门户读取成功后，网站系统将其更新为1';
comment on column OA_DZGG_INTERFACE_TEMP.notice_title
  is '通知公告/规则制度标题';
comment on column OA_DZGG_INTERFACE_TEMP.notice_content
  is '通知公告/规则制度正文';
comment on column OA_DZGG_INTERFACE_TEMP.notice_src
  is '通知来源';
comment on column OA_DZGG_INTERFACE_TEMP.apply_org_code
  is '申请部门code';
comment on column OA_DZGG_INTERFACE_TEMP.apply_org_name
  is '申请部门名称';
comment on column OA_DZGG_INTERFACE_TEMP.apply_user_name
  is '申请用户名';
comment on column OA_DZGG_INTERFACE_TEMP.apply_user_empno
  is '用户员工编号';
alter table OA_DZGG_INTERFACE_TEMP
  add primary key (REQUEST_ID);

--prompt
--prompt Creating table OA_DZGG_INTERFACE_TEMP_ATTACH
--prompt ============================================
--prompt
create table OA_DZGG_INTERFACE_TEMP_ATTACH
(
  request_id   VARCHAR2(50),
  file_name    VARCHAR2(50),
  file_address VARCHAR2(300)
)
;
comment on table OA_DZGG_INTERFACE_TEMP_ATTACH
  is '泛微公告同步临时附件表';
comment on column OA_DZGG_INTERFACE_TEMP_ATTACH.request_id
  is '流程实例id';
comment on column OA_DZGG_INTERFACE_TEMP_ATTACH.file_name
  is '文件名称';
comment on column OA_DZGG_INTERFACE_TEMP_ATTACH.file_address
  is '文件地址';

--prompt
--prompt Creating table OA_INFOCHANGE
--prompt ============================
--prompt
create table OA_INFOCHANGE
(
  id               NUMBER(20) not null,
  title            VARCHAR2(50),
  apply_dept_id    VARCHAR2(50),
  apply_dept_name  VARCHAR2(50),
  apply_user_id    VARCHAR2(50),
  apply_user_name  VARCHAR2(50),
  apply_datetime   DATE default sysdate,
  urgent_level     VARCHAR2(50),
  del_flag         NUMBER(1) default 0,
  approval_status  VARCHAR2(50) default 'draft',
  isneed_bussiness NUMBER(1) default 0,
  update_datetime  DATE,
  update_user_name VARCHAR2(50),
  update_user_id   VARCHAR2(50),
  order_num        VARCHAR2(50)
)
;
comment on table OA_INFOCHANGE
  is '信息交流';
comment on column OA_INFOCHANGE.id
  is '信息交流ID，主键';
comment on column OA_INFOCHANGE.title
  is '信息交流标题';
comment on column OA_INFOCHANGE.apply_dept_id
  is '申请部门id';
comment on column OA_INFOCHANGE.apply_dept_name
  is '申请部门名称';
comment on column OA_INFOCHANGE.apply_user_id
  is '申请人员id';
comment on column OA_INFOCHANGE.apply_user_name
  is '申请人员名称';
comment on column OA_INFOCHANGE.apply_datetime
  is '申请日期，默认当前日期';
comment on column OA_INFOCHANGE.urgent_level
  is '紧急程度，默认0，一般';
comment on column OA_INFOCHANGE.del_flag
  is '是否删除，默认0否，1，是，0，否';
comment on column OA_INFOCHANGE.approval_status
  is '审批状态，默认0，0，审判中，1，审批完成';
comment on column OA_INFOCHANGE.isneed_bussiness
  is '是否需要业务负责人审批，默认0否，1，是，0，否';
comment on column OA_INFOCHANGE.update_datetime
  is '更新时间';
comment on column OA_INFOCHANGE.update_user_name
  is '更新人姓名';
comment on column OA_INFOCHANGE.update_user_id
  is '更新人id';
comment on column OA_INFOCHANGE.order_num
  is '流水号';

--prompt
--prompt Creating table OA_MEETING_ADDRESS
--prompt =================================
--prompt
create table OA_MEETING_ADDRESS
(
  id                NUMBER not null,
  meeting_id        NUMBER,
  room_id           VARCHAR2(60),
  address           VARCHAR2(300),
  room_name         VARCHAR2(300),
  room_num          VARCHAR2(60),
  force_back        VARCHAR2(10),
  logistics_manager VARCHAR2(10),
  create_by         VARCHAR2(300),
  create_time       TIMESTAMP(6),
  update_by         VARCHAR2(300),
  update_time       TIMESTAMP(6),
  ext1              VARCHAR2(300),
  ext2              VARCHAR2(300),
  ext3              VARCHAR2(300),
  ext4              VARCHAR2(300),
  ext5              VARCHAR2(300)
)
;
comment on column OA_MEETING_ADDRESS.id
  is '主键';
comment on column OA_MEETING_ADDRESS.meeting_id
  is '会议主键';
comment on column OA_MEETING_ADDRESS.room_id
  is '会议室主键';
comment on column OA_MEETING_ADDRESS.address
  is '会议室地址';
comment on column OA_MEETING_ADDRESS.room_name
  is '会议室名称';
comment on column OA_MEETING_ADDRESS.room_num
  is '会议室编号';
comment on column OA_MEETING_ADDRESS.force_back
  is '是否强制备案';
comment on column OA_MEETING_ADDRESS.logistics_manager
  is '是否后勤统管';
comment on column OA_MEETING_ADDRESS.create_by
  is '创建人';
comment on column OA_MEETING_ADDRESS.create_time
  is '创建时间';
comment on column OA_MEETING_ADDRESS.update_by
  is '最后更新人';
comment on column OA_MEETING_ADDRESS.update_time
  is '最后更新时间';
comment on column OA_MEETING_ADDRESS.ext1
  is '备用字段';
comment on column OA_MEETING_ADDRESS.ext2
  is '备用字段';
comment on column OA_MEETING_ADDRESS.ext3
  is '备用字段';
comment on column OA_MEETING_ADDRESS.ext4
  is '备用字段';
comment on column OA_MEETING_ADDRESS.ext5
  is '备用字段';
alter table OA_MEETING_ADDRESS
  add primary key (ID);

--prompt
--prompt Creating table OA_MEETING_ARRGE
--prompt ===============================
--prompt
create table OA_MEETING_ARRGE
(
  id               NUMBER(20) not null,
  title            VARCHAR2(50),
  apply_dept_id    VARCHAR2(50),
  apply_dept_name  VARCHAR2(50),
  apply_user_id    VARCHAR2(50),
  apply_user_name  VARCHAR2(50),
  apply_datetime   DATE default sysdate,
  urgent_level     VARCHAR2(50),
  del_flag         NUMBER(1) default 0,
  approval_status  VARCHAR2(50) default 'draft',
  isneed_bussiness NUMBER(1) default 0,
  update_datetime  DATE,
  update_user_name VARCHAR2(50),
  update_user_id   VARCHAR2(50),
  order_num        VARCHAR2(50)
)
;
comment on table OA_MEETING_ARRGE
  is '会议安排';
comment on column OA_MEETING_ARRGE.id
  is '会议安排ID，主键';
comment on column OA_MEETING_ARRGE.title
  is '会议安排标题';
comment on column OA_MEETING_ARRGE.apply_dept_id
  is '申请部门id';
comment on column OA_MEETING_ARRGE.apply_dept_name
  is '申请部门名称';
comment on column OA_MEETING_ARRGE.apply_user_id
  is '申请人员id';
comment on column OA_MEETING_ARRGE.apply_user_name
  is '申请人员名称';
comment on column OA_MEETING_ARRGE.apply_datetime
  is '申请日期，默认当前日期';
comment on column OA_MEETING_ARRGE.urgent_level
  is '紧急程度，默认0，一般';
comment on column OA_MEETING_ARRGE.del_flag
  is '是否删除，默认0否，1，是，0，否';
comment on column OA_MEETING_ARRGE.approval_status
  is '审批状态，默认0，0，审判中，1，审批完成';
comment on column OA_MEETING_ARRGE.isneed_bussiness
  is '是否需要业务负责人审批，默认0否，1，是，0，否';
comment on column OA_MEETING_ARRGE.update_datetime
  is '更新时间';
comment on column OA_MEETING_ARRGE.update_user_name
  is '更新人姓名';
comment on column OA_MEETING_ARRGE.update_user_id
  is '更新人id';
comment on column OA_MEETING_ARRGE.order_num
  is '流水号';

--prompt
--prompt Creating table OA_MEETING_HEADER
--prompt ================================
--prompt
create table OA_MEETING_HEADER
(
  id                 NUMBER not null,
  meeting_no         VARCHAR2(60),
  meeting_title      VARCHAR2(300),
  meeting_content    VARCHAR2(3000),
  emergency          VARCHAR2(10),
  create_by_name     VARCHAR2(60),
  dept_id            VARCHAR2(60),
  dept_name          VARCHAR2(60),
  contact_user_id    VARCHAR2(60),
  contact_user_name  VARCHAR2(60),
  contact_telphone   VARCHAR2(60),
  contact_email      VARCHAR2(60),
  meeting_start_date VARCHAR2(60),
  meeting_end_date   VARCHAR2(60),
  start_time         VARCHAR2(60),
  end_time           VARCHAR2(60),
  meeting_type       VARCHAR2(60),
  out_members        VARCHAR2(600),
  is_back            VARCHAR2(10),
  is_notice          VARCHAR2(10),
  meeting_resouce    VARCHAR2(600),
  remarks            VARCHAR2(600),
  create_by          VARCHAR2(300),
  create_time        TIMESTAMP(6),
  update_by          VARCHAR2(300),
  update_time        TIMESTAMP(6),
  ext1               VARCHAR2(300),
  ext2               VARCHAR2(300),
  ext3               VARCHAR2(300),
  ext4               VARCHAR2(300),
  ext5               VARCHAR2(300)
)
;
comment on column OA_MEETING_HEADER.id
  is '主键';
comment on column OA_MEETING_HEADER.meeting_title
  is '会议主题';
comment on column OA_MEETING_HEADER.meeting_content
  is '会议内容';
comment on column OA_MEETING_HEADER.emergency
  is '紧急程度';
comment on column OA_MEETING_HEADER.create_by_name
  is '申请人姓名';
comment on column OA_MEETING_HEADER.dept_id
  is '申请人部门ID';
comment on column OA_MEETING_HEADER.dept_name
  is '申请人部门名称';
comment on column OA_MEETING_HEADER.contact_user_id
  is '联系人';
comment on column OA_MEETING_HEADER.contact_user_name
  is '联系人姓名';
comment on column OA_MEETING_HEADER.contact_telphone
  is '联系人联系电话';
comment on column OA_MEETING_HEADER.contact_email
  is '联系人联系EMAIL';
comment on column OA_MEETING_HEADER.meeting_start_date
  is '会议时间';
comment on column OA_MEETING_HEADER.meeting_end_date
  is '会议结束日期';
comment on column OA_MEETING_HEADER.start_time
  is '会议开始时间';
comment on column OA_MEETING_HEADER.end_time
  is '会议结束时间';
comment on column OA_MEETING_HEADER.meeting_type
  is '会议类型';
comment on column OA_MEETING_HEADER.out_members
  is '外部参会人员';
comment on column OA_MEETING_HEADER.is_back
  is '是否需要备案';
comment on column OA_MEETING_HEADER.is_notice
  is '是否发送参会通知';
comment on column OA_MEETING_HEADER.meeting_resouce
  is '会议资源';
comment on column OA_MEETING_HEADER.remarks
  is '备注';
comment on column OA_MEETING_HEADER.create_by
  is '创建人';
comment on column OA_MEETING_HEADER.create_time
  is '创建时间';
comment on column OA_MEETING_HEADER.update_by
  is '最后更新人';
comment on column OA_MEETING_HEADER.update_time
  is '最后更新时间';
comment on column OA_MEETING_HEADER.ext1
  is '备用字段';
comment on column OA_MEETING_HEADER.ext2
  is '备用字段';
comment on column OA_MEETING_HEADER.ext3
  is '备用字段';
comment on column OA_MEETING_HEADER.ext4
  is '备用字段';
comment on column OA_MEETING_HEADER.ext5
  is '备用字段';
alter table OA_MEETING_HEADER
  add primary key (ID);

--prompt
--prompt Creating table OA_MEETING_MEMBERS
--prompt =================================
--prompt
create table OA_MEETING_MEMBERS
(
  id          NUMBER not null,
  meeting_id  NUMBER,
  user_id     VARCHAR2(60),
  user_name   VARCHAR2(60),
  dept_id     VARCHAR2(60),
  dept_name   VARCHAR2(100),
  telphone    VARCHAR2(60),
  email       VARCHAR2(60),
  create_by   VARCHAR2(300),
  create_time TIMESTAMP(6),
  update_by   VARCHAR2(300),
  update_time TIMESTAMP(6),
  ext1        VARCHAR2(300),
  ext2        VARCHAR2(300),
  ext3        VARCHAR2(300),
  ext4        VARCHAR2(300),
  ext5        VARCHAR2(300)
)
;
comment on column OA_MEETING_MEMBERS.id
  is '主键';
comment on column OA_MEETING_MEMBERS.meeting_id
  is '会议主键';
comment on column OA_MEETING_MEMBERS.user_id
  is '参会人员ID';
comment on column OA_MEETING_MEMBERS.user_name
  is '参会人员姓名';
comment on column OA_MEETING_MEMBERS.dept_id
  is '参会人员所属部门';
comment on column OA_MEETING_MEMBERS.dept_name
  is '参会人员所属部门名称';
comment on column OA_MEETING_MEMBERS.telphone
  is '联系电话';
comment on column OA_MEETING_MEMBERS.email
  is '联系邮箱';
comment on column OA_MEETING_MEMBERS.create_by
  is '创建人';
comment on column OA_MEETING_MEMBERS.create_time
  is '创建时间';
comment on column OA_MEETING_MEMBERS.update_by
  is '最后更新人';
comment on column OA_MEETING_MEMBERS.update_time
  is '最后更新时间';
comment on column OA_MEETING_MEMBERS.ext1
  is '备用字段';
comment on column OA_MEETING_MEMBERS.ext2
  is '备用字段';
comment on column OA_MEETING_MEMBERS.ext3
  is '备用字段';
comment on column OA_MEETING_MEMBERS.ext4
  is '备用字段';
comment on column OA_MEETING_MEMBERS.ext5
  is '备用字段';
alter table OA_MEETING_MEMBERS
  add primary key (ID);

--prompt
--prompt Creating table OA_MEETING_RECORD
--prompt ================================
--prompt
create table OA_MEETING_RECORD
(
  id               NUMBER(20) not null,
  title            VARCHAR2(50),
  apply_dept_id    VARCHAR2(50),
  apply_dept_name  VARCHAR2(50),
  apply_user_id    VARCHAR2(50),
  apply_user_name  VARCHAR2(50),
  apply_datetime   DATE default sysdate,
  del_flag         NUMBER(1) default 0,
  isneed_bussiness NUMBER(1) default 0,
  bussiness_id     VARCHAR2(50),
  bussiness_name   VARCHAR2(50),
  urgent_level     VARCHAR2(50),
  approval_status  VARCHAR2(50) default 'draft',
  update_datetime  DATE,
  update_user_name VARCHAR2(50),
  update_user_id   VARCHAR2(50),
  order_num        VARCHAR2(50)
)
;
comment on table OA_MEETING_RECORD
  is '校历';
comment on column OA_MEETING_RECORD.id
  is '会议纪要ID，主键';
comment on column OA_MEETING_RECORD.title
  is '会议纪要标题';
comment on column OA_MEETING_RECORD.apply_dept_id
  is '申请部门id';
comment on column OA_MEETING_RECORD.apply_dept_name
  is '申请部门名称';
comment on column OA_MEETING_RECORD.apply_user_id
  is '申请人员id';
comment on column OA_MEETING_RECORD.apply_user_name
  is '申请人员名称';
comment on column OA_MEETING_RECORD.apply_datetime
  is '申请日期，默认当前日期';
comment on column OA_MEETING_RECORD.del_flag
  is '是否删除，默认0否，1，是，0，否';
comment on column OA_MEETING_RECORD.isneed_bussiness
  is '是否需要业务负责人审批，默认0否，1，是，0，否';
comment on column OA_MEETING_RECORD.bussiness_id
  is '业务负责人id';
comment on column OA_MEETING_RECORD.bussiness_name
  is '业务负责人姓名';
comment on column OA_MEETING_RECORD.urgent_level
  is '紧急程度，默认0，一般';
comment on column OA_MEETING_RECORD.approval_status
  is '审批状态，默认0，0，审判中，1，审批完成';
comment on column OA_MEETING_RECORD.update_datetime
  is '更新时间';
comment on column OA_MEETING_RECORD.update_user_name
  is '更新人姓名';
comment on column OA_MEETING_RECORD.update_user_id
  is '更新人id';
comment on column OA_MEETING_RECORD.order_num
  is '流水号';
alter table OA_MEETING_RECORD
  add primary key (ID);

--prompt
--prompt Creating table OA_MEETING_ROOM
--prompt ==============================
--prompt
create table OA_MEETING_ROOM
(
  id                NUMBER not null,
  room_num          VARCHAR2(60),
  room_name         VARCHAR2(300),
  address           VARCHAR2(300),
  force_back        VARCHAR2(10),
  logistics_manager VARCHAR2(10),
  max_persons       NUMBER,
  dept_id           VARCHAR2(60),
  dept_name         VARCHAR2(300),
  room_status       VARCHAR2(60),
  room_resource     VARCHAR2(300),
  room_duty_id      VARCHAR2(60),
  room_duty_name    VARCHAR2(300),
  order_num         NUMBER,
  room_desc         VARCHAR2(600),
  create_by         VARCHAR2(300),
  create_time       TIMESTAMP(6),
  update_by         VARCHAR2(300),
  update_time       TIMESTAMP(6),
  ext1              VARCHAR2(300),
  ext2              VARCHAR2(300),
  ext3              VARCHAR2(300),
  ext4              VARCHAR2(300),
  ext5              VARCHAR2(300)
)
;
comment on column OA_MEETING_ROOM.id
  is '主键';
comment on column OA_MEETING_ROOM.room_num
  is '会议室编号';
comment on column OA_MEETING_ROOM.room_name
  is '会议室地址';
comment on column OA_MEETING_ROOM.address
  is '会议室名称';
comment on column OA_MEETING_ROOM.force_back
  is '是否强制备案';
comment on column OA_MEETING_ROOM.logistics_manager
  is '是否后勤统管';
comment on column OA_MEETING_ROOM.max_persons
  is '可容纳人数';
comment on column OA_MEETING_ROOM.dept_id
  is '所属机构';
comment on column OA_MEETING_ROOM.dept_name
  is '所属机构名称';
comment on column OA_MEETING_ROOM.room_status
  is '会议室状态';
comment on column OA_MEETING_ROOM.room_resource
  is '会议室设备';
comment on column OA_MEETING_ROOM.room_duty_id
  is '会议室负责人';
comment on column OA_MEETING_ROOM.room_duty_name
  is '会议室负责人姓名';
comment on column OA_MEETING_ROOM.order_num
  is '排序号';
comment on column OA_MEETING_ROOM.room_desc
  is '会议室描述';
comment on column OA_MEETING_ROOM.create_by
  is '创建人';
comment on column OA_MEETING_ROOM.create_time
  is '创建时间';
comment on column OA_MEETING_ROOM.update_by
  is '最后更新人';
comment on column OA_MEETING_ROOM.update_time
  is '最后更新时间';
comment on column OA_MEETING_ROOM.ext1
  is '备用字段';
comment on column OA_MEETING_ROOM.ext2
  is '备用字段';
comment on column OA_MEETING_ROOM.ext3
  is '备用字段';
comment on column OA_MEETING_ROOM.ext4
  is '备用字段';
comment on column OA_MEETING_ROOM.ext5
  is '备用字段';
alter table OA_MEETING_ROOM
  add primary key (ID);

--prompt
--prompt Creating table OA_MSS_INFO
--prompt ==========================
--prompt
create table OA_MSS_INFO
(
  id             NUMBER not null,
  mss_title      VARCHAR2(1000),
  mss_content    CLOB,
  mss_type       VARCHAR2(60),
  send_user_id   VARCHAR2(60),
  send_user_name VARCHAR2(60),
  request_id     VARCHAR2(60),
  create_by      VARCHAR2(300),
  create_time    TIMESTAMP(6),
  update_by      VARCHAR2(300),
  update_time    TIMESTAMP(6),
  ext1           VARCHAR2(300),
  ext2           VARCHAR2(300),
  ext3           VARCHAR2(300),
  ext4           VARCHAR2(300),
  ext5           VARCHAR2(300)
)
;
comment on column OA_MSS_INFO.id
  is '主键';
comment on column OA_MSS_INFO.mss_title
  is '短信主题';
comment on column OA_MSS_INFO.mss_content
  is '短信内容';
comment on column OA_MSS_INFO.mss_type
  is '短信类型';
comment on column OA_MSS_INFO.send_user_id
  is '发送人';
comment on column OA_MSS_INFO.send_user_name
  is '发送人姓名';
comment on column OA_MSS_INFO.request_id
  is '请求id';
comment on column OA_MSS_INFO.create_by
  is '创建人';
comment on column OA_MSS_INFO.create_time
  is '创建时间';
comment on column OA_MSS_INFO.update_by
  is '最后更新人';
comment on column OA_MSS_INFO.update_time
  is '最后更新时间';
comment on column OA_MSS_INFO.ext1
  is '短信失败原因';
comment on column OA_MSS_INFO.ext2
  is '备用字段';
comment on column OA_MSS_INFO.ext3
  is '备用字段';
comment on column OA_MSS_INFO.ext4
  is '备用字段';
comment on column OA_MSS_INFO.ext5
  is '备用字段';
alter table OA_MSS_INFO
  add primary key (ID);

--prompt
--prompt Creating table OA_MSS_SEND_INFO
--prompt ===============================
--prompt
create table OA_MSS_SEND_INFO
(
  id                NUMBER not null,
  mss_title         VARCHAR2(1000),
  mss_type          VARCHAR2(60),
  send_user_id      VARCHAR2(60),
  send_user_name    VARCHAR2(60),
  request_id        VARCHAR2(60),
  receive_id        VARCHAR2(60),
  receive_name      VARCHAR2(60),
  receive_telephone VARCHAR2(60),
  create_by         VARCHAR2(300),
  create_time       TIMESTAMP(6),
  update_by         VARCHAR2(300),
  update_time       TIMESTAMP(6),
  ext1              VARCHAR2(300),
  ext2              VARCHAR2(300),
  ext3              VARCHAR2(300),
  ext4              VARCHAR2(300),
  ext5              VARCHAR2(300),
  is_send           VARCHAR2(10),
  mss_id            VARCHAR2(30),
  mss_count         NUMBER,
  resource_system   VARCHAR2(60)
)
;
comment on column OA_MSS_SEND_INFO.id
  is '主键';
comment on column OA_MSS_SEND_INFO.mss_title
  is '短信主题';
comment on column OA_MSS_SEND_INFO.mss_type
  is '短信类型';
comment on column OA_MSS_SEND_INFO.send_user_id
  is '发送人';
comment on column OA_MSS_SEND_INFO.send_user_name
  is '发送人姓名';
comment on column OA_MSS_SEND_INFO.request_id
  is '请求id';
comment on column OA_MSS_SEND_INFO.receive_id
  is '接收短信';
comment on column OA_MSS_SEND_INFO.receive_name
  is '接收短信人';
comment on column OA_MSS_SEND_INFO.receive_telephone
  is '接收短信号码';
comment on column OA_MSS_SEND_INFO.create_by
  is '创建人';
comment on column OA_MSS_SEND_INFO.create_time
  is '创建时间';
comment on column OA_MSS_SEND_INFO.update_by
  is '最后更新人';
comment on column OA_MSS_SEND_INFO.update_time
  is '最后更新时间';
comment on column OA_MSS_SEND_INFO.ext1
  is '短信失败原因';
comment on column OA_MSS_SEND_INFO.ext2
  is '备用字段';
comment on column OA_MSS_SEND_INFO.ext3
  is '备用字段';
comment on column OA_MSS_SEND_INFO.ext4
  is '备用字段';
comment on column OA_MSS_SEND_INFO.ext5
  is '备用字段';
comment on column OA_MSS_SEND_INFO.is_send
  is '是否发送';
comment on column OA_MSS_SEND_INFO.mss_id
  is '短信头id';
comment on column OA_MSS_SEND_INFO.mss_count
  is '预计占用短信数量';
comment on column OA_MSS_SEND_INFO.resource_system
  is '来源系统';
alter table OA_MSS_SEND_INFO
  add primary key (ID);

--prompt
--prompt Creating table OA_NEWS
--prompt ======================
--prompt
create table OA_NEWS
(
  news_id                      NUMBER(20) not null,
  news_type_code               VARCHAR2(300),
  news_type_name               VARCHAR2(300),
  news_title                   VARCHAR2(300),
  news_thumbnails_url          VARCHAR2(1000),
  ispic_news                   VARCHAR2(1),
  news_hit                     NUMBER(20) default 0,
  news_datetime                DATE default SYSDATE,
  news_updatetime              DATE default SYSDATE,
  news_contributor             VARCHAR2(20),
  news_content                 NCLOB,
  news_image_url               VARCHAR2(300),
  news_movie_url               VARCHAR2(300),
  news_keyword                 VARCHAR2(300),
  news_istop                   VARCHAR2(1) default 0,
  del_flag                     VARCHAR2(1) default 0,
  news_applytime               DATE default SYSDATE,
  news_isapproval              VARCHAR2(1),
  phone                        NUMBER(20),
  news_urgent_level            VARCHAR2(50),
  news_contribut_dept          VARCHAR2(255),
  news_business_leader         VARCHAR2(255),
  news_contribut_platform      VARCHAR2(255),
  news_status                  VARCHAR2(50),
  news_editor                  VARCHAR2(20),
  news_delivery_dept           VARCHAR2(100),
  news_contribut_platform_code VARCHAR2(255),
  news_delivery_dept_code      VARCHAR2(255),
  news_publictime              DATE,
  news_contributor_dept_code   VARCHAR2(50),
  author                       VARCHAR2(50),
  org_code                     VARCHAR2(50),
  order_num                    VARCHAR2(50),
  apply_user_id                VARCHAR2(50)
)
;
comment on table OA_NEWS
  is '院校新闻表';
comment on column OA_NEWS.news_id
  is '自增长id';
comment on column OA_NEWS.news_type_code
  is '新闻类型，取字典表CODE';
comment on column OA_NEWS.news_type_name
  is '新闻类别名称，取字典表name字段';
comment on column OA_NEWS.news_title
  is '新闻标题';
comment on column OA_NEWS.news_thumbnails_url
  is '新闻缩略图';
comment on column OA_NEWS.ispic_news
  is '是否是轮播图片，1表示是，0表示否';
comment on column OA_NEWS.news_hit
  is '点击次数';
comment on column OA_NEWS.news_datetime
  is '发布时间，默认系统时间';
comment on column OA_NEWS.news_updatetime
  is '更新时间，默认系统时间';
comment on column OA_NEWS.news_contributor
  is '新闻供稿人';
comment on column OA_NEWS.news_content
  is '新闻内容';
comment on column OA_NEWS.news_image_url
  is '新闻配图url';
comment on column OA_NEWS.news_movie_url
  is '新闻视频url';
comment on column OA_NEWS.news_keyword
  is '新闻关键字';
comment on column OA_NEWS.news_istop
  is '新闻是否置顶，1置顶，默认0';
comment on column OA_NEWS.del_flag
  is '删除状态，默认0，表示未删除';
comment on column OA_NEWS.news_applytime
  is '申请时间，默认当天时间';
comment on column OA_NEWS.news_isapproval
  is '是否需业务负责人审批，1是，0否';
comment on column OA_NEWS.phone
  is '供稿人电话';
comment on column OA_NEWS.news_urgent_level
  is '紧急程度，取字典表';
comment on column OA_NEWS.news_contribut_dept
  is '供稿部门';
comment on column OA_NEWS.news_business_leader
  is '业务负责人';
comment on column OA_NEWS.news_contribut_platform
  is '投稿平台';
comment on column OA_NEWS.news_status
  is '取字典表';
comment on column OA_NEWS.news_editor
  is '新闻编辑人';
comment on column OA_NEWS.news_delivery_dept
  is '投递部门';
comment on column OA_NEWS.news_contribut_platform_code
  is '投稿平台字典表对应的code';
comment on column OA_NEWS.news_delivery_dept_code
  is '投递部门对应字典表的code';
comment on column OA_NEWS.news_publictime
  is '新闻最终发布通过时间';
comment on column OA_NEWS.news_contributor_dept_code
  is '新闻供稿人所在部门code';
comment on column OA_NEWS.author
  is '新闻作者';
comment on column OA_NEWS.org_code
  is '组织代码';
comment on column OA_NEWS.order_num
  is '流程编码';
comment on column OA_NEWS.apply_user_id
  is '申请人id';
alter table OA_NEWS
  add primary key (NEWS_ID);

--prompt
--prompt Creating table OA_NOTICE
--prompt ========================
--prompt
create table OA_NOTICE
(
  id               NUMBER(20) not null,
  title            VARCHAR2(200) not null,
  urgent_level     VARCHAR2(50),
  notice_type_code VARCHAR2(50),
  notice_type_name VARCHAR2(50),
  apply_org_code   VARCHAR2(50),
  apply_org_name   VARCHAR2(50),
  apply_user_id    VARCHAR2(50),
  apply_user_name  VARCHAR2(50),
  apply_datetime   DATE default SYSDATE,
  notice_sort_code VARCHAR2(50),
  notice_sort_name VARCHAR2(50),
  notice_hit       NUMBER(10) default 0,
  notice_content   NCLOB,
  del_flag         NUMBER(1) default 0,
  approval_status  VARCHAR2(50) default 'draft',
  isneed_bussiness NUMBER(1) default 0,
  update_datetime  DATE,
  update_user_name VARCHAR2(50),
  update_user_id   VARCHAR2(50),
  order_num        VARCHAR2(50),
  apply_dept_code  VARCHAR2(50)
)
;
comment on table OA_NOTICE
  is '通知公告表';
comment on column OA_NOTICE.id
  is '通知公告id，主键';
comment on column OA_NOTICE.title
  is '通知公告标题';
comment on column OA_NOTICE.urgent_level
  is '紧急程度';
comment on column OA_NOTICE.notice_type_code
  is '通知公告分类CODE，取自字典';
comment on column OA_NOTICE.notice_type_name
  is '通知公告分类，取自字典，例：内部公告、院校公告';
comment on column OA_NOTICE.apply_org_code
  is '申请部门ID';
comment on column OA_NOTICE.apply_org_name
  is '申请部门';
comment on column OA_NOTICE.apply_user_id
  is '申请人ID';
comment on column OA_NOTICE.apply_user_name
  is '申请人';
comment on column OA_NOTICE.apply_datetime
  is '申请时间';
comment on column OA_NOTICE.notice_sort_code
  is '公告类别CODE，取自字典';
comment on column OA_NOTICE.notice_sort_name
  is '公告类别，取自字典，例：行政通知、人事任免、会议通知、规章制度通知、其他通知公告';
comment on column OA_NOTICE.notice_hit
  is '点击量';
comment on column OA_NOTICE.notice_content
  is '公告正文';
comment on column OA_NOTICE.del_flag
  is '是否删除，默认0， 0，否，1，是';
comment on column OA_NOTICE.approval_status
  is '审批状态，默认0，0，审核中，1，审批完成';
comment on column OA_NOTICE.isneed_bussiness
  is '是否需要业务负责人审批，默认0否，1，是，0，否';
comment on column OA_NOTICE.update_datetime
  is '更新时间';
comment on column OA_NOTICE.update_user_name
  is '更新人姓名';
comment on column OA_NOTICE.update_user_id
  is '更新人id';
comment on column OA_NOTICE.order_num
  is '流水号';
comment on column OA_NOTICE.apply_dept_code
  is '申请单位code';
alter table OA_NOTICE
  add primary key (ID);

--prompt
--prompt Creating table OA_NOTICE_OTHER
--prompt ==============================
--prompt
create table OA_NOTICE_OTHER
(
  id               NUMBER(20) not null,
  title            VARCHAR2(50),
  app_type_code    VARCHAR2(50),
  app_type_name    VARCHAR2(50),
  apply_org_code   VARCHAR2(50),
  apply_org_name   VARCHAR2(50),
  apply_user_id    VARCHAR2(50),
  apply_user_name  VARCHAR2(50),
  apply_datetime   DATE default sysdate,
  urgent_level     VARCHAR2(50),
  del_flag         NUMBER(1) default 0,
  approval_status  VARCHAR2(50) default 'draft',
  isneed_bussiness NUMBER(1) default 0,
  update_datetime  DATE,
  update_user_name VARCHAR2(50),
  update_user_id   VARCHAR2(50),
  order_num        VARCHAR2(50),
  apply_dept_code  VARCHAR2(50),
  notice_hit       NUMBER(10) default 0
)
;
comment on table OA_NOTICE_OTHER
  is '校历';
comment on column OA_NOTICE_OTHER.id
  is '校历ID，主键';
comment on column OA_NOTICE_OTHER.title
  is '校历标题';
comment on column OA_NOTICE_OTHER.app_type_code
  is '应用类型code';
comment on column OA_NOTICE_OTHER.app_type_name
  is '应用类型名称';
comment on column OA_NOTICE_OTHER.apply_org_code
  is '申请部门code';
comment on column OA_NOTICE_OTHER.apply_org_name
  is '申请部门名称';
comment on column OA_NOTICE_OTHER.apply_user_id
  is '申请人员id';
comment on column OA_NOTICE_OTHER.apply_user_name
  is '申请人员名称';
comment on column OA_NOTICE_OTHER.apply_datetime
  is '申请日期，默认当前日期';
comment on column OA_NOTICE_OTHER.urgent_level
  is '紧急程度，默认0，一般';
comment on column OA_NOTICE_OTHER.del_flag
  is '是否删除，默认0否，1，是，0，否';
comment on column OA_NOTICE_OTHER.approval_status
  is '审批状态，默认0，0，审判中，1，审批完成';
comment on column OA_NOTICE_OTHER.isneed_bussiness
  is '是否需要业务负责人审批，默认0否，1，是，0，否';
comment on column OA_NOTICE_OTHER.update_datetime
  is '更新时间';
comment on column OA_NOTICE_OTHER.update_user_name
  is '更新人姓名';
comment on column OA_NOTICE_OTHER.update_user_id
  is '更新人id';
comment on column OA_NOTICE_OTHER.order_num
  is '流水号';
comment on column OA_NOTICE_OTHER.apply_dept_code
  is '申请部门code';
comment on column OA_NOTICE_OTHER.notice_hit
  is '点击次数';

--prompt
--prompt Creating table OA_RESOURCE
--prompt ==========================
--prompt
create table OA_RESOURCE
(
  id               NUMBER(20) not null,
  title            VARCHAR2(50),
  apply_dept_id    VARCHAR2(50),
  apply_dept_name  VARCHAR2(50),
  apply_user_id    VARCHAR2(50),
  apply_user_name  VARCHAR2(50),
  apply_datetime   DATE default sysdate,
  urgent_level     VARCHAR2(50),
  del_flag         NUMBER(1) default 0,
  approval_status  VARCHAR2(50) default 'draft',
  isneed_bussiness NUMBER(1) default 0,
  update_datetime  DATE,
  update_user_name VARCHAR2(50),
  update_user_id   VARCHAR2(50),
  order_num        VARCHAR2(50)
)
;
comment on table OA_RESOURCE
  is '资源下载';
comment on column OA_RESOURCE.id
  is '资源下载ID，主键';
comment on column OA_RESOURCE.title
  is '资源下载标题';
comment on column OA_RESOURCE.apply_dept_id
  is '申请部门id';
comment on column OA_RESOURCE.apply_dept_name
  is '申请部门名称';
comment on column OA_RESOURCE.apply_user_id
  is '申请人员id';
comment on column OA_RESOURCE.apply_user_name
  is '申请人员名称';
comment on column OA_RESOURCE.apply_datetime
  is '申请日期，默认当前日期';
comment on column OA_RESOURCE.urgent_level
  is '紧急程度，默认0，一般';
comment on column OA_RESOURCE.del_flag
  is '是否删除，默认0否，1，是，0，否';
comment on column OA_RESOURCE.approval_status
  is '审批状态，默认0，0，审判中，1，审批完成';
comment on column OA_RESOURCE.isneed_bussiness
  is '是否需要业务负责人审批，默认0否，1，是，0，否';
comment on column OA_RESOURCE.update_datetime
  is '更新时间';
comment on column OA_RESOURCE.update_user_name
  is '更新人姓名';
comment on column OA_RESOURCE.update_user_id
  is '更新人id';
comment on column OA_RESOURCE.order_num
  is '流水号';

--prompt
--prompt Creating table OA_RULE_SYSTEM
--prompt =============================
--prompt
create table OA_RULE_SYSTEM
(
  id               NUMBER(20) not null,
  title            VARCHAR2(50) not null,
  urgent_level     VARCHAR2(50),
  rule_sort_code   VARCHAR2(50),
  rule_sort_name   VARCHAR2(50),
  apply_org_code   VARCHAR2(50),
  apply_org_name   VARCHAR2(50),
  apply_user_id    VARCHAR2(50),
  apply_user_name  VARCHAR2(50),
  apply_datetime   DATE default SYSDATE,
  del_flag         NUMBER(1) default 0,
  rule_content     NCLOB,
  rule_hit         NUMBER(5) default 0,
  approval_status  VARCHAR2(50) default 'draft',
  isneed_bussiness NUMBER(1) default 0,
  update_datetime  DATE,
  update_user_name VARCHAR2(50),
  update_user_id   VARCHAR2(50),
  order_num        VARCHAR2(50),
  apply_dept_code  VARCHAR2(50)
)
;
comment on table OA_RULE_SYSTEM
  is '规则制度';
comment on column OA_RULE_SYSTEM.id
  is '规章制度ID';
comment on column OA_RULE_SYSTEM.title
  is '规则制度标题';
comment on column OA_RULE_SYSTEM.urgent_level
  is '紧急程度';
comment on column OA_RULE_SYSTEM.rule_sort_code
  is '规则字段类别CODE，取自字典';
comment on column OA_RULE_SYSTEM.rule_sort_name
  is '规则字段类别，取自字典，例：办公制度、财务制度、人事制度、科技管理制度、其他制度';
comment on column OA_RULE_SYSTEM.apply_org_code
  is '申请部门ID';
comment on column OA_RULE_SYSTEM.apply_org_name
  is '申请部门';
comment on column OA_RULE_SYSTEM.apply_user_id
  is '申请用户ID';
comment on column OA_RULE_SYSTEM.apply_user_name
  is '申请用户';
comment on column OA_RULE_SYSTEM.apply_datetime
  is '申请时间';
comment on column OA_RULE_SYSTEM.del_flag
  is '是否删除，默认0， 0，否，1，是';
comment on column OA_RULE_SYSTEM.rule_content
  is '规则制度内容';
comment on column OA_RULE_SYSTEM.rule_hit
  is '点击次数';
comment on column OA_RULE_SYSTEM.approval_status
  is '审批状态，默认0，0，审核中，1，审批完成';
comment on column OA_RULE_SYSTEM.isneed_bussiness
  is '是否需要业务负责人审批，默认0否，1，是，0，否';
comment on column OA_RULE_SYSTEM.update_datetime
  is '更新时间';
comment on column OA_RULE_SYSTEM.update_user_name
  is '更新人姓名';
comment on column OA_RULE_SYSTEM.update_user_id
  is '更新人id';
comment on column OA_RULE_SYSTEM.order_num
  is '流水号';
comment on column OA_RULE_SYSTEM.apply_dept_code
  is '申请部门code';
create index INDEX_APPLY_DATETIME on OA_RULE_SYSTEM (APPLY_DATETIME);
alter table OA_RULE_SYSTEM
  add primary key (ID);

--prompt
--prompt Creating table OA_SCHEDULE_HISTORY
--prompt ==================================
--prompt
create table OA_SCHEDULE_HISTORY
(
  id          NUMBER not null,
  schedule_id NUMBER,
  row_num     NUMBER,
  business_id VARCHAR2(60),
  work_id     VARCHAR2(30),
  work_name   VARCHAR2(300),
  work_desc   VARCHAR2(300),
  work_status VARCHAR2(300),
  show_flag   VARCHAR2(60),
  user_id     VARCHAR2(300),
  user_name   VARCHAR2(300),
  dept_id     VARCHAR2(300),
  dept_name   VARCHAR2(300),
  start_time  TIMESTAMP(6),
  end_time    TIMESTAMP(6),
  create_by   VARCHAR2(300),
  create_time TIMESTAMP(6),
  update_by   VARCHAR2(300),
  update_time TIMESTAMP(6),
  ext1        VARCHAR2(300),
  ext2        VARCHAR2(300),
  ext3        VARCHAR2(300),
  ext4        VARCHAR2(300),
  ext5        VARCHAR2(300),
  person_type VARCHAR2(60),
  notice_type VARCHAR2(60)
)
;
comment on column OA_SCHEDULE_HISTORY.id
  is '主键';
comment on column OA_SCHEDULE_HISTORY.schedule_id
  is '日程表此记录的主键';
comment on column OA_SCHEDULE_HISTORY.row_num
  is '行号';
comment on column OA_SCHEDULE_HISTORY.business_id
  is '业务单据主键';
comment on column OA_SCHEDULE_HISTORY.work_id
  is '日程主键';
comment on column OA_SCHEDULE_HISTORY.work_name
  is '日程历史简述';
comment on column OA_SCHEDULE_HISTORY.work_desc
  is '日程历史内容';
comment on column OA_SCHEDULE_HISTORY.work_status
  is '日程状态';
comment on column OA_SCHEDULE_HISTORY.user_id
  is '日程对象';
comment on column OA_SCHEDULE_HISTORY.user_name
  is '日程对象名称';
comment on column OA_SCHEDULE_HISTORY.dept_id
  is '日程对象所属部门ID';
comment on column OA_SCHEDULE_HISTORY.dept_name
  is '日程对象所属部门名称';
comment on column OA_SCHEDULE_HISTORY.start_time
  is '日程开始时间';
comment on column OA_SCHEDULE_HISTORY.end_time
  is '日程结束时间';
comment on column OA_SCHEDULE_HISTORY.create_by
  is '日程历史创建人';
comment on column OA_SCHEDULE_HISTORY.create_time
  is '日程历史创建时间';
comment on column OA_SCHEDULE_HISTORY.update_by
  is '历史记录最后更新人';
comment on column OA_SCHEDULE_HISTORY.update_time
  is '历史记录日程最后更新时间';
comment on column OA_SCHEDULE_HISTORY.ext1
  is '备用字段';
comment on column OA_SCHEDULE_HISTORY.ext2
  is '备用字段';
comment on column OA_SCHEDULE_HISTORY.ext3
  is '备用字段';
comment on column OA_SCHEDULE_HISTORY.ext4
  is '备用字段';
comment on column OA_SCHEDULE_HISTORY.ext5
  is '备用字段';
alter table OA_SCHEDULE_HISTORY
  add primary key (ID);

--prompt
--prompt Creating table OA_SCHEDULE_SHARE_CONFIG
--prompt =======================================
--prompt
create table OA_SCHEDULE_SHARE_CONFIG
(
  id                 NUMBER not null,
  user_id            VARCHAR2(60),
  user_name          VARCHAR2(60),
  share_to_id        VARCHAR2(60),
  share_to_name      VARCHAR2(60),
  share_to_dept_id   VARCHAR2(60),
  share_to_dept_name VARCHAR2(60),
  permissions        VARCHAR2(60),
  enable_flag        VARCHAR2(60),
  create_by          VARCHAR2(300),
  create_time        TIMESTAMP(6),
  update_by          VARCHAR2(300),
  update_time        TIMESTAMP(6),
  ext1               VARCHAR2(300),
  ext2               VARCHAR2(300),
  ext3               VARCHAR2(300),
  ext4               VARCHAR2(300),
  ext5               VARCHAR2(300)
)
;
comment on column OA_SCHEDULE_SHARE_CONFIG.id
  is '主键';
comment on column OA_SCHEDULE_SHARE_CONFIG.user_id
  is '发起共享人id';
comment on column OA_SCHEDULE_SHARE_CONFIG.user_name
  is '发起共享人姓名';
comment on column OA_SCHEDULE_SHARE_CONFIG.share_to_id
  is '共享人';
comment on column OA_SCHEDULE_SHARE_CONFIG.share_to_name
  is '共享人姓名';
comment on column OA_SCHEDULE_SHARE_CONFIG.share_to_dept_id
  is '共享人所在部门id';
comment on column OA_SCHEDULE_SHARE_CONFIG.share_to_dept_name
  is '共享人所在部门名称';
comment on column OA_SCHEDULE_SHARE_CONFIG.permissions
  is '权限：VIEW:查看/EDIT:编辑';
comment on column OA_SCHEDULE_SHARE_CONFIG.enable_flag
  is '是否有效：Y/N';
comment on column OA_SCHEDULE_SHARE_CONFIG.create_by
  is '创建人';
comment on column OA_SCHEDULE_SHARE_CONFIG.create_time
  is '创建时间';
comment on column OA_SCHEDULE_SHARE_CONFIG.update_by
  is '最后更新人';
comment on column OA_SCHEDULE_SHARE_CONFIG.update_time
  is '最后更新时间';
comment on column OA_SCHEDULE_SHARE_CONFIG.ext1
  is '备用字段';
comment on column OA_SCHEDULE_SHARE_CONFIG.ext2
  is '备用字段';
comment on column OA_SCHEDULE_SHARE_CONFIG.ext3
  is '备用字段';
comment on column OA_SCHEDULE_SHARE_CONFIG.ext4
  is '备用字段';
comment on column OA_SCHEDULE_SHARE_CONFIG.ext5
  is '备用字段';
alter table OA_SCHEDULE_SHARE_CONFIG
  add primary key (ID);

--prompt
--prompt Creating table OA_SCHEDULE_TABLE
--prompt ================================
--prompt
create table OA_SCHEDULE_TABLE
(
  id          NUMBER not null,
  row_num     NUMBER,
  business_id VARCHAR2(60),
  work_type   VARCHAR2(30),
  work_name   VARCHAR2(300),
  work_desc   VARCHAR2(300),
  work_status VARCHAR2(300),
  show_flag   VARCHAR2(60),
  user_id     VARCHAR2(300),
  user_name   VARCHAR2(300),
  dept_id     VARCHAR2(300),
  dept_name   VARCHAR2(300),
  start_time  TIMESTAMP(6),
  end_time    TIMESTAMP(6),
  create_by   VARCHAR2(300),
  create_time TIMESTAMP(6),
  update_by   VARCHAR2(300),
  update_time TIMESTAMP(6),
  ext1        VARCHAR2(300),
  ext2        VARCHAR2(300),
  ext3        VARCHAR2(300),
  ext4        VARCHAR2(300),
  ext5        VARCHAR2(300),
  person_type VARCHAR2(60),
  notice_type VARCHAR2(60)
)
;
comment on column OA_SCHEDULE_TABLE.id
  is '主键';
comment on column OA_SCHEDULE_TABLE.row_num
  is '行号';
comment on column OA_SCHEDULE_TABLE.business_id
  is '业务单据主键';
comment on column OA_SCHEDULE_TABLE.work_type
  is '日程类型';
comment on column OA_SCHEDULE_TABLE.work_name
  is '日程简述';
comment on column OA_SCHEDULE_TABLE.work_desc
  is '日程内容';
comment on column OA_SCHEDULE_TABLE.work_status
  is '日程状态';
comment on column OA_SCHEDULE_TABLE.show_flag
  is '是否显示(0显示/1隐藏)';
comment on column OA_SCHEDULE_TABLE.user_id
  is '日程对象';
comment on column OA_SCHEDULE_TABLE.user_name
  is '日程对象名称';
comment on column OA_SCHEDULE_TABLE.dept_id
  is '日程对象所属部门ID';
comment on column OA_SCHEDULE_TABLE.dept_name
  is '日程对象所属部门名称';
comment on column OA_SCHEDULE_TABLE.start_time
  is '日程开始时间';
comment on column OA_SCHEDULE_TABLE.end_time
  is '日程结束时间';
comment on column OA_SCHEDULE_TABLE.create_by
  is '日程创建人';
comment on column OA_SCHEDULE_TABLE.create_time
  is '日程创建时间';
comment on column OA_SCHEDULE_TABLE.update_by
  is '日程最后更新人';
comment on column OA_SCHEDULE_TABLE.update_time
  is '日程最后更新时间';
comment on column OA_SCHEDULE_TABLE.ext1
  is '备用字段';
comment on column OA_SCHEDULE_TABLE.ext2
  is '备用字段';
comment on column OA_SCHEDULE_TABLE.ext3
  is '备用字段';
comment on column OA_SCHEDULE_TABLE.ext4
  is '备用字段';
comment on column OA_SCHEDULE_TABLE.ext5
  is '备用字段';
alter table OA_SCHEDULE_TABLE
  add primary key (ID);

--prompt
--prompt Creating table OA_SEND_PROCESS_TASK
--prompt ===================================
--prompt
create table OA_SEND_PROCESS_TASK
(
  id                   NUMBER(20) not null,
  process_define_key   VARCHAR2(50) not null,
  process_instance_id  VARCHAR2(50) not null,
  task_id              VARCHAR2(50) not null,
  task_key             VARCHAR2(50) not null,
  task_name            VARCHAR2(500) not null,
  task_title           VARCHAR2(255),
  task_description     VARCHAR2(1000),
  task_stauts          VARCHAR2(300) not null,
  approver_acount      VARCHAR2(50) not null,
  approver_name        VARCHAR2(300) not null,
  approver_station     VARCHAR2(300),
  approver_description VARCHAR2(255),
  object_id            VARCHAR2(60) not null,
  object_order_num     VARCHAR2(60),
  object_stauts        VARCHAR2(300) not null,
  object_type          VARCHAR2(300),
  object_url           VARCHAR2(2550),
  object_description   VARCHAR2(2000),
  opinion              VARCHAR2(4000),
  create_time          TIMESTAMP(6),
  update_time          TIMESTAMP(6),
  duration_time        NUMBER(20),
  submitter_code       VARCHAR2(50),
  submitter_name       VARCHAR2(300),
  submitter_org        VARCHAR2(50),
  approver_org         VARCHAR2(50),
  source_system        VARCHAR2(255),
  create_by            VARCHAR2(50),
  create_by_org_id     VARCHAR2(50),
  process_create_time  DATE,
  object_app_url       VARCHAR2(2000),
  emergency            VARCHAR2(2),
  submitter_station    VARCHAR2(300),
  create_by_name       VARCHAR2(60),
  previous_task_id     VARCHAR2(60)
)
;
comment on table OA_SEND_PROCESS_TASK
  is '抄送待阅表';
comment on column OA_SEND_PROCESS_TASK.id
  is '主键ID';
comment on column OA_SEND_PROCESS_TASK.process_define_key
  is '流程定义key';
comment on column OA_SEND_PROCESS_TASK.task_id
  is '任务ID';
comment on column OA_SEND_PROCESS_TASK.task_key
  is '任务定义key';
comment on column OA_SEND_PROCESS_TASK.task_name
  is '任务定义名称';
comment on column OA_SEND_PROCESS_TASK.task_title
  is '任务标题';
comment on column OA_SEND_PROCESS_TASK.task_description
  is '任务描述';
comment on column OA_SEND_PROCESS_TASK.task_stauts
  is '流程任务审批状态 【1待审批 2已批准 3已驳回 4已提交 5待提交 6已转办 7 终止流程 8 撤回】';
comment on column OA_SEND_PROCESS_TASK.approver_acount
  is '审批人账号';
comment on column OA_SEND_PROCESS_TASK.approver_name
  is '审批人姓名';
comment on column OA_SEND_PROCESS_TASK.approver_station
  is '审批人岗位';
comment on column OA_SEND_PROCESS_TASK.approver_description
  is '审批人描述';
comment on column OA_SEND_PROCESS_TASK.object_id
  is '审批对象ID';
comment on column OA_SEND_PROCESS_TASK.object_order_num
  is '审批对象业务单据编号';
comment on column OA_SEND_PROCESS_TASK.object_stauts
  is '审批对象审批状态 【1审批中 2已通过 3 已驳回 4 停止审批】';
comment on column OA_SEND_PROCESS_TASK.object_type
  is '审批对象类型';
comment on column OA_SEND_PROCESS_TASK.object_url
  is '审批对象URL';
comment on column OA_SEND_PROCESS_TASK.object_description
  is '审批对象描述';
comment on column OA_SEND_PROCESS_TASK.opinion
  is '审批意见';
comment on column OA_SEND_PROCESS_TASK.create_time
  is '创建时间';
comment on column OA_SEND_PROCESS_TASK.update_time
  is '更新时间';
comment on column OA_SEND_PROCESS_TASK.duration_time
  is '持续时间毫秒数';
comment on column OA_SEND_PROCESS_TASK.submitter_code
  is '提交人账号';
comment on column OA_SEND_PROCESS_TASK.submitter_name
  is '提交人姓名';
comment on column OA_SEND_PROCESS_TASK.submitter_org
  is '提交人组织';
comment on column OA_SEND_PROCESS_TASK.approver_org
  is '审批人组织';
comment on column OA_SEND_PROCESS_TASK.source_system
  is '审批单归属系统';
comment on column OA_SEND_PROCESS_TASK.create_by
  is '任务创建人，即该条记录的创建人';
comment on column OA_SEND_PROCESS_TASK.create_by_org_id
  is '任务创建人所在部门';
comment on column OA_SEND_PROCESS_TASK.process_create_time
  is '流程发起时间';
comment on column OA_SEND_PROCESS_TASK.object_app_url
  is 'app地址';
comment on column OA_SEND_PROCESS_TASK.emergency
  is '紧急程度';
comment on column OA_SEND_PROCESS_TASK.submitter_station
  is '提交人岗位';
comment on column OA_SEND_PROCESS_TASK.create_by_name
  is '创建人姓名';
comment on column OA_SEND_PROCESS_TASK.previous_task_id
  is '上一个节点的任务id';
alter table OA_SEND_PROCESS_TASK
  add primary key (ID);


--spool off
