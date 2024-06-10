VERSION 0.8
ARG --global mvn_cache="type=cache,target=/root/.m2/repository,id=mvn,sharing=shared"

deps:
    FROM maven:3-amazoncorretto-21
    COPY pom.xml .
    RUN --mount $mvn_cache mvn dependency:resolve
    RUN rm pom.xml

src:
    FROM +deps
    COPY src/ src/
    COPY pom.xml .

build:
    FROM +src
    RUN --mount $mvn_cache mvn package
    SAVE ARTIFACT target/castle-casters-1.0.0-SNAPSHOT.jar AS LOCAL castle-casters.jar

test:
    FROM +src
    RUN --mount $mvn_cache mvn test
