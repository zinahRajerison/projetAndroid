var sha256=require('js-sha256')
var helper=require('./Helper.js')

class Function{
    seLogger =function(name,mdp){
        return new Promise(function(resolve,reject){
            new helper().seConnecter().then(function(db){
                console.log(db);
                // var nvmdp=sha256(mdp)
                var query = {"nom_user":name,"mdp_user":mdp}
                console.log(query)
                db.collection('User').findOne(query)
                .then(result => {
                    console.log(result)
                    if(result==null){
                        reject("pas de compte");
                    }
                    else{
                        resolve(result)
                    }
                })
                .catch(error => console.error(error))
            }).catch(
                error => console.log("Connexion base de donnee echouee")
            )
        })
    }
    rechercherAnimal =function(arechercher){
        return new Promise(function(resolve,reject){
            new helper().seConnecter().then(function(db){
                var query = { nom_animal: { $regex: new RegExp(arechercher.name,'i')},id_categorie: Number(arechercher.id_categorie) ,id_pays: Number(arechercher.id_pays) } 
                console.log(query)
                db.collection('animal').find(query) .toArray()
                .then(result => {
                    resolve(result)
                })
                .catch(error => console.error(error))
            }).catch(
                error => console.log("Connexion base de donnee echouee")
            )
        })
    }
    
    findAll =function(table){
        return new Promise(function(resolve,reject){
            new helper().seConnecter().then(function(db){
                db.collection(table).find().toArray()
                .then(results => {
                    resolve(results);
                })
                .catch(reject(error))
            }).catch(
                error => console.log("Connexion base de donnee echouee")
            )
        })
        
    }
    getFavoris =function(table){
        return new Promise(function(resolve,reject){
            new helper().seConnecter().then(function(db){
                db.collection(table).find().toArray()
                .then(results => {
                    resolve(results);
                })
                .catch(error => console.error(error))
            }).catch(
                error => console.log("Connexion base de donnee echouee")
            )
        })
    }

    signup =function(name,mdp){
        return new Promise(function(resolve,reject){
            new helper().seConnecter().then(function(db){
                console.log(db);
                // var nvmdp=sha256(mdp)
                db.collection('User').find().limit(1).sort({$natural:-1}).toArray().then(result => {
                    var query = {"_id":result[0]._id + 1,"nom_user":name,"mdp_user":mdp}
                    console.log(query)
                    db.collection('User').insertOne(query)
                    .then(result => {
                        console.log(result)
                        resolve(true)
                    })
                    .catch(error => console.error(error))
                })
                .catch(error => console.error(error))
            }).catch(
                error => console.log("Connexion base de donnee echouee")
            )
        })
    }

    findById =function(id){
        return new Promise(function(resolve,reject){
            new helper().seConnecter().then(function(db){
                console.log(db);
                var query = {"_id":(parseInt(id))}
                console.log(query)
                db.collection('animal').findOne(query)
                .then(result => {
                    console.log(result)
                    if(result==null){
                        reject("pas d'animal");
                    }
                    else{
                        resolve(result)
                    }
                })
                .catch(error => console.error(error))
            }).catch(
                error => console.log("Connexion base de donnee echouee")
            )
        })
    }

    ajoutFavoris =function(id_animal, id_user){
        return new Promise(function(resolve,reject){
            new helper().seConnecter().then(function(db){
                console.log(db);
                db.collection('favoris').find().limit(1).sort({$natural:-1}).toArray().then(result => {
                    var id = 0;
                    if(result.length > 0){
                        id = result[0]._id;
                    }
                    var query = {"_id":id + 1,"id_user":parseInt(id_user),"id_animal":parseInt(id_animal)}
                    console.log(query)
                    db.collection('favoris').insertOne(query)
                    .then(result => {
                        console.log(result)
                        resolve(true)
                    })
                    .catch(error => console.error(error))
                })
                .catch(error => console.error(error))
            }).catch(
                error => console.log("Connexion base de donnee echouee")
            )
        });
    }
    
}
module.exports = Function;