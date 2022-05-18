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
    
}
module.exports = Function;