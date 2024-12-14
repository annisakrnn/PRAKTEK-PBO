package perpustakaan.frontend;

import perpustakaan.backend.Kategori;
import perpustakaan.backend.DataBuku;
public class TestDataBuku {
    public static void main(String[] args) {
Kategori novel = new Kategori().getById(2);
Kategori referensi = new Kategori().getById(1);
DataBuku buku1 = new DataBuku(novel, "Timun Mas", "Elex Media", "Bang Supit");
DataBuku buku2 = new DataBuku(referensi, "Metode Linier", "Springer", "Alex Baldwin");
DataBuku buku3 = new DataBuku(novel, "Bintang Terang", "Erlangga", "Mat Sewoot");
buku1.save();
buku2.save();
buku2.setJudul("Aljabar Linier");
buku2.save();
buku3.delete();
for(DataBuku b : new DataBuku().getAll())
{
System.out.println("Kategori: " + b.getKategori().getNama() + ", Judul: " + b.getJudul());
}
for(DataBuku b : new DataBuku().search("timun"))
{
System.out.println("Kategori: " + b.getKategori().getNama() + ", Judul: " + b.getJudul());
}  
    }
}
