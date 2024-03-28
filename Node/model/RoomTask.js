const { DataTypes } = require('sequelize');
const sequelize = require('../config/db');
const Task = sequelize.define("task");
const Room = sequelize.define("room");

const RoomTask = sequelize.define('RoomTask', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    }
});

Room.belongsToMany(Task, { through: RoomTask, as : "room", foreignKey: "room_id"  });
Task.belongsToMany(Room, { through: RoomTask, as : "task", foreignKey: "task_id"  });

module.exports = RoomTask;
