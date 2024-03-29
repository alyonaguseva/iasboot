insert into measured_parameter VALUES(5, 'Осредненные значения угла по оси X', 0);
insert into measured_parameter VALUES(6, 'Осредненные значения угла по оси Y', 0);
insert into measured_parameter VALUES(7, 'Осредненные значения температуры', 0);
insert into measured_parameter VALUES(8, 'Среднеквадратическая погрешность угла по оси X', 0);
insert into measured_parameter VALUES(9, 'Среднеквадратическая погрешность угла по оси Y', 0);
insert into measured_parameter VALUES(10, 'Температура', 0);

update sensor set name = 'ИС3701.Т-com1' where id = 1;
update sensor set name = 'ИС3701.Т-com2' where id = 2;
update sensor set name = 'ИС3701.Т-com3' where id = 3;

update signal set id_measured_parameter = 10 where id in (1,2,3);

insert into sensor VALUES (88, 'ИН1.01', 1, 'Оптические', 1, false);
insert into sensor VALUES (89, 'ИН1.02', 1, 'Оптические', 1, false);
insert into sensor VALUES (90, 'ИН1.03', 1, 'Оптические', 1, false);
insert into sensor VALUES (91, 'ИН1.04', 1, 'Оптические', 1, false);
insert into sensor VALUES (92, 'ИН1.05', 1, 'Оптические', 1, false);
insert into sensor VALUES (93, 'ИН1.06', 1, 'Оптические', 1, false);
insert into sensor VALUES (94, 'ИН1.07', 1, 'Оптические', 1, false);
insert into sensor VALUES (95, 'ИН1.08', 1, 'Оптические', 1, false);
insert into sensor VALUES (96, 'ИН1.09', 1, 'Оптические', 1, false);
insert into sensor VALUES (97, 'ИН1.10', 1, 'Оптические', 1, false);
insert into sensor VALUES (98, 'ИН1.11', 1, 'Оптические', 1, false);
insert into sensor VALUES (99, 'ИН1.12', 1, 'Оптические', 1, false);
insert into sensor VALUES (100, 'ИН1.13', 1, 'Оптические', 1, false);
insert into sensor VALUES (101, 'ИН1.14', 1, 'Оптические', 1, false);
insert into sensor VALUES (102, 'ИН2.01', 1, 'Оптические', 1, false);
insert into sensor VALUES (103, 'ИН2.02', 1, 'Оптические', 1, false);
insert into sensor VALUES (104, 'ИН2.03', 1, 'Оптические', 1, false);
insert into sensor VALUES (105, 'ИН2.04', 1, 'Оптические', 1, false);
insert into sensor VALUES (106, 'ИН2.05', 1, 'Оптические', 1, false);
insert into sensor VALUES (107, 'ИН2.06', 1, 'Оптические', 1, false);
insert into sensor VALUES (108, 'ИН2.07', 1, 'Оптические', 1, false);
insert into sensor VALUES (109, 'ИН2.08', 1, 'Оптические', 1, false);
insert into sensor VALUES (110, 'ИН3.01', 1, 'Оптические', 1, false);
insert into sensor VALUES (111, 'ИН3.02', 1, 'Оптические', 1, false);
insert into sensor VALUES (112, 'ИН3.03', 1, 'Оптические', 1, false);
insert into sensor VALUES (113, 'ИН3.04', 1, 'Оптические', 1, false);
insert into sensor VALUES (114, 'ИН3.05', 1, 'Оптические', 1, false);
insert into sensor VALUES (115, 'ИН3.06', 1, 'Оптические', 1, false);
insert into sensor VALUES (116, 'ИН3.07', 1, 'Оптические', 1, false);
insert into sensor VALUES (117, 'ИН3.08', 1, 'Оптические', 1, false);
insert into sensor VALUES (118, 'ИН3.09', 1, 'Оптические', 1, false);
insert into sensor VALUES (119, 'ИН3.10', 1, 'Оптические', 1, false);
insert into sensor VALUES (120, 'ИН3.11', 1, 'Оптические', 1, false);
insert into sensor VALUES (121, 'ИН3.12', 1, 'Оптические', 1, false);

insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (88, 88, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (89, 88, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (90, 88, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (91, 88, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (92, 88, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (93, 89, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (94, 89, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (95, 89, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (96, 89, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (97, 89, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (98, 90, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (99, 90, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (100, 90, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (101, 90, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (102, 90, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (103, 91, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (104, 91, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (105, 91, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (106, 91, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (107, 91, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (108, 92, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (109, 92, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (110, 92, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (111, 92, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (112, 92, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (113, 93, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (114, 93, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (115, 93, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (116, 93, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (117, 93, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (118, 94, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (119, 94, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (120, 94, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (121, 94, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (122, 94, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (123, 95, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (124, 95, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (125, 95, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (126, 95, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (127, 95, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (128, 96, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (129, 96, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (130, 96, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (131, 96, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (132, 96, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (133, 97, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (134, 97, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (135, 97, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (136, 97, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (137, 97, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (138, 98, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (139, 98, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (140, 98, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (141, 98, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (142, 98, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (143, 99, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (144, 99, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (145, 99, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (146, 99, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (147, 99, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (148, 100, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (149, 100, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (150, 100, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (151, 100, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (152, 100, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (153, 101, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (154, 101, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (155, 101, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (156, 101, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (157, 101, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (158, 102, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (159, 102, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (160, 102, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (161, 102, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (162, 102, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (163, 103, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (164, 103, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (165, 103, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (166, 103, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (167, 103, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (168, 104, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (169, 104, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (170, 104, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (171, 104, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (172, 104, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (173, 105, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (174, 105, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (175, 105, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (176, 105, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (177, 105, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (178, 106, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (179, 106, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (180, 106, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (181, 106, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (182, 106, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (183, 107, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (184, 107, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (185, 107, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (186, 107, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (187, 107, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (188, 108, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (189, 108, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (190, 108, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (191, 108, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (192, 108, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (193, 109, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (194, 109, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (195, 109, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (196, 109, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (197, 109, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (198, 110, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (199, 110, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (200, 110, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (201, 110, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (202, 110, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (203, 111, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (204, 111, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (205, 111, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (206, 111, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (207, 111, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (208, 112, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (209, 112, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (210, 112, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (211, 112, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (212, 112, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (213, 113, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (214, 113, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (215, 113, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (216, 113, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (217, 113, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (218, 114, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (219, 114, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (220, 114, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (221, 114, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (222, 114, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (223, 115, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (224, 115, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (225, 115, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (226, 115, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (227, 115, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (228, 116, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (229, 116, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (230, 116, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (231, 116, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (232, 116, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (233, 117, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (234, 117, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (235, 117, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (236, 117, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (237, 117, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (238, 118, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (239, 118, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (240, 118, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (241, 118, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (242, 118, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (243, 119, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (244, 119, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (245, 119, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (246, 119, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (247, 119, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (248, 120, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (249, 120, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (250, 120, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (251, 120, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (252, 120, 1, 9);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (253, 121, 1, 5);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (254, 121, 1, 6);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (255, 121, 1, 7);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (256, 121, 1, 8);
insert into signal(id, id_sensor, id_type, id_measured_parameter) VALUES (257, 121, 1, 9);