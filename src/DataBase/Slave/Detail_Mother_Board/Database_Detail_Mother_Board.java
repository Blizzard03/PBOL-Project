/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Slave.Detail_Mother_Board;

//Import Data Base Connector and Detail_Mother_Board_Models
import DataBaseConnector.Database_Connection;
import Models.Slave.Detail_Mother_Board.Detail_Mother_Board_Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mariq
 */
public class Database_Detail_Mother_Board {

    public int Total;
    private Detail_Mother_Board_Models dmobom = new Detail_Mother_Board_Models();

    public Detail_Mother_Board_Models getDetail_Mother_Board_Models() {
        return (dmobom);
    }

    public void setDetail_Mother_Board_Models(Detail_Mother_Board_Models dmbm) {
        dmobom = dmbm;
    }

    public ObservableList<Detail_Mother_Board_Models> Load_Data(String kode) {
        try {
            ObservableList<Detail_Mother_Board_Models> tableData = FXCollections.observableArrayList();
            Database_Connection con = new Database_Connection();
            con.Open_Connection();
            con.statement = con.Database_UTS_Conection.createStatement();
            ResultSet rs = con.statement.executeQuery("Select dmb.Nomor_Transaksi,mobo.ID_MOTHER_BOARD,mobo.Nama_Mother_Board,dmb.Quantity,mobo.Harga "
                    + "from detail_mother_board dmb join mother_board mobo on(dmb.IID_MOTHER_BOARD=mobo.ID_MOTHER_BOARD) WHERE Nomor_Transaksi LIKE '" + kode + "'");
            int i = 1;
            while (rs.next()) {
                Detail_Mother_Board_Models d = new Detail_Mother_Board_Models();
                d.setNomor_Transaksi(rs.getString("Nomor_Transaksi"));
                d.setID_MOTHER_BOARD(rs.getString("ID_MOTHER_BOARD"));
                d.setNama_Mother_Board(rs.getString("Nama_Mother_Board"));
                d.setQuantity(rs.getInt("Quantity"));
                d.setHarga(rs.getInt("Harga"));

                int Total = 0;
                int Quantity = rs.getInt("Quantity");
                int Harga = rs.getInt("Harga");
                Total = Quantity * Harga;
                d.setTotal(Total);
                tableData.add(d);
                i++;
            }
            con.Discconnect();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int Validate(String nomor) {
        int num = 0;
        try {
            Database_Connection con = new Database_Connection();
            con.Open_Connection();
            con.statement = con.Database_UTS_Conection.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from detail_mother_board where Nomor_Transaksi = '" + nomor + "'");
            while (rs.next()) {
                num = rs.getInt("jml");
            }
            con.Discconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    public boolean Insert_Data() {
        boolean Success = false;
        Database_Connection con = new Database_Connection();
        try {
            con.Open_Connection();
            con.preparedStatement = con.Database_UTS_Conection.prepareStatement("insert into  detail_mother_board (Nomor_Transaksi, ID_MOTHER_BOARD,Quantity) values (?,?,?)");
            con.preparedStatement.setString(1, getDetail_Mother_Board_Models().getNomor_Transaksi());
            con.preparedStatement.setString(2, getDetail_Mother_Board_Models().getID_MOTHER_BOARD());
            con.preparedStatement.setInt(3, getDetail_Mother_Board_Models().getQuantity());
            con.preparedStatement.executeUpdate();
            Success = true;
        } catch (Exception e) {
            e.printStackTrace();
            Success = false;
        } finally {
            con.Discconnect();
            return Success;
        }
    }

    public boolean Delete_Data(String nomor, String id) {
        boolean Success = false;
        Database_Connection con = new Database_Connection();
        try {
            con.Open_Connection();;
            con.preparedStatement = con.Database_UTS_Conection.prepareStatement("delete from detail_mother_board where Nomor_Transaksi  = ? and ID_MOTHER_BOARD = ?");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.setString(2, id);
            con.preparedStatement.executeUpdate();
            Success = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.Discconnect();
            return Success;
        }
    }

    public boolean Delete_All_Data(String nomor) {
        boolean Success = false;
        Database_Connection con = new Database_Connection();
        try {
            con.Open_Connection();;
            con.preparedStatement = con.Database_UTS_Conection.prepareStatement("delete from detail_mother_board where Nomor_Transaksi = ?");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();
            Success = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.Discconnect();
            return Success;
        }
    }

    public boolean Update_Data() {
        boolean Success = false;
        Database_Connection con = new Database_Connection();
        try {
            con.Open_Connection();
            con.preparedStatement = con.Database_UTS_Conection.prepareStatement("update detail_cooler set ID_MOTHER_BOARD = ?, Quantity = ?  where  Nomor_Transaksi= ? ");
            con.preparedStatement.setString(1, getDetail_Mother_Board_Models().getID_MOTHER_BOARD());
            con.preparedStatement.setInt(2, getDetail_Mother_Board_Models().getQuantity());
            con.preparedStatement.setString(3, getDetail_Mother_Board_Models().getNomor_Transaksi());
            con.preparedStatement.executeUpdate();
            Success = true;
        } catch (Exception e) {
            e.printStackTrace();
            Success = false;
        } finally {
            con.Discconnect();
            return Success;
        }
    }

    public ObservableList<Detail_Mother_Board_Models> Search_Detil_Cooler(String kode) {
        try {
            Total = 0;
            ObservableList<Detail_Mother_Board_Models> tableData;
            tableData = FXCollections.observableArrayList();
            Database_Connection con = new Database_Connection();
            con.Open_Connection();
            con.statement = con.Database_UTS_Conection.createStatement();
            ResultSet rs = con.statement.executeQuery("Select dmb.Nomor_Transaksi,mobo.ID_MOTHER_BOARD,mobo.Nama_Mother_Board,dmb.Quantity,mobo.Harga "
                    + "from detail_mother_board dmb join mother_board mobo on(dmb.IID_MOTHER_BOARD=mobo.ID_MOTHER_BOARD) WHERE Nomor_Transaksi LIKE '" + kode + "'");
            int i = 1;
            while (rs.next()) {
                Detail_Mother_Board_Models d = new Detail_Mother_Board_Models();
                d.setNomor_Transaksi(rs.getString("Nomor_Transaksi"));
                d.setID_MOTHER_BOARD(rs.getString("ID_MOTHER_BOARD"));
                d.setNama_Mother_Board(rs.getString("Nama_Mother_Board"));
                d.setQuantity(rs.getInt("Quantity"));
                d.setHarga(rs.getInt("Harga"));

                int Total = 0;
                int Quantity = rs.getInt("Quantity");
                int Harga = rs.getInt("Harga");
                Total = Quantity * Harga;
                d.setTotal(Total);
                tableData.add(d);
                i++;
            }
            con.Discconnect();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
