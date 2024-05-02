XNAT Plugin Development

Objective:
Develop a plugin for XNAT to create a new data type and integrate it into the XNAT system using Gradle.

Steps: (skip to Step 5 if edit exsiting plugins)
1.	Setting Up Environment:
•	Install necessary tools: Java Development Kit (JDK), Gradle, XNAT. (Tips: check Compatibility between JDK and Gradle. For example, JDK 15&Gradle 6.7 can work)
•	Ensure XNAT is running and accessible.
2.	Create Gradle Project:
•	Create a new directory for the project.
•	Iinitialise Gradle project using ‘gradle init‘’.
•	Choose appropriate options (e.g., Java application, Groovy language).
•	Navigate to the project directory.
3.	Add XNAT Dependencies:
•	Add XNAT dependencies to build.gradle:
4.	Develop Plugin:
•	Create necessary Java/Groovy classes for the plugin.
•	Create your schema file at /src/main/resources/schemas/schema/schema.xsd , replacing 'schema' with whatever you want to name your schema (for example, /src/main/resources/schemas/NIRS/NIRS.xsd 
•	Configuring XNAT Data Models: edit .java file at src\main\java\org\nrg\xnatx\plugins\data type name\
According to: https://wiki.xnat.org/documentation/xnat-plugin-configurations
•	Customized XNAT screens .vm files at src\main\resources\META-INF\resources\templates\screens
According to: https://wiki.xnat.org/documentation/xnat-data-type-development
5.	Build and Package Plugin:
•	Run ‘./gradlew build’ to compile the plugin.
•	Verify that the build is successful.
•	Package the plugin into a JAR file using Gradle: ‘.\gradlew jar’. Tips: everytime you make any change to schema or java class, use ‘.\gradlew jar’ to generate an updated JAR file. 
•	The JAR file will be located at ./build/libs/
6.	Deploy Plugin to XNAT:
•	Copy the generated JAR file to the XNAT plugins directory.
•	Restart XNAT instance to load the new plugin.
•	Verify that the plugin is successfully loaded in the XNAT admin interface.
7.	Test Plugin:
•	Create test data to test the functionality of the new data type.
•	Verify that the plugin behaves as expected.
•	Debug and fix any issues encountered during testing.



