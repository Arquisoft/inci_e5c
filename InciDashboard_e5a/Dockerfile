FROM maven:3.5-jdk-8-alpine
RUN ls -l
WORKDIR /usr/src/inci_e5c/InciDashboard_e5a
COPY . /usr/src/inci_e5c/InciDashboard_e5a
RUN mvn package
EXPOSE 8091
CMD ["java", "-jar", "target/InciDashboard_e5a-0.0.1.jar"]