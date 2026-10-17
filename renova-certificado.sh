#!/bin/bash
set -e

cd ~/projeto/financeiro-app/

echo "👉 Parando container do nginx..."
sudo docker stop nginx-container || true

echo "👉 Renovando certificado SSL..."
renewal_status=0
sudo certbot renew || renewal_status=$?

echo "👉 Liberando porta 80..."
sudo systemctl stop nginx || true
sudo fuser -k 80/tcp || true

echo "👉 Subindo aplicação..."
sh deploy.sh

if [ "$renewal_status" -ne 0 ]; then
	echo "❌ Aplicação no ar, mas a renovação do certificado falhou (código $renewal_status)."
	exit "$renewal_status"
fi

echo "✅ Certificado renovado com sucesso!"
