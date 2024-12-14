package perpustakaan.backend;

import java.util.ArrayList;
import java.sql.*;

public class Pegawai1 {

    private int idpegawai;
    private String nama;
    private String alamat;
    private String notelp;
    private String jabatan;
    private String username;
    private String password;

    // Constructor
    public Pegawai1() {
    }

    public Pegawai1(int idpegawai, String nama, String alamat, String notelp, String jabatan, String username, String password) {
        this.idpegawai = idpegawai;
        this.nama = nama;
        this.alamat = alamat;
        this.notelp = notelp;
        this.jabatan = jabatan;
        this.username = username;
        this.password = password;
    }

    public int getIdpegawai() {
        return idpegawai;
    }

    public void setIdpegawai(int idpegawai) {
        this.idpegawai = idpegawai;
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

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Pegawai1 getById(int id) {
        Pegawai1 peg = new Pegawai1();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM pegawai WHERE idpegawai = '" + id + "'");
        try {
            if (rs.next()) {
                peg.setIdpegawai(rs.getInt("idpegawai"));
                peg.setNama(rs.getString("nama"));
                peg.setAlamat(rs.getString("alamat"));
                peg.setNotelp(rs.getString("notelp"));
                peg.setJabatan(rs.getString("jabatan"));
                peg.setUsername(rs.getString("username"));
                peg.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return peg;
    }

    public ArrayList<Pegawai1> getAll() {
        ArrayList<Pegawai1> listPegawai = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM pegawai");
        try {
            while (rs.next()) {
                Pegawai1 peg = new Pegawai1();
                peg.setIdpegawai(rs.getInt("idpegawai"));
                peg.setNama(rs.getString("nama"));
                peg.setAlamat(rs.getString("alamat"));
                peg.setNotelp(rs.getString("notelp"));
                peg.setJabatan(rs.getString("jabatan"));
                peg.setUsername(rs.getString("username"));
                peg.setPassword(rs.getString("password"));
                listPegawai.add(peg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPegawai;
    }

    public ArrayList<Pegawai1> search(String keyword) {
        ArrayList<Pegawai1> listPegawai = new ArrayList<>();
        String sql = "SELECT * FROM pegawai WHERE " +
                     "nama LIKE '%" + keyword + "%' OR " +
                     "alamat LIKE '%" + keyword + "%' OR " +
                     "notelp LIKE '%" + keyword + "%' OR " +
                     "jabatan LIKE '%" + keyword + "%' OR " +
                     "username LIKE '%" + keyword + "%' OR " +
                     "password LIKE '%" + keyword + "%'";
        ResultSet rs = DBHelper.selectQuery(sql);
        try {
            while (rs.next()) {
                Pegawai1 peg = new Pegawai1();
                peg.setIdpegawai(rs.getInt("idpegawai"));
                peg.setNama(rs.getString("nama"));
                peg.setAlamat(rs.getString("alamat"));
                peg.setNotelp(rs.getString("notelp"));
                peg.setJabatan(rs.getString("jabatan"));
                peg.setUsername(rs.getString("username"));
                peg.setPassword(rs.getString("password"));
                listPegawai.add(peg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPegawai;
    }

    public void save() {
        if (getById(idpegawai).getIdpegawai() == 0) { // If the pegawai does not exist, insert it
            String SQL = "INSERT INTO pegawai (nama, alamat, notelp, jabatan, username, password) VALUES (" +
                         "'" + this.nama + "', " +
                         "'" + this.alamat + "', " +
                         "'" + this.notelp + "', " +
                         "'" + this.jabatan + "', " +
                         "'" + this.username + "', " +
                         "'" + this.password + "')";
            this.idpegawai = DBHelper.insertQueryGetID(SQL); // Get the generated ID after insertion
        } else { // If the pegawai exists, update it
            String SQL = "UPDATE pegawai SET " +
                         "nama = '" + this.nama + "', " +
                         "alamat = '" + this.alamat + "', " +
                         "notelp = '" + this.notelp + "', " +
                         "jabatan = '" + this.jabatan + "', " +
                         "username = '" + this.username + "', " +
                         "password = '" + this.password + "' " +
                         "WHERE idpegawai = '" + this.idpegawai + "'";
            DBHelper.executeQuery(SQL); // Execute the update query
        }
    }

    public void delete() {
        String SQL = "DELETE FROM pegawai WHERE idpegawai = '" + this.idpegawai + "'";
        DBHelper.executeQuery(SQL); 
    }

    public boolean isUsernameExists(String username) {
        boolean result = false;
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM pegawai WHERE username = '" + username + "'");
        try {
            if (rs.next()) {
                result = true; 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
