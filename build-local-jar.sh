#!/bin/sh

cd lib
mvn install:install-file -Dfile=line-bot-api-client-0.2.0-SNAPSHOT.jar -DgroupId=com.line -DartifactId=line-bot-api-client -Dversion=0.2.0-SNAPSHOT -Dpackaging=jar
mvn install:install-file -Dfile=line-bot-model-0.2.0-SNAPSHOT.jar -DgroupId=com.line -DartifactId=line-bot-model -Dversion=0.2.0-SNAPSHOT -Dpackaging=jar
mvn install:install-file -Dfile=line-bot-servlet-0.2.0-SNAPSHOT.jar -DgroupId=com.line -DartifactId=line-bot-servlet -Dversion=0.2.0-SNAPSHOT -Dpackaging=jar
mvn install:install-file -Dfile=line-bot-spring-boot-0.2.0-SNAPSHOT.jar -DgroupId=com.line -DartifactId=line-bot-spring-boot -Dversion=0.2.0-SNAPSHOT -Dpackaging=jar
echo "install done."