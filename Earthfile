# =====================================================
# DEPRECATED: This Earthfile has been replaced
# =====================================================
#
# This project has migrated from Earthly/Jenkins to GitHub Actions/Dagger.
#
# New CI/CD configuration:
# - GitHub Actions: .github/workflows/ci.yaml
# - Dagger Module: .dagger/
#
# See MIGRATION.md for details about the migration.
#
# This file is kept for reference and can be removed after successful migration.
# =====================================================

VERSION --try 0.8
FROM maven:3-amazoncorretto-21
WORKDIR /workspace

ARG --global mvn_cache="type=cache,target=/root/.m2/repository,id=mvn,sharing=shared"

ci:
  BUILD +build

deps:
  COPY pom.xml .
  RUN --mount $mvn_cache mvn dependency:resolve
  RUN rm pom.xml

src:
  FROM +deps
  COPY src src
  COPY pom.xml .

build:
  FROM +src
  RUN --mount $mvn_cache mvn package
  SAVE ARTIFACT target/castle-casters-1.0.0-SNAPSHOT-jar-with-dependencies.jar AS LOCAL castle-casters.jar

test:
  FROM +src
  TRY
    RUN --mount $mvn_cache mvn test
  FINALLY
    SAVE ARTIFACT target/surefire-reports AS LOCAL surefire-reports
  END
