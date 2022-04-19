create table users
(
    id         bigserial primary key,
    login      varchar(30) unique not null,
    password   varchar(80) not null,
    email      varchar(50) unique not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

-- password: 123456
insert into users (login, password, email)
values ('user', '$2a$12$eSzHnbBEOLjRs7kqw5rZJ.Z9.ipo9YzthjrlYDwenGvQJdkgTsUme', 'bob_johnson@gmail.com'),
       ('admin', '$$2a$12$eSzHnbBEOLjRs7kqw5rZJ.Z9.ipo9YzthjrlYDwenGvQJdkgTsUme', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);
