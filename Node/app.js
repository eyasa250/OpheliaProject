const express = require('express');
const bodyParser = require('body-parser');

const app = express();
app.use(bodyParser.json());

//models
const Chambre = require('./model/room'); 

//routers
const roomRouter = require('./routes/roomrouter');
app.use('/rooms', roomRouter);


app.use(bodyParser.json());


const port = process.env.PORT || 8080

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
