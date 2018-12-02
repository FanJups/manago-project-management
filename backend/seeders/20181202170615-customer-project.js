'use strict';

module.exports = {
  up: (queryInterface, Sequelize) => {
      return queryInterface.bulkInsert('CustomerProject', [
          {projectName: "MyProject1", customerId: 1},
          {projectName: "My Another Project", customerId: 1},
          {projectName: "My Another Project", customerId: 2}
      ]);
  },

  down: (queryInterface, Sequelize) => {
      return queryInterface.bulkDelete('CustomerProject', null, {});
  }
};
