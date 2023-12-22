CREATE OR REPLACE FUNCTION generate_uuid_v4() RETURNS uuid
AS
'SELECT overlay(overlay(md5(random()::text || '':'' || clock_timestamp()::text) placing ''4'' from 13) placing
                to_hex(floor(random() * (11 - 8 + 1) + 8)::int)::text from 17)::uuid'
    LANGUAGE SQL;

create table users (
    id uuid primary key not null default generate_uuid_v4(),
    username varchar unique not null,
    password varchar not null
);

create table phones (
    id uuid primary key not null default generate_uuid_v4(),
    brand varchar not null,
    model varchar not null,
    reserved boolean not null default false,
    booked_at timestamp,
    booked_by uuid constraint phones_user_id_fk references users (id) on delete set null,
    network_technology varchar not null,
    bands_2g varchar,
    bands_3g varchar,
    bands_4g varchar,
    bands_5g varchar,
    created_date timestamp not null default now()
);