-- Dodanie kolumny role do tabeli login
ALTER TABLE login ADD COLUMN role VARCHAR(50) NOT NULL DEFAULT 'USER';

-- Aktualizacja haseł na hashe BCrypt oraz przydzielenie ról
-- Jawne hasła dla celów testowych:
-- jkowalski: password123
UPDATE login SET pass = '$2b$12$MdPP6Uv45QbtfkMnRE3uSuyov8VVpATUHHTMu4HGdu9GCWED/ILVC', role = 'USER' WHERE login = 'jkowalski';

-- anowak: password456
UPDATE login SET pass = '$2b$12$mSgo7Tlo0pZ5jNSco/KW.OBWZsdK71X8BJu2W6LTTisEuWGOfzBfS', role = 'USER' WHERE login = 'anowak';

-- pwisniewski: securePass7
UPDATE login SET pass = '$2b$12$Jfa61RtxOGK/FlTuHXrBeeOgTlUybx.zGTzrZLnu0K3SLGEi713yK', role = 'USER' WHERE login = 'pwisniewski';

-- mwojcik: maria2026
UPDATE login SET pass = '$2b$12$kiTyBPJKhkxr84FZ0GoyZejkJc3oqx07wZhjef6JwP//ZC2sW78Ie', role = 'USER' WHERE login = 'mwojcik';

-- kkaminski: adminPassword
UPDATE login SET pass = '$2b$12$WSxE5R7T44y9VAy5tZs6aeBkCy/VQYYQJcaPUM.0xnMfV80C2zj4u', role = 'ADMIN' WHERE login = 'kkaminski';
