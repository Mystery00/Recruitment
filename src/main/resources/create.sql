# 创建数据库
CREATE DATABASE db_bjyzpw;

# 表1 - 热搜表
create table db_bjyzpw.tb_hot_search (
  hs_id    bigint(20) not null auto_increment, # id
  hs_href  text       not null, # 热搜的跳转链接
  hs_value text       not null, # 热搜显示名称
  hs_date  text       not null, # 热搜存储日期
  primary key (hs_id)
);
# 插入语句
insert into db_bjyzpw.tb_hot_search (hs_href, hs_value, hs_date)
values (?, ?, ?);

# 删除语句
delete
from db_bjyzpw.tb_hot_search
where hs_date = ?;

# 查询语句
select *
from db_bjyzpw.tb_hot_search
where hs_date = ?;

# 表2 - 关键词表
create table db_bjyzpw.tb_keyword (
  k_id            bigint(20) not null auto_increment, # id
  k_category      text       not null, # 分类名称
  k_group         text       not null, # 分组名称
  k_key_word_list text       not null, # 关键词列表
  k_date          text       not null, # 存储日期
  primary key (k_id)
);
# 插入语句
insert into db_bjyzpw.tb_keyword (k_category, k_group, k_key_word_list, k_date)
values (?, ?, ?, ?);
# 删除语句
delete
from db_bjyzpw.tb_keyword
where k_date = ?;
# 查询语句
select *
from db_bjyzpw.tb_keyword;

# 表3 - 公司表
create table db_bjyzpw.tb_company_info (
  ci_id                  bigint(20) not null auto_increment, # id
  ci_name                text       not null, # 公司名称
  ci_company_icon_url    text       not null, # 公司图标地址
  ci_company_url         text       not null, # 公司网址
  ci_company_introduce   text       not null, # 公司简介
  ci_labels              text       not null, # 公司标签
  ci_introduction_string text       not null, # 公司介绍
  ci_pictures            text       not null, # 公司介绍中的图片
  ci_hy                  text       not null, # 行业领域
  ci_gm                  text       not null, # 公司规模
  ci_city                text       not null, # 城市
  ci_jd                  text       not null, # 融资阶段
  ci_location            text       not null, # 公司地点
  primary key (ci_id)
);
# 插入语句
insert into db_bjyzpw.tb_company_info (ci_id,
                                       ci_name,
                                       ci_company_icon_url,
                                       ci_company_url,
                                       ci_company_introduce,
                                       ci_labels,
                                       ci_introduction_string,
                                       ci_pictures,
                                       ci_hy,
                                       ci_gm,
                                       ci_city,
                                       ci_jd,
                                       ci_location)
values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
# 查询语句
select *
from db_bjyzpw.tb_company_info
where ci_id = ?;

# 表4 - 职位表
create table db_bjyzpw.tb_job_info (
  ji_id               bigint(20) not null auto_increment, # id
  ji_name             text       not null, # 职位名称
  ji_salary           text       not null, # 薪资
  ji_city             text       not null, # 工作城市
  ji_exp              text       not null, # 工作经验
  ji_grade            text       not null, # 学位
  ji_gx               text       not null, # 工作性质
  ji_tag              text       not null, # 标签
  ji_temptation       text       not null, # 职业诱惑
  ji_description      text       not null, # 职位描述
  ji_word_address     text       not null, # 工作地址（详细）
  ji_publish_time     text       not null, # 发布时间
  ji_company_name     text       not null, # 公司名称
  ji_company_icon_url text       not null, # 公司图标地址
  ji_company_id       text       not null, # 公司id
  ji_hy               text       not null, # 行业领域
  ji_jd               text       not null, # 融资阶段
  ji_gm               text       not null, # 公司规模
  ji_company_website  text       not null, # 公司网址
  primary key (ji_id)
);
# 插入语句
insert into db_bjyzpw.tb_job_info (ji_id,
                                   ji_name,
                                   ji_salary,
                                   ji_city,
                                   ji_exp,
                                   ji_grade,
                                   ji_gx,
                                   ji_tag,
                                   ji_temptation,
                                   ji_description,
                                   ji_word_address,
                                   ji_publish_time,
                                   ji_company_name,
                                   ji_company_icon_url,
                                   ji_company_id,
                                   ji_hy,
                                   ji_jd,
                                   ji_gm,
                                   ji_company_website)
values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
# 删除语句
delete
from db_bjyzpw.tb_job_info
where ji_id = ?;
# 查询语句
select *
from db_bjyzpw.tb_job_info
where ji_id = ?;