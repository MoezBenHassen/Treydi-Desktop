-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 19 fév. 2023 à 14:33
-- Version du serveur : 10.4.24-MariaDB
-- Version de PHP : 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `treydi_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

CREATE TABLE `article` (
  `id_article` int(11) NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `contenu` varchar(255) DEFAULT NULL,
  `date_publication` date DEFAULT NULL,
  `id_categorie` int(11) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT NULL,
  `id_user` int(11) NOT NULL,
  `auteur` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `categorie_article`
--

CREATE TABLE `categorie_article` (
  `id_cat` int(11) NOT NULL,
  `libelle_cat` varchar(255) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `categorie_coupon`
--

CREATE TABLE `categorie_coupon` (
  `id_categoriecoupon` int(11) NOT NULL,
  `nom_categorie` varchar(255) DEFAULT NULL,
  `description_categorie` varchar(255) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `categorie_coupon`
--

INSERT INTO `categorie_coupon` (`id_categoriecoupon`, `nom_categorie`, `description_categorie`, `archived`) VALUES
(1, 'categoriecoupon1', 'desc', 0);

-- --------------------------------------------------------

--
-- Structure de la table `categorie_items`
--

CREATE TABLE `categorie_items` (
  `id_categorie` int(11) NOT NULL,
  `nom_categorie` varchar(255) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `categorie_items`
--

INSERT INTO `categorie_items` (`id_categorie`, `nom_categorie`, `archived`) VALUES
(1, 'puzzle', 0),
(2, 'Video Games', 0),
(3, 'Anime', 0),
(4, 'Manga', 0),
(5, 'Books', 0);

-- --------------------------------------------------------

--
-- Structure de la table `coupon`
--

CREATE TABLE `coupon` (
  `id_coupon` int(11) NOT NULL,
  `titre_coupon` varchar(255) DEFAULT NULL,
  `description_coupon` varchar(255) DEFAULT NULL,
  `date_expiration` date DEFAULT NULL,
  `etat_coupon` varchar(255) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT NULL,
  `id_categorie` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `coupon`
--

INSERT INTO `coupon` (`id_coupon`, `titre_coupon`, `description_coupon`, `date_expiration`, `etat_coupon`, `id_user`, `archived`, `id_categorie`) VALUES
(1, 'Coupon 1', 'coupon de reduction', '2023-02-17', 'state coupon', 5, 0, 1),
(2, 'Coupon 2', 'description coupon2', '2023-02-18', 'state', 6, 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `echange`
--

CREATE TABLE `echange` (
  `id_echange` int(11) NOT NULL,
  `date_echange` date DEFAULT NULL,
  `id_user1` int(11) DEFAULT NULL,
  `id_user2` int(11) DEFAULT NULL,
  `id_item` int(11) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `echange`
--

INSERT INTO `echange` (`id_echange`, `date_echange`, `id_user1`, `id_user2`, `id_item`, `archived`) VALUES
(2, '2023-02-01', 5, 6, 1, 0);

-- --------------------------------------------------------

--
-- Structure de la table `item`
--

CREATE TABLE `item` (
  `id_item` int(11) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `etat` enum('Neuf','Occasion') DEFAULT NULL,
  `type` enum('Physical','Digital','Service') DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_categorie` int(11) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `item`
--

INSERT INTO `item` (`id_item`, `libelle`, `description`, `etat`, `type`, `id_user`, `id_categorie`, `archived`) VALUES
(1, 'item1', 'description item1', 'Neuf', 'Physical', 5, 1, 0),
(2, 'item2', 'description item2', 'Occasion', 'Digital', 6, 4, 0);

-- --------------------------------------------------------

--
-- Structure de la table `livraison`
--

CREATE TABLE `livraison` (
  `id_livraison` int(11) NOT NULL,
  `date_livraison` date DEFAULT NULL,
  `etat_livraison` enum('En-cours','Terminé','Annulé') DEFAULT NULL,
  `adresse_livraison` varchar(255) DEFAULT NULL,
  `id_livreur` int(11) DEFAULT NULL,
  `id_echange` int(11) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `livraison`
--

INSERT INTO `livraison` (`id_livraison`, `date_livraison`, `etat_livraison`, `adresse_livraison`, `id_livreur`, `id_echange`, `archived`) VALUES
(1, '2023-02-02', 'En-cours', 'adresse livraison', 4, 2, 0),
(2, '2023-02-13', 'Annulé', 'adresse livraison', 4, 2, 0);

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `id_reclamation` int(11) NOT NULL,
  `titre_reclamation` varchar(255) DEFAULT NULL,
  `description_reclamation` varchar(255) DEFAULT NULL,
  `etat_reclamation` enum('Traité','En-cours') DEFAULT NULL,
  `date_creation` date DEFAULT NULL,
  `date_cloture` date DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`id_reclamation`, `titre_reclamation`, `description_reclamation`, `etat_reclamation`, `date_creation`, `date_cloture`, `id_user`, `archived`) VALUES
(2, 'Reclamation1', 'Description Reclamation1', 'Traité', '2023-02-01', '2023-02-02', 5, 0);

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

