// chambre.js

//const { Sequelize } = require('sequelize');
//const db = require('../config/db'); // Import your MySQL connection
//const sequelize = new Sequelize(db);

// Define the Chambre model

module.exports = (sequelize, DataTypes) => {

const room = sequelize.define('room', {
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
return room}

//module.exports = Chambre;
