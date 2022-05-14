const express = require('express');
const app = express();
var user = require('./src/User.js')
var helper = require('./src/Helper.js')
const port = process.env.PORT || 3000;

app.listen(port);

app.get('/', function (req, res) {
  res.send('Hello World');
});
app.get('/login',function(req,res){
      new helper().seConnecter().then(function(pool){
          console.log(pool);
      }).catch(function(err){
        console.log("error");
      })
      // new user(res.body.name,res.body.pwd).seLogger().then(function(user){
      //     res.send(new reponse(200,"Connected",user))
      // }).catch(function(error){
      //     res.send(new reponse(400,error,null))
      // })
});
