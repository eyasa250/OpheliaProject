const { DataTypes } = require('sequelize');
const sequelize = require('../config/db');
const RoomTask = require('../model/RoomTask');


const Room = sequelize.define('Room', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    nom: {
        type: DataTypes.STRING,
        allowNull: false
    }
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
