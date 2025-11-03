/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package master;
import clss.Koneksi;
import java.awt.Color;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import master.brKategori;
/**
 *
 * @author Admin
 */
public class frmMstKategori extends javax.swing.JFrame {
    private Connection conn;
    private Statement stat;
    private ResultSet rs;
    private String sql;
    private int currentRecordIndex = 0; // Default nilai untuk nomor record saat ini
    private int totalInputs = 0;
    /**
     * Creates new form ParentTrans
     */
    public frmMstKategori() {
        initComponents();
        initializeDatabase();
        jToolBar1.setFloatable(false);
        jToolBar2.setFloatable(false);
        setLocationRelativeTo(null);
        awal();
    }

private void openKategoriDialog() {
    brKategori dialog = new brKategori(this);
    dialog.setVisible(true); // Menampilkan dialog sebagai modal
    String ID = dialog.getSelectedID();
    String kode = dialog.getSelectedKode();
    String nama = dialog.getSelectedNama();
    String keterangan = dialog.getSelectedKeterangan();
    boolean aktif = dialog.isAktif();

    if (kode != null && !kode.isEmpty()) {
        // Mengisi komponen di form utama
        IDKategori.setText(ID);
        jtKode.setText(kode);
        jtNama.setText(nama);
        jEditorPane1.setText(keterangan);
        cmbAktif.setSelected(aktif);
    } else {
        System.out.println("Tidak ada kategori yang dipilih.");
    }
}
    // Metode dan event handler lainnya


