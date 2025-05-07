import java.sql.*;

public class DatabaseConnector {
    private final String URL = "jdbc:mysql://localhost:3306/db_mahasiswa";
    private final String USER = "root";
    private final String PASSWORD = "";
    private Connection conn;

    public DatabaseConnector() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // tangani ClassNotFoundException juga
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Koneksi berhasil ke database.");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
    }
    
    public void tambahMahasiswa(Mahasiswa mhs) {
        try {
            String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mhs.getNama());
            ps.setString(2, mhs.getNim());
            ps.setString(3, mhs.getJurusan());
            ps.executeUpdate();
            System.out.println("Data berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println("Gagal menambah data: " + e.getMessage());
        }
    }

    public void tampilkanMahasiswa() {
        try {
            String sql = "SELECT * FROM mahasiswa";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        " | Nama: " + rs.getString("nama") +
                        " | NIM: " + rs.getString("nim") +
                        " | Jurusan: " + rs.getString("jurusan"));
            }
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
        }
    }

    public void updateMahasiswa(int id, String nama, String nim, String jurusan) {
        try {
            String sql = "UPDATE mahasiswa SET nama=?, nim=?, jurusan=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nama);
            ps.setString(2, nim);
            ps.setString(3, jurusan);
            ps.setInt(4, id);
            ps.executeUpdate();
            System.out.println("Data berhasil diupdate.");
        } catch (SQLException e) {
            System.out.println("Gagal mengupdate data: " + e.getMessage());
        }
    }

    public void hapusMahasiswa(int id) {
        try {
            String sql = "DELETE FROM mahasiswa WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Data berhasil dihapus.");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus data: " + e.getMessage());
        }
    }
}
