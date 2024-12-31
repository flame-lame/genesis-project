create database if not exists genesis;

create table if not exists user
(
    id        bigint auto_increment
        primary key,
    name      varchar(255) not null,
    person_id varchar(50)  not null,
    surname   varchar(255) not null,
    uuid      varchar(50)   not null,
    constraint user_uuid_key
        unique (uuid),
    constraint user_person_id_key
        unique (person_id)
);

insert into user (person_id, name, surname, uuid)
values ('jXa4g3H7oPq2', 'Sinclare', 'Millmoe', UUID()),
       ('yB9fR6tK0wLm', 'Garret', 'Segar', UUID()),
       ('cN1vZ8pE5sYx', 'Wendeline', 'Busfield', UUID()),
       ('tQdG2kP3mJfB', 'Bancroft', 'Proven', UUID()),
       ('iM5sO6zXcW7v', 'Etan', 'Yegorchenkov', UUID()),
       ('rU8nA9eT2bYh', 'Merrili', 'Dillinger', UUID()),
       ('wV6eH1fK7qZj', 'Clare', 'Beilby', UUID()),
       ('sL4gN9dC3bXz', 'Scarlet', 'Pawnsford', UUID()),
       ('kR0aZ7vW2nDl', 'Helaina', 'Hayball', UUID()),
       ('eI1oY6tQ9dKj', 'Guglielmo', 'Kepp', UUID());
