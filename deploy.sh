#!/bin/bash
set -e

echo "👉 Atualizando repositório web..."
cd financeiro-web/
git pull -4
npm install
npm run build
cd ..

echo "👉 Atualizando repositório back..."
cd financeiro-api/
git pull -4
cd ..

echo "👉 Subindo e construindo containers com Docker..."
docker compose up --build -d

echo "✅ Deploy finalizado com sucesso!"
