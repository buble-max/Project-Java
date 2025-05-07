public class Mahasiswa extends Person {
    private String nim;
    private String jurusan;

    public Mahasiswa(String nama, String nim, String jurusan) {
        super(nama);
        this.nim = nim;
        this.jurusan = jurusan;
    }

    public Mahasiswa(String nama, String nim) {
        super(nama);
        this.nim = nim;
        this.jurusan = "TIK";
    }

    // Override method getNama() dari kelas Person untuk memberikan implementasi khusus
    @Override
    public String getNama() {
        // Mengembalikan nama dengan format "Mahasiswa: [nama]"
        return "" + super.getNama();
    }

    // Getter untuk atribut nim
    public String getNim() {
        return nim;
    }

    // Setter untuk atribut nim
    public void setNim(String nim) {
        this.nim = nim;
    }

    // Getter untuk atribut jurusan
    public String getJurusan() {
        return this.jurusan;
    }

    // Setter untuk atribut jurusan
    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }
}
