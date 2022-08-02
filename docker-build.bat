docker build . -t shortener-link-generator
docker stop shortener-link-generator
docker rm shortener-link-generator
docker run -d -p 8080:8080 --name=shortener-link-generator shortener-link-generator