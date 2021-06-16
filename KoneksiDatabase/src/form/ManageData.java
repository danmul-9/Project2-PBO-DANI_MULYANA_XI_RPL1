package form;

import classes.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ManageData extends javax.swing.JDialog {

    Connection koneksi;

    public ManageData(java.awt.Frame parent, boolean modal, String act, String nis) {
        super(parent, modal);
        initComponents();
        koneksi = DatabaseConnection.getKoneksi("localhost", "3306", "root", "", "db_sekolah");
    }

    public void SimpanData(){
        String nis      = txtNIS.getText();
        String nama     = txtNama.getText();
        String kelas    = cmbKelas.getSelectedItem().toString();
        String jurusan  = cmbJurusan.getSelectedItem().toString();
    
        try{
            Statement stmt = koneksi.createStatement();
            String query = "INSERT INTO t_siswa(nis,nama,kelas,jurusan) " + "VALUES('"+nis+"','"+nama+"','"+kelas+"','"+jurusan+"')";
            System.out.println(query);
            int berhasil = stmt.executeUpdate(query);
            if(berhasil == 1){
                JOptionPane.showMessageDialog(null, " Data Berhasil Dimasukkan");
            }else{
                JOptionPane.showMessageDialog(null, " Data Gagal Dimasukkan");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Pada Database");
        }
    }

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        SimpanData();
    }

    private void cmdTambahActionPerformed(java.awt.event.ActionEvent evt){
        ManageData tambahData = new ManageData(this, true);
        tambahData.setVisible(true)
    }

    int baris;
    private void tbl_siswaMouseClicked(java.awt.event.ActionEvent evt){
        baris = tbl_siswa.getSelectedRow();
    }

    private void cmdHapusActionPerformed(java.awt.event.ActionEvent evt){
        String idWhoWantToDelete = tbl_siswa.getValueAt(baris, 0).toString();
        try {
            Statement stmt = koneksi.createStatement();
            String query = "DELETE from t_siswa where nis = "+idWhoWantToDelete+";";
            int berhasil = stmt.executeUpdate(qurey);
            if(berhasil == 1){
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                dtm.getDataVector().removeAllElements();
                showData();
            } else {
                JOptionPane.showMessageDialog(null, "Data tidak berhasil di hapus")
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }


}

