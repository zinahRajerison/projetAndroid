var helper=require('./Helper.js')
class Animal{
    constructor(nom_animal, femelle, enfant, image, son, video, id_categorie, id_pays){
        this.nom_animal = nom_animal;
        this.femelle = femelle;
        this.enfant = enfant;
        this.image = image;
        this.son = son;
        this.video = video;
        this.id_categorie = id_categorie;
        this.id_pays = id_pays;
    }
    set id(id) {
        this._id = id
    }
    findById =function(){
        return new Promise(function(resolve,reject){
            new helper().seConnecter().then(function(pool){
                const requete = {
                    text: 'select * animal where id_animal=$1',
                    values: [ this.id ]
                };
                pool.query(requete,(err,res)=>{
                    if(err){
                        console.log("Error : "+ err);
                        reject(err);
                    }
                    else{
                        this.id = res.rows[0].id_animal;
                        this.nom_animal = res.rows[0].nom_animal;
                        this.femelle = res.rows[0].femelle;
                        this.enfant = res.rows[0].enfant;
                        this.image = res.rows[0].image;
                        this.son = res.rows[0].son;
                        this.video = res.rows[0].video;
                        this.id_categorie = res.rows[0].id_categorie;
                        this.id_pays = res.rows[0].id_pays;
                        resolve(null, );
                    };
                });
            }).catch(
                error => console.log("Connexion base de donnee echouee")
            )
        });
    }
}
module.exports=Animal
