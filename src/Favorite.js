var helper=require('./Helper.js')
class Favorite{
    constructor(id_user, id_animal){
        this.id_user = id_user;
        this.id_animal = id_animal;
    }
    save =function(){
        return new Promise(function(resolve,reject){
            new helper().seConnecter().then(function(pool){
                const requete = {
                    text: 'insert into favorite values ($1, $2)',
                    values: [ this.id_user, this.id_animal ]
                };
                pool.query(requete,(err,res)=>{
                    if(err){
                        console.log("Error : "+ err);
                        reject(err);
                    }
                    else{
                        resolve(null, );
                    };
                });
            }).catch(
                error => console.log("Connexion base de donnee echouee")
            )
        });
    }
}
module.exports=Favorite
