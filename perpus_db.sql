-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 21, 2025 at 03:04 AM
-- Server version: 8.0.30
-- PHP Version: 8.2.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `perpus_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `id` int NOT NULL,
  `judul` varchar(50) NOT NULL,
  `genre` varchar(50) NOT NULL,
  `tahun` varchar(50) NOT NULL,
  `penulis` varchar(50) NOT NULL,
  `link_cover` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` enum('Tersedia','Dipinjam','Dihapus') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`id`, `judul`, `genre`, `tahun`, `penulis`, `link_cover`, `status`) VALUES
(1, 'Nobita', 'Fiksi', '2000', 'Naruto', 'http', 'Dihapus'),
(5, 'Bumi', 'Fiksi', '2015', 'Tere Liye', '-', 'Dihapus'),
(6, 'Naruto', 'Komedi', '2005', 'Kabuto', 'http', 'Dihapus'),
(7, '7', 'Naruto Shippuden', 'Komedi', '2005', 'Kabuto', 'Dihapus'),
(8, 'The Psychology of Money', 'Motivation', '2022', 'Morgan Housel', 'https://m.media-amazon.com/images/I/71g2ednj0JL.jpg', 'Dipinjam'),
(9, 'Naruto', 'Komik', '2000', 'Ntah', 'https://cdn-icons-png.flaticon.com/512/29/29302.png', 'Dihapus'),
(10, 'Atomic Habits', 'Motivation', '2015', 'James Clear', 'https://m.media-amazon.com/images/I/91bYsX41DVL.jpg ', 'Tersedia'),
(11, 'Harry Potter and the Sorcerer\'s Stone', 'Fantasy', '1997', 'J.K. Rowling', 'https://images-na.ssl-images-amazon.com/images/I/81iqZ2HHD-L.jpg', 'Dipinjam');

-- --------------------------------------------------------

--
-- Table structure for table `riwayat_peminjaman`
--

CREATE TABLE `riwayat_peminjaman` (
  `id_peminjaman` int NOT NULL,
  `id_buku` int NOT NULL,
  `id_user` int NOT NULL,
  `tanggal_peminjaman` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `riwayat_peminjaman`
--

INSERT INTO `riwayat_peminjaman` (`id_peminjaman`, `id_buku`, `id_user`, `tanggal_peminjaman`) VALUES
(1, 1, 1, '2025-05-20'),
(2, 5, 1, '2025-05-20'),
(5, 1, 2, '2025-05-20'),
(6, 8, 1, '2025-05-20'),
(7, 10, 1, '2025-05-21'),
(8, 10, 2, '2025-05-21'),
(9, 9, 3, '2025-05-21'),
(10, 11, 1, '2025-05-21');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'Maan', '123'),
(2, 'Luqmaan', '12345'),
(3, 'Apis', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `riwayat_peminjaman`
--
ALTER TABLE `riwayat_peminjaman`
  ADD PRIMARY KEY (`id_peminjaman`),
  ADD KEY `user` (`id_user`),
  ADD KEY `buku` (`id_buku`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `buku`
--
ALTER TABLE `buku`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `riwayat_peminjaman`
--
ALTER TABLE `riwayat_peminjaman`
  MODIFY `id_peminjaman` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `riwayat_peminjaman`
--
ALTER TABLE `riwayat_peminjaman`
  ADD CONSTRAINT `buku` FOREIGN KEY (`id_buku`) REFERENCES `buku` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `user` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
