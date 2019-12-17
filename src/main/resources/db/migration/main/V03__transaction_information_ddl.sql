CREATE TABLE transaction_information
(
	id bigint NOT NULL PRIMARY KEY,
	request_id CHARACTER VARYING(120) NOT NULL ,
	transaction_guid CHARACTER VARYING(120) NOT NULL,
    transaction_type CHARACTER VARYING(120) NOT NULL,
    merchant_id CHARACTER VARYING(120) NOT NULL,
    terminal_id BIGINT NOT NULL,
    identifier CHARACTER VARYING(1) NOT NULL,
    control_char CHARACTER VARYING(1) NOT NULL,
    verfication_only BOOLEAN ,
    routing_number BIGINT  NOT NULL,
    account_number BIGINT  NOT NULL,
    check_number BIGINT,
    account_type CHARACTER VARYING(120) NOT NULL,
    company_name CHARACTER VARYING(120),
    first_name CHARACTER VARYING(120) NOT NULL,
    last_name CHARACTER VARYING(120) NOT NULL,
    address1 CHARACTER VARYING(1200) NOT NULL,
    address2 CHARACTER VARYING(1200) NOT NULL,
    city CHARACTER VARYING(120) NOT NULL,
    state CHARACTER VARYING(120) NOT NULL,
    zip_code CHARACTER VARYING(10) NOT NULL,
    phone_number CHARACTER VARYING(10) NOT NULL,
    dl_state CHARACTER VARYING(2) NOT NULL,
    dl_number CHARACTER VARYING(15) NOT NULL,
    courtsey_card_id CHARACTER VARYING(120) NOT NULL,
    dob_year SMALLINT,
    check_amount DECIMAL NOT NULL,
    micr_data CHARACTER VARYING(120),
    check_image_front bytea,
    check_image_back bytea ,
    ecode_options CHARACTER VARYING(120),
    created_on timestamp without time zone NOT NULL,
    modified_on timestamp without time zone NOT NULL,
    created_by character varying(100) NOT NULL,
    modified_by character varying(100) NOT NULL,
    version bigint
);

CREATE SEQUENCE seq_transaction_information
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;