insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('1136100884788047891','PRO_ACTIVE_TYPE_BCOO_2019','项目活动类型_重大协同_2019',to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss'),to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss'),'system','system');
insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('1136100884788047892','10','国家重点实验室','1136100884788047891',to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss'),to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss'),'system','system');
insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('1136100884788047893','20','队列研究平台','1136100884788047891',to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss'),to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss'),'system','system');
insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('1136100884788047894','30','生物信息平台','1136100884788047891',to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss'),to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss'),'system','system');
insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('1136100884788047895','40','其他','1136100884788047891',to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss'),to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss'),'system','system');

--审批单据添加字段
alter table SRPMS_VOUCHER add AUDIT_MODE NVARCHAR2(20);
alter table SRPMS_VOUCHER add LEAD_DEPT_CODE NVARCHAR2(50);

comment on column SRPMS_VOUCHER.AUDIT_MODE
  is '审批模式 selfOnly:本单位审批 topOnly:医科院审批 all:本单位及医科院共同审批';
comment on column SRPMS_VOUCHER.LEAD_DEPT_CODE
  is '承担单位CODE';


