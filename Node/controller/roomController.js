// roomController.js

const { room } = require('../model'); // Import the Chambre model

// Get all rooms
exports.getAllRooms = async (req, res) => {

  try {
    //res.send('This is the rooms route');

    const rooms = await room.findAll();
    res.json(rooms);
  } catch (error) {
    console.error(error);
    res.send('there is an error');
  }

};

// Get a single room by ID
exports.getRoomById = async (req, res) => {
  const { id } = req.params;
  try {
    const room = await room.findByPk(id);
    if (!room) {
      return res.status(404).json({ message: "Room not found" });
    }
    res.json(room);
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: "Server Error" });
  }
};

// Create a new room
exports.createRoom = async (req, res) => {
  const { nom } = req.body;
  try {
    const newRoom = await room.create({ nom });
    res.status(201).json(newRoom);
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: "Server Error" });
  }
};

// Update an existing room
exports.updateRoom = async (req, res) => {
  const { id } = req.params;
  const { nom } = req.body;
  try {
    const room = await room.findByPk(id);
    if (!room) {
      return res.status(404).json({ message: "Room not found" });
    }
    room.nom = nom;
    await room.save();
    res.json(room);
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: "Server Error" });
  }
};

// Delete an existing room
exports.deleteRoom = async (req, res) => {
  const { id } = req.params;
  try {
    const room = await room.findByPk(id);
    if (!room) {
      return res.status(404).json({ message: "Room not found" });
    }
    await room.destroy();
    res.status(204).end();
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: "Server Error" });
  }
};