    private void initializeDatabase() {
                try {
            conn = Koneksi.getConnection();
            if (conn != null) {
                stat = conn.createStatement();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to establish connection to the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error initializing database connection: " + e.getMessage());
        }
    }
    private void IDotomatis() {
    try {
        String sql = "SELECT MAX(Kode) AS MAX_Kode FROM mkategori";
        rs = stat.executeQuery(sql);
        if (rs.next()) {
            String lastCode = rs.getString("MAX_Kode"); // Mendapatkan kode terakhir dari database
            
            // Mendapatkan dua huruf pertama dari field nama
            String nama = jtNama.getText().trim(); // Ganti jTNama sesuai dengan field nama di form Anda
            String duaHurufNama = nama.substring(0, Math.min(nama.length(), 2)).toUpperCase(); // Ambil dua huruf pertama
            
            // Mendapatkan nomor urut terakhir
            int nomorUrut = 0;
            if (lastCode != null && lastCode.startsWith(duaHurufNama)) {
                // Jika sudah ada kode dengan dua huruf nama yang sama
                String nomorStr = lastCode.substring(2); // Ambil bagian nomor urut setelah dua huruf nama
                nomorUrut = Integer.parseInt(nomorStr) + 1; // Increment nomor urut
            } else {
                // Jika belum ada kode untuk dua huruf nama tersebut
                nomorUrut = 1;
            }
            
            // Format nomor urut menjadi empat digit dengan leading zeros
            String nomorUrutFormatted = String.format("%04d", nomorUrut);
            
            // Buat kode lengkap
            String kodeLengkap = duaHurufNama + nomorUrutFormatted;
            
            jtKode.setText(kodeLengkap);
        } else {
            // Jika tidak ada data sebelumnya, beri kode untuk dua huruf pertama dari field nama + nomor urut 0001
            String nama = jtKode.getText().trim(); // Ganti jTNama sesuai dengan field nama di form Anda
            String duaHurufNama = nama.substring(0, Math.min(nama.length(), 2)).toUpperCase(); // Ambil dua huruf pertama
            String nomorUrutFormatted = "0001"; // Nomor urut awal
            
            // Buat kode lengkap
            String kodeLengkap = duaHurufNama + nomorUrutFormatted;
            
            jtKode.setText(kodeLengkap);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
//[Tampilan]
    public void awal(){
        jLabel1.setText("[Browse]");
        jButtonTambah.setEnabled(true);
        jButtonUbah.setEnabled(true);
        jButtonHapus.setEnabled(true);
        jtKode.setEnabled(false);
        jtNama.setEnabled(false);
        btnSimpan.setEnabled(false);
        btnCencel.setEnabled(false);
        btnExite.setEnabled(false);
        jEditorPane1.setEnabled(false);
        jToolBar1.setEnabled(true);
        navaktif();
        data_terakhir();
    }
    private void navaktif(){
    jButton4.setEnabled(true);
    jButton5.setEnabled(true);
    jButton6.setEnabled(true);
    jButton7.setEnabled(true);
}
    private void navnonaktif(){
    jButton4.setEnabled(false);
    jButton5.setEnabled(false);
    jButton6.setEnabled(false);
    jButton7.setEnabled(false);
}
    public void tambah(){
        jLabel1.setText("[Tambah]");
        jButtonUbah.setEnabled(false);
        jButtonHapus.setEnabled(false);
        jtKode.setText("");
        jEditorPane1.setText("");
        jButton6.setEnabled(true);
        jButton5.setEnabled(true);
        jButton4.setEnabled(false);
        btnSimpan.setEnabled(true);
        btnCencel.setEnabled(true);
        btnExite.setEnabled(true);
        jtKode.setEnabled(true);
        jtNama.setEnabled(true);
        jEditorPane1.setEnabled(true);
        jtNama.setText("");
        cmbAktif.setSelected(true);
    
        PtxjtKodeFocusLost();
        jtNama.requestFocusInWindow();
        jToolBar1.setEnabled(false);
        navnonaktif();
    }
    public void ubah(){
         jLabel1.setText("[Ubah]");
        jButtonTambah.setEnabled(false);
        jButtonHapus.setEnabled(false);
        jButton6.setEnabled(true);
        jButton5.setEnabled(true);
        jButton4.setEnabled(false);
        btnSimpan.setEnabled(true);
        btnCencel.setEnabled(true);
        btnExite.setEnabled(true);
        jtKode.setEnabled(true);
        jtNama.setEnabled(true);
        jEditorPane1.setEnabled(true);
        cmbAktif.setSelected(true);
       navnonaktif();
    }
//[kode]
    private void PtxjtKodeFocusLost() {                                      
        // TODO add your handling code here:
        if (jtKode.getText().equals("")) {
        jtKode.setText("Kode");
        jtKode.setForeground(Color.GRAY);
    }
    }     
//[CRUD]]
    private void ubahdata() {
    PreparedStatement pstmt = null; // PreparedStatement untuk update
    ResultSet rs = null; // ResultSet untuk menampung hasil query overlap
    int IKategori = Integer.parseInt(IDKategori.getText());
    try {
         String sqlUpdate = "UPDATE mkategori SET Nama = ?, Deskripsi = ?, IsAktif = ? WHERE Kode = ?";
        
        // Mendapatkan nilai dari komponen-komponen UI
        String kode = jtKode.getText();
        String nama = jtNama.getText();
        String keterangan = jEditorPane1.getText();
        boolean aktif = cmbAktif.isSelected();
        String aktifStr = aktif ? "Ya" : "Tidak";

        // Membuat PreparedStatement untuk update
        pstmt = conn.prepareStatement(sqlUpdate);
        pstmt.setString(1, nama);
        pstmt.setString(2, keterangan);
        pstmt.setString(3, aktifStr);
        pstmt.setString(4, kode);

        // Eksekusi query SQL untuk update data
        int hasil = pstmt.executeUpdate();
        
        if (hasil > 0) {
            JOptionPane.showMessageDialog(null, "Data berhasil diubah di database.");
        } else {
            JOptionPane.showMessageDialog(null, "Gagal mengubah data di database.");
        }
//        rs.next();
        
        // Jika terdapat overlap (jika diperlukan), tampilkan pesan error
        
        if (hasil > 0) {
            JOptionPane.showMessageDialog(null, "Data berhasil diubah.");
            
            // Setelah mengubah data, muat data terbaru
            data_terakhir();
        } else {
            JOptionPane.showMessageDialog(null, "Gagal mengubah data.");
        }
        
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
    } finally {
        try {
            // Menutup PreparedStatement dan ResultSet
            if (pstmt != null) {
                pstmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            // Koneksi tidak ditutup di sini agar dapat digunakan di tempat lain
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
    private void simpan() {
    PreparedStatement pstmt = null;
            if (jtKode.getText().equals("Kode")) {
            jtKode.setText("");
            jtKode.setForeground(Color.BLACK);
        }
    IDotomatis();
    
    try {
        // Query SQL untuk insert data ke dalam tabel
        String sqlInsert = "INSERT INTO mkategori (Kode, Nama, Deskripsi, IsAktif) VALUES (?, ?, ?, ?)";
        
        // Mendapatkan nilai dari komponen-komponen UI
        String kode = jtKode.getText();
        String nama = jtNama.getText();
        String keterangan = jEditorPane1.getText();
        boolean aktif = cmbAktif.isSelected();
        String aktifStr = aktif ? "Ya" : "Tidak";
        
        // Membuat PreparedStatement untuk insert
        pstmt = conn.prepareStatement(sqlInsert);
        pstmt.setString(1, kode);
        pstmt.setString(2, nama);
        pstmt.setString(3, keterangan);
        pstmt.setString(4, aktifStr);
        
        // Eksekusi query SQL untuk insert data
        int hasil = pstmt.executeUpdate();
        
        if (hasil > 0) {
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan ke database.");
        } else {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan data ke database.");
        }
        
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan SQL: " + e.getMessage());
    } finally {
        try {
            // Menutup PreparedStatement
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
//[datanav]
    private void updateRecordLabel() {
    recordLabel.setText("Record: " + currentRecordIndex + " of " + totalInputs);
}
    private void next() {
    if (currentRecordIndex < totalInputs) { // Pastikan tidak melebihi jumlah total input
        try {
            currentRecordIndex++;
            
            // Query SQL untuk mengambil data record berikutnya berdasarkan currentRecordIndex
            String sqlSelectNext = "SELECT * FROM mkategori ORDER BY IDKategori ASC LIMIT ?, 1";
            
            // Membuat PreparedStatement untuk eksekusi query
            PreparedStatement pstmt = conn.prepareStatement(sqlSelectNext);
            pstmt.setInt(1, currentRecordIndex - 1); // -1 karena index di SQL dimulai dari 0
            
            // Eksekusi query dan ambil hasil
            ResultSet rs = pstmt.executeQuery();
            
            // Memeriksa apakah ada hasil dari query
            if (rs.next()) {
                // Ambil nilai kolom-kolom dari hasil query
                
                String kode = rs.getString("Kode");
                String nama = rs.getString("Nama");
                String keterangan = rs.getString("Deskripsi");
                boolean aktif = rs.getString("IsAktif").equals("Ya");
                
                String ID = rs.getString("IDKategori");
                
                IDKategori.setText(ID);
                // Tampilkan nilai-nilai ini di komponen UI sesuai kebutuhan
                jtKode.setText(kode);
                jtNama.setText(nama);
                jEditorPane1.setText(keterangan);
                cmbAktif.setSelected(aktif);
                
                // Update JLabel untuk menampilkan informasi nomor record
                updateRecordLabel();
                
            } else {
                JOptionPane.showMessageDialog(null, "Data berikutnya tidak ditemukan.");
            }
            
            // Tutup ResultSet dan PreparedStatement
            rs.close();
            pstmt.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengambil data: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null, "Anda sudah berada pada record terakhir.");
        // Tidak perlu mengubah currentRecordIndex jika sudah di record terakhir
    }
}
    private int hitungJumlahRecords() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
    int totalRecords = 0;
    
    try {
        // Query SQL untuk menghitung jumlah total record
        String sqlCount = "SELECT COUNT(*) AS total FROM mkategori";
        
        // Membuat PreparedStatement untuk query COUNT
        pstmt = conn.prepareStatement(sqlCount);
        
        // Eksekusi query
        rs = pstmt.executeQuery();
        
        // Jika terdapat hasil dari query, ambil jumlah total record
        if (rs.next()) {
            totalRecords = rs.getInt("total");
        }
        
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menghitung jumlah record: " + e.getMessage());
    } finally {
        try {
            // Menutup PreparedStatement dan ResultSet
            if (pstmt != null) {
                pstmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    return totalRecords;
}
    private void previous() {
    if (currentRecordIndex > 1) { // Pastikan tidak kurang dari record pertama
        try {
            currentRecordIndex--;
            
            // Query SQL untuk mengambil data record sebelumnya berdasarkan currentRecordIndex
            String sqlSelectPrevious = "SELECT * FROM mkategori ORDER BY IDKategori ASC LIMIT ?, 1";
            
            // Membuat PreparedStatement untuk eksekusi query
            PreparedStatement pstmt = conn.prepareStatement(sqlSelectPrevious);
            pstmt.setInt(1, currentRecordIndex - 1); // -1 karena index di SQL dimulai dari 0
            
            // Eksekusi query dan ambil hasil
            ResultSet rs = pstmt.executeQuery();
            
            // Memeriksa apakah ada hasil dari query
            if (rs.next()) {
                // Ambil nilai kolom-kolom dari hasil query
                String ID = rs.getString("IDKategori");
                String kode = rs.getString("Kode");
                String nama = rs.getString("Nama");
                String keterangan = rs.getString("Deskripsi");
                boolean aktif = rs.getString("IsAktif").equals("Ya");
                
               
                
                IDKategori.setText(ID);
                // Tampilkan nilai-nilai ini di komponen UI sesuai kebutuhan
                jtKode.setText(kode);
                jtNama.setText(nama);
                jEditorPane1.setText(keterangan);
                cmbAktif.setSelected(aktif);
                
                // Update JLabel untuk menampilkan informasi nomor record
                updateRecordLabel();
                
            } else {
                JOptionPane.showMessageDialog(null, "Data sebelumnya tidak ditemukan.");
            }
            
            // Tutup ResultSet dan PreparedStatement
            rs.close();
            pstmt.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengambil data: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null, "Anda sudah berada pada record pertama.");
        // Tidak perlu mengubah currentRecordIndex jika sudah di record pertama
    }
}
    private void data_awal() {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try {
        String sqlCount = "SELECT COUNT(*) AS total FROM mkategori";
        PreparedStatement pstmtCount = conn.prepareStatement(sqlCount);
        ResultSet rsCount = pstmtCount.executeQuery();
        
        if (rsCount.next()) {
            totalInputs = rsCount.getInt("total");
        }
        
        rsCount.close();
        pstmtCount.close();
        
        // Di sini Anda bisa menentukan nomor urutan input saat ini, misalnya dari indeks 1
        currentRecordIndex = 1;
        
        // Update JLabel untuk menampilkan informasi nomor urutan input saat ini dari total input
        updateRecordLabel();
        // Query SQL untuk mengambil data paling awal
        String sqlSelect = "SELECT IDKategori, Kode, Nama, Deskripsi, IsAktif FROM mkategori ORDER BY IDKategori ASC LIMIT 1";
        
        // Membuat PreparedStatement untuk query select
        pstmt = conn.prepareStatement(sqlSelect);
        
        // Eksekusi query
        rs = pstmt.executeQuery();
        
        // Jika terdapat hasil dari query, tampilkan ke dalam komponen UI
        if (rs.next()) {
                String ID = rs.getString("IDKategori");
                String kode = rs.getString("Kode");
                String nama = rs.getString("Nama");
                String keterangan = rs.getString("Deskripsi");
                boolean aktif = rs.getString("IsAktif").equals("Ya");
                
               
                
                IDKategori.setText(ID);
                // Tampilkan nilai-nilai ini di komponen UI sesuai kebutuhan
                jtKode.setText(kode);
                jtNama.setText(nama);
                jEditorPane1.setText(keterangan);
               cmbAktif.setSelected(aktif);

        } else {
            // Jika tidak ada hasil, mungkin menampilkan pesan atau melakukan penanganan lainnya
            JOptionPane.showMessageDialog(null, "Tidak ada data yang tersedia.");
        }
        
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
    } finally {
        try {
            // Menutup PreparedStatement dan ResultSet
            if (pstmt != null) {
                pstmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
    private void data_terakhir() {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try {
        // Query SQL untuk mengambil jumlah total record
        String sqlCount = "SELECT COUNT(*) AS total FROM mkategori";
        PreparedStatement pstmtCount = conn.prepareStatement(sqlCount);
        ResultSet rsCount = pstmtCount.executeQuery();
        
        if (rsCount.next()) {
            totalInputs = rsCount.getInt("total");
        }
        
        rsCount.close();
        pstmtCount.close();
        
        // Set currentRecordIndex ke totalInputs untuk menunjukkan data terakhir
        currentRecordIndex = totalInputs;
        
        // Update JLabel untuk menampilkan informasi nomor urutan input saat ini dari total input
        updateRecordLabel();
        
        // Query SQL untuk mengambil data terakhir
        String sqlSelect = "SELECT IDKategori, Kode, Nama, Deskripsi, IsAktif FROM mkategori ORDER BY IDKategori DESC LIMIT 1";
        
        // Membuat PreparedStatement untuk query select
        pstmt = conn.prepareStatement(sqlSelect);
        
        // Eksekusi query
        rs = pstmt.executeQuery();
        
        // Jika terdapat hasil dari query, tampilkan ke dalam komponen UI
        if (rs.next()) {
                String ID = rs.getString("IDKategori");
                String kode = rs.getString("Kode");
                String nama = rs.getString("Nama");
                String keterangan = rs.getString("Deskripsi");
                boolean aktif = rs.getString("IsAktif").equals("Ya");
                
               
                
                IDKategori.setText(ID);
                // Tampilkan nilai-nilai ini di komponen UI sesuai kebutuhan
                jtKode.setText(kode);
                jtNama.setText(nama);
                jEditorPane1.setText(keterangan);
                cmbAktif.setSelected(aktif);
                
        } else {
            // Jika tidak ada hasil, mungkin menampilkan pesan atau melakukan penanganan lainnya
            JOptionPane.showMessageDialog(null, "Tidak ada data yang tersedia.");
        }
        
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
    } finally {
        try {
            // Menutup PreparedStatement dan ResultSet
            if (pstmt != null) {
                pstmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        IDKategori = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jButtonTambah = new javax.swing.JButton();
        jButtonUbah = new javax.swing.JButton();
        jButtonHapus = new javax.swing.JButton();
        recordLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnExite = new javax.swing.JButton();
        btnCencel = new javax.swing.JButton();
        cmbAktif = new javax.swing.JCheckBox();
        jtKode = new javax.swing.JTextField();
        lblKode = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        lblKode1 = new javax.swing.JLabel();
        jtNama = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JTextArea();
        lblKode2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        IDKategori.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Master Kategori");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 102, 255));

        jPanel3.setBackground(new java.awt.Color(94, 169, 245));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setMinimumSize(new java.awt.Dimension(100, 25));
        jPanel3.setPreferredSize(new java.awt.Dimension(100, 25));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Mode Aktif :");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("[Browse]");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jToolBar2.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar2.setRollover(true);

        jButton4.setText("<<");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton4);

        jButton5.setText("<");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton5);

        jButton6.setText(">");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton6);

        jButton7.setText(">>");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton7);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jToolBar1.setBackground(new java.awt.Color(94, 169, 245));
        jToolBar1.setRollover(true);
        jToolBar1.setMaximumSize(new java.awt.Dimension(100, 25));
        jToolBar1.setMinimumSize(new java.awt.Dimension(100, 25));

        jButtonTambah.setText("Tambah");
        jButtonTambah.setFocusable(false);
        jButtonTambah.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonTambah.setMaximumSize(new java.awt.Dimension(60, 30));
        jButtonTambah.setMinimumSize(new java.awt.Dimension(60, 30));
        jButtonTambah.setPreferredSize(new java.awt.Dimension(60, 30));
        jButtonTambah.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTambahActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonTambah);

        jButtonUbah.setText("Ubah");
        jButtonUbah.setFocusable(false);
        jButtonUbah.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonUbah.setMaximumSize(new java.awt.Dimension(53, 30));
        jButtonUbah.setMinimumSize(new java.awt.Dimension(53, 30));
        jButtonUbah.setPreferredSize(new java.awt.Dimension(53, 30));
        jButtonUbah.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUbahActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonUbah);

        jButtonHapus.setText("Hapus");
        jButtonHapus.setFocusable(false);
        jButtonHapus.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonHapus.setMaximumSize(new java.awt.Dimension(53, 30));
        jButtonHapus.setMinimumSize(new java.awt.Dimension(53, 30));
        jButtonHapus.setPreferredSize(new java.awt.Dimension(53, 30));
        jButtonHapus.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHapusActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonHapus);

        recordLabel.setBackground(new java.awt.Color(0, 0, 0));
        recordLabel.setForeground(new java.awt.Color(0, 51, 255));
        recordLabel.setText("1 of 9999");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(recordLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(recordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnExite.setText("Exit Ctrl + X");

        btnCencel.setText("Cancel");
        btnCencel.setMinimumSize(new java.awt.Dimension(92, 23));

        cmbAktif.setText("Data Aktif");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(cmbAktif)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCencel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExite)
                .addGap(29, 29, 29))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCencel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmbAktif))
        );

        jtKode.setMaximumSize(new java.awt.Dimension(64, 22));
        jtKode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtKodeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtKodeFocusLost(evt);
            }
        });

        lblKode.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblKode.setText("Kode :");
        lblKode.setMaximumSize(new java.awt.Dimension(33, 22));
        lblKode.setMinimumSize(new java.awt.Dimension(33, 22));
        lblKode.setPreferredSize(new java.awt.Dimension(33, 22));

        jButton11.setText("jButton11");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        lblKode1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblKode1.setText("Nama :");
        lblKode1.setMaximumSize(new java.awt.Dimension(33, 22));
        lblKode1.setMinimumSize(new java.awt.Dimension(33, 22));
        lblKode1.setPreferredSize(new java.awt.Dimension(33, 22));

        jtNama.setMaximumSize(new java.awt.Dimension(64, 22));
        jtNama.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtNamaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtNamaFocusLost(evt);
            }
        });

        jEditorPane1.setColumns(20);
        jEditorPane1.setRows(5);
        jScrollPane1.setViewportView(jEditorPane1);

        lblKode2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblKode2.setText("Keterangan :");
        lblKode2.setMaximumSize(new java.awt.Dimension(33, 22));
        lblKode2.setMinimumSize(new java.awt.Dimension(33, 22));
        lblKode2.setPreferredSize(new java.awt.Dimension(33, 22));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblKode, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblKode1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblKode2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtKode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton11))
                    .addComponent(lblKode, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblKode1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblKode2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jMenu1.setText("System");

        jMenuItem1.setText("Close Ctrl + X");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtKodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtKodeFocusGained
        // TODO add your handling code here:
        if (jtKode.getText().equals("Kode")) {
            jtKode.setText("");
            jtKode.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jtKodeFocusGained

    private void jtKodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtKodeFocusLost
        // TODO add your handling code here:
        if (jtKode.getText().equals("")) {
            jtKode.setText("Kode");
            jtKode.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_jtKodeFocusLost

    private void jtNamaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtNamaFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtNamaFocusGained

    private void jtNamaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtNamaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jtNamaFocusLost

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        String action = jLabel1.getText(); // Mendapatkan teks dari JLabel
         if (action.equals("[Tambah]")) {
        simpan(); // Jalankan metode simpan jika teks adalah "Tambah"
        } else if (action.equals("[Ubah]")) {
             ubahdata(); // Jalankan metode ubah jika teks adalah "Ubah"
       } else {
        JOptionPane.showMessageDialog(null, "Aksi tidak dikenali: " + action);
    }
         awal();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void jButtonTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTambahActionPerformed
        // TODO add your handling code here:
        tambah();
    }//GEN-LAST:event_jButtonTambahActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        next();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        previous();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        data_awal();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        data_terakhir();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButtonUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUbahActionPerformed
        // TODO add your handling code here:
        ubah();
    }//GEN-LAST:event_jButtonUbahActionPerformed

    private void jButtonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHapusActionPerformed
        // TODO add your handling code here:
        PreparedStatement pstmt = null; // Menggunakan PreparedStatement untuk delete
    try {
        // Query SQL untuk delete data dari tabel
        String sqlDelete = "DELETE FROM mkategori WHERE Kode=?";
        
        // Mendapatkan nilai dari komponen-komponen UI
        String kode = jtKode.getText(); // Kode yang akan dihapus
        
        // Membuat PreparedStatement untuk delete
        pstmt = conn.prepareStatement(sqlDelete);
        pstmt.setString(1, kode); // Hapus data berdasarkan kode
        
        // Eksekusi query SQL untuk delete data
        int hasil = pstmt.executeUpdate();
        
        if (hasil > 0) {
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus dari database.");
        } else {
            JOptionPane.showMessageDialog(null, "Gagal menghapus data dari database. Data dengan kode " + kode + " tidak ditemukan.");
        }
        awal();
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
    } finally {
        try {
            // Menutup PreparedStatement
            if (pstmt != null) {
                pstmt.close();
            }
            // Koneksi tidak ditutup di sini agar dapat digunakan di tempat lain
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    }//GEN-LAST:event_jButtonHapusActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        openKategoriDialog();
    }//GEN-LAST:event_jButton11ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmMstKategori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMstKategori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMstKategori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMstKategori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMstKategori().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IDKategori;
    private javax.swing.JButton btnCencel;
    private javax.swing.JButton btnExite;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JCheckBox cmbAktif;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButtonHapus;
    private javax.swing.JButton jButtonTambah;
    private javax.swing.JButton jButtonUbah;
    private javax.swing.JTextArea jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JTextField jtKode;
    private javax.swing.JTextField jtNama;
    private javax.swing.JLabel lblKode;
    private javax.swing.JLabel lblKode1;
    private javax.swing.JLabel lblKode2;
    private javax.swing.JLabel recordLabel;
    // End of variables declaration//GEN-END:variables
}
