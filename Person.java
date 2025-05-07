// Class unik dan Utama
public class Person { 
    protected String nama;

    public Person(String nama) {
        this.nama = nama;
    }

    // Yang di override di Mahasiswa
    public String getNama() {
        return this.nama;
    }
}
