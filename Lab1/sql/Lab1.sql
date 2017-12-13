create table `order`
(
	oid int auto_increment
		primary key,
	ordertime datetime not null,
	ordername varchar(255) not null,
	ordercount int not null,
	orderprice double not null
)
;

create table user
(
	uid int auto_increment
		primary key,
	username varchar(255) not null,
	password varchar(255) not null
)
;

create table user_order
(
	uoid int auto_increment
		primary key,
	uid int not null,
	oid int not null,
	constraint user_order_user_uid_fk
		foreign key (uid) references j2eelab.user (uid),
	constraint user_order_order_oid_fk
		foreign key (oid) references j2eelab.`order` (oid)
)
;

create index user_order_order_oid_fk
	on user_order (oid)
;

create index user_order_user_uid_fk
	on user_order (uid)
;

