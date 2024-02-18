docker run --detach
  --env MYSQL_ROOT_PASSWORD=docker
  --env MYSQL_USER=docker-user
  --env MYSQL_PASSWORD=docker
  --env MYSQL_DATABASE=social-media-db
  --name mysql
  --publish 3306:3306
mysql:8-oracle