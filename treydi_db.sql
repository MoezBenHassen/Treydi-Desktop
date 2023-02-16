-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 16, 2023 at 01:40 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `treydi_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `administrateur`
--

CREATE TABLE `administrateur` (
  `id_admin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `administrateur`
--

INSERT INTO `administrateur` (`id_admin`) VALUES
(7);

-- --------------------------------------------------------

--
-- Table structure for table `categorie`
--

CREATE TABLE `categorie` (
  `id_categorie` int(11) NOT NULL,
  `nom_categorie` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categorie`
--

INSERT INTO `categorie` (`id_categorie`, `nom_categorie`) VALUES
(1, 'puzzle'),
(2, 'Video Games'),
(3, 'Anime'),
(4, 'Manga'),
(5, 'Books');

-- --------------------------------------------------------

--
-- Table structure for table `coupon`
--

CREATE TABLE `coupon` (
  `id_coupon` int(11) NOT NULL,
  `titre_coupon` varchar(255) DEFAULT NULL,
  `description_coupon` varchar(255) DEFAULT NULL,
  `date_expiration` date DEFAULT NULL,
  `etat_coupon` varchar(255) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `coupon`
--

INSERT INTO `coupon` (`id_coupon`, `titre_coupon`, `description_coupon`, `date_expiration`, `etat_coupon`, `id_user`) VALUES
(1, 'Coupon 1', 'coupon de reduction', '2023-02-17', 'state coupon', 5),
(2, 'Coupon 2', 'description coupon2', '2023-02-18', 'state', 6);

-- --------------------------------------------------------

--
-- Table structure for table `echange`
--

CREATE TABLE `echange` (
  `id_echange` int(11) NOT NULL,
  `date_echange` date DEFAULT NULL,
  `id_user1` int(11) DEFAULT NULL,
  `id_user2` int(11) DEFAULT NULL,
  `id_item` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `echange`
--

INSERT INTO `echange` (`id_echange`, `date_echange`, `id_user1`, `id_user2`, `id_item`) VALUES
(2, '2023-02-01', 5, 6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `id_item` int(11) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `etat` enum('New','Used') DEFAULT NULL,
  `type` enum('Physical','Digital','Service') DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_categorie` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`id_item`, `libelle`, `description`, `etat`, `type`, `id_user`, `id_categorie`) VALUES
(1, 'item1', 'description item1', 'New', 'Physical', 5, 1),
(2, 'item2', 'description item2', 'Used', 'Digital', 6, 4);

-- --------------------------------------------------------

--
-- Table structure for table `livraison`
--

