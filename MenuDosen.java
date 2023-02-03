package UasProject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class MenuDosen {
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
        System.out.println("========= MENU DOSEN ==========");
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
        String sql = "SELECT * FROM tbl_dosen";

        try {
            rs = state.executeQuery(sql);
            
            System.out.println("+-----------------+");
            System.out.println("|    DATA DOSEN   |");
            System.out.println("+-----------------+");
            System.out.println("");

            while(rs.next()){
                System.out.println("NIP: " + rs.getString("nip_dosen"));
                System.out.println("Nama: " + rs.getString("nama_dosen"));
                System.out.println("Alamat: " + rs.getString("alamat_dosen"));
                System.out.println("-------------------------------------");
                System.out.println("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void insertMahasiswa(){
        System.out.println("+-------------------------+");
        System.out.println("|    INSERT DATA DOSEN    |");
        System.out.println("+-------------------------+");
        System.out.println("");
         try {
            System.out.print("NIP: ");
            String nip = input.readLine().trim();
            System.out.print("Nama: ");
            String nama = input.readLine().trim();
            System.out.print("Alamat: ");
            String alamat = input.readLine().trim();

            String sql = "INSERT INTO tbl_dosen (nip_dosen, nama_dosen, alamat_dosen) VALUE('%s', '%s', '%s')";
            sql = String.format(sql, nip, nama, alamat);
            
            System.out.println("Data telah ditambahkan...");
            System.out.println("");

            state.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void updateMahasiswa(){
        System.out.println("+-----------------------+");
        System.out.println("|    EDIT DATA DOSEN    |");
        System.out.println("+-----------------------+");
        System.out.println("");
          try {
            System.out.print("NIP yang mau diedit: ");
            int nip = Integer.parseInt(input.readLine());
            System.out.print("Nama: ");
            String nama = input.readLine().trim();
            System.out.print("Alamat: ");
            String alamat = input.readLine().trim();

            String sql = "UPDATE tbl_dosen SET nama_dosen='%s', alamat_dosen='%s' WHERE nip_dosen=%d";
            sql = String.format(sql, nama, alamat, nip);
            
            System.out.println("Data telah diubah...");
            System.out.println("");
            
            state.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void deleteMahasiswa(){
        System.out.println("+-------------------------+");
        System.out.println("|    DELETE DATA DOSEN    |");
        System.out.println("+-------------------------+");
        System.out.println("");
        try {
            System.out.print("NIP yang mau dihapus: ");
            int nip = Integer.parseInt(input.readLine());

            String sql = String.format("DELETE FROM tbl_dosen WHERE nip_dosen=%d", nip);

            state.execute(sql);
            
            System.out.println("Data telah terhapus...");
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
