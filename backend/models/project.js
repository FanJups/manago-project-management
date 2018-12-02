'use strict';
module.exports = (sequelize, DataTypes) => {
    const Project = sequelize.define('Project', {
        name: {
            type: DataTypes.STRING, primaryKey: true
        },
        description: {
            type: DataTypes.STRING
        }
    }, {
        timestamps: false, freezeTableName: true
    });
    Project.associate = function(models) {
        // associations can be defined here
    };
    return Project;
};
