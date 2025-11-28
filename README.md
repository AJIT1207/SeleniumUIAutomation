# OrangeHRM Automation Project

This project automates login and basic workflows for the OrangeHRM demo site using Java, Selenium WebDriver, and Maven.

## Features

- Automated login to OrangeHRM demo site
- Configurable browser and credentials via properties file
- Supports implicit and explicit waits

## Prerequisites

- Java 8 or higher
- Maven 3.x
- IntelliJ IDEA (recommended)
- Firefox browser (default, configurable)

## Setup

1. Clone the repository:
2. Open the project in IntelliJ IDEA.
3. Configure `src/resources/config.properties` as needed:
   - `url`: Application URL
   - `browser`: Browser type (e.g., firefox, chrome)
   - `user` and `pass`: Login credentials
   - `implicitWait` and `explicitWait`: Wait times in seconds

4. Build the project:
5. ## License

This project is for educational/demo purposes.

## Contact

For questions, open an issue on GitHub.
