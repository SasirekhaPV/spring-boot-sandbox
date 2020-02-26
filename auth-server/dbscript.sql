-- command to import this dbscript into H2 database (change the path to script file)
-- runscript from '/Users/vinodkumar/Desktop/spring-boot-sandbox/auth-server/dbscript.sql'

drop table if exists ASSIGN_PERMISSION_TO_ROLE;
drop table if exists ASSIGN_USER_TO_ROLE;
drop table if exists PERMISSIONS;
drop table if exists ROLES;
drop table if exists USERS;

CREATE TABLE PERMISSIONS (
	ID INT PRIMARY KEY AUTO_INCREMENT, 
	PERMISSION_NAME VARCHAR(100)
);

CREATE TABLE USERS (
	ID INT PRIMARY KEY AUTO_INCREMENT, 
	FIRST_NAME VARCHAR(50),
	LAST_NAME VARCHAR(50),
	EMAIL_ID VARCHAR(255) UNIQUE, 
	PASSWORD VARCHAR(1000),
	MOBILE VARCHAR(20),
	COUNTRY VARCHAR(50),
	USER_TYPE VARCHAR(20)
);

CREATE TABLE ROLES (
	ID INT PRIMARY KEY AUTO_INCREMENT, 
	ROLE_NAME VARCHAR(20)
);

CREATE TABLE ASSIGN_PERMISSION_TO_ROLE (
	ID INT PRIMARY KEY AUTO_INCREMENT, 
	PERMISSION_ID INT, 
	FOREIGN KEY(PERMISSION_ID) REFERENCES PERMISSIONS (ID), 
	ROLE_ID INT, 
	FOREIGN KEY(ROLE_ID) REFERENCES ROLES(ID)
);

CREATE TABLE ASSIGN_USER_TO_ROLE (
	ID INT PRIMARY KEY AUTO_INCREMENT, 
	USER_ID INT, 
	FOREIGN KEY(USER_ID) REFERENCES USERS(ID), 
	ROLE_ID INT, 
	FOREIGN KEY(ROLE_ID) REFERENCES ROLES(ID)
);


INSERT INTO PERMISSIONS (PERMISSION_NAME) VALUES 
('ROLE_view_permission'), 
('ROLE_create_role'), 
('ROLE_edit_role'), 
('ROLE_view_role'), 
('ROLE_delete_role'),
('ROLE_assign_permissions_to_role'),
('ROLE_view_permission_by_role'),
('ROLE_create_users'),
('ROLE_edit_users'),
('ROLE_view_users'),
('ROLE_delete_users'),
('ROLE_assign_users_to_role'),
('ROLE_view_users_by_role'),
('ROLE_view_products'),		-- role required for product-service
('ROLE_view_one_product'),	-- role required for product-service
('ROLE_add_product'),		-- role required for product-service
('ROLE_update_product'),	-- role required for product-service
('ROLE_delete_product')		-- role required for product-service
; -- add more roles/permissions based on your client application demands



INSERT INTO ROLES (ID, ROLE_NAME) VALUES 
(1, 'ADMINISTRATOR'), 
(2, 'GUEST');

INSERT INTO USERS (FIRST_NAME, LAST_NAME, EMAIL_ID, PASSWORD, MOBILE, COUNTRY, USER_TYPE) VALUES 
('Vinod', 'Kumar', 'vinod@vinod.co','$2a$10$iXqWAkJo7ylqEQsZqmdUjeIwf.twc7UIvqchZLT7Qe/XN1j71nefG', '9731424784', 'India', 'system_admin'),
('Shyam', 'Sundar', 'shyam@example.com','$2a$10$iXqWAkJo7ylqEQsZqmdUjeIwf.twc7UIvqchZLT7Qe/XN1j71nefG', null, 'India', 'admin'),
('Guest', 'Guest', 'guest@example.com','$2a$10$iXqWAkJo7ylqEQsZqmdUjeIwf.twc7UIvqchZLT7Qe/XN1j71nefG', null, 'India', 'guest')
;

INSERT INTO ASSIGN_PERMISSION_TO_ROLE (PERMISSION_ID, ROLE_ID) VALUES 
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(10,1),
(11,1),
(12,1),
(13,1),
(14,1),
(15,1),
(16,1),
(17,1),
(18,1),
(14,2),	-- GUEST
(15,2)	-- GUEST
;


INSERT INTO ASSIGN_USER_TO_ROLE (USER_ID, ROLE_ID) VALUES 
(2, 1), -- shyam@example.com --> ADMINISTRATOR 
(3, 2); -- guest@example.com --> GUEST

SELECT * FROM USERS WHERE EMAIL_ID='shyam@example.com';

SELECT DISTINCT P.PERMISSION_NAME FROM PERMISSIONS P 
	INNER JOIN ASSIGN_PERMISSION_TO_ROLE P_R ON P.ID=P_R.PERMISSION_ID
	INNER JOIN ROLES R ON R.ID=P_R.ROLE_ID 
	INNER JOIN ASSIGN_USER_TO_ROLE U_R ON U_R.ROLE_ID=R.ID
	INNER JOIN USERS U ON U.ID=U_R.USER_ID
	WHERE U.EMAIL_ID='shyam@example.com';
	


