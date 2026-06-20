#!/usr/bin/env bash

# Pobierz ścieżkę do katalogu, w którym znajduje się skrypt, a następnie przejdź katalog wyżej (do roota projektu)
PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$PROJECT_ROOT" || exit 1

echo "🛑 Zatrzymywanie kontenera z bazą danych MySQL..."
docker compose -f backend/database/docker-compose.yml stop

echo "✅ Baza danych została zatrzymana."
