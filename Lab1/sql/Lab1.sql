create table myuser
(
	username varchar(255) not null
		primary key,
	password varchar(255) not null,
	constraint myuser_username_uindex
		unique (username)
)
;

create table `order`
(
	oid int auto_increment
		primary key,
	ordertime datetime not null,
	ordername varchar(255) not null,
	ordercount int not null,
	orderprice double not null,
	username varchar(255) not null,
	constraint order_myuser_username_fk
		foreign key (username) references j2eelab.myuser (username)
			on update cascade on delete cascade
)
;

create index order_myuser_username_fk
	on `order` (username)
;

INSERT INTO J2EELab.myuser (username, password) VALUES ('skx', 'skx');
INSERT INTO J2EELab.myuser (username, password) VALUES ('szs', 'szs');
INSERT INTO J2EELab.myuser (username, password) VALUES ('zgq', 'zgq');
INSERT INTO J2EELab.myuser (username, password) VALUES ('zyz', 'zyz');
INSERT INTO J2EELab.myuser (username, password) VALUES ('zzc', 'zzc');

INSERT INTO J2EELab.`order` (ordertime, ordername, ordercount, orderprice, username) VALUES ('2017-12-13 20:44:15', 'Apple', 123, 123.12, 'skx');
INSERT INTO J2EELab.`order` (ordertime, ordername, ordercount, orderprice, username) VALUES ('2017-06-13 18:44:36', 'Orange', 64, 234.62, 'zzc');
INSERT INTO J2EELab.`order` (ordertime, ordername, ordercount, orderprice, username) VALUES ('2017-08-16 20:45:24', 'Banana', 8, 30.2, 'skx');
INSERT INTO J2EELab.`order` (ordertime, ordername, ordercount, orderprice, username) VALUES ('2016-06-16 23:45:36', 'Watermelon', 421, 888.49, 'szs');
INSERT INTO J2EELab.`order` (ordertime, ordername, ordercount, orderprice, username) VALUES ('2016-09-19 16:48:19', 'Peach', 451, 999.21, 'zyz');
INSERT INTO J2EELab.`order` (ordertime, ordername, ordercount, orderprice, username) VALUES ('2017-10-03 17:53:55', 'Pear', 23, 145.67, 'szs');
INSERT INTO J2EELab.`order` (ordertime, ordername, ordercount, orderprice, username) VALUES ('2014-08-28 01:49:16', 'Lemon', 99, 467.83, 'skx');