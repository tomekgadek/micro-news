# Micro News - Backend (Context/Skill for AI)

Ten dokument definiuje kontekst techniczny dla części serwerowej projektu `micro-news`. Asystenci AI powinni traktować te instrukcje jako bazową wiedzę o tym module oraz opierać się na nich podczas wdrażania rozwiązań w części serwerowej.

## Stos technologiczny (Tech Stack)

Aplikacja backendowa opiera się na poniższym stosie technologicznym:
- **Język programowania:** Java 21
- **Główny framework:** Spring Boot 3.3.0
- **Zależności:** Spring Web (REST API, MVC), Spring Data JPA, Spring Security, JWT (JJWT)
- **Testowanie:** Groovy 4 + Spock 2.4 (pod `src/test/groovy`)
- **System budowania:** Maven
- **Driver bazy danych:** MySQL (`mysql-connector-j`)

## Architektura i Konwencje Kodowania

Aplikacja jest zaprojektowana jako monolit modularny (moduły: `identity`, `content`, `media`, `activity`) z następującymi regułami architektonicznymi:

1. **Enkapsulacja Pakietowa (Package-Private)**:
   - Wszystkie klasy domenowe (encje JPA, repozytoria) powinny być domyślnie pakietowe (brak modyfikatora `public`), aby chronić granice modułu przed bezpośrednim dostępem z zewnątrz.
   - Jedynym publicznym punktem wejścia do logiki modułu jest klasa fasady (np. `ContentFacade`, `IdentityFacade`).
2. **DTO Mappings (`dto()` method)**:
   - Każda encja JPA (np. `Article`, `Section`, `Image`) implementuje pakietową metodę `dto()` (np. `ArticleDto dto()`), która mapuje właściwości encji na odpowiadający jej rekord DTO. Upraszcza to konwersję i utrzymuje encje w czystości bez zbędnych getterów/setterów.
3. **Komunikacja między modułami**:
   - Moduły komunikują się ze sobą wstrzykując swoje fasady (np. `ContentFacade` wstrzykuje `MediaFacade`).

## Strategia Testowania

- **Testy Jednostkowe (Unit Specs)**:
  - Testy są pisane w języku Groovy przy użyciu frameworka Spock i powinny znajdować się w tym samym pakiecie co kod produkcyjny (pod `src/test/groovy/...`).
  - Do testowania logiki biznesowej bez angażowania kontenera Spring i bazy danych (co przyspiesza testy) stosowane są atrapy repozytoriów w pamięci (np. `InMemoryArticleRepository`, `InMemoryImageRepository`).
  - Klasy konfiguracyjne modulów (np. `ContentConfiguration`) udostępniają pakietowe, bezargumentowe lub uproszczone konstruktory fasad, przyjmujące atrapy repozytoriów dla ułatwienia testów jednostkowych.

## Baza Danych MySQL (Docker)

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
