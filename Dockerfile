#########################################################################################################

# Step : Test and package
#FROM maven:3.6.2-jdk-11-openj9 AS build
# install packages
RUN apt-get update && \
    apt-get install -y curl \
    wget \
    openjdk-8-jdk

WORKDIR /usr/share/wow

COPY ./pom.xml ./pom.xml

RUN mvn dependency:go-offline

# copy your other files
COPY ./src ./src

# build for release
RUN mvn package

ENTRYPOINT [mvn clean test verify]
#########################################################################################################
