insert into user_details(id, birth_date, name)
values (1001, current_date(), 'Rafal');

insert into user_details(id, birth_date, name)
values (1002, current_date(), 'Naru');

insert into user_details(id, birth_date, name)
values (1003, current_date(), 'Jennie');

insert into post(id, description, user_id)
values (2001, 'I want to learn AWS', 1001);

insert into post(id, description, user_id)
values (2002, 'I want to learn Java', 1001);

insert into post(id, description, user_id)
values (2003, 'I want to learn React', 1002);
