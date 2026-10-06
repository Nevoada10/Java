if [[ -n $(docker compose ls | grep postgres) ]]; then 
  echo "Reiniciant el docker compose de postgres"
  docker compose down --remove-orphans
  docker rmi $(docker images|grep postgres|tr -s " " |cut -f3 -d" ")  
fi
echo "-----------------------------------------------------------------------------"
echo "Aixecant els serveis en segon pla i mantenint-los en execució persistent..."
docker compose up postgres -d
echo "-----------------------------------------------------------------------------"
echo "Quin usuari vols crear? "
read user
echo "Creant l'usuari $user..."
docker exec -it $(docker ps|grep postgres|cut -f1 -d" ") /bin/bash -c "psql template1 -U admin -c 'DROP USER IF EXISTS $user; CREATE USER $user CREATEDB' && psql template1 -U $user"
