INSERT INTO users(username, password)
VALUES ('test1', '$2y$10$ml2fyoXCe4/sQLgvRe0YSuQkhUrdEGdzSRfPYiorNn8CNe9iOmhqy');

INSERT INTO phones(brand, model, network_technology, bands_2g, bands_3g, bands_4g, bands_5g)
VALUES ('Samsung', 'Galaxy S9', 'GSM / CDMA / HSPA / EVDO / LTE',
        'GSM 850 / 900 / 1800 / 1900 - SIM 1 & SIM 2 (dual-SIM model only)\nCDMA 800 / 1900 - USA',
        'HSDPA 850 / 900 / 1700(AWS) / 1900 / 2100 - Global, USA\nCDMA2000 1xEV-DO - USA',
        '1, 2, 3, 4, 5, 7, 8, 12, 13, 17, 18, 19, 20, 25, 26, 28, 32, 38, 39, 40, 41, 66 - Global\n1, 2, 3, 4, 5, 7, 8, 12, 13, 14, 17, 18, 19, 20, 25, 26, 28, 29, 30, 38, 39, 40, 41, 46, 66, 71 - USA',
        null),
       ('Samsung', 'Galaxy S8', 'GSM / HSPA / LTE',
        'GSM 850 / 900 / 1800 / 1900 - SIM 1 & SIM 2 (dual-SIM model only)',
        'HSDPA 850 / 900 / 1700(AWS) / 1900 / 2100',
        '1, 2, 3, 4, 5, 7, 8, 12, 13, 17, 18, 19, 20, 25, 26, 28, 32, 66, 38, 39, 40, 41',
        null),
       ('Samsung', 'Galaxy S8', 'GSM / HSPA / LTE',
        'GSM 850 / 900 / 1800 / 1900 - SIM 1 & SIM 2 (dual-SIM model only)',
        'HSDPA 850 / 900 / 1700(AWS) / 1900 / 2100',
        '1, 2, 3, 4, 5, 7, 8, 12, 13, 17, 18, 19, 20, 25, 26, 28, 32, 66, 38, 39, 40, 41',
        null),
       ('Motorola', 'Nexus 6', 'GSM / CDMA / HSPA / LTE',
        'GSM 850 / 900 / 1800 / 1900 - all models\nCDMA 800 / 1900 - XT1103',
        'HSDPA 800 / 850 / 900 / 1700 / 1800 / 1900 / 2100 - XT1100\nHSDPA 850 / 900 / 1700 / 1900 / 2100 - XT1103\nHSDPA 850 / 900 / 1900 / 2100 - Verizon',
        '1, 3, 5, 7, 8, 9, 19, 20, 28, 41 - XT1100\n2, 3, 4, 5, 7, 12, 13, 17, 25, 26, 29, 41 - XT1103\n4, 13 - Verizon',
        null),
       ('Oneplus', '9', 'GSM / CDMA / HSPA / LTE / 5G',
        'GSM 850 / 900 / 1800 / 1900 - SIM 1 & SIM 2\nCDMA 800 / 1900',
        'HSDPA 800 / 850 / 900 / 1700(AWS) / 1800 / 1900 / 2100',
        '1, 2, 3, 4, 5, 7, 8, 12, 13, 17, 18, 19, 20, 25, 26, 28, 32, 38, 39, 40, 41, 66 - EU\n1, 2, 3, 4, 5, 7, 8, 12, 13, 17, 18, 19, 20, 25, 26, 28, 30, 32, 38, 39, 40, 41, 46, 48, 66, 71 - NA\n1, 2, 3, 4, 5, 7, 8, 12, 17, 18, 19, 20, 26, 34, 38, 39, 40, 41, 46 - IN\n1, 2, 3, 4, 5, 7, 8, 12, 17, 18, 19, 20, 26, 34, 38, 39, 40, 41 - CN',
        '1, 3, 5, 7, 8, 20, 28, 38, 40, 41, 66, 78 SA/NSA - EU\n1, 2, 3, 5, 7, 8, 20, 25, 28, 38, 40, 41, 48, 66, 71, 77, 78 SA/NSA - NA\n41, 78 SA/NSA - IN\n1, 3, 28, 41, 78, 79 SA/NSA - CN'),
       ('Apple', 'iPhone 13', 'GSM / CDMA / HSPA / EVDO / LTE / 5G',
        'GSM 850 / 900 / 1800 / 1900 - SIM 1 & SIM 2 (dual-SIM)\nCDMA 800 / 1900',
        'HSDPA 850 / 900 / 1700(AWS) / 1900 / 2100\nCDMA2000 1xEV-DO',
        '1, 2, 3, 4, 5, 7, 8, 12, 13, 17, 18, 19, 20, 25, 26, 28, 30, 32, 34, 38, 39, 40, 41, 42, 46, 48, 66 - A2633, A2634, A2635\n1, 2, 3, 4, 5, 7, 8, 11, 12, 13, 14, 17, 18, 19, 20, 21, 25, 26, 28, 29, 30, 32, 34, 38, 39, 40, 41, 42, 46, 48, 66, 71 - A2482, A2631',
        '1, 2, 3, 5, 7, 8, 12, 20, 25, 28, 29, 30, 38, 40, 41, 48, 66, 71, 77, 78, 79 Sub6/mmWave - A2633\n1, 2, 3, 5, 7, 8, 12, 20, 25, 28, 29, 30, 38, 40, 41, 48, 66, 71, 77, 78, 79, 258, 260, 261 SA/NSA/Sub6/mmWave - A2482\n1, 2, 3, 5, 7, 8, 12, 20, 25, 28, 29, 30, 38, 40, 41, 48, 66, 71, 77, 78, 79 SA/NSA/Sub6 - A2631\n1, 2, 3, 5, 7, 8, 12, 20, 25, 28, 30, 38, 40, 41, 48, 66, 77, 78, 79 SA/NSA/Sub6 - A2634, A2635'),
       ('Apple', 'iPhone 12', 'GSM / CDMA / HSPA / EVDO / LTE / 5G',
        'GSM 850 / 900 / 1800 / 1900 - SIM 1 & SIM 2 (dual-SIM) - for China\nCDMA 800 / 1900',
        'HSDPA 850 / 900 / 1700(AWS) / 1900 / 2100\nCDMA2000 1xEV-DO',
        '1, 2, 3, 4, 5, 7, 8, 12, 13, 14, 17, 18, 19, 20, 25, 26, 28, 29, 30, 32, 34, 38, 39, 40, 41, 42, 46, 48, 66, 71 - A2172\n1, 2, 3, 4, 5, 7, 8, 12, 13, 17, 18, 19, 20, 25, 26, 28, 30, 32, 34, 38, 39, 40, 41, 42, 46, 48, 66 - A2403, A2404\n1, 2, 3, 4, 5, 7, 8, 12, 13, 14, 17, 18, 19, 20, 21, 25, 26, 28, 29, 30, 32, 34, 38, 39, 40, 41, 42, 46, 48, 66, 71 - A2402',
        '1, 2, 3, 5, 7, 8, 12, 20, 25, 28, 38, 40, 41, 66, 71, 77, 78, 79, 260, 261 Sub6/mmWave - A2172\n1, 2, 3, 5, 7, 8, 12, 20, 25, 28, 38, 40, 41, 66, 77, 78, 79 Sub6 - A2402, A2403, A2404'),
       ('Apple', 'iPhone 11', 'GSM / CDMA / HSPA / EVDO / LTE',
        'GSM 850 / 900 / 1800 / 1900 - SIM 1 & SIM 2 (dual-SIM) - for China\nCDMA 800 / 1900',
        'HSDPA 850 / 900 / 1700(AWS) / 1900 / 2100\nCDMA2000 1xEV-DO',
        '1, 2, 3, 4, 5, 7, 8, 11, 12, 13, 17, 18, 19, 20, 21, 25, 26, 28, 29, 30, 32, 34, 38, 39, 40, 41, 42, 46, 48, 66 - A2221\n1, 2, 3, 4, 5, 7, 8, 12, 13, 14, 17, 18, 19, 20, 25, 26, 29, 30, 34, 38, 39, 40, 41, 42, 46, 48, 66, 71 - A2111, A2223',
        null),
       ('Apple', 'iPhone X', 'GSM / HSPA / LTE',
        'GSM 850 / 900 / 1800 / 1900',
        'HSDPA 850 / 900 / 1700(AWS) / 1900 / 2100',
        '1, 2, 3, 4, 5, 7, 8, 12, 13, 17, 18, 19, 20, 25, 26, 28, 29, 30, 34, 38, 39, 40, 41, 66',
        null),
       ('Nokia', '3310', 'GSM',
        'GSM 900 / 1800',
        null,
        null,
        null);