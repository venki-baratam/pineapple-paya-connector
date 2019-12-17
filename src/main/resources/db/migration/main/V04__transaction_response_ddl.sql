CREATE TABLE transaction_response
(
  id BIGINT NOT NULL PRIMARY KEY,
  trasaction_info_id BIGINT NOT NULL,
  transaction_guid CHARACTER VARYING(120) NOT NULL ,
  transaction_type CHARACTER VARYING(120) NOT NULL ,
  request_id CHARACTER VARYING(120) NOT NULL ,
  sec_code CHARACTER VARYING(6)  NOT NULL ,
  tx_status BOOLEAN DEFAULT FALSE,
  response_type	CHARACTER VARYING(1),
  response_type_text CHARACTER VARYING(1200),
  result_code INTEGER,
  type_code INTEGER,
  code CHARACTER VARYING(120),
  message CHARACTER VARYING(1200),
  created_on timestamp without time zone NOT NULL,
  modified_on timestamp without time zone NOT NULL,
  created_by character varying(100) NOT NULL,
  modified_by character varying(100) NOT NULL,
  version bigint
);

CREATE SEQUENCE seq_transaction_response
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;