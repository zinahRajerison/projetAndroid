const { Pool, Client } = require('pg');
const List = require('./classes/List');
const utils = require('./utils');
const connectionString = 'postgresql://chatbotvoyage:chatbot@localhost:5432/chatbotvoyage';
const pool = new Pool({
  connectionString,
})

function getCity(callback){
    var liste = [];
    var requete= "select * from city";
    
    pool.query(requete,(err,res)=>{
        if(err){
            console.log("Error : "+ err);
            callback(err);
        }
        else{
            var taille = res.rows.length;
            for(var i=0; i<taille; i++){
                console.log("id " + res.rows[i].id);
            }
            callback(null, liste);
        };
    });
}

// Recherche de toutes les villes dans la region
function getCityRegion(nameregion, callback){
    var liste = '';
    const requete = {
        text: 'select * from v_cityregion where name_region=$1',
        values: [ nameregion ],
    };
    
    pool.query(requete,(err,res)=>{
        if(err){
            console.log("Error : "+ err);
            callback(err);
        }
        else{
            var taille = res.rows.length;
            liste = liste + ' ' + res.rows[0].name;
            for(var i=1; i<taille; i++){
                liste = liste + ', ' + res.rows[i].name;
            }
            callback(null, liste);
        };
    });
}


function getRegion(callback){
    var liste = [];
    var requete= "select * from region";
    
    pool.query(requete,(err,res)=>{
        if(err){
            console.log("Error : "+ err);
            callback(err);
        }
        else{
            var taille = res.length;
            for(var i=0; i<taille; i++){
                var city = new List("region", "name", res[i].name);
                liste[i]=city;
                console.log(city.description);
            }
            callback(null, liste);
        };
    });
}

// Recherche du nom de l'infrastructure ou de la ville ou de la region
function getNameList(table, callback){
    var liste = [];
    var requete= "select * from " + table;
    
    pool.query(requete,(err,res)=>{
        if(err){
            console.log("Error : "+ err);
            callback(err);
        }
        else{
            var taille = res.rows.length;
            for(var i=0; i<taille; i++){
                var temp = new List(table, "name", res.rows[i].name);
                liste[i]=temp;
            }
            callback(null, liste);
        };
    });
}

// Hotel
// Recherche de tous les hotels dans la ville
function getCityHotel(namecity, callback){
    var liste = '';
    const requete = {
        text: "select * from v_typeinfracity where cityname=$1 and type='hotel'",
        values: [ namecity ],
    };
    
    pool.query(requete,(err,res)=>{
        if(err){
            console.log("Error : "+ err);
            callback(err);
        }
        else{
            var taille = res.rows.length;
            if(res.rows.length==0){
                callback("Aucune donnée correspondante");
            }else{
                liste = liste + ' ' + res.rows[0].name;
                for(var i=1; i<taille; i++){
                    liste = liste + ', ' + res.rows[i].name;
                }
                callback(null, liste);
            }
        };
    });
}

// Recherche des prix des chambres d'un hotel donné
function getHotelPrice(nameInfra, callback){
    var liste = '';
    const requete = {
        text: "select * from v_hotelroomprice where name=$1",
        values: [ nameInfra ],
    };
    
    pool.query(requete,(err,res)=>{
        if(err){
            console.log("Error : "+ err);
            callback(err);
        }
        else{
            var taille = res.rows.length;
            if(res.rows.length==0){
                callback("Aucune donnée correspondante");
            }else{
                liste = liste + ' ' + res.rows[0].price + 'Ar pour ' + res.rows[0].room_detail;
                for(var i=1; i<taille; i++){
                    liste = liste + ' ' + res.rows[i].price + 'Ar pour ' + res.rows[i].room_detail;
                }
                callback(null, liste);
            }
        };
    });
}

// Recherche des prix d'une chambre donnée d'un hotel donné
function getRoomPrice(nameInfra, room, callback){
    var liste = '';
    const requete = {
        text: "select * from v_hotelroomprice where name=$1 and room_detail=$2",
        values: [ nameInfra, room ],
    };
    
    pool.query(requete,(err,res)=>{
        if(err){
            console.log("Error : "+ err);
            callback(err);
        }
        else{
            // var taille = res.rows.length;
            
            if(res.rows.length==0){
                callback(null, "Aucune donnée correspondante");
            }else{
                liste = liste + ' ' + res.rows[0].pricemin + 'Ar et ' + res.rows[0].pricemax + 'Ar';
                callback(null, liste);
            }
        };
    });
}

