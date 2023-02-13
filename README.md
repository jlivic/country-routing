# country-routing

## Environment setup

Build the project with Maven

  Start from any tool (preferrably IntelliJ) with default mirror  
  * mvn clean install

  
Test:
  Open terminal and paste:
  
  * curl http://localhost:9091/routing/CZE/FRA   - to get 200
  * curl http://localhost:9091/routing/CZE/CRO   - to get 400
  
  
Checkout unit tests.
