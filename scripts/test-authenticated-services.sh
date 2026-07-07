#!/usr/bin/env bash

# Pobierz ścieżkę do katalogu, w którym znajduje się skrypt
SCRIPTS_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# Kolory dla wygody
RED='\033[0;31m'
GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m' # Brak koloru

API_URL="http://localhost:8080"
USERNAME="jkowalski"
PASSWORD="password123"

echo -e "${BLUE}🔍 Testowanie zalogowanych usług na ${API_URL} dla użytkownika '${USERNAME}'...${NC}\n"

# 1. Logowanie w celu pobrania tokenu JWT
echo -e "Testing: POST /identity/login"
LOGIN_RESPONSE=$(curl -s -w "\n%{http_code}" -X POST "${API_URL}/identity/login" \
     -H "Content-Type: application/json" \
     -d "{\"login\":\"${USERNAME}\",\"password\":\"${PASSWORD}\"}")

# Rozdzielenie kodu statusu od ciała odpowiedzi
RESPONSE_BODY=$(echo "$LOGIN_RESPONSE" | head -n 1)
RESPONSE_CODE=$(echo "$LOGIN_RESPONSE" | tail -n 1)

if [ "$RESPONSE_CODE" -eq 200 ]; then
    echo -e "${GREEN}✅ Zalogowano pomyślnie (Status: 200)${NC}"
else
    echo -e "${RED}❌ Logowanie nie powiodło się (Status: ${RESPONSE_CODE})${NC}"
    echo -e "Odpowiedź serwera: ${RESPONSE_BODY}"
    exit 1
fi

# Wyciągnięcie tokenu z formatu {"token":"XYZ"} bez zależności od jq
TOKEN=$(echo "$RESPONSE_BODY" | sed -n 's/.*"token":"\([^"]*\)".*/\1/p')

if [ -z "$TOKEN" ]; then
    echo -e "${RED}❌ Nie udało się wyodrębnić tokenu JWT z odpowiedzi!${NC}"
    exit 1
fi

echo -e "${GREEN}🔑 Token JWT pobrany pomyślnie.${NC}"

# 2. Test GET /articles z pobranym tokenem (Powinno być 200 OK)
echo -e "\nTesting: GET /articles (authenticated)"
ARTICLES_RESPONSE=$(curl -s -w "\n%{http_code}" "${API_URL}/articles" \
     -H "Authorization: Bearer ${TOKEN}")

ARTICLES_BODY=$(echo "$ARTICLES_RESPONSE" | head -n 1)
ARTICLES_CODE=$(echo "$ARTICLES_RESPONSE" | tail -n 1)

if [ "$ARTICLES_CODE" -eq 200 ]; then
    echo -e "${GREEN}✅ GET /articles zakończone sukcesem (Status: 200)${NC}"
    echo -e "Lista artykułów: ${ARTICLES_BODY}"
else
    echo -e "${RED}❌ GET /articles nie powiodło się (Status: ${ARTICLES_CODE})${NC}"
    echo -e "Odpowiedź serwera: ${ARTICLES_BODY}"
    exit 1
fi

# 3. Test GET /articles/1 z pobranym tokenem (Powinno być 200 OK)
echo -e "\nTesting: GET /articles/1 (authenticated)"
DETAILS_RESPONSE=$(curl -s -w "\n%{http_code}" "${API_URL}/articles/1" \
     -H "Authorization: Bearer ${TOKEN}")

DETAILS_BODY=$(echo "$DETAILS_RESPONSE" | head -n 1)
DETAILS_CODE=$(echo "$DETAILS_RESPONSE" | tail -n 1)

if [ "$DETAILS_CODE" -eq 200 ]; then
    echo -e "${GREEN}✅ GET /articles/1 zakończone sukcesem (Status: 200)${NC}"
    echo -e "Szczegóły artykułu: ${DETAILS_BODY}"
else
    echo -e "${RED}❌ GET /articles/1 nie powiodło się (Status: ${DETAILS_CODE})${NC}"
    echo -e "Odpowiedź serwera: ${DETAILS_BODY}"
    exit 1
fi

echo -e "\n${GREEN}🎉 Wszystkie testy usług wymagających logowania zakończone sukcesem!${NC}"
exit 0
