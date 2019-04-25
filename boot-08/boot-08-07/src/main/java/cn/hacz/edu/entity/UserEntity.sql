-- auto Generated on 2019-04-25
-- DROP TABLE IF EXISTS user_entity;
CREATE TABLE user_entity(
	id INT (11) NOT NULL AUTO_INCREMENT COMMENT '功能描述：主键id',
	name VARCHAR (50) NOT NULL DEFAULT '' COMMENT '属性描述：姓名',
	age INT (11) NOT NULL DEFAULT -1 COMMENT '功能描述：年龄',
	class_num INT (11) NOT NULL DEFAULT -1 COMMENT '功能描述：班级号',
	birthday DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '出生日期',
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'user_entity';
