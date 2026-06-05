import java.util.ArrayList;

public class Menu {
    private ArrayList<MenuItem> daftarMenu;

    public Menu() {
        daftarMenu = new ArrayList<>();
    }

    public void tambahMenu(MenuItem item) {
        daftarMenu.add(item);
    }

    public ArrayList<MenuItem> getDaftarMenu() {
        return daftarMenu;
    }

    public void tampilSemuaMenu() {
        for (int i = 0; i < daftarMenu.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarMenu.get(i).tampilMenu();
        }
    }

    public MenuItem getItem(int index) throws Exception {
        if (index < 0 || index >= daftarMenu.size()) {
            throw new Exception("Menu tidak ditemukan!");
        }

        return daftarMenu.get(index);
    }
}