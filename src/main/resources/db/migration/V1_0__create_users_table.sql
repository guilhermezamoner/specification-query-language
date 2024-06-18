CREATE TABLE IF NOT EXISTS `users` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(20),
    `email` varchar(50),
    `age` int,
    `birthdate` timestamp
);

insert into users (name, email, age, birthdate) values ('John Doe', 'john@gmail.com', 30, '1990-01-01 00:00:00');
insert into users (name, email, age, birthdate) values ('Jane Doe', 'jane@outlook.com', 25, '1995-01-01 00:00:00');
insert into users (name, email, age, birthdate) values ('Alice', 'alice@apple.com', 35, '1985-01-01 00:00:00');