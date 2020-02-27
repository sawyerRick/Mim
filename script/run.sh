
# client1
java -jar target/mim-client-0.0.1-SNAPSHOT.jar --server.port=9090 --mim.config.srcId=123456 --mim.config.srcName=sawyer


# client2
java -jar target/mim-client-0.0.1-SNAPSHOT.jar --server.port=9091 --mim.config.srcId=123 --mim.config.srcName=sa


# client3
java -jar target/mim-client-0.0.1-SNAPSHOT.jar --server.port=9092 --mim.config.srcId=12 --mim.config.srcName=serial


# 注册
curl -d "srcId=111&srcName=myname"  http://localhost:8082/registry