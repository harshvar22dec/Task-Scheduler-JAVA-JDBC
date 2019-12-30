package p1;
//  Created by:- Harshvardhan Singh(harshvardhan.singh22dec@gmail.com) during Sem-III(26 Dec. 2019)
// Created with:- Intellij Idea Professional,using mysql database localhost,project built on linux,using xampp.
//Database Overview:- DBName="Schedule of Sem-IV",TableName="Datewise",Fields="date,month,year,day,work,pendingtod"
import java.sql.*;
import java.time.LocalDateTime;//using to automatic insertion of current date,month,year,day
import java.util.Scanner;
public class Test{
    static void updatePend(Scanner sc) throws SQLException,ClassNotFoundException {//this method is to update your pending tasks
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Schedule of the Sem-IV","root","");
        System.out.print("Enter date of the pending work being updated:");
        String dt=sc.next();
        Statement st=con.createStatement();
        System.out.print("Enter something to be replaced: ");
        st.executeUpdate("update Datewise set pendingtod='"+sc.next()+"' where date="+dt);
        con.close();
        System.out.println("Successfully Done!");
    }
    static void showPend() throws SQLException,ClassNotFoundException {//this method is to show your pending tasks
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Schedule of the Sem-IV", "root", "");
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select * from Datewise");
        while (rs.next()){
            if (!rs.getString(6).startsWith("%")) {
                System.out.println("#[\nDate: " + rs.getInt(1) + "." + rs.getInt(2)
                        + "." + rs.getInt(3) + "\tDay: " + rs.getString(4)
                        + "\nWorks:-\t" + rs.getString(5) + "\nPending= "+rs.getString(6)+"\n]\n");
            }
            }
        con.close();
        System.out.println("Operation Successfully Done!");
    }
    static void insert(Scanner sc) throws SQLException,ClassNotFoundException {//inserts a new record
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Schedule of the Sem-IV","root","");
        PreparedStatement st=con.prepareStatement("insert into Datewise values(?,?,?,?,?,?)");
        st.setInt(1,LocalDateTime.now().getDayOfMonth());//returns current date of system
        st.setInt(2,LocalDateTime.now().getMonthValue());//returns current month of system
        st.setInt(3,LocalDateTime.now().getYear());//returns current year of system
        st.setString(4,LocalDateTime.now().getDayOfWeek().toString());//returns current week day of system
        System.out.print("Enter Tasks/work of today ,do not press space bar: ");
        st.setString(5,sc.next());
        st.setString(6,"%");//for pending, by default %
        st.executeUpdate();
        con.close();
        System.out.println("Successfully Done!");
    }
    static void delete(Scanner sc) throws SQLException,ClassNotFoundException {//deletes a/many specific records
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Schedule of the Sem-IV","root","");
        PreparedStatement st=null;
        System.out.print("press t to delete by date,m to delete by month,y to delete by year");
        String s=sc.next().toLowerCase();
        if(s.equals("t")) {
            System.out.print("Enter date to delete");
            s="delete from Datewise where date="+Integer.toString(sc.nextInt());
            st = con.prepareStatement(s);
            st.executeUpdate();
        }
        else if(s.equals("m"))
        {
            System.out.print("Enter month to delete");
            s="delete from Datewise where month="+Integer.toString(sc.nextInt());
            st=con.prepareStatement(s);
            st.executeUpdate();
        }
        else if(s.equals("y"))
        {
            System.out.print("Enter year to delete");
            s="delete from Datewise where year="+Integer.toString(sc.nextInt());
            st=con.prepareStatement(s);
            st.executeUpdate();
        }
        con.close();
        System.out.println("Successfully Done!");
    }
    static void deleteall(Scanner sc) throws SQLException,ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Schedule of the Sem-IV","root","");
        Statement st=con.createStatement();
        System.out.print("Are you sure to deleteAll, enter n to cancel,y to yes:");
        String res=sc.next();
        if (res.equals("y"))
            st.executeUpdate("delete from Datewise where day!=''");
        else if (res.equals("n"))
            System.out.println("Canceled");
        con.close();
        System.out.println("Operation Successfully Done!");
    }
    static void view()throws SQLException,ClassNotFoundException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Schedule of the Sem-IV","root","");
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select * from Datewise");
        while (rs.next()) {
            System.out.println("#[\nDate: " + rs.getInt(1) + "." + rs.getInt(2)
                    + "." + rs.getInt(3) + "\tDay: " + rs.getString(4)
                    + "\nWorks:-\t" + rs.getString(5) + "\n]\n");
        }
        con.close();
        System.out.println("Operation Successfully Done!");
    }
    public static void main(String args[]) throws SQLException,ClassNotFoundException {//main method/driver method
    Scanner sc=new Scanner(System.in);
    while (true) {
        System.out.print("Enter i to insert,v to view,u to update pendings,s to view pendings,d to delete,a to deleteall: ");
        String s = sc.next().toLowerCase();
        if (s.equals("i"))
            insert(sc);
        else if (s.equals("v"))
            view();
        else if (s.equals("d"))
            delete(sc);
        else if (s.equals("a"))
            deleteall(sc);
        else if (s.equals("u"))
            updatePend(sc);
        else if (s.equals("s"))
            showPend();
        else
        {
            System.out.print("Wrong entry!");
            sc.close();
            break;
        }
    }
    }
}
