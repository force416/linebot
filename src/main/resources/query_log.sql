CREATE TABLE `query_log` (
  `serial_no` int(32) NOT NULL AUTO_INCREMENT COMMENT '流水號',
  `line_id` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'line的id',
  `name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT 'line的名字',
  `keyword` varchar(600) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '查詢的關鍵字',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '新增日期',
  PRIMARY KEY (`serial_no`),
  UNIQUE KEY `serial_no_UNIQUE` (`serial_no`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