// Recherche d'hotel ayant "room_details" comme détails
function getCityHotelRoom(city, room_details, callback){
    var liste = new Array();
    liste[0] = '';
    var requete = "select * from v_hotelroomprice where cityname='" + city + "' and room_detail like ('%" + room_details[0] + "%')";

    if(room_details.length>1){
        requete = "select * from v_hotelroomprice where cityname='" + city + "' and room_detail like ('%" + room_details[0] + "%') and room_detail like ('%" + room_details[1] + "%')";
        // requete = "select * from v_hotelroomprice where cityname='" + city + "' and room_detail like ('%" + room_details[0] + " et " + room_details[1] + "%')";
    }
    
    pool.query(requete,(err,res)=>{
        if(err){
            console.log("Error : "+ err);
            callback(err);
        }
        else{
            var taille = res.rows.length;
            if(res.rows.length==0){
                callback("Aucune donnée correspondante");
            }else{
                for(var i=0; i<taille; i++){
                    liste[i+1] = res.rows[i].name + ': ' + res.rows[i].room_detail + ' pour ' + res.rows[i].price + 'Ar la nuit ';
                }
                callback(null, liste);
            }
        };
    });
}


// Restaurant
// Recherche des prix de chaque specialité d'un restaurant donné
function getRestoPrice(nameInfra, callback){
    var liste = '';
    const requete = {
        text: "select * from v_restospecprice where name=$1",
        values: [ nameInfra ],
    };
    
    pool.query(requete,(err,res)=>{
        if(err){
            console.log("Error : "+ err);
            callback(err);
        }
        else{
            var taille = res.rows.length;
            if(res.rows.length==0){
                callback("Aucune donnée correspondante");
            }else{
                liste = liste + ' ' + res.rows[0].pricemin + 'Ar et ' + res.rows[0].pricemax + 'Ar pour la spécialité ' + res.rows[0].speciality;
                for(var i=1; i<taille; i++){
                    liste = liste + ' ' + res.rows[i].pricemin + 'Ar et ' + res.rows[i].pricemax + 'Ar pour la spécialité ' + res.rows[i].speciality;
                }
                callback(null, liste);
            }
        };
    });
}

function getRestoSpec(spec, city, callback){
    var liste = '';
    const requete = {
        text: "select * from v_restospecprice where speciality=$1 and cityname=$2",
        values: [ spec, city ],
    };
    pool.query(requete,(err,res)=>{
        if(err){
            console.log("Error : "+ err);
            callback(err);
        }
        else{
            var taille = res.rows.length;
            if(res.rows.length==0){
                callback("Aucune donnée correspondante");
            }else{
                liste = liste + ' ' + res.rows[0].name + ' pour le prix de ' + res.rows[0].pricemin + 'Ar à ' + res.rows[0].pricemax + 'Ar';
                for(var i=1; i<taille; i++){
                    liste = liste + ' ' + res.rows[0].name + ' pour le prix de ' + res.rows[i].pricemin + 'Ar et ' + res.rows[i].pricemax + 'Ar';
                }
                callback(null, liste);
            }
        };
    });
}

// Recherche des prix d'une spécialité donnée d'un restaurant donné
function getSpecialityPrice(nameInfra, nameSpeciality, callback){
    var liste = '';
    const requete = {
        text: "select * from v_restospecprice where name=$1 and speciality=$2",
        values: [ nameInfra, nameSpeciality ],
    };
    
    pool.query(requete,(err,res)=>{
        if(err){
            console.log("Error : "+ err);
            callback(err);
        }
        else{
            // var taille = res.rows.length;
            if(res.rows.length==0){
                callback("Aucune donnée correspondante");
            }else{
                liste = liste + ' ' + res.rows[0].pricemin + 'Ar et ' + res.rows[0].pricemax + 'Ar';
                callback(null, liste);
            }
        };
    });
}

// Acticité et monument
// Recherche des activités/monuments dans une ville donnée
function getActivityCity(city, callback){
    var liste = '';
    const requete = {
        text: "select * from v_typeinfracity where cityname=$1 and flag=true",
        values: [ city ],
    };
    
    pool.query(requete,(err,res)=>{
        if(err){
            console.log("Error : "+ err);
            callback(err);
        }
        else{
            var taille = res.rows.length;
            if(res.rows.length==0){
                callback("Aucune donnée correspondante");
            }else{
                liste = liste + ' ' + res.rows[0].name;
                for(var i=1; i<taille; i++){
                    liste = liste + ', ' + res.rows[i].name;
                }
                callback(null, liste);
            }
        };
    });
}

