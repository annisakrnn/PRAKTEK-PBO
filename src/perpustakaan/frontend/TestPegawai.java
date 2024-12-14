package perpustakaan.frontend;

import perpustakaan.backend.Pegawai1;

public class TestPegawai {
    public static void main(String[] args) {
        Pegawai1 peg1 = new Pegawai1(0, "John Doe", "Jl. Merdeka 1", "08123456789", "Admin", "johndoe", "password123");
        Pegawai1 peg2 = new Pegawai1(0, "Jane Smith", "Jl. Kemerdekaan 2", "08198765432", "Staff", "janesmith", "password456");

        System.out.println("Testing Insert Pegawai...");
        if (!peg1.isUsernameExists(peg1.getUsername())) {
            peg1.save();
        } else {
            System.out.println("Username '" + peg1.getUsername() + "' sudah digunakan!");
        }

        if (!peg2.isUsernameExists(peg2.getUsername())) {
            peg2.save();
        } else {
            System.out.println("Username '" + peg2.getUsername() + "' sudah digunakan!");
        }

        peg2.setAlamat("Jl. Baru 45");
        peg2.save(); 

        peg2.delete(); 

        System.out.println("\nData Pegawai:");
        for (Pegawai1 p : new Pegawai1().getAll()) {
            System.out.println("Nama: " + p.getNama() + 
                               ", Alamat: " + p.getAlamat() + 
                               ", Telepon: " + p.getNotelp() + 
                               ", Jabatan: " + p.getJabatan() + 
                               ", Username: " + p.getUsername());
        }

        System.out.println("\nHasil Pencarian Pegawai dengan keyword 'John':");
        for (Pegawai1 p : new Pegawai1().search("John")) {
            System.out.println("Nama: " + p.getNama() + 
                               ", Alamat: " + p.getAlamat() + 
                               ", Telepon: " + p.getNotelp() + 
                               ", Jabatan: " + p.getJabatan() + 
                               ", Username: " + p.getUsername());
        }
    }
}
