CREATE SCHEMA IF NOT EXISTS eventuate AUTHORIZATION clients_user;

drop table if exists eventuate.message;

CREATE TABLE eventuate.message (
  ID VARCHAR(1000) PRIMARY KEY,
  DESTINATION VARCHAR(1000) NOT NULL,
  HEADERS VARCHAR(1000) NOT NULL,
  PAYLOAD VARCHAR(1000) NOT NULL,
  MESSAGE_PARTITION SMALLINT,
  PUBLISHED int NOT NULL,
  CREATION_TIME BIGINT
);