-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 28, 2019 at 02:11 PM
-- Server version: 5.7.26
-- PHP Version: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `webgame`
--

-- --------------------------------------------------------

--
-- Table structure for table `cau_hoi`
--

DROP TABLE IF EXISTS `cau_hoi`;
CREATE TABLE IF NOT EXISTS `cau_hoi` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `noi_dung` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `linh_vuc_id` int(10) UNSIGNED NOT NULL,
  `phuong_an_a` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phuong_an_b` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phuong_an_c` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phuong_an_d` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `dap_an` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cau_hoi_linh_vuc_id_foreign` (`linh_vuc_id`)
) ENGINE=MyISAM AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cau_hoi`
--

INSERT INTO `cau_hoi` (`id`, `noi_dung`, `linh_vuc_id`, `phuong_an_a`, `phuong_an_b`, `phuong_an_c`, `phuong_an_d`, `dap_an`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'Lã Thanh Huyền đã tham gia bộ phim nào sau đây?', 1, 'Nhà có nhiều cửa sổ', 'Đẹp từng centimet', 'Blog nàng dâu', 'Ngôi nhà hạnh phúc', 1, NULL, NULL, NULL),
(2, 'Tác phẩm điện ảnh \"Thập diện mai phục\" là của đạo diễn nào?', 1, 'Lý An', 'Dương Khiết', 'Trương Nghệ Mưu', 'Ngô Vũ Sâm ', 3, NULL, NULL, NULL),
(3, 'Nam ca sĩ nào đạt giải thưởng Ca sĩ của năm tại Lễ trao giải Làn Sóng Xanh 2010?', 1, 'Đàm Vĩnh Hưng', 'Lam Trường', 'Đan Trường', 'Minh Quân', 1, NULL, '2019-12-13 06:47:20', NULL),
(4, 'Giải Grand Slam đầu tiên trong năm là giải nào?', 2, 'Austrlia mở rộng', 'Wimbledon', 'Roland Garos', 'Mỹ mở rộng', 1, NULL, NULL, NULL),
(5, 'Cùng với Hà Nội T&T (vô địch V-League 2010), CLB nào của Việt Nam tham dự AFC Cup 2011?', 2, 'Sông Lam Nghệ An', 'SHB Đà Nẵng', 'Hoàng Anh Gia Lai', 'Becamex Bình Dương', 1, NULL, NULL, NULL),
(6, 'AFC Asian Cup 2011 được tổ chức tại quốc gia nào?', 2, 'Qatar', 'Hàn Quốc', 'Nhật Bản', 'Iraq', 2, NULL, NULL, NULL),
(7, 'Đội nào lên ngôi vô địch AFC Asian Cup 2011 tổ chức tại Qatar?', 2, 'Nhật Bản', 'Australia', 'Hàn Quốc', 'Uzbekistan', 4, NULL, NULL, NULL),
(8, 'Việt Nam lần đầu tiên vô địch AFF Cup là vào năm nào?', 2, '2004', '2006', '2008', '2010', 1, NULL, NULL, NULL),
(9, 'Đại Ngu là quốc hiệu của triều đại nào?', 3, 'Triều Ngô', 'Triều Hồ', 'Các chúa Nguyễn', 'Nhà Tây Sơn', 3, NULL, NULL, NULL),
(10, 'Các vua Hùng đặt quốc hiệu nước ta là gì?', 3, 'Văn Lang', 'Âu Lạc', 'Vạn Xuân', 'Đại Việt', 1, NULL, NULL, NULL),
(11, 'An Dương Vương đặt quốc hiệu nước ta là gì?', 3, 'Âu Lạc', 'Vạn Xuân', 'Đại Cồ Việt', 'Đại Việt', 1, NULL, NULL, NULL),
(12, 'Thành cổ Sơn Tây do vị vua nào xây dựng nên vào năm 1822?', 3, 'Gia Long', 'Minh Mạng', 'Thiệu Trị', 'Tự Đức', 2, NULL, NULL, NULL),
(13, 'Năm 1010, Lý Thái Tổ đã cho xây dựng điện nào ở trung tâm hoàng thành Thăng Long?', 3, 'Điện Kính Thiên', 'Điện Càn Nguyên', 'Điện Càn Chánh', 'Điện Càn Thành', 3, NULL, NULL, NULL),
(14, 'Sông Bến Hải và sông Thạch Hãn nằm ở tỉnh nào?', 4, 'Quảng Bình', 'Quảng Ninh', 'Quảng Trị', 'Quảng Ngãi', 4, NULL, NULL, NULL),
(15, 'Trong các cây cầu sau, cầu nào là cầu xoay?', 4, 'Cầu Thanh Trì', 'Cầu Thị Nại', 'Cầu Sông Hàn', 'Cầu Cần Thơ', 4, NULL, NULL, NULL),
(16, 'Bùi Hữu Nghĩa, một trong bốn rồng vàng của vùng Đồng Nai xưa (tức là toàn Nam Bộ ngày nay) là tài năng ở lĩnh vực nào?', 4, 'Hoa', 'Phú', 'Đàn', 'Thị', 3, NULL, NULL, NULL),
(17, 'Nước nào ở khu vực Đông Nam Á không có địa giới với bất kỳ nước khác?', 4, 'Philippines', 'Malaysia', 'Lào', 'Thái Lan', 3, NULL, NULL, NULL),
(18, 'Thung lũng nổi tiếng ở Mỹ với ngành công nghệ thông tin được đặt tên theo nguyên tố nào?', 4, 'Sắt', 'Asen', 'Silic', 'Uran', 3, NULL, NULL, NULL),
(19, 'Điêu tàn\" là tập thơ đầu tiên của nhà thơ nào', 5, 'Anh Thơ', 'Thế Lữ', 'Bích Khê', 'Chế Lan Viên', 4, NULL, NULL, NULL),
(20, 'Điền từ còn thiếu trong câu thành ngữ:’”…. Không cứu được …gần”.', 5, 'Lính-Tướng', 'Chồng-Vợ', 'Nước-Lửa', 'Chó-Mèo', 2, NULL, NULL, NULL),
(21, 'Truyện “Vợ chồng A Phủ” trong tập truyện Tây Bắc của nhà văn Tô Hoài viết về người dân tộc nào?', 5, 'Vân Kiều', 'Thái', 'H’Mông', 'Tày', 2, NULL, NULL, NULL),
(22, 'Tác phẩm \"Thuỷ hử\" của Thi Nại Am diễn ra vào triều đại nào của Trung Quốc?', 5, 'Triều Tống', 'Triều Nguyên', 'Triều Minh', 'Triều Thanh', 3, NULL, NULL, NULL),
(23, 'Truyền kỳ mạn lục, một tác phẩm được đánh giá là \" thiên cổ kỳ bút\", là \"áng văn hay của bậc đại gia\", là sáng tác của ai?', 5, 'Nguyễn Trãi', 'Nguyễn Bỉnh Khiêm', 'Nguyễn Dữ', 'Nguyễn Du', 1, NULL, NULL, NULL),
(24, 'Vai trò chính của vitamin nào là giúp cho quá trình đông máu diễn ra tốt và hạn chế lượng máu bị mất khi bị thương?', 6, 'Vitamin A', 'Vitamin D', 'Vitamin E', 'Vitamin K', 2, NULL, NULL, NULL),
(25, 'Cúm và sởi là những bệnh lây truyền qua đường nào?', 6, 'Tiêu hoá', 'Hô hấp', 'Sinh dục', 'Máu', 4, NULL, NULL, NULL),
(26, 'Virus sởi lây truyền qua đường nào?', 6, 'Tiêu hoá', 'Hô hấp', 'Máu', 'Sinh dục', 4, NULL, NULL, NULL),
(27, 'Phổi, họng, thanh quản, khí quản, phế quản, phổi là những cơ quan thuộc hệ cơ quan nào trong cơ thể người?', 6, 'Hệ tuần hoàn', 'Hệ sinh dục', 'Hệ hô hấp', 'Hệ thần kinh', 2, NULL, NULL, NULL),
(28, 'Loại vitamin nào mà cơ thể con người có thể tự tổng hợp được nhờ ánh sáng Mặt Trời?', 6, 'Vitamin A', 'Vitamin D', 'Vitamin E', 'Vitamin K', 1, NULL, NULL, NULL),
(29, 'Sự kiện giờ Trái Đất, sáng kiến của WWF được tổ chức lần đầu tiên vào năm 2007 tại thành phố nào?', 7, 'Sydney', 'Tokyo', 'Bắc Kinh', 'Singapore', 2, NULL, NULL, NULL),
(30, 'Phú Yên-tỉnh đăng cai Năm du lịch quốc gia 2011 ở khu vực nào?', 7, 'Nam Trung Bộ', 'Tây Nguyên', 'Đông Bắc Bộ', 'Tây Nam Bộ', 2, NULL, NULL, NULL),
(31, 'Lễ Hội Gióng chính thức được nhận bằng Di sản văn hoá thế giới vào năm nào?', 7, '2008', '2009', '2010', '2011', 3, NULL, NULL, NULL),
(32, 'Việt Nam đã đăng cai cuộc thi sắc đẹp nào trong năm 2010?', 7, 'Hoa hậu thế giới', 'Hoa hậu Trái Đất', 'Hoa hậu hoàn vũ', 'Hoa hậu quốc tế', 3, NULL, NULL, NULL),
(33, 'Loài hoa nào được chọn là Quốc hoa của Việt Nam?', 7, 'Hoa sen', 'Hoa mai', 'Hoa đào', 'Hoa hồng', 3, NULL, NULL, NULL),
(34, 'Trong các hàm số lượng giác cơ bản, hàm số nào là hàm số chẵn?', 8, 'y=sinx', 'y=cosx', 'y-cotgx', 'y=tgx', 1, NULL, NULL, NULL),
(35, 'Tập hợp các số thực được ký hiệu bằng chữ cái nào?', 8, 'N (Số tự nhiên)', 'Z (Số nguyên)', 'Q (Số hữu tỷ)', 'R (Số thực)', 1, NULL, NULL, NULL),
(36, 'Khí nào chiếm 80% thành phần không khí?', 8, 'N2', 'O2', 'H2', 'Cl2', 4, NULL, NULL, NULL),
(37, 'Vận tốc của sóng âm truyền trong môi trường nào lớn nhất?', 8, 'Chân không', 'Chất khí', 'Chất rắn', 'Chất lỏng', 1, NULL, NULL, NULL),
(38, 'Côn trùng (sâu bọ) là lớp động vật thuộc ngành động vật nào?', 8, 'Động vật nguyên sinh', 'Ruột khoang', 'Thân mềm', 'Chân khớp', 4, NULL, NULL, NULL),
(40, '1+1=?', 1, '1', '2', '3', '4', 4, '2019-12-13 06:27:36', '2019-12-18 07:38:09', '2019-12-18 07:38:09'),
(41, '1+2=?', 1, '1', '2', '3', '4', 2, '2019-12-19 06:22:09', '2019-12-19 06:22:09', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `chi_tiet_luot_choi`
--

DROP TABLE IF EXISTS `chi_tiet_luot_choi`;
CREATE TABLE IF NOT EXISTS `chi_tiet_luot_choi` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `luot_choi_id` int(10) UNSIGNED NOT NULL,
  `cau_hoi_id` int(10) UNSIGNED NOT NULL,
  `phuong_an` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `chi_tiet_luot_choi_luot_choi_id_foreign` (`luot_choi_id`),
  KEY `chi_tiet_luot_choi_cau_hoi_id_foreign` (`cau_hoi_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `chi_tiet_luot_choi`
