'use strict';
module.exports = (sequelize, DataTypes) => {
  const CustomerProject = sequelize.define('CustomerProject', {
    customerId: DataTypes.INTEGER,
    projectName: DataTypes.STRING
  }, {});
  CustomerProject.associate = function(models) {
  };
  return CustomerProject;
};
