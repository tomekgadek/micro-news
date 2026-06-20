#!/usr/bin/env bash

echo "Połączono! Wchodzę do powłoki MySQL (baza: micro-news)..."
# Używamy -h 127.0.0.1 zamiast localhost, aby wymusić połączone TCP z kontenerem Dockera
mysql -h 127.0.0.1 -P 3306 -u mn_backend_user -p'mn_Backend_Password_2026#' micro-news
