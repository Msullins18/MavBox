DROP TABLE Customers;
DROP TABLE Movies;
DROP TABLE Games;
DROP TABLE Rentals;


CREATE TABLE Customers (
   customerName varchar(255) NOT NULL,
   customerID integer NOT NULL,
   creditCardNumber varchar(255) NOT NULL
);

CREATE TABLE Movies (
   title varchar(255) NOT NULL,
   rentalCode varchar(255) NOT NULL,
   price float NOT NULL,
   rentalType varchar(255) NOT NULL,
   isAvailable boolean NOT NULL
);

CREATE TABLE Games (
   title varchar(255) NOT NULL,
   rentalCode varchar(255) NOT NULL,
   price float NOT NULL,
   rentalType varchar(255) NOT NULL,
   isAvailable boolean NOT NULL
);

CREATE TABLE Rentals (
   customerName varchar(255) NOT NULL,
   rentalCode varchar(255) NOT NULL
);

INSERT INTO Movies (title,rentalCode,price,rentalType,isAvailable)
VALUES
   ('Titanic','1AD',4.99,'DVD',TRUE),
   ('Sixth Sense','4DB',3.25,'BLURAY',TRUE),
   ('Avengers','9Y',1.59,'DVD',TRUE),
   ('Sixth Sense','4D',3.25,'DVD',TRUE),
   ('Raiders Of The Lost Ark','12T',1.79,'BLURAY',TRUE),
   ('Split','8R',2.59,'BLURAY',TRUE),
   ('Titanic','1A',4.99,'BLURAY',TRUE),
   ('Star Wars','2C',5.99,'DVD',TRUE);

INSERT INTO Games (title,rentalCode,price,rentalType,isAvailable)
VALUES
   ('Warcraft','5D',3.99,'PLAYSTATION',TRUE),
   ('Tomb Raider','12F',3.59,'NINTENDO',TRUE),
   ('Call Of Duty','2G',5.99,'XBOX',TRUE),
   ('Lego Jurassic World','9Q',2.25,'PLAYSTATION',TRUE),
   ('Fallout','7W',1.49,'NINTENDO',TRUE);