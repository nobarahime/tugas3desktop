import java.io.*;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static Menu menu = new Menu();
    static Pesanan pesanan = new Pesanan();

    public static void main(String[] args) {
        loadMenu();
        int pilih;
        do {
            System.out.println ("Restoran Bintang Lima Mas Aldi");
            System.out.println ("1. Tambah Menu");
            System.out.println ("2. Tampilkan Menu");
            System.out.println ("3. Pesan");
            System.out.println ("4. Hitung Total");
            System.out.println ("5. Tampilkan Struk");
            System.out.println ("6. Simpan Menu");
            System.out.println ("7. Keluar");
            System.out.print ("Pilih :");
            pilih = input.nextInt();
            input.nextLine();

            switch (pilih) {
                case 1:
                    tambahMenu();
                    break;
                case 2:
                    menu.tampilSemuaMenu();
                    break;
                case 3:
                    pesanMenu();
                    break;
                case 4:
                    System.out.println("Total + Rp " + pesanan.hitungTotal());
                    break;
                case 5:
                    pesanan.tampilStruk();
                    simpanStruk();
                    break;
                case 6:
                    simpanMenu();
                    break;
                case 7:
                    System.out.println("Program selesai");
                    break;
                default:
                    System.out.println("Pilihan salah!");
            }
        }
        while (pilih != 7);
    }

    static void tambahMenu() {
        System.out.println("1. Makanan");
        System.out.println("2. Minuman");
        System.out.println("3. Diskon");
        int jenis = input.nextInt();
        input.nextLine();

        String nama;
        double harga;
        switch (jenis) {
            case 1:
                System.out.print("Nama : ");
                nama = input.nextLine();
                System.out.print("Harga : ");
                harga = input.nextDouble();
                input.nextLine();
                System.out.print("Jenis makanan : ");
                String jm = input.nextLine();
                menu.tambahMenu(new Makanan(nama, harga, jm));
                break;
            case 2:
                System.out.print("Nama : ");
                nama = input.nextLine();
                System.out.print("Harga : ");
                harga = input.nextDouble();
                input.nextLine();
                System.out.print("Jenis minuman : ");
                String jn = input.nextLine();
                menu.tambahMenu(new Minuman(nama, harga, jn));
                break;
            case 3:
                System.out.print("Nama Diskon : ");
                nama = input.nextLine();
                System.out.print("Persen Diskon : ");
                double d = input.nextDouble();
                menu.tambahMenu(new Diskon (nama, d));
                break;
        }
    }

    static void pesanMenu () {
        try {
            menu.tampilSemuaMenu();
            System.out.print("Pilih nomor menu :");
            int nomor = input.nextInt();
            MenuItem item = menu.getItem(nomor - 1);
            pesanan.tambahPesanan(item);
            System.out.println(item.getNama() + " berhasil ditambahkan.");
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    static void simpanMenu() {
        try {
            FileWriter fw = new FileWriter("menu.txt");
            for (MenuItem item : menu.getDaftarMenu()) {
                fw.write(item.getNama() + "," + item.getHarga() + "," + item.getKategori() + "\n");
            }
            fw.close();
            System.out.println("Menu berhasil ditambahkan");
        }
        catch (IOException e) {
            System.out.println("Gagal menyimpan file.");
        }
    }

    static void loadMenu() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("menu.txt"));
            String baris;
            while ((baris = br.readLine()) != null) {
                String[] data = baris.split(",");
                String nama = data[0];
                double harga = Double.parseDouble(data[1]);
                String kategori = data[2];
                if (kategori.equals("Makanan")) {
                    menu.tambahMenu(new Makanan (nama, harga, "Default"));
                }
                else if (kategori.equals("Minuman")) {
                    menu.tambahMenu(new Minuman (nama, harga, "Default"));
                }
            }
            br. close();
        }
        catch (Exception e) {
            System.out.println("File menu belum ada");
        }
    }

    static void simpanStruk() {
        try {
            FileWriter fw = new FileWriter("struk.txt");
            for (MenuItem item : pesanan.getDaftarPesanan()) {
                fw.write(item.getNama() + " - Rp " + item.getHarga() + "\n");
            }
            fw.write("\nTotal : Rp " + pesanan.hitungTotal());
            fw.close();
        }
        catch (IOException e) {
            System.out.println("Gagal menyimpan struk");
        }
    }
}