// Recherche des tarifs d'une activité/monuments donné
function getActivityPrice(nameActi, callback){
    var liste = '';
    const requete = {
        text: "select * from infrastructure where name=$1",
        values: [ nameActi ],
    };
    
    pool.query(requete,(err,res)=>{
        if(err){
            console.log("Error : "+ err);
            callback(err);
        }
        else{
            // var taille = res.rows.length;
            if(res.rows.length==0){
                callback("Aucune donnée correspondante");
            }else{
                liste = liste + ' ' + res.rows[0].pricemin + 'Ar et ' + res.rows[0].pricemax + 'Ar';
                callback(null, liste);
            }
        };
    });
}

// Details de tout type d'infrastructure
function getDetails(name, callback){
    var liste = '';
    const requete = {
        text: "select * from infrastructure where name=$1",
        values: [ name ],
    };
    
    pool.query(requete,(err,res)=>{
        if(err){
            console.log("Error : "+ err);
            callback(err);
        }
        else{
            var taille = res.rows.length;
            if(res.rows.length==0){
                callback("Aucune donnée correspondante");
            }else{
                liste = liste + ' ' + res.rows[0].details;
                if(res.rows[0].tel!=''){
                    liste = liste + ', téléphone: ' + res.rows[0].tel;
                }
                if(res.rows[0].email!=''){
                    liste = liste + ', email: ' + res.rows[0].email;
                }
                if(res.rows[0].address!=''){
                    liste = liste + ', adresse: ' + res.rows[0].address;
                }
                if(res.rows[0].website!=''){
                    liste = liste + ', site web: ' + res.rows[0].website;
                }
                // for(var i=1; i<taille; i++){
                //     liste = liste + ', ' + res.rows[i].details;
                // }
                callback(null, liste);
            }
        };
    });
}

// fonction de modulation du planning
function getPlanning(nb, city, callback){
    var liste = new Array();
    liste[0] = '';
    const requete = {
        text: "select * from v_typeinfracity where cityname=$1 and flag=true order by opening_time asc",
        values: [ city ],
    };

    pool.query(requete,(err,res)=>{
        if(err){
            console.log("Error : "+ err);
            callback(err);
        }
        else{
            if(res.rows.length==0){
                callback("Aucune donnée correspondante");
            }else{
                
                var part = utils.getRandomPart(res.rows, nb);
                var i=0;
                var i1 = 0;
                var i2 = 0;
                var il = 1;
                var oneday = '';
                var budgetmin = 0;
                var budgetmax = 0;
                while(i<part[0].length){
                    if(i==0){
                        oneday = oneday + " " + (i + 1) + "er jour: ";
                    }else{
                        oneday = oneday + " " + (i + 1) + "eme jour: ";
                    }
                    oneday = oneday + part[0][i1].name + ' ouvert de ' + part[0][i1].opening_time + ' à ' + part[0][i1].closure_time;
                    budgetmin = budgetmin + Number(part[0][i1].pricemin);
                    budgetmax = budgetmax + Number(part[0][i1].pricemax);
                    if(part[1]!=null){
                        oneday = oneday + ', ';
                        oneday = oneday + part[1][i2].name + ' ouvert de ' + part[1][i2].opening_time + ' à ' + part[1][i2].closure_time;
                        budgetmin = budgetmin + Number(part[1][i2].pricemin);
                        budgetmax = budgetmax + Number(part[1][i2].pricemax);
                    }
                    liste[il] = oneday;
                    oneday = '';
                    il++;
                    i1++;
                    i2++;
                    i++;
                }
                liste[il] = 'Pour cela vous devriez preparer au moins ' + budgetmin + ' Ar et ' + budgetmax + ' Ar au plus';
                callback(null, liste);
            }
        }
    });
}

function getBudget(city, budget, callback){
    var liste = '';
    const requete = {
        text: "select * from v_typeinfracity where cityname=$1 and pricemin<=$2 and flag=true order by pricemin asc",
        values: [ city, budget ],
    };

    pool.query(requete,(err,res)=>{
        if(err){
            console.log("Error : "+ err);
            callback(err);
        }
        else{
            var actualprice = 0;
            var temp = 0;
            if(res.rows.length==0){
                callback("Aucune donnée correspondante");
            }else{
                for(var i=0; i<res.rows.length; i++){
                    actualprice = actualprice + Number(res.rows[i].pricemin);
                    if(actualprice<=budget){
                        if(i>0){
                            liste = liste + ', ';
                        }
                        liste = liste + res.rows[i].name;
                    }else{
                        break;
                    }
                    temp = actualprice;
                }
                liste = liste + ' (pour seulement ' + temp + ' Ar au total)';
                callback(null, liste);
            }
        }
    });
}

