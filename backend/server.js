const config = require('./config/env');
const db = require('./config/db/db-config');

const express = require('express');
const PORT = 8080;
const HOST = '0.0.0.0';


const app = express();
app.get('/', (req, res) => {
  res.send('Hello world\n');
});


app.listen(PORT, HOST);
console.log(`Running on http://${HOST}:${PORT}`);
console.log(config.config);
db.sequelize.authenticate().then(() => console.log("Successfully connected")).catch(err => console.log(err));