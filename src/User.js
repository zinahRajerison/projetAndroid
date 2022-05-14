var md5=require('md5')
var helper=require('./Helper.js')
class User{
    constructor(name,pwd){
        this.name=name;
        this.pwd=pwd;
    }
    seLogger =function(){
        return "huhu";
    }
}
module.exports=User
