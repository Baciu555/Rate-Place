--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.5
-- Dumped by pg_dump version 9.6.5

-- Started on 2017-10-05 20:55:20

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2169 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 186 (class 1259 OID 24810)
-- Name: opinion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE opinion (
    id integer NOT NULL,
    content character varying(255) NOT NULL,
    entry_date date,
    grade integer,
    place_id integer NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE opinion OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 24808)
-- Name: opinion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE opinion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE opinion_id_seq OWNER TO postgres;

--
-- TOC entry 2170 (class 0 OID 0)
-- Dependencies: 185
-- Name: opinion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE opinion_id_seq OWNED BY opinion.id;


--
-- TOC entry 188 (class 1259 OID 24818)
-- Name: place; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE place (
    id integer NOT NULL,
    description text NOT NULL,
    image_path character varying(255),
    latitude double precision NOT NULL,
    longitude double precision NOT NULL,
    name character varying(50),
    type_id integer NOT NULL
);


ALTER TABLE place OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 24816)
-- Name: place_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE place_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE place_id_seq OWNER TO postgres;

--
-- TOC entry 2171 (class 0 OID 0)
-- Dependencies: 187
-- Name: place_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE place_id_seq OWNED BY place.id;


--
-- TOC entry 190 (class 1259 OID 24829)
-- Name: type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE type (
    id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE type OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 24827)
-- Name: type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE type_id_seq OWNER TO postgres;

--
-- TOC entry 2172 (class 0 OID 0)
-- Dependencies: 189
-- Name: type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE type_id_seq OWNED BY type.id;


--
-- TOC entry 184 (class 1259 OID 24795)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE users (
    id integer NOT NULL,
    avatar_path character varying(255),
    email character varying(255) NOT NULL,
    password character varying(50),
    register_date timestamp without time zone,
    username character varying(20)
);


ALTER TABLE users OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 24793)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO postgres;

--
-- TOC entry 2173 (class 0 OID 0)
-- Dependencies: 183
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- TOC entry 2020 (class 2604 OID 24813)
-- Name: opinion id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY opinion ALTER COLUMN id SET DEFAULT nextval('opinion_id_seq'::regclass);


--
-- TOC entry 2021 (class 2604 OID 24821)
-- Name: place id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY place ALTER COLUMN id SET DEFAULT nextval('place_id_seq'::regclass);


--
-- TOC entry 2022 (class 2604 OID 24832)
-- Name: type id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY type ALTER COLUMN id SET DEFAULT nextval('type_id_seq'::regclass);


--
-- TOC entry 2019 (class 2604 OID 24798)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- TOC entry 2158 (class 0 OID 24810)
-- Dependencies: 186
-- Data for Name: opinion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY opinion (id, content, entry_date, grade, place_id, user_id) FROM stdin;
1	Dobry kebab	2017-10-05	4	1	12
2	Jest Extra :D	2017-10-05	5	10	12
3	Niestety dostałem nieświeże sushi :/	2017-10-05	2	5	13
4	Hotel 1 klasa :)	2017-10-05	5	8	13
5	Ok	2017-10-05	4	9	13
6	Fajny Klimat :D	2017-10-05	4	6	13
7	Niemiła obsługa, reszta wporządku	2017-10-05	3	8	14
8	Fajny hotel	2017-10-05	4	2	14
9	Dobra kuchnia :)	2017-10-05	5	3	14
10	Jest fajnie	2017-10-05	3	4	14
11	spoko	2017-10-05	4	10	15
12	git	2017-10-05	5	3	15
13	dobre jedzenie	2017-10-05	4	7	15
14	jest git 	2017-10-05	4	6	15
15	Super!	2017-10-05	5	10	16
\.


--
-- TOC entry 2174 (class 0 OID 0)
-- Dependencies: 185
-- Name: opinion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('opinion_id_seq', 15, true);


