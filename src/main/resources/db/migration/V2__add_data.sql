-- admin/admin
INSERT INTO users (login, password, role)
values ('admin', '$2a$10$csznBGiCXAQ.v4QyRSkbV.x8//TvJISzzRse21AgHTIgQoVF3jpfK', 'ADMIN');
-- xxx/123
INSERT INTO users (login, password, role)
values ('user', '$2a$10$EOv4C4b69cJf1Jx9YCKLCe3No7Rui1CTywg6rFw5/i4yhHF/GIR2S', 'USER');


INSERT INTO routes (title, description)
values ('circle movement', 'turn left at every turn');
