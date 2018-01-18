DROP DATABASE IF EXISTS Horario;
CREATE DATABASE Horario;
USE Horario;

CREATE TABLE OfertaEducativa (
codOe char(3) PRIMARY KEY,
nombre varchar(70),
descripcion varchar(255),
tipo ENUM('CFGS','CFGM','FPB','ESO','BACH'),
fechaLey date);

CREATE TABLE Profesor(
codProf char(3) PRIMARY KEY,
nombre varchar(20),
apellidos varchar(40),
fechaAlta timestamp);


CREATE TABLE Curso(
codOe char(3),
codCurso char(2),
codTutor char(3) NOT NULL,
PRIMARY KEY(codOe, codCurso),
CONSTRAINT FK_codOe FOREIGN KEY (codOe)
REFERENCES OfertaEducativa(codOe) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FK_codTutor FOREIGN KEY (codTutor)
REFERENCES Profesor(codProf) ON DELETE RESTRICT ON UPDATE CASCADE);

CREATE TABLE TramoHorario(
codTramo char(2) PRIMARY KEY,
horaInicio TIME,
horaFin TIME,
dia ENUM('LUNES' , 'MARTES' , 'MIERCOLES' , 'JUEVES', 'VIERNES')
);

CREATE TABLE Asignatura(
codAsig varchar(5) PRIMARY KEY,
nombre varchar(80) NOT NULL,
horasSemanales tinyint unsigned,
horasTotales smallint unsigned
);

CREATE TABLE Reparto(
codOe char(3),
codCurso char(2),
codAsig VARCHAR(5),
codProf char(3),
PRIMARY KEY(codOe, codCurso, codAsig, codProf),
CONSTRAINT FK_CodOeYCurso FOREIGN KEY (codOe, codCurso)
REFERENCES Curso(codOe,codCurso) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FK_CodAsig FOREIGN KEY (codAsig)
REFERENCES Asignatura(codAsig) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FK_CodProf FOREIGN KEY (codProf)
REFERENCES Profesor(codProf) ON DELETE RESTRICT ON UPDATE CASCADE);

CREATE TABLE Horario(
codOe char(3),
codCurso char(2),
codAsig varchar(5),
codTramo char(2),
PRIMARY KEY(codOe, codCurso, codAsig, codTramo),
CONSTRAINT FK_CodOeCurso FOREIGN KEY (codOe, codCurso)
REFERENCES Curso(codOe,codCurso) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FK_CodAsignatura FOREIGN KEY (codAsig)
REFERENCES Asignatura(codAsig) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FK_CodTramo FOREIGN KEY (codTramo)
REFERENCES TramoHorario(codTramo) ON DELETE CASCADE ON UPDATE CASCADE);


