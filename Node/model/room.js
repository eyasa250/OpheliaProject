const { Sequelize, DataTypes } = require('sequelize');
const sequelize = require('../config/db'); // Import the sequelize instance from the configuration

// Define the Room model
const Room = sequelize.define('room', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    nom: {
        type: DataTypes.STRING,
        allowNull: false
    }
}, {
    timestamps: false // Disable createdAt and updatedAt fields
});

// Sync the model with the database
sequelize.sync({ force: false })
    .then(() => {
        console.log('Room model synchronized with database.');
    })
    .catch(error => {
        console.error('Error synchronizing Room model:', error);
    });

module.exports = Room;
