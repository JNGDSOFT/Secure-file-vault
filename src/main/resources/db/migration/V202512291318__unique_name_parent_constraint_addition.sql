ALTER TABLE public.nodes
ADD CONSTRAINT unique_name_parent
UNIQUE (name, id_parent_node);