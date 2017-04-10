-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 08, 2017 at 07:13 AM
-- Server version: 5.5.54-cll
-- PHP Version: 5.5.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `maytwelv_nottspark`
--

-- --------------------------------------------------------

--
-- Table structure for table `TABLE_LEAVER`
--

CREATE TABLE `TABLE_LEAVER` (
  `KEY_L_ID` int(11) NOT NULL,
  `KEY_L_USER_ID` int(11) NOT NULL,
  `KEY_L_LOCATION` varchar(99) COLLATE utf8_unicode_ci DEFAULT NULL,
  `KEY_L_DESC` varchar(99) COLLATE utf8_unicode_ci DEFAULT NULL,
  `KEY_L_PARINGSTATUS` int(11) DEFAULT NULL,
  `KEY_L_DATE` varchar(99) COLLATE utf8_unicode_ci DEFAULT NULL,
  `KEY_L_TIME` varchar(99) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `TABLE_LEAVER`
--

INSERT INTO `TABLE_LEAVER` (`KEY_L_ID`, `KEY_L_USER_ID`, `KEY_L_LOCATION`, `KEY_L_DESC`, `KEY_L_PARINGSTATUS`, `KEY_L_DATE`, `KEY_L_TIME`) VALUES
(1, 1, 'ZONE B - Near Blue Building', 'yjmui', 1, '07/04/2017', '05:04 PM'),
(2, 2, 'ZONE B - Near Blue Building', 'ZONE B - Near Blue Building', 1, '07/04/2017', '05:04 PM'),
(3, 3, 'ZONE S - Near Sport Complex', 'near tennis court', 0, '08/04/2017', '10:57 am'),
(4, 1, 'ZONE K - Behind Kapas Hall', 'right behind', 1, '08/04/2017', '10:57 am'),
(5, 2, 'ZONE R1 - Next to Redang Hall', 'in the middle', 0, '08/04/2017', '03:58 PM'),
(6, 3, 'ZONE T - Between Tioman and Langkawi Hall', 'almost leaving', 0, '08/04/2017', '09:58 AM');

-- --------------------------------------------------------

--
-- Table structure for table `TABLE_USER`
--

CREATE TABLE `TABLE_USER` (
  `KEY_USER_ID` int(11) NOT NULL,
  `KEY_USER_USERNAME` varchar(99) COLLATE utf8_unicode_ci DEFAULT NULL,
  `KEY_USER_NAME` varchar(99) COLLATE utf8_unicode_ci DEFAULT NULL,
  `KEY_USER_CONTACTNUM` varchar(99) COLLATE utf8_unicode_ci DEFAULT NULL,
  `KEY_USER_EMAIL` varchar(99) COLLATE utf8_unicode_ci DEFAULT NULL,
  `KEY_CAR_MAKE` varchar(99) COLLATE utf8_unicode_ci DEFAULT NULL,
  `KEY_CAR_MODEL` varchar(99) COLLATE utf8_unicode_ci DEFAULT NULL,
  `KEY_CAR_PLATE` varchar(99) COLLATE utf8_unicode_ci DEFAULT NULL,
  `KEY_REGISTERDATE` varchar(99) COLLATE utf8_unicode_ci DEFAULT NULL,
  `KEY_USER_ACCOUNTTYPE` varchar(99) COLLATE utf8_unicode_ci DEFAULT NULL,
  `KEY_USER_PASSWORD` varchar(99) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `TABLE_USER`
--

INSERT INTO `TABLE_USER` (`KEY_USER_ID`, `KEY_USER_USERNAME`, `KEY_USER_NAME`, `KEY_USER_CONTACTNUM`, `KEY_USER_EMAIL`, `KEY_CAR_MAKE`, `KEY_CAR_MODEL`, `KEY_CAR_PLATE`, `KEY_REGISTERDATE`, `KEY_USER_ACCOUNTTYPE`, `KEY_USER_PASSWORD`) VALUES
(1, 'wong123', 'Wong Lim Wong', '0152368956', 'wong@gmail.com', 'Honda', 'Civic', 'AAA 1234', '11-02-2017', 'STUDENT', 'wong123'),
(2, 'vivian', 'Yeoh Hui Wen', '0196587498', 'vivian@gmail.com', 'Honda', 'Civic', 'AAA 1234', '11-02-2017', 'STUDENT', 'wong123'),
(3, 'xingtong', 'Loo Xingtong', '0138967458', 'loo@gmail.com', 'Honda', 'Civic', 'AAA 1234', '11-02-2017', 'STUDENT', 'wong123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `TABLE_LEAVER`
--
ALTER TABLE `TABLE_LEAVER`
  ADD PRIMARY KEY (`KEY_L_ID`);

--
-- Indexes for table `TABLE_USER`
--
ALTER TABLE `TABLE_USER`
  ADD PRIMARY KEY (`KEY_USER_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `TABLE_LEAVER`
--
ALTER TABLE `TABLE_LEAVER`
  MODIFY `KEY_L_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `TABLE_USER`
--
ALTER TABLE `TABLE_USER`
  MODIFY `KEY_USER_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
