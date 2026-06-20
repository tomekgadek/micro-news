#!/usr/bin/env bash

# Pobierz ścieżkę do katalogu, w którym znajduje się skrypt, a następnie przejdź katalog wyżej (do roota projektu)
PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$PROJECT_ROOT" || exit 1

echo "🚀 Uruchamianie kontenera z bazą danych MySQL..."
docker compose -f backend/database/docker-compose.yml up -d

echo "✅ Baza danych powinna być teraz dostępna na localhost:3306!"
