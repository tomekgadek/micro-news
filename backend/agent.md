# Micro News - Backend (Context/Skill for AI)

Ten dokument definiuje kontekst techniczny dla części serwerowej projektu `micro-news`. Asystenci AI powinni traktować te instrukcje jako bazową wiedzę o tym module oraz opierać się na nich podczas wdrażania rozwiązań w części serwerowej.

## Stos technologiczny (Tech Stack)

Aplikacja backendowa opiera się na poniższym stosie technologicznym:
- **Język programowania:** Java 21
- **Główny framework:** Spring Boot 3.3.0
- **Zależności:** Spring Web (REST API, MVC), Spring Data JPA
- **System budowania:** Maven
- **Driver bazy danych:** MySQL (`mysql-connector-j`)

## Architektura & Baza Danych MySQL (Docker)

Infrastruktura bazy danych (MySQL 8.0) jest konteneryzowana i jej definicja znajduje się w katalogu `database`. 
Aby zapewnić poprawność i wygodę operacyjną, **AI musi używać** przygotowanych skryptów z głównego katalogu `scripts` (np. `start-db.sh`, `connect-db.sh`).

Alternatywnie, z poziomu katalogu `backend/database/` można użyć komendy dockera:
```bash
docker compose up -d
```

### Parametry Połączenia z Bazą

Podczas konfiguracji aplikacji backendowej lub testowania połączenia po TCP/IP z poziomu fizycznego komputera hosta, AI (oraz deweloper) musi bazować na poniższych danych:

- **Host TCP:** `127.0.0.1` *(Notatka dla AI: z poziomu hosta omijaj wartość 'localhost' przy kliencie MySQL CLI, by nie wymuszać użycia socketu uniksowego)*
- **Port:** `3306`
- **Nazwa bazy danych:** `micro-news`
- **Użytkownik (App):** `mn_backend_user`
- **Hasło (App):** `mn_Backend_Password_2026#`
- **Konto ROOT:** `root` (Hasło: `Root_Secure_MN_2026!`)

**Lokalizacja modułu:** `/Users/tomaszgadek/IdeaProjects/micro-news/backend/`
