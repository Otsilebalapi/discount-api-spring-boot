# discount-api-spring-boot
Retail Store Discount calculation API. Backend Skill demonstration


# Introduction

This is a Discount API implementation in Spring Boot which calculates discounts for different types of users, following specific Discount rules:

An Employe gets 30% discount on the bill;

An Affiliate gets 10% discount on the bill;

A Customer who is registered for over 2 years, is eligible for 5% discount on the bill;

A Person is only eligible of One percentage discount.

# INSTALL & RUN

Clone the repository
Open a terminal and cd into the project

Run the following commands:
   
   "mvn clean"
    
    "mvn install"
    
    "mvn spring-boot:run"
    
The app should be up and running on localhost:8081

# TOOLS USED

Spring Boot;

H2 - in memory db for this demo;

Swagger - API documentation;

JWT - token authenication;

Maven Surefire - test report and Code coverage;


# TESTING

To run all tests, execute the following command:

    "mvn test"
    
All test reports and code coverage will be found in the 

"target>surefire-reports folder"

