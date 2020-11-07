package ATMEmulator;
import com.mysql.jdbc.Driver;
import java.io.*;
import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author rahul
 */
public class atm {
    public static void main(String args[])throws Exception
    {
       Scanner sc=new Scanner(System.in);
       BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
       try
       {
       Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM","rahul@localhost","1234");
       System.out.println("connection established successfully");
       Statement stmt=conn.createStatement();
       System.out.println("choose from the two options :");
       /* admin is a person or authority who can perform the following operations 
       as:=
       1- they can create users for the atm machine
       2) they can delete or barred the users from taking out the money
       3) they can update the details of the users
       4) they can add the notes to the atm machine machine which can be 1000,500,100 or 50 rupees notes
       5) they can add the money to the account of the user
         next as a user of the atm machine person can perform two operations and is allowed to use 
          the services of the atm only when he/she is authenticated the operations a person can perform as a user are:
       1) person can withdraw a cash 
       2) person can check his balance 
       
      */
       System.out.println("1. to enter as admin");
       System.out.println("2. to enter as user");
       int input=sc.nextInt();
       if(input==1)
       {
         System.out.println("enter the admin-id :");
         String admin=br.readLine();
         System.out.println("enter the password :");
         String pass=br.readLine();
         Statement stmt1=conn.createStatement();
         String query="select * from admin";
         ResultSet rs=stmt1.executeQuery(query);
         int flag=0;
         while(rs.next())
         {
            String ad=rs.getString("adminid");
            String pa=rs.getString("password");
            //System.out.println("adminid "+ad+"with password"+pa);
            if(ad.equals(admin)&&pa.equals(pass))
            {
                flag=1;
            }
         }
         if(flag==1)
         {
           int ch;
           System.out.println("hello admin");
         do
         {
         System.out.println("choose from the following operations to perform :");
         System.out.println("1. to add the user to the atm machine");
         System.out.println("2. to delete the user from atm machine");
         System.out.println("3. to add the money to user's account");
         System.out.println("4. to update the details of a user ");
         System.out.println("5. to add the money to the atm machine");
         int choice=sc.nextInt();
         switch(choice)
          {
             case 1:
                  System.out.println("enter the account-number,pin,username and balance to be added");
                  String accno=br.readLine();
                  int pinno=sc.nextInt();
                  String username=br.readLine();
                  double balance=sc.nextDouble();
                  String query1="insert into user values('"+accno+"','"+pinno+"','"+username+"','"+balance+"') ";
                  stmt1.executeUpdate(query1);
                  System.out.println("user added to the machine successfully");
                  break;
                  
             case 2:
                  System.out.println("enter the account number of user to delete ");
                  accno=br.readLine();
                  String query2="select acctno from user";
                  rs=stmt.executeQuery(query2);
                  flag=0;
                  while(rs.next())
                  {
                      String acc=rs.getString("acctno");
                      if(acc.equals(accno))
                      {
                          flag=1;
                      }
                  }
                  if(flag==1)
                  {
                      String query3="delete from user where acctno='"+accno+"'";
                      stmt.executeUpdate(query3);
                      System.out.println("the user is deleted successfully");
                      
                  }
                  else
                  {
                    System.out.println("no such user with that account-number exists ");
                    
                  }
                  break;
             
             case 3:
                  //to add the money to user's account
                 System.out.println("enter the account number of the user");
                 String acc=br.readLine();
                 String query4="select bal from user where acctno='"+acc+"'";
                 rs=stmt.executeQuery(query4);
                 balance=0;
                 while(rs.next())
                 {
                    balance=rs.getDouble("bal");
                 }
                 if(balance!=0)
                 {
                   System.out.println("enter the amount to be added");
                   Double amount=sc.nextDouble();
                   balance+=amount;
                   String query5="update user set bal='"+balance+"'where acctno='"+acc+"'";
                   stmt.executeUpdate(query5);
                   System.out.println("the amount updated successfully");
                 }
                 else
                 {
                   System.out.println("no account number exists of this type");
                 }
                 break;
             
             case 4:
                 //update the details of the user
                 System.out.println("enter the account number of the user");
                 acc=br.readLine();
                 String query6="Select * from user where acctno='"+acc+"'";
                 rs=stmt.executeQuery(query6);
                 int x=0;
                 while(rs.next())
                 {
                   x=1;
                 }
                 if(x==1)
                 {
                    System.out.println("enter the new name of the user");
                    String name=br.readLine();
                    String query7="update user set uname='"+name+"'where acctno='"+acc+"'";
                    stmt.executeUpdate(query7);
                    System.out.println("user account updated successfully");
                 }
                 else
                 {
                    System.out.println("no user with that account number exits");
                    
                 }
                 break;
              
             case 5:
                 //add the money to the atm machine
                 int tnote=0,fhnote=0,hnote=0,fnote=0;
                 System.out.println("enter the thousand notes to be added ");
                 int th=sc.nextInt();
                 System.out.println("enter the five-hundred notes to be added ");
                 int fh=sc.nextInt();
                 System.out.println("enter the hundred notes to be added");
                 int h=sc.nextInt();
                 System.out.println("enter the fifty notes to be added");
                 int f=sc.nextInt();
                 String query8="select * from money";
                 rs=stmt.executeQuery(query8);
                 while(rs.next())
                 {
                  tnote=rs.getInt("thousand");
                  fhnote=rs.getInt("fivehundred");
                  hnote=rs.getInt("hundred");
                  fnote=rs.getInt("fifty");
                  System.out.println("s");
                 }
                 tnote+=th;
                 fhnote+=fh;
                 hnote+=h;
                 fnote+=f;
                 String query9="update money set thousand='"+tnote+"',fivehundred='"+fhnote+"',hundred='"+hnote+"',fifty='"+fnote+"'";
                 stmt.executeUpdate(query9);
                 break;
              
             default:
                 System.out.println("invalid choice");
                 
          }
         System.out.println("enter 1 for continue or 2 for exit from program");
         ch=sc.nextInt();
         }while(ch==1);
         }
         else
         {
           System.out.println("admin authentication failed");
         }
       }
       else if(input==2)
       {
         //user will be authenticated and then will be allowed to perform various operations on machine like cash withdrawl etc
          System.out.println("enter the account number of the user");
          String acc=br.readLine();
          System.out.println("enter the pin of the user");
          int pinno=sc.nextInt();
          String query10="select * from user";
          ResultSet rs=stmt.executeQuery(query10);
          int flag=0;
          while(rs.next())
          {
            String a=rs.getString("acctno");
            int p=rs.getInt("pin");
            if(acc.equals(a)&&pinno==p)
            {
              System.out.println("user authentication successfull");
              flag=1;
            }
          }
          if(flag==1)
          {
              int ch;
              do
              {
             System.out.println("choose from various options :");
             System.out.println("1. cash withdrawal facility");
             System.out.println("2. balance check facility");
             int choice=sc.nextInt();
             if(choice==1)
             {
               System.out.println("enter the amount of cash to withdraw");
               int amt=sc.nextInt();
               //get the balance of the  user who has been authenticated to withdraw money
               String str="select bal from user where acctno='"+acc+"'";
               rs=stmt.executeQuery(str);
               int baltotal=0;
               while(rs.next())
               {
                  baltotal=rs.getInt("bal");
               }
               //now find out the number of 1000,500,100 and 50 rupees notes in the bank 
               int thcount=0,fhcount=0,hcount=0,fcount=0;
               str="select * from money";
               rs=stmt.executeQuery(str);
               while(rs.next())
               {
                 thcount=rs.getInt("thousand");
                 fhcount=rs.getInt("fivehundred");
                 hcount=rs.getInt("hundred");
                 fcount=rs.getInt("fifty");
               }
               //calculate the total amount to check the balance in the atm machine
               int totalamt=thcount*1000+fhcount*500+hcount*100+fcount*50;
               
               //look for various akward situations arises when one withdraws from atm
               
               if(totalamt==0)
               {
                   System.out.println("atm balance is nill");
               }
               else if(baltotal<0||baltotal<amt)
               {
                  System.out.println("not sufficient balance to withdraw");
               }
               else if(amt%50!=0)
               {
                  System.out.println("please enter the amount in multiples of 50,100,500,1000");
               }
               else if(fcount==0&&amt%100==50)
               {
                  System.out.println("there is no 50 rupees notes so enter the amount in multiples of 100,500,1000");
               }
               else if(hcount==0&&fcount==0&&(amt%500==50||amt%500==100))
               {
                  System.out.println("there is no 50 and 100 rs notes so enter the amount in multiples of 500 and 1000");
               }
               else if(hcount==0&&fcount==0&&fhcount==0&&(amt%1000==50||amt%1000==100||amt%1000==500))
               {
                  System.out.println("there is no 50,100 and 500 notes so enter the multiples of 1000");
               }
               else
               {
               //like this so many possibilities can be there above are the some possibilties
               //now calculate the number of 50,100,500 and 1000 notes which can make up the total
                int thnotes=0,thleft=0,fhnotes=0,fhleft=0,hnotes=0,hleft=0,fnotes=0,fleft=0;  
                
                //first distribute 1000 rupees notes tomake up the amount
                if(thcount>=(amt/1000))
                {
                   //simple case when amt=2300 and thcount=3 then thnotes=2 and thleft=300
                   thnotes=amt/1000;
                   thleft=amt%1000;  
                }
                else if(thcount>0)
                {
                  //simple case when amt=4600 and thcount=2 then thnotes=2 and thleft=1600
                   thnotes=thcount;
                   thleft=amt%(thcount*1000);
                }
                else 
                {
                  //simple case when amt=1200 and thcount=0 then thnotes=0 and thleft=1200
                   thnotes=0;
                   thleft=amt;
                }
                
                //to distribute the 500 rs notes to make up the amount
                if(fhcount>=(thleft/500))
                {
                   //simple case when thleft=700 and fhcount=1 then fhnotes=1 and fhleft=200
                   fhnotes=thleft/500;
                   fhleft=thleft%500;  
                }
                else if(fhcount>0)
                {
                  //simple case when thleft=1600 and fhcount=2 then fhnotes=2 and fhleft=600
                   fhnotes=fhcount;
                   fhleft=thleft%(fhcount*500);
                }
                else 
                {
                  //simple case when thleft=1200 and fhcount=0 then fhnotes=0 and fhleft=1200
                   fhnotes=0;
                   fhleft=thleft;
                }
                
                //to distribute the 100 rs notes to make up the amount
                if(hcount>=(fhleft/100))
                {
                   //simple case when fhleft=750 and hcount=8 then hnotes=7 and hleft=50
                   hnotes=fhleft/100;
                   hleft=fhleft%100;  
                }
                else if(hcount>0)
                {
                  //simple case when fhleft=600 and hcount=2 then hnotes=2 and hleft=400
                   hnotes=hcount;
                   hleft=fhleft%(hcount*100);
                }
                else 
                {
                  //simple case when fhleft=1200 and hcount=0 then  hnotes=0 and hleft=1200
                   hnotes=0;
                   hleft=fhleft;
                }
                
                //to distribute the 500 rs notes to make up the amount
                if(fcount>=(hleft/50))
                {
                   
                   fnotes=hleft/50;
                   fleft=hleft%50;  
                }
                else if(fcount>0)
                {
                 
                   fnotes=fcount;
                   fleft=hleft%(fcount*50);
                }
                else 
                {
                 
                   fnotes=0;
                   fleft=hleft;
                }
                
                System.out.println("cash is withdraw");
                System.out.println("the amount of cash withdrawn :"+amt);
                System.out.println("do you want the receipt  enter 1 for yes or 2 for no");
                int x=sc.nextInt();
                if(x==1)
                {
                  if(thnotes!=0)
                  {
                     System.out.println("number of thousand notes withdrawn is "+thnotes+" with thousand amount as :"+thnotes*1000);
                  }
                  if(fhnotes!=0)
                  {
                     System.out.println("number of fivehunded notes withdrawn is "+fhnotes+" with fivehundered amount as :"+fhnotes*500);
                  }
                  if(hnotes!=0)
                  {
                     System.out.println("number of hundred notes withdrawn is "+hnotes+" with hundred amount as :"+hnotes*100);
                  }
                  if(fnotes!=0)
                  {
                     System.out.println("number of fifty notes withdrawn is "+fnotes+" with fifty amount as :"+fnotes*50);
                  }
                  System.out.println("Therefore the total amount withdrawn is :"+amt);
                  System.out.println("Thanks for using the atm service");
                }
                else if(x==2)
                {
                 System.out.println("Thanks for using the atm service");
                }
                thcount=thcount-thnotes;
                fhcount=fhcount-fhnotes;
                hcount=hcount-hnotes;
                fcount=fcount-fnotes;
                String str1="update money set thousand='"+thcount+"',fivehundred='"+fhcount+"',hundred='"+hcount+"',fifty='"+fcount+"'";
                stmt.executeUpdate(str1);
                baltotal=baltotal-amt;
                str1="update user set bal='"+baltotal+"'where acctno='"+acc+"'";
                stmt.executeUpdate(str1);
                
               }
             }
             else if(choice==2)
             {
               //to show the balance to the user
                 String str2="select bal from user where acctno='"+acc+"'";
                 rs=stmt.executeQuery(str2);
                 Double baltotal=0.0;
                 while(rs.next())
                 {
                    baltotal=rs.getDouble("bal");
                 }
                 System.out.println("the total current balance is :"+baltotal);
             }
             else
             {
               System.out.println("invalid choice");
             }
           System.out.println("enter 1 for continue balance check and cash withdrawal and 0 for exit");
           ch=sc.nextInt();
          }while(ch==1);
          }
          else
          {
             System.out.println("user authentication failed enter correct account and pin number");
          }    
       }
       else
       {
         System.out.println("input is invalid");
       }
    }
    catch(Exception e)
    {
      System.out.println("an exception is caught "+e);
    }
  }
}
