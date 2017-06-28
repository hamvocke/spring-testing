#! /usr/bin/env bash

echo "Install Postgresql"
sudo apt-get install -y postgresql postgresql-contrib
# Allow connections to Postgres from outside of vagrant
# Edit postgresql.conf to change listen address to '*':
sed -i "s/#listen_addresses = 'localhost'/listen_addresses = '*'/" "/etc/postgresql/9.5/main/postgresql.conf"

# Append to pg_hba.conf to add password auth:
echo "host    all             all             all                     md5" >> "/etc/postgresql/9.5/main/pg_hba.conf"

# Restart so that all new config is loaded:
service postgresql restart

DB_USER=testuser
DB_PASS=postgres
DB_NAME=testdb

#sudo su postgres -c "psql -c \"CREATE USER ${DB_USER} WITH PASSWORD '${DB_PASS}'\""
#sudo su postgres -c "createdb -E UTF8 -T template0 --locale=en_US.utf8 -O ${DB_NAME} wtm"

cat << EOF | sudo su - postgres -c psql
-- Create the database user:
CREATE USER ${DB_USER} WITH PASSWORD '${DB_PASS}';

-- Create the database:
CREATE DATABASE ${DB_NAME} WITH OWNER=${DB_USER}
                                  LC_COLLATE='en_US.utf8'
                                  LC_CTYPE='en_US.utf8'
                                  ENCODING='UTF8'
                                  TEMPLATE=template0;
EOF
