insert into team (id, "name")
values (1, 'E1'),
       (2, 'E2'),
       (3, 'E3');

insert into player (id, "name", id_team)
values (1, 'J1', 1),
       (2, 'J2', 1),
       (3, 'J3', 2),
       (4, 'J4', 2),
       (5, 'J5', 3),
       (6, 'J6', 3);

insert into match(id, id_team_A, id_team_B, stadium, "datetime")
values (1, 1, 2, 'S1', '2023-01-01 10:00:00'),
       (2, 2, 3, 'S2', '2023-01-01 14:00:00'),
       (3, 1, 3, 'S3', '2023-01-01 18:00:00');

-- Score : E1 4 vs 2 E2
insert into player_score(id, id_match, id_player, "minute", own_goal)
values (1, 1, 1, 10, false),
       (2, 1, 1, 20, false),
       (3, 1, 1, 30, false),
       (4, 1, 2, 40, true),
       (5, 1, 3, 50, false),
       (6, 1, 4, 60, true);

-- Score : E2 2 vs 0 E2
insert into player_score(id, id_match, id_player, "minute", own_goal)
values (7, 2, 3, 70, false),
       (8, 2, 6, 80, true);