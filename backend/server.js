const db = require('./models/index');

const express = require('express');
const bodyParser = require('body-parser');
const PORT = 8080;
const HOST = '0.0.0.0';


const app = express();
app.use(bodyParser.json());

const testCreate = () => {
    db.customer.create({
        firstName: "Test",
        lastName: "last name",
        email: "a@a.a"
    })
        .then(customer => console.log(customer))
        .catch(err => console.log(err))
};

const testGet = () => {
    db.customer.findAll({
        include: [db.project]
    }).then(data => {
        console.log(data);
    })
}
//app.use(require('./routes'));


app.listen(PORT, HOST);
console.log(`Running on http://${HOST}:${PORT}`);
console.log(db.environment);
db.sequelize.authenticate()
    .then(() => {
        console.log("Successfully connected");
        testGet();
    })
    .catch(err => console.log(err));
