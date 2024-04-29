const mongoose = require('mongoose');

const taskSchema = new mongoose.Schema({
  name: {
    type: String,
    required: true,
  },
  
  // Add any other fields you need for a task
});

const Task = mongoose.model('Task', taskSchema);

module.exports = Task;
