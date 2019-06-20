-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: christianweavesdotcom
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.16.04.1

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
-- Table structure for table `DATABASECHANGELOG`
--

DROP TABLE IF EXISTS `DATABASECHANGELOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOG`
--

LOCK TABLES `DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOG` VALUES ('create article table','csw','db.changelog.0.0.1.xml','2017-07-27 22:41:04',1,'EXECUTED','7:87a157dc45f32edfc60a084856067732','createTable tableName=tb_articles','',NULL,'3.5.3',NULL,NULL,'1191664141'),('create article archive table','csw','db.changelog.0.0.1.xml','2019-03-23 23:10:31',3,'EXECUTED','7:fbcfc5fc1ed7400af1d3360c556deec8','createTable tableName=tb_articles_archive','',NULL,'3.5.3',NULL,NULL,'3382631715'),('sample content','csw','db.changelog.0.0.1.xml','2019-05-20 22:22:35',4,'EXECUTED','7:695c545cc2dee2a8cdc590348dea3dd1','sql','',NULL,'3.5.3',NULL,NULL,'8387355434'),('login table','csw','db.changelog.0.0.1.xml','2019-05-20 23:12:10',5,'EXECUTED','7:44912f674429abddf0eb262581ea94f2','sql','',NULL,'3.5.3',NULL,NULL,'8390330101');
/*!40000 ALTER TABLE `DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DATABASECHANGELOGLOCK`
--

DROP TABLE IF EXISTS `DATABASECHANGELOGLOCK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DATABASECHANGELOGLOCK` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOGLOCK`
--

LOCK TABLES `DATABASECHANGELOGLOCK` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOGLOCK` VALUES (1,_binary '\0',NULL,NULL);
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_articles`
--

DROP TABLE IF EXISTS `tb_articles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_articles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `subtitle` varchar(255) DEFAULT NULL,
  `body` longtext,
  `date_added` timestamp NULL DEFAULT NULL,
  `featured` tinyint(4) DEFAULT NULL,
  `archived` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_articles`
--

LOCK TABLES `tb_articles` WRITE;
/*!40000 ALTER TABLE `tb_articles` DISABLE KEYS */;
INSERT INTO `tb_articles` VALUES (10,'Useful links','A place to store useful links found on my digital journey','<p>&nbsp;</p>\r\n\r\n<p>Nice effect for article backgrounds<a href=\"https://codepen.io/anon/pen/xNYPOa\" style=\"text-decoration:none;\"><u> https://codepen.io/anon/pen/xNYPOa</u></a></p>\r\n\r\n<p>Some good css tutorials<a href=\"https://css-tricks.com/\" style=\"text-decoration:none;\"><u> https://css-tricks.com/</u></a></p>\r\n','2019-06-05 22:34:27',1,0);
/*!40000 ALTER TABLE `tb_articles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_articles_archive`
--

DROP TABLE IF EXISTS `tb_articles_archive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_articles_archive` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `subtitle` varchar(255) DEFAULT NULL,
  `body` text,
  `date_added` timestamp NULL DEFAULT NULL,
  `featured` tinyint(4) DEFAULT NULL,
  `archived` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_articles_archive`
--

LOCK TABLES `tb_articles_archive` WRITE;
/*!40000 ALTER TABLE `tb_articles_archive` DISABLE KEYS */;
INSERT INTO `tb_articles_archive` VALUES (1,'A TIT (not a real tit)','LE!','<p>TEST</p>\r\n\r\n<p>tests</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">public void class {}</code></pre>\r\n\r\n<p>This code will do stuff!</p>\r\n\r\n<p><img alt=\"alt text\" src=\"http://localhost:8080/ChristianWeaves.com/getImage?file=index.png\" style=\"float:right; height:160px; width:465px\" /></p>\r\n',NULL,1,1),(2,'c title','c subtitle','c body',NULL,NULL,NULL),(3,'A TITLE (not a real tit)','LE!','<p>TEST</p>\r\n\r\n<p>tests</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">public void class {}</code></pre>\r\n\r\n<p>This code will do stuff!</p>\r\n\r\n<p><img alt=\"alt text\" src=\"http://localhost:8080/ChristianWeaves.com/getImage?file=index.png\" style=\"float:right; height:160px; width:465px\" /></p>\r\n',NULL,1,1),(4,'b title','b subtitle','<pre>\r\nb body b c d \r\n\r\npublic class csw {\r\n   abc\r\n}\r\n\r\n\r\n</pre>\r\n\r\n<pre>\r\n<code>hello mofo</code></pre>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&lt;code&gt;test&lt;/code&gt;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAJoAAABeCAYAAADbhg21AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAoRJREFUeJzt3N1uqzAQReGh6vu/sntRWUKOQwjYm/HM+m6OTqWmpCxsfpxupZRiwGQ/T28AciA0SBAaJAgNEoQGCUKDBKFBgtAgQWiQIDRIEBokCA0ShAYJQoMEoUGC0CBBaJAgNEgQGiQIDRKEBglCgwShQYLQIEFokPid+upb838+E5/WnND2gZXO15DO2NB6gQE28hytRlasH1kxRrXExoxomzGC4RBXnZAgNEgQGiQIDRKEBglCgwShQYLQIEFokCA0SBAaJAgNEoQGifurN7bm35mOfgarR1y7H1pdZ6ba0b2fwzo39+Z+ZmCU/aLKUa/Val/7U7zfbsvV1cdB1vr5D21EZJ8+JHM2vt73nNmuILHcscbFwIidVGzsMvPk4XxrjdCwPEKDBKHdcWbK5YrYzAhNg/M5QnMt0GhIaJAgNEiMuWHLOcixq1NgoD8j4f/JwOo4CM0se2hBRosVxAytF1Dva+9GG+XSpyTWDu3dg+3eQ/NvpjD10qcE5vwhvtE76MrqitUFG03HhlZHgpHBRY7pk0Dvffx9tP1SnGBHJa6bd8M20NEoVWeEYL+/uRcD+xuOwX5xUwQMrJr/CKpOpUyjqemedRJbamvfR4siwemFdvUGo9qrel4WODKzJ5YJtffakMIz69G4QPiX6P0/e47m+ZnizG1KcE7W4mJAzeuBNdnzS7kVU6iXHZtoqmw9H1oVfSckubp8x8fUuX8IH21HRD+ATvIRWuX54uCKSO/lJl+hRcEo9sJnaCtf/q+87RP5C21/vrbS1ENgh/yFVq32qIrADvkNzYydF4if+2gIjdAgQWiQIDRIEBokCA0ShAYJQoMEoUGC0CBBaJD4A9ziV+Oo5Zr2AAAAAElFTkSuQmCC\" /></p>\r\n\r\n<p>&nbsp;</p>\r\n',NULL,NULL,0),(5,'b title','b subtitle','<pre>\r\nb body b c d \r\n\r\npublic class csw {\r\n   abc\r\n}\r\n\r\n\r\n</pre>\r\n\r\n<pre>\r\n<code>hello mofo</code></pre>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&lt;code&gt;test&lt;/code&gt;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAJoAAABeCAYAAADbhg21AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAoRJREFUeJzt3N1uqzAQReGh6vu/sntRWUKOQwjYm/HM+m6OTqWmpCxsfpxupZRiwGQ/T28AciA0SBAaJAgNEoQGCUKDBKFBgtAgQWiQIDRIEBokCA0ShAYJQoMEoUGC0CBBaJAgNEgQGiQIDRKEBglCgwShQYLQIEFokPid+upb838+E5/WnND2gZXO15DO2NB6gQE28hytRlasH1kxRrXExoxomzGC4RBXnZAgNEgQGiQIDRKEBglCgwShQYLQIEFokCA0SBAaJAgNEoQGifurN7bm35mOfgarR1y7H1pdZ6ba0b2fwzo39+Z+ZmCU/aLKUa/Val/7U7zfbsvV1cdB1vr5D21EZJ8+JHM2vt73nNmuILHcscbFwIidVGzsMvPk4XxrjdCwPEKDBKHdcWbK5YrYzAhNg/M5QnMt0GhIaJAgNEiMuWHLOcixq1NgoD8j4f/JwOo4CM0se2hBRosVxAytF1Dva+9GG+XSpyTWDu3dg+3eQ/NvpjD10qcE5vwhvtE76MrqitUFG03HhlZHgpHBRY7pk0Dvffx9tP1SnGBHJa6bd8M20NEoVWeEYL+/uRcD+xuOwX5xUwQMrJr/CKpOpUyjqemedRJbamvfR4siwemFdvUGo9qrel4WODKzJ5YJtffakMIz69G4QPiX6P0/e47m+ZnizG1KcE7W4mJAzeuBNdnzS7kVU6iXHZtoqmw9H1oVfSckubp8x8fUuX8IH21HRD+ATvIRWuX54uCKSO/lJl+hRcEo9sJnaCtf/q+87RP5C21/vrbS1ENgh/yFVq32qIrADvkNzYydF4if+2gIjdAgQWiQIDRIEBokCA0ShAYJQoMEoUGC0CBBaJD4A9ziV+Oo5Zr2AAAAAElFTkSuQmCC\" /></p>\r\n\r\n<p>&nbsp;</p>\r\n',NULL,NULL,0),(6,'b title','b subtitle','<pre>\r\nb body b c d \r\n\r\npublic class csw {\r\n   abc\r\n}\r\n\r\n\r\n</pre>\r\n\r\n<pre>\r\n<code>hello mofo</code></pre>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&lt;code&gt;test&lt;/code&gt;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAJoAAABeCAYAAADbhg21AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAoRJREFUeJzt3N1uqzAQReGh6vu/sntRWUKOQwjYm/HM+m6OTqWmpCxsfpxupZRiwGQ/T28AciA0SBAaJAgNEoQGCUKDBKFBgtAgQWiQIDRIEBokCA0ShAYJQoMEoUGC0CBBaJAgNEgQGiQIDRKEBglCgwShQYLQIEFokPid+upb838+E5/WnND2gZXO15DO2NB6gQE28hytRlasH1kxRrXExoxomzGC4RBXnZAgNEgQGiQIDRKEBglCgwShQYLQIEFokCA0SBAaJAgNEoQGifurN7bm35mOfgarR1y7H1pdZ6ba0b2fwzo39+Z+ZmCU/aLKUa/Val/7U7zfbsvV1cdB1vr5D21EZJ8+JHM2vt73nNmuILHcscbFwIidVGzsMvPk4XxrjdCwPEKDBKHdcWbK5YrYzAhNg/M5QnMt0GhIaJAgNEiMuWHLOcixq1NgoD8j4f/JwOo4CM0se2hBRosVxAytF1Dva+9GG+XSpyTWDu3dg+3eQ/NvpjD10qcE5vwhvtE76MrqitUFG03HhlZHgpHBRY7pk0Dvffx9tP1SnGBHJa6bd8M20NEoVWeEYL+/uRcD+xuOwX5xUwQMrJr/CKpOpUyjqemedRJbamvfR4siwemFdvUGo9qrel4WODKzJ5YJtffakMIz69G4QPiX6P0/e47m+ZnizG1KcE7W4mJAzeuBNdnzS7kVU6iXHZtoqmw9H1oVfSckubp8x8fUuX8IH21HRD+ATvIRWuX54uCKSO/lJl+hRcEo9sJnaCtf/q+87RP5C21/vrbS1ENgh/yFVq32qIrADvkNzYydF4if+2gIjdAgQWiQIDRIEBokCA0ShAYJQoMEoUGC0CBBaJD4A9ziV+Oo5Zr2AAAAAElFTkSuQmCC\" /></p>\r\n\r\n<p>&nbsp;</p>\r\n',NULL,NULL,0),(7,'b title','b subtitle','<pre>\r\nb body b c d \r\n\r\npublic class csw {\r\n   abc\r\n}\r\n\r\n\r\n</pre>\r\n\r\n<pre>\r\n<code>hello mofo</code></pre>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&lt;code&gt;test&lt;/code&gt;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAJoAAABeCAYAAADbhg21AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAoRJREFUeJzt3N1uqzAQReGh6vu/sntRWUKOQwjYm/HM+m6OTqWmpCxsfpxupZRiwGQ/T28AciA0SBAaJAgNEoQGCUKDBKFBgtAgQWiQIDRIEBokCA0ShAYJQoMEoUGC0CBBaJAgNEgQGiQIDRKEBglCgwShQYLQIEFokPid+upb838+E5/WnND2gZXO15DO2NB6gQE28hytRlasH1kxRrXExoxomzGC4RBXnZAgNEgQGiQIDRKEBglCgwShQYLQIEFokCA0SBAaJAgNEoQGifurN7bm35mOfgarR1y7H1pdZ6ba0b2fwzo39+Z+ZmCU/aLKUa/Val/7U7zfbsvV1cdB1vr5D21EZJ8+JHM2vt73nNmuILHcscbFwIidVGzsMvPk4XxrjdCwPEKDBKHdcWbK5YrYzAhNg/M5QnMt0GhIaJAgNEiMuWHLOcixq1NgoD8j4f/JwOo4CM0se2hBRosVxAytF1Dva+9GG+XSpyTWDu3dg+3eQ/NvpjD10qcE5vwhvtE76MrqitUFG03HhlZHgpHBRY7pk0Dvffx9tP1SnGBHJa6bd8M20NEoVWeEYL+/uRcD+xuOwX5xUwQMrJr/CKpOpUyjqemedRJbamvfR4siwemFdvUGo9qrel4WODKzJ5YJtffakMIz69G4QPiX6P0/e47m+ZnizG1KcE7W4mJAzeuBNdnzS7kVU6iXHZtoqmw9H1oVfSckubp8x8fUuX8IH21HRD+ATvIRWuX54uCKSO/lJl+hRcEo9sJnaCtf/q+87RP5C21/vrbS1ENgh/yFVq32qIrADvkNzYydF4if+2gIjdAgQWiQIDRIEBokCA0ShAYJQoMEoUGC0CBBaJD4A9ziV+Oo5Zr2AAAAAElFTkSuQmCC\" /></p>\r\n\r\n<p>&nbsp;</p>\r\n',NULL,NULL,0),(8,'b title','b subtitle','<pre>\r\nb body b c d \r\n\r\npublic class csw {\r\n   abc\r\n}\r\n\r\n\r\n</pre>\r\n\r\n<pre>\r\n<code>hello mofo</code></pre>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&lt;code&gt;test&lt;/code&gt;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAJoAAABeCAYAAADbhg21AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAoRJREFUeJzt3N1uqzAQReGh6vu/sntRWUKOQwjYm/HM+m6OTqWmpCxsfpxupZRiwGQ/T28AciA0SBAaJAgNEoQGCUKDBKFBgtAgQWiQIDRIEBokCA0ShAYJQoMEoUGC0CBBaJAgNEgQGiQIDRKEBglCgwShQYLQIEFokPid+upb838+E5/WnND2gZXO15DO2NB6gQE28hytRlasH1kxRrXExoxomzGC4RBXnZAgNEgQGiQIDRKEBglCgwShQYLQIEFokCA0SBAaJAgNEoQGifurN7bm35mOfgarR1y7H1pdZ6ba0b2fwzo39+Z+ZmCU/aLKUa/Val/7U7zfbsvV1cdB1vr5D21EZJ8+JHM2vt73nNmuILHcscbFwIidVGzsMvPk4XxrjdCwPEKDBKHdcWbK5YrYzAhNg/M5QnMt0GhIaJAgNEiMuWHLOcixq1NgoD8j4f/JwOo4CM0se2hBRosVxAytF1Dva+9GG+XSpyTWDu3dg+3eQ/NvpjD10qcE5vwhvtE76MrqitUFG03HhlZHgpHBRY7pk0Dvffx9tP1SnGBHJa6bd8M20NEoVWeEYL+/uRcD+xuOwX5xUwQMrJr/CKpOpUyjqemedRJbamvfR4siwemFdvUGo9qrel4WODKzJ5YJtffakMIz69G4QPiX6P0/e47m+ZnizG1KcE7W4mJAzeuBNdnzS7kVU6iXHZtoqmw9H1oVfSckubp8x8fUuX8IH21HRD+ATvIRWuX54uCKSO/lJl+hRcEo9sJnaCtf/q+87RP5C21/vrbS1ENgh/yFVq32qIrADvkNzYydF4if+2gIjdAgQWiQIDRIEBokCA0ShAYJQoMEoUGC0CBBaJD4A9ziV+Oo5Zr2AAAAAElFTkSuQmCC\" /></p>\r\n\r\n<p>&nbsp;</p>\r\n',NULL,NULL,0),(9,'b title','b subtitle','<pre>\r\nb body b c d \r\n\r\npublic class csw {\r\n   abc\r\n}\r\n\r\n\r\n</pre>\r\n\r\n<pre>\r\n<code>hello mofo</code></pre>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&lt;code&gt;test&lt;/code&gt;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAJoAAABeCAYAAADbhg21AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAoRJREFUeJzt3N1uqzAQReGh6vu/sntRWUKOQwjYm/HM+m6OTqWmpCxsfpxupZRiwGQ/T28AciA0SBAaJAgNEoQGCUKDBKFBgtAgQWiQIDRIEBokCA0ShAYJQoMEoUGC0CBBaJAgNEgQGiQIDRKEBglCgwShQYLQIEFokPid+upb838+E5/WnND2gZXO15DO2NB6gQE28hytRlasH1kxRrXExoxomzGC4RBXnZAgNEgQGiQIDRKEBglCgwShQYLQIEFokCA0SBAaJAgNEoQGifurN7bm35mOfgarR1y7H1pdZ6ba0b2fwzo39+Z+ZmCU/aLKUa/Val/7U7zfbsvV1cdB1vr5D21EZJ8+JHM2vt73nNmuILHcscbFwIidVGzsMvPk4XxrjdCwPEKDBKHdcWbK5YrYzAhNg/M5QnMt0GhIaJAgNEiMuWHLOcixq1NgoD8j4f/JwOo4CM0se2hBRosVxAytF1Dva+9GG+XSpyTWDu3dg+3eQ/NvpjD10qcE5vwhvtE76MrqitUFG03HhlZHgpHBRY7pk0Dvffx9tP1SnGBHJa6bd8M20NEoVWeEYL+/uRcD+xuOwX5xUwQMrJr/CKpOpUyjqemedRJbamvfR4siwemFdvUGo9qrel4WODKzJ5YJtffakMIz69G4QPiX6P0/e47m+ZnizG1KcE7W4mJAzeuBNdnzS7kVU6iXHZtoqmw9H1oVfSckubp8x8fUuX8IH21HRD+ATvIRWuX54uCKSO/lJl+hRcEo9sJnaCtf/q+87RP5C21/vrbS1ENgh/yFVq32qIrADvkNzYydF4if+2gIjdAgQWiQIDRIEBokCA0ShAYJQoMEoUGC0CBBaJD4A9ziV+Oo5Zr2AAAAAElFTkSuQmCC\" /></p>\r\n\r\n<p>&nbsp;</p>\r\n',NULL,NULL,0),(10,'b title X','b subtitle Y','<pre>\r\nb body b c d \r\n\r\npublic class csw {\r\n   abc\r\n}\r\n\r\n\r\n</pre>\r\n\r\n<pre>\r\n<code>hello mofo</code></pre>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&lt;code&gt;test&lt;/code&gt;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAJoAAABeCAYAAADbhg21AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAoRJREFUeJzt3N1uqzAQReGh6vu/sntRWUKOQwjYm/HM+m6OTqWmpCxsfpxupZRiwGQ/T28AciA0SBAaJAgNEoQGCUKDBKFBgtAgQWiQIDRIEBokCA0ShAYJQoMEoUGC0CBBaJAgNEgQGiQIDRKEBglCgwShQYLQIEFokPid+upb838+E5/WnND2gZXO15DO2NB6gQE28hytRlasH1kxRrXExoxomzGC4RBXnZAgNEgQGiQIDRKEBglCgwShQYLQIEFokCA0SBAaJAgNEoQGifurN7bm35mOfgarR1y7H1pdZ6ba0b2fwzo39+Z+ZmCU/aLKUa/Val/7U7zfbsvV1cdB1vr5D21EZJ8+JHM2vt73nNmuILHcscbFwIidVGzsMvPk4XxrjdCwPEKDBKHdcWbK5YrYzAhNg/M5QnMt0GhIaJAgNEiMuWHLOcixq1NgoD8j4f/JwOo4CM0se2hBRosVxAytF1Dva+9GG+XSpyTWDu3dg+3eQ/NvpjD10qcE5vwhvtE76MrqitUFG03HhlZHgpHBRY7pk0Dvffx9tP1SnGBHJa6bd8M20NEoVWeEYL+/uRcD+xuOwX5xUwQMrJr/CKpOpUyjqemedRJbamvfR4siwemFdvUGo9qrel4WODKzJ5YJtffakMIz69G4QPiX6P0/e47m+ZnizG1KcE7W4mJAzeuBNdnzS7kVU6iXHZtoqmw9H1oVfSckubp8x8fUuX8IH21HRD+ATvIRWuX54uCKSO/lJl+hRcEo9sJnaCtf/q+87RP5C21/vrbS1ENgh/yFVq32qIrADvkNzYydF4if+2gIjdAgQWiQIDRIEBokCA0ShAYJQoMEoUGC0CBBaJD4A9ziV+Oo5Zr2AAAAAElFTkSuQmCC\" /></p>\r\n\r\n<p>&nbsp;</p>\r\n',NULL,NULL,0),(11,'b title X','b subtitle Y','<pre>\r\nb body b c d \r\n\r\npublic class csw {\r\n   abc\r\n}\r\n\r\n\r\n</pre>\r\n\r\n<pre>\r\n<code>hello mofo</code></pre>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&lt;code&gt;test&lt;/code&gt;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAJoAAABeCAYAAADbhg21AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAoRJREFUeJzt3N1uqzAQReGh6vu/sntRWUKOQwjYm/HM+m6OTqWmpCxsfpxupZRiwGQ/T28AciA0SBAaJAgNEoQGCUKDBKFBgtAgQWiQIDRIEBokCA0ShAYJQoMEoUGC0CBBaJAgNEgQGiQIDRKEBglCgwShQYLQIEFokPid+upb838+E5/WnND2gZXO15DO2NB6gQE28hytRlasH1kxRrXExoxomzGC4RBXnZAgNEgQGiQIDRKEBglCgwShQYLQIEFokCA0SBAaJAgNEoQGifurN7bm35mOfgarR1y7H1pdZ6ba0b2fwzo39+Z+ZmCU/aLKUa/Val/7U7zfbsvV1cdB1vr5D21EZJ8+JHM2vt73nNmuILHcscbFwIidVGzsMvPk4XxrjdCwPEKDBKHdcWbK5YrYzAhNg/M5QnMt0GhIaJAgNEiMuWHLOcixq1NgoD8j4f/JwOo4CM0se2hBRosVxAytF1Dva+9GG+XSpyTWDu3dg+3eQ/NvpjD10qcE5vwhvtE76MrqitUFG03HhlZHgpHBRY7pk0Dvffx9tP1SnGBHJa6bd8M20NEoVWeEYL+/uRcD+xuOwX5xUwQMrJr/CKpOpUyjqemedRJbamvfR4siwemFdvUGo9qrel4WODKzJ5YJtffakMIz69G4QPiX6P0/e47m+ZnizG1KcE7W4mJAzeuBNdnzS7kVU6iXHZtoqmw9H1oVfSckubp8x8fUuX8IH21HRD+ATvIRWuX54uCKSO/lJl+hRcEo9sJnaCtf/q+87RP5C21/vrbS1ENgh/yFVq32qIrADvkNzYydF4if+2gIjdAgQWiQIDRIEBokCA0ShAYJQoMEoUGC0CBBaJD4A9ziV+Oo5Zr2AAAAAElFTkSuQmCC\" /></p>\r\n\r\n<p>&nbsp;</p>\r\n',NULL,NULL,0),(12,'A TITLE (not a real tit)','LE!','<p>TEST</p>\r\n\r\n<p>tests</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">public void class {}</code></pre>\r\n\r\n<p>This code will do stuff!</p>\r\n\r\n<p><img alt=\"alt text\" src=\"http://localhost:8080/ChristianWeaves.com/getImage?file=index.png\" style=\"float:right; height:160px; width:465px\" /></p>\r\n',NULL,1,0),(13,'A TITLE (not a real tit)','TITLE! NEW','<p>TEST</p>\r\n\r\n<p>tests</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">public void class {}</code></pre>\r\n\r\n<p>This code will do stuff!</p>\r\n\r\n<p><img alt=\"alt text\" src=\"http://localhost:8080/ChristianWeaves.com/getImage?file=index.png\" style=\"float:right; height:160px; width:465px\" /></p>\r\n',NULL,1,0),(14,'c title','c subtitle','A c body ',NULL,NULL,0),(15,'c title','c subtitle','B c body ',NULL,NULL,0),(16,'c title','c subtitle','B c body ',NULL,NULL,0),(17,'b title X','b subtitle Y','<pre>\r\nb body b c d \r\n\r\npublic class csw {\r\n   abc\r\n}\r\n\r\n\r\n</pre>\r\n\r\n<pre>\r\n<code>hello mofo</code></pre>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&lt;code&gt;test&lt;/code&gt;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAJoAAABeCAYAAADbhg21AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAoRJREFUeJzt3N1uqzAQReGh6vu/sntRWUKOQwjYm/HM+m6OTqWmpCxsfpxupZRiwGQ/T28AciA0SBAaJAgNEoQGCUKDBKFBgtAgQWiQIDRIEBokCA0ShAYJQoMEoUGC0CBBaJAgNEgQGiQIDRKEBglCgwShQYLQIEFokPid+upb838+E5/WnND2gZXO15DO2NB6gQE28hytRlasH1kxRrXExoxomzGC4RBXnZAgNEgQGiQIDRKEBglCgwShQYLQIEFokCA0SBAaJAgNEoQGifurN7bm35mOfgarR1y7H1pdZ6ba0b2fwzo39+Z+ZmCU/aLKUa/Val/7U7zfbsvV1cdB1vr5D21EZJ8+JHM2vt73nNmuILHcscbFwIidVGzsMvPk4XxrjdCwPEKDBKHdcWbK5YrYzAhNg/M5QnMt0GhIaJAgNEiMuWHLOcixq1NgoD8j4f/JwOo4CM0se2hBRosVxAytF1Dva+9GG+XSpyTWDu3dg+3eQ/NvpjD10qcE5vwhvtE76MrqitUFG03HhlZHgpHBRY7pk0Dvffx9tP1SnGBHJa6bd8M20NEoVWeEYL+/uRcD+xuOwX5xUwQMrJr/CKpOpUyjqemedRJbamvfR4siwemFdvUGo9qrel4WODKzJ5YJtffakMIz69G4QPiX6P0/e47m+ZnizG1KcE7W4mJAzeuBNdnzS7kVU6iXHZtoqmw9H1oVfSckubp8x8fUuX8IH21HRD+ATvIRWuX54uCKSO/lJl+hRcEo9sJnaCtf/q+87RP5C21/vrbS1ENgh/yFVq32qIrADvkNzYydF4if+2gIjdAgQWiQIDRIEBokCA0ShAYJQoMEoUGC0CBBaJD4A9ziV+Oo5Zr2AAAAAElFTkSuQmCC\" /></p>\r\n\r\n<p>&nbsp;</p>\r\n',NULL,NULL,0),(18,'b title X','b subtitle Y','<pre>\r\nb body b c d \r\n\r\npublic class csw {\r\n   abc\r\n}\r\n\r\n\r\n</pre>\r\n\r\n<pre>\r\n<code>hello mofo</code></pre>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&lt;code&gt;test&lt;/code&gt;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAJoAAABeCAYAAADbhg21AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAoRJREFUeJzt3N1uqzAQReGh6vu/sntRWUKOQwjYm/HM+m6OTqWmpCxsfpxupZRiwGQ/T28AciA0SBAaJAgNEoQGCUKDBKFBgtAgQWiQIDRIEBokCA0ShAYJQoMEoUGC0CBBaJAgNEgQGiQIDRKEBglCgwShQYLQIEFokPid+upb838+E5/WnND2gZXO15DO2NB6gQE28hytRlasH1kxRrXExoxomzGC4RBXnZAgNEgQGiQIDRKEBglCgwShQYLQIEFokCA0SBAaJAgNEoQGifurN7bm35mOfgarR1y7H1pdZ6ba0b2fwzo39+Z+ZmCU/aLKUa/Val/7U7zfbsvV1cdB1vr5D21EZJ8+JHM2vt73nNmuILHcscbFwIidVGzsMvPk4XxrjdCwPEKDBKHdcWbK5YrYzAhNg/M5QnMt0GhIaJAgNEiMuWHLOcixq1NgoD8j4f/JwOo4CM0se2hBRosVxAytF1Dva+9GG+XSpyTWDu3dg+3eQ/NvpjD10qcE5vwhvtE76MrqitUFG03HhlZHgpHBRY7pk0Dvffx9tP1SnGBHJa6bd8M20NEoVWeEYL+/uRcD+xuOwX5xUwQMrJr/CKpOpUyjqemedRJbamvfR4siwemFdvUGo9qrel4WODKzJ5YJtffakMIz69G4QPiX6P0/e47m+ZnizG1KcE7W4mJAzeuBNdnzS7kVU6iXHZtoqmw9H1oVfSckubp8x8fUuX8IH21HRD+ATvIRWuX54uCKSO/lJl+hRcEo9sJnaCtf/q+87RP5C21/vrbS1ENgh/yFVq32qIrADvkNzYydF4if+2gIjdAgQWiQIDRIEBokCA0ShAYJQoMEoUGC0CBBaJD4A9ziV+Oo5Zr2AAAAAElFTkSuQmCC\" /></p>\r\n\r\n<p>&nbsp;</p>\r\n',NULL,NULL,0),(19,'c title','c subtitle','B c body ',NULL,NULL,0),(20,'A TITLE (not a real tit)','TITLE! NEW','<p>123 TEST</p>\r\n\r\n<p>tests</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">public void class {}</code></pre>\r\n\r\n<p>This code will do stuff!</p>\r\n\r\n<p>&nbsp;</p>\r\n',NULL,1,0),(21,'A TITLE (not a real tit)','TITLE! NEW','<p>ABC 123 TEST</p>\r\n\r\n<p>tests</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">public void class {}</code></pre>\r\n\r\n<p>This code will do stuff!</p>\r\n\r\n<p>&nbsp;</p>\r\n',NULL,1,0),(22,'AA TITLE (not a real tit)','TITLE! NEW','<p>ABC 123 TEST</p>\r\n\r\n<p>tests</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">public void class {}</code></pre>\r\n\r\n<p>This code will do stuff!</p>\r\n\r\n<p>&nbsp;</p>\r\n',NULL,1,0),(23,'AA TITLE (not a real tit)','TITLE! NEW','<p>ABC 123 TEST</p>\r\n\r\n<p>tests 9999</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">public void class {}</code></pre>\r\n\r\n<p>This code will do stuff!</p>\r\n\r\n<p>&nbsp;</p>\r\n',NULL,1,0),(24,'AA TITLE (not a real tit)','TITLE! NEW','<p>ABC 123 TEST</p>\r\n\r\n<p>tests 9999</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">public void class {}</code></pre>\r\n\r\n<p>This code will do stuff!</p>\r\n\r\n<p><img alt=\"alt text\" src=\"http://localhost:8080/ChristianWeaves.com/getResource?file=LouisWeaves.jpg\" /></p>\r\n',NULL,1,0),(25,'AA TITLE (not a real tit)','TITLE! NEW','<p>ABC 123 TEST</p>\r\n\r\n<p>tests 9999</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">public void class {}</code></pre>\r\n\r\n<p>This code will do stuff!</p>\r\n\r\n<p><img alt=\"alt text\" src=\"http://localhost:8080/ChristianWeaves.com/getResource?file=LouisWeaves.jpg\" style=\"height:160px; width:120px\" /></p>\r\n',NULL,1,0),(26,'AA TITLE (not a real title)','TITLE! NEW','<p>ABC 123 TEST</p>\r\n\r\n<p>tests 9999</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">public void class {}</code></pre>\r\n\r\n<p>This code will do stuff!</p>\r\n\r\n<p><img alt=\"alt text\" src=\"http://localhost:8080/ChristianWeaves.com/getResource?file=LouisWeaves.jpg\" style=\"height:160px; width:120px\" /></p>\r\n',NULL,1,0),(27,'AA TITLE (not a real title)','TITLE! NEW x','<p>ABC 123 TEST xxx</p>\r\n\r\n<p>tests 9999</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">public void class {}</code></pre>\r\n\r\n<p>This code will do stuff!</p>\r\n\r\n<p><img alt=\"alt text\" src=\"http://localhost:8080/ChristianWeaves.com/getResource?file=LouisWeaves.jpg\" style=\"height:160px; width:120px\" /></p>\r\n',NULL,1,0),(28,'AAB TITLE (not a real title)','TITLE! NEW x','<p>ABC 123 TEST xxx</p>\r\n\r\n<p>tests 9999</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">public void class {}</code></pre>\r\n\r\n<p>This code will do stuff!</p>\r\n\r\n<p><img alt=\"alt text\" src=\"http://localhost:8080/ChristianWeaves.com/getResource?file=LouisWeaves.jpg\" style=\"height:160px; width:120px\" /></p>\r\n',NULL,1,0),(29,'AAB TITLE (not a real title)','TITLE! NEW xz','<p>ABC 123 TEST xxx</p>\r\n\r\n<p>tests 9999</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">public void class {}</code></pre>\r\n\r\n<p>This code will do stuff!</p>\r\n\r\n<p><img alt=\"alt text\" src=\"http://localhost:8080/ChristianWeaves.com/getResource?file=LouisWeaves.jpg\" style=\"height:160px; width:120px\" /></p>\r\n',NULL,1,0),(30,'ABCAAB TITLE (not a real title)','TITLE! NEW xz','<p>ABC 123 TEST xxx</p>\r\n\r\n<p>tests 9999</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">public void class {}</code></pre>\r\n\r\n<p>This code will do stuff!</p>\r\n\r\n<p><img alt=\"alt text\" src=\"http://localhost:8080/ChristianWeaves.com/getResource?file=LouisWeaves.jpg\" style=\"height:160px; width:120px\" /></p>\r\n',NULL,1,0),(31,'123ABCAAB TITLE (not a real title)','TITLE! NEW xz','<p>ABC 123 TEST xxx</p>\r\n\r\n<p>tests 9999</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">public void class {}</code></pre>\r\n\r\n<p>This code will do stuff!</p>\r\n\r\n<p><img alt=\"alt text\" src=\"http://localhost:8080/ChristianWeaves.com/getResource?file=LouisWeaves.jpg\" style=\"height:160px; width:120px\" /></p>\r\n',NULL,1,0),(32,'x123ABCAAB TITLE (not a real title)','TITLE! NEW xz','<p>ABC 123 TEST xxx</p>\r\n\r\n<p>tests 9999</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">public void class {}</code></pre>\r\n\r\n<p>This code will do stuff!</p>\r\n\r\n<p><img alt=\"alt text\" src=\"http://localhost:8080/ChristianWeaves.com/getResource?file=LouisWeaves.jpg\" style=\"height:160px; width:120px\" /></p>\r\n',NULL,1,0),(33,'A TITLE (not a real title)','TITLE! NEW xz','<p>ABC 123 TEST xxx</p>\r\n\r\n<p>tests 9999</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">public void class {}</code></pre>\r\n\r\n<p>This code will do stuff!</p>\r\n\r\n<p><img alt=\"alt text\" src=\"http://localhost:8080/ChristianWeaves.com/getResource?file=LouisWeaves.jpg\" style=\"height:160px; width:120px\" /></p>\r\n',NULL,1,0),(34,'A TITLE (not a real title)....','TITLE! NEW xz','<p>ABC 123 TEST xxx</p>\r\n\r\n<p>tests 9999</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">public void class {}</code></pre>\r\n\r\n<p>This code will do stuff!</p>\r\n\r\n<p><img alt=\"alt text\" src=\"http://localhost:8080/ChristianWeaves.com/getResource?file=LouisWeaves.jpg\" style=\"height:160px; width:120px\" /></p>\r\n',NULL,1,0),(35,'A TITLE (not a real title)....','TITLE! NEW xz','<p>ABC 123 TEST xxx</p>\r\n\r\n<p>tests 9999</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">public void class {}</code></pre>\r\n\r\n<p>This code will do stuff!</p>\r\n\r\n<p><img alt=\"alt text\" src=\"http://localhost:8080/ChristianWeaves.com/getResource?file=LouisWeaves.jpg\" style=\"height:533px; width:401px\" /></p>\r\n',NULL,1,0),(36,'A TITLE (not a real title)....','TITLE! NEW xz','<p>ABC 123 TEST xxx</p>\r\n\r\n<p>tests 9999</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">public void class {}</code></pre>\r\n\r\n<p>This code will do stuff!</p>\r\n\r\n<p><img alt=\"alt text\" src=\"http://localhost:8080/ChristianWeaves.com/getResource?file=LouisWeaves.jpg\" style=\"height:533px; width:401px\" /></p>\r\n',NULL,1,0),(37,'A TITLE (not a real title)....','TITLE! NEW xz a very lart sub title for this that and the other','<p>ABC 123 TEST xxx</p>\r\n\r\n<p>tests 9999</p>\r\n\r\n<pre>\r\n<code class=\"language-java\">public void class {}</code></pre>\r\n\r\n<p>This code will do stuff!</p>\r\n\r\n<p><img alt=\"alt text\" src=\"http://localhost:8080/ChristianWeaves.com/getResource?file=LouisWeaves.jpg\" style=\"height:533px; width:401px\" /></p>\r\n',NULL,1,0),(38,'A TITLE (not a real title)....','TITLE! NEW xz a very lart sub title for this that and the other','<pre>\r\nb body b c d \r\n\r\npublic class csw {\r\n   abc\r\n}\r\n\r\n\r\n</pre>\r\n\r\n<pre>\r\n<code>hello mofo</code></pre>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&lt;code&gt;test&lt;/code&gt;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAJoAAABeCAYAAADbhg21AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAoRJREFUeJzt3N1uqzAQReGh6vu/sntRWUKOQwjYm/HM+m6OTqWmpCxsfpxupZRiwGQ/T28AciA0SBAaJAgNEoQGCUKDBKFBgtAgQWiQIDRIEBokCA0ShAYJQoMEoUGC0CBBaJAgNEgQGiQIDRKEBglCgwShQYLQIEFokPid+upb838+E5/WnND2gZXO15DO2NB6gQE28hytRlasH1kxRrXExoxomzGC4RBXnZAgNEgQGiQIDRKEBglCgwShQYLQIEFokCA0SBAaJAgNEoQGifurN7bm35mOfgarR1y7H1pdZ6ba0b2fwzo39+Z+ZmCU/aLKUa/Val/7U7zfbsvV1cdB1vr5D21EZJ8+JHM2vt73nNmuILHcscbFwIidVGzsMvPk4XxrjdCwPEKDBKHdcWbK5YrYzAhNg/M5QnMt0GhIaJAgNEiMuWHLOcixq1NgoD8j4f/JwOo4CM0se2hBRosVxAytF1Dva+9GG+XSpyTWDu3dg+3eQ/NvpjD10qcE5vwhvtE76MrqitUFG03HhlZHgpHBRY7pk0Dvffx9tP1SnGBHJa6bd8M20NEoVWeEYL+/uRcD+xuOwX5xUwQMrJr/CKpOpUyjqemedRJbamvfR4siwemFdvUGo9qrel4WODKzJ5YJtffakMIz69G4QPiX6P0/e47m+ZnizG1KcE7W4mJAzeuBNdnzS7kVU6iXHZtoqmw9H1oVfSckubp8x8fUuX8IH21HRD+ATvIRWuX54uCKSO/lJl+hRcEo9sJnaCtf/q+87RP5C21/vrbS1ENgh/yFVq32qIrADvkNzYydF4if+2gIjdAgQWiQIDRIEBokCA0ShAYJQoMEoUGC0CBBaJD4A9ziV+Oo5Zr2AAAAAElFTkSuQmCC\" /></p>\r\n\r\n<p>&nbsp;</p>\r\n',NULL,NULL,0),(39,'Useful links','A place to store useful links found on my digital journey','<p>Nice effect for article backgrounds</p>\r\n\r\n<p><a href=\"https://codepen.io/anon/pen/xNYPOa\" style=\"text-decoration:none;\"><u>https://codepen.io/anon/pen/xNYPOa</u></a></p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>Some good css tutorials</p>\r\n\r\n<p><a href=\"https://css-tricks.com/\" style=\"text-decoration:none;\"><u>https://css-tricks.com/</u></a></p>\r\n','2019-06-05 22:34:27',NULL,0),(40,'Useful links','A place to store useful links found on my digital journey','<p>Nice effect for article backgrounds</p>\r\n\r\n\r\n<p><a href=\"https://codepen.io/anon/pen/xNYPOa\" style=\"text-decoration:none;\"><u>https://codepen.io/anon/pen/xNYPOa</u></a></p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>Some good css tutorials</p>\r\n\r\n<p><a href=\"https://css-tricks.com/\" style=\"text-decoration:none;\"><u>https://css-tricks.com/</u></a></p>\r\n','2019-06-05 22:34:27',NULL,0),(41,'Useful links','A place to store useful links found on my digital journey','<p>&nbsp;</p>\r\n\r\n<p>Nice effect for article backgrounds<a href=\"https://codepen.io/anon/pen/xNYPOa\" style=\"text-decoration:none;\"><u> https://codepen.io/anon/pen/xNYPOa</u></a></p>\r\n\r\n<p>Some good css tutorials<a href=\"https://css-tricks.com/\" style=\"text-decoration:none;\"><u> https://css-tricks.com/</u></a></p>\r\n','2019-06-05 22:34:27',NULL,0);
/*!40000 ALTER TABLE `tb_articles_archive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_users`
--

DROP TABLE IF EXISTS `tb_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` tinytext NOT NULL,
  `role` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `usrname` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_users`
--

LOCK TABLES `tb_users` WRITE;
/*!40000 ALTER TABLE `tb_users` DISABLE KEYS */;
INSERT INTO `tb_users` VALUES (1,'csw','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','ADMINISTRATOR');
/*!40000 ALTER TABLE `tb_users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-13 22:59:00
