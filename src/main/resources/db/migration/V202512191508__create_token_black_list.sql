CREATE TABLE public.token_blacklist (
	jti uuid NOT NULL,
	expiration_instant int8 NOT NULL,
	CONSTRAINT token_blacklist_pk PRIMARY KEY (jti)
);
