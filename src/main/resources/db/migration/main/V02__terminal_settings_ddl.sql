CREATE TABLE terminal_settings (
    id bigint NOT NULL,
    terminal_id bigint NOT NULL UNIQUE,
    sec_code character varying(100) NOT NULL,
    is_gateway_terminal boolean NOT NULL,
    allow_consumer_credits boolean NOT NULL,
    dl_required boolean NOT NULL,
    run_check_verification boolean NOT NULL,
    run_identity_verification boolean NOT NULL,
    schema_xsd_data jsonb NOT NULL,
    xml_template_data jsonb NOT NULL,
    active boolean,
    deactivated_on timestamp without time zone,
    deactivated_by character varying(100),
    created_on timestamp without time zone NOT NULL,
    modified_on timestamp without time zone NOT NULL,
    created_by character varying(100) NOT NULL,
    modified_by character varying(100) NOT NULL,
    version bigint
);

CREATE SEQUENCE seq_terminal_settings
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;