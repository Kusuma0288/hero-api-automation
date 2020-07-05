#########################################################################################################

# Step : Test and package
#FROM maven:3.6.2-jdk-11-openj9 AS build
# install packages
RUN apt-get update && \
    apt-get install -y curl \
    wget \
    openjdk-8-jdk

/# echo "---java version---"
/# java -version

/# update-alternatives --list java    
#FROM maven:3.6.0-jdk-11 AS build

WORKDIR /usr/share/wow

COPY ./pom.xml ./pom.xml

RUN mvn dependency:go-offline

# copy your other files
COPY ./src ./src

# build for release
RUN mvn package

#
## Step : Package image
#FROM openjdk:8-jre-alpine
#
#EXPOSE 4567
#
## copy over the built artifact from the maven image
#COPY --from=target /usr/share/wow/target/*.jar ./
#
### set the startup command to run your binary
#CMD ["java", "-jar", "./target/*.jar"]
#
ENTRYPOINT [mvn clean test verify]

#ENTRYPOINT ["/bin/sh", "-c", "sh testexecution.sh"]
#########################################################################################################
