#Employee Attrition System
## Made using Spring Boot 3 + Spring Security 6 (using JWT)
## Endpoints: 
### /onBoard: onboard Employees and add entry in the database. Authenticated.
### /offBoard: offboard Employees and modify entry in the database. Authenticated.
### /getAttritionRate: gives the attrition percentage to the requesting HR. Authneticated.
### /getEmployeeList: returns the list of all the employees in the database.
### /Employee: returns the details on the particular employee who's empId was sent through the Request Params.
### (Please note: Authenticated endpoints can be accessed by on the HR)

### JWT token valid only for 5 mins.
