insert into player (id, "name", id_team, guardian)
values (7, 'G1', 1, true),
       (8, 'G2', 2, true),
       (9, 'G3', 3, true);


alter sequence team_id_seq restart with 4;
alter sequence player_id_seq restart with 10;
alter sequence match_id_seq restart with 4;
alter sequence player_score_id_seq restart with 15;