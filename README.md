
.                     |.
:--------------------:|:--------------------:
![GTCC](docs/gt.png)  |![GCP](docs/aws_gcp.png)

# gatech-api

Spring Boot REST API deployed to Google App Engine F1 and managed by AWS API Gateway.

[![Build Status](https://travis-ci.org/aubrey-y/gatech-api.svg?branch=master)](https://travis-ci.org/aubrey-y/gatech-api)
![GitHub top language](https://img.shields.io/github/languages/top/aubrey-y/gatech-api)

## Setup

For local development, you will need to:

1. Install Java 11 and Maven 3.6.3

2. Provision GCP Project with Firestore

3. Set environment variable `GOOGLE_CLOUD_PROJECT` to GCP Project Id

4. Install [gcloud sdk](https://cloud.google.com/sdk/docs/downloads-interactive#linux), run `gcloud init`, and select
the correct project (this is so `GCLOUD_CONFIG` in `pom.xml` actually refers to something)

5. Run `mvn clean install` and `mvn spring-boot:run`

6. Access API at [localhost:8080/api/v1](http://localhost:8080/api/v1)

7. Access Swagger UI at [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

8. Deploy to Google App Engine via `mvn appengine:deploy`
