#Requirements
#Design
#Deployment


#Running Commands

1. ./start.sh  : Will pick up default end points or overwrite them using environment variables HTTP_PORT,CSCARDS_ENDPOINT,SCOREDCARDS_ENDPOINT.
2. ./start.sh -p port_no -s "service1 service2 service3 service4" : Starts the service on port_no and with the help of 4 upstream services.Can be extended to 10 or 20 or 30, just pass them on. Currently these upstreams are called in sync with no threading in place. Can be optimized by calling them in parallel and then aggregating.[TODO],
3. ./stop.sh : Kill the running service. Fetch the pid and just kills that java process.



