const { Sequelize, DataTypes } = require('sequelize');
const sequelize = require('../config/db'); // Import the sequelize instance from the configuration

// Define the User model
const User = sequelize.define('User', {
    // Define your user schema here
    username: {
        type: DataTypes.STRING,
        allowNull: false
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
});

// Sync the model with the database
sequelize.sync({ force: false })
    .then(() => {
        console.log('User model synchronized with database.');
    })
    .catch(error => {
        console.error('Error synchronizing User model:', error);
    });
    module.exports = {
        user: User
    };
