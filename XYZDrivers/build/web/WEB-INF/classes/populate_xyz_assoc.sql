INSERT INTO claims VALUES
(1, 'me-aydin', '2016-04-16', 'change mirror', 'APPROVED', 120),
(2, 'me-aydin', '2016-09-08', 'repair scratch', 'APPROVED', 90),
(3, 'e-simons', '2016-10-10', 'polishing tyers', 'APPROVED', 75);

INSERT INTO members VALUES
('e-simons', 'Edmond Simons', '123 Kings Street, Aberdeen, AB12 2AB', '1965-11-22', '2015-01-03', 'APPROVED', 0),
('m-malcolm ', 'Mary Malcolm', '3 London Road, Luton, LU1 1QY', '1990-08-08', '2015-01-17', 'APPROVED', 0),
('me-aydin', 'Mehmet Aydin', '29 Station Rd, London, N3 2SG', '1968-10-20', '2015-01-26', 'APPROVED', 0),
('r-french', 'Rob French', '13 Stafford Street, Aberdeen, AB12 1AQ', '1968-12-21', '2015-01-27', 'APPROVED', 0),
('m-wood ', 'Mike Wood', '10 London Avenue, Luton, LU12 3SB', '1982-08-18', '2015-10-03', 'APPROVED', 0),
('e-aydin', 'Emin Aydin', '148 Station Rd, London, N3 2SG', '1968-10-10', '2015-10-09', 'APPLIED', 0),
('mem-3', 'Member Three', 'Address Three', '1998-03-02', '2016-01-17', 'APPROVED', 0),
('mem-4', 'Member Four', 'Address Four', '1987-07-08', '2016-04-10', 'APPROVED', 0),
('mem-5', 'Member Five', 'Address Five', '1988-03-02', '2016-06-13', 'APPROVED', 0),
('mem-6', 'Member Six', 'Address Six', '1997-07-08', '2016-10-09', 'APPROVED', 0);

INSERT INTO payments  VALUES
(1, 'e-simons', 'FEE', 10, '2015-01-07','10:08:21'),
(2, 'm-malcolm', 'FEE', 10, '2015-01-24','11:28:25'),
(3, 'me-aydin', 'FEE', 10, '2015-01-26','18:00:00'),
(4, 'r-french', 'FEE', 10, '2015-01-28','09:12:00'),
(5, 'm-wood', 'FEE', 10, '2015-10-25','08:44:13'),
(6, 'e-aydin', 'FEE', 10, '2015-10-26','10:08:21'),
(7, 'e-simons', 'FEE', 10, '2016-01-25','11:00:00'),
(8, 'm-malcolm', 'FEE', 10, '2016-01-25','11:18:21'),
(9, 'me-aydin', 'FEE', 10, '2016-02-05','16:38:13'),
(10, 'm-wood', 'FEE', 10, '2016-10-12','09:44:18'),
(11, 'e-aydin', 'FEE', 10, '2016-10-20','14:42:45'),
(12, 'mem-3', 'FEE', 10, '2016-01-23','01:01:01'),
(13, 'mem-4', 'FEE', 10, '2016-05-16','11:13:11'),
(14, 'mem-5', 'FEE', 10, '2016-06-13','00:00:00'),
(15, 'mem-6', 'FEE', 10, '2016-11-06','07:13:00');

INSERT INTO users  VALUES
('admin', 'admin', 'ADMIN'),
('e-simons', '221165', 'APPROVED'),
('m-malcolm', '080890', 'APPROVED'),
('me-aydin', '201068', 'APPROVED'),
('mem-3', '020398', 'APPROVED'),
('mem-4', '070887', 'APPROVED'),
('r-french', '211268', 'APPROVED'),
('m-wood', '180882', 'APPROVED'),
('e-aydin', '101068', 'APPROVED'),
('mem-5', '020388', 'APPROVED'),
('mem-6', '070897', 'APPROVED');
