-- MySQL dump 10.10
--
-- Host: localhost    Database: aspe
-- ------------------------------------------------------
-- Server version	5.0.27-community-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts` (
  `accountid` tinyint(3) unsigned NOT NULL,
  `accountname` varchar(30) NOT NULL,
  `accountcode` smallint(5) unsigned default NULL,
  PRIMARY KEY  (`accountid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
CREATE TABLE `branch` (
  `branchid` tinyint(3) unsigned NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL default 'none',
  `balance` float NOT NULL default '0',
  `extend` tinyint(4) NOT NULL default '15',
  `counter` int(10) unsigned NOT NULL default '0',
  `advance_interest` float NOT NULL default '0',
  `service_charge` float NOT NULL default '0',
  `reserve` tinyint(4) NOT NULL default '15',
  `archive` tinyint(1) NOT NULL default '0',
  `owner` tinyint(4) NOT NULL,
  `pt_number` int(8) unsigned NOT NULL,
  PRIMARY KEY  (`branchid`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `name_2` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `last_name` varchar(20) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `middle_name` varchar(20) NOT NULL,
  `address` varchar(60) NOT NULL default '',
  `archive` tinyint(1) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

--
-- Table structure for table `genkey`
--

DROP TABLE IF EXISTS `genkey`;
CREATE TABLE `genkey` (
  `pid` int(10) unsigned NOT NULL default '0',
  `password` char(10) NOT NULL default '',
  PRIMARY KEY  (`pid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Table structure for table `interest`
--

DROP TABLE IF EXISTS `interest`;
CREATE TABLE `interest` (
  `interestid` int(10) unsigned NOT NULL,
  `day` tinyint(3) unsigned NOT NULL,
  `rate` tinyint(3) unsigned NOT NULL default '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Table structure for table `journal`
--

DROP TABLE IF EXISTS `journal`;
CREATE TABLE `journal` (
  `journalid` int(10) unsigned NOT NULL auto_increment,
  `journal_date` date NOT NULL default '0000-00-00',
  `accountid` tinyint(4) NOT NULL default '0',
  `branchid` tinyint(4) NOT NULL default '0',
  `description` varchar(100) default NULL,
  `amount` float NOT NULL default '0',
  `journal_group` varchar(18) default '0',
  PRIMARY KEY  (`journalid`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Table structure for table `ledger`
--

DROP TABLE IF EXISTS `ledger`;
CREATE TABLE `ledger` (
  `ledgerid` int(10) unsigned NOT NULL default '0',
  `ledger_date` date NOT NULL,
  `encoder` varchar(20) NOT NULL,
  PRIMARY KEY  (`ledgerid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Table structure for table `level`
--

DROP TABLE IF EXISTS `level`;
CREATE TABLE `level` (
  `id` tinyint(3) unsigned NOT NULL,
  `description` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `page`
--

DROP TABLE IF EXISTS `page`;
CREATE TABLE `page` (
  `pageid` int(10) unsigned NOT NULL default '0',
  `user` tinyint(4) NOT NULL default '10',
  `customer` tinyint(4) NOT NULL default '10',
  `pawn` tinyint(4) NOT NULL default '10',
  `redeem` tinyint(4) NOT NULL default '10',
  `foreclose` tinyint(4) NOT NULL default '10',
  `pullout` tinyint(4) NOT NULL default '10',
  `outstanding` tinyint(4) NOT NULL default '10',
  `inventory` tinyint(4) NOT NULL default '10',
  `auction` tinyint(4) NOT NULL default '10',
  PRIMARY KEY  (`pageid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Table structure for table `pawn`
--

DROP TABLE IF EXISTS `pawn`;
CREATE TABLE `pawn` (
  `pid` int(10) unsigned NOT NULL auto_increment,
  `serial` bigint(20) unsigned NOT NULL default '0',
  `bcode` tinyint(3) unsigned NOT NULL default '0',
  `create_date` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `loan_date` date NOT NULL default '0000-00-00',
  `nameid` int(10) unsigned NOT NULL,
  `loan` float NOT NULL default '0',
  `appraised` float NOT NULL default '0',
  `description` varchar(255) default NULL,
  `service_charge` float NOT NULL default '0',
  `advance_interest` float NOT NULL default '0',
  `encoder` varchar(50) default NULL,
  `extend` tinyint(3) unsigned NOT NULL default '0',
  `branch` int(10) unsigned NOT NULL,
  `bpid` int(8) unsigned NOT NULL,
  `pt` int(8) unsigned NOT NULL,
  PRIMARY KEY  (`pid`),
  KEY `nameid` (`nameid`)
) ENGINE=MyISAM AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

--
-- Table structure for table `pullout`
--

DROP TABLE IF EXISTS `pullout`;
CREATE TABLE `pullout` (
  `pid` int(10) unsigned NOT NULL default '0',
  `create_date` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `pullout_date` date NOT NULL default '0000-00-00',
  `username` varchar(50) default NULL,
  `encoder` varchar(50) default NULL,
  `auction` tinyint(1) NOT NULL default '0',
  PRIMARY KEY  (`pid`),
  KEY `username` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Table structure for table `redeem`
--

DROP TABLE IF EXISTS `redeem`;
CREATE TABLE `redeem` (
  `pid` int(10) unsigned NOT NULL default '0',
  `create_date` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `redeem_date` date NOT NULL default '0000-00-00',
  `encoder` varchar(50) default NULL,
  `interest` float NOT NULL,
  PRIMARY KEY  (`pid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uid` tinyint(4) NOT NULL auto_increment,
  `username` varchar(50) default NULL,
  `password` varchar(50) default NULL,
  `lastname` varchar(50) default NULL,
  `firstname` varchar(50) default NULL,
  `mi` varchar(2) default NULL,
  `level` tinyint(3) unsigned default NULL,
  `branch` int(10) unsigned NOT NULL default '0',
  `archive` tinyint(1) NOT NULL default '0',
  `loan_date` date default NULL,
  PRIMARY KEY  (`uid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

INSERT INTO `users` VALUES (0,'admin','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,9,1,0,'2007-06-12');

insert into branch (branchid,archive) values (1,0);

insert into page values (1,10,10,10,10,10,10,10,10,10);

insert into level values(1,"ENCODER");
insert into level values(7,"MANAGER");
insert into level values(8,"OWNER");
insert into level values(9,"ADMIN");

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2008-10-20 15:31:02
