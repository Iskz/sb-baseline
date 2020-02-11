INSERT INTO OAUTH_CLIENT_DETAILS
(CLIENT_ID, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES,
 WEB_SERVER_REDIRECT_URI, AUTHORITIES, ACCESS_TOKEN_VALIDITY,
 REFRESH_TOKEN_VALIDITY, ADDITIONAL_INFORMATION, AUTOAPPROVE)
VALUES
('axiataClientId', 'axiataPassword', 'axiata,read,write',
 'password,authorization_code,refresh_token', null, null, 172800, 172800, null, true);