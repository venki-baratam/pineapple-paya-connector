CREATE TABLE transaction_log
(
  id bigint NOT NULL PRIMARY KEY,
  transaction_guid CHARACTER VARYING(120) NOT NULL,
  terminal_id BIGINT NOT NULL,
  transaction_type CHARACTER VARYING(120) NOT NULL,
  request_id CHARACTER VARYING(120),
  transaction_time timestamp without time zone NOT NULL,
  message_type CHARACTER VARYING(100) NOT NULL,
  response_message CHARACTER VARYING(300),
  response_code INTEGER,
  created_on timestamp without time zone NOT NULL,
  modified_on timestamp without time zone NOT NULL,
  created_by character varying(100) NOT NULL,
  modified_by character varying(100) NOT NULL,
  version bigint
);

CREATE SEQUENCE seq_transaction_log
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;