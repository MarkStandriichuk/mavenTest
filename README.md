# mavenTest

## Requirements

- JDK 8
- SQLite
- Allure Framework

## This project contains the following tests

### Database test

1. Checks that the population density is below 50 people/sq.km. only in the USA
2. Checks that the sum of the population of all countries is less than 2 billion people

### Iframe test

1. Checks suggestion in search results
2. Checks link in search results

## Run

- Open folder as a project of your IDE
- All test located in `src/test/java/tests`
- Perform right mose click on test file you want and choose "Run" option
- To open test report paste the following command in the terminal `allure serve allure-results`