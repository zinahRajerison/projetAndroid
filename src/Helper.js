const { Pool, Client } = require('pg');
const connectionString = 'postgresql://postgres:123456@localhost:5432/zoo';
class Helper{
    seConnecter =function(){
        return new Promise(function(resolve,reject){
            const pool = new Pool({
                connectionString,
            })
            resolve(pool);
        })
    }
}
module.exports=Helper


