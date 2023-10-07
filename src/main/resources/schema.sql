create table if not exists taco_order
(
    id              identity,
    delivery_Name   varchar(50) not null,
    delivery_Street varchar(50) not null,
    delivery_City   varchar(50) not null,
    delivery_State  varchar(2)  not null,
    delivery_Zip    varchar(10) not null,
    cc_number       varchar(16) not null,
    cc_expiration   varchar(5)  not null,
    cc_cvv          varchar(3)  not null,
    user_id         bigint      not null,
    placed_at       timestamp   not null
);

create table if not exists taco
(
    id             identity,
    name           varchar(50) not null,
    taco_order     bigint      not null,
    taco_order_key bigint      not null,
    created_at     timestamp   not null
);

create table if not exists ingredient_ref
(
    ingredient varchar(4) not null,
    taco       bigint     not null,
    taco_key   bigint     not null
);
create table if not exists ingredient
(
    id   varchar(4)  not null unique,
    name varchar(25) not null,
    type varchar(10) not null
);
create table if not exists user_acc (
    id bigint not null primary key,
    city varchar(255),
    full_name varchar(255),
    password varchar(255),
    phone_number varchar(255),
    state varchar(255),
    street varchar(255),
    username varchar(255),
    zip varchar(255)
);
alter table taco
    add foreign key (taco_order) references taco_order (id);
alter table ingredient_ref
    add foreign key (ingredient) references ingredient (id);