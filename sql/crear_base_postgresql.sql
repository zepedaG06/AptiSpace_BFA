DO $$
BEGIN
	IF EXISTS (SELECT FROM pg_roles WHERE rolname = 'aptispace_bfa') THEN
		ALTER USER aptispace_bfa WITH PASSWORD 'aptispace_bfa';
	ELSE
		CREATE USER aptispace_bfa WITH PASSWORD 'aptispace_bfa';
	END IF;
END
$$;

SELECT 'CREATE DATABASE aptispace_bfa OWNER aptispace_bfa'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'aptispace_bfa')\gexec

ALTER DATABASE aptispace_bfa OWNER TO aptispace_bfa;
GRANT ALL PRIVILEGES ON DATABASE aptispace_bfa TO aptispace_bfa;
