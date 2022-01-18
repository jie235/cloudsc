create table scdemo.oauth_client_details
(
    client_id               varchar(256) PRIMARY KEY,
    resource_ids            varchar(256),
    client_secret           varchar(256),
    "scope"                 varchar(256),
    authorized_grant_types  varchar(256),
    web_server_redirect_uri varchar(256),
    authorities             varchar(256),
    access_token_validity   integer,
    refresh_token_validity  integer,
    additional_information  varchar(4096),
    autoapprove             varchar(256)
);

create table scdemo.oauth_client_token
(
    token_id          varchar(256),
    "token"           bytea,
    authentication_id varchar(256) PRIMARY KEY,
    user_name         varchar(256),
    client_id         varchar(256)
);

create table scdemo.oauth_access_token
(
    token_id          varchar(256),
    "token"           bytea,
    authentication_id varchar(256) PRIMARY KEY,
    user_name         varchar(256),
    client_id         varchar(256),
    authentication    bytea,
    refresh_token     varchar(256)
);

create table scdemo.oauth_refresh_token
(
    token_id       varchar(256),
    "token"        bytea,
    authentication bytea
);

create table scdemo.oauth_code
(
    code           varchar(256),
    authentication bytea
);

create table scdemo.oauth_approvals
(
    userId         varchar(256),
    clientId       varchar(256),
    scope          varchar(256),
    status         varchar(10),
    expiresAt      timestamp,
    lastModifiedAt timestamp
);

create table scdemo.ClientDetails
(
    appId                  varchar(256) PRIMARY KEY,
    resourceIds            varchar(256),
    appSecret              varchar(256),
    "scope"                varchar(256),
    grantTypes             varchar(256),
    redirectUrl            varchar(256),
    authorities            varchar(256),
    access_token_validity  integer,
    refresh_token_validity integer,
    additionalInformation  varchar(4096),
    autoApproveScopes      varchar(256)
);

create table scdemo."users"
(
    id         integer      not null,
    "password" varchar(255),
    "username" varchar(255) not null,
    "role"     varchar(255)
);

insert into scdemo."users"
VALUES ('1', '$2a$10$gExKdT3nkoFKfW1cFlqQUuFji3azHG.W4Pe3/WxHKANg3TpkSJRfW', 'zhangjian', 'ADMIN');
