Requirements
================

We need to create a Credit Card aggregation service which pulls in the data from "N" providers , do a quick score using their data and returns the response as a json.


Design
===========
High Level Design

Create an API Endpoint which implements the specification given in the microservices swagger.json file.



Technologies Used
===========
1. Java8
2. Spring Boot
3. Spring Rest
4. AWS for Deployment
5. Maven as a build system

Deployment
===========

1. ssh -i "AWS_KEY" box_public_DNS
2. Install Java :sudo apt-get install openjdk-8-jdk
3. Clone the code from github or download the tar.gz file in the root folder of github repository given by Gurpreet.
4. tar -xvzf fileName.tar.gz or git clone https://github.com/gurpreetsachdeva/credit-cards-aggregator-service
5. ./start.sh -p port_no &
6. Make sure you allow the security group to query the port opened in previous step,port_no.
7. Use Supervisord or /etc/init.d to run this as a service instead.


Running Commands
======================

1. ./start.sh  : Will pick up default end points or overwrite them using environment variables HTTP_PORT,CSCARDS_ENDPOINT,SCOREDCARDS_ENDPOINT.
2. ./start.sh -p port_no -s "services_config" : Start aggregating from n services given by service_config. Upgradation to multithreading fetching instead of doing a serial call is pending. Each of the services are separated by a # and each of the four params of a service are separated by a dollar.
E.g 
./start.sh -p port_no -s "CSCards$https://app.clearscore.com/api/global/backend-tech-test/v1/cards$10$eligibility#ScoredCards$https://app.clearscore.com/api/global/backend-tech-test/v2/creditcards$100$approvalRating"

3. ./stop.sh : Kill the running service. Fetch the pid and just kills that java process.

Swagger UI
===========
http://localhost:8080/credit-cards-aggregator-service/swagger-ui.html

http://ec2-13-59-150-60.us-east-2.compute.amazonaws.com:8080/credit-cards-aggregator-service/swagger-ui.html

