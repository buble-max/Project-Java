import java.util.Scanner; 

public class MainApp {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) { // Membuat Scanner untuk membaca input dari keyboard (dengan try-with-resources)
            DatabaseConnector db = new DatabaseConnector(); // Membuat objek DatabaseConnector untuk mengakses database

            while (true) { 
                System.out.println("\nMenu:"); 
                System.out.println("1. Tambah Mahasiswa"); 
                System.out.println("2. Tampilkan Mahasiswa"); 
                System.out.println("3. Update Mahasiswa"); 
                System.out.println("4. Hapus Mahasiswa"); 
                System.out.println("5. Keluar"); 
                System.out.print("Pilih menu: "); 
                int pilihan = input.nextInt(); 
                input.nextLine(); // Mengonsumsi newline yang tersisa setelah nextInt()

                switch (pilihan) {
                    case 1: 
                        System.out.print("Nama: ");
                        String nama = input.nextLine();
                        System.out.print("NIM: ");
                        String nim = input.nextLine();
                        System.out.print("Jurusan: ");
                        String jurusan = input.nextLine();
                        Mahasiswa mhs = new Mahasiswa(nama, nim, jurusan);
                        db.tambahMahasiswa(mhs); // Panggil method untuk menambah ke database
                        break;
                    case 2:
                        db.tampilkanMahasiswa();
                        break;
                    case 3:
                        System.out.print("ID Mahasiswa: ");
                        int idUpdate = input.nextInt(); input.nextLine();
                        System.out.print("Nama Baru: ");
                        String namaBaru = input.nextLine();
                        System.out.print("NIM Baru: ");
                        String nimBaru = input.nextLine();
                        System.out.print("Jurusan Baru: ");
                        String jurusanBaru = input.nextLine();
                        db.updateMahasiswa(idUpdate, namaBaru, nimBaru, jurusanBaru);
                        break;
                    case 4:
                        System.out.print("ID Mahasiswa yang akan dihapus: ");
                        int idHapus = input.nextInt(); input.nextLine();
                        db.hapusMahasiswa(idHapus);
                        break;
                    case 5:
                        System.out.println("Terima kasih!");
                        return;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
                }
            }
        }
    }

