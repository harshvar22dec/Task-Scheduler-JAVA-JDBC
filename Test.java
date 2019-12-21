package p1;
//  Created by:- Harshvardhan Singh(harshvardhan.singh22dec@gmail.com)
// Created with:- Intellij Idea Professional,using mysql database localhost,project built on linux,using xampp.
import java.sql.*;
import java.util.Scanner;
public class Test{
    static void insert(Scanner sc) throws SQLException,ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Schedule of the Sem-IV","root","");
        PreparedStatement st=con.prepareStatement("insert into Datewise values(?,?,?,?,?)");
        System.out.print("Enter Date: ");
        st.setInt(1,sc.nextInt());
        System.out.print("Enter Month: ");
        st.setInt(2,sc.nextInt());
        System.out.print("Enter Year: ");
        st.setInt(3,sc.nextInt());
        System.out.print("Enter Day: ");
        st.setString(4,sc.next());
        System.out.print("Enter Tasks/work ,do not press space bar: ");
        st.setString(5,sc.next());
        st.executeUpdate();
        con.close();
        System.out.println("Successfully Done!");
    }
    static void delete(Scanner sc) throws SQLException,ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Schedule of the Sem-IV","root","");
        PreparedStatement st=null;
        System.out.print("press t to delete by date,m to delete by month,y to delete by year");
        String s=sc.next().toLowerCase();
        if(s.equals("t")) {
            System.out.print("Enter date to delete");
            s="delete from Datewise where date="+Integer.toString(sc.nextInt());
            st = con.prepareStatement(s);
        }
        else if(s.equals("m"))
        {
            System.out.print("Enter month to delete");
            s="delete from Datewise where month="+Integer.toString(sc.nextInt());
            st=con.prepareStatement(s);
        }
        else if(s.equals("y"))
        {
            System.out.print("Enter year to delete");
            s="delete from Datewise where year="+Integer.toString(sc.nextInt());
            st=con.prepareStatement(s);
        }
        st.executeUpdate();
        con.close();
        System.out.println("Successfully Done!");
    }
    static void deleteall() throws SQLException,ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Schedule of the Sem-IV","root","");
        Statement st=con.createStatement();
        st.executeUpdate("delete from Datewise where day!=''");
        con.close();
        System.out.println("Successfully Done!");
    }
    static void view()throws SQLException,ClassNotFoundException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Schedule of the Sem-IV","root","");
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select * from Datewise");
        while (rs.next())
            System.out.println("#[\nDate: "+rs.getInt(1)+"."+rs.getInt(2)+"."+rs.getInt(3)+"\tDay: "+rs.getString(4)+"\n"+rs.getString(5)+"\n]\n");
        con.close();
        System.out.println("Operation Successfully Done!");
    }
    public static void main(String args[]) throws SQLException,ClassNotFoundException {
    Scanner sc=new Scanner(System.in);
    while (true) {
        System.out.print("Enter i to insert,v to view,d to delete,a to deleteall: ");
        String s = sc.next().toLowerCase();
        if (s.equals("i"))
            insert(sc);
        else if (s.equals("v"))
            view();
        else if (s.equals("d"))
            delete(sc);
        else if (s.equals("a"))
            deleteall();
        else
        {
            System.out.print("Wrong entry!");
            sc.close();
            break;
        }
    }
    }
}
