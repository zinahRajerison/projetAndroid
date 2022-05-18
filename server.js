const express = require('express');
var bodyParser = require('body-parser');
const app = express();
var user = require('./src/User.js')
var func = require('./src/Function.js');
var reponse = require('./src/Response.js');
const port = process.env.PORT || 3000;

app.listen(3000);

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(function(req, res, next){
    res.setHeader('content-type', 'text/plain');

    // Website you wish to allow to connect
    res.setHeader('Access-Control-Allow-Origin', '*');

    // Request methods you wish to allow
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');

    // Request headers you wish to allow
    res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,content-type');

    // Set to true if you need the website to include cookies in the requests sent
    // to the API (e.g. in case you use sessions)
    res.setHeader('Access-Control-Allow-Credentials', true);
    // res.send(404, 'Page introuvable !');
    next();
});

app.post('/', function (req, res) {
  res.send(new reponse(200,req.body.name,null))
  // res.send("Hello world");
});
app.post('/login',function(req,res){
    new func().seLogger(req.body.name,req.body.pwd).then(function(user){
        res.send(new reponse(200,"Connected",user))
    }).catch(function(error){
        res.send(new reponse(400,error,null))
    })
});

app.post('/signup', function(req, res){
  console.log(req.body.name);
  /*var newUser = new user(req.body.name, req.body.pwd);
  newUser.signup().then(function(err, result){
    if(err){
      console.log(err);
      res.status(400).json({
        status: 'error',
        error: err,
      });
    }else{
      res.status(200).json({
        status: 'success',
        data: result,
      });
    }
  }).catch(function(err){
    console.log("error");
  });*/
});
app.post('/findAnimal',function(req,res){
  new func().rechercherAnimal(req.body).then(function(results){
  var toRespond = new reponse(200,"Data gotten successfully",results);
  res.send(toRespond);
  }).catch( function(error){
      var toRespond =new reponse(400,error,null);
      res.send(toRespond)
  })
});
app.get('/findAll',function(req,res){
  var fonc=new func();
  console.log(req.body)
  fonc.findAll('animal').then(function(results){
  var toRespond =new reponse(200,"Data gotten successfully",results);
  res.send(toRespond);
  }).catch( function(error){
      var toRespond =new reponse(400,error,null);
      res.send(toRespond)
  })
});
app.get('/getFavoris/:id',function(req,res){
  var fonc=new func();
  var idUser = Number(req.params.id);
  console.log(idUser)
  fonc.getFavoris(idUser).then(function(results){
  var toRespond = new reponse(200,"Data gotten successfully",results);
  res.send(toRespond);
  }).catch( function(error){
      var toRespond =new reponse(400,error,null);
      res.send(toRespond)
  })
});
