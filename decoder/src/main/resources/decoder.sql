create table theme(
    id int auto_increment primary key,
    title varchar(30) not null,
    intro varchar(100) not null,
    info varchar(100) not null,
    main_img varchar(200) not null,
    icon_img varchar(200) not null
    );
create table theme_timeLines(
    id int auto_increment primary key,
    first_time varchar(30) not null,
    second_time varchar(100) not null,
    third_time varchar(30) not null,
    fourth_time varchar(30) not null,
    fifth_time varchar(30) not null,
    six_time varchar(30) not null,
    theme_id int not null,
    foreign key(theme_id) references theme(id)
    );
    
create table booked_time(
    id int auto_increment primary key,
    booked_time varchar(30) not null,
    booked_date date not null,
    booked_name varchar(20) not null,
    phone varchar(40) not null,
    email varchar(50) not null,
    people_count int default 2 not null,
    booked_theme int not null,
    timeLines_id int not null,
    foreign key(timeLines_id) references theme_timeLines(id),
    foreign key(booked_theme) references theme(id)
    );
create table managers(
    id int auto_increment primary key,
    user_id varchar(30) not null,
    user_password varchar(64)  not null,
    name varchar(20) not null
    );