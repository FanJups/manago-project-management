-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: manago-db
-- Generation Time: Jan 07, 2019 at 03:33 PM
-- Server version: 5.7.22
-- PHP Version: 7.2.6

SET @@auto_increment_increment=10;
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `manago_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `customer_id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `zip_code` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `customer_project`
--

DROP TABLE IF EXISTS `customer_project`;
CREATE TABLE `customer_project` (
  `name` varchar(255) NOT NULL,
  `customer_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `employee_id` bigint(20) NOT NULL,
  `employment_type` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `salary` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
CREATE TABLE `history` (
  `history_id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `operation_id` varchar(255) NOT NULL,
  `task_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `team` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `resource_id` bigint(20) NOT NULL,
  `bought_at` datetime DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `manufacturer` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `resource_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `resource_type`
--

DROP TABLE IF EXISTS `resource_type`;
CREATE TABLE `resource_type` (
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
CREATE TABLE `status` (
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `task_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `project_name` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `name` varchar(255) NOT NULL,
  `monthly_cost` double DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `team_employee`
--

DROP TABLE IF EXISTS `team_employee`;
CREATE TABLE `team_employee` (
  `name` varchar(255) NOT NULL,
  `employee_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `team_resource`
--

DROP TABLE IF EXISTS `team_resource`;
CREATE TABLE `team_resource` (
  `name` varchar(255) NOT NULL,
  `resource_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `employee_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `customer_project`
--
ALTER TABLE `customer_project`
  ADD PRIMARY KEY (`name`,`customer_id`),
  ADD KEY `FKnb0kcjkl2j8xdssyk4l6p5ja6` (`customer_id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`);

--
-- Indexes for table `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`history_id`),
  ADD KEY `FK95xsp1wupmh62devoxh4o2okh` (`task_id`);

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`name`),
  ADD KEY `FKovdk5eklge43h5dutm5i5nx8o` (`team`);

--
-- Indexes for table `resource`
--
ALTER TABLE `resource`
  ADD PRIMARY KEY (`resource_id`),
  ADD KEY `FKokgoivf49492nnqln8q986ltu` (`resource_type`);

--
-- Indexes for table `resource_type`
--
ALTER TABLE `resource_type`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`task_id`),
  ADD KEY `FK82ogu5quub0bhyuhp25riy7pf` (`parent_id`),
  ADD KEY `FKmn1q28ckesniikwvnhsnao76j` (`project_name`),
  ADD KEY `FKio4h16n2qjj7hwuo2sp0u223m` (`status`);

--
-- Indexes for table `team`
--
ALTER TABLE `team`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `team_employee`
--
ALTER TABLE `team_employee`
  ADD PRIMARY KEY (`name`,`employee_id`),
  ADD KEY `FK7vlhtqnfmyn6egrq2low85sq3` (`employee_id`);

--
-- Indexes for table `team_resource`
--
ALTER TABLE `team_resource`
  ADD PRIMARY KEY (`name`,`resource_id`),
  ADD KEY `FKb0bcdqf78fdeukuogli57aq21` (`resource_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`),
  ADD KEY `FK211dk0pe7l3aibwce8yy61ota` (`employee_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `employee_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `history`
--
ALTER TABLE `history`
  MODIFY `history_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `resource`
--
ALTER TABLE `resource`
  MODIFY `resource_id` bigint(20) NOT NULL AUTO_INCREMENT;


ALTER TABLE `task`
  MODIFY `task_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customer_project`
--
ALTER TABLE `customer_project`
  ADD CONSTRAINT `FKbkacyicaqw3fxmwei6aujpmn7` FOREIGN KEY (`name`) REFERENCES `project` (`name`),
  ADD CONSTRAINT `FKnb0kcjkl2j8xdssyk4l6p5ja6` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

--
-- Constraints for table `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `FK95xsp1wupmh62devoxh4o2okh` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE;

--
-- Constraints for table `project`
--
ALTER TABLE `project`
  ADD CONSTRAINT `FKovdk5eklge43h5dutm5i5nx8o` FOREIGN KEY (`team`) REFERENCES `team` (`name`) ON DELETE CASCADE;

--
-- Constraints for table `resource`
--
ALTER TABLE `resource`
  ADD CONSTRAINT `FKokgoivf49492nnqln8q986ltu` FOREIGN KEY (`resource_type`) REFERENCES `resource_type` (`name`) ON DELETE CASCADE;

--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `FK82ogu5quub0bhyuhp25riy7pf` FOREIGN KEY (`parent_id`) REFERENCES `task` (`task_id`),
  ADD CONSTRAINT `FKio4h16n2qjj7hwuo2sp0u223m` FOREIGN KEY (`status`) REFERENCES `status` (`name`),
  ADD CONSTRAINT `FKmn1q28ckesniikwvnhsnao76j` FOREIGN KEY (`project_name`) REFERENCES `project` (`name`);

--
-- Constraints for table `team_employee`
--
ALTER TABLE `team_employee`
  ADD CONSTRAINT `FK7vlhtqnfmyn6egrq2low85sq3` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`),
  ADD CONSTRAINT `FKk2c4n4ehqtyvb4eaw8srb4qy` FOREIGN KEY (`name`) REFERENCES `team` (`name`);

--
-- Constraints for table `team_resource`
--
ALTER TABLE `team_resource`
  ADD CONSTRAINT `FKb0bcdqf78fdeukuogli57aq21` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`resource_id`),
  ADD CONSTRAINT `FKfmid4vv2uufdtpulp38r86c7t` FOREIGN KEY (`name`) REFERENCES `team` (`name`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK211dk0pe7l3aibwce8yy61ota` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`);

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
