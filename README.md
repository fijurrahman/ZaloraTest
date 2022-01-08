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


Step 3 -> After Sucessfull post you will get the alias name

http://localhost:8080/zal1QIBEnD6U2


Step 4-> Request Will be stored in the in memory db

http://localhost:8080/admin/h2-console

Default login 
username : sa
pwd:

select * from URLEntity

![image](https://user-images.githubusercontent.com/29682980/148654837-3de6a1aa-72d2-482a-b24e-fb519607dc2f.png)



