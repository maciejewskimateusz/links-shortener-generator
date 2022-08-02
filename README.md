# links-shortener-generator
Simple URL shortener generator created using:
* Spring boot
* Spring Web
* H2 DB


# Installation
1. You need java 17
2. Open docker-build.bat
3. Enjoy

# Usage
* POST `/api/links` with body as `your_long_url` for creating short url with random alias
* POST `/api/links/{your_alias}` with body as `"alias": "your_alias" and "url": "your_long_url"` for creating short url with your alias
* GET `/api/links/{id}` - get long url from short alias
