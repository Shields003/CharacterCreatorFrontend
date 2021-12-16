FROM adoptopenjdk/openjdk14:x86_64-ubuntu-jdk-14.0.1_7 AS build

RUN mkdir -p /home/web
ADD . /home/web/src/
WORKDIR /home/web/src
RUN ./gradlew dependencies

RUN ./gradlew --no-daemon :web:build

FROM nginx:alpine

COPY --from=build /home/web/src/web/build/distributions/* /usr/share/nginx/html/
RUN ls /usr/share/nginx/html


