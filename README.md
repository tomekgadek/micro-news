# Micro News

Minimalistyczny system newsów składający się z backendu, frontendu oraz aplikacji mobilnej.

## Spis treści

1. [Backend (Java & Spring Boot)](#backend-java--spring-boot)
2. [Frontend (Angular)](#frontend-angular)
3. [Aplikacja Mobilna (Kotlin)](#aplikacja-mobilna-kotlin)
4. [Plan Implementacji](#plan-implementacji)

---

## Backend (Java & Spring Boot)

W tej sekcji znajduje się serwerowa część aplikacji odpowiedzialna za logikę biznesową, udostępnianie REST API dla klientów oraz zarządzanie danymi.

### Wymagania
* Java 17+
* Maven
* MySQL (uruchamiany jako kontener Docker)
* Flyway (zarządzanie schematem bazy danych)

### Uruchomienie
1. Przejdź do katalogu z backendem.
2. Skonfiguruj połączenie z bazą danych w `application.properties` lub `application.yml`.
3. Uruchom aplikację komendą: `./mvnw spring-boot:run`.

---

## Frontend (Angular)

Aplikacja webowa podzielona na dwie strefy: publiczną część frontową dla zwykłych użytkowników do przeglądania newsów oraz ukryty panel CMS, w którym Administrator może zarządzać systemem.

### Wymagania
* Node.js (najlepiej LTS)
* Angular CLI

### Uruchomienie
1. Przejdź do katalogu z aplikacją frontendową.
2. Zainstaluj niezbędne pakiety komendą: `npm install`.
3. Uruchom serwer deweloperski komendą: `ng serve`.
4. Otwórz przeglądarkę i przejdź pod adres: `http://localhost:4200/`.

---

## Aplikacja Mobilna (Kotlin)

Natywna aplikacja przeznaczona na platformę Android. Umożliwia wygodny dostęp do systemu z urządzeń mobilnych.

### Wymagania
* Android Studio (najnowsza stabilna wersja)
* Android SDK
* Gradle

### Uruchomienie
1. Otwórz projekt (lub odpowiedni katalog) w Android Studio.
2. Poczekaj na synchronizację Gradle.
3. Wybierz emulator z Android Virtual Device (AVD) lub podłącz fizyczne urządzenie.
4. Kliknij przycisk *Run* w Android Studio.

---

## Plan Implementacji (Status projektu: 0% - Do wdrożenia)

### 1. Baza Danych (Docker, MySQL, Flyway)
- [DONE] Utworzenie pliku `docker-compose.yml` uruchamiającego kontener z bazą MySQL.
- [DONE] Skonfigurowanie mechanizmu Flyway w aplikacji backendowej do śledzenia i aplikowania migracji.
- [DONE] Przygotowanie skryptów migracyjnych Flyway (SQL) tworzących struktury tabel (`user`, `login`, `article`, `section`, `art_user`, `image`, `art_img`).
- [DONE] Zdefiniowanie poprawnych kluczy obcych i relacji (m.in. 1:N dla sekcji z artykułami, N:M dla zdjęć, logowanie aktywności do `art_user`).
- [TO DO] Przygotowanie skryptu z początkowymi danymi testowymi dla bazy MySQL (sekcje: Sport, Film, Nauka, testowi użytkownicy i newsy).

### 2. Backend (Java, Spring Boot API, Swagger)
- [DONE] Inicjalizacja projektu Spring Boot API (zależności m.in. Spring Web, Spring Data JPA, Flyway, sterownik MySQL).
- [DONE] Konfiguracja połączenia aplikacji z bazą MySQL uruchomioną w Dockerze.
- [DONE] Skonfigurowanie biblioteki Swagger (OpenAPI) w celu automatycznego wygenerowania i udostępnienia dokumentacji API.
- [DONE] Utworzenie encji JPA dokładnie odwzorowujących docelowy schemat bazy MySQL.
- [TO DO] Implementacja mechanizmu zabezpieczeń (np. Spring Security) oraz logowania z wyraźnym podziałem na role (Admin oraz User).
- [TO DO] Zbudowanie ogólnodostępnych endpointów REST dla aplikacji klienckich (pobieranie list artykułów dla poszczególnych sekcji oraz pełnych szczegółów ze zdjęciami).
- [TO DO] Zbudowanie chronionych endpointów REST dla panelu CMS umożliwiających operacje CRUD na artykułach, sekcjach czy obrazkach.
- [TO DO] Implementacja mechanizmu logowania odczytu: odpowiedni endpoint, który zarejestruje w bazie wizytę zalogowanego użytkownika (User) na konkretnym artykule (`art_user`).
- [DONE] Zweryfikowanie wszystkich endpointów z poziomu wbudowanego interfejsu Swagger UI.

### 3. Frontend (Angular - Aplikacja frontowa + CMS)
- [TO DO] Inicjalizacja nowego projektu środowisku Angular.
- [TO DO] Skonfigurowanie zaawansowanego routingu z podziałem na część publiczną oraz panel CMS (zabezpieczony przed brakiem autoryzacji).
- [TO DO] Budowa 3-kolumnowego układu na stronie głównej wyświetlającego newsy podzielone na Sport, Film, Naukę.
- [TO DO] Stworzenie widoku szczegółów (treść "Lorem ipsum...", tytuły, daty, galeria ze zdjęciami, przycisk powrotu).
- [TO DO] Wywołanie endpointu zapisującego fakt przeczytania artykułu podczas wejścia w dany wpis przez zalogowanego użytkownika.
- [TO DO] Przygotowanie graficznego interfejsu administracyjnego do edycji, usuwania i tworzenia nowej zawartości w bazie.
- [TO DO] Integracja frontendu z API oraz dodanie odpowiednich formularzy logowania sterujących tym, czy ktoś wchodzi do CMS, czy korzysta tylko z funkcji dla usera.

### 4. Aplikacja Mobilna (Kotlin)
- [TO DO] Inicjalizacja projektu w środowisku Android Studio.
- [TO DO] Konfiguracja niezbędnych bibliotek do żądań sieciowych (np. Retrofit) oraz komponentów interfejsu (RecyclerView).
- [TO DO] Budowa widoku strony głównej i odpowiednie listowanie pogrupowanych newsów.
- [TO DO] Implementacja własnego widoku ekranu logowania i jego komunikacja z backendem.
- [TO DO] Budowa aktywności / fragmentu wyświetlającego detale artykułu.
- [TO DO] Oprogramowanie akcji otwierania newsa, aby aplikacja mobilna rejestrowała fakt odczytu po stronie API serwera.
- [TO DO] Osadzenie widoku zdjęć oraz dodanie logiki i przycisku powrotu do strony głównej.
