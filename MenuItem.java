//abstraction
public abstract class MenuItem {
    private String nama;
    private double harga;
    private String kategori;
    //constructor
    public MenuItem(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }
    //getter
    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public String getKategori() {
        return kategori;
    }
    //abstraction
    public abstract void tampilMenu();
}