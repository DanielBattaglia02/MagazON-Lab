/*
Autore: Daniel Battaglia
 */

package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connessione implements AutoCloseable
{
    private Connection con;

    public Connessione()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/Magazon";    //localhost per locale
            this.con = DriverManager.getConnection(url, "root", "basidati23");
            System.out.println("Connessione avvenuta \n");
        }
        catch (Exception var2)
        {
            System.out.println("Connessione fallita");
            var2.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.con;
    }

    public void closeConnection() throws SQLException
    {
        try
        {
            if (this.con != null && !this.con.isClosed())
            {
                this.con.close();
            }
        }
        catch (SQLException var2)
        {
            var2.printStackTrace();
        }
    }

    // Metodo close() per l'implementazione di AutoCloseable
    @Override
    public void close() throws SQLException {
        try {
            if (this.con != null && !this.con.isClosed()) {
                this.con.close();
            }
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }
}

