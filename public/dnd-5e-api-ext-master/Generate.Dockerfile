FROM openjdk:14-alpine AS build

RUN apk update && apk add --no-cache unzip
RUN mkdir -p /home/generate
ADD . /home/generate/src/
WORKDIR /home/generate/src

RUN ./gradlew --no-daemon :generate:build
WORKDIR /home/generate
RUN mkdir app
RUN unzip -d /home /home/generate/src/generate/build/distributions/generate.zip
RUN ls app

FROM openjdk:14-alpine
COPY --from=build /home/generate /home/generate
ENTRYPOINT "/home/generate/bin/generate"
