-- auto Generated on 2019-01-29
-- DROP TABLE IF EXISTS tb_log;
CREATE TABLE tb_log(
	id INT (11) NOT NULL AUTO_INCREMENT COMMENT '功能描述：id',
	name VARCHAR (50) NOT NULL DEFAULT '' COMMENT '属性描述：name',
	info VARCHAR (50) NOT NULL DEFAULT '' COMMENT '属性描述：info',
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'tb_log';