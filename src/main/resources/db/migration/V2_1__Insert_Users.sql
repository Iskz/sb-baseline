-- Initial user insertion
TRUNCATE TABLE USERS CASCADE;

INSERT INTO USERS ( USER_ID, USERNAME, PASSWORD, EMAIL, MOBILE_NO, ENABLED, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, PASSWORD_RESET )
VALUES ( NEXTVAL('NBA_SCHEMA.USER_SEQUENCE'),'Team01', 'Team01', 'Team01@gmail.com', 'Team01-12341234', true, true, true, true, false );

INSERT INTO USER_AUTH_GROUP( USER_AUTH_GROUP_ID, USERNAME, AUTH_GROUP ) VALUES ( NEXTVAL('USER_AUTH_SEQUENCE'), 'Team01', 'USER' );