CREATE TABLE `reponse` (
  `id_reponse` int(11) NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `id_reclamation` int(11) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reponse`
--

INSERT INTO `reponse` (`id_reponse`, `titre`, `description`, `date`, `id_reclamation`, `archived`) VALUES
(1, 'REPONSE 1', 'desc', '2022-09-06', 2, 0);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id_user` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `role` enum('admin','trader','livreur') DEFAULT NULL,
  `archived` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_user`, `password`, `nom`, `prenom`, `email`, `adresse`, `avatar_url`, `score`, `date_naissance`, `role`, `archived`) VALUES
(4, 'PASS', ' torkhani', 'adem', 'email@gmail.com', 'adresse', 'C:/images', NULL, NULL, 'livreur', 0),
(5, 'pass', 'oussema', 'bourigua', 'oussembouriga@gmail.com', 'fel mseken idourou bel skeken, mestir', 'C://images', 5435, '2023-02-02', 'trader', 0),
(6, 'pass', 'ben hassen', 'mpez', 'moez@gmail.com', 'adresse', 'C:/images', 457, '2023-02-01', 'trader', 0),
(7, 'pass', 'kalthoum', 'dridi', 'kalthoum@gmail.com', 'adresse U3', 'C:/images', 645, '2023-02-17', 'admin', 0),
(8, 'PASS', 'marouan', 'ayed', 'marouan@gmail.com', 'adresse marouan', 'C://images', 6451, '2023-02-23', 'admin', 0),
(9, 'PASS', 'Dhia', 'Jebali', 'dhia@gmail.com', 'adresse dhia', 'D://images', 5341, '2023-02-01', 'livreur', 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`id_article`),
  ADD KEY `id_categorie` (`id_categorie`),
  ADD KEY `id_user` (`id_user`);

--
-- Index pour la table `categorie_article`
--
ALTER TABLE `categorie_article`
  ADD PRIMARY KEY (`id_cat`);

--
-- Index pour la table `categorie_coupon`
--
ALTER TABLE `categorie_coupon`
  ADD PRIMARY KEY (`id_categoriecoupon`);

--
-- Index pour la table `categorie_items`
--
ALTER TABLE `categorie_items`
  ADD PRIMARY KEY (`id_categorie`);

--
-- Index pour la table `coupon`
--
ALTER TABLE `coupon`
  ADD PRIMARY KEY (`id_coupon`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_categoriecoupon` (`id_categorie`);

--
-- Index pour la table `echange`
--
ALTER TABLE `echange`
  ADD PRIMARY KEY (`id_echange`),
  ADD KEY `id_item` (`id_item`),
  ADD KEY `id_user1` (`id_user1`),
  ADD KEY `id_user2` (`id_user2`);

--
-- Index pour la table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id_item`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_categorie` (`id_categorie`);

--
-- Index pour la table `livraison`
--
ALTER TABLE `livraison`
  ADD PRIMARY KEY (`id_livraison`),
  ADD KEY `id_livreur` (`id_livreur`),
  ADD KEY `id_echange` (`id_echange`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id_reclamation`),
  ADD KEY `id_user` (`id_user`);

--
-- Index pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`id_reponse`),
  ADD KEY `reponse_ibfk_1` (`id_reclamation`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `article`
--
ALTER TABLE `article`
  MODIFY `id_article` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `categorie_article`
--
ALTER TABLE `categorie_article`
  MODIFY `id_cat` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `categorie_coupon`
--
ALTER TABLE `categorie_coupon`
  MODIFY `id_categoriecoupon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `categorie_items`
--
ALTER TABLE `categorie_items`
  MODIFY `id_categorie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `coupon`
--
ALTER TABLE `coupon`
  MODIFY `id_coupon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `echange`
--
ALTER TABLE `echange`
  MODIFY `id_echange` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `item`
--
ALTER TABLE `item`
  MODIFY `id_item` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `livraison`
--
ALTER TABLE `livraison`
  MODIFY `id_livraison` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id_reclamation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `id_reponse` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `article`
--
ALTER TABLE `article`
  ADD CONSTRAINT `article_ibfk_1` FOREIGN KEY (`id_categorie`) REFERENCES `categorie_article` (`id_cat`),
  ADD CONSTRAINT `article_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id_user`);

--
-- Contraintes pour la table `coupon`
--
ALTER TABLE `coupon`
  ADD CONSTRAINT `coupon_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id_user`),
  ADD CONSTRAINT `coupon_ibfk_2` FOREIGN KEY (`id_categorie`) REFERENCES `categorie_coupon` (`id_categoriecoupon`);

--
-- Contraintes pour la table `echange`
--
ALTER TABLE `echange`
  ADD CONSTRAINT `echange_ibfk_1` FOREIGN KEY (`id_item`) REFERENCES `item` (`id_item`),
  ADD CONSTRAINT `echange_ibfk_2` FOREIGN KEY (`id_user1`) REFERENCES `utilisateur` (`id_user`),
  ADD CONSTRAINT `echange_ibfk_3` FOREIGN KEY (`id_user2`) REFERENCES `utilisateur` (`id_user`);

--
-- Contraintes pour la table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id_user`),
  ADD CONSTRAINT `item_ibfk_2` FOREIGN KEY (`id_categorie`) REFERENCES `categorie_items` (`id_categorie`);

--
-- Contraintes pour la table `livraison`
--
ALTER TABLE `livraison`
  ADD CONSTRAINT `livraison_ibfk_1` FOREIGN KEY (`id_livreur`) REFERENCES `utilisateur` (`id_user`),
  ADD CONSTRAINT `livraison_ibfk_2` FOREIGN KEY (`id_echange`) REFERENCES `echange` (`id_echange`);

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `reclamation_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id_user`);

--
-- Contraintes pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `reponse_ibfk_1` FOREIGN KEY (`id_reclamation`) REFERENCES `reclamation` (`id_reclamation`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
