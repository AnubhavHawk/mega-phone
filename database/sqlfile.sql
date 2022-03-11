--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: admin_configuration; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.admin_configuration (
    id bigint NOT NULL,
    default_domain character varying(255),
    default_validity bigint NOT NULL,
    max_validity bigint NOT NULL
);


ALTER TABLE public.admin_configuration OWNER TO postgres;

--
-- Name: biller_field_mapping; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.biller_field_mapping (
    id integer NOT NULL,
    biller_endpoint_url character varying(255),
    biller_id character varying(255),
    biller_message character varying(255),
    field1 character varying(255),
    field10 character varying(255),
    field11 character varying(255),
    field12 character varying(255),
    field13 character varying(255),
    field14 character varying(255),
    field15 character varying(255),
    field16 character varying(255),
    field17 character varying(255),
    field18 character varying(255),
    field19 character varying(255),
    field2 character varying(255),
    field20 character varying(255),
    field21 character varying(255),
    field22 character varying(255),
    field23 character varying(255),
    field24 character varying(255),
    field25 character varying(255),
    field26 character varying(255),
    field27 character varying(255),
    field28 character varying(255),
    field29 character varying(255),
    field3 character varying(255),
    field30 character varying(255),
    field31 character varying(255),
    field32 character varying(255),
    field33 character varying(255),
    field34 character varying(255),
    field35 character varying(255),
    field36 character varying(255),
    field37 character varying(255),
    field38 character varying(255),
    field39 character varying(255),
    field4 character varying(255),
    field40 character varying(255),
    field41 character varying(255),
    field42 character varying(255),
    field43 character varying(255),
    field44 character varying(255),
    field45 character varying(255),
    field46 character varying(255),
    field47 character varying(255),
    field48 character varying(255),
    field49 character varying(255),
    field5 character varying(255),
    field50 character varying(255),
    field6 character varying(255),
    field7 character varying(255),
    field8 character varying(255),
    field9 character varying(255),
    time_left_for_trigger integer NOT NULL
);


ALTER TABLE public.biller_field_mapping OWNER TO postgres;

--
-- Name: contact_based_field; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.contact_based_field (
    id integer NOT NULL,
    biller_id character varying(255),
    contact_field_index integer NOT NULL,
    mobile_or_email character varying(255)
);


ALTER TABLE public.contact_based_field OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: long_url; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.long_url (
    url_id bigint NOT NULL,
    create_at timestamp without time zone,
    creator character varying(255),
    original_url character varying(255),
    short_url character varying(255),
    valid_till timestamp without time zone
);


ALTER TABLE public.long_url OWNER TO postgres;

--
-- Name: time_based_field; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.time_based_field (
    id integer NOT NULL,
    biller_id character varying(255),
    time_field_index integer NOT NULL,
    time_format character varying(255)
);


ALTER TABLE public.time_based_field OWNER TO postgres;

--
-- Name: token_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.token_user (
    id bigint NOT NULL,
    password character varying(255),
    username character varying(255)
);


ALTER TABLE public.token_user OWNER TO postgres;

--
-- Data for Name: admin_configuration; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.admin_configuration (id, default_domain, default_validity, max_validity) FROM stdin;
1	me.ga	2592000	3888000
\.


--
-- Data for Name: biller_field_mapping; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.biller_field_mapping (id, biller_endpoint_url, biller_id, biller_message, field1, field10, field11, field12, field13, field14, field15, field16, field17, field18, field19, field2, field20, field21, field22, field23, field24, field25, field26, field27, field28, field29, field3, field30, field31, field32, field33, field34, field35, field36, field37, field38, field39, field4, field40, field41, field42, field43, field44, field45, field46, field47, field48, field49, field5, field50, field6, field7, field8, field9, time_left_for_trigger) FROM stdin;
1	https://api.github.com/users/defunkt	BLR001	Your bill is going to expire soon, click the link below $$##$$ to pay now.\n-Github	login	gists_url	starred_url	subscriptions_url	organizations_url	repos_url	events_url	received_events_url	type	site_admin	name	id	company	blog	location	email	hireable	bio	twitter_username	public_repos	public_gists	followers	node_id	following	created_at	updated_at	\N	\N	\N	\N	\N	\N	\N	avatar_url	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	gravatar_id	\N	url	html_url	followers_url	following_url	24
\.


--
-- Data for Name: contact_based_field; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.contact_based_field (id, biller_id, contact_field_index, mobile_or_email) FROM stdin;
3	BLR001	22	mobile
\.


--
-- Data for Name: long_url; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.long_url (url_id, create_at, creator, original_url, short_url, valid_till) FROM stdin;
\.


--
-- Data for Name: time_based_field; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.time_based_field (id, biller_id, time_field_index, time_format) FROM stdin;
2	BLR001	31	YYYY-MM-DDThh:mm:ss
\.


--
-- Data for Name: token_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.token_user (id, password, username) FROM stdin;
1	5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8	BLR001
2	6cf615d5bcaac778352a8f1f3360d23f02f34ec182e259897fd6ce485d7870d4	BLR002
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 3, true);


--
-- Name: admin_configuration admin_configuration_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin_configuration
    ADD CONSTRAINT admin_configuration_pkey PRIMARY KEY (id);


--
-- Name: biller_field_mapping biller_field_mapping_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.biller_field_mapping
    ADD CONSTRAINT biller_field_mapping_pkey PRIMARY KEY (id);


--
-- Name: contact_based_field contact_based_field_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contact_based_field
    ADD CONSTRAINT contact_based_field_pkey PRIMARY KEY (id);


--
-- Name: long_url long_url_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.long_url
    ADD CONSTRAINT long_url_pkey PRIMARY KEY (url_id);


--
-- Name: time_based_field time_based_field_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.time_based_field
    ADD CONSTRAINT time_based_field_pkey PRIMARY KEY (id);


--
-- Name: token_user token_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.token_user
    ADD CONSTRAINT token_user_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

