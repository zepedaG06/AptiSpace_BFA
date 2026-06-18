DO $$
BEGIN
	IF EXISTS (SELECT FROM pg_roles WHERE rolname = 'aptispace') THEN
		ALTER USER aptispace WITH PASSWORD 'aptispace';
	ELSE
		CREATE USER aptispace WITH PASSWORD 'aptispace';
	END IF;
END
$$;

SELECT 'CREATE DATABASE aptispace_bfa OWNER aptispace'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'aptispace_bfa')\gexec

ALTER DATABASE aptispace_bfa OWNER TO aptispace;
GRANT ALL PRIVILEGES ON DATABASE aptispace_bfa TO aptispace;
