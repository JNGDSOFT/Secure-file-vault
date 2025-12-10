ALTER TABLE public.users
ADD COLUMN role VARCHAR(50);

UPDATE public.users
SET role = 'USER'
WHERE role IS NULL;

ALTER TABLE public.users
ALTER COLUMN role SET NOT NULL;