# 🎭 Playwright - The Internet Automation Testing Framework

## Description
This is an automated testing framework built with **Java** using **Playwright**, **TestNG**, and **Maven**. It is designed to perform functional tests on the website [The Internet](https://the-internet.herokuapp.com), which provides a variety of examples to test common web application features.

---

## Project Structure

```plaintext
src
├── main
│   ├── java
│   │   ├── base
│   │   │   └── BaseTest.java               # Base class for configuration and test reuse
│   │   ├── pages                           # Page Objects for subpages
│   │   │   ├── AddRemoveElementsPage.java
│   │   │   ├── CheckboxPage.java
│   │   │   ├── DropdownPage.java
│   │   │   └── ...                         # Additional pages as implemented
│   │   ├── utils                           # Utilities for configuration and handling
│   │   │   ├── BrowserFactory.java
│   │   │   ├── ConfigReader.java
│   │   │   ├── FrameworkConfig.java
│   │   │   ├── Logger.java
│   │   │   ├── SubPage.java
│   │   │   └── TestDataManager.java
│   └── resources
│       ├── config.properties               # Framework configuration
│       └── TestData.json                   # Test data
├── test
│   ├── java
│   │   ├── tests                           # Test cases organized by subpage
│   │   │   ├── AddRemoveElementsTest.java
│   │   │   ├── CheckboxTest.java
│   │   │   ├── DropdownTest.java
│   │   │   └── ...                         # Additional tests as subpages expand
│   └── resources
│       └── testng.xml                      # TestNG configuration file
```

---

## Technologies and Tools
* Java 17: Primary programming language.
* Playwright: Library for web application testing.
* TestNG: Testing framework for organizing and executing test cases.
* Maven: Build and project management tool.
* JSON: Format for test data.

---

## Setting Up the Project
### Prerequisites
1. Install Java 17 and set the JAVA_HOME environment vaiable.
2. Install Maven and ensure it is accessible from the terminal.

### Clone the Project
```planetext
git clone <repository_url>
cd playwright-the-internet-java-tests
```

### Dependency Setup
Maven automatically manages the dependencies defined in the pom.xml file. To download them, run:
```planetext 
mvn clean install
```

### Configure the ```config.properties``` file
Ensure the values in ```src/main/resources/config.properties``` are correct for your environment:

```plaintext
baseUrl=https://the-internet.herokuapp.com
browser=chromium
headless=false
debugMode=true
```

---

## Running the Tests
### Running from the Terminal
You can run all the tests using Maven:
```mvn test```
To run a specific test suite defined in testng.xml:
```mvn test -DsuiteXmlFile=src/test/resources/testng.xml```

---

## Next Steps
* [ ] Add more test cases to cover all subpages of The Internet. 🧪
* [ ] Implement custom reporting (e.g., with Extent Reports). 📂
* [x] Enable parallel executions using TestNG and Maven. 🎉
* [ ] Document class APIs using Javadoc. 📎

---

## Contributing ✨
If you'd like to contribute to the project, please fork the repository and submit a pull request with your improvements or suggestions.

---

## Author
Project developed by _Federico Sánchez Vidarte_
