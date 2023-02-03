package UasProject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class MenuMataKuliah {
    static final String jdbc = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost/perkuliahan";
    static String username = "root";
    static String password = "";
    
    static Connection conn;
    static Statement state;
    static ResultSet rs;
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);
    
    public static void main(String[] args) {
        try {
            Class.forName(jdbc);

            conn = DriverManager.getConnection(url, username, password);
            state = conn.createStatement();

            while (!conn.isClosed()) {
                showMenu();
            }

            state.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void showMenu() {
        System.out.println("========= MENU MATA KULIAH ==========");
        System.out.println("1. Show Data");
        System.out.println("2. Insert Data");
        System.out.println("3. Edit Data");
        System.out.println("4. Delete Data");
        System.out.println("0. Exit");
        System.out.println("");
        System.out.print("PILIHAN> ");
        System.out.print("");

        try {
            int pilihan = Integer.parseInt(input.readLine());

            switch (pilihan) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    showData();
                    break;
                case 2:
                    insertMahasiswa();
                    break;
                case 3:
                    updateMahasiswa();
                    break;
                case 4:
                    deleteMahasiswa();
                    break;
                default:
                    System.out.println("Pilihan salah!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void showData() {
        String sql = "SELECT * FROM tbl_matkul";

        try {
            rs = state.executeQuery(sql);
            
            System.out.println("+-----------------------+");
            System.out.println("|    DATA MATA KULIAH   |");
            System.out.println("+-----------------------+");
            System.out.println("");

            while(rs.next()){
                System.out.println("Kode_Matkul: " + rs.getString("kode_matkul"));
                System.out.println("Nama_Matkul: " + rs.getString("nama_matkul"));
                System.out.println("SKS: " + rs.getInt("sks"));
                System.out.println("-------------------------------------");
                System.out.println("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void insertMahasiswa(){
        System.out.println("+-------------------------------+");
        System.out.println("|    INSERT DATA MATA KULIAH    |");
        System.out.println("+-------------------------------+");
        System.out.println("");
         try {
            System.out.print("Kode_Matkul: ");
            String kode = input.readLine().trim();
            System.out.print("Nama_Matkul: ");
            String nama = input.readLine().trim();
            System.out.print("SKS: ");
            String sks = input.readLine().trim();

            String sql = "INSERT INTO tbl_matkul (kode_matkul, nama_matkul, sks) VALUE('%s', '%s', '%s')";
            sql = String.format(sql, kode, nama, sks);
            
            System.out.println("Data telah ditambahkan...");
            System.out.println("");

            state.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void updateMahasiswa(){
        System.out.println("+-----------------------------+");
        System.out.println("|    EDIT DATA MATA KULIAH    |");
        System.out.println("+-----------------------------+");
        System.out.println("");
          try {
            System.out.print("Kode_Matkul yang mau diedit: ");
            int kode = Integer.parseInt(input.readLine());
            System.out.print("Nama_Matkul: ");
            String nama = input.readLine().trim();
            System.out.print("SKS: ");
            String sks = input.readLine().trim();

            String sql = "UPDATE tbl_matkul SET nama_matkul='%s', sks='%s' WHERE kode_matkul=%d";
            sql = String.format(sql, nama, sks, kode);
            
            System.out.println("Data telah diubah...");
            System.out.println("");
            
            state.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void deleteMahasiswa(){
        System.out.println("+-------------------------------+");
        System.out.println("|    DELETE DATA MATA KULIAH    |");
        System.out.println("+-------------------------------+");
        System.out.println("");
        try {
            System.out.print("Kode_Matkul yang mau dihapus: ");
            int kode = Integer.parseInt(input.readLine());

            String sql = String.format("DELETE FROM tbl_matkul WHERE kode_matkul=%d", kode);

            state.execute(sql);
            
            System.out.println("Data telah terhapus...");
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
