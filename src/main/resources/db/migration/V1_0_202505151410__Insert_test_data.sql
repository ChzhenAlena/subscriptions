INSERT INTO subscriptions (id, name, price) VALUES
(1, 'YouTube Premium', 11.99),
(2, 'Netflix Standard', 15.49),
(3, 'Spotify Premium', 9.99),
(4, 'Яндекс.Плюс', 7.99),
(5, 'Apple Music', 9.99);

-- Добавляем тестовых пользователей (пароли: "test1234")
INSERT INTO users (id, username, email, password) VALUES
(1, 'ivan_ivanov', 'ivan@example.com', '$2a$10$xJ5s5Jz8JQZ5J5s5Jz8JQO5J5s5Jz8JQZ5J5s5Jz8JQO5J5s5Jz8JQZ'),
(2, 'anna_smith', 'anna@example.com', '$2a$10$xJ5s5Jz8JQZ5J5s5Jz8JQO5J5s5Jz8JQZ5J5s5Jz8JQO5J5s5Jz8JQZ');

-- Добавляем подписки
INSERT INTO user_subscriptions (user_id, subscription_id) VALUES
(1, 1),
(1, 3),
(2, 2),
(2, 4);

SELECT setval('users_id_seq', (SELECT MAX(id) FROM "users"));
SELECT setval('subscriptions_id_seq', (SELECT MAX(id) FROM "subscriptions"));