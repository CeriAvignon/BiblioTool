-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Dim 08 Janvier 2017 à 16:14
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
-- Structure de la table `articles`
--

CREATE TABLE IF NOT EXISTS `articles` (
  `id_art` int(11) NOT NULL,
  `titre_art` varchar(30) DEFAULT NULL,
  `url_art` varchar(30) DEFAULT NULL,
  `doi` varchar(25) DEFAULT NULL,
  `num_vol` int(11) DEFAULT NULL,
  `num_issue` int(11) DEFAULT NULL,
  `num_pagedeb` int(11) DEFAULT NULL,
  `num_pagefin` int(11) DEFAULT NULL,
  `anne_pub` int(11) DEFAULT NULL,
  `id_jour` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_art`),
  KEY `FK_Articles_id_jour` (`id_jour`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `auteurs`
--

CREATE TABLE IF NOT EXISTS `auteurs` (
  `id_aut` int(11) NOT NULL,
  `nom_aut` varchar(30) DEFAULT NULL,
  `pren_aut` varchar(30) DEFAULT NULL,
  `affiliation` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_aut`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `citations`
--

CREATE TABLE IF NOT EXISTS `citations` (
  `id_cita` int(11) NOT NULL,
  `citation` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_cita`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `citer`
--

CREATE TABLE IF NOT EXISTS `citer` (
  `id_art` int(11) NOT NULL,
  `id_cita` int(11) NOT NULL,
  PRIMARY KEY (`id_art`,`id_cita`),
  KEY `FK_citer_id_cita` (`id_cita`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `ecrire`
--

CREATE TABLE IF NOT EXISTS `ecrire` (
  `id_art` int(11) NOT NULL,
  `id_aut` int(11) NOT NULL,
  PRIMARY KEY (`id_art`,`id_aut`),
  KEY `FK_ecrire_id_aut` (`id_aut`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `journal`
--

CREATE TABLE IF NOT EXISTS `journal` (
  `id_jour` int(11) NOT NULL,
  `titre_jour` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_jour`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `articles`
--
ALTER TABLE `articles`
  ADD CONSTRAINT `FK_Articles_id_jour` FOREIGN KEY (`id_jour`) REFERENCES `journal` (`id_jour`);

--
-- Contraintes pour la table `citer`
--
ALTER TABLE `citer`
  ADD CONSTRAINT `FK_citer_id_cita` FOREIGN KEY (`id_cita`) REFERENCES `citations` (`id_cita`),
  ADD CONSTRAINT `FK_citer_id_art` FOREIGN KEY (`id_art`) REFERENCES `articles` (`id_art`);

--
-- Contraintes pour la table `ecrire`
--
ALTER TABLE `ecrire`
  ADD CONSTRAINT `FK_ecrire_id_aut` FOREIGN KEY (`id_aut`) REFERENCES `auteurs` (`id_aut`),
  ADD CONSTRAINT `FK_ecrire_id_art` FOREIGN KEY (`id_art`) REFERENCES `articles` (`id_art`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
