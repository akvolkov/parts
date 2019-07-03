CREATE DATABASE parts

CREATE TABLE parts
(
    Id SERIAL PRIMARY KEY,
    PartName CHARACTER VARYING(30) NOT NULL,
    PartNumber CHARACTER VARYING(30) NOT NULL,
	Vendor CHARACTER VARYING(30) NOT NULL,
	Qty INTEGER NOT NULL,
	Shipped Date,
	Receive Date
);

INSERT INTO Parts (PartName, PartNumber, Vendor, qty, shipped, receive) 
VALUES 
	('blade1', 'bl-000001', 'stainless steel Corporation', 10, 'Jan-08-2019', 'Jan-30-2019'),
	('blade2', 'bl-000002', 'stainless steel Corporation', 5, 'Feb-13-2019', 'Feb-20-2019'),
	('knife1', 'kn-000001', 'stainless steel Corporation', 25, 'Jan-08-2019', 'Feb-20-2019'),
	('knife2', 'kn-000002', 'stainless steel Corporation', 27, 'Feb-01-2018', 'Feb-10-2018'),
	('fork1', 'frk-000001', 'stainless steel Corporation', 76, 'Mar-23-2019', 'Mar-31-2019'),
	('fork2', 'frk-000002', 'stainless steel Corporation', 136, 'Mar-08-2019', 'Apr-1-2019'),
	('fork3', 'frk-000003', 'stainless steel Corporation', 54, 'Apr-23-2017', 'May-20-2017'),
	('spoon1', 'sp-000001', 'Pupkin`s spoons Corporation', 345, 'May-02-2019', 'May-29-2019'),	
	('spoon4', 'sp-000004', 'Pupkin`s spoons Corporation', 456, 'May-02-2018', 'May-29-2019'),
	('sword_long', 'sw-000012', 'Vasya Corporation', 13, 'Dec-31-2018', 'Jan-10-2019'),
	('sword_medium', 'sw-000021', 'Vasya Corporation', 9, 'Dec-31-2018', 'Jan-10-2019'),
	('sword_short', 'sw-000035', 'Vasya Corporation', 24, 'Dec-10-2017', 'Feb-28-2019');
	
INSERT INTO Parts (PartName, PartNumber, Vendor, qty) 
VALUES 
	('spoon3', 'sp-000003', 'Pupkin`s spoons Corporation', 123);
	
INSERT INTO Parts (PartName, PartNumber, Vendor, qty, shipped) 
VALUES 
	('spoon2', 'sp-000002', 'Pupkin`s spoons Corporation', 234, 'Jun-10-2019')