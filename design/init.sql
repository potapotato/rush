create table if not exists Permission
(
    id             int auto_increment
        primary key,
    permission_str varchar(255) not null
);

create table if not exists Role
(
    id   int auto_increment
        primary key,
    name varchar(100) not null
);

create table if not exists Role_permission
(
    id            int auto_increment
        primary key,
    role_id       int not null,
    permission_id int not null,
    constraint Role_permission_Permission_id_fk
        foreign key (permission_id) references Permission (id),
    constraint Role_permission_Role_id_fk
        foreign key (role_id) references Role (id)
);

create table if not exists User
(
    id           int auto_increment
        primary key,
    username     varchar(50)                          not null,
    password     varchar(255)                         not null,
    nickname     varchar(255)                         not null,
    salt         varchar(10)                          not null,
    icon_url     varchar(255)                         null,
    created_time datetime   default CURRENT_TIMESTAMP not null,
    email        varchar(50)                          null,
    enabled      tinyint(1) default 1                 null,
    constraint User_nickname_uindex
        unique (nickname),
    constraint User_username_uindex
        unique (username)
)
    comment '用户表';

create table if not exists Course
(
    id             int auto_increment
        primary key,
    name           varchar(100)                         not null,
    description    varchar(255)                         not null,
    img_url        varchar(255)                         null,
    created_time   datetime   default CURRENT_TIMESTAMP not null,
    create_user_id int                                  not null,
    enabled        tinyint(1) default 1                 null,
    constraint Course_name_uindex
        unique (name),
    constraint Course_User_id_fk
        foreign key (create_user_id) references User (id)
);

create table if not exists Chapter
(
    id           int auto_increment
        primary key,
    course_id    int                                  not null,
    name         varchar(100)                         not null,
    created_time datetime   default CURRENT_TIMESTAMP not null,
    enabled      tinyint(1) default 1                 null,
    constraint Chapter_name_uindex
        unique (name),
    constraint Chapter_Course_id_fk
        foreign key (course_id) references Course (id)
);

create table if not exists Course_role
(
    id        int auto_increment
        primary key,
    course_id int not null,
    role_id   int not null,
    constraint Course_role_Course_id_fk
        foreign key (course_id) references Course (id),
    constraint Course_role_Role_id_fk
        foreign key (role_id) references Role (id)
);

create table if not exists Section
(
    id           int auto_increment
        primary key,
    chapter_id   int                                  not null,
    name         varchar(100)                         not null,
    created_time datetime   default CURRENT_TIMESTAMP not null,
    enabled      tinyint(1) default 1                 null,
    constraint Section_name_uindex
        unique (name),
    constraint Section_Chapter_id_fk
        foreign key (chapter_id) references Chapter (id)
);

create table if not exists Question
(
    id             int auto_increment
        primary key,
    section_id     int                                  not null,
    question_url   varchar(255)                         null,
    question_text  varchar(1000)                        null,
    answer_url     varchar(255)                         null,
    answer_text    varchar(1000)                        null,
    score          int                                  null,
    type           int                                  null,
    created_time   datetime   default CURRENT_TIMESTAMP not null,
    create_user_id int                                  not null,
    enabled        tinyint(1) default 1                 not null,
    constraint Question_answer_text_uindex
        unique (answer_text),
    constraint Question_answer_url_uindex
        unique (answer_url),
    constraint Question_question_text_uindex
        unique (question_text),
    constraint Question_question_url_uindex
        unique (question_url),
    constraint Question_Section_id_fk
        foreign key (section_id) references Section (id),
    constraint Question_User_id_fk
        foreign key (create_user_id) references User (id)
);

create table if not exists User_role
(
    id      int auto_increment
        primary key,
    user_id int not null,
    role_id int not null,
    constraint User_role_Role_id_fk
        foreign key (role_id) references Role (id),
    constraint User_role_User_id_fk
        foreign key (user_id) references User (id)
);

