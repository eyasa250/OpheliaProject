const { Sequelize } = require('sequelize');
PORT=5000
// Initialize Sequelize with your MySQL database credentials
const sequelize = new Sequelize('ophelia0', 'root', '', {
  host: 'localhost',
  dialect: 'mysql' // This should be a string
});

// Test the database connection
async function testConnection() {
  try {
    await sequelize.authenticate();
    console.log('Connected to MySQL database successfully.');
  } catch (error) {
    console.error('Error connecting to MySQL database:', error);
  }
}

testConnection();

module.exports = sequelize;
