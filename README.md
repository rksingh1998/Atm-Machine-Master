# ATM-MACHINE
A java program to develop a system which works similar to atm machine.Performing all the operations of atm using data storage as mysql.

In this system there are two people involved:
1) Admin-who is responsible for providing the atm services to others.
2) User-who usually access the services of the atm.

Admin is allowed to perform the following operations which includes:-
1) adding the account number of user to machine
2) deleting the user 
3) updating the details of the user
4) adding the notes of 100,500,1000,50 to the machine
5) adding the amount of money provided by user to his/her account

User can perform the following operations :-
1) user can retrieve his/her money from atm after proper authentication.
2) user can check his/her balance after authentication

Also admin is allowed to acess the machine if he/she is present in the database admin otherwise he/she is not allowed to perform any operation
A database in mysql is maintained names ATM consists of three tables namely :-
1) admin table- to hava the records of all the admins associated to that atm machine.
2) user table - to have the details of all the users associated to that machine having their details as username,account-number,pincode and balance in their account.
3) money table -for holding the details of how many RS 50,RS 100,RS 500,RS 1000 notes are there in machine.

JDBC Driver is used for connections between java program and mysql.

