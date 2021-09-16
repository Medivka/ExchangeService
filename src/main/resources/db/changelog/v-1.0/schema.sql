
CREATE TABLE `heroku_f5939ac599e38c3`.`section`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

CREATE TABLE `heroku_f5939ac599e38c3`.`role`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `role` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

CREATE TABLE `heroku_f5939ac599e38c3`.`profile`
(
    `id`       bigint       NOT NULL AUTO_INCREMENT,
    `age`      int          DEFAULT NULL,
    `city`     varchar(255) DEFAULT NULL,
    `email`    varchar(255) DEFAULT NULL,
    `lastname` varchar(255) DEFAULT NULL,
    `name`     varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    `status`   varchar(255) NOT NULL,
    `username` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;


CREATE TABLE `heroku_f5939ac599e38c3`.`profile_role`
(
    `role_id`   bigint NOT NULL,
    `client_id` bigint NOT NULL,
    PRIMARY KEY (`role_id`, `client_id`),
    KEY         `FK9uh6ebst0d27ynk42o315r6xj` (`client_id`),
    CONSTRAINT `FK9sx4d1mv1bx7ycdst08d1grmh` FOREIGN KEY (`role_id`) REFERENCES `heroku_f5939ac599e38c3`.`role` (`id`),
    CONSTRAINT `FK9uh6ebst0d27ynk42o315r6xj` FOREIGN KEY (`client_id`) REFERENCES `heroku_f5939ac599e38c3`.`profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

CREATE TABLE `heroku_f5939ac599e38c3`.`comment`
(
    `id`      bigint NOT NULL AUTO_INCREMENT,
    `message` varchar(255) DEFAULT NULL,
    `profile` bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY       `FKbfkaluhym7wlg9h5xv7syg3w` (`profile`),
    CONSTRAINT `FKbfkaluhym7wlg9h5xv7syg3w` FOREIGN KEY (`profile`) REFERENCES `heroku_f5939ac599e38c3`.`profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

CREATE TABLE `heroku_f5939ac599e38c3`.`course`
(
    `id`            bigint NOT NULL AUTO_INCREMENT,
    `course_status` varchar(255) DEFAULT NULL,
    `days`          int          DEFAULT NULL,
    `end_course`    date         DEFAULT NULL,
    `name`          varchar(255) NOT NULL,
    `price`         int          DEFAULT NULL,
    `start_course`  date         DEFAULT NULL,
    `section`       bigint       NOT NULL,
    `speaker`       bigint       NOT NULL,
    PRIMARY KEY (`id`),
    KEY             `FKh8jbiya71ka6appat1ld86ark` (`section`),
    KEY             `FKkpkjp82mi0r83qnjx7hg8c0el` (`speaker`),
    CONSTRAINT `FKh8jbiya71ka6appat1ld86ark` FOREIGN KEY (`section`) REFERENCES `heroku_f5939ac599e38c3`.`section` (`id`),
    CONSTRAINT `FKkpkjp82mi0r83qnjx7hg8c0el` FOREIGN KEY (`speaker`) REFERENCES `heroku_f5939ac599e38c3`.`profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

CREATE TABLE `heroku_f5939ac599e38c3`.`course_profile`
(
    `id_profile` bigint NOT NULL,
    `id_course`  bigint NOT NULL,
    KEY          `FK51bq93imhxwcg51bb0slo49xr` (`id_course`),
    KEY          `FKop93326k2abljx45u1c9mdw2a` (`id_profile`),
    CONSTRAINT `FK51bq93imhxwcg51bb0slo49xr` FOREIGN KEY (`id_course`) REFERENCES `heroku_f5939ac599e38c3`.`course` (`id`),
    CONSTRAINT `FKop93326k2abljx45u1c9mdw2a` FOREIGN KEY (`id_profile`) REFERENCES `heroku_f5939ac599e38c3`.`profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

CREATE TABLE `heroku_f5939ac599e38c3`.`course_comment`
(
    `id_course`  bigint NOT NULL,
    `id_comment` bigint NOT NULL,
    KEY          `FK45v3mo4kbjbgjiwh99uy9r20r` (`id_comment`),
    KEY          `FKg9mmfq552m9bs2o7yaq5sv9uv` (`id_course`),
    CONSTRAINT `FK45v3mo4kbjbgjiwh99uy9r20r` FOREIGN KEY (`id_comment`) REFERENCES `heroku_f5939ac599e38c3`.`comment` (`id`),
    CONSTRAINT `FKg9mmfq552m9bs2o7yaq5sv9uv` FOREIGN KEY (`id_course`) REFERENCES `heroku_f5939ac599e38c3`.`course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

CREATE TABLE `heroku_f5939ac599e38c3`.`lesson`
(
    `id`              bigint NOT NULL AUTO_INCREMENT,
    `duration`        int          DEFAULT NULL,
    `lesson_status`   varchar(255) DEFAULT NULL,
    `local_date_time` datetime(6) DEFAULT NULL,
    `name`            varchar(255) NOT NULL,
    `price`           int          DEFAULT NULL,
    `course`          bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY               `FKb17a13pfigs9wj87ph9ms4wjh` (`course`),
    CONSTRAINT `FKb17a13pfigs9wj87ph9ms4wjh` FOREIGN KEY (`course`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;


INSERT INTO `heroku_f5939ac599e38c3`.`section` (`id`, `name`)
VALUES ('1', 'Mathematics');
INSERT INTO `heroku_f5939ac599e38c3`.`section` (`id`, `name`)
VALUES ('2', 'Programming');
INSERT INTO `heroku_f5939ac599e38c3`.`section` (`id`, `name`)
VALUES ('3', 'Physics');
INSERT INTO `heroku_f5939ac599e38c3`.`section` (`id`, `name`)
VALUES ('4', 'Dancing');
INSERT INTO `heroku_f5939ac599e38c3`.`section` (`id`, `name`)
VALUES ('5', 'Languages');

INSERT INTO `heroku_f5939ac599e38c3`.`role` (`id`, `role`)
VALUES ('1', 'ROLE_USER');
INSERT INTO `heroku_f5939ac599e38c3`.`role` (`id`, `role`)
VALUES ('2', 'ROLE_ADMIN');

INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('1', '22', 'Grodno', 'zamulko@mail.ru', 'Adminovich', 'admin', 'admin', 'LISTENER', 'admin');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('2', '24', 'Vavilon', 'mudrii@mail.ru', 'Userovich', 'user', 'user', 'LISTENER', 'user');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('3', '22', 'Orel', 'zamulko@mail.ru', 'Zamulko', 'Viktor', 'victor', 'LISTENER', 'victor');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('4', '21', 'Novgorod', 'malinovska@yandex.ru', 'Malinovskaia', 'Masha', 'masha', 'LISTENER', 'masha');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('5', '34', 'Grodno', 'vigo@mail.ru', 'Vigo', 'Kiril', 'kiril', 'LISTENER', 'kiril');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('6', '45', 'Minsk', 'prihodko@gmail.com', 'Prihodko', 'Natasha', 'natasha', 'LISTENER', 'natasha');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('7', '25', 'Gomel', 'nichiporenko@tut.by', 'Nichiporenko', 'Anna', 'anna', 'LISTENER', 'anna');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('8', '22', 'Vitebsk', 'vishkina@rambler.ru', 'Vishkina', 'Ulia', 'ulia', 'LISTENER', 'ulia');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('9', '29', 'Orsha', 'homichuk@rambler.ru', 'Homichuk', 'Alexandra', 'alexandra', 'LISTENER', 'alexandra');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('10', '30', 'Krichev', 'pervii@yahoo.com', 'Pervii', 'Petr', 'petr', 'LISTENER', 'petr');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('11', '25', 'Zhabinka', 'gromova@mail.ru', 'Gromova', 'Nina', 'nina', 'LISTENER', 'nina');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('12', '30', 'Moscow', 'vasilevski@tut.by', 'Vasilevski', 'Eugeni', 'eugeni', 'LISTENER', 'eugeni');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('13', '45', 'Minsk', 'hilimonchik@tut.by', 'Hilimonchik', 'Gena', 'gena', 'LISTENER', 'gena');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('14', '34', 'Pinsk', 'mosina@mail.ru', 'Mosina', 'Sveta', 'sveta', 'LISTENER', 'sveta');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('15', '32', 'Kerch', 'kostroma@yandex.ru', 'Kostromskaia', 'Tania', 'tania', 'LISTENER', 'tania');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('16', '22', 'Belgrad', 'kira@rambler.ru', 'Naithky', 'Kira', 'kira', 'LISTENER', 'kira');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('17', '26', 'Murmansk', 'scherbook@yahoo.com', 'Scherbook', 'Luda', 'luda', 'LISTENER', 'luda');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('18', '41', 'Lipensk', 'dulo@yandex.ru', 'Dul', 'Misha', 'misha', 'LISTENER', 'misha');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('19', '34', 'Briansk', 'margo@tut.by', 'Mezhina', 'Margorita', 'margorita', 'LISTENER', 'margorita');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('20', '38', 'Tyla', 'krasotka@mail.ru', 'Prekrasnaia', 'Elena', 'elena', 'LISTENER', 'elena');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('21', '34', 'Rostov', 'malina@yahoo.com', 'Malinina', 'Elizaveta', 'liza', 'LISTENER', 'liza');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('22', '23', 'Mogilev', 'trafimov@gmail.com', 'Trafimov', 'Sergei', 'sergei', 'LISTENER', 'sergei');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('23', '29', 'Kursk', 'lebedev@tut.by', 'Lebedev', 'Stanislav', 'stas', 'LISTENER', 'stas');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('24', '36', 'Novosibirsk', 'kirkorov@gmail.com', 'Kirkorov', 'Philip', 'fil', 'LISTENER', 'fil');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('25', '46', 'Olushta', 'Izmailov@tnt.ru', 'Izmailov', 'Alexandr', 'alex', 'SPEAKER', 'alex');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('26', '50', 'Moscow', 'burunov@tnt.ru', 'Burunov', 'Sergei', 'burunov', 'SPEAKER', 'burunov');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('27', '47', 'Minsk', 'borisov@yaahho.com', 'Borisov', 'Eugeni', 'borisov', 'SPEAKER', 'borisov');
INSERT INTO `heroku_f5939ac599e38c3`.`profile` (`id`, `age`, `city`, `email`, `lastname`, `name`, `password`, `status`, `username`)
VALUES ('28', '34', 'Novgorod', 'mashyk@tut.by', 'Mashyk', 'Mila', 'mila', 'SPEAKER', 'mila');

INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '1');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '2');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '3');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '4');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '5');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '6');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '7');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '8');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '9');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '10');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '11');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '12');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '13');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '14');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '15');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '16');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '17');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '18');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '19');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '20');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '21');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '22');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '23');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '24');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '25');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('1', '26');
INSERT INTO `heroku_f5939ac599e38c3`.`profile_role` (`role_id`, `client_id`)
VALUES ('2', '1');

INSERT INTO `heroku_f5939ac599e38c3`.`comment` (`id`, `message`, `profile`)
VALUES ('1', 'all good', '1');
INSERT INTO `heroku_f5939ac599e38c3`.`comment` (`id`, `message`, `profile`)
VALUES ('2', 'amazing', '2');
INSERT INTO `heroku_f5939ac599e38c3`.`comment` (`id`, `message`, `profile`)
VALUES ('3', 'perfect!!!', '5');
INSERT INTO `heroku_f5939ac599e38c3`.`comment` (`id`, `message`, `profile`)
VALUES ('4', 'very good', '3');
INSERT INTO `heroku_f5939ac599e38c3`.`comment` (`id`, `message`, `profile`)
VALUES ('5', 'funny', '6');
INSERT INTO `heroku_f5939ac599e38c3`.`comment` (`id`, `message`, `profile`)
VALUES ('6', 'perfecto', '9');
INSERT INTO `heroku_f5939ac599e38c3`.`comment` (`id`, `message`, `profile`)
VALUES ('7', 'crazy', '8');
INSERT INTO `heroku_f5939ac599e38c3`.`comment` (`id`, `message`, `profile`)
VALUES ('8', 'bad', '2');
INSERT INTO `heroku_f5939ac599e38c3`.`comment` (`id`, `message`, `profile`)
VALUES ('9', ':(', '9');
INSERT INTO `heroku_f5939ac599e38c3`.`comment` (`id`, `message`, `profile`)
VALUES ('10', 'its very good', '10');
INSERT INTO `heroku_f5939ac599e38c3`.`comment` (`id`, `message`, `profile`)
VALUES ('11', 'i fink this a drive school((', '11');
INSERT INTO `heroku_f5939ac599e38c3`.`comment` (`id`, `message`, `profile`)
VALUES ('12', 'great', '12');
INSERT INTO `heroku_f5939ac599e38c3`.`comment` (`id`, `message`, `profile`)
VALUES ('13', 'amazing', '13');
INSERT INTO `heroku_f5939ac599e38c3`.`comment` (`id`, `message`, `profile`)
VALUES ('14', 'very nice', '14');
INSERT INTO `heroku_f5939ac599e38c3`.`comment` (`id`, `message`, `profile`)
VALUES ('15', 'no bad', '15');
INSERT INTO `heroku_f5939ac599e38c3`.`comment` (`id`, `message`, `profile`)
VALUES ('16', 'this project very good', '16');

INSERT INTO `heroku_f5939ac599e38c3`.`course` (`id`, `course_status`, `days`, `end_course`, `name`, `price`, `start_course`, `section`,
                                `speaker`)
VALUES ('1', 'OPEN', '62', '2021-12-03', 'Java SE', '36000', '2021-10-03', '2', '23');
INSERT INTO `heroku_f5939ac599e38c3`.`course` (`id`, `course_status`, `days`, `end_course`, `name`, `price`, `start_course`, `section`,
                                `speaker`)
VALUES ('2', 'OPEN', '54', '2021-08-26', 'Java EE', '32000', '2021-07-03', '2', '24');
INSERT INTO `heroku_f5939ac599e38c3`.`course` (`id`, `course_status`, `days`, `end_course`, `name`, `price`, `start_course`, `section`,
                                `speaker`)
VALUES ('3', 'CLOSED', '30', '2021-08-19', 'Testing', '19000', '2021-07-19', '2', '25');
INSERT INTO `heroku_f5939ac599e38c3`.`course` (`id`, `course_status`, `days`, `end_course`, `name`, `price`, `start_course`, `section`,
                                `speaker`)
VALUES ('4', 'OPEN', '19', '2021-08-17', 'SQL', '16000', '2021-07-29', '2', '26');


INSERT INTO `heroku_f5939ac599e38c3`.`course` (`id`, `course_status`, `days`, `end_course`, `name`, `price`, `start_course`, `section`,
                                `speaker`)
VALUES ('5', 'OPEN', '19', '2021-08-17', 'Fractional numbers', '16000', '2021-09-01', '1', '26');
INSERT INTO `heroku_f5939ac599e38c3`.`course` (`id`, `course_status`, `days`, `end_course`, `name`, `price`, `start_course`, `section`,
                                `speaker`)
VALUES ('6', 'CLOSED', '19', '2021-08-17', 'Logarithms', '16000', '2021-11-30', '1', '23');
INSERT INTO `heroku_f5939ac599e38c3`.`course` (`id`, `course_status`, `days`, `end_course`, `name`, `price`, `start_course`, `section`,
                                `speaker`)
VALUES ('7', 'OPEN', '29', '2021-08-17', 'Adding numbers', '16000', '2021-10-21', '1', '26');
INSERT INTO `heroku_f5939ac599e38c3`.`course` (`id`, `course_status`, `days`, `end_course`, `name`, `price`, `start_course`, `section`,
                                `speaker`)
VALUES ('8', 'OPEN', '39', '2021-08-17', 'Fractions', '16000', '2021-07-29', '1', '23');

INSERT INTO `heroku_f5939ac599e38c3`.`course` (`id`, `course_status`, `days`, `end_course`, `name`, `price`, `start_course`, `section`,
                                `speaker`)
VALUES ('9', 'OPEN', '19', '2021-08-17', 'Particle movement', '16000', '2021-07-29', '3', '26');
INSERT INTO `heroku_f5939ac599e38c3`.`course` (`id`, `course_status`, `days`, `end_course`, `name`, `price`, `start_course`, `section`,
                                `speaker`)
VALUES ('10', 'CLOSED', '17', '2021-08-17', 'Beams', '16000', '2021-07-29', '3', '26');
INSERT INTO `heroku_f5939ac599e38c3`.`course` (`id`, `course_status`, `days`, `end_course`, `name`, `price`, `start_course`, `section`,
                                `speaker`)
VALUES ('11', 'OPEN', '23', '2021-08-17', 'Friction force', '16000', '2021-07-29', '3', '24');

INSERT INTO `heroku_f5939ac599e38c3`.`course` (`id`, `course_status`, `days`, `end_course`, `name`, `price`, `start_course`, `section`,
                                `speaker`)
VALUES ('12', 'OPEN', '19', '2021-08-17', 'POP', '16000', '2021-07-29', '4', '25');
INSERT INTO `heroku_f5939ac599e38c3`.`course` (`id`, `course_status`, `days`, `end_course`, `name`, `price`, `start_course`, `section`,
                                `speaker`)
VALUES ('13', 'OPEN', '19', '2021-08-17', 'Brakedance', '16000', '2021-12-19', '4', '24');
INSERT INTO `heroku_f5939ac599e38c3`.`course` (`id`, `course_status`, `days`, `end_course`, `name`, `price`, `start_course`, `section`,
                                `speaker`)
VALUES ('14', 'OPEN', '46', '2021-08-17', 'Strip', '16000', '2021-09-14', '4', '25');
INSERT INTO `heroku_f5939ac599e38c3`.`course` (`id`, `course_status`, `days`, `end_course`, `name`, `price`, `start_course`, `section`,
                                `speaker`)
VALUES ('15', 'OPEN', '60', '2021-08-17', 'Folk dances', '16000', '2021-07-29', '4', '26');
INSERT INTO `heroku_f5939ac599e38c3`.`course` (`id`, `course_status`, `days`, `end_course`, `name`, `price`, `start_course`, `section`,
                                `speaker`)
VALUES ('16', 'OPEN', '73', '2021-08-17', 'Tiktonik', '16000', '2021-07-29', '4', '23');

INSERT INTO `heroku_f5939ac599e38c3`.`course` (`id`, `course_status`, `days`, `end_course`, `name`, `price`, `start_course`, `section`,
                                `speaker`)
VALUES ('17', 'CLOSED', '19', '2021-08-17', 'English', '16000', '2021-10-17', '5', '23');
INSERT INTO `heroku_f5939ac599e38c3`.`course` (`id`, `course_status`, `days`, `end_course`, `name`, `price`, `start_course`, `section`,
                                `speaker`)
VALUES ('18', 'CLOSED', '98', '2021-08-17', 'French', '16000', '2021-07-29', '5', '24');
INSERT INTO `heroku_f5939ac599e38c3`.`course` (`id`, `course_status`, `days`, `end_course`, `name`, `price`, `start_course`, `section`,
                                `speaker`)
VALUES ('19', 'CLOSED', '19', '2021-08-17', 'Polish', '16000', '2021-11-29', '5', '25');
INSERT INTO `heroku_f5939ac599e38c3`.`course` (`id`, `course_status`, `days`, `end_course`, `name`, `price`, `start_course`, `section`,
                                `speaker`)
VALUES ('20', 'CLOSED', '119', '2021-08-17', 'Russian', '16000', '2021-12-29', '5', '26');
INSERT INTO `heroku_f5939ac599e38c3`.`course` (`id`, `course_status`, `days`, `end_course`, `name`, `price`, `start_course`, `section`,
                                `speaker`)
VALUES ('21', 'OPEN', '39', '2021-08-17', 'German', '16000', '2021-07-29', '5', '23');

INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('1', '1');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('2', '1');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('3', '2');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('4', '2');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('5', '2');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('6', '3');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('7', '3');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('8', '4');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('9', '4');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('10', '5');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('11', '5');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('12', '6');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('13', '6');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('14', '7');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('15', '8');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('16', '8');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('17', '9');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('18', '9');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('19', '10');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('20', '11');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('21', '12');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('22', '13');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('1', '14');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('2', '14');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('3', '15');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('4', '15');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('5', '16');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('6', '16');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('7', '17');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('8', '17');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('9', '18');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('10', '18');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('11', '19');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('12', '19');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('13', '20');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('14', '20');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('15', '21');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('16', '21');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('17', '21');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('18', '21');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('19', '21');
INSERT INTO `heroku_f5939ac599e38c3`.`course_profile` (`id_profile`, `id_course`)
VALUES ('20', '20');


INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('4', '1');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('4', '2');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('4', '3');

INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('1', '4');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('1', '5');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('1', '6');

INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('2', '7');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('2', '8');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('2', '9');

INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('3', '2');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('3', '10');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('5', '11');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('5', '1');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('6', '2');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('7', '3');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('8', '4');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('9', '5');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('10', '6');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('11', '7');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('12', '8');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('13', '9');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('14', '10');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('15', '12');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('16', '13');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('17', '14');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('18', '15');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('19', '16');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('20', '14');
INSERT INTO `heroku_f5939ac599e38c3`.`course_comment` (`id_course`, id_comment)
VALUES ('21', '11');

INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('1', '120', 'GROUP', '2021-10-03 15:00:00.000000', 'Your first Hello World Programm', '3000', '1');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('2', '120', 'GROUP', '2021-10-08 15:00:00.000000', 'Variables', '3000', '1');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('3', '90', 'GROUP', '2021-10-12 15:00:00.000000', 'Strings', '3000', '1');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('4', '120', 'INDIVIDUAL', '2021-10-17 15:00:00.000000', 'While Loops', '3000', '1');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('5', '90', 'GROUP', '2021-10-22 15:00:00.000000', 'For Loops', '3000', '1');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('6', '120', 'GROUP', '2021-10-27 15:00:00.000000', 'If Statements', '3000', '2');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('7', '120', 'GROUP', '2021-11-02 15:00:00.000000', 'Getting User Input', '3000', '1');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('8', '90', 'GROUP', '2021-11-07 15:00:00.000000', 'Comments, Variable Scope, Do While Loops', '3000', '1');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('9', '120', 'GROUP', '2021-11-12 15:00:00.000000', 'Switch Statements', '3000', '1');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('10', '90', 'GROUP', '2021-11-17 15:00:00.000000', 'Arrays', '3000', '1');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('11', '120', 'GROUP', '2021-11-22 15:00:00.000000', 'Multidimensional Arrays, Nested For Loops', '3000', '1');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('12', '120', 'GROUP', '2021-11-27 15:00:00.000000', 'Classes, Methods, and Objects', '3000', '1');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('13', '90', 'GROUP', '2021-12-03 15:00:00.000000', 'Return Types and Method Parameters', '3000', '1');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('14', '90', 'INDIVIDUAL', '2021-07-03 18:30:00.000000', 'Introduction.', '3500', '2');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('15', '120', 'GROUP', '2021-07-13 18:30:00.000000', 'Java Memory Model (JMM)', '3500', '3');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('16', '120', 'GROUP', '2021-07-18 18:30:00.000000', 'XML', '4000', '2');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('17', '120', 'INDIVIDUAL', '2021-07-22 18:30:00.000000', ' JSON', '3000', '2');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('18', '120', 'GROUP', '2021-07-26 18:30:00.000000', 'HTTP/HTTPS', '3500', '2');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('19', '120', 'INDIVIDUAL', '2021-07-30 18:30:00.000000', 'SQL', '3500', '2');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('20', '120', 'GROUP', '2021-08-03 18:30:00.000000', 'Design patterns', '3500', '2');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('21', '120', 'GROUP', '2021-08-09 18:30:00.000000', 'Java Servlets API', '3500', '2');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('22', '120', 'GROUP', '2021-08-13 18:30:00.000000', 'Java Persistence API (JPA) & Hibernate', '3500', '2');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('23', '120', 'GROUP', '2021-08-16 18:30:00.000000', 'Enterprise Java Beans (EJB)', '3500', '2');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('24', '120', 'INDIVIDUAL', '2021-08-19 18:30:00.000000', ' Spring Framework', '3500', '2');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('25', '90', 'GROUP', '2021-08-26 18:30:00.000000', ' Spring Security', '3500', '2');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('26', '120', 'GROUP', '2021-07-19 16:30:00.000000', 'Testing Basics', '2500', '3');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('27', '90', 'GROUP', '2021-07-24 16:30:00.000000', 'Test design and test analysis', '2500', '3');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('28', '120', 'GROUP', '2021-07-29 16:30:00.000000', 'Tester tools', '2500', '3');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('29', '120', 'GROUP', '2021-08-06 16:30:00.000000', 'API testing', '2500', '3');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('30', '120', 'GROUP', '2021-08-11 16:30:00.000000', 'Testing mobile applications', '2500', '3');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('31', '120', 'INDIVIDUAL', '2021-08-19 16:30:00.000000', 'Basics of Automation', '2500', '3');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('32', '120', 'GROUP', '2021-07-29 19:30:00.000000', 'Introduction to Databases and SQL', '4000', '4');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('33', '120', 'GROUP', '2021-08-09 19:30:00.000000', 'Basics of Database Normalization', '4000', '4');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('34', '120', 'GROUP', '2021-08-17 19:30:00.000000', 'Advanced SQL', '4000', '4');


INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('35', '120', 'INDIVIDUAL', '2021-07-03 19:30:00.000000', 'Fractional numbers first lesson', '4000', '5');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('36', '120', 'INDIVIDUAL', '2021-07-23 19:30:00.000000', 'Fractional numbers second lesson', '4000', '5');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('37', '120', 'INDIVIDUAL', '2021-08-17 19:30:00.000000', 'Fractional numbers third lesson', '4000', '5');

INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('38', '120', 'GROUP', '2021-08-17 19:30:00.000000', 'Logarithms first lesson', '4000', '6');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('39', '90', 'GROUP', '2021-09-17 19:30:00.000000', 'Logarithms second lesson', '2600', '6');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('40', '120', 'GROUP', '2021-09-17 19:30:00.000000', 'Logarithms third lesson', '2600', '6');

INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('41', '120', 'GROUP', '2021-08-17 19:30:00.000000', 'Adding numbers first lesson', '3600', '7');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('42', '120', 'GROUP', '2021-09-11 19:30:00.000000', 'Adding numbers second lesson', '2600', '7');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('43', '120', 'GROUP', '2021-10-09 19:30:00.000000', 'Adding numbers third lesson', '4000', '7');

INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('44', '120', 'GROUP', '2021-10-09 19:30:00.000000', 'Fractions first lesson', '4000', '8');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('45', '120', 'INDIVIDUAL', '2021-11-09 18:00:00.000000', 'Fractionss second lesson', '4550', '8');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('46', '120', 'GROUP', '2021-12-09 19:30:00.000000', 'Fractions third lesson', '4000', '8');

INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('47', '120', 'GROUP', '2021-10-03 19:30:00.000000', 'Particle movement first lesson', '3000', '9');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('48', '90', 'GROUP', '2021-11-09 19:30:00.000000', 'Particle movement second lesson', '4200', '9');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('49', '120', 'GROUP', '2021-12-10 19:30:00.000000', 'Particle movement third lesson', '4800', '9');

INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('50', '120', 'GROUP', '2021-07-29 19:30:00.000000', 'Beams first lesson', '4500', '10');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('51', '120', 'GROUP', '2021-08-10 19:30:00.000000', 'Beams second lesson', '4800', '10');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('52', '120', 'GROUP', '2021-09-10 19:30:00.000000', 'Beams third lesson', '4800', '10');

INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('53', '120', 'GROUP', '2021-08-10 14:30:00.000000', 'Friction force first lesson', '5800', '11');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('54', '120', 'GROUP', '2021-09-10 19:30:00.000000', 'Friction force second lesson', '4800', '11');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('55', '120', 'GROUP', '2021-10-10 15:30:00.000000', 'Friction force third lesson', '6800', '11');

INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('56', '120', 'GROUP', '2021-09-10 15:30:00.000000', 'POP first lesson', '6800', '12');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('57', '120', 'GROUP', '2021-09-20 15:30:00.000000', 'POP second lesson', '6800', '12');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('58', '120', 'GROUP', '2021-09-30 15:30:00.000000', 'POP third lesson', '6800', '12');

INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('59', '120', 'GROUP', '2021-10-11 15:30:00.000000', 'Brakedance first lesson', '6800', '13');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('60', '90', 'INDIVIDUAL', '2021-10-19 15:30:00.000000', 'Brakedance second lesson', '6800', '13');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('61', '120', 'GROUP', '2021-10-29 15:30:00.000000', 'Brakedance third lesson', '6800', '13');

INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('62', '120', 'GROUP', '2021-10-12 15:30:00.000000', 'Strip first lesson', '6800', '14');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('63', '90', 'GROUP', '2021-10-19 15:30:00.000000', 'Strip second lesson', '6800', '14');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('64', '120', 'GROUP', '2021-11-30 15:30:00.000000', 'Strip third lesson', '6800', '14');

INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('65', '120', 'GROUP', '2021-10-30 15:30:00.000000', 'Folk dances first lesson', '1800', '15');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('66', '120', 'GROUP', '2021-11-09 17:30:00.000000', 'Folk dances second lesson', '3400', '15');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('67', '120', 'GROUP', '2021-11-30 18:00:00.000000', 'Folk dances third lesson', '3800', '15');

INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('68', '120', 'GROUP', '2021-09-30 18:00:00.000000', 'Tiktonik first lesson', '2800', '16');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('69', '120', 'GROUP', '2021-10-11 18:00:00.000000', 'Tiktonik second lesson', '4800', '16');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('70', '120', 'GROUP', '2021-11-21 19:00:00.000000', 'Tiktonik third lesson', '1800', '16');

INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('71', '120', 'GROUP', '2021-10-21 19:00:00.000000', 'English first lesson', '1800', '17');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('72', '90', 'GROUP', '2021-11-21 18:00:00.000000', 'English second lesson', '1800', '17');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('73', '120', 'GROUP', '2021-12-21 19:00:00.000000', 'English third lesson', '1800', '17');

INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('74', '120', 'GROUP', '2021-09-21 19:00:00.000000', 'French first lesson', '1800', '18');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('75', '120', 'INDIVIDUAL', '2021-10-09 18:00:00.000000', 'French second lesson', '1800', '18');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('76', '120', 'GROUP', '2021-10-19 19:00:00.000000', 'French third lesson', '1800', '18');

INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('77', '90', 'GROUP', '2021-10-01 19:00:00.000000', 'Polish first lesson', '2800', '19');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('78', '120', 'GROUP', '2021-10-11 19:00:00.000000', 'Polish second lesson', '2800', '19');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('79', '120', 'GROUP', '2021-10-16 19:00:00.000000', 'Polish third lesson', '2800', '19');

INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('80', '120', 'GROUP', '2021-10-16 19:00:00.000000', 'Russian first lesson', '2800', '20');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('81', '120', 'GROUP', '2021-10-20 19:00:00.000000', 'Russian second lesson', '3800', '20');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('82', '120', 'GROUP', '2021-10-21 19:00:00.000000', 'Russian third lesson', '2800', '20');

INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('83', '120', 'GROUP', '2021-10-01 19:00:00.000000', 'German first lesson', '2800', '20');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('84', '120', 'INDIVIDUAL', '2021-10-11 19:00:00.000000', 'German second lesson', '2800', '20');
INSERT INTO `heroku_f5939ac599e38c3`.`lesson` (`id`, `duration`, `lesson_status`, `local_date_time`, `name`, `price`, `course`)
VALUES ('85', '90', 'GROUP', '2021-11-21 19:00:00.000000', 'German third lesson', '2800', '20');





