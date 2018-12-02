CREATE TABLE IF NOT EXISTS Customer (
    customerId INT AUTO_INCREMENT,
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    email VARCHAR(70) NOT NULL,
    company VARCHAR(50),
    address VARCHAR(100),
    city VARCHAR(50),
    zipCode VARCHAR(10),
    PRIMARY KEY (customerId)
) ENGINE=INNODB;
