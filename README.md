# Project_Ecom_Api_CartSystem
### Flow:
* -> Registration(Registers users through username, password, mobile)
* -> LogIn(Displays "Sign-in successful" if credentials are matched)
* -> Products(Admin will Add products in database for clients, if not present) 
* -> Cart(User will Add or Update products, get user Cart details, and also generates a Bill_Id on checkout)
* -> Bill(Gets total price and Bill_Id)
* -> Payment(upon entering Bill_Id and UserId Shows "payment success" or "failure")
* -> Order(gets user order details) //under process(Non-Functional)

### Functional APIs
* Register User- http://localhost:8080/api/users  //POST
* Register User- http://localhost:8080/api/users/{id}  //GET
* LogIn - http://localhost:8080/api/users/signin  //POST
* Product - http://localhost:8080/api/products/{id}  //GET, PUT, DELETE
* Cart - http://localhost:8080/api/carts/user={userId}/add  //POST
* Cart - http://localhost:8080/api/carts/user={userId}  //GET
* Cart - http://localhost:8080/api/carts/user={userId}/update //PUT
* Bill - http://localhost:8080/api/bills/totalPrice/user={userId}  //GET
* Bill - http://localhost:8080/api/bills/process  //POST
* Bill - http://localhost:8080/api/bills/BillId/user={userId}  //GET

### Non-Functional APIs
* Order -  http://localhost:8080/api/orders
