#!/bin/bash
echo "Deletando a base atual"
sudo -u postgres dropdb gincaneiro
echo "Creating user"
sudo -u postgres psql -c "CREATE USER gincaneiro WITH PASSWORD 'gincaneiro'"
echo "Criando novas bases de dados"
sudo -u postgres createdb gincaneiro -O gincaneiro