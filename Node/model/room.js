const mongoose = require('mongoose');
const TASK_NAMES = require('../config/tasks'); // Adjust path as necessary
const roomSchema = new mongoose.Schema({
  nom: { type: String, required: true },
 tasks: [{ type: String, enum: TASK_NAMES }] // Ensure only tasks from the predefined list can be added
});

const Room = mongoose.model('Room', roomSchema);

module.exports = Room;

// Example usage:
/*
let myRoom = new Room({nom : "Tuto" , tasks : ["Cleaning", "Vacuuming"] });
myRoom.save((err) =>{if(err) console.log(err)});
console.log(myRoom); */

// To retrieve a document by its ID:
/*
Room.findById("587a9eb146b2f03e3cdddbf5", (err, doc)=>{
  if(!doc){ return res.send('No such user!')}
  else{
    console.log("The user's email is %s", doc.email);
    return res.json(doc.tasks);
  }
})*/

// To get all documents in a collection:
/*
Room.find({}, 'tasks', function (err, docs) {
  console.log(docs);