class Quizz{
    constructor(animal,indice,randomFamille,question){
        this.animal=animal;
        this.indice=indice;
        this.randomFamille=randomFamille;
        this.question=question;
    }
    get getAnimal(){
        return this.animal;
    }
    get getIndice()
    {
        return this.indice;
    }
    get getQuestion()
    {
        return this.question;
    }
    get getRandomFamille()
    {
        return this.randomFamille;
    }
    set setAnimal(animal){
        this.animal=animal;
    }
    set setIndice(indice)
    {
        this.indice=indice;
    }
    set setQuestion(question)
    {
        this.question=question;
    }
    set setRamdomFamille(ramdomFamille)
    {
        this.ramdomFamille=ramdomFamille;
    }
}
module.exports=Quizz