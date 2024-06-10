VERSION --try 0.8
PROJECT sjerred/castle-casters

ARG --global mvn_cache="type=cache,target=/root/.m2/repository,id=mvn,sharing=shared"

ci:
  BUILD +build

deps:
  FROM maven:3-amazoncorretto-21
  COPY pom.xml .
  RUN --mount $mvn_cache mvn dependency:resolve
  RUN rm pom.xml

src:
  FROM +deps
  COPY src pom.xml .

build:
  FROM +src
  RUN --mount $mvn_cache mvn package
  SAVE ARTIFACT target/castle-casters-1.0.0-SNAPSHOT.jar AS LOCAL castle-casters.jar

test:
  FROM +src
  TRY
    RUN --mount $mvn_cache mvn test
  FINALLY
    SAVE ARTIFACT target/surefire-reports AS LOCAL surefire-reports
  END
