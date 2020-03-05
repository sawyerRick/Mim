
# client1
java -jar mim-client/target/mim-client-0.0.1-SNAPSHOT.jar --server.port=9090 --mim.config.userId=2369058761398941 --mim.config.username=serial


# client2
java -jar mim-client/target/mim-client-0.0.1-SNAPSHOT.jar --server.port=9091 --mim.config.userId=2369106717063327 --mim.config.username=sawyer


# 注册
curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"username":"serial"}'  http://localhost:8081/registry

curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"username":"sawyer"}'  http://localhost:8081/registry