CREATE TABLE public.user_role (
    id_user int8 NOT NULL,
    role_name VARCHAR(50) NOT NULL,
    
    CONSTRAINT user_role_pk PRIMARY KEY (id_user, role_name),

    CONSTRAINT user_role_fk_user
        FOREIGN KEY (id_user)
        REFERENCES public.users (id)
);

