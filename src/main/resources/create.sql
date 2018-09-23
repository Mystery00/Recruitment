# 创建数据库
CREATE DATABASE 'db_bjyzpw';

# 表1——公司表：tb_company
    公司id、公司名称、公司主页、公司领域、公司规模、发展阶段

CREATE TABLE `db_bjyzpw`.`tb_company`  (
  `c_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '公司id',
  `c_name` text NOT NULL COMMENT '公司名称',
  `c_home_url` varchar(255) NULL DEFAULT NULL COMMENT '公司主页',
  `c_land` varchar(255) NULL DEFAULT NULL COMMENT '公司领域',
  `c_num` text NULL COMMENT '公司规模',
  `c_develope` varchar(255) NULL DEFAULT NULL COMMENT '发展阶段',
  PRIMARY KEY (`c_id`)
);

# 表2——公司招聘职位表： tb_company_job
#    id、公司名称、招聘职位名称、工作地点（城市名）、工作地址（更详细）、薪资、工作经验要求、职业诱惑、职位描述、学历要求、工作类型、关键词（数组）、发布时间、发布网站

CREATE TABLE `db_bjyzpw`.`tb_company_job`  (
  `cj_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '公司招聘职位id',
  `cj_name` varchar(255) NOT NULL COMMENT '公司名称',
  `cj_job_name` varchar(255) NULL COMMENT '招聘职位名称',
  `cj_city` varchar(255) NULL COMMENT '工作地点（城市）',
  `cj_location` varchar(255) NULL COMMENT '工作地址（详细地址）',
  `cj_pay` varchar(255) NULL COMMENT '薪资',
  `cj_exp` varchar(255) NULL COMMENT '工作经验要求',
  `cj_temptation` varchar(255) NULL COMMENT '职业诱惑',
  `cj_description` text NULL COMMENT '职业描述',
  `cj_grade` varchar(255) NULL COMMENT '学历要求',
  `cj_type` varchar(255) NULL COMMENT '工作类型cj_',
  `cj_keyword` varchar(255) NULL COMMENT '关键词',
  `cj_publish_time` datetime(0) NULL COMMENT '发布时间',
  `cj_publish_website` varchar(255) NULL COMMENT '发布网站',
  PRIMARY KEY (`cj_id`)
);

# 表3——关键词表： tb_keyword
#    关键词id、关键词

CREATE TABLE `db_bjyzpw`.`tb_keyword`  (
  `k_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '关键词id',
  `k_name` varchar(255) NOT NULL COMMENT '关键词',
  PRIMARY KEY (`k_id`)
);

# 表4——用户表： tb_user
#    用户id、用户名、密码、昵称

CREATE TABLE `db_bjyzpw`.`tb_user`  (
  `u_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `u_username` varchar(255) NOT NULL COMMENT '用户名',
  `u_password` varchar(255) NOT NULL COMMENT '密码',
  `u_name` varchar(255) NOT NULL COMMENT '昵称',
  PRIMARY KEY (`u_id`)
);

# 表5——职位收藏表： tb_mark_job
#    id、用户id、职位id

CREATE TABLE `db_bjyzpw`.`tb_mark_job`  (
  `tmj_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `tmj_user_id` bigint(20) NOT NULL COMMENT '用户id',
  `tmj_job_id` bigint(20) NOT NULL COMMENT '职位id',
  PRIMARY KEY (`tmj_id`),
  CONSTRAINT `tmj_user_id` FOREIGN KEY (`tmj_user_id`) REFERENCES `db_bjyzpw`.`tb_user` (`u_id`),
  CONSTRAINT `tmj_job_id` FOREIGN KEY (`tmj_job_id`) REFERENCES `db_bjyzpw`.`tb_company_job` (`cj_id`)
);

# 表6——公司收藏表： tb_mark_company
#    id、用户id、公司id

CREATE TABLE `db_bjyzpw`.`tb_mark_company`  (
  `tmc_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `tmc_user_id` bigint(20) NOT NULL COMMENT '用户id',
  `tmc_company_id` bigint(20) NOT NULL COMMENT '公司id',
  PRIMARY KEY (`tmc_id`),
  CONSTRAINT `tmc_user_id` FOREIGN KEY (`tmc_user_id`) REFERENCES `db_bjyzpw`.`tb_user` (`u_id`),
  CONSTRAINT `tmc_company_id` FOREIGN KEY (`tmc_company_id`) REFERENCES `db_bjyzpw`.`tb_company_job` (`cj_id`)
);

# 功能需求：
#    登陆、注册、关键词、反关键词、查询公司信息（天眼查）、收藏