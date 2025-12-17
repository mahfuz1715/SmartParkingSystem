Lab Task 1: Solid Principle

1. Single Responsibility Principle:

In "LoginController" code file, Single Responsibility Principle violated because the class
has multiple responsibility of login and registration. So we differentiate into two classes
"LoginController" and "RegistrationController".

2. Open Closed Principle:

In "UserDao" class OCP violated because it violates the "Open for extentions but closed for
modifications".
If we want to add admin panel or try to change anything in admin side the code violated OCP
becaues code will be modified. 
So we differentiate "UserDao" and "AdminUserDao" where AdminUserDao extends
UserDao.

3. Liskov’s Substitute Principle:

In any of the our code files LSP does not violated. As an example:
In "Vehicle" class, subclasses (e.g.  Car, Truck) can safely extend it
without breaking the behavior. So LSP not voilated in this file.

4. Interface Segregation Principle:

ISP states that no client should be forced to depend on methods it does not use.
In our "PaymentService" class we can check if clients to depend on irrelevant methods?
ISP is not violated in this case. The PaymentService class provides methods that are
directly relevant.

5. Dependency Inversion Principle:

In the "PaymentDaon" class DIP violated because low level module depends on high level
modules in the class. So we created an interface to solve the Dependency Inversion Principle.

Lab Task 2: Code smells:

1. Dead Code:

In AdminDashboard.java code file, handlePark(), handleRelease(), viewUsers() has no work.
So this is dead code and we remove those.
In LoginFrame.java code file, import java.io.File; this import & private float imageOpacity 
= 0.5f; this code has no work, so this is also dead code & We remove those.
In ParkingDashboard.java code file, import java.awt.event.ActionEvent;
this import has no works and we remove it.

2. Oddball Solution:

In VehicleDao.java, The class uses two different ways to store/retrieve data: a SQL Database 
(JDBC) and an ArrayList (in-memory). This is an "Oddball Solution" because the same problem 
(data persistence) is solved in two different ways within one class.
We Removed the inconsistent in-memory ArrayList and standardized the class to use the 
database.

3. Feature Envy:

PaymentService is calculating the charge by heavily accessing the data of the Ticket object 
(times, duration). It seems more interested in the Ticket class's data than its own.
We Moved the calculation logic from the Service to the Model where the data resides.

4. Feature Envy:

The line this.getContentPane().getComponent(0)... is a "Train Wreck." It reaches deep into 
the internal structure of the parent's UI components.
Instead of digging through the parent’s internal component hierarchy, we use a controlled 
method to configure the UI. This follows the Law of Demeter.

5. Duplicated Code:

The logic for defining table columns and UI setup is repeated. In LoginFrame, the styling of 
usernameField and passwordField (borders, fonts, padding) is identical but written twice.
The applyFieldStyle method is now called for both the username and password fields.
We Used Lambda expressions e -> handleLogin() for cleaner code.
We Ensured setupUI and setupButtons handle all the logic previously inside the constructor.

6. Black Sheep:

The createVehicle(Vehicle vehicle) method in the original VehicleDao was a "Black Sheep" 
because it did not adhere to the standard architecture of the project's data access layer. 
Every other DAO (e.g., UserDao, ParkingDao) used DBConnection and SQL statements for 
persistence, but this specific method deviated entirely by managing data using a static, 
in-memory ArrayList.
Fix: The createVehicle method was rewritten to use the DBConnection.getConnection() and 
standard SQL INSERT statements, making its behavior consistent with all other DAO classes in 
the project.

7. Switch Statements:

We have no switch statements in our code, so no code violation here.

8. Inappropriate Naming:

We think we don't have any inappropriate naming code smells in our project.
We think we have named our methods, files correctly.

9. Comments:

We don't have any unnecessary comments in any of our project files.

10. Alternative Class with Different Inteface:

We also think we don't have this type of code smells in our project.