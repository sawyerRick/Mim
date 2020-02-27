
# client1
java -jar target/mim-client-0.0.1-SNAPSHOT.jar --server.port=9090 --mim.config.userId=123456 --mim.config.username=serial


# client2
java -jar target/mim-client-0.0.1-SNAPSHOT.jar --server.port=9091 --mim.config.userId=123 --mim.config.username=sawyer


# client3
java -jar target/mim-client-0.0.1-SNAPSHOT.jar --server.port=9092 --mim.config.userId=12 --mim.config.username=sa


# 注册
curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"userId":123456, "username":"serial"}'  http://localhost:8082/registry

curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"userId":123, "username":"sawyer"}'  http://localhost:8082/registry

curl -d "userId=12&userName=sa"  http://localhost:8082/registry