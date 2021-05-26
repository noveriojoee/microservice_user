FROM java:8
ADD eSignature-0.0.1-SNAPSHOT.jar eSignature-0.0.1-SNAPSHOT.jar
EXPOSE 8071
CMD ["java","-Xms768m","-Xmx1024m","-XX:PermSize=768m","-XX:MaxPermSize=768m","-jar","eSignature-0.0.1-SNAPSHOT.jar","log.out","&"]
