
# 编译 xxx.proto

src=mim-tool/protobuf/MimMsgProtocal.proto && dest=mim-tool/src/main/java && protoc --java_out=${dest} ${src}
