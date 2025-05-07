import java.sql.*; // Mengimpor library untuk bekerja dengan database menggunakan JDBC

public class DatabaseConnector { // Deklarasi kelas utama
    private final String URL = "jdbc:mysql://localhost:3306/db_mahasiswa"; // URL koneksi ke database
    private final String USER = "root"; // Username untuk database
    private final String PASSWORD = ""; // Password untuk database
    private Connection conn; // Objek koneksi ke database

    public DatabaseConnector() { // Konstruktor untuk menginisialisasi koneksi ke database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Memuat driver JDBC MySQL
            conn = DriverManager.getConnection(URL, USER, PASSWORD); // Membuka koneksi ke database
            System.out.println("Koneksi berhasil ke database."); // Pesan jika koneksi berhasil
        } catch (ClassNotFoundException e) { // Menangani jika driver tidak ditemukan
            System.out.println("Driver tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) { // Menangani jika koneksi gagal
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
    }
    
    public void tambahMahasiswa(String nama, String nim) { // Metode untuk menambah mahasiswa dengan nama dan NIM
        Mahasiswa m = new Mahasiswa(nama, nim, "Belum Ditentukan"); // Membuat objek Mahasiswa dengan jurusan default
        tambahMahasiswa(m); // Memanggil metode tambahMahasiswa dengan objek Mahasiswa
    }

    public void tambahMahasiswa(Mahasiswa mhs) { // Metode untuk menambah mahasiswa ke database
        try {
            String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)"; // Query SQL untuk menambah data
            PreparedStatement ps = conn.prepareStatement(sql); // Membuat statement yang aman dari SQL Injection
            ps.setString(1, mhs.getNama()); // Mengisi parameter pertama dengan nama
            ps.setString(2, mhs.getNim()); // Mengisi parameter kedua dengan NIM
            ps.setString(3, mhs.getJurusan()); // Mengisi parameter ketiga dengan jurusan
            ps.executeUpdate(); // Menjalankan query
            System.out.println("Data berhasil ditambahkan."); // Pesan jika data berhasil ditambahkan
        } catch (SQLException e) { // Menangani jika terjadi kesalahan SQL
            System.out.println("Gagal menambah data: " + e.getMessage());
        }
    }
    
    public void tampilkanMahasiswa() { // Metode untuk menampilkan semua data mahasiswa
        try {
            String sql = "SELECT * FROM mahasiswa"; // Query SQL untuk mengambil semua data
            Statement stmt = conn.createStatement(); // Membuat statement untuk menjalankan query
            ResultSet rs = stmt.executeQuery(sql); // Menjalankan query dan mendapatkan hasilnya
            while (rs.next()) { // Iterasi melalui hasil query
                System.out.println("ID: " + rs.getInt("id") + // Menampilkan ID mahasiswa
                        " | Mahasiswa: " + rs.getString("nama") + // Menampilkan nama mahasiswa
                        " | NIM: " + rs.getString("nim") + // Menampilkan NIM mahasiswa
                        " | Jurusan: " + rs.getString("jurusan")); // Menampilkan jurusan mahasiswa
            }
        } catch (SQLException e) { // Menangani jika terjadi kesalahan SQL
            System.out.println("Gagal menampilkan data: " + e.getMessage());
        }
    }

    public void updateMahasiswa(int id, String nama, String nim, String jurusan) { // Metode untuk mengupdate data mahasiswa
        try {
            String sql = "UPDATE mahasiswa SET nama=?, nim=?, jurusan=? WHERE id=?"; // Query SQL untuk update data
            PreparedStatement ps = conn.prepareStatement(sql); // Membuat statement yang aman dari SQL Injection
            ps.setString(1, nama); // Mengisi parameter pertama dengan nama baru
            ps.setString(2, nim); // Mengisi parameter kedua dengan NIM baru
            ps.setString(3, jurusan); // Mengisi parameter ketiga dengan jurusan baru
            ps.setInt(4, id); // Mengisi parameter keempat dengan ID mahasiswa
            ps.executeUpdate(); // Menjalankan query
            System.out.println("Data berhasil diupdate."); // Pesan jika data berhasil diupdate
        } catch (SQLException e) { // Menangani jika terjadi kesalahan SQL
            System.out.println("Gagal mengupdate data: " + e.getMessage());
        }
    }

    public void hapusMahasiswa(int id) { // Metode untuk menghapus data mahasiswa berdasarkan ID
        try {
            String sql = "DELETE FROM mahasiswa WHERE id=?"; // Query SQL untuk menghapus data
            PreparedStatement ps = conn.prepareStatement(sql); // Membuat statement yang aman dari SQL Injection
            ps.setInt(1, id); // Mengisi parameter pertama dengan ID mahasiswa
            ps.executeUpdate(); // Menjalankan query
            System.out.println("Data berhasil dihapus."); // Pesan jika data berhasil dihapus
        } catch (SQLException e) { // Menangani jika terjadi kesalahan SQL
            System.out.println("Gagal menghapus data: " + e.getMessage());
        }
    }
}