INSERT INTO OfertaEducativa VALUES
("SMR","Grado Medio de Sistemas Microinformáticos y Redes","El CFGM SMR tiene una duración de 2000 horas
 repartidas entre dos cursos académicos incluyendo un trimestre de Formacion en centros de trabajo (FCT)
 en empresas del Sector",'CFGM','2009-07-07'),
("DAM","Grado Superior de Desarollo de Aplicaciones Multiplataforma","El CFGs DAM tiene una duración de 2000 horas repartidas entre dos cursos académicos incluyendo 400 horas de Formacion en centros de trabajo (FCT) en empresas del Sector",'CFGS','2011-06-16');

INSERT INTO Profesor VALUES
("AGL","Ana","Gutiérrez Lozano",'1999-09-01'),
("PJM","Pedro","Joya Máñez",'2000-09-01'),
("EPM","Eva","Peralta Macías",'2009-09-01'),
("MPG","Manuel Jesús","Padilla Gutiérrez",'2016-09-01'),
("JGG","Javier","Graña González",'2011-09-01'),
("PBG","Pilar","Baena García",'2007-09-01'),
("DAS","Daniel","Ayala Soriano",'2006-09-01'),
("MGD","María","Gallego Díaz",'2016-09-01'),
("CJC","Carmen","Jurado Cano",'2013-09-01'),
("DJC","María Dolores","Jiménez Cortés",'2010-09-01'),
("DQS","David","Quero Sánchez",'2017-09-01'),
("JJL","Jose María","Jiménez Llodra",'2015-09-01');

INSERT INTO Curso VALUES
("SMR","1A","DJC"),
("SMR","2A","JGG"),
("DAM","1A","CJC"),
("DAM","2A","DQS");

INSERT INTO Asignatura VALUES
("RED", "Redes Locales", 7,224),
("@RED", "Desdoble de Redes Locales", 3,224),
("SISM", "Sistemas operativos monopuestos", 5,160),
("MONT", "Montaje y mantenimiento de equipos", 7,224),
("@MONT", "Desdoble de Montaje y mantenimiento de equipos", 3,224),
("APLI", "Aplicaciones ofimáticas", 8, 256),
("@APLI", "Desdoble de Aplicaciones ofimáticas", 4, 256),
("SEG", "Seguridad informática", 5,105),
("HLC", "Horas de Libre Configuración", 3,63),
("SISR", "Sistemas operativos en red",7,147),
("SERV","Servicios en red",7,147),
("APLIW","Aplicaciones web",4,84),
("EIEM", "Empresa e iniciativa empresarial", 4,84),
("SIST","Sistemas informáticos",6,192),
("@SIST","Desdoble de Sistemas informáticos",3,192),
("BDD","Bases de Datos",6,192),
("@BDD","Desdoble de Bases de Datos",3,192),
("PROG","Programación",8, 256),
("@PROG","Desdoble de Programación",6, 256),
("ENT","Entornos de desarrollo",3,96),
("MARC","Lenguajes de marcas y sistemas de gestión de información", 4,128),
("FOL","Formación y orientación laboral",3,96),
("AD","Acceso a datos",5,105),
("DI","Desarrollo de interfaces",7,147),
("PSPRO","Programación de servicios y procesos",3,63),
("PMDMO","Programación multimedia y dispositivos móviles",4,84),
("EIE","Empresa e iniciativa emprendedora",4,84),
("SGE","Sistemas de gestión empresarial",4,84);

INSERT INTO TramoHorario VALUES
("L1", "08:15:00", "09:15:00", 'LUNES'), ("L2", "09:15:00", "10:15:00", 'LUNES'), ("L3", "10:15:00", "11:15:00", 'LUNES'),
("L4", "11:45:00", "12:45:00", 'LUNES'), ("L5", "12:45:00", "13:45:00", 'LUNES'), ("L6", "13:45:00", "14:45:00", 'LUNES'),
("M1", "08:15:00", "09:15:00", 'MARTES'), ("M2", "09:15:00", "10:15:00", 'MARTES'), ("M3", "10:15:00", "11:15:00", 'MARTES'),
("M4", "11:45:00", "12:45:00", 'MARTES'), ("M5", "12:45:00", "13:45:00", 'MARTES'), ("M6", "13:45:00", "14:45:00", 'MARTES'),
("X1", "08:15:00", "09:15:00", 'MIERCOLES'), ("X2", "09:15:00", "10:15:00", 'MIERCOLES'), ("X3", "10:15:00", "11:15:00", 'MIERCOLES'),
("X4", "11:45:00", "12:45:00", 'MIERCOLES'), ("X5", "12:45:00", "13:45:00", 'MIERCOLES'), ("X6", "13:45:00", "14:45:00", 'MIERCOLES'),
("J1", "08:15:00", "09:15:00", 'JUEVES'), ("J2", "09:15:00", "10:15:00", 'JUEVES'), ("J3", "10:15:00", "11:15:00", 'JUEVES'),
("J4", "11:45:00", "12:45:00", 'JUEVES'), ("J5", "12:45:00", "13:45:00", 'JUEVES'), ("J6", "13:45:00", "14:45:00", 'JUEVES'),
("V1", "08:15:00", "09:15:00", 'VIERNES'), ("V2", "09:15:00", "10:15:00", 'VIERNES'), ("V3", "10:15:00", "11:15:00", 'VIERNES'),
("V4", "11:45:00", "12:45:00", 'VIERNES'), ("V5", "12:45:00", "13:45:00", 'VIERNES'), ("V6", "13:45:00", "14:45:00", 'VIERNES');


INSERT INTO Reparto VALUES
("SMR","1A","RED","JGG"),("SMR","1A","@RED","MGD"),("SMR","1A","SISM","DJC"),
("SMR","1A","MONT","DJC"),("SMR","1A","@MONT","DQS"),("SMR","1A","@MONT","CJC"),
("SMR","1A","APLI","CJC"),("SMR","1A","@APLI","PBG"),("SMR","1A","FOL","JJL"),
("SMR","2A","SEG","AGL"),("SMR","2A","HLC","JGG"),("SMR","2A","SISR","PBG"),
("SMR","2A","SERV","JGG"),("SMR","2A","APLIW","PJM"),("SMR","2A","EIEM","JJL"),
("DAM","1A","SIST","CJC"),("DAM","1A","@SIST","PBG"),("DAM","1A","BDD","AGL"),
("DAM","1A","@BDD","EPM"),("DAM","1A","PROG","EPM"),("DAM","1A","@PROG","PJM"),
("DAM","1A","ENT","EPM"),("DAM","1A","MARC","MGD"),("DAM","1A","FOL","JJL"),
("DAM","2A","AD","EPM"),("DAM","2A","DI","DQS"),("DAM","2A","PSPRO","PJM"),
("DAM","2A","PMDMO","PJM"),("DAM","2A","EIE","JJL"),("DAM","2A","SGE","CJC"),
("DAM","2A","HLC","MGD");

-- 1 CFGM
INSERT INTO Horario VALUES
("SMR", "1A", "SISM", "L4"), ("SMR", "1A", "SISM", "L5"),
("SMR", "1A", "APLI", "L6"), ("SMR", "1A", "@APLI", "L6"),
("SMR", "1A", "APLI", "M2"), ("SMR", "1A", "@APLI", "M2"),
("SMR", "1A", "APLI", "M3"), ("SMR", "1A", "RED", "M4"),
("SMR", "1A", "RED", "M5"),  ("SMR", "1A", "SISM", "M6"),
("SMR", "1A", "APLI", "X2"), ("SMR", "1A", "@APLI", "X2"),
("SMR", "1A", "RED", "X4"),  ("SMR", "1A", "@RED", "X4"),
("SMR", "1A", "APLI", "X5"), ("SMR", "1A", "SISM", "X6"),
("SMR", "1A", "RED", "J2"),  ("SMR", "1A", "@RED", "J2"),
("SMR", "1A", "SISM", "J4"),
("SMR", "1A", "APLI", "J5"), ("SMR", "1A", "@APLI", "J5"),
("SMR", "1A", "APLI", "J6"),
("SMR", "1A", "RED", "V1"),  ("SMR", "1A", "RED", "V2"),
("SMR", "1A", "RED", "V3"),  ("SMR", "1A", "@RED", "V3"),
("SMR", "1A", "APLI", "V6"),
-- Por si acaso:
("SMR", "1A", "MONT", "L1"), ("SMR", "1A", "MONT", "L2"),
("SMR", "1A", "MONT", "L3"), ("SMR", "1A", "@MONT", "L3"),
("SMR", "1A", "FOL", "M1"),  ("SMR", "1A", "FOL", "X1"),
("SMR", "1A", "MONT", "X3"), ("SMR", "1A", "@MONT", "X3"),
("SMR", "1A", "FOL", "J1"),
("SMR", "1A", "MONT", "J3"), ("SMR", "1A", "@MONT", "J3"),
("SMR", "1A", "MONT", "V4"), ("SMR", "1A", "MONT", "V5"),

-- 2 CFGM
("SMR","2A","EIE","L1"),("SMR","2A","SEG","L2"),("SMR","2A","SISR","L3"),
("SMR","2A","SERV","L4"),("SMR","2A","APLIW","L5"),("SMR","2A","HLC","L6"),
("SMR","2A","SEG","M1"),("SMR","2A","EIE","M2"),("SMR","2A","SERV","M3"),
("SMR","2A","SISR","M4"),("SMR","2A","SISR","M5"),("SMR","2A","APLIW","M6"),
("SMR","2A","SISR","X1"),("SMR","2A","HLC","X2"),("SMR","2A","HLC","X3"),
("SMR","2A","EIE","X4"),("SMR","2A","APLIW","X5"),("SMR","2A","SEG","X6"),
("SMR","2A","SEG","J1"),("SMR","2A","EIE","J2"),("SMR","2A","SERV","J3"),
("SMR","2A","SERV","J4"),("SMR","2A","SERV","J5"),("SMR","2A","SISR","J6"),
("SMR","2A","SISR","V1"),("SMR","2A","SISR","V2"),("SMR","2A","APLIW","V3"),
("SMR","2A","SERV","V4"),("SMR","2A","SERV","V5"),("SMR","2A","SEG","V6"),

-- 1 CFGS
("DAM","1A","ENT","L1"),("DAM","1A","PROG","L2"),("DAM","1A","BDD","L3"),
("DAM","1A","BDD","L4"),("DAM","1A","BDD","L5"),("DAM","1A","FOL","L6"),
("DAM","1A","SIST","M1"),("DAM","1A","PROG","M2"),("DAM","1A","PROG","M3"),
("DAM","1A","SIST","M4"),("DAM","1A","FOL","M5"),("DAM","1A","MARC","M6"),
("DAM","1A","MARC","X1"),("DAM","1A","BDD","X2"),("DAM","1A","SIST","X3"),
("DAM","1A","PROG","X4"),("DAM","1A","PROG","X5"),("DAM","1A","SIST","X6"),
("DAM","1A","ENT","J1"),("DAM","1A","PROG","J2"),("DAM","1A","SIST","J3"),
("DAM","1A","MARC","J4"),("DAM","1A","PROG","J5"),("DAM","1A","FOL","J6"),
("DAM","1A","BDD","V1"),("DAM","1A","BDD","V2"),("DAM","1A","SIST","V3"),
("DAM","1A","PROG","V4"),("DAM","1A","ENT","V5"),("DAM","1A","MARC","V6"),
("DAM","1A","@BDD","L3"),("DAM","1A","@BDD","L4"),("DAM","1A","@BDD","X2"),
("DAM","1A","@PROG","L2"),("DAM","1A","@PROG","M2"),("DAM","1A","@PROG","M3"),
("DAM","1A","@PROG","X4"),("DAM","1A","@PROG","J2"),("DAM","1A","@PROG","V4"),

-- 2 CFGS
("DAM","2A","DI","L1"),("DAM","2A","DI","L2"),("DAM","2A","PMDMO","L3"),
("DAM","2A","PMDMO","L4"),("DAM","2A","HLC","L5"),("DAM","2A","PSPRO","L6"),
("DAM","2A","DI","M1"),("DAM","2A","DI","M2"),("DAM","2A","EIE","M3"),
("DAM","2A","PMDMO","M4"),("DAM","2A","DI","M5"),("DAM","2A","PMDMO","M6"),
("DAM","2A","AD","X1"),("DAM","2A","EIE","X2"),("DAM","2A","HLC","X3"),
("DAM","2A","SGE","X4"),("DAM","2A","DI","X5"),("DAM","2A","PSPRO","X6"),
("DAM","2A","HLC","J1"),("DAM","2A","SGE","J2"),("DAM","2A","AD","J3"),
("DAM","2A","AD","J4"),("DAM","2A","EIE","J5"),("DAM","2A","DI","J6"),
("DAM","2A","DI","V1"),("DAM","2A","AD","V2"),("DAM","2A","AD","V3"),
("DAM","2A","SGE","V4"),("DAM","2A","PSPRO","V5"),("DAM","2A","EIE","V6");
