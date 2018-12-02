'use strict';
const fs = require("fs");
module.exports = {
  up: (queryInterface, Sequelize) => {
    return queryInterface.sequelize.query(fs.readFileSync("raw/migration/customerproject.sql", "utf8"));
  },
  down: (queryInterface, Sequelize) => {
    return queryInterface.sequelize.query('DROP TABLE IF EXISTS CustomerProject;');
  }
};
