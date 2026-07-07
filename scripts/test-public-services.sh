#!/usr/bin/env bash

# Pobierz ścieżkę do katalogu, w którym znajduje się skrypt
SCRIPTS_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# Kolory dla wygody
RED='\033[0;31m'
GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m' # Brak koloru

API_URL="http://localhost:8080"

echo -e "${BLUE}🔍 Testowanie publicznych usług na ${API_URL}...${NC}\n"

# 1. Test GET /articles/public (Powinno być 200 OK)
echo -e "Testing: GET /articles/public"
RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" "${API_URL}/articles/public")

if [ "$RESPONSE_CODE" -eq 200 ]; then
    echo -e "${GREEN}✅ GET /articles/public zakończone sukcesem (Status: 200)${NC}"
else
    echo -e "${RED}❌ GET /articles/public nie powiodło się (Status: ${RESPONSE_CODE})${NC}"
    exit 1
fi

# 2. Test GET /articles bez logowania (Powinno być 401 Unauthorized)
echo -e "\nTesting: GET /articles (without credentials)"
RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" "${API_URL}/articles")

if [ "$RESPONSE_CODE" -eq 401 ]; then
    echo -e "${GREEN}✅ GET /articles został zablokowany prawidłowo (Status: 401)${NC}"
else
    echo -e "${RED}❌ GET /articles nie został zablokowany! (Status: ${RESPONSE_CODE})${NC}"
    exit 1
fi

# 3. Test GET /articles/1 bez logowania (Powinno być 401 Unauthorized)
echo -e "\nTesting: GET /articles/1 (without credentials)"
RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" "${API_URL}/articles/1")

if [ "$RESPONSE_CODE" -eq 401 ]; then
    echo -e "${GREEN}✅ GET /articles/1 został zablokowany prawidłowo (Status: 401)${NC}"
else
    echo -e "${RED}❌ GET /articles/1 nie został zablokowany! (Status: ${RESPONSE_CODE})${NC}"
    exit 1
fi

echo -e "\n${GREEN}🎉 Wszystkie testy usług publicznych zakończone sukcesem!${NC}"
exit 0
