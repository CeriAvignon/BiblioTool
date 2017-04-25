-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 25 Avril 2017 à 11:01
-- Version du serveur :  5.6.16
-- Version de PHP :  5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `bd-biblio`
--

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

CREATE TABLE IF NOT EXISTS `article` (
  `idArt` int(11) NOT NULL,
  `titleArt` varchar(30) DEFAULT NULL,
  `urlPage` varchar(25) DEFAULT NULL,
  `urlPdf` varchar(30) DEFAULT NULL,
  `doi` varchar(25) DEFAULT NULL,
  `numVol` int(11) DEFAULT NULL,
  `numIssue` int(11) DEFAULT NULL,
  `numPage` varchar(25) DEFAULT NULL,
  `nbPage` int(11) DEFAULT NULL,
  `yearPub` date DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `idJour` int(11) DEFAULT NULL,
  PRIMARY KEY (`idArt`),
  KEY `FK_Article_idJour` (`idJour`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `author`
--

CREATE TABLE IF NOT EXISTS `author` (
  `idAut` int(11) NOT NULL,
  `nameAut` varchar(30) DEFAULT NULL,
  `surnameAut` varchar(30) DEFAULT NULL,
  `affiliation` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idAut`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `journal`
--

CREATE TABLE IF NOT EXISTS `journal` (
  `idJour` int(11) NOT NULL,
  `nameJour` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idJour`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reference`
--

CREATE TABLE IF NOT EXISTS `reference` (
  `idArt` int(11) NOT NULL,
  `idArt_Article` int(11) NOT NULL,
  PRIMARY KEY (`idArt`,`idArt_Article`),
  KEY `FK_Reference_idArt_Article` (`idArt_Article`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `writes`
--

CREATE TABLE IF NOT EXISTS `writes` (
  `idArt` int(11) NOT NULL,
  `idAut` int(11) NOT NULL,
  PRIMARY KEY (`idArt`,`idAut`),
  KEY `FK_writes_idAut` (`idAut`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `article`
--
ALTER TABLE `article`
  ADD CONSTRAINT `FK_Article_idJour` FOREIGN KEY (`idJour`) REFERENCES `journal` (`idJour`);

--
-- Contraintes pour la table `reference`
--
ALTER TABLE `reference`
  ADD CONSTRAINT `FK_Reference_idArt` FOREIGN KEY (`idArt`) REFERENCES `article` (`idArt`),
  ADD CONSTRAINT `FK_Reference_idArt_Article` FOREIGN KEY (`idArt_Article`) REFERENCES `article` (`idArt`);

--
-- Contraintes pour la table `writes`
--
ALTER TABLE `writes`
  ADD CONSTRAINT `FK_writes_idArt` FOREIGN KEY (`idArt`) REFERENCES `article` (`idArt`),
  ADD CONSTRAINT `FK_writes_idAut` FOREIGN KEY (`idAut`) REFERENCES `author` (`idAut`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