--
-- TOC entry 2160 (class 0 OID 24818)
-- Dependencies: 188
-- Data for Name: place; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY place (id, description, image_path, latitude, longitude, name, type_id) FROM stdin;
1	Dara Kebab mieści się na rzeszowskim Rynku. Lokal oferuje oryginalne tureckie potrawy: kebab w różnych postaciach, zestawy, tureckie pierogi i dania regionalne oraz potrawy mięsne.	dara-kebab-rynek.jpg	50.037609000000003	22.005527000000001	Dara Kebab	1
2	Do Państwa dyspozycji oddajemy 49 elegancko i komfortowo wyposażonych pokoi, w tym:\r\n\r\n10 pokoi jednoosobowych,\r\n30 pokoi dwuosobowych,\r\n6 półampartamentów,\r\n3 apartamenty.\r\nWszystkie pokoje są klimatyzowane, posiadają stałe, bezprzewodowe łącze internetowe, telefon, telewizor LCD, pay TV, TV kablową, sejf, minibar, łazienkę z prysznicem lub wanną, suszarkę do włosów. Obiekt jest przystosowany do przyjęcia osób niepełnosprawnych.	hotel-ambasadorski.jpg	50.037404000000002	22.005821999999998	Hotel Ambasadorski	3
3	PYSZNE JEDZENIE, KTÓRE SPRAWIA RADOŚĆ. NOWATORSKIE POMYSŁY NA TRADYCYJNE POTRAWY W KLIMATYCZNYM OTOCZENIU PRZYPRAWIAMY UŚMIECHEM :)	radosc-rzeszow.jpg	50.037790000000001	22.004339000000002	restauracja Radość	2
4	Grota swoją działalność rozpoczęła 7.10.2010r. i nie mal od początku przypadła do gustu mieszkańcom Rzeszowa spacerujących ulicą 3-go Maja. Stało się tak ponieważ Grota zaoferowała swoim gościom połączenie trzech elementów gastronomii.\r\nNaszym celem jest dostarczenie naszym klientom najwyższą jakość obsługi, jedzenia w niskiej cenie.\r\nDuży lokal i szeroki asortyment sprawia iż każdy znajdzie w nim coś dla siebie.	grota.jpg	50.036937000000002	22.001325999999999	Grota	2
5	Najlepsze Sushi w Rzeszowie.\r\nZapraszamy :)	zori-sushi.jpg	50.037242999999997	22.001812000000001	Zori Sushi	2
6	Muzyka: dla ciała i duszy, także na żywo.\r\nKlub Nora Aktora mieści się w klimatycznych piwnicach starej kamienicy na rzeszowskim Rynku. Wchodząc do pubu od strony ul. Króla Kazimierza, możecie być pewni, że spotkacie się z miłą i profesjonalną obsługą, a atmosfera klubu zachwyci Was swoją wyjątkowością.	nora-aktora.jpg	50.036960000000001	22.004845	Nora Aktora	1
7	Wyobraź sobie miejsce, w którym możesz oddawać się wysublimowanym kulinarnym przyjemnościom. Miejsce, które swoim wystrojem wprawia Cię w doskonały nastrój. Miejsce, w którym kucharze i kelnerzy dbają o Twoje podniebienie, a doskonale dobrana muzyka jeszcze wyraźniej uwypukla wyjątkowość smaków. Tym właśnie jest Restauracja Konfitura.	konfitura.jpg	50.037011	22.003416000000001	Konfitura	2
8	Hotel Rzeszów to miejsce gdzie historia przeplata się z nowoczesnością i ekskluzywnym wystrojem.\r\nDoskonała lokalizacja w sercu miasta to kolejny z licznych atutów. 147 komfortowych pokoi z najlepszym widokiem na Rzeszów.	hotel-rzeszow.jpg	50.042019000000003	21.998809999999999	Hotel Rzeszów	3
9	Nowoczesny, stylowy wystrój.\r\nCiepła i przytulna aranżacja\r\nBogate Menu.\r\nPrzystępna cena.\r\nZapraszamy do odwiedzania restauracji.	saigon.jpg	50.039147	22.006978	Saigon	2
10	Hokus Pokus jest gastrobarem, czyli "przenikanie się baru i kuchni". Oparta na idei STREETFOOD+. Nasza kuchnia, to połączenie bogactwa smaku ulicznych kuchni świata z najwyższej klasy składnikami oraz restauracyjną jakością. Hokus Pokus ma najlepsze śniadania, do wyboru lunch serwowany każdego dnia oraz oryginalną kartę.\r\n\r\nIndustrialne, ale ciepłe wnętrze tętni życiem niczym najpopularniejsze knajpy w Europie. Możesz spróbować znakomite koktajle, świeże drinki oraz niepowtarzalne słodkości.	hokus-pokus.jpg	50.036292000000003	22.001270000000002	Hokus Pokus	1
\.


