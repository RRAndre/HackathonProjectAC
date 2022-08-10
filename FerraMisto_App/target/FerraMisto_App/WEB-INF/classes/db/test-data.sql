DELETE FROM user;
INSERT INTO user(ID, FIRSTNAME, LASTNAME, EMAIL, PHONE) VALUES
    (1, 'Woman', 'Victim', 'mail@gmail.com', '919919919');

DELETE FROM supporters;
INSERT INTO supporters(ID, FIRSTNAME, LASTNAME, EMAIL, PHONE) VALUES
    (1, 'André', 'Martins', 'random@gmail.com', '919919919');

DELETE FROM bodyguards;
INSERT INTO bodyguards(ID, FIRSTNAME, LASTNAME) VALUES
    (1, 'Américo', 'trincatodos');

DELETE FROM letters;
INSERT INTO letters(ID, CONTENT) VALUES 
    (1, 'This is the first letter');



