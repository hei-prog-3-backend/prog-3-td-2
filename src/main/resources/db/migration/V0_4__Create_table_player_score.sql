create table if not exists player_score
(
    id        serial not null primary key,
    id_match  integer references match (id),
    id_player integer references player (id),
    "minute"  integer,
    own_goal  boolean
);
