# BackendTest Project

## Instructions to run the code:

Requirements -
Java 17
JAVA_HOME variable must be set in your environment.

Clone the repository, then, inside the project folder you must run the following Maven commands in your CLI:

Linux - ./mvnw spring-boot:run

Windows - ./mvnw.cmd spring-boot:run

## What were the main technical decisions you made:
I began the development process by creating the entity classes. Subsequently, I made the Controller responsible for handling incoming requests to the server,i like this method because you can do test requests by using curl or any request interface. Following this, I implemented a service where I initially included all the product rules.

With the services structured, I had conducted a series of tests to validate the functionality of each resource.

## Relevant comments about your project:
A little after i did the service where i included all the product rules, i identified an opportunity to enhance the code organization. Recognizing  logic involved in scoring and project eligibility, I decided to create a separate class for each. This modular approach enhances code readability and maintainability.
