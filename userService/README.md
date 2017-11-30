# UserService

How to start the UserService application
---

1. Run `mvn clean install` to build your application (needes maven wrapper)
2. Start application with `java -jar target/userService-0.1.0-SNAPSHOT.jar server config.yml`
3. Server running at `http://localhost:50012`
4. Admin service running at `http://localhost:50011`

Health Check
---

To see your applications health enter url `http://localhost:50012/healthcheck`
