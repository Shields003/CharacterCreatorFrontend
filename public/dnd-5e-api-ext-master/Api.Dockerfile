FROM openjdk:14-alpine AS build

RUN apk update && apk add --no-cache unzip
RUN mkdir -p /home/api

ADD . /home/api/src/
WORKDIR /home/api/src

RUN ./gradlew --no-daemon :api:build
WORKDIR /home/api
RUN mkdir app
RUN unzip -d /home /home/api/src/api/build/distributions/api.zip
RUN ls app

FROM openjdk:14-alpine
COPY --from=build /home/api /home/api
ENTRYPOINT "/home/api/bin/api"
