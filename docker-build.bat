docker network create links-mysql
docker stop mysql
docker rm mysql
docker run -d -e MYSQL_ROOT_PASSWORD=lol -p 3307:3306 --restart=unless-stopped -v /var/lib/mysql:/var/lib/mysql --network links-mysql --name mysql mysql

docker build . -t shortener-link-generator
docker stop shortener-link-generator
docker rm shortener-link-generator
docker run -d -p 8080:8080 --name=shortener-link-generator -e SPRING_PROFILES_ACTIVE=prod --network links-mysql shortener-link-generator