function getLocation(name, callback){
    var liste = [{ name: "", latitude: "", longitude: ""}];
    const requete = {
        text: "select * from infrastructure where name=$1",
        values: [ name ],
    };
    
    pool.query(requete,(err,res)=>{
        if(err){
            console.log("Error : "+ err);
            callback(err);
        }
        else{
            liste = [{ 
                name: res.rows[0].name, 
                latitude: res.rows[0].latitude, 
                longitude: res.rows[0].longitude 
            }];
            callback(null, liste);
        };
    });
}

// recherche de tous les endroits dans un rayon de 10km pres (à vol d'oiseau) d'un point donné
function getNear(latitude, longitude, callback){
    var liste = [];
    var infratemp = "";
    var distancemax = 10;
    const requete = {
        text: "select name, distance from (select name, flag, round(haversine(longitude, latitude, $1, $2)) as distance from v_typeinfracity) as t1 where t1.distance<=$3 and flag='t'",
        values: [ longitude, latitude, distancemax ],
    };
    
    pool.query(requete,(err,res)=>{
        if(err){
            console.log("Error : "+ err);
            callback(err);
        }
        else{
            if(res.rows.length==0){
                callback("Aucune donnée correspondante");
            }else{
                for(var i=0; i<res.rows.length; i++){
                    infratemp = res.rows[i].name + ' à ' + res.rows[i].distance + 'km de votre position';
                    liste[i] = infratemp;
                }
                callback(null, liste);
            }
        };
    });
}

function getIdCityByName(name, callback){
    const requete = {
        text: "select * from infrastructure where name=$1",
        values: [ name ],
    };
    
    pool.query(requete,(err,res)=>{
        if(err){
            console.log("Error : "+ err);
            callback(err);
        }
        else{
            callback(null, res.rows[0].id);
        };
    });
}

function getFavoriteNb(id, callback){
    const requete = {
        text: "select * from favorite where id_1=$1",
        values: [ id ],
    };
    
    pool.query(requete,(err,res)=>{
        if(err){
            console.log("Error : "+ err);
            callback(err);
        }
        else{
            if(res.rows.length>0){
                callback(null, res.rows[0].nbview);
            }else{
                callback(null, 0);
            }
        };
    });
}

function insertfavoris(nom, callback){
    getIdCityByName(nom, function(err, id){
        getFavoriteNb(id, function(err, nb){
            if(nb!=0){
                const requete = {
                    text: "update favorite set nbview=$1 where id_1=$2",
                    values: [ Number(nb)+1, id ],
                };
                pool.query(requete, (err, res)=>{
                    if(err){
                        console.log("Error : "+ err);
                        callback(err);
                    }else{
                        callback(null, true);
                    }
                });
            }else{
                const requete = {
                    text: "insert into favorite values(nextval('idfavorite'), $1, $2)",
                    values: [ 1, id ],
                };
                pool.query(requete, (err, res)=>{
                    if(err){
                        console.log("Error : "+ err);
                        callback(err);
                    }else{
                        callback(null, true);
                    }
                });
            }
        });
    });
}

// suggestion
function getSuggest(namecity, callback){
    var liste = '';
    var requete = {
        text: "select * from v_suggest",
        values: [  ],
    };
    if(namecity!=''){
        requete = {
            text: "select * from v_suggest where cityname=$1",
            values: [ namecity ],
        };
    }
    
    pool.query(requete,(err,res)=>{
        if(err){
            console.log("Error : "+ err);
            callback(err);
        }
        else{
            var taille = res.rows.length;
            if(res.rows.length==0){
                callback("Aucune donnée correspondante");
            }else{
                liste = liste + ' ' + res.rows[0].name;
                for(var i=1; i<taille; i++){
                    liste = liste + ', ' + res.rows[i].name;
                }
                callback(null, liste);
            }
        };
    });
}

module.exports = { getCity, getCityRegion, getRegion, getNameList, getCityHotel, getHotelPrice, getRoomPrice, getCityHotelRoom, getRestoPrice, getRestoSpec, getSpecialityPrice, getActivityCity, getDetails, getPlanning, getActivityPrice, getBudget, getLocation, getNear, insertfavoris, getSuggest };