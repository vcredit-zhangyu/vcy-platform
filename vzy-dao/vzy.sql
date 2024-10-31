drop table if exists t_device;
create table t_device (
                          `id` bigint unsigned not null AUTO_INCREMENT comment '主键',
                          `device_no` varchar(64) not null comment '设备号',
                          `device_name` varchar(64) default null comment '设备名称',
                          `device_status` varchar(16) default null comment '设备状态',
                          `device_charge` varchar(64) default null comment '负责人',
                          `device_charge_acct` varchar(64) default null comment '负责人账号',
                          `expire_date` date default null comment '到期日期',
                          `del_flag` char(1) not null comment '删除标志',
                          `created_by` varchar(64) not null comment '创建人',
                          `created_time` datetime not null default current_timestamp comment '创建时间',
                          `updated_by` varchar(64) not null comment '修改人',
                          `updated_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
                          primary key (`id`)
)comment='设备表';



drop table if exists t_device_detail;
create table t_device_detail (
                                 `id` bigint unsigned not null AUTO_INCREMENT comment '主键',
                                 `device_id` bigint not null comment '设备ID',
                                 `device_type` varchar(64) default null comment '设备机型',
                                 `device_brand` varchar(64) default null comment '设备品牌',
                                 `device_model` varchar(64) default null comment '设备型号',
                                 `device_system` varchar(32) default null comment '设备系统',
                                 `device_system_version` varchar(32) default null comment '设备系统版本',
                                 `device_login_user` varchar(64) default null comment '设备用户名',
                                 `device_login_key` varchar(128) default null comment '设备密码',
                                 `device_imei` varchar(128) default null comment '设备IMEI',
                                 `device_serial_no` varchar(128) default null comment '设备序列号',
                                 `device_resolution` varchar(64) default null comment '设备分辨率',
                                 `device_color` varchar(64) default null comment '设备颜色',
                                 `device_size` varchar(64) default null comment '设备尺寸',
                                 `device_accessory` varchar(256) default null comment '设备配件',
                                 `device_remark` varchar(512) default null comment '设备备注',
                                 `del_flag` char(1) not null comment '删除标志',
                                 `created_by` varchar(64) not null comment '创建人',
                                 `created_time` datetime not null default current_timestamp comment '创建时间',
                                 `updated_by` varchar(64) not null comment '修改人',
                                 `updated_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
                                 primary key (`id`)
)comment='设备详情表';




drop table if exists t_device_apply;
create table t_device_apply (
                                `id` bigint unsigned not null AUTO_INCREMENT comment '主键',
                                `device_id` bigint not null comment '设备ID',
                                `apply_type` varchar(64) default null comment '申请类型',
                                `apply_time` datetime default null comment '申请日期',
                                `apply_person` varchar(64) default null comment '申请人',
                                `apply_person_acct` varchar(64) default null comment '申请人账号',
                                `apply_period` varchar(64) default null comment '申请周期',
                                `apply_status` varchar(64) default null comment '审核状态',
                                `approve_person` varchar(64) default null comment '审核人',
                                `approve_person_acct` varchar(64) default null comment '审核人账号',
                                `approve_result` varchar(64) default null comment '审核结果',
                                `approve_reason` varchar(128) default null comment '审核原因',
                                `approve_time` datetime default null comment '审核时间',
                                `del_flag` char(1) not null comment '删除标志',
                                `created_by` varchar(64) not null comment '创建人',
                                `created_time` datetime not null default current_timestamp comment '创建时间',
                                `updated_by` varchar(64) not null comment '修改人',
                                `updated_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
                                primary key (`id`)
)comment='设备申请记录表';



drop table if exists t_device_log;
create table t_device_log (
                              `id` bigint unsigned not null AUTO_INCREMENT comment '主键',
                              `device_id` bigint not null comment '设备ID',
                              `device_name` varchar(64) default null comment '设备名称',
                              `device_no` varchar(64) default null comment '设备号',
                              `oper_person` varchar(64) default null comment '操作人',
                              `oper_person_acct` varchar(64) default null comment '操作人账号',
                              `oper_time` datetime default null comment '操作时间',
                              `oper_detail` varchar(1024) default null comment '操作详情',
                              `del_flag` char(1) not null comment '删除标志',
                              `created_by` varchar(64) not null comment '创建人',
                              `created_time` datetime not null default current_timestamp comment '创建时间',
                              `updated_by` varchar(64) not null comment '修改人',
                              `updated_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
                              primary key (`id`)
)comment='设备变更日志表';




drop table if exists t_device_app;
create table t_device_app (
                              `id` bigint unsigned not null AUTO_INCREMENT comment '主键',
                              `device_id` bigint not null comment '设备ID',
                              `last_app` varchar(64) default null comment '最近使用app',
                              `app_time` datetime default null comment '使用app时间',
                              `del_flag` char(1) not null comment '删除标志',
                              `created_by` varchar(64) not null comment '创建人',
                              `created_time` datetime not null default current_timestamp comment '创建时间',
                              `updated_by` varchar(64) not null comment '修改人',
                              `updated_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
                              primary key (`id`)
)comment='设备使用记录表';


drop table if exists t_device_app_version;
create table t_device_app_version (
                              `id` bigint unsigned not null AUTO_INCREMENT comment '主键',
                              `app_name` varchar(64) default null comment 'app名称',
                              `app_platform` varchar(64) not null comment 'app平台',
                              `app_version` varchar(64) not null comment 'app版本',
                              `version_remark` varchar(512) default null comment '版本备注',
                              `del_flag` char(1) not null comment '删除标志',
                              `created_by` varchar(64) not null comment '创建人',
                              `created_time` datetime not null default current_timestamp comment '创建时间',
                              `updated_by` varchar(64) not null comment '修改人',
                              `updated_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
                              primary key (`id`)
)comment='app版本表';