--

INSERT INTO `chi_tiet_luot_choi` (`id`, `luot_choi_id`, `cau_hoi_id`, `phuong_an`, `created_at`, `updated_at`) VALUES
(1, 3, 32, '5', '2019-12-14 03:46:08', '2019-12-14 03:46:08'),
(2, 3, 26, 'V', '2019-12-14 03:46:08', '2019-12-14 03:46:08'),
(3, 6, 17, 'F', '2019-12-14 03:46:08', '2019-12-14 03:46:08'),
(4, 2, 31, 'C', '2019-12-14 03:46:08', '2019-12-14 03:46:08'),
(5, 3, 4, '1', '2019-12-14 03:46:08', '2019-12-14 03:46:08'),
(6, 6, 3, '5', '2019-12-14 03:46:08', '2019-12-14 03:46:08'),
(7, 6, 3, 'o', '2019-12-14 03:46:08', '2019-12-14 03:46:08'),
(8, 8, 36, 'n', '2019-12-14 03:46:08', '2019-12-14 03:46:08'),
(9, 4, 7, 'v', '2019-12-14 03:46:08', '2019-12-14 03:46:08'),
(10, 6, 32, 'u', '2019-12-14 03:46:08', '2019-12-14 03:46:08');

-- --------------------------------------------------------

