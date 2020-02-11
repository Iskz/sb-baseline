--DROP TABLE IF EXISTS NBA_SCHEMA.FLYWAY_SCHEMA_HISTORY;
--DROP TABLE IF EXISTS NBA_SCHEMA.OAUTH_CLIENT_DETAILS;
--DROP TABLE IF EXISTS NBA_SCHEMA.OAUTH_CLIENT_TOKEN;
--DROP TABLE IF EXISTS NBA_SCHEMA.OAUTH_ACCESS_TOKEN;
--DROP TABLE IF EXISTS NBA_SCHEMA.OAUTH_REFRESH_TOKEN;
--DROP TABLE IF EXISTS NBA_SCHEMA.OAUTH_CODE;
--DROP TABLE IF EXISTS NBA_SCHEMA.OAUTH_APPROVALS;
--DROP TABLE IF EXISTS NBA_SCHEMA.CLIENT_DETAILS;
--DROP TABLE IF EXISTS NBA_SCHEMA.USER_AUTH_GROUP;
--DROP TABLE IF EXISTS NBA_SCHEMA.USERS;
--DROP TABLE IF EXISTS NBA_SCHEMA.NBA_SEGMENTS CASCADE;
--DROP TABLE IF EXISTS NBA_SCHEMA.NBA_SUB_SEGMENTS CASCADE;
--
--DROP SEQUENCE IF EXISTS NBA_SCHEMA.USER_SEQUENCE;
--DROP SEQUENCE IF EXISTS NBA_SCHEMA.USER_AUTH_SEQUENCE;

CREATE TABLE OAUTH_CLIENT_DETAILS
(
    client_id               VARCHAR(255) PRIMARY KEY,
    resource_ids            VARCHAR(255),
    client_secret           VARCHAR(255),
    scope                   VARCHAR(255),
    authorized_grant_types  VARCHAR(255),
    web_server_redirect_uri VARCHAR(255),
    authorities             VARCHAR(255),
    access_token_validity   INTEGER,
    refresh_token_validity  INTEGER,
    additional_information  VARCHAR(4096),
    autoapprove             VARCHAR(255)
);

CREATE TABLE OAUTH_CLIENT_TOKEN
(
    token_id          VARCHAR(255),
    token             BYTEA,
    authentication_id VARCHAR(255) PRIMARY KEY,
    user_name         VARCHAR(255),
    client_id         VARCHAR(255)
);

CREATE TABLE OAUTH_ACCESS_TOKEN
(
    token_id          VARCHAR(255),
    token             BYTEA,
    authentication_id VARCHAR(255) PRIMARY KEY,
    user_name         VARCHAR(255),
    client_id         VARCHAR(255),
    authentication    BYTEA,
    refresh_token     VARCHAR(255)
);

CREATE TABLE OAUTH_REFRESH_TOKEN
(
    token_id       VARCHAR(255),
    token          BYTEA,
    authentication BYTEA
);

CREATE TABLE OAUTH_CODE
(
    code           VARCHAR(255),
    authentication BYTEA
);

CREATE TABLE OAUTH_APPROVALS
(
    userId         VARCHAR(255),
    clientId       VARCHAR(255),
    scope          VARCHAR(255),
    status         VARCHAR(10),
    expiresAt      TIMESTAMP,
    lastModifiedAt TIMESTAMP
);

CREATE TABLE CLIENT_DETAILS
(
    appId                  VARCHAR(255) PRIMARY KEY,
    resourceIds            VARCHAR(255),
    appSecret              VARCHAR(255),
    scope                  VARCHAR(255),
    grantTypes             VARCHAR(255),
    redirectUrl            VARCHAR(255),
    authorities            VARCHAR(255),
    access_token_validity  INTEGER,
    refresh_token_validity INTEGER,
    additionalInformation  VARCHAR(4096),
    autoApproveScopes      VARCHAR(255)
);