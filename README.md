# ZaloraTest

Please refer the below steps to run the application

Step 1 -> Execute the jar 
java -jar UrlShortner-0.0.1-SNAPSHOT.jar

Step 2 -> Run the curl command to post the request
curl --location --request POST 'http://localhost:8080/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "alias":"zal",
    "url" :"https://www.zalora.com.my"
}'

output

{"alias":"zal1QIBEnD6U2","url":"https://www.zalora.com.my"}

curl --location --request POST 'http://localhost:8080/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "alias":"gcp",
    "url" :"https://cloud.google.com/pubsub#section-7"
}'

{"alias":"gcpoA950H8","url":"https://cloud.google.com/pubsub#section-7"}


Step 3 -> After Sucessfull post you will get the alias name

http://localhost:8080/zal1QIBEnD6U2
http://localhost:8080/gcpoA950H8


Step 4-> Request Will be stored in the in memory db

http://localhost:8080/admin/h2-console

Default login 
username : sa
pwd:

select * from URLtbl

![image](https://user-images.githubusercontent.com/29682980/148693478-91896db3-ff96-4cb2-ba1a-c3c8aa55b447.png)







