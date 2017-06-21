create table locations(id bigint auto_increment, name varchar(255), lat double, lon double, primary key (id));
insert into locations(name, lat, lon) values ('Budapest', 47.4979, 19.0402);