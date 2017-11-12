# TCS-Nielsen-Hackathon
This project was developed as part of the TCS-Nielsen Hackathon contest organized in 2014. The objective of the contest was to develop a web application using a mock API provided by [Nielsen India](http://www.nielsen.com/in/en.html). The API provided by the Nelsen included raw data for a list of products that can be queried using the product's description, its characteristics, Universal Product Code (UPC) and health information.

# Approach
Our approach includes three distinct views namely:
- *Consumer view:* Provided consumers information about the availability of a particular product in their vicinity. This included retailer information for the desired location. The consumers could also query a product based on its health characteristics.
- *Retailer view:* The retailers can query the products based on sales to determine the future stocks to order. The information provides the retailers an efficient way to manage their inventory.  
- *Manufacturer view:* The manufacturers can use this view to check the performance of a specific product in a geographic location and receive feedback about the product from the consumers as well as retailers.

# Technology
The developed web application was hosted on the Tomcat server running on localhost and included following layers:
1. A front-end developed in Angular JS to query and display the desired product's information. *This part of the code is not included in this repository.*
2. A business logic layer developed in Java to manage the requests and the corresponding response.
