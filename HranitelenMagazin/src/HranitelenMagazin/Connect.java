package HranitelenMagazin;


import java.sql.*;
import java.util.*;
import java.util.logging.*;



/**
 *
 * @author User
 */
public class Connect {
     public Connection conn;
    public Statement stmt;
    public ResultSet rs;
    
    public Connect(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:HranitelenMagazin.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    }
    
    public ArrayList<String> select(String[] columnsArray, String table){
        ArrayList data = new ArrayList<String>();
        String columns = String.join(", ", columnsArray);
        String sql = "SELECT " + columns + " FROM " + table;

        try{
            System.out.println(sql);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                String row = "";
                for (int i = 0; i < columnsArray.length; i++) {
                    row = row + rs.getString(columnsArray[i])+"---";
                }
                row=row.substring(0, row.length()-3);
                data.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
    
      public ArrayList<String> selectWhere(String[] columnsArray, String table, String whereCol, String whereValue){
        ArrayList data = new ArrayList<String>();   
        String columns = String.join(", ",columnsArray);
        String sql = "SELECT " + columns + " FROM " + table + " WHERE " + whereCol + " LIKE '%" + whereValue + "%'";
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println(sql);
            while(rs.next()){
                String row="";
                for (int i = 0; i < columnsArray.length; i++) {
                    row+=rs.getString(columnsArray[i])+"---";
                }
               data.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return data;
    }
      
       public void update(String table, String[] columnArray, String[] valuesArray, String whereCol, String whereVal) {

        String sql = "UPDATE " + table + " SET ";
        for (int i = 0; i < columnArray.length; i++) {
            sql += columnArray[i] + " = '" + valuesArray[i] + "' ,";
        }
        sql = sql.substring(0, sql.length() - 2);
        sql += " WHERE " + whereCol + " = '" + whereVal + "'";
        System.err.println(sql);
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
      
      
      
       public ArrayList<String> selectWhereAnd(String[] columnsArray, String table, String[] whereCols, String[] whereValues){
        ArrayList data = new ArrayList<String>();
        String columns = String.join(", ",columnsArray);

        String sql = "SELECT " + columns + " FROM " + table + " WHERE ";
        
        for(int i=0; i<whereCols.length; i++){
            sql = sql + whereCols[i] + " LIKE '" + whereValues[i] + "' AND ";
        }
        sql = sql.substring(0,sql.length()-5);
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println(sql);
            while(rs.next()){
                String row="";
                for (int i = 0; i < columnsArray.length; i++) {
                    row+=rs.getString(columnsArray[i])+"---";
                }
                row=row.substring(0, row.length()-3);
               data.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
       
       public ArrayList<String> selectOrderBy(String[] columnArray, String table, String column, String order) {
        ArrayList<String> data = new ArrayList<String>();
        String columns = String.join(", ", columnArray);
        String sql = "SELECT " + columns + " FROM " + table + " ORDER BY " + column + " " + order;
       
        System.out.println(sql);

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String row = "";
                for (int i = 0; i < columnArray.length; i++) {
                    row = row + rs.getString(columnArray[i]) + "---";

                }

                row = row.substring(0, row.length() - 3);
                System.out.println(row);
                data.add(row);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
        
      public ArrayList<String> selectGroupBy(String[] columnsArray, String table){
        ArrayList<String> data = new ArrayList<String>(); 
        String columns = String.join(", ",columnsArray);
        String sql = "SELECT " + columns + " FROM " + table;

        System.out.println(sql);
        
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                String row = "";
                for (int i = 0; i < columnsArray.length; i++) {
                    row = row + rs.getString(columnsArray[i]) + "---";
                    
                }
                row = row.substring(0, row.length()-3);
                data.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
      
      
      public void close(){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Throwable ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<String> selectWherelogin(String[] columnArray, String table, int[] whereCol, String[] values) {
        ArrayList<String> data = new ArrayList<String>();
        String columns = String.join(", ", columnArray);
        String sql = "SELECT " + columns + " FROM " + table + " WHERE ";
        for (int i = 0; i < whereCol.length; i++) {
            sql += columnArray[whereCol[i]] + " LIKE '" + values[i] + "' AND ";

        }
        sql = sql.substring(0, sql.length() - 4);
        System.out.println(sql);
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String row = "";
                for (int i = 0; i < columnArray.length; i++) {
                    row = row + rs.getString(columnArray[i]) + "---";
                }

                row = row.substring(0, row.length() - 3);

                data.add(row);

            }
            System.out.println(data);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
    
     public void delete(String table, String column, String value) {
        String sql = "delete from " + table + " where " + column + " = " + value;
        System.out.println(sql);
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
     
       public void insert(String table, String[] columnsArray, String[] valuesArray) {

        String columns = String.join(", ", columnsArray);
        String values = String.join("', '", valuesArray);
        String sql = "Insert into " + table + " (" + columns + ") values ('" + values + "')";

        System.out.println(sql);
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
       
        public ArrayList<String> selectJoin(String[]columnsArray, String table1,String table2,String wherecol){
        ArrayList data = new ArrayList<String>();   
        String columns = String.join(", ",columnsArray); 
        String sql = "SELECT "+columns+" FROM "+
                table1 + " WHERE "+wherecol +" =( SELECT "+ wherecol + " FROM " + table2;
        
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println(sql);
            while(rs.next()){
                String row="";
                for (int i = 0; i < columnsArray.length; i++) {
                    row+=rs.getString(columnsArray[i])+"---";
                }
                row=row.substring(0, row.length()-3);
               data.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return data;
    }
}
