const express = require('express');
const router = express.Router();
const { associateTasksWithRoom, displayTasksOfRoom } = require('../controller/roomtaskControler'); 
const Task = require ('../model/task');
 const Room = require('../model/room');


// Route to associate tasks with a room
router.post('/:roomId/tasks', async (req, res) => {
  const { roomId } = req.params;
  const { taskIds } = req.body;

  try {
    await associateTasksWithRoom(roomId, taskIds);
    res.status(200).send('Tasks associated with room successfully.');
  } catch (error) {
    console.error('Error:', error);
    res.status(500).send('Error associating tasks with room.');
  }
});



router.get('/:roomId/display', async (req, res) => {
    const { roomId } = req.params;
  
    try {
      // Fetch tasks associated with the room ID
      const tasks = await Task.findAll({
        where: { roomId },
        attributes: ['id', 'title', 'description']
      });
  
      // Format the tasks array
      const formattedTasks = tasks.map(task => ({
        taskId: task.id,
        title: task.title,
        description: task.description
      }));
  
      // Send the formatted tasks in the response
      res.status(200).json({
        roomId,
        tasks: formattedTasks
      });
    } catch (error) {
      console.error('Error:', error);
      res.status(500).send('Error displaying tasks of room.');
    }
  });

module.exports = router;
