const { DataTypes } = require('sequelize');
const sequelize = require('../config/db'); 

const User = sequelize.define('User', {
    username: {
        type: DataTypes.STRING,
        allowNull: false,
        unique: true
    },
    email: {
        type: DataTypes.STRING,
        allowNull: false,
        unique: true
    },
    password: {
        type: DataTypes.STRING,
        allowNull: false
    }
}, {
    timestamps: false // Exclude timestamps (createdAt, updatedAt) during synchronization
});

sequelize.sync({ force: false })
    .then(() => {
        console.log('User model synchronized with database.');
    })
    .catch(error => {
        console.error('Error synchronizing User model:', error);
    });

module.exports = User;
