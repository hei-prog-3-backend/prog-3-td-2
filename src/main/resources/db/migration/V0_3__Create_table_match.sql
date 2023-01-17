create table if not exists match
(
    id         serial                      not null primary key,
    id_team_A  integer references team (id),
    id_team_B  integer references team (id),
    stadium    varchar,
    "datetime" timestamp without time zone not null
);