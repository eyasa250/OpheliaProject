const express = require('express');
const bodyParser = require('body-parser');

const app = express();
app.use(bodyParser.json());

//models

//routers
const userrouter = require('./routes/userrouter');
const roomRouter = require('./routes/roomrouter');
const taskRouter = require('./routes/taskrouter');

app.use('/user', userrouter);
app.use('/room', roomRouter);
app.use('/task', taskRouter);

app.get("/", (req, res) => {
  res.send("Test route is working");
});

app.use(bodyParser.json());


const port = process.env.PORT || 8090

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
