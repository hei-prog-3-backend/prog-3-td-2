create table if not exists player
(
    id       serial  not null primary key,
    "name"   varchar,
    guardian boolean not null default false,
    id_team  integer references team (id)
);