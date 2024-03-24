const {  DataTypes } = require('sequelize');
const sequelize = require('../config/db'); // Import the sequelize instance from the configuration

// Define the Task model
const Task = sequelize.define('Task', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    title: {
        type: DataTypes.STRING,
        allowNull: false
    },
    points: {
        type: DataTypes.INTEGER,
        allowNull: false,
        defaultValue: 0 // You can set a default value for points
    }
   
});

// Sync the model with the database
sequelize.sync({ force: false })
    .then(() => {
        console.log('Task model synchronized with database.');
    })
    .catch(error => {
        console.error('Error synchronizing Task model:', error);
    });

module.exports = Task;
