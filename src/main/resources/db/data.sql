-- User and Roles --
insert into users(user_email, password, first_name, last_name, active, updated_at) values ('agent1@gmail.com', '$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2', 'Agent', 'One', 1, now())
insert into roles(role, updated_at) values('USER', now())
insert into user_roles(user_id, role_id)values(1, 1)

-- Products --
insert into products(name, updated_at, updated_by)values('Slat', now(), 1);
insert into products(name, updated_at, updated_by)values('Bottom rail', now(), 1);
insert into products(name, updated_at, updated_by)values('Valance', now(), 1);

-- Dimensions --
insert into dimensions(dimension, updated_at, updated_by)values(10.0, now(), 1);
insert into dimensions(dimension, updated_at, updated_by)values(9.50, now(), 1);
insert into dimensions(dimension, updated_at, updated_by)values(10.50, now(), 1);

-- Wood Types --
insert into wood_types(name, updated_at, updated_by)values('Bass/Poplar', now(), 1);
insert into wood_types(name, updated_at, updated_by)values('Ash', now(), 1);
insert into wood_types(name, updated_at, updated_by)values('Oak', now(), 1);

-- Finishes --
insert into finishes(name, updated_at, updated_by)values('Paint', now(), 1);
insert into finishes(name, updated_at, updated_by)values('Stain', now(), 1);
insert into finishes(name, updated_at, updated_by)values('Print', now(), 1);

-- Tiers --
insert into tiers(name, updated_at, updated_by)values('Premium', now(), 1);
insert into tiers(name, updated_at, updated_by)values('Standard', now(), 1);
insert into tiers(name, updated_at, updated_by)values('Special', now(), 1);