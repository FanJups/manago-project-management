drop table if exists customer;
drop table if exists customer_project;
drop table if exists project;

create table customer (
       customer_id bigint not null auto_increment,
        address varchar(255),
        city varchar(255),
        company varchar(255),
        email varchar(255) not null,
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        zip_code varchar(255),
        primary key (customer_id)
    ) engine=InnoDB;

create table customer_project (
       customer_id bigint not null,
        name varchar(255) not null,
        primary key (customer_id, name)
    ) engine=InnoDB;

create table project (
       name varchar(255) not null,
        description varchar(255) not null,
        primary key (name)
    ) engine=InnoDB;

create table task (
       task_id bigint not null auto_increment,
        name varchar(255) not null,
        parent_id bigint,
        primary key (task_id)
    ) engine=InnoDB;

alter table customer_project
       add constraint FKbkacyicaqw3fxmwei6aujpmn7
       foreign key (name)
       references project (name);

alter table customer_project
       add constraint FKnb0kcjkl2j8xdssyk4l6p5ja6
       foreign key (customer_id)
       references customer (customer_id);

alter table task
       add constraint FK82ogu5quub0bhyuhp25riy7pf
       foreign key (parent_id)
       references task (task_id);