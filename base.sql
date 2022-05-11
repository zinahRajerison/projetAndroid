create database zoo;
alter database zoo owner to postgres;

create table customer(
    id_user serial primary key,
    nom_user varchar(40) not null,
    mdp_user varchar(40) not null
);

create table categorie(
    id_categorie serial primary key,
    nom_categorie varchar (40) not null
);
create table pays(
    id_pays serial primary key,
    nom_pays varchar(40) not null
);
create table animal(
    id_animal serial primary key,
    nom_animal varchar(40) not null,
    femelle varchar(40) not null,
    enfant varchar(40) not null,
    image varchar(50),
    son varchar(50),
    video varchar(50),
    id_categorie int not null references categorie(id_categorie),
    id_pays int not null references pays(id_pays)
);