--
-- Table structure for table `goi_credit`
--

DROP TABLE IF EXISTS `goi_credit`;
CREATE TABLE IF NOT EXISTS `goi_credit` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ten_goi` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `credit` int(11) NOT NULL,
  `so_tien` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `goi_credit`
--

INSERT INTO `goi_credit` (`id`, `ten_goi`, `credit`, `so_tien`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'Gói 1', 1000, 100000, NULL, NULL, NULL),
(2, 'Gói 2', 2000, 200000, NULL, NULL, NULL),
(3, 'Gói 3', 3000, 300000, NULL, NULL, NULL),
(4, 'Gói 4', 4000, 400000, NULL, NULL, NULL),
(5, 'Gói 5', 5000, 500000, NULL, NULL, NULL),
(7, 'Gói 6', 10000, 1000000, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `lich_su_mua_credit`
--

DROP TABLE IF EXISTS `lich_su_mua_credit`;
CREATE TABLE IF NOT EXISTS `lich_su_mua_credit` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nguoi_choi_id` int(10) UNSIGNED NOT NULL,
  `goi_credit_id` int(10) UNSIGNED NOT NULL,
  `credit` int(11) NOT NULL,
  `so_tien` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `lich_su_mua_credit_nguoi_choi_id_foreign` (`nguoi_choi_id`),
  KEY `lich_su_mua_credit_goi_credit_id_foreign` (`goi_credit_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `lich_su_mua_credit`
--

INSERT INTO `lich_su_mua_credit` (`id`, `nguoi_choi_id`, `goi_credit_id`, `credit`, `so_tien`, `created_at`, `updated_at`) VALUES
(1, 1, 1, 1000, 100000, NULL, NULL),
(2, 2, 2, 2000, 200000, NULL, NULL),
(3, 3, 3, 3000, 300000, NULL, NULL),
(4, 4, 4, 4000, 400000, NULL, NULL),
(5, 5, 1, 1000, 100000, NULL, NULL),
(6, 6, 1, 1000, 100000, NULL, NULL),
(7, 7, 1, 1000, 100000, NULL, NULL),
(8, 8, 2, 2000, 200000, NULL, NULL),
(9, 9, 3, 3000, 300000, NULL, NULL),
(10, 10, 2, 2000, 200000, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `linh_vuc`
--

DROP TABLE IF EXISTS `linh_vuc`;
CREATE TABLE IF NOT EXISTS `linh_vuc` (
  `linh_vuc_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ten_linh_vuc` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`linh_vuc_id`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `linh_vuc`
--

INSERT INTO `linh_vuc` (`linh_vuc_id`, `ten_linh_vuc`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'Âm nhạc - Phim', NULL, NULL, NULL),
(2, 'Thể thao', NULL, NULL, NULL),
(3, 'Lịch sử', NULL, NULL, NULL),
(4, 'Địa lý', NULL, NULL, NULL),
(5, 'Văn học', NULL, NULL, NULL),
(6, 'Y học', NULL, NULL, NULL),
(7, 'Văn hóa sự kiện', NULL, NULL, NULL),
(8, 'Khoa học tự nhiên', NULL, NULL, NULL),
(29, 'Toán học', '2019-12-11 20:41:15', '2019-12-11 20:41:26', '2019-12-11 20:41:26'),
(24, 'Bóng đá', '2019-12-11 05:52:53', '2019-12-11 05:53:03', '2019-12-11 05:53:03'),
(26, 'kế toán', '2019-12-11 20:29:42', '2019-12-19 06:18:53', '2019-12-19 06:18:53');

-- --------------------------------------------------------

--
-- Table structure for table `luot_choi`
--

DROP TABLE IF EXISTS `luot_choi`;
CREATE TABLE IF NOT EXISTS `luot_choi` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nguoi_choi_id` int(10) UNSIGNED NOT NULL,
  `so_cau` int(11) NOT NULL,
  `diem` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ngay_gio` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `luot_choi_nguoi_choi_id_foreign` (`nguoi_choi_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `luot_choi`
--

INSERT INTO `luot_choi` (`id`, `nguoi_choi_id`, `so_cau`, `diem`, `ngay_gio`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 1, 5, '5000', '27/04/2019', NULL, NULL, NULL),
(2, 2, 10, '10000', '30/05/2019', NULL, NULL, NULL),
(3, 3, 6, '6000', '12/11/2019', NULL, NULL, NULL),
(4, 4, 11, '11000', '13/11/2019', NULL, NULL, NULL),
(5, 5, 8, '8000', '10/10/2019', NULL, NULL, NULL),
(6, 8, 3, '3000', '15/10/2019', NULL, NULL, NULL),
(7, 2, 9, '6000', '28/04/2019', NULL, NULL, NULL),
(8, 3, 9, '10000', '31/05/2019', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

DROP TABLE IF EXISTS `migrations`;
CREATE TABLE IF NOT EXISTS `migrations` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `migration` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(14, '2014_10_12_000000_create_users_table', 1),
(15, '2014_10_12_100000_create_password_resets_table', 1),
(16, '2019_10_14_130848_create_linh_vucs_table', 2),
(17, '2019_10_14_130903_create_cau_hois_table', 2),
(18, '2019_10_23_143642_create_nguoi_chois_table', 2),
(19, '2019_11_05_132144_create_goi_credits_table', 2),
(20, '2019_11_09_141457_create_lich_su_mua_credits_table', 2),
(21, '2019_11_15_092904_create_luot_chois_table', 2),
(22, '2019_11_26_132152_create_quan_tri_viens_table', 3),
(23, '2019_12_14_093001_create_chi_tiet_luot_chois_table', 4);

-- --------------------------------------------------------

--
-- Table structure for table `nguoi_choi`
--

DROP TABLE IF EXISTS `nguoi_choi`;
CREATE TABLE IF NOT EXISTS `nguoi_choi` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ten_dang_nhap` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mat_khau` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `hinh_dai_dien` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `diem_cao_nhat` int(11) NOT NULL,
  `credit` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `remember_token` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `nguoi_choi`
--

INSERT INTO `nguoi_choi` (`id`, `ten_dang_nhap`, `mat_khau`, `email`, `hinh_dai_dien`, `diem_cao_nhat`, `credit`, `created_at`, `updated_at`, `deleted_at`, `remember_token`) VALUES
(1, 'WQ7sCp6o', 'ghQwGY', 'WQ7sCp6o@gmail.com', 'WQ7sCp6o.jpg', 2336, 16, '2019-11-15 02:55:45', '2019-11-15 02:55:45', NULL, NULL),
(2, 'Jw8UaoGV', 'nciMBW', 'Jw8UaoGV@gmail.com', 'Jw8UaoGV.jpg', 3419, 227, '2019-11-15 02:55:45', '2019-11-15 02:55:45', NULL, NULL),
(3, '3aY1j27H', 'rIo2aF', '3aY1j27H@gmail.com', '3aY1j27H.jpg', 2615, 111, '2019-11-15 02:55:45', '2019-11-15 02:55:45', NULL, NULL),
(4, 'Tb1Q660A', 'A3TftG', 'Tb1Q660A@gmail.com', 'Tb1Q660A.jpg', 3297, 458, '2019-11-15 02:55:45', '2019-11-15 02:55:45', NULL, NULL),
(5, 'ngyQNjWw', 'Tf3mW6', 'ngyQNjWw@gmail.com', 'ngyQNjWw.jpg', 1789, 488, '2019-11-15 02:55:45', '2019-11-15 02:55:45', NULL, NULL),
(6, 'rEdMmRko', 'rMbx4i', 'rEdMmRko@gmail.com', 'rEdMmRko.jpg', 4850, 271, '2019-11-15 02:55:45', '2019-11-15 02:55:45', NULL, NULL),
(7, 'U40r4QfD', 'pvGfga', 'U40r4QfD@gmail.com', 'U40r4QfD.jpg', 2422, 45, '2019-11-15 02:55:45', '2019-11-15 02:55:45', NULL, NULL),
(8, 'A7qK6uWd', '5YgkEv', 'A7qK6uWd@gmail.com', 'A7qK6uWd.jpg', 2741, 134, '2019-11-15 02:55:45', '2019-11-15 02:55:45', NULL, NULL),
(9, 'qdNsjgsx', 'K1cosl', 'qdNsjgsx@gmail.com', 'qdNsjgsx.jpg', 2186, 266, '2019-11-15 02:55:45', '2019-11-15 02:55:45', NULL, NULL),
(10, 'ifhz2rZH', 'KRoHFe', 'ifhz2rZH@gmail.com', 'ifhz2rZH.jpg', 1689, 105, '2019-11-15 02:55:45', '2019-11-15 02:55:45', NULL, NULL),
(12, 'nhoxking500', '$2y$10$JbP/SjVFLojaM2jgsKieKOJAhZpaJ1MlOOcQWa8iKW3d77lpXfn7O', 'nhoxking500@gmail.com', 'nhoxking500.jpg', 4873, 432, '2019-12-26 02:15:25', '2019-12-26 02:15:25', NULL, NULL),
(13, 'CapooCat', '$2y$10$nheNQNOFQCSzwzz72YpC3uIn1eaFykXhGj1noRi2hON6IdVWoFG6C', 'tranthaitat99@gmail.com', '', 1800, 19000, '2019-12-27 20:17:32', '2019-12-28 07:04:41', NULL, NULL),
(14, 'Anti', '$2y$10$v4s0A.nGPSJyRpNUSIi3LO4dCx2Mm9eQtXv0.GzCwEMljjAOt56zq', 'tran@gmail.com', '', 1200, 18000, '2019-12-27 23:43:02', '2019-12-28 07:08:19', NULL, NULL),
(15, 'abc', '$2y$10$Lc8UvANfBiaWTzqQE0Ofqer.5eCVTkjncP9c09p0CaTADurSR.ekO', 'abc@gmail.com', '', 0, -1000, '2019-12-27 23:46:14', '2019-12-28 07:05:18', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `password_resets`
--

DROP TABLE IF EXISTS `password_resets`;
CREATE TABLE IF NOT EXISTS `password_resets` (
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  KEY `password_resets_email_index` (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `quan_tri_vien`
--

DROP TABLE IF EXISTS `quan_tri_vien`;
CREATE TABLE IF NOT EXISTS `quan_tri_vien` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ten_dang_nhap` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mat_khau` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ho_ten` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `remember_token` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `quan_tri_vien`
--

INSERT INTO `quan_tri_vien` (`id`, `ten_dang_nhap`, `mat_khau`, `ho_ten`, `deleted_at`, `created_at`, `updated_at`, `remember_token`) VALUES
(1, 'nhoxking500@gmail.com', '$2y$10$AKz62l8Ukk8.bC0ssjMDnOr6c7RWN2qQMHcqAKcNlZlNEMCsD0bFi', 'Duc Tran', NULL, NULL, NULL, NULL),
(2, 'nhoxking1', '$2y$10$P6wf4HwTDZ3XJsbtVV3sB.FAbj3Igf9KKV25y3cESyKSfguOce/zy', 'Tran Huy', NULL, NULL, NULL, NULL),
(3, 'nhoxking2', '$2y$10$cj67.1/lQautjPN5uPVxzOVtTrvd3d/y3GwBlyzB9E8/MkoKws5QK', 'Nam Huy', NULL, NULL, NULL, NULL),
(4, 'nhoxking3', '$2y$10$vPtPNzod/WVC5r7N8YmcSuj9adx5EujMrLO8rqTRfYF3W/Quy56Ru', 'Văn Đức', NULL, NULL, NULL, NULL),
(5, 'nhoxking@gmail.com', '$2y$10$FXNuSVuc962mdhV/cnFgfuon0YrZ9gDbbiXNrCbaAOD6E1AJXHwv2', 'TranHuyDuc', NULL, NULL, NULL, 'kOXIAvA8g0jdRyMKSLqspMuvGwJ9DgcIGj6dmlFOgNdpoMxYBB4OS1RmoB6M'),
(6, 'huyduc@gmail.com', '$2y$10$K4cZQj37bFd0zbr4kpqaXuinrs8doGPqUg/SXQ82ufqKPWdoFJnMe', 'TranHuyDuc', NULL, NULL, NULL, 'z27WGotYo4mlwOCiZQLSMQ2JQZtTEoH93QrhdmbuZfjbKEvDefMWkq6LoVzj'),
(7, 'vuhuong.09102@gmail.com', '$2y$10$5SEcPjd2gCt6dMJxj6u9P.GWwaOd1BJHlMN25HXiQw2tsJIFR9tem', 'Hương Vũ', NULL, '2019-12-08 18:13:22', '2019-12-08 18:13:22', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_email_unique` (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
