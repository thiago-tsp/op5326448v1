#!/bin/bash

set -e

echo "Credenciais para acesso ao Artifactory"
read -p "Chave do Usuário: " ATF_USER
read -sp "Senha do Usuário: " ATF_TOKEN; echo

export ATF_USER
export ATF_TOKEN


echo "Credenciais para execução do DBIQ"
read -p "Chave do Usuário (Tecnica - Homologação): " DBIQ_USER
read -sp "Senha do Usuário (Homologação) ($DBIQ_USER): " DBIQ_TOKEN; echo

export DBIQ_USER
export DBIQ_TOKEN

export WORKSPACE=${PWD}

#mvn clean install -Datf.repo.username=$USERNAME -Datf.repo.password=$PASSWORD -Ddbiq.skip=false -Ddbiq.unbreakable=true -Ddbiq.user=DBIQ_USER -Ddbiq.password=DBIQ_TOKEN -DskipTests=false
mvn clean package -DskipTests -f pom.xml 
#-Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true -Dmaven.wagon.http.ssl.ignore.validity.dates=true

# Execucao do build e up do projeto
docker-compose -f $PWD/run/docker-compose.vpn.yaml up --build

