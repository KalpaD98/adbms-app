# adbms-app

# Introduction to the system architecture
We implemented our system as a web-based solution. Used Model-View-Controller (MVC) as the architectural pattern of the system. MVC is one of the frequently used architectural patterns in web development. The system is developed using Spring MVC which is a Java framework. Using MVC, group members can scale the system individually.

#Model
Model responsible for all business logic of the system. Model is directly accessing the MongoDB database and handles all CRUD requests with the database.

#View
The view is the only component that directly interacts with the user. Implemented view with Thymeleaf template engine with the help of tomcat server and spring-boot. Tomcat serves server-side rendered static HTML pages for the client.

#Controller
Controller component act as the interface between view and model components. HTTP requests/responses mapping, rendering required pages and queries from the view managed by the controller component.
