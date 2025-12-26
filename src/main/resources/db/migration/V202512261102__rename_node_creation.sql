ALTER TABLE public.nodes RENAME COLUMN creation_instant TO created_at;
ALTER TABLE public.nodes RENAME COLUMN id_user TO owner_id;