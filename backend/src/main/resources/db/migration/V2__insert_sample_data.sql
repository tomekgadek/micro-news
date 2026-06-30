-- Dodanie sekcji
INSERT INTO section (id, name) VALUES (1, 'Sport');
INSERT INTO section (id, name) VALUES (2, 'Film');
INSERT INTO section (id, name) VALUES (3, 'Nauka');

-- Dodanie użytkowników
INSERT INTO user (id, firstname, surname, city) VALUES (1, 'Jan', 'Kowalski', 'Warszawa');
INSERT INTO user (id, firstname, surname, city) VALUES (2, 'Anna', 'Nowak', 'Kraków');
INSERT INTO user (id, firstname, surname, city) VALUES (3, 'Piotr', 'Wiśniewski', 'Gdańsk');
INSERT INTO user (id, firstname, surname, city) VALUES (4, 'Maria', 'Wójcik', 'Wrocław');
INSERT INTO user (id, firstname, surname, city) VALUES (5, 'Krzysztof', 'Kamiński', 'Poznań');

-- Dodanie loginów (hasła do zmiany, dane testowe)
INSERT INTO login (login, pass, id_user) VALUES ('jkowalski', 'password123', 1);
INSERT INTO login (login, pass, id_user) VALUES ('anowak', 'password456', 2);
INSERT INTO login (login, pass, id_user) VALUES ('pwisniewski', 'securePass7', 3);
INSERT INTO login (login, pass, id_user) VALUES ('mwojcik', 'maria2026', 4);
INSERT INTO login (login, pass, id_user) VALUES ('kkaminski', 'adminPassword', 5);

-- Dodanie artykułów
-- Sport (id_section = 1)
INSERT INTO article (id, title, content, date_art, id_section) VALUES 
(1, 'Robert Lewandowski w Chicago Fire. Co z grą w reprezentacji Polski?', 'Robert Lewandowski przenosi się do ligi MLS, podpisując kontrakt z klubem Chicago Fire. Władze klubu potwierdziły pozyskanie ikony światowego futbolu. Ruch ten wywołał falę spekulacji dotyczących dalszej kariery Lewandowskiego w reprezentacji Polski oraz jego roli w nadchodzących meczach eliminacyjnych.', '2026-06-30 17:45:00', 1),
(2, 'Wielki powrót Hurkacza na Wimbledon. Polak ograł faworyta', 'Hubert Hurkacz sprawił ogromną niespodziankę na kortach Wimbledonu, eliminując wyżej notowanego Caspera Ruuda. Polski tenisista pokazał niezwykłą odporność psychiczną i znakomity serwis, wygrywając to spotkanie.', '2026-06-29 18:19:00', 1);

-- Film (id_section = 2)
INSERT INTO article (id, title, content, date_art, id_section) VALUES 
(3, 'Serial Lalka. Jest data premiery w serwisie Netflix', 'Netflix zaprezentował oficjalny zwiastun i podał dokładną datę premiery nowej adaptacji Lalki Bolesława Prusa. Serial w nowoczesnej oprawie ma przyciągnąć przed ekrany zarówno starszych widzów, jak i młodzież szkolną.', '2026-06-27 10:00:00', 2),
(4, 'Shrek 5: Jest polski zwiastun. Zmiany w dubbingu', 'Doczekaliśmy się oficjalnego polskiego zwiastuna filmu Shrek 5. Fani na całym świecie czekali na powrót zielonego ogra, jednak internauci od razu zwrócili uwagę na istotne zmiany w obsadzie dubbingowej kultowych postaci.', '2026-06-17 08:58:00', 2),
(5, '1670 powraca. Jest zwiastun trzeciego sezonu i data premiery', 'Jeden z największych polskich hitów platformy streamingowej powraca z trzecim sezonem. Twórcy opublikowali pełen humoru zwiastun oraz ogłosili datę premiery nowych przygód szlachcica Jana Pawła i mieszkańców wsi Adamczycha.', '2026-06-16 12:13:21', 2),
(6, 'Pippi Pończoszanka powraca. Twórcy Paddingtona szykują hit', 'Kultowa bohaterka książek Astrid Lindgren powraca na ekrany kin. Studio odpowiedzialne za sukces filmów o misiu Paddingtonie przygotowuje pełnometrażowy film o Pippi Pończoszance. Zapowiada się wielki kinowy hit dla całych rodzin.', '2026-06-27 07:03:00', 2);

