-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 09 mars 2023 à 08:44
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
  `description` varchar(500) DEFAULT NULL,
  `contenu` mediumtext DEFAULT NULL,
  `date_publication` date DEFAULT NULL,
  `id_categorie` int(11) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT NULL,
  `id_user` int(11) NOT NULL,
  `auteur` varchar(255) DEFAULT NULL,
  `avg_rating` decimal(2,1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `article`
--

INSERT INTO `article` (`id_article`, `titre`, `description`, `contenu`, `date_publication`, `id_categorie`, `archived`, `id_user`, `auteur`, `avg_rating`) VALUES
(1, 'Dragon Ball Z: Budokai Tenkaichi 4 Release Date', 'The crowd roared as the trailer for Dragon Ball Z: Budokai Tenkaichi 4 played at the Dragon Ball Games Battle Hour 2023 yesterday.', 'The crowd roared as the trailer for Dragon Ball Z: Budokai Tenkaichi 4 played at the Dragon Ball Games Battle Hour 2023 yesterday. With there being many years passing since the previous installment, the crowd\'s reaction is justified for this incredibly beloved series.\r\n\r\nThe trailers show a seamless transition from the previous installment into this one, then showing Goku powering up into his Super Saiyan form, a feature of the combat portion of the game.\r\n\r\nThe games have players play as iconic Dragon Ball Z characters while fighting others, with animations reminiscent of the anime\'s style, and unique moves for each character, this game has solidified itself as a titan within the gaming industry.\r\n\r\nEven though the official trailer for the game dropped yesterday, there was no mention of a release date for the project. The trailer did not show any indication of a time window for players, with a note at the bottom of the screen saying footage is not final and the game is still in development.\r\n\r\nHype for this game is sure to continue for a long while, but hope is sure to be held for a release towards either the end of this year, or the beginning of next. However, only time will tell as to the validity of this vision.', '2023-03-06', 1, 0, 4, ' Raymond DePaul', NULL),
(2, 'Every Video Game Release Coming Soon For Nintendo Switch', 'The Nintendo Switch\'s library is vast, and it is constantly growing. Here are all the upcoming major Switch games and their release dates.', 'The Nintendo Switch has been a runaway success, proving that raw power is not the be-all and end-all for consoles. Through a combination of Nintendo\'s first-party games, a solid selection of triple-A third-party titles, and all the indie projects anyone could ever want, the Switch has amassed a stellar library that can match most platforms in terms of quality and quantity.\r\n\r\nThe Legend of Zelda: Breath of the Wild and Super Mario Odyssey are two of the greatest games of the last five years, but there is always a chance that the best Nintendo Switch game has yet to be released. 2021 produced Super Mario 3D World + Bowser\'s Fury, Monster Hunter Rise, NEO: The World Ends With You, The Legend of Zelda: Skyward Sword HD, Shin Megami Tensei 5, and Metroid Dread, and 2022 was decent as well\r\n\r\nHere\'s a look at all the titles we can expect to see on the Nintendo Switch in 2023 and beyond. Which big Nintendo Switch games have release dates? Please note the focus is on North American release dates.\r\n\r\nUpdated March 4, 2023: The upcoming Nintendo Switch games were added to the calendar over the last week: Vanaris Tactics, Vernal Edge, Strayed Lights, Disaster Detective Saiga: An Indescribable Mystery, Scrap Games, Puss in Boots: Interactive Book, Antigravity Racing, Island Cities, Mario Kart 8 Deluxe: Booster Course Pass - Wave 4, Mythology Waifus Mahjong, Titanium Hound, EvilUP, Life of Delta, Terminal Velocity: Boosted Edition, Tents & Trees, Link The Cubes, Post Void, Subway Midnight, Off The Tracks, Omen of Sorrow, Flashout 3, Gripper, Infinite Guitars, Anyaroth: The Queen\'s Tyranny, Orebody: Binder\'s Tale, Doodle World Deluxe, Assault Suits Valken Declassified, The Last Worker, Pretty Girls Tile Match, Alekon, Moe Waifu H, Trinity Trigger, Magical Drop 6, Neko Rescue Tale, Mugen Souls, Family Fun Night, Garden Simulator, Fitness Circuit.\r\n\r\nMarch 2023 has a couple of exciting games slated for release. ONI: Road to be the Mightiest Oni and DC Justice League: Cosmic Chaos could be fun picks for younger players, although fans of DC Comics might get a kick out of the latter as well.\r\n\r\nAfter more than a decade, Fatal Frame: Mask of the Lunar Eclipse is finally set for a Western release. Originally released in 2008, Koei Tecmo\'s horror game was a Nintendo Wii Japanese exclusive, and the title generally received positive reviews. Currently, the biggest Nintendo Switch exclusive of March 2023 is Bayonetta Origins: Cereza and the Lost Demon, a prequel centering around the titular Umbra Witch\'s early life and encounter with her first demon.\r\n\r\nMarch 1: BROK the InvestiGator (PS5, PS4, XBX/S, XBO, Switch)\r\nMarch 1: A Fox and His Robot (Switch, PC)\r\nMarch 1: Green Soldiers Heroes (Switch)\r\nMarch 1: Ken Follett\'s The Pillars of the Earth (Switch)\r\nMarch 1: Norn9: Var Commons (Switch)\r\nMarch 2: Aery - Calm Mind 3 (Switch)\r\nMarch 2: Bonfire Peaks: Lost Memories (Switch)\r\nMarch 2: Chess Pills (Switch)\r\nMarch 2: Dream Park Story (Switch)\r\nMarch 2: Fitness Boxing Fist of the North Star (Switch)\r\nMarch 2: Live Factory (Switch)\r\nMarch 2: Mario + Rabbids Sparks of Hope: Tower of Doooom (Switch)\r\nMarch 2: Mayhem in Single Valley (PS5, PS4, XBX/S, XBO, Switch, PC)\r\nMarch 2: Meg\'s Monster (Xbox One, Switch, PC)\r\nMarch 2: PowerWash Simulator: Midgar Special Pack (PS5, PS4, XBX/S, XBO, Switch, PC)\r\nMarch 2: Pretty Girls Breakers! PLUS (Switch)\r\nMarch 2: The Smile Alchemist (Switch, PC)\r\nMarch 2: Vanaris Tactics (XBO, Switch)\r\nMarch 3: The Atla Archives (Switch)\r\nMarch 3: Disaster Detective Saiga: An Indescribable Mystery (Switch)\r\nMarch 3: Gunman Tales (Switch)\r\nMarch 3: Puss in Boots: Interactive Book (Switch)\r\nMarch 3: Ro (Switch)\r\nMarch 3: Ruku\'s Heart Balloon (Switch)\r\nMarch 3: Scrap Games (Switch)\r\nMarch 3: Void Scrappers (Switch)\r\nMarch 6: Dead Cells: Return to Castlevania (PS4, XBO, Switch, PC)\r\nMarch 7: Dead by Daylight: Tools of Torment (PS5, PS4, XBX/S, XBO, Switch, PC)\r\nMarch 7: Little Witch Nobeta (PS4, Switch)\r\nMarch 7: Pronty: Fishy Adventure (Switch)\r\nMarch 9: Antigravity Racing (Switch)\r\nMarch 9: Cannon Dancer Osman (Switch)\r\nMarch 9: Caverns of Mars: Recharged (XBO, Switch, PC)\r\nMarch 9: Chippy & Noppo (Switch, PC)\r\nMarch 9: Fatal Frame: Mask of the Lunar Eclipse (PS5, PS4, XBX/S, XBO, Switch, PC)\r\nMarch 9: Figment 2: Creed Valley (PS5, PS4, XBX/S, XBO, Switch, PC)\r\nMarch 9: The Good Life - Behind the Secret of Rainy Woods (Switch)\r\nMarch 9: Know by Heart... (Switch)\r\nMarch 9: The Last Spell (PS5, PS4, Switch, PC)\r\nMarch 9: Ib (Switch)\r\nMarch 9: Island Cities (Switch)\r\nMarch 9: Mari and Bayu - The Road Home (Switch)\r\nMarch 9: Mario Kart 8 Deluxe: Booster Course Pass - Wave 4 (Switch)\r\nMarch 9: Mystic Gate (Switch, PC)\r\nMarch 9: ONI: Road to be the Mightiest Oni (Switch, PC)\r\nMarch 9: Paranormasight: The Seven Mysteries of Honjo (Switch, PC)\r\nMarch 9: Record of Agarest War (Switch)\r\nMarch 9: Tiny Troopers: Global Ops (PS5, PS4, XBX/S, XBO, Switch, PC)\r\nMarch 9: Zapling Bygone (XBX/S, XBO, Switch)\r\nMarch 10: DC Justice League: Cosmic Chaos (PS5, PS4, XBX/S, XBO, Switch, PC)\r\nMarch 10: EvilUP (Switch)\r\nMarch 10: Felix the Toy DX (Switch)\r\nMarch 10: Mato Anomalies (PS5, PS4, XBX/S, XBO, Switch, PC)\r\nMarch 10: Mythology Waifus Mahjong (Switch, PC)\r\nMarch 11: Titanium Hound (Switch)\r\nMarch 13: Life of Delta (Switch)\r\nMarch 14: The Legend of Heroes: Trails to Azure (PS4, Switch, PC)\r\nMarch 14: Tents & Trees (Switch)\r\nMarch 14: Terminal Velocity: Boosted Edition (Switch)\r\nMarch 14: Vernal Edge (PS5, PS4, XBX/S, XBO, Switch, PC)\r\nMarch 14: The Wreck (PS5, PS4, XBX/S, XBO, Switch, PC)\r\nMarch 15: Tricky Thief (PS5, PS4, XBX/S, XBO, Switch, PC)\r\nMarch 16: Alice Gear Aegis CS: Concerto of Simulatrix (PS5, PS4, Switch)\r\nMarch 16: Backbeat (Switch)\r\nMarch 16: Link The Cubes (Switch)\r\nMarch 16: Loot Box Simulator - Heroes of the Dark Age (Switch)\r\nMarch 16: Nono Adventure (Switch)\r\nMarch 16: Numolition (Switch)\r\nMarch 16: Post Void (PS5, PS4, Switch)\r\nMarch 16: Session: Skate Sim (Switch)\r\nMarch 16: Sixtar Gate: Startrail (Switch)\r\nMarch 16: Terracotta (Switch)\r\nMarch 17: Backbeat (XBX/S, XBO, Switch)\r\nMarch 17: Bayonetta Origins: Cereza and the Lost Demon (Switch)\r\nMarch 17: Flame Keeper (Switch)\r\nMarch 17: FUR Squadron (Switch, PC)\r\nMarch 17: Peppa Pig: World Adventures (PS5, PS4, XBX/S, XBO, Switch, PC)\r\nMarch 18: Fantasy Ball (Switch)\r\nMarch 20: Animals Names (Switch)\r\nMarch 20: FurryFury: Smash & Roll (Switch)\r\nMarch 21: Deceive Inc. (PS5, XBX/S, PC)\r\nMarch 21: Remnant: From the Ashes (Switch)\r\nMarch 22: Have a Nice Death (Switch, PC)\r\nMarch 23: Monorail Stories (Switch)\r\nMarch 23: Omen of Sorrow (PS5, Switch)\r\nMarch 23: Rakuen (Switch)\r\nMarch 23: The Settlers: New Allies (PS5, PS4, XBX/S, XBO, Switch)\r\nMarch 23: Storyteller (Switch, PC)\r\nMarch 23: Sushi Bar Express (Switch)\r\nMarch 24: Atelier Ryza 3: Alchemist of the End & the Secret Key (PS5, PS4, Switch, PC)\r\nMarch 24: Flashout 3 (Switch)\r\nMarch 24: Nefasto\'s Misadventure: Meeting Noeroze (Switch)\r\nMarch 28: Chef Life: A Restaurant Simulator (PS5, PS4, XBX/S, XBO, Switch, PC)\r\nMarch 28: Kana Quest (Switch)\r\nMarch 28: MLB The Show 23 (PS5, PS4, XBX/S, XBO, Switch)\r\nMarch 29: Gripper (Switch)\r\nMarch 29: RunBean Galactic (PS5, PS4, XBX/S, XBO, Switch)\r\nMarch 30: Anyaroth: The Queen\'s Tyranny (Switch, PC)\r\nMarch 30: Assault Suits Valken Declassified (Switch)\r\nMarch 30: Doodle World Deluxe (Switch)\r\nMarch 30: Dredge (PS5, PS4, XBX/S, XBO, Switch, PC)\r\nMarch 30: Guns N\' Runs (Switch)\r\nMarch 30: Infinite Guitars (XBO, Switch, PC)\r\nMarch 30: The Last Worker (PS5, XBX/S, Switch, PC)\r\nMarch 30: Lunark (PS5, PS4, XBX/S, XBO, Switch, PC)\r\nMarch 30: The Last Worker (Switch)\r\nMarch 30: Norn9: Var Commons (Switch)\r\nMarch 30: Orebody: Binder\'s Tale (Switch)\r\nMarch 30: Papertris (Switch)\r\nMarch 30: Saga of Sins (PS5, PS4, XBX/S, Switch, PC)\r\nMarch 31: Blade Assault (PS5, PS4, Switch)\r\nMarch 31: Formula Retro Racing: World Tour (Switch, PC)', '2023-03-04', 1, 0, 4, 'GAME RANT', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `article_ratings`
--

CREATE TABLE `article_ratings` (
  `id_rating` int(11) NOT NULL,
  `id_article` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `rating` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `article_ratings`
--

INSERT INTO `article_ratings` (`id_rating`, `id_article`, `id_user`, `rating`) VALUES
(2, 1, 4, 3);

-- --------------------------------------------------------

--
-- Structure de la table `categorie_article`
--

CREATE TABLE `categorie_article` (
  `id_cat` int(11) NOT NULL,
  `libelle_cat` varchar(255) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `categorie_article`
--

INSERT INTO `categorie_article` (`id_cat`, `libelle_cat`, `archived`) VALUES
(1, 'News', 0),
(2, 'short story', 0),
(4, 'RECHERE', 1),
(5, 'HISTORY', 0),
(7, 'Gaming', 0),
(8, 'Anime', 0);

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
(1, 'Coupon Casual', 'PERCENTAGE OFF DELIVERY', 0),
(2, 'Coupon Exclusif', 'FREE DELIVERY', 0),
(3, 'Coupon Special', 'FREE MOBILE TOPUP', 0);

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
(1, 'Puzzle', 0),
(2, 'Video Games', 0),
(3, 'Anime', 0),
(4, 'Manga', 0),
(5, 'Books', 0),
(6, 'Token', 0);

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
  `archived` tinyint(1) DEFAULT 0,
  `id_categorie` int(11) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `coupon`
--

INSERT INTO `coupon` (`id_coupon`, `titre_coupon`, `description_coupon`, `date_expiration`, `etat_coupon`, `id_user`, `archived`, `id_categorie`, `code`) VALUES
(1, 'CouponFevrier', 'Coupon 30% sur la livraison', '2023-02-28', 'VALID', 13, 1, 1, 'FevCoupon23'),
(3, 'CouponFevrier', 'Coupon 30% sur la livraison', '2023-02-28', 'VALID', 13, 1, 3, 'LoyaltyCoupon23'),
(110, 'Coupon Mars', 'Coupon 10% sur la livraison', '2023-04-01', 'NOT_VALID', 13, 0, 1, 'CasCoupon110'),
(112, 'Coupon Mars Gold', 'Coupon 30% sur la livraison', '2023-04-01', 'VALID', 13, 0, 3, 'LoyaltyCoupon112'),
(118, 'Coupon Mars Gold', 'Coupon 30% sur la livraison', '2023-04-01', 'VALID', 13, 0, 3, 'LoyaltyCoupon113'),
(120, 'Coupon Mars Silver', 'Coupon 100% Livraison', '2023-04-01', 'VALID', 13, 0, 2, 'SpecCoupon119'),
(121, 'azeaze ', 'azeaze', '2023-03-16', 'azeaze', NULL, 1, 3, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `coupon_old`
--

CREATE TABLE `coupon_old` (
  `id_coupon` int(11) NOT NULL,
  `titre_coupon` varchar(255) DEFAULT NULL,
  `description_coupon` varchar(255) DEFAULT NULL,
  `date_expiration` date DEFAULT NULL,
  `etat_coupon` varchar(255) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT NULL,
  `id_categorie` int(11) DEFAULT NULL,
  `code` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `coupon_old`
--

INSERT INTO `coupon_old` (`id_coupon`, `titre_coupon`, `description_coupon`, `date_expiration`, `etat_coupon`, `id_user`, `archived`, `id_categorie`, `code`) VALUES
(1, 'Coupon 1', 'coupon de reduction', '2023-02-17', 'state coupon', 5, 0, 1, NULL),
(2, 'Coupon 2', 'description coupon2', '2023-02-18', 'state', 6, 0, 1, NULL),
(3, 'Coupon Mars', 'Coupon 30% sur la livraison', '2023-04-01', 'VALID', 13, NULL, 1, 'CasCoupon3'),
(4, 'Coupon Mars', 'Coupon 30% sur la livraison', '2023-04-01', 'VALID', 13, NULL, 1, 'CasCoupon4'),
(5, 'Coupon Mars', 'Coupon 30% sur la livraison', '2023-04-01', 'VALID', 13, NULL, 1, 'CasCoupon5');

-- --------------------------------------------------------

--
-- Structure de la table `echange`
--

CREATE TABLE `echange` (
  `id_echange` int(11) NOT NULL,
  `date_echange` date DEFAULT NULL,
  `id_user1` int(11) DEFAULT NULL,
  `id_user2` int(11) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT 0,
  `liv_etat` enum('Accepter','Non_Accepter') NOT NULL DEFAULT 'Non_Accepter',
  `titre_echange` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `echange`
--

INSERT INTO `echange` (`id_echange`, `date_echange`, `id_user1`, `id_user2`, `archived`, `liv_etat`, `titre_echange`) VALUES
(195, '2023-03-09', 13, NULL, 0, 'Non_Accepter', NULL),
(196, '2023-03-09', 13, NULL, 0, 'Non_Accepter', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `echange_old`
--

CREATE TABLE `echange_old` (
  `id_echange` int(11) NOT NULL,
  `date_echange` date DEFAULT NULL,
  `id_user1` int(11) DEFAULT NULL,
  `id_user2` int(11) DEFAULT NULL,
  `id_item` int(11) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `echange_old`
--

INSERT INTO `echange_old` (`id_echange`, `date_echange`, `id_user1`, `id_user2`, `id_item`, `archived`) VALUES
(2, '2023-02-01', NULL, NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Structure de la table `echange_proposer`
--

CREATE TABLE `echange_proposer` (
  `id_prop` int(11) NOT NULL,
  `id_echange` int(11) NOT NULL,
  `date_proposer` date NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `archived` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `item`
--

CREATE TABLE `item` (
  `id_item` int(11) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `etat` enum('Neuf','Occasion','Null') DEFAULT NULL,
  `type` enum('Physique','Virtuelle','Service') DEFAULT NULL,
  `imageurl` varchar(255) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_categorie` int(11) DEFAULT NULL,
  `id_echange` int(11) DEFAULT NULL,
  `likes` int(11) DEFAULT NULL,
  `dislikes` int(11) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `item`
--

INSERT INTO `item` (`id_item`, `libelle`, `description`, `etat`, `type`, `imageurl`, `id_user`, `id_categorie`, `id_echange`, `likes`, `dislikes`, `archived`) VALUES
(1, 'item1', 'description item1', 'Neuf', 'Physique', '', 5, 1, 195, 0, 0, 1),
(17, 'Playstation 3 Slim', 'Brand new PS3 Slim with 2 controllers', 'Neuf', 'Physique', 'https://m.media-amazon.com/images/I/61MOauHyPYL._AC_UF1000,1000_QL80_.jpg', 4, 2, 0, 0, 0, 1),
(18, 'Wii', 'A perfectly used working wii', 'Occasion', 'Physique', 'https://i5.walmartimages.com/asr/78633936-9a30-4cce-8eb2-3a730c57eaa1.c173af15bb490c92e80ccc65dcbc9665.jpeg?odnHeight=612&odnWidth=612&odnBg=FFFFFF', 4, 2, 0, 2, 2, 0),
(19, 'Insérer libellés', 'Insérer descriptions', 'Occasion', 'Physique', 'file:/C:/Home/Documents/University/ETIC3A%20S2/Projet%20D%c3%a9veloppement%20Web%20Java/Examens/PIDEV/Java/out/production/Treydi-Desktop/GUI/Assets/Icons/image.png', 4, 2, 0, 0, 0, 1),
(20, 'Insérer libelléb', 'Insérer descriptionh', 'Null', 'Virtuelle', 'file:/C:/Home/Pictures/Covers/Game%20Covers/3D%20Dot%20Game%20Heroes%20Cover.jpg', 4, 2, 0, 0, 0, 1),
(21, 'x', 'x', 'Occasion', 'Physique', 'file:/C:/Home/Documents/University/ETIC3A%20S2/Projet%20D%c3%a9veloppement%20Web%20Java/Examens/PIDEV/Java/out/production/Treydi-Desktop/GUI/Assets/Icons/image.png', 4, 3, 0, 0, 0, 1),
(22, 'Wii Mini', 'A used wii mini in good condition.', 'Occasion', 'Physique', 'https://i.ebayimg.com/images/g/mrMAAOSwDgFi6CJv/s-l500.jpg', 5, 2, 196, 1, 0, 0),
(23, 'Wii black new', 'A brand new black wii.', 'Neuf', 'Physique', 'https://i.ebayimg.com/images/g/fcoAAOSwwpxjuJ8C/s-l500.jpg', 4, 2, NULL, 0, 0, 0),
(24, 'Bowser Plushie', 'Bowser cute plushie excellent quality condition.', 'Occasion', 'Physique', 'https://i.ebayimg.com/images/g/AfIAAOSwZG9iWlHO/s-l500.jpg', 4, 3, 0, 1, 0, 0),
(25, 'Formatting PC', 'I can offer you to format your computer and install anything you need.', 'Null', 'Service', 'https://helpdeskgeek.com/wp-content/pictures/2019/06/install-from-within-windows-9.png', 4, 4, 0, 1, 0, 0),
(26, 'tes', 'tes', 'Occasion', 'Physique', 'file:/C:/Home/Documents/University/ETIC3A%20S2/Projet%20D%c3%a9veloppement%20Web%20Java/Examens/PIDEV/Java/out/production/Treydi-Desktop/GUI/Assets/Icons/image.png', 4, 2, 0, 0, 0, 1),
(27, 'Insérer libellésd', 'Insérer descriptionsd', 'Occasion', 'Physique', 'file:/C:/Home/Documents/University/ETIC3A%20S2/Projet%20D%c3%a9veloppement%20Web%20Java/Examens/PIDEV/Java/out/production/Treydi-Desktop/GUI/Assets/Icons/image.png', 4, 2, 0, 0, 0, 1),
(28, 'Item test', 'ITEM TEST DESCRIPTION ', 'Null', 'Virtuelle', 'file:/C:/Users/SBS/Desktop/Integration/Treydi-Desktop/src/GUI/Assets/images/s-c.jpg', 7, 1, 0, 0, 0, 1),
(29, 'azeaze', 'azeazeaz', 'Occasion', 'Physique', 'file:/C:/Users/SBS/Desktop/Integration/Treydi-Desktop/src/GUI/Assets/images/61MOauHyPYL._AC_UF1000,1000_QL80_.jpg', 7, 1, 0, 0, 0, 0),
(30, 'TEST ITEM 2', 'TEST ITEM 3', 'Null', 'Virtuelle', 'file:/C:/Users/SBS/Desktop/Integration/Treydi-Desktop/out/production/Treydi-Desktop/GUI/Assets/Icons/image.png', 13, 1, 0, 0, 0, 0),
(33, 'u2', 'x', 'Occasion', 'Physique', 'file:/C:/Home/Documents/University/ETIC3A%20S2/Projet%20D%c3%a9veloppement%20Web%20Java/Examens/PIDEV/Java/out/production/Treydi-Desktop/GUI/Assets/Icons/image.png', 16, 3, 0, 0, 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `item_old`
--

CREATE TABLE `item_old` (
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
-- Déchargement des données de la table `item_old`
--

INSERT INTO `item_old` (`id_item`, `libelle`, `description`, `etat`, `type`, `id_user`, `id_categorie`, `archived`) VALUES
(1, 'item1', 'description item1', 'Neuf', 'Physical', 5, 1, 0),
(2, 'item2', 'description item2', 'Occasion', 'Digital', 6, 4, 0);

-- --------------------------------------------------------

--
-- Structure de la table `like_items`
--

CREATE TABLE `like_items` (
  `id_user` int(11) NOT NULL,
  `id_item` int(11) NOT NULL,
  `choice` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `like_items`
--

INSERT INTO `like_items` (`id_user`, `id_item`, `choice`) VALUES
(4, 22, 0),
(4, 24, 0),
(4, 25, 0);

-- --------------------------------------------------------

--
-- Structure de la table `livraison`
--

CREATE TABLE `livraison` (
  `id_livraison` int(11) NOT NULL,
  `date_creation_livraison` date DEFAULT NULL,
  `etat_livraison` enum('Encours','Termine','Annule') DEFAULT NULL,
  `adresse_livraison1` varchar(255) DEFAULT NULL,
  `id_livreur` int(11) DEFAULT NULL,
  `id_echange` int(11) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT 0,
  `adresse_livraison2` varchar(255) DEFAULT NULL,
  `date_terminer_livraison` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `livraison_old`
--

CREATE TABLE `livraison_old` (
  `id_livraison` int(11) NOT NULL,
  `date_livraison` date DEFAULT NULL,
  `etat_livraison` enum('En-cours','Terminé','Annulé') DEFAULT NULL,
  `adresse_livraison` varchar(255) DEFAULT NULL,
  `id_livreur` int(11) DEFAULT NULL,
  `id_echange` int(11) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `livraison_old`
--

INSERT INTO `livraison_old` (`id_livraison`, `date_livraison`, `etat_livraison`, `adresse_livraison`, `id_livreur`, `id_echange`, `archived`) VALUES
(1, '2023-02-02', 'En-cours', 'adresse livraison', 4, 2, 0),
(2, '2023-02-13', 'Annulé', 'adresse livraison', 4, 2, 0);

-- --------------------------------------------------------

--
-- Structure de la table `old_categorie_coupon`
--

CREATE TABLE `old_categorie_coupon` (
  `id_categoriecoupon` int(11) NOT NULL,
  `nom_categorie` varchar(255) DEFAULT NULL,
  `description_categorie` varchar(255) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `old_categorie_coupon`
--

INSERT INTO `old_categorie_coupon` (`id_categoriecoupon`, `nom_categorie`, `description_categorie`, `archived`) VALUES
(1, 'categoriecoupon1', 'desc', 0);

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `id_reclamation` int(11) NOT NULL,
  `titre_reclamation` varchar(255) DEFAULT NULL,
  `description_reclamation` varchar(500) DEFAULT NULL,
  `etat_reclamation` enum('Traité','En_cours') DEFAULT NULL,
  `date_creation` date DEFAULT NULL,
  `date_cloture` date DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`id_reclamation`, `titre_reclamation`, `description_reclamation`, `etat_reclamation`, `date_creation`, `date_cloture`, `id_user`, `archived`) VALUES
(2, 'Reclamation1', 'Description Reclamation1', 'Traité', '2023-02-01', '2023-02-02', 5, 0),
(3, ' test reclamation 222', 'descr test', 'En_cours', '2023-03-09', NULL, 4, 1),
(4, 'aze', 'aze', 'En_cours', '2023-03-09', NULL, 4, 0),
(5, 'aaaaa', 'aaaaaa', 'En_cours', '2023-03-09', NULL, 4, 0),
(6, 'aze', 'aze', 'En_cours', '2023-03-09', NULL, 4, 0);

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

CREATE TABLE `reponse` (
  `id_reponse` int(11) NOT NULL,
  `titre_reponse` varchar(255) DEFAULT NULL,
  `description_reponse` varchar(255) DEFAULT NULL,
  `date_reponse` date DEFAULT NULL,
  `id_reclamation` int(11) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reponse`
--

INSERT INTO `reponse` (`id_reponse`, `titre_reponse`, `description_reponse`, `date_reponse`, `id_reclamation`, `archived`) VALUES
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
(4, 'adem', ' torkhani', 'adem', 'adem', 'adresse', 'C:/images', NULL, NULL, 'trader', 0),
(5, 'pass', 'oussema', 'bourigua', 'oussembouriga@gmail.com', 'fel mseken idourou bel skeken, mestir', 'C://images', 5435, '2023-02-02', 'trader', 0),
(6, 'pt7jE6UB', 'ben hassen', 'mpez', 'moezbh.mbh@gmail.com', 'adresse', 'C:/images', 457, '2023-02-01', 'trader', 0),
(7, 'ad', 'kalthoum', 'dridi', 'ad', 'adresse U3', 'C:/images', 645, '2023-02-17', 'admin', 0),
(9, 'PASS', 'Dhia', 'Jebali', 'dhia@gmail.com', 'adresse dhia', 'D://images', 5341, '2023-02-01', 'livreur', 0),
(11, 'trader', 'trader', 'trader', 'trader', 'trader', '../Assets/icons/avatar1.png', 0, '2000-02-02', 'trader', 0),
(12, 'trader2', 'trader2', 'trader2', 'trader2', 'trader2', 'C:/Users/SBS/Desktop/Integration/Treydi-Desktop/src/GUI/Assets/icons/avatar1.png', 0, '2000-02-02', 'trader', 0),
(13, 'a', 'trader3', 'trader3', 'a', 'trader3', 'file:/C:/Users/SBS/Desktop/Integration/Treydi-Desktop/src/GUI/Assets/icons/avatar1.png', 99997999, '2000-02-02', 'trader', 0),
(14, 'livreur', 'livreur', 'livreur', 'livreur', 'livreur', 'GUI/Assets/icons/avatar1.png', NULL, NULL, 'livreur', 0),
(15, 'ali', 'aliUpdated', 'ali', 'ali', 'ali', 'file:/C:/Users/SBS/Desktop/Integration/Treydi-Desktop/out/production/Treydi-Desktop/GUI/Assets/icons/avatar1.png', NULL, NULL, 'livreur', 0),
(16, 'aa', 'aa', 'aa', 'aa', 'aa', 'GUI/Assets/icons/avatar1.png', 0, '2000-02-02', 'trader', 0),
(17, 'lv', 'lv', 'lv', 'lv', 'lv', 'GUI/Assets/icons/avatar1.png', NULL, NULL, 'livreur', 0);

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
-- Index pour la table `article_ratings`
--
ALTER TABLE `article_ratings`
  ADD PRIMARY KEY (`id_rating`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_article` (`id_article`);

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
  ADD KEY `id_categoriecoupon` (`id_categorie`),
  ADD KEY `id_user` (`id_user`);

--
-- Index pour la table `coupon_old`
--
ALTER TABLE `coupon_old`
  ADD PRIMARY KEY (`id_coupon`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_categoriecoupon` (`id_categorie`);

--
-- Index pour la table `echange`
--
ALTER TABLE `echange`
  ADD PRIMARY KEY (`id_echange`),
  ADD KEY `id_user1` (`id_user1`),
  ADD KEY `id_user2` (`id_user2`);

--
-- Index pour la table `echange_old`
--
ALTER TABLE `echange_old`
  ADD PRIMARY KEY (`id_echange`),
  ADD KEY `id_item` (`id_item`),
  ADD KEY `id_user1` (`id_user1`),
  ADD KEY `id_user2` (`id_user2`);

--
-- Index pour la table `echange_proposer`
--
ALTER TABLE `echange_proposer`
  ADD PRIMARY KEY (`id_prop`),
  ADD KEY `id_echange` (`id_echange`),
  ADD KEY `id_user` (`id_user`);

--
-- Index pour la table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id_item`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_categorie` (`id_categorie`);

--
-- Index pour la table `item_old`
--
ALTER TABLE `item_old`
  ADD PRIMARY KEY (`id_item`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_categorie` (`id_categorie`);

--
-- Index pour la table `like_items`
--
ALTER TABLE `like_items`
  ADD PRIMARY KEY (`id_user`,`id_item`);

--
-- Index pour la table `livraison`
--
ALTER TABLE `livraison`
  ADD PRIMARY KEY (`id_livraison`),
  ADD KEY `id_livreur` (`id_livreur`),
  ADD KEY `id_echange` (`id_echange`);

--
-- Index pour la table `livraison_old`
--
ALTER TABLE `livraison_old`
  ADD PRIMARY KEY (`id_livraison`),
  ADD KEY `id_livreur` (`id_livreur`),
  ADD KEY `id_echange` (`id_echange`);

--
-- Index pour la table `old_categorie_coupon`
--
ALTER TABLE `old_categorie_coupon`
  ADD PRIMARY KEY (`id_categoriecoupon`);

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
  MODIFY `id_article` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `article_ratings`
--
ALTER TABLE `article_ratings`
  MODIFY `id_rating` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `categorie_article`
--
ALTER TABLE `categorie_article`
  MODIFY `id_cat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `categorie_coupon`
--
ALTER TABLE `categorie_coupon`
  MODIFY `id_categoriecoupon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `categorie_items`
--
ALTER TABLE `categorie_items`
  MODIFY `id_categorie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `coupon`
--
ALTER TABLE `coupon`
  MODIFY `id_coupon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=122;

--
-- AUTO_INCREMENT pour la table `coupon_old`
--
ALTER TABLE `coupon_old`
  MODIFY `id_coupon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `echange`
--
ALTER TABLE `echange`
  MODIFY `id_echange` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=197;

--
-- AUTO_INCREMENT pour la table `echange_old`
--
ALTER TABLE `echange_old`
  MODIFY `id_echange` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `echange_proposer`
--
ALTER TABLE `echange_proposer`
  MODIFY `id_prop` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `item`
--
ALTER TABLE `item`
  MODIFY `id_item` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT pour la table `item_old`
--
ALTER TABLE `item_old`
  MODIFY `id_item` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `livraison`
--
ALTER TABLE `livraison`
  MODIFY `id_livraison` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT pour la table `livraison_old`
--
ALTER TABLE `livraison_old`
  MODIFY `id_livraison` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `old_categorie_coupon`
--
ALTER TABLE `old_categorie_coupon`
  MODIFY `id_categoriecoupon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id_reclamation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `id_reponse` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

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
-- Contraintes pour la table `article_ratings`
--
ALTER TABLE `article_ratings`
  ADD CONSTRAINT `article_ratings_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id_user`),
  ADD CONSTRAINT `article_ratings_ibfk_2` FOREIGN KEY (`id_article`) REFERENCES `article` (`id_article`);

--
-- Contraintes pour la table `coupon`
--
ALTER TABLE `coupon`
  ADD CONSTRAINT `coupon_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id_user`),
  ADD CONSTRAINT `coupon_ibfk_2` FOREIGN KEY (`id_categorie`) REFERENCES `categorie_coupon` (`id_categoriecoupon`);

--
-- Contraintes pour la table `coupon_old`
--
ALTER TABLE `coupon_old`
  ADD CONSTRAINT `coupon_old_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id_user`),
  ADD CONSTRAINT `coupon_old_ibfk_2` FOREIGN KEY (`id_categorie`) REFERENCES `old_categorie_coupon` (`id_categoriecoupon`);

--
-- Contraintes pour la table `echange`
--
ALTER TABLE `echange`
  ADD CONSTRAINT `echange_ibfk_2` FOREIGN KEY (`id_user1`) REFERENCES `utilisateur` (`id_user`),
  ADD CONSTRAINT `echange_ibfk_3` FOREIGN KEY (`id_user2`) REFERENCES `utilisateur` (`id_user`);

--
-- Contraintes pour la table `echange_old`
--
ALTER TABLE `echange_old`
  ADD CONSTRAINT `echange_old_ibfk_1` FOREIGN KEY (`id_item`) REFERENCES `item_old` (`id_item`),
  ADD CONSTRAINT `echange_old_ibfk_2` FOREIGN KEY (`id_user1`) REFERENCES `utilisateur` (`id_user`),
  ADD CONSTRAINT `echange_old_ibfk_3` FOREIGN KEY (`id_user2`) REFERENCES `utilisateur` (`id_user`);

--
-- Contraintes pour la table `echange_proposer`
--
ALTER TABLE `echange_proposer`
  ADD CONSTRAINT `id_echange` FOREIGN KEY (`id_echange`) REFERENCES `echange` (`id_echange`),
  ADD CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id_user`);

--
-- Contraintes pour la table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id_user`),
  ADD CONSTRAINT `item_ibfk_2` FOREIGN KEY (`id_categorie`) REFERENCES `categorie_items` (`id_categorie`);

--
-- Contraintes pour la table `item_old`
--
ALTER TABLE `item_old`
  ADD CONSTRAINT `item_old_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id_user`),
  ADD CONSTRAINT `item_old_ibfk_2` FOREIGN KEY (`id_categorie`) REFERENCES `categorie_items` (`id_categorie`);

--
-- Contraintes pour la table `livraison`
--
ALTER TABLE `livraison`
  ADD CONSTRAINT `livraison_ibfk_1` FOREIGN KEY (`id_livreur`) REFERENCES `utilisateur` (`id_user`),
  ADD CONSTRAINT `livraison_ibfk_2` FOREIGN KEY (`id_echange`) REFERENCES `echange` (`id_echange`);

--
-- Contraintes pour la table `livraison_old`
--
ALTER TABLE `livraison_old`
  ADD CONSTRAINT `livraison_old_ibfk_1` FOREIGN KEY (`id_livreur`) REFERENCES `utilisateur` (`id_user`),
  ADD CONSTRAINT `livraison_old_ibfk_2` FOREIGN KEY (`id_echange`) REFERENCES `echange_old` (`id_echange`);

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
