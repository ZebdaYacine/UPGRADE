create database upgrade;
use upgrade;

create table office(
id int primary key auto_increment,
name varchar(50) unique
);

create table speciality(
id int primary key auto_increment,
name varchar(50) unique
);

create table need(
id int primary key AUTO_INCREMENT,
idOffice int not null,
idSpeciality int not null,
FOREIGN KEY (idOffice) REFERENCES office(id) on delete cascade on update cascade,
FOREIGN KEY (idSpeciality) REFERENCES speciality(id) on delete cascade on update cascade
);

create table workingDate(
id int primary key AUTO_INCREMENT,
dateWorke date
);

create table grade(
id int primary key auto_increment,
name varchar(50) unique,
deploma varchar(50), 
note int 
);

create table employer(
id int primary key auto_increment,
firstName varchar(50),
lastName varchar(50),
phone varchar(20) unique,
birthDate date,
recruitmentDate date,
socialStatus varchar(50),
deploma varchar(50),
nbrchildren int ,
note int,
nbrFonrmations int,
experience int,
idGrade int not null,
idOffice int not null,
unique(firstName,lastName,birthDate),
FOREIGN KEY (idGrade) REFERENCES grade(id) on delete cascade on update cascade,
FOREIGN KEY (idOffice) REFERENCES office(id) on delete cascade on update cascade
);

create table work(
id int primary key auto_increment,
idEmployer int not null,
idWorkingDate int not null,
status varchar(50),
FOREIGN KEY (idEmployer) REFERENCES employer(id) on delete cascade on update cascade,
FOREIGN KEY (idWorkingDate) REFERENCES workingDate(id) on delete cascade on update cascade
)

