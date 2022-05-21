const express = require('express');
var bodyParser = require('body-parser');
const app = express();
var user = require('./src/User.js')
var func = require('./src/Function.js');
var reponse = require('./src/Response.js');
const port = process.env.PORT || 3000;

app.listen(port);

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

app.get('/', function (req, res) {
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
  new func().signup(req.body.name, req.body.pwd).then(function(verif){
    res.send(new reponse(200,"Registered",verif))
  }).catch(function(error){
    res.send(new reponse(400,error,null))
  })
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

app.get('/findAllCategory',function(req,res){
  var fonc=new func();
  console.log(req.body)
  fonc.findAll('categorie').then(function(results){
  var toRespond =new reponse(200,"Data gotten successfully",results);
  res.send(toRespond);
  }).catch( function(error){
      var toRespond =new reponse(400,error,null);
      res.send(toRespond)
  })
});

app.get('/findAllPays',function(req,res){
  var fonc=new func();
  console.log(req.body)
  fonc.findAll('pays').then(function(results){
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
app.post('/findById',function(req,res){
  new func().findById(req.body.id, req.body.iduser).then(function(results){
  var toRespond = new reponse(200,"Data gotten successfully",results);
  res.send(toRespond);
  }).catch( function(error){
      var toRespond =new reponse(400,error,null);
      res.send(toRespond)
  })
});
app.post('/ajoutFavoris', function(req, res){
  new func().ajoutFavoris(req.body.idanimal, req.body.iduser).then(function(verif){
    res.send(new reponse(200,"Favorite saved",verif))
  }).catch(function(error){
    res.send(new reponse(400,error,null))
  })
});