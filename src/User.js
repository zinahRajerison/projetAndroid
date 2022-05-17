var sha256=require('js-sha256')
var helper=require('./Helper.js')
class User{
    constructor(name,pwd){
        this.name=name;
        this.pwd=pwd;
    }
    set id(id) {
        this._id = id
    }
    seLogger =function(){
        return new Promise(function(resolve,reject){
            new helper().seConnecter().then(function(pool){
                const requete = {
                    text: 'select * customer where nom_user=$1 and mdp_user=$2',
                    values: [ this.name,sha256(this.pwd) ]
                };
                pool.query(requete,(err,res)=>{
                    if(err){
                        console.log("Error : "+ err);
                        reject(err);
                    }
                    else{
                        this.id = res.rows[0].id_user;
                        resolve(null, );
                    };
                });
            }).catch(
                error => console.log("Connexion base de donnee echouee")
            )
        });
    }
    signup = function(){
        return new Promise(function(resolve,reject){
            new helper().seConnecter().then(function(pool){
                console.log(User.this.name);
                const requete = {
                    text: 'insert into customer values ($1, $2)',
                    values: [ this.name, sha256(this.pwd) ]
                };
                pool.query(requete,(err,res)=>{
                    if(err){
                        console.log("Error : "+ err);
                        reject(err);
                    }
                    else{
                        this.id = res.rows[0].id_user;
                        resolve(null, );
                    };
                });
            }).catch(
                error => console.log(error)
            )
        });
    }

    
}
module.exports=User
