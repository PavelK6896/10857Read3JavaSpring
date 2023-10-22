FROM gradle:jdk21-graal AS builder

RUN java -version
RUN gradle -version
RUN native-image --version

WORKDIR /
ADD ../settings.gradle gradlew build.gradle gradlew.bat ./app-r/
WORKDIR /app-r
RUN gradle dependencies

FROM node:16.15.1-alpine AS build-front
RUN apk add curl unzip zip

ARG token="-"
ARG appNameFolderGit="10317Read2TSAngular-master"

RUN curl -H "Authorization: token $token" -L -O https://github.com/PavelK6896/10317Read2TSAngular/archive/refs/heads/master.zip
RUN unzip -d ./source/ master.zip

WORKDIR /source/$appNameFolderGit/
RUN npm install
RUN npm run "build prod static"

ARG deployBuild
ARG appName="10317-read2-ts-angular"
ARG search="http://localhost:8080"
ARG files="./dist/$appName/*"

RUN for f in $files; do \
         if [[ $search != "" && $deployBuild != "" && "$f" == *"main"* ]]; then \
           sed -i "s+$search+$deployBuild+g" "$f"; \
         fi \
       done

WORKDIR /
RUN cp -r ./source/10317Read2TSAngular-master/dist/10317-read2-ts-angular/ mian

FROM builder AS gradle
WORKDIR /
COPY ../src ./app-r/src/

RUN rm -r /app-r/src/main/resources/static/main
COPY --from=build-front "/mian" /app-r/src/main/resources/static/main

WORKDIR /app-r
RUN gradle nativeCompile

FROM oraclelinux:9-slim
ENV NAME_APP=read3

COPY --from=gradle "/app-r/build/native/nativeCompile/$NAME_APP" read3-native
CMD [ "sh", "-c", "./read3-native " ]

# docker build --progress=plain -t r1-j902 -f ops/read2.native25.Dockerfile .
# docker run --name j903 -p 8080:8080 -d r1-j903


# docker build --no-cache --progress=plain --build-arg deployBuild="https://bbafgaje26q1ffth08gm.containers.yandexcloud.net" -t r1-j904 -f ops/read2.native2.Dockerfile .
# docker login --username oauth --password secret cr.yandex
# docker image tag r1-j903 cr.yandex/crpbtkqol2ing4gt1s4p/r3:v4
# docker push cr.yandex/crpbtkqol2ing4gt1s4p/r3:v4