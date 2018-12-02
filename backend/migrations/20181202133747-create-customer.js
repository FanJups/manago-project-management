'use strict';
module.exports = {
  up: (queryInterface, Sequelize) => {
    return queryInterface.sequelize.query(
        'CREATE TABLE Customers (' +
        'customerId INT AUTO_INCREMENT, ' +
        'firstName VARCHAR(100) NOT NULL, ' +
        'lastName VARCHAR(100) NOT NULL, ' +
        'email VARCHAR(70) NOT NULL, ' +
        'company VARCHAR(50), ' +
        'address VARCHAR(100), ' +
        'city VARCHAR(50), ' +
        'zipCode VARCHAR(10), ' +
        'PRIMARY KEY (customerId)) ENGINE=INNODB;');
  },
  down: (queryInterface, Sequelize) => {
    return queryInterface.sequelize.query('DROP TABLE IF EXISTS Customer;');
  }
};