--
-- TOC entry 2175 (class 0 OID 0)
-- Dependencies: 187
-- Name: place_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('place_id_seq', 10, true);


--
-- TOC entry 2162 (class 0 OID 24829)
-- Dependencies: 190
-- Data for Name: type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY type (id, name) FROM stdin;
1	bar
2	restauracja
3	hotel
\.


--
-- TOC entry 2176 (class 0 OID 0)
-- Dependencies: 189
-- Name: type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('type_id_seq', 3, true);


--
-- TOC entry 2156 (class 0 OID 24795)
-- Dependencies: 184
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY users (id, avatar_path, email, password, register_date, username) FROM stdin;
12	avatar1.gif	12345@wp.pl	12345	2017-10-03 00:14:43.374	pisak
13	default-avatar.jpg	baciu@gmail.com	123456	2017-10-05 18:17:52.517	baciu
14	default-avatar.jpg	banan@wp.pl	banan	2017-10-05 18:21:17.263	banan
15	smile.jpg	uzytkownik@gmail.com	12345	2017-10-05 18:23:37.838	uzytkownik
16	default-avatar.jpg	tajfun@wp.pl	123456	2017-10-05 20:49:44.756	tajfun
\.


--
-- TOC entry 2177 (class 0 OID 0)
-- Dependencies: 183
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('users_id_seq', 16, true);


--
-- TOC entry 2030 (class 2606 OID 24815)
-- Name: opinion opinion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY opinion
    ADD CONSTRAINT opinion_pkey PRIMARY KEY (id);


--
-- TOC entry 2032 (class 2606 OID 24826)
-- Name: place place_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY place
    ADD CONSTRAINT place_pkey PRIMARY KEY (id);


--
-- TOC entry 2034 (class 2606 OID 24834)
-- Name: type type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY type
    ADD CONSTRAINT type_pkey PRIMARY KEY (id);


--
-- TOC entry 2024 (class 2606 OID 24805)
-- Name: users uk_6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- TOC entry 2026 (class 2606 OID 24807)
-- Name: users uk_r43af9ap4edm43mmtq01oddj6; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT uk_r43af9ap4edm43mmtq01oddj6 UNIQUE (username);


--
-- TOC entry 2028 (class 2606 OID 24803)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2036 (class 2606 OID 24840)
-- Name: opinion fk7ce989bael9ib668qg78shpyt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY opinion
    ADD CONSTRAINT fk7ce989bael9ib668qg78shpyt FOREIGN KEY (user_id) REFERENCES users(id);


--
-- TOC entry 2035 (class 2606 OID 24835)
-- Name: opinion fk8tr592bt4tmsh9ldxpn2o205n; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY opinion
    ADD CONSTRAINT fk8tr592bt4tmsh9ldxpn2o205n FOREIGN KEY (place_id) REFERENCES place(id);


--
-- TOC entry 2037 (class 2606 OID 24845)
-- Name: place fkdwesyk0ckbc6rsieee3il40ra; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY place
    ADD CONSTRAINT fkdwesyk0ckbc6rsieee3il40ra FOREIGN KEY (type_id) REFERENCES type(id);


-- Completed on 2017-10-05 20:55:23

--
-- PostgreSQL database dump complete
--

