/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perpustakaan.frontend;

/**
 *
 * @author LENOVO
 */
import perpustakaan.backend.Anggota;
public class TestAnggota {
     public static void main(String[] args) {
        Anggota ang1 = new Anggota("John Doe", "Jl. Merdeka 1", "08123456789");
        Anggota ang2 = new Anggota("Jane Smith", "Jl. Kemerdekaan 2", "08198765432");
        Anggota ang3 = new Anggota("Alice Johnson", "Jl. Proklamasi 3", "08112233445");

        // Test insert
        ang1.save();
        ang2.save();
        ang3.save();

        // Test update
        ang2.setAlamat("Jl. Baru 45");
        ang2.save();

        // Test delete
        ang3.delete();

        // Test select all
        for (Anggota a : new Anggota().getAll()) {
            System.out.println("Nama: " + a.getNama() + ", Alamat: " + a.getAlamat() + ", Telepon: " + a.getTelepon());
        }
    }
}
