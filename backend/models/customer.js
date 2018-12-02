'use strict';
module.exports = (sequelize, DataTypes) => {
  const Customer = sequelize.define('Customer', {
    customerId: {
      type: DataTypes.INTEGER, primaryKey: true
    },
    firstName: {
      type: DataTypes.STRING
    },
    lastName: DataTypes.STRING,
    email: DataTypes.STRING,
    company: DataTypes.STRING,
    address: DataTypes.STRING,
    city: DataTypes.STRING,
    zipCode: DataTypes.STRING
  }, {
    timestamps: false
  });
  Customer.associate = function(models) {
    // associations can be defined here
  };
  return Customer;
};
