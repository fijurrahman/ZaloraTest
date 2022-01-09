# ZaloraTest

Please refer the below steps to run the application

Step 1 -> Execute the jar 
java -jar UrlShortner-0.0.1-SNAPSHOT.jar

Step 2 -> Run the curl command to post the request
curl --location --request POST 'http://localhost:8080/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "alias":"zalMY",
    "url" :"https://www.zalora.com.my"
}'

output
{"alias":"zalMYcW8sw45d","url":"https://www.zalora.com.my"}



curl --location --request POST 'http://localhost:8080/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "alias":"zalNZ",
    "url" :"https://www.zalora.com.my"
}'

output
{"alias":"zalNZB5rY2kk6","url":"https://www.zalora.com.my"}


curl --location --request POST 'http://localhost:8080/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "alias":"gcp",
    "url" :"https://cloud.google.com/pubsub#section-7"
}'

{"alias":"gcpk0JTHB1f","url":"https://cloud.google.com/pubsub#section-7"}


Step 3 -> After Sucessfull post you will get the alias name

http://localhost:8080/zalNZB5rY2kk6
http://localhost:8080/gcpk0JTHB1f


Step 4-> Request Will be stored in the in memory db

http://localhost:8080/admin/h2-console

Default login 
username : sa
pwd:

select * from URLtbl

![image](https://user-images.githubusercontent.com/29682980/148693645-a7430ef2-cabb-42a8-87a3-2103ad5fdef6.png)




Documentation for API

please clone the below below branch

master-javaDoc