-- Nauka (id_section = 3)
INSERT INTO article (id, title, content, date_art, id_section) VALUES 
(7, 'Wielki Zderzacz Hadronów CERN wyłączony. Oto co przyniesie przyszłość', 'CERN tymczasowo wyłączył Wielki Zderzacz Hadronów (LHC) w celu przeprowadzenia kluczowych modernizacji. Inżynierowie przygotowują maszynę do projektu High-Luminosity LHC, który ma drastycznie zwiększyć liczbę zderzeń cząstek i otworzyć nową erę w fizyce.', '2026-06-30 16:12:00', 3),
(8, 'Konkurs rzutów karnych rozstrzyga się... w głowie', 'Naukowcy zbadali aktywność mózgu piłkarzy podczas wykonywania rzutów karnych. Wyniki jednoznacznie wskazują, że o sukcesie decyduje nie tylko technika, ale przede wszystkim odporność na stres i kontrola nad stanami lękowymi.', '2026-06-30 15:32:00', 3),
(9, 'Na świecie może istnieć nawet trzy razy więcej gatunków owadów, niż myśleliśmy', 'Najnowsze międzynarodowe badania nad różnorodnością biologiczną sugerują, że dotychczasowe szacunki dotyczące liczby gatunków owadów na Ziemi były znacznie niedoszacowane. Nowe metody genetyczne ujawniły tysiące nieznanych wcześniej odmian.', '2026-06-30 12:20:00', 3),
(10, 'Historyczna misja ratunkowa NASA. Satelita serwisowy przedłuży życie obserwatorium Swift', 'NASA planuje historyczne przedsięwzięcie polegające na orbitalnym serwisowaniu teleskopu Swift za pomocą autonomicznego satelity serwisowego LINK. Misja ta ma na celu tankowanie oraz wymianę podzespołów starzejącego się teleskopu kosmicznego.', '2026-06-30 09:33:00', 3);

-- Dodanie obrazków
INSERT INTO image (id, href, title) VALUES 
(1, 'https://rmf.rmffm.pl/news/attachments/c4/e2/robert-lewandowski-fot-636x421-c4e25664.jpg', 'Robert Lewandowski w Chicago Fire'),
(2, 'https://rmf.rmffm.pl/news/attachments/8e/77/hubert-hurkacz-fot-636x421-8e776638.jpg', 'Hubert Hurkacz na Wimbledonie'),
(3, 'https://rmf.rmffm.pl/news/attachments/f2/41/netfliv-636x421-f241e016.jpg', 'Kadr z nowej ekranizacji Lalki'),
(4, 'https://rmf.rmffm.pl/attachments/000MXQ2JK3SO2BCS-C0.jpg', 'Zwiastun Shreka 5'),
(5, 'https://rmf24.rmffm.pl/attachments/000MXK5H4KK7BXQY-C0.jpg', 'Kadr z trzeciego sezonu 1670'),
(6, 'https://rmf.rmffm.pl/news/attachments/5d/0e/kadr-z-filmu-o-pippi-z-1968-roku-636x421-5d0e40c3.jpg', 'Pippi Pończoszanka z 1968 roku'),
(7, 'https://rmf.rmffm.pl/news/attachments/95/33/science-photo-lib-636x421-95336bd6.jpg', 'Wnętrze Wielkiego Zderzacza Hadronów CERN'),
(8, 'https://rmf.rmffm.pl/news/attachments/2e/be/rzuty-karne-w-meczu-niemcy-636x421-2ebee8f7.jpg', 'Rzuty karne na boisku'),
(9, 'https://rmf.rmffm.pl/news/attachments/92/1c/przelom-w-badaniach-owadow-636x421-921c8d21.jpg', 'Owady w środowisku naturalnym'),
(10, 'https://rmf.rmffm.pl/news/attachments/9f/ab/swift-boost-mission-fot-636x421-9fabc087.jpg', 'Teleskop kosmiczny Swift');

-- Powiązanie artykułów z obrazkami
INSERT INTO art_img (id_article, id_image) VALUES (1, 1);
INSERT INTO art_img (id_article, id_image) VALUES (2, 2);
INSERT INTO art_img (id_article, id_image) VALUES (3, 3);
INSERT INTO art_img (id_article, id_image) VALUES (4, 4);
INSERT INTO art_img (id_article, id_image) VALUES (5, 5);
INSERT INTO art_img (id_article, id_image) VALUES (6, 6);
INSERT INTO art_img (id_article, id_image) VALUES (7, 7);
INSERT INTO art_img (id_article, id_image) VALUES (8, 8);
INSERT INTO art_img (id_article, id_image) VALUES (9, 9);
INSERT INTO art_img (id_article, id_image) VALUES (10, 10);
