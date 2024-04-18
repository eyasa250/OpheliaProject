const Room = require('../model/room');
const Task = require('../model/task');

// Function to associate tasks with a room
async function associateTasksWithRoom(roomId, taskIds) {
  try {
    const room = await Room.findByPk(roomId);
    if (!room) {
      throw new Error(`Room with ID ${roomId} not found.`);
    }

    if (!Array.isArray(taskIds) || taskIds.length === 0) {
      throw new Error('taskIds must be a non-empty array.');
    }

    const tasks = await Task.findAll({
      where: {
        id: taskIds
      }
    });

    await room.addTasks(tasks);

    console.log(`Tasks associated with Room ID ${roomId} successfully.`);
    return true;
  } catch (error) {
    console.error('Error associating tasks with room:', error);
    throw error;
  }
}

// Function to display tasks associated with a room
async function displayTasksOfRoom(roomId) {
  try {
    const room = await Room.findByPk(roomId, { include: Task });
    if (!room) {
      throw new Error(`Room with ID ${roomId} not found.`);
    }

    console.log(`Tasks associated with Room ID ${roomId}:`);
    room.Tasks.forEach(task => {
      console.log(`- Task ID: ${task.id}, Title: ${task.title}, Description: ${task.description}`);
    });

    return true;
  } catch (error) {
    console.error('Error displaying tasks of room:', error);
    throw error;
  }
}

// Example usage:
const roomId = 1; // ID of the existing room
const existingTaskIds = [1, 2, 3]; // Example existing task IDs

associateTasksWithRoom(roomId, existingTaskIds)
  .then((success) => {
    if (success) {
      console.log('Tasks associated with room successfully.');
      // After associating tasks with room, display them
      displayTasksOfRoom(roomId);
    } else {
      console.log('Failed to associate tasks with room.');
    }
  })
  .catch((error) => {
    console.error('Error:', error);
  });
 exports.displayTasksOfRoom=displayTasksOfRoom;  
  exports.associateTasksWithRoom = associateTasksWithRoom;
