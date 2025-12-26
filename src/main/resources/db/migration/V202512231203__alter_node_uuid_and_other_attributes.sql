ALTER TABLE public.nodes DROP COLUMN id CASCADE;
ALTER TABLE public.nodes ADD COLUMN id uuid;

ALTER TABLE public.nodes DROP COLUMN id_parent_node;
ALTER TABLE public.nodes ADD COLUMN id_parent_node uuid;

ALTER TABLE public.nodes ALTER COLUMN "name" DROP NOT NULL;
ALTER TABLE public.nodes ADD tree_path text NOT NULL;
ALTER TABLE public.nodes ADD node_content_type varchar(100) NULL;
ALTER TABLE public.nodes RENAME COLUMN creation_date_time TO creation_instant;
