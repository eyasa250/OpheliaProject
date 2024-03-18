const Task = require('../model/task');

// Create a task
exports.createTask = async (req, res) => {
    try {
        const { title, description, dueDate } = req.body;
        const task = await Task.create({ title, description, dueDate });
        res.status(201).json({ message: "Task created successfully", task });
    } catch (error) {
        console.error("Error creating task:", error);
        res.status(500).json({ message: "Server error" });
    }
};

// Get all tasks
exports.getAllTasks = async (req, res) => {
    try {
        const tasks = await Task.findAll();
        res.json({ tasks });
    } catch (error) {
        console.error("Error fetching tasks:", error);
        res.status(500).json({ message: "Server error" });
    }
};

// Get task by ID
exports.getTaskById = async (req, res) => {
    const taskId = req.params.id;
    try {
        const task = await Task.findByPk(taskId);
        if (!task) {
            return res.status(404).json({ message: "Task not found." });
        }
        res.json({ task });
    } catch (error) {
        console.error("Error fetching task:", error);
        res.status(500).json({ message: "Server error" });
    }
};

// Update task by ID
exports.updateTaskById = async (req, res) => {
    const taskId = req.params.id;
    try {
        const { title, description, dueDate, completed } = req.body;
        const updatedTask = await Task.update(
            { title, description, dueDate, completed },
            { where: { id: taskId } }
        );
        if (updatedTask[0] === 0) {
            return res.status(404).json({ message: "Task not found." });
        }
        res.json({ message: "Task updated successfully" });
    } catch (error) {
        console.error("Error updating task:", error);
        res.status(500).json({ message: "Server error" });
    }
};

// Delete task by ID
exports.deleteTaskById = async (req, res) => {
    const taskId = req.params.id;
    try {
        const deletedTaskCount = await Task.destroy({ where: { id: taskId } });
        if (deletedTaskCount === 0) {
            return res.status(404).json({ message: "Task not found." });
        }
        res.json({ message: "Task deleted successfully" });
    } catch (error) {
        console.error("Error deleting task:", error);
        res.status(500).json({ message: "Server error" });
    }
};
