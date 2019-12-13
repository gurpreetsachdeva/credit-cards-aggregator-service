###Usage 
#Examples
#./start.sh port service1_url service2_url service3_url 
#./start.sh -p port -s1 service1_url -s2 service2_url -s3 service3_url
#./start.sh 
# Above command will pick up the values for system environment variables HTTP_PORT,CSCARDS_ENDPOINT,SCOREDCARDS_ENDPOINT[Default Services]

DEFAULT_PORT=8080
DEFAULT_SERVICE1=https://app.clearscore.com/api/global/backend-tech-test/v1/cards
DEFAULT_SERVICE2=https://app.clearscore.com/api/global/backend-tech-test/v2/creditcards

#!/bin/bash

SERVICE_PORT=$HTTP_PORT
SERVICE1=$CSCARDS_ENDPOINT
SERVICE2=$SCOREDCARDS_ENDPOINT


 
 
 
helpFunction()
{
   echo ""
   echo "Usage: $0 -p paramA -s url1 -k url2"
   echo -e "\t-p Service Port"
   echo -e "\t-s1 Service1 Endpoint URL1"
   echo -e "\t-s2 Service2 Endpoint URL2"
   exit 1 # Exit script after printing help
}


while getopts "p:s:k:" opt
do
   case "$opt" in
      p ) port="$OPTARG" ;;
      s) url1="$OPTARG" ;;
      k) url2="$OPTARG" ;;
   esac
done

# Set the variables from enivornoment variables
PORT=$HTTP_PORT
SERVICE1=$CSCARDS_ENDPOINT
SERVICE2=$SCOREDCARDS_ENDPOINT


#Overwrite with command line arguments if supplied 
if [ ! -z "$port" ] 
then 
	echo "Overwriting port"
	PORT=$port
fi

if [ ! -z "$url1" ] 
then 
	SERVICE1=$url1
fi

if [ ! -z "$url2" ] 
then 
	SERVICE2=$url2
fi 

#Otherwise Use the defaults for those who are not overwritten

if [ -z "$PORT" ]
then 
	PORT=$DEFAULT_PORT
fi 


if [ -z "$SERVICE1" ]
then 
	SERVICE1=$DEFAULT_SERVICE1
fi 

if [ -z "$SERVICE2" ]
then 
	SERVICE2=$DEFAULT_SERVICE2
fi 



echo "spring-boot:run" ,$PORT $SERVICE1 $SERVICE2
./mvnw spring-boot:run $PORT $SERVICE1 $SERVICE2
