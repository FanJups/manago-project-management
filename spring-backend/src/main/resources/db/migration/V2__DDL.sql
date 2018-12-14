
START TRANSACTION;

DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `customer_project`;
CREATE TABLE IF NOT EXISTS `customer_project` (
  `customer_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`customer_id`,`name`),
  KEY `FKbkacyicaqw3fxmwei6aujpmn7` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `employee_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employment_type` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `salary` double DEFAULT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



DROP TABLE IF EXISTS `history`;
CREATE TABLE IF NOT EXISTS `history` (
  `history_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `operation_date` datetime NOT NULL,
  `operation_id` varchar(255) NOT NULL,
  `task_id` bigint(20) NOT NULL,
  PRIMARY KEY (`history_id`),
  KEY `FK95xsp1wupmh62devoxh4o2okh` (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `project`;
CREATE TABLE IF NOT EXISTS `project` (
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `team` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`name`),
  KEY `FKovdk5eklge43h5dutm5i5nx8o` (`team`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `resource`;
CREATE TABLE IF NOT EXISTS `resource` (
  `resource_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bought_at` datetime DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `manufacturer` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `resource_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`resource_id`),
  KEY `FKokgoivf49492nnqln8q986ltu` (`resource_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `resource_type`;
CREATE TABLE IF NOT EXISTS `resource_type` (
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `status`;
CREATE TABLE IF NOT EXISTS `status` (
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `task`;
CREATE TABLE IF NOT EXISTS `task` (
  `task_id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `project_name` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  KEY `FK82ogu5quub0bhyuhp25riy7pf` (`parent_id`),
  KEY `FKmn1q28ckesniikwvnhsnao76j` (`project_name`),
  KEY `FKio4h16n2qjj7hwuo2sp0u223m` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `team`;
CREATE TABLE IF NOT EXISTS `team` (
  `name` varchar(255) NOT NULL,
  `monthly_cost` double DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `team_employee`;
CREATE TABLE IF NOT EXISTS `team_employee` (
  `name` varchar(255) NOT NULL,
  `employee_id` bigint(20) NOT NULL,
  PRIMARY KEY (`name`,`employee_id`),
  KEY `FK7vlhtqnfmyn6egrq2low85sq3` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `team_resource`;
CREATE TABLE IF NOT EXISTS `team_resource` (
  `name` varchar(255) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  PRIMARY KEY (`name`,`resource_id`),
  KEY `FKb0bcdqf78fdeukuogli57aq21` (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `employee_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `FK211dk0pe7l3aibwce8yy61ota` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `customer_project`
  ADD CONSTRAINT `FKbkacyicaqw3fxmwei6aujpmn7` FOREIGN KEY (`name`) REFERENCES `project` (`name`),
  ADD CONSTRAINT `FKnb0kcjkl2j8xdssyk4l6p5ja6` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

ALTER TABLE `history`
  ADD CONSTRAINT `FK95xsp1wupmh62devoxh4o2okh` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE;

ALTER TABLE `project`
  ADD CONSTRAINT `FKovdk5eklge43h5dutm5i5nx8o` FOREIGN KEY (`team`) REFERENCES `team` (`name`) ON DELETE CASCADE;

ALTER TABLE `resource`
  ADD CONSTRAINT `FKokgoivf49492nnqln8q986ltu` FOREIGN KEY (`resource_type`) REFERENCES `resource_type` (`name`) ON DELETE CASCADE;

ALTER TABLE `task`
  ADD CONSTRAINT `FK82ogu5quub0bhyuhp25riy7pf` FOREIGN KEY (`parent_id`) REFERENCES `task` (`task_id`),
  ADD CONSTRAINT `FKio4h16n2qjj7hwuo2sp0u223m` FOREIGN KEY (`status`) REFERENCES `status` (`name`) ON DELETE CASCADE,
  ADD CONSTRAINT `FKmn1q28ckesniikwvnhsnao76j` FOREIGN KEY (`project_name`) REFERENCES `project` (`name`) ON DELETE CASCADE;

ALTER TABLE `team_employee`
  ADD CONSTRAINT `FK7vlhtqnfmyn6egrq2low85sq3` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`),
  ADD CONSTRAINT `FKk2c4n4ehqtyvb4eaw8srb4qy` FOREIGN KEY (`name`) REFERENCES `team` (`name`);

ALTER TABLE `team_resource`
  ADD CONSTRAINT `FKb0bcdqf78fdeukuogli57aq21` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`resource_id`),
  ADD CONSTRAINT `FKfmid4vv2uufdtpulp38r86c7t` FOREIGN KEY (`name`) REFERENCES `team` (`name`);

ALTER TABLE `user`
  ADD CONSTRAINT `FK211dk0pe7l3aibwce8yy61ota` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`);
COMMIT;
