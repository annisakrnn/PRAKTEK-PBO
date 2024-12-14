package perpustakaan.backend;

import java.sql.*;
import java.util.ArrayList;

public class Anggota {
    private int idanggota;
    private String nama;
    private String alamat;
    private String telepon;

    public Anggota(){
    
    }
    public Anggota(String nama, String alamat, String telepon) {
        this.nama = nama;
        this.alamat = alamat;
        this.telepon = telepon;
    }
    public int getidanggota() {
        return idanggota;
    }
    public void setIdanggota(int idanggota) {
        this.idanggota = idanggota;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    public String getTelepon() {
        return telepon;
    }
    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    //mengambil data anggota berdasarkan id anggota
    public Anggota getById(int id) {
        Anggota anggota = new Anggota();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM anggota WHERE idanggota = '" + id + "'");
        try {
            if (rs.next()) {
                anggota.setIdanggota(rs.getInt("idanggota"));
                anggota.setNama(rs.getString("nama"));
                anggota.setAlamat(rs.getString("alamat"));
                anggota.setTelepon(rs.getString("telepon"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return anggota;
    }
    //mengambil semua data anggota
    public ArrayList<Anggota> getAll() {
        ArrayList<Anggota> listAnggota = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM anggota");

        try {
            while (rs.next()) {
                Anggota anggota = new Anggota();
                anggota.setIdanggota(rs.getInt("idanggota"));
                anggota.setNama(rs.getString("nama"));
                anggota.setAlamat(rs.getString("alamat"));
                anggota.setTelepon(rs.getString("telepon"));
                listAnggota.add(anggota);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAnggota;
    }
    //membuat untuk pencarian berdasarkan nama,alamat, telepon
    public ArrayList<Anggota> search(String keyword) {
        ArrayList<Anggota> listAnggota = new ArrayList<>();
        String sql = "SELECT * FROM anggota WHERE "
                   + "nama LIKE '%" + keyword + "%' "
                   + "OR alamat LIKE '%" + keyword + "%' "
                   + "OR telepon LIKE '%" + keyword + "%'";
        ResultSet rs = DBHelper.selectQuery(sql);
        try {
            while (rs.next()) {
                Anggota anggota = new Anggota();
                anggota.setIdanggota(rs.getInt("idanggota"));
                anggota.setNama(rs.getString("nama"));
                anggota.setAlamat(rs.getString("alamat"));
                anggota.setTelepon(rs.getString("telepon"));
                listAnggota.add(anggota);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAnggota;
    }
    //menyimpan dengan melakukan insert atau update
    public void save() {
        if (getById(idanggota).getidanggota() == 0) {
            String sql = "INSERT INTO anggota (nama, alamat, telepon) VALUES ("
                       + "'" + this.nama + "', "
                       + "'" + this.alamat + "', "
                       + "'" + this.telepon + "')";
            this.idanggota = DBHelper.insertQueryGetID(sql);
        } else {
            String sql = "UPDATE anggota SET "
                       + "nama = '" + this.nama + "', "
                       + "alamat = '" + this.alamat + "', "
                       + "telepon = '" + this.telepon + "' "
                       + "WHERE idanggota = '" + this.idanggota + "'";
            DBHelper.executeQuery(sql);
        }
    }
    //menghapus anggota berdasarkan id
    public void delete() {
        String sql = "DELETE FROM anggota WHERE idanggota = '" + this.idanggota + "'";
        DBHelper.executeQuery(sql);
    }
}
