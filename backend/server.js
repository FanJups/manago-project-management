const db = require('./models/index');

const express = require('express');
const bodyParser = require('body-parser');
const PORT = 8080;
const HOST = '0.0.0.0';


const app = express();
app.use(bodyParser.json());

const testCreate = () => {
    db["Customer"].create({
        firstName: "Test",
        lastName: "last name",
        email: "a@a.a"
    })
        .then(customer => console.log(customer))
        .catch(err => console.log(err))
};
//app.use(require('./routes'));


app.listen(PORT, HOST);
console.log(`Running on http://${HOST}:${PORT}`);
console.log(db.environment);
db.sequelize.authenticate()
    .then(() => {
        console.log("Successfully connected")
        testCreate();
    })
    .catch(err => console.log(err));
