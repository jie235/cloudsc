--login with super user

--crate role
create role cloudsc with login password 'psd2021';

--create database
create database cloudsc with owner cloudsc encoding utf8;

--logout super and sign in with user cloudsc to database cloudsc then create schema
--or add authorization limit to schema(requires first select a database or else the schema would be in public area instead of specific database)
create schema if not exists scdemo authorization cloudsc;