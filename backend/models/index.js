'use strict';

const fs = require('fs');
const path = require('path');
const Sequelize = require('sequelize');
const basename = path.basename(__filename);
const env = process.env.NODE_ENV || 'development';
const config = require(`${__dirname }/../config/config.json`)[env];
const db = {};

Sequelize.DATE.prototype._stringify = function _stringify(date, options) {
    date = this._applyTimezone(date, options);
    return date.format('YYYY-MM-DD HH:mm:ss.SSS');
};

let sequelize;
sequelize = new Sequelize(config.database, config.username, config.password, config);

fs
    .readdirSync(__dirname)
    .filter((file) => {
        return file.indexOf('.') !== 0 && file !== basename && file.slice(-3) === '.js';
    })
    .forEach((file) => {
        const model = sequelize.import(path.join(__dirname, file));
        db[model.name] = model;
    });

Object.keys(db).forEach((modelName) => {
    if (db[modelName].associate) {
        db[modelName].associate(db);
    }
});


db.customer = require('./customer')(sequelize, Sequelize);
db.project = require('./project')(sequelize, Sequelize);
db.customer.belongsToMany(db.project, {through: "CustomerProject", foreignKey: "customerId", otherKey: "projectName"});
db.project.belongsToMany(db.customer, {through: "CustomerProject", foreignKey: "projectName", otherKey: "customerId"});

db.environment = env;
db.sequelize = sequelize;
db.Sequelize = Sequelize;


module.exports = db;
