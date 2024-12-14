package perpustakaan.backend;

import java.sql.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;




public class Peminjaman {

    private int idpeminjaman;
    private Anggota anggota;
    private Pegawai1 pegawai;
    private DataBuku buku;
    private String tanggalPinjam;
    private String tanggalKembali;

    public Peminjaman() {
        anggota = new Anggota();
        buku = new DataBuku();
        pegawai = new Pegawai1();
    }

    public Peminjaman(Anggota anggota, DataBuku buku, Pegawai1 pegawai, String tanggalPinjam, String tanggalKembali) {
        this.anggota = anggota;
        this.buku = buku;
        this.pegawai = pegawai;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
    }

    public int getIdpeminjaman() {
        return idpeminjaman;
    }

    public void setIdpeminjaman(int idpeminjaman) {
        this.idpeminjaman = idpeminjaman;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public void setAnggota(Anggota anggota) {
        this.anggota = anggota;
    }

    public Pegawai1 getPegawai() {
        return pegawai;
    }
    public DataBuku getBuku() {
        return buku;
    }

    public void setDataBuku(DataBuku buku) {
        this.buku = buku;
    }

    public void setPegawai(Pegawai1 pegawai) {
        this.pegawai = pegawai;
    }
    

    public String getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(String tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public String getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(String tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public Peminjaman getById(int id) {
        Peminjaman peminjaman = new Peminjaman();
        String sql = "SELECT p.*, a.nama as nama_anggota, a.alamat, a.telepon, "
           + "b.judul, b.penerbit, b.penulis, k.nama as kategori, k.keterangan "
           + "FROM peminjaman p "
           + "LEFT JOIN anggota a ON p.idanggota = a.idanggota "
           + "LEFT JOIN buku b ON p.idbuku = b.idbuku "
           + "LEFT JOIN kategori k ON b.idkategori = k.idkategori "
           + "WHERE p.idpeminjaman = 1";
        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                peminjaman.setIdpeminjaman(rs.getInt("idpeminjaman"));
                peminjaman.getAnggota().setIdanggota(rs.getInt("idanggota"));
                peminjaman.getAnggota().setNama(rs.getString("nama_anggota"));
                peminjaman.getAnggota().setAlamat(rs.getString("alamat"));
                peminjaman.getAnggota().setTelepon(rs.getString("telepon"));

                peminjaman.getBuku().setIdbuku(rs.getInt("idbuku"));
                peminjaman.getBuku().setJudul(rs.getString("judul"));
                peminjaman.getBuku().setPenerbit(rs.getString("penerbit"));
                peminjaman.getBuku().setPenulis(rs.getString("penulis"));
                peminjaman.getBuku().getKategori().setNama(rs.getString("kategori"));

                peminjaman.setTanggalPinjam(rs.getString("tanggalPinjam"));
                peminjaman.setTanggalKembali(rs.getString("tanggalKembali"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return peminjaman;
    }

    public ArrayList<Peminjaman> getAll() {
        ArrayList<Peminjaman> listPeminjaman = new ArrayList();
        String sql = "SELECT p.*, "
             + "a.nama AS nama_anggota, a.alamat, a.telepon, "
             + "b.judul, b.penerbit, b.penulis, "
             + "k.nama AS kategori, k.keterangan, "
             + "pg.idpegawai, pg.nama AS nama_pegawai "
             + "FROM peminjaman p "
             + "LEFT JOIN anggota a ON p.idanggota = a.idanggota "
             + "LEFT JOIN buku b ON p.idbuku = b.idbuku "
             + "LEFT JOIN kategori k ON b.idkategori = k.idkategori "
             + "LEFT JOIN pegawai pg ON p.idpegawai = pg.idpegawai";
        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                Peminjaman peminjaman = new Peminjaman();
                peminjaman.setIdpeminjaman(rs.getInt("idpeminjaman"));
                peminjaman.getAnggota().setIdanggota(rs.getInt("idanggota"));
                peminjaman.getAnggota().setAlamat(rs.getString("alamat"));
                peminjaman.getAnggota().setTelepon(rs.getString("telepon"));

                peminjaman.getBuku().setIdbuku(rs.getInt("idbuku"));
                peminjaman.getBuku().setJudul(rs.getString("judul"));
                peminjaman.getBuku().setPenerbit(rs.getString("penerbit"));
                peminjaman.getBuku().setPenulis(rs.getString("penulis"));
                peminjaman.getBuku().getKategori().setNama(rs.getString("kategori"));

                peminjaman.setTanggalPinjam(rs.getString("tanggalPinjam"));
                peminjaman.setTanggalKembali(rs.getString("tanggalKembali"));

                listPeminjaman.add(peminjaman);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPeminjaman;
    }

    public ArrayList<Peminjaman> search(String keyword) {
        ArrayList<Peminjaman> listPeminjaman = new ArrayList();
        String sql = "SELECT p.*, "
             + "a.nama AS nama_anggota, a.alamat, a.telepon, "
             + "b.judul, b.penerbit, b.penulis, "
             + "k.nama AS kategori, k.keterangan, "
             + "pg.idpegawai, pg.nama AS nama_pegawai "
             + "FROM peminjaman p "
             + "LEFT JOIN anggota a ON p.idanggota = a.idanggota "
             + "LEFT JOIN buku b ON p.idbuku = b.idbuku "
             + "LEFT JOIN kategori k ON b.idkategori = k.idkategori "
             + "LEFT JOIN pegawai pg ON p.idpegawai = pg.idpegawai"
             + "AND (a.nama LIKE ? "
             + "OR b.judul LIKE ? "
             + "OR p.tanggalPinjam LIKE ?)";

        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                Peminjaman peminjaman = new Peminjaman();
                peminjaman.setIdpeminjaman(rs.getInt("idpeminjaman"));
                peminjaman.getAnggota().setIdanggota(rs.getInt("idanggota"));
                peminjaman.getAnggota().setNama(rs.getString("nama_anggota"));
                peminjaman.getAnggota().setAlamat(rs.getString("alamat"));
                peminjaman.getAnggota().setTelepon(rs.getString("telepon"));

                peminjaman.getBuku().setIdbuku(rs.getInt("idbuku"));
                peminjaman.getBuku().setJudul(rs.getString("judul"));
                peminjaman.getBuku().setPenerbit(rs.getString("penerbit"));
                peminjaman.getBuku().setPenulis(rs.getString("penulis"));
                peminjaman.getBuku().getKategori().setNama(rs.getString("kategori"));

                peminjaman.setTanggalPinjam(rs.getString("tanggalPinjam"));
                peminjaman.setTanggalKembali(rs.getString("tanggalKembali"));

                listPeminjaman.add(peminjaman);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPeminjaman;
    }

    public boolean isValidDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void save() {
    if (!isValidDate(this.tanggalPinjam)) {
    System.out.println("Error: Tanggal pinjam tidak valid.");
    return;
}

if (this.getBuku().getIdbuku() == 0) {
    System.out.println("Error: ID Buku tidak valid atau belum ada di database.");
    return;
}

if (this.getAnggota().getidanggota() == 0) {
    System.out.println("Error: ID Anggota tidak valid atau belum ada di database.");
    return;
}

if (getById(idpeminjaman).getIdpeminjaman() == 0) {
    String SQL;
    if (this.tanggalKembali == null || this.tanggalKembali.trim().isEmpty()) {
        // Jika tanggal kembali tidak diisi, gunakan NULL
        SQL = "INSERT INTO peminjaman (idanggota, idbuku, idpegawai, tanggalPinjam, tanggalKembali) VALUES("
            + this.getAnggota().getidanggota() + ", "
            + this.getBuku().getIdbuku() + ", "
            + this.getPegawai().getIdpegawai() + ", "
            + "'" + this.tanggalPinjam + "', "
            + "NULL"
            + ")";
    } else {
        SQL = "INSERT INTO peminjaman (idanggota, idbuku, idpegawai, tanggalPinjam, tanggalKembali) VALUES("
            + this.getAnggota().getidanggota() + ", "
            + this.getBuku().getIdbuku() + ", "
            + this.getPegawai().getIdpegawai() + ", "
            + "'" + this.tanggalPinjam + "', "
            + "'" + this.tanggalKembali + "'"
            + ")";
    }
    this.idpeminjaman = DBHelper.insertQueryGetID(SQL);
} else {
    String SQL = "UPDATE peminjaman SET "
            + "idanggota = " + this.getAnggota().getidanggota() + ", "
            + "idbuku = " + this.getBuku().getIdbuku() + ", "
            + "idpegawai = " + this.getPegawai().getIdpegawai() + ", "
            + "tanggalPinjam = '" + this.tanggalPinjam + "', ";
    if (this.tanggalKembali == null || this.tanggalKembali.trim().isEmpty()) {
        SQL += "tanggalKembali = NULL ";
    } else {
        SQL += "tanggalKembali = '" + this.tanggalKembali + "' ";
    }
    SQL += "WHERE idpeminjaman = " + this.idpeminjaman;
    DBHelper.executeQuery(SQL);
}
    }

}
