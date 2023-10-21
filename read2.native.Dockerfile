FROM gradle:jdk21-graal AS builder

RUN java -version
RUN gradle -version
RUN native-image --version

WORKDIR /
ADD settings.gradle gradlew build.gradle gradlew.bat ./app-r/
WORKDIR /app-r
RUN gradle dependencies

FROM builder AS gradle
WORKDIR /
COPY src ./app-r/src/
WORKDIR /app-r
RUN gradle nativeCompile

FROM oraclelinux:9-slim
ENV NAME_APP=read3

COPY --from=gradle "/app-r/build/native/nativeCompile/$NAME_APP" read3-native
CMD [ "sh", "-c", "./read3-native " ]

# docker build --progress=plain -t r1-j87 -f read2.native.Dockerfile .
# docker run --name j87 -p 8080:8080 -d r1-j87