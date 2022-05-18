use explorateur;

db.createUser({
    user : "explorateurUser",
    pwd : "123456",
    roles : ["readWrite", "dbAdmin"]
});
db.createCollection("User"); 
db.createCollection("pays");
db.createCollection("categorie");
db.createCollection("animal");
db.createCollection("favoris");

db.User.insertOne({
    "_id":1,"nom_user":"Rakoto","mdp_user":"mdpRakoto"
});
db.User.insertOne({
    "_id":2,"nom_user":"Jean","mdp_user":"mdpJean"
});
db.pays.insertOne({"_id":1,"nom_pays":"Madagascar"});
db.pays.insertOne({"_id":2,"nom_pays":"Etats-Unis"});
db.pays.insertOne({"_id":3,"nom_pays":"Kenya"});

db.categorie.insertOne({"_id":1,"nom_categorie":"sauvage"});
db.categorie.insertOne({"_id":2,"nom_categorie":"domestique"});
db.animal.insertOne({
    "_id":1,
    "nom_animal":"Lion",
    "id_pays":1,
    "id_categorie":1,
    "femelle":"Lionne",
    "enfant":"Lionceau"
});
db.animal.insertOne({
    "_id":2,
    "nom_animal":"Boeuf",
    "id_pays":1,
    "id_categorie":2,
    "femelle":"Vache",
    "enfant":"Veau"
});

///login
db.User.findOne({"nom_user":"Rakoto","mdp_user":"mdpRakoto"});

//findByCriteria(nomAnimal, idcategorie,idPays)
regex

//findAll
db.animal.find().pretty();

///getFavoris
select animal.* from favoris join animal on animal._id = favoris.id_animal join user on user._id= favoris.id_user;

//quizz