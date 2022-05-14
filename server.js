const express = require('express');
const app = express();
var user = require('./src/User.js')
const port = process.env.PORT || 3000;

app.listen(port);

app.get('/', function (req, res) {
  res.send('Hello World');
});
app.post('/login',function(req,res){
  // var customer=new client(req.body.mail,req.body.mdp)
  // console.log(customer.mail)
      new user(res.body.name,res.body.pwd).seLogger().then(function(user){
          res.send(new reponse(200,"Connected",user))
      }).catch(function(error){
          res.send(new reponse(400,error,null))
      })
});
