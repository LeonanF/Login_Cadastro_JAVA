
create table users(
	name varchar(30) not null,
    address varchar(30) not null,
    phone varchar(11) not null,
    cpf varchar(11) PRIMARY KEY,
    bloodtype varchar(2) not null,
    rhFactor char(1) not null,
    course varchar(30) not null,
    emergencyPhone varchar(11) not null,
    username varchar(15) unique not null,
    password varchar(12) not null
)
