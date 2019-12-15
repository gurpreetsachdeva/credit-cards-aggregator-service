#Requirements
#Design
#Deployment


#Running Commands

1. ./start.sh  : Will pick up default end points or overwrite them using environment variables HTTP_PORT,CSCARDS_ENDPOINT,SCOREDCARDS_ENDPOINT.
2. ./start.sh -p port_no -s "services_config" : Start aggregating from n services given by service_config. Upgradation to multithreading fetching instead of doing a serial call is pending. Each of the services are separated by a # and each of the four params of a service are separated by a dollar.
E.g 
./start.sh -p port_no -s "CSCards$https://app.clearscore.com/api/global/backend-tech-test/v1/cards$10$eligibility#ScoredCards$https://app.clearscore.com/api/global/backend-tech-test/v2/creditcards$100$approvalRating"

3. ./stop.sh : Kill the running service. Fetch the pid and just kills that java process.

#Swagger UI
http://localhost:8080/credit-cards-aggregator-service/swagger-ui.html

