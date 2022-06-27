FROM maven:3-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn -B package

FROM tomcat:10-jdk17-openjdk-slim
COPY --from=build /app/target/webapp.war $CATALINA_HOME/webapps/ROOT.war
EXPOSE 8080
