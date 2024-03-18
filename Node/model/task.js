const { Sequelize, DataTypes } = require('sequelize');
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
    description: {
        type: DataTypes.TEXT,
        allowNull: true
    },
    dueDate: {
        type: DataTypes.DATE,
        allowNull: true
    },
    completed: {
        type: DataTypes.BOOLEAN,
        allowNull: false,
        defaultValue: false
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
