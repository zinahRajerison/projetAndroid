const MongoClient = require('mongodb').MongoClient
// var url="mongodb+srv://ekalyUser:2EAUIIgK6RUvnkHC@cluster0.ut6sd.mongodb.net/ekaly?retryWrites=true&w=majority"
const url = 'mongodb://127.0.0.1:27017/'
const dbName = 'explorateur'

class Helper{
    seConnecter=function(){
        return new Promise(function(resolve,reject)
        {
            console.log("here")
            MongoClient.connect(url,(err, client) => {
                if(err) console.log(err)
                else{
                    var db = client.db(dbName)
                    console.log(`Connected MongoDB: ${url}`)
                    console.log(`Database: ${dbName}`)
                    resolve(db);
                }
            });
        });
    };
}
module.exports=Helper


