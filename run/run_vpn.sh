#!/bin/bash

set -e

printf "\n"
printf '==================================================================================\n'
printf '========== INICIADO SCRIPT DE RUN CONFIGURADO PARA HOT DEPLOY QUARKUS ============\n'
printf '==================================================================================\n'
printf "\n"

echo "Informe as suas credenciais para acesso ao DB2 de desenvolvimento"
echo "(Confira no seu docker-compose se as configurações estão corretas)"
read -p "Chave do Usuário: " DB2_USER
read -sp "Senha do Usuário: " DB2_PASSWORD; echo

export DB2_USER
export DB2_PASSWORD

echo "Credenciais para acesso ao DBIQ"
read -p "Chave Técnica de Homologação: " DBIQ_USER
read -sp "Senha do Usuário Homologação: " DBIQ_TOKEN; echo

export DBIQ_USER
export DBIQ_TOKEN

printf "======= Path do Script de Carga do H2 no Contexto do Local =======\n"
# LOCAL SCRIPT CARGA H2
export WORKSPACE="${PWD}/src/main/resources"
export myUID=${UID}
export myGroup=${GID}
printf ${WORKSPACE}
printf "\n"
printf "\n"

printf "============== Construido o Projeto com Maven Local ==============\n"
printf "\n"
mvn clean install -Ddbiq.skip=false -Ddbiq.unbreakable=true -Ddbiq.user=$DBIQ_USER -Ddbiq.password=$DBIQ_TOKEN
printf "\n"
printf "============ Projeto Construido com Sucesso pelo Maven ===========\n"
printf "\n"


printf "======= Path do Script de Carga do H2 no Contexto do Docker ======\n"
# export WORKSPACE="/app/src/main/resources"
# printf ${WORKSPACE}
# printf "\n"
# printf "\n"

export JAVA_OPTS="-Djava.rmi.server.hostname=0.0.0.0 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=8999 -Dcom.sun.management.jmxremote.rmi.port=8999 -Dcom.sun.management.jmxremote.local.only=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"
export JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000" 

printf "== Executando o Docker Compose Com Build para subir a aplicação ==\n"
printf "\n"

docker-compose -f $PWD/run/docker-compose.vpn.yaml up --build