CREATE TABLE `livraison` (
  `id_livraison` int(11) NOT NULL,
  `date_livraison` date DEFAULT NULL,
  `etat_livraison` enum('En-cours','Terminé','Annulé') DEFAULT NULL,
  `adresse_livraison` varchar(255) DEFAULT NULL,
  `id_livreur` int(11) DEFAULT NULL,
  `id_echange` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `livraison`
--

INSERT INTO `livraison` (`id_livraison`, `date_livraison`, `etat_livraison`, `adresse_livraison`, `id_livreur`, `id_echange`) VALUES
(1, '2023-02-02', 'En-cours', 'adresse livraison', 4, 2),
(2, '2023-02-13', 'Annulé', 'adresse livraison', 4, 2);

-- --------------------------------------------------------

--
-- Table structure for table `livreur`
--

CREATE TABLE `livreur` (
  `id_livreur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `livreur`
--

INSERT INTO `livreur` (`id_livreur`) VALUES
(4);

-- --------------------------------------------------------

--
-- Table structure for table `reclamation`
--

CREATE TABLE `reclamation` (
  `id_reclamation` int(11) NOT NULL,
  `titre_reclamation` varchar(255) DEFAULT NULL,
  `description_reclamation` varchar(255) DEFAULT NULL,
  `etat_reclamation` enum('Traité','En-cours') DEFAULT NULL,
  `date_creation` date DEFAULT NULL,
  `date_cloture` date DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reclamation`
--

INSERT INTO `reclamation` (`id_reclamation`, `titre_reclamation`, `description_reclamation`, `etat_reclamation`, `date_creation`, `date_cloture`, `id_user`) VALUES
(2, 'Reclamation1', 'Description Reclamation1', 'Traité', '2023-02-01', '2023-02-02', 5);

-- --------------------------------------------------------

--
-- Table structure for table `trader`
--

CREATE TABLE `trader` (
  `id_trader` int(11) NOT NULL,
  `score` int(11) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `trader`
--

INSERT INTO `trader` (`id_trader`, `score`, `date_naissance`) VALUES
(5, 356, '0000-00-00'),
(6, 123, '2023-02-02');

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id_user` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`id_user`, `password`, `nom`, `prenom`, `email`, `adresse`, `avatar_url`) VALUES
(4, 'PASS', '(Livreur) torkhani', 'adem', 'email@gmail.com', 'adresse', 'C:/images'),
(5, 'pass', 'user 1 (trader)', 'prenomUser 1', 'user@gmail.com', 'adresse', 'C://images'),
(6, 'pass', 'user2 (trader)', 'prenomUser 2', 'user2@gmail.com', 'adresse', 'C:/images'),
(7, 'cvmlk', ' (admin )user3', 'prenomUser3', 'user3@gmail.com', 'adresse U3', 'C:/images');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administrateur`
--
ALTER TABLE `administrateur`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indexes for table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id_categorie`);

--
-- Indexes for table `coupon`
--
ALTER TABLE `coupon`
  ADD PRIMARY KEY (`id_coupon`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `echange`
--
ALTER TABLE `echange`
  ADD PRIMARY KEY (`id_echange`),
  ADD KEY `id_item` (`id_item`),
  ADD KEY `id_user1` (`id_user1`),
  ADD KEY `id_user2` (`id_user2`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id_item`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_categorie` (`id_categorie`);

--
-- Indexes for table `livraison`
--
ALTER TABLE `livraison`
  ADD PRIMARY KEY (`id_livraison`),
  ADD KEY `id_livreur` (`id_livreur`),
  ADD KEY `id_echange` (`id_echange`);

--
-- Indexes for table `livreur`
--
ALTER TABLE `livreur`
  ADD PRIMARY KEY (`id_livreur`);

--
-- Indexes for table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id_reclamation`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `trader`
--
ALTER TABLE `trader`
  ADD PRIMARY KEY (`id_trader`);

--
-- Indexes for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `administrateur`
--
ALTER TABLE `administrateur`
  MODIFY `id_admin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id_categorie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `coupon`
--
ALTER TABLE `coupon`
  MODIFY `id_coupon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `echange`
--
ALTER TABLE `echange`
  MODIFY `id_echange` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `id_item` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `livraison`
--
ALTER TABLE `livraison`
  MODIFY `id_livraison` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `livreur`
--
ALTER TABLE `livreur`
  MODIFY `id_livreur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id_reclamation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `trader`
--
ALTER TABLE `trader`
  MODIFY `id_trader` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `administrateur`
--
ALTER TABLE `administrateur`
  ADD CONSTRAINT `administrateur_ibfk_1` FOREIGN KEY (`id_admin`) REFERENCES `utilisateur` (`id_user`);

--
-- Constraints for table `coupon`
--
ALTER TABLE `coupon`
  ADD CONSTRAINT `coupon_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id_user`);

--
-- Constraints for table `echange`
--
ALTER TABLE `echange`
  ADD CONSTRAINT `echange_ibfk_1` FOREIGN KEY (`id_item`) REFERENCES `item` (`id_item`),
  ADD CONSTRAINT `echange_ibfk_2` FOREIGN KEY (`id_user1`) REFERENCES `utilisateur` (`id_user`),
  ADD CONSTRAINT `echange_ibfk_3` FOREIGN KEY (`id_user2`) REFERENCES `utilisateur` (`id_user`);

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id_user`),
  ADD CONSTRAINT `item_ibfk_2` FOREIGN KEY (`id_categorie`) REFERENCES `categorie` (`id_categorie`);

--
-- Constraints for table `livraison`
--
ALTER TABLE `livraison`
  ADD CONSTRAINT `livraison_ibfk_1` FOREIGN KEY (`id_livreur`) REFERENCES `livreur` (`id_livreur`),
  ADD CONSTRAINT `livraison_ibfk_2` FOREIGN KEY (`id_echange`) REFERENCES `echange` (`id_echange`);

--
-- Constraints for table `livreur`
--
ALTER TABLE `livreur`
  ADD CONSTRAINT `livreur_ibfk_1` FOREIGN KEY (`id_livreur`) REFERENCES `utilisateur` (`id_user`);

--
-- Constraints for table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `reclamation_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id_user`);

--
-- Constraints for table `trader`
--
ALTER TABLE `trader`
  ADD CONSTRAINT `trader_ibfk_1` FOREIGN KEY (`id_trader`) REFERENCES `utilisateur` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
