/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perpustakaan.frontend;

import perpustakaan.backend.Kategori;

/**
 *
 * 
 * @author LENOVO
 */
public class TestBackend {
    public static void main(String[] args) {
        // Membuat objek Kategori
        //Kategori kat1 = new Kategori("Novel", "Koleksi buku novel");
        Kategori kat2 = new Kategori("Referensi", "Buku referensi ilmiah");
        //Kategori kat3 = new Kategori("Komik", "Komik anak-anak");

        // Test insert
        //kat1.save();
        kat2.save();
        //kat3.save();

        // Test update
        //kat2.setKeterangan("Koleksi buku referensi ilmiah");
        //kat2.save();

        // Test delete
        //System.out.println("Yang dihapus:");
        //kat2.delete();

        // Test select all
        System.out.println("Daftar Kategori:");
        for (Kategori k : new Kategori().getAll()) {
            System.out.println("Nama: " + k.getNama() + ", Ket: " + k.getKeterangan());
        }

        // Test search
        System.out.println("\nHasil Pencarian (kata kunci: 'ilmiah'):");
        for (Kategori k : new Kategori().search("ilmiah")) {
            System.out.println("Nama: " + k.getNama() + ", Ket: " + k.getKeterangan());
        }
    }
}
