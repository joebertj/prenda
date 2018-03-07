-- MySQL dump 10.10
--
-- Host: localhost    Database: prenda
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
  `accountid` tinyint unsigned NOT NULL,
  `accountname` varchar(30) NOT NULL,
  `accountcode` smallint unsigned default NULL,
  PRIMARY KEY  (`accountid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
CREATE TABLE `branch` (
  `branchid` smallint unsigned NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL default 'none',
  `balance` float NOT NULL default '0',
  `extend` tinyint unsigned NOT NULL default '15',
  `counter` tinyint unsigned NOT NULL default '0',
  `advance_interest` float NOT NULL default '0',
  `service_charge` float NOT NULL default '0',
  `reserve` tinyint unsigned NOT NULL default '15',
  `archive` boolean NOT NULL default '0',
  `owner` tinyint unsigned NOT NULL,
  `pt_number` int unsigned NOT NULL,
  PRIMARY KEY  (`branchid`),
  UNIQUE KEY `name` (`name`),
  FOREIGN KEY `owner` (`owner`) REFERENCES users (`uid`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` mediumint unsigned NOT NULL auto_increment,
  `last_name` varchar(20) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `middle_name` varchar(20) NOT NULL,
  `address` varchar(100) NOT NULL default '',
  `archive` boolean NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Table structure for table `genkey`
--

DROP TABLE IF EXISTS `genkey`;
CREATE TABLE `genkey` (
  `pid` int(10) unsigned NOT NULL default '0',
  `password` char(10) NOT NULL default '',
  PRIMARY KEY  (`pid`),
  FOREIGN KEY `pid` (`pid`) REFERENCES pawn (`pid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Table structure for table `interest`
--

DROP TABLE IF EXISTS `interest`;
CREATE TABLE `interest` (
  `interestid` smallint unsigned NOT NULL,
  `day` tinyint unsigned NOT NULL,
  `rate` tinyint unsigned NOT NULL default '0',
  PRIMARY KEY (`interestid`,`day`),
  FOREIGN KEY `interestid` (`interestid`) REFERENCES branch (`branchid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Table structure for table `journal`
--

DROP TABLE IF EXISTS `journal`;
CREATE TABLE `journal` (
  `journalid` int unsigned NOT NULL auto_increment,
  `journal_date` date NOT NULL default '0000-00-00',
  `accountid` tinyint(4) NOT NULL default '0',
  `branchid` tinyint(4) NOT NULL default '0',
  `description` varchar(100) default NULL,
  `amount` float NOT NULL default '0',
  `journal_group` varchar(20) default '0',
  PRIMARY KEY  (`journalid`),
  FOREIGN KEY `branchid` (`branchid`) REFERENCES branch (`branchid`),
  FOREIGN KEY `accountid` (`accountid`) REFERENCES accounts (`accountid`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Table structure for table `ledger`
--

DROP TABLE IF EXISTS `ledger`;
CREATE TABLE `ledger` (
  `ledgerid` int unsigned NOT NULL default '0',
  `ledger_date` date NOT NULL,
  `encoder` varchar(50) NOT NULL,
  PRIMARY KEY  (`ledgerid`),
  FOREIGN KEY `encoder` (`encoder`) REFERENCES users (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Table structure for table `level`
--

DROP TABLE IF EXISTS `level`;
CREATE TABLE `level` (
  `id` tinyint unsigned NOT NULL,
  `description` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Table structure for table `page`
--

DROP TABLE IF EXISTS `page`;
CREATE TABLE `page` (
  `pageid` smallint unsigned NOT NULL default '0',
  `user` tinyint NOT NULL default '10',
  `customer` tinyint NOT NULL default '10',
  `pawn` tinyint NOT NULL default '10',
  `redeem` tinyint NOT NULL default '10',
  `foreclose` tinyint NOT NULL default '10',
  `pullout` tinyint NOT NULL default '10',
  `outstanding` tinyint NOT NULL default '10',
  `inventory` tinyint NOT NULL default '10',
  `auction` tinyint NOT NULL default '10',
  PRIMARY KEY  (`pageid`),
  FOREIGN KEY `pageid` (`pageid`) REFERENCES branch (`branchid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Table structure for table `pawn`
--

DROP TABLE IF EXISTS `pawn`;
CREATE TABLE `pawn` (
  `pid` int unsigned NOT NULL auto_increment,
  `serial` int unsigned NOT NULL default '0',
  `bcode` tinyint unsigned NOT NULL default '0',
  `create_date` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `loan_date` date NOT NULL default '0000-00-00',
  `nameid` int unsigned NOT NULL,
  `loan` float NOT NULL default '0',
  `appraised` float NOT NULL default '0',
  `description` varchar(255) default NULL,
  `service_charge` float NOT NULL default '0',
  `advance_interest` float NOT NULL default '0',
  `encoder` varchar(20) default NULL,
  `extend` tinyint unsigned NOT NULL default '0',
  `branch` int unsigned NOT NULL,
  `bpid` int unsigned NOT NULL,
  `pt` int unsigned NOT NULL,
  PRIMARY KEY  (`pid`),
  FOREIGN KEY `nameid` (`nameid`) REFERENCES customer (`id`),
  FOREIGN KEY `encoder` (`encoder`) REFERENCES users (`username`),
  FOREIGN KEY `branch` (`branch`) REFERENCES branch (`branchid`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Table structure for table `pullout`
--

DROP TABLE IF EXISTS `pullout`;
CREATE TABLE `pullout` (
  `pid` int unsigned NOT NULL default '0',
  `create_date` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `pullout_date` date NOT NULL default '0000-00-00',
  `username` varchar(20) default NULL,
  `encoder` varchar(20) default NULL,
  `auction` boolean NOT NULL default '0',
  PRIMARY KEY  (`pid`),
  FOREIGN KEY `username` (`username`) REFERENCES users (`username`),
  FOREIGN KEY `encoder` (`encoder`) REFERENCES users (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Table structure for table `redeem`
--

DROP TABLE IF EXISTS `redeem`;
CREATE TABLE `redeem` (
  `pid` int unsigned NOT NULL default '0',
  `create_date` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `redeem_date` date NOT NULL default '0000-00-00',
  `encoder` varchar(20) default NULL,
  `interest` float NOT NULL,
  PRIMARY KEY  (`pid`),
  FOREIGN KEY `encoder` (`encoder`) REFERENCES users (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uid` smallint NOT NULL auto_increment,
  `username` varchar(20) default NULL,
  `password` varchar(60) default NULL,
  `lastname` varchar(20) default NULL,
  `firstname` varchar(50) default NULL,
  `mi` varchar(2) default NULL,
  `level` tinyint unsigned default NULL,
  `branch` smallint unsigned NOT NULL default '0',
  `archive` boolean NOT NULL default '0',
  `loan_date` date default NULL,
  PRIMARY KEY  (`uid`),
  UNIQUE KEY `username` (`username`),
  FOREIGN KEY `branch` (`branch`) REFERENCES branch (`branchid`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `jewelry`;
CREATE TABLE `jewelry` (
  `branchid` smallint unsigned NOT NULL,
  `caratid` tinyint unsigned NOT NULL default '0',
  `minimum` float NOT NULL default '0',
  `maximum` float NOT NULL default '0',
  PRIMARY KEY  (`branchid`,`caratid`),
  FOREIGN KEY `branchid` (`branchid`) REFERENCES branch (`branchid`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `users` VALUES (1,'admin','$2a$10$xjahs1aLp6l2pjNtN6GTseil9bj5eWRiSP.l0SmCzXdyEHX/IQY1e',NULL,NULL,NULL,9,1,0,'2007-06-12');
INSERT INTO `users` VALUES (2,'owner','$2a$12$tAAhe7xEy9cJIyoth/d3bOau8Cs04wXxGVlXdII76vlXuaDWYOwTW',NULL,NULL,NULL,8,1,0,'2007-06-12');

insert into branch (branchid,archive) values (1,0);

insert into page values (1,10,10,10,10,10,10,10,10,10);

insert into jewelry values (1,10,100,150),(1,14,150,200),(1,18,200,250),(1,22,250,300),(1,24,300,350);

insert into level values(1,"ENCODER");
insert into level values(3,"LIAISON");
insert into level values(7,"MANAGER");
insert into level values(8,"OWNER");
insert into level values(9,"ADMIN");

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,'ASSET',1),(2,'LIABILITIES',2),(3,'CAPITAL',3),(4,'INCOME',4),(5,'EXPENSES',5),(6,'CURRENT ASSEST',101),(7,'CASH ON HAND',10101),(8,'ACCOUNTS PAYABLE',201),(9,'FIXED ASSET',102),(10,'FURNITURES AND EXPENSES',10201),(11,'ACCUM. DEPRECATION - F & F',10202),(12,'OWNER CAPITAL',301),(13,'OWNER DRAWINGS',302),(14,'INTEREST INCOME',401),(15,'OTHER INCOME',402),(16,'OFFICE SUPPLIES',501),(17,'SALARIES AND WAGES',502),(18,'TRANSPORTATION AND ALLOWANCES',503),(19,'LOANS EXTENDED',10103),(20,'UTILITIES (POWER & WATER)',504),(21,'COMMUNICATION AND POSTAGE',505),(22,'TAXES AND LICENCES',506),(23,'DONATION',507),(24,'RENT',508),(25,'ADVERTISING',509),(26,'JEWELRY SALE',403),(27,'MEETINGS AND REPRESENTATION',510),(28,'DEPRECATION EXPENSE',511),(29,'IT EQUIPMENT',10203),(30,'ACCUM. DEPRECIATION - IT EQUIP',10204),(31,'SECURITY SERVICES',512),(32,'PREMIUM',515),(33,'REPAIRS AND MAINTENANCE',513),(34,'HONORARIUM',514),(35,'STAFF HOUSE',516),(36,'INTEREST ON PAST DUE LOANS',517);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `interest` WRITE;
/*!40000 ALTER TABLE `interest` DISABLE KEYS */;
INSERT INTO `interest` VALUES (1,0,1),(1,1,2),(1,2,2),(1,3,2),(1,4,3),(1,5,3),(1,6,3),(1,7,3),(1,8,4),(1,9,4),(1,10,4),(1,11,5),(1,12,5),(1,13,5),(1,14,5),(1,15,6),(1,16,6),(1,17,6),(1,18,6),(1,19,6),(1,20,6),(1,21,6),(1,22,6),(1,23,6),(1,24,6),(1,25,6),(1,26,6),(1,27,6),(1,28,6),(1,29,6),(1,30,6),(1,31,6),(1,32,6),(1,33,6),(1,34,6),(2,0,2),(2,1,2),(2,2,2),(2,3,3),(2,4,3),(2,5,3),(2,6,4),(2,7,4),(2,8,4),(2,9,4),(2,10,5),(2,11,5),(2,12,5),(2,13,5),(2,14,5),(2,15,5),(2,16,5),(2,17,5),(2,18,5),(2,19,5),(2,20,5),(2,21,5),(2,22,5),(2,23,5),(2,24,5),(2,25,5),(2,26,5),(2,27,5),(2,28,5),(2,29,5),(2,30,5),(2,31,5),(2,32,5),(2,33,5),(2,34,5);
/*!40000 ALTER TABLE `interest` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2008-10-20 15:31:02
