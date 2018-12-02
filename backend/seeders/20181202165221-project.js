'use strict';

module.exports = {
  up: (queryInterface, Sequelize) => {
      return queryInterface.bulkInsert('Project', [
          {name: "MyProject1", description: "Dummy test description"},
          {name: "My Another Project", description: "An exciting project"}
          ]);
  },

  down: (queryInterface, Sequelize) => {
      return queryInterface.bulkDelete('Project', null, {});
  }
};
