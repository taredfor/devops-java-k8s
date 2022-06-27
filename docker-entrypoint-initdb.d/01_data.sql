INSERT INTO users("id", "username", "password")
VALUES (1, 'admin', '$argon2id$v=19$m=4096,t=3,p=1$EKafiERPY6EZq1xDjJi/nA$eouD+lnrkCIdRr+aV7g6OcvO/8mYz4vB6fw2Xhi6k4Q'),
       (2, 'student', '$argon2id$v=19$m=4096,t=3,p=1$E/VPfGBdCIpj6MNmy4kPzA$TU1ZvkzaNDknWXoDz2ShVAfpsgENadPIpNV00PUk3Gk');

ALTER SEQUENCE users_id_seq RESTART WITH 3;

INSERT INTO tokens("token", "userId")
VALUES ('6NSb+2kcdKF44ut4iBu+dm6YLu6pakWapvxHtxqaPgMr5iRhox/HlhBerAZMILPjwnRtXms+zDfVTLCsao9nuw==', 1);


INSERT INTO cards("id", "ownerId", "number", "balance")
VALUES (1, 1, '**** *888', 50000),
       (2, 2, '**** *999', 90000);

ALTER SEQUENCE cards_id_seq RESTART WITH 3;
