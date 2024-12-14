package perpustakaan.backend;

import java.sql.*;
import java.util.ArrayList;

public class DataBuku {
    private int idbuku;
    private Kategori kategori = new Kategori(); 
    private String judul;
    private String penerbit;
    private String penulis;

    public DataBuku() {
    }

    public DataBuku(Kategori kategori, String judul, String penerbit, String penulis) {
        this.kategori = kategori;
        this.judul = judul;
        this.penerbit = penerbit;
        this.penulis = penulis;
    }

    public int getIdbuku() {
        return idbuku;
    }

    public void setIdbuku(int idbuku) {
        this.idbuku = idbuku;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    //mengambil data buku berdasarkan id buku
    public DataBuku getById(int id) {
        DataBuku buku = new DataBuku();
        String sql = "SELECT b.*, k.nama as kategori, k.keterangan "
                + "FROM buku b LEFT JOIN kategori k ON b.idkategori = k.idkategori "
                + "WHERE b.idbuku = '" + id + "'";

        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                buku.setIdbuku(rs.getInt("idbuku"));
                buku.getKategori().setIdkategori(rs.getInt("idkategori"));
                buku.getKategori().setNama(rs.getString("kategori"));
                buku.getKategori().setKeterangan(rs.getString("keterangan"));
                buku.setJudul(rs.getString("judul"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setPenulis(rs.getString("penulis"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buku;
    }


    // untuk mendapatkan semua buku
    public ArrayList<DataBuku> getAll() {
        ArrayList<DataBuku> listBuku = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT b.idbuku, b.judul, b.penerbit, b.penulis, k.idkategori, k.nama, k.keterangan " +
                                            "FROM buku b LEFT JOIN kategori k ON b.idkategori = k.idkategori");
        try {
            while (rs.next()) {
                DataBuku buku = new DataBuku();
                buku.setIdbuku(rs.getInt("idbuku"));
                buku.getKategori().setIdkategori(rs.getInt("idkategori"));
                buku.getKategori().setNama(rs.getString("nama"));
                buku.getKategori().setKeterangan(rs.getString("keterangan"));
                buku.setJudul(rs.getString("judul"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setPenulis(rs.getString("penulis"));
                listBuku.add(buku);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBuku;
    }

    // untuk menyimpan data buku (insert atau update)
    public void save() {
        if (getById(idbuku).getIdbuku() == 0) {
            String SQL = "INSERT INTO buku (judul, idkategori, penulis, penerbit) VALUES ('" +
                         this.judul + "', '" +
                         this.getKategori().getIdkategori() + "', '" +
                         this.penulis + "', '" +
                         this.penerbit + "')";
            this.idbuku = DBHelper.insertQueryGetID(SQL);
        } else {
            String SQL = "UPDATE buku SET " +
                         "judul = '" + this.judul + "', " +
                         "idkategori = '" + this.getKategori().getIdkategori() + "', " +
                         "penulis = '" + this.penulis + "', " +
                         "penerbit = '" + this.penerbit + "' " +
                         "WHERE idbuku = " + this.idbuku;
            DBHelper.executeQuery(SQL);
        }
    }

    //untuk menghapus buku
    public void delete() {
        String query = "DELETE FROM buku WHERE idbuku = " + this.idbuku;
        DBHelper.executeQuery(query);
    }

    //pencarian buku berdasarkan judul
    public ArrayList<DataBuku> search(String keyword) {
        ArrayList<DataBuku> listBuku = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT b.idbuku, b.judul, b.penerbit, b.penulis, k.idkategori, k.nama, k.keterangan " +
                                            "FROM buku b LEFT JOIN kategori k ON b.idkategori = k.idkategori " +
                                            "WHERE b.judul LIKE '%" + keyword + "%' " +
                                            "OR b.penerbit LIKE '%" + keyword + "%' " +
                                            "OR b.penulis LIKE '%" + keyword + "%'");
        try {
            while (rs.next()) {
                DataBuku buku = new DataBuku();
                buku.setIdbuku(rs.getInt("idbuku"));
                buku.getKategori().setIdkategori(rs.getInt("idkategori"));
                buku.getKategori().setNama(rs.getString("nama"));
                buku.getKategori().setKeterangan(rs.getString("keterangan"));
                buku.setJudul(rs.getString("judul"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setPenulis(rs.getString("penulis"));
                listBuku.add(buku);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBuku;
    }

}
