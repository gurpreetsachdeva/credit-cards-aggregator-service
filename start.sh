###Usage 
#Examples
#./start.sh 
# Above command will pick up the values for system environment variables HTTP_PORT,CSCARDS_ENDPOINT,SCOREDCARDS_ENDPOINT[Default Services]

#./start.sh -p port -s services_config where service_config is a combinaton of multiple services

#./start.sh -p port_no -s "CSCards$https://app.clearscore.com/api/global/backend-tech-test/v1/cards$10$eligibility#ScoredCards$https://app.clearscore.com/api/global/backend-tech-test/v2/creditcards$100$approvalRating"

#!/bin/bash


DEFAULT_PORT=8080
DEFAULT_SERVICEURL1=https://app.clearscore.com/api/global/backend-tech-test/v1/cards
DEFAULT_SERVICEURL2=https://app.clearscore.com/api/global/backend-tech-test/v2/creditcards

DEFAULT_SERVICE1_NAME=CSCards
DEFAULT_SERVICE2_NAME=ScoredCards

DEFAULT_SERVICE1_FIELD=eligibility

DEFAULT_SERVICE2_FIELD=approvalRating

DEFAULT_SERVICE1_SCALE=10

DEFAULT_SERVICE2_SCALE=100

PORT=$HTTP_PORT
SERVICE1_URL=$CSCARDS_ENDPOINT
SERVICE2_URL=$SCOREDCARDS_ENDPOINT



if [ -z "$PORT" ] 
then 
	echo "Overwriting port using -p"
	PORT=$DEFAULT_PORT
fi

if [ -z "$SERVICE1_URL" ] 
then 
	echo "Overwriting CS Cards as environment variable is blank"
	SERVICE1_URL=$DEFAULT_SERVICEURL1
fi

if [ -z "$SERVICE2_URL" ] 
then 
	echo "Overwriting Scored Cards as environment variable is blank"
	SERVICE2_URL=$DEFAULT_SERVICEURL2
	
fi

DEFAULT_SERVICES=$DEFAULT_SERVICE1_NAME\$$SERVICE1_URL\$$DEFAULT_SERVICE1_SCALE\$$DEFAULT_SERVICE1_FIELD#$DEFAULT_SERVICE2_NAME\$$SERVICE2_URL\$$DEFAULT_SERVICE2_SCALE\$$DEFAULT_SERVICE2_FIELD
echo $DEFAULT_SERVICES
 
 
helpFunction()
{
   echo ""
   echo "Usage: $0 -p paramA -s url1 -k url2"
   echo -e "\t-p Service_Port"
   echo -e "\t-s Services_Config i.e ServiceName1$URL1$scale1$field1#ServiceName2$URL2$scale$field2"
   exit 1 # Exit script after printing help
}


while getopts "p:s:" opt
do
   case "$opt" in
      p ) port="$OPTARG" ;;
      s) config="$OPTARG" ;;
   esac
done





#Overwrite with command line arguments if supplied 
if [ ! -z "$port" ] 
then 
	echo "Overwriting port using -p"
	PORT=$port
fi

if [ ! -z "$config" ] 
then 
	echo "Overwriting services using -s"
	SERVICES=$config
fi

#Otherwise Use the defaults for those who are not overwritten

if [ -z "$PORT" ]
then 
	PORT=$DEFAULT_PORT
fi 


if [ -z "$SERVICES" ]
then 
	SERVICES=$DEFAULT_SERVICES
fi 




echo "spring-boot:run" --server.port=$PORT,--service.endpoints=$SERVICES

./mvnw spring-boot:run --server.port=$PORT,--service.endpoints=$SERVICES
