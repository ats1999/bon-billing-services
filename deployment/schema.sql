--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4 (Postgres.app)
-- Dumped by pg_dump version 17.4 (Postgres.app)

-- SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
-- SET transaction_timeout = 0;
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
-- Name: bill; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bill (
    id character varying(255) NOT NULL,
    user_id integer NOT NULL,
    amount double precision NOT NULL,
    context character varying(255),
    created_at timestamp(6) without time zone,
    due_date timestamp(6) without time zone,
    paid_on timestamp(6) without time zone
);


ALTER TABLE public.bill OWNER TO postgres;

--
-- Name: bill_payment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bill_payment (
    transaction_id character varying(255) NOT NULL,
    context character varying(255),
    payment_date timestamp(6) without time zone NOT NULL,
    payment_method character varying(255) NOT NULL,
    transaction_status character varying(255) NOT NULL,
    bill_id character varying(255) NOT NULL,
    bill_user_id integer NOT NULL,
    is_rewarded boolean,
    CONSTRAINT bill_payment_payment_method_check CHECK (((payment_method)::text = ANY ((ARRAY['CREDIT_CARD'::character varying, 'DEBIT_CARD'::character varying, 'NET_BANKING'::character varying])::text[]))),
    CONSTRAINT bill_payment_transaction_status_check CHECK (((transaction_status)::text = ANY ((ARRAY['PENDING'::character varying, 'COMPLETED'::character varying, 'FAILED'::character varying])::text[])))
);


ALTER TABLE public.bill_payment OWNER TO postgres;

--
-- Name: bon_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bon_user (
    id integer NOT NULL,
    email character varying(254) NOT NULL,
    first_name character varying(100) NOT NULL,
    last_name character varying(100) NOT NULL,
    password_hash character varying(200) NOT NULL
);


ALTER TABLE public.bon_user OWNER TO postgres;

--
-- Name: bon_user_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bon_user_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.bon_user_seq OWNER TO postgres;

--
-- Name: reward; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reward (
    id character varying(255) NOT NULL,
    user_id integer NOT NULL,
    description character varying(255) NOT NULL,
    issued_at timestamp(6) without time zone NOT NULL,
    status smallint NOT NULL,
    value character varying(255) NOT NULL,
    expiry_date timestamp(6) without time zone,
    CONSTRAINT reward_status_check CHECK (((status >= 0) AND (status <= 1)))
);


ALTER TABLE public.reward OWNER TO postgres;

--
-- Name: bill_payment bill_payment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bill_payment
    ADD CONSTRAINT bill_payment_pkey PRIMARY KEY (transaction_id);


--
-- Name: bill bill_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bill
    ADD CONSTRAINT bill_pkey PRIMARY KEY (id, user_id);


--
-- Name: bon_user bon_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bon_user
    ADD CONSTRAINT bon_user_pkey PRIMARY KEY (id);


--
-- Name: reward reward_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reward
    ADD CONSTRAINT reward_pkey PRIMARY KEY (id, user_id);


--
-- Name: bon_user ukp0ejrojjkuwtljh3jktbab4h3; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bon_user
    ADD CONSTRAINT ukp0ejrojjkuwtljh3jktbab4h3 UNIQUE (email);


--
-- Name: bill_payment fk288jorna2iipjbnhja5s3oshv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bill_payment
    ADD CONSTRAINT fk288jorna2iipjbnhja5s3oshv FOREIGN KEY (bill_id, bill_user_id) REFERENCES public.bill(id, user_id);


--
-- PostgreSQL database dump complete
--

