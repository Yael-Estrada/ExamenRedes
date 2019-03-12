Drop database if exists FORO;
Create DATABASE FORO;
USE FORO;

CREATE TABLE TEMAS(
idTemas int auto_increment,
Nombre varchar(20),
Descripcion varchar(100), 
primary key(idTemas)
);

CREATE TABLE Publicaciones(
idPublicaciones int auto_increment,
idTemas int,
publicacion varchar(500),
tipo boolean,
usuario varchar(20),
fecha date,
primary key(idPublicaciones),
foreign key (idTemas) references TEMAS (idTemas)
);


delimiter **
create procedure publicar(in tema varchar(45), in nombre varchar(45), in texto varchar(200),in tipo boolean,in f date)
begin

declare id int;
set id = (select idTemas from Temas where temas.nombre=tema);
insert into publicaciones(idtemas,publicacion,usuario,tipo,fecha) values(id,texto,nombre,tipo,f);

end; **
delimiter ;

delimiter **
create procedure crearTema(in tema varchar(45), in descrip varchar(100))
begin

insert into temas(nombre,descripcion) values(tema,descrip);

end; **
delimiter ;

delimiter **
create procedure mostrarPublicaciones(in tema varchar(45))
begin
declare id int;
set id = (select idTemas from Temas where temas.nombre=tema);
select tema as 'Tema',publicacion,usuario,fecha from publicaciones where idTemas =  id;
end; **
delimiter ;
	
delimiter **
create procedure buscarPublicaciones(in tema varchar(45),in f varchar(45))
begin
declare id int;
set id = (select idTemas from Temas where temas.nombre=tema);
if tema = null then
	select tema as 'Tema',publicacion,usuario,fecha from publicaciones where publicaciones.fecha=f;
else 
	if f=null then 
		select tema as 'Tema', publicacion,usuario,fecha from publicaciones where publicaciones.idTemas =  id;
	else
		select tema as 'Tema',publicacion,usuario,fecha from publicaciones where publicaciones.idTemas =  id and publicaciones.fecha=f;
	end if;
end if;

end; **
delimiter ;