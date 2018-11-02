const db = require('./models/index');

const express = require('express');
const bodyParser = require('body-parser');
const PORT = 8080;
const HOST = '0.0.0.0';


const app = express();
app.use(bodyParser.json());


app.get('/', (req, res) => {
    res.send('Hello world\n');
});

app.post('/test-post', (req, res) => {
    console.log(req.body);
    const obj = {
        test: "Test"
    };
    res.status(201).send(obj);
});


app.listen(PORT, HOST);
console.log(`Running on http://${HOST}:${PORT}`);
console.log(db.environment);
db.sequelize.authenticate()
    .then(() => console.log("Successfully connected"))
    .catch(err => console.log(err));