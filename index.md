# Micro News - Główny Punkt Wejścia (Context/Skill for AI)

Projekt `micro-news` to wielomodułowa aplikacja. Ten plik służy jako główny drogowskaz nawigacyjny dla programistów oraz asystentów AI współpracujących przy projekcie.

## Struktura projektu

Projekt składa się z trzech głównych części. Każda z nich posiada własny plik `index.md` zawierający szczegółowe informacje techniczne (AI powinno zawsze zapoznać się z tym plikiem po wejściu do danego modułu):

- **[Backend](./backend)** - Kod części serwerowej oraz infrastruktury bazodanowej.
- **[Frontend](./frontend)** - Aplikacja webowa dla użytkowników.
- **[Mobile](./mobile)** - Aplikacja mobilna.

## Narzędzia i Skrypty (Zarządzanie środowiskiem)

W katalogu `scripts` znajdują się pomocnicze skrypty ułatwiające pracę i zarządzanie środowiskiem developerskim:
- `./scripts/start-db.sh` - uruchamia środowisko bazy danych MySQL w kontenerze Docker.
- `./scripts/stop-db.sh` - zatrzymuje kontener bazy danych.
- `./scripts/connect-db.sh` - otwiera sesję terminalową bazy danych uwierzytelniając od razu predefiniowanego użytkownika deweloperskiego.

**Lokalizacja projektu:** `/Users/tomaszgadek/IdeaProjects/micro-news/`
