const config = require('../env');
const Sequelize = require('sequelize');
const sequelize = new Sequelize(config.config.db_name, 'root', 'root', {
    host: config.config.db_host,
    port: config.config.db_port,
    dialect: 'mysql'
});

module.exports = {
    sequelize
}   