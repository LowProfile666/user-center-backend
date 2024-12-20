FROM maven:3.8.3-openjdk-17 as builder

# 将本地代码复制到容器镜像中
WORKDIR /app
COPY pom.xml .
COPY src ./src

# 构建项目，并跳过测试
RUN mvn package -DskipTests

# 在容器启动时运行 Web 服务
CMD ["java", "-jar", "/app/target/user-center-backend-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod"]