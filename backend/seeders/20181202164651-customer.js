'use strict';

module.exports = {
  up: (queryInterface, Sequelize) => {
    return queryInterface.bulkInsert('Customer', [
        { firstName: "John", lastName: "Doe", email: "a@a.a", company: "Intel", address: "11 Main Street", city: "Bigtown", zipCode: "12345" },
        { firstName: "Jane", lastName: "Jones", email: "a@a.a", company: "Intel", address: "22 Yellow Street", city: "Smalltown", zipCode: "54321" }
        ]);
  },

  down: (queryInterface, Sequelize) => {
    return queryInterface.bulkDelete('Customer', null, {});
  }
};
