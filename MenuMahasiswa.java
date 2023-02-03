package UasProject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class MenuMahasiswa {
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
        System.out.println("========= MENU MAHASISWA ==========");
        System.out.println("1. Show Data Mahasiswa");
        System.out.println("2. Insert Data");
        System.out.println("3. Edit Data");
        System.out.println("4. Delete Data");
        System.out.println("5. Pengisian KRS");
        System.out.println("6. Show Data KRS");
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
                case 5:
                    inputKRS();
                    break;
                case 6:
                    showDataKRS();
                    break;
                default:
                    System.out.println("Pilihan salah!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void showData() {
        String sql = "SELECT * FROM tbl_mahasiswa";

        try {
            rs = state.executeQuery(sql);
            
            System.out.println("+----------------------+");
            System.out.println("|    DATA MAHASISWA    |");
            System.out.println("+----------------------+");
            System.out.println("");

            while(rs.next()){
                System.out.println("NIM: " + rs.getString("nim_mahasiswa"));
                System.out.println("Nama: " + rs.getString("nama_mahasiswa"));
                System.out.println("Prodi: " + rs.getString("prodi"));
                System.out.println("Semester: " + rs.getInt("semester"));
                System.out.println("-------------------------------------");
                System.out.println("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void insertMahasiswa(){
        System.out.println("+-----------------------------+");
        System.out.println("|    INSERT DATA MAHASISWA    |");
        System.out.println("+-----------------------------+");
        System.out.println("");
         try {
            System.out.print("NIM: ");
            String nim = input.readLine().trim();
            System.out.print("Nama: ");
            String nama = input.readLine().trim();
            System.out.print("Prodi: ");
            String prodi = input.readLine().trim();
            System.out.print("Semester: ");
            String semester = input.readLine().trim();

            String sql = "INSERT INTO tbl_mahasiswa (nim_mahasiswa, nama_mahasiswa, prodi, semester) VALUE('%s', '%s', '%s', '%s')";
            sql = String.format(sql, nim, nama, prodi, semester);
            
            System.out.println("Data telah ditambahkan...");
            System.out.println("");

            state.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void updateMahasiswa(){
        System.out.println("+---------------------------+");
        System.out.println("|    EDIT DATA MAHASISWA    |");
        System.out.println("+---------------------------+");
        System.out.println("");
          try {
            System.out.print("NIM yang mau diedit: ");
            int nim = Integer.parseInt(input.readLine());
            System.out.print("Nama: ");
            String nama = input.readLine().trim();
            System.out.print("Prodi: ");
            String prodi = input.readLine().trim();
            System.out.print("Semester: ");
            String semester = input.readLine().trim();

            String sql = "UPDATE tbl_mahasiswa SET nama_mahasiswa='%s', prodi='%s', semester='%s' WHERE nim_mahasiswa=%d";
            sql = String.format(sql, nama, prodi, semester, nim);
            
            System.out.println("Data telah diubah...");
            System.out.println("");
            
            state.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void deleteMahasiswa(){
        System.out.println("+-----------------------------+");
        System.out.println("|    DELETE DATA MAHASISWA    |");
        System.out.println("+-----------------------------+");
        System.out.println("");
        try {
            System.out.print("NIM yang mau dihapus: ");
            int nim = Integer.parseInt(input.readLine());

            String sql = String.format("DELETE FROM tbl_mahasiswa WHERE nim_mahasiswa=%d", nim);

            state.execute(sql);
            
            System.out.println("Data telah terhapus...");
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void inputKRS(){
        System.out.println("+-------------------------------+");
        System.out.println("|    PENGISIAN KRS MAHASISWA    |");
        System.out.println("+-------------------------------+");
        System.out.println("");
        try{
            System.out.print("ID KRS: ");
            String id = input.readLine().trim();
            System.out.print("NIM Mahasiswa: ");
            String nim = input.readLine().trim();
            System.out.print("Kode Mata Kuliah: ");
            String kode = input.readLine().trim();
            System.out.print("SKS: ");
            String sks = input.readLine().trim();
            System.out.print("Nama Dosen: ");
            String nama = input.readLine().trim();
            
            String sql = "INSERT INTO tbl_krs (id_krs, nim_mahasiswa, kode_matkul, sks, nama_dosen) VALUE('%s', '%s', '%s', '%s', '%s')";
            sql = String.format(sql, id, nim, kode, sks, nama);
            
            System.out.println("Pengisian KRS berhasil...");
            System.out.println("");
            
            state.execute(sql);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    static void showDataKRS(){
        String sql = "SELECT * FROM tbl_krs";

        try {
            rs = state.executeQuery(sql);
            
            System.out.println("+--------------------------+");
            System.out.println("|    DATA KRS MAHASISWA    |");
            System.out.println("+--------------------------+");
            System.out.println("");

            while(rs.next()){
                System.out.println("Id KRS: " + rs.getString("id_krs"));
                System.out.println("NIM: " + rs.getString("nim_mahasiswa"));
                System.out.println("Kode Mata Kuliah: " + rs.getString("kode_matkul"));
                System.out.println("SKS: " + rs.getInt("sks"));
                System.out.println("Nama Dosen: " + rs.getString("nama_dosen"));
                System.out.println("-------------------------------------");
                System.out.println("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
