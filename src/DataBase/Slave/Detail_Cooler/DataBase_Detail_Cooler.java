/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Slave.Detail_Cooler;

//Import  Database Connector and Detail_Cooler_Models Class
import DataBaseConnector.Database_Connection;
import Models.Slave.Detail_Cooler.Detail_Cooler_Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mariq
 */
public class DataBase_Detail_Cooler {

    public int Total;
    private Detail_Cooler_Models dcoolerm = new Detail_Cooler_Models();

    public Detail_Cooler_Models getDetail_Cooler_Model() {
        return (dcoolerm);
    }

    public void setDetail_Cooler_Model(Detail_Cooler_Models lrs) {
        dcoolerm = lrs;
    }

    public ObservableList<Detail_Cooler_Models> Load_Data(String kode) {
        try {
            ObservableList<Detail_Cooler_Models> tableData = FXCollections.observableArrayList();
            Database_Connection con = new Database_Connection();
            con.Open_Connection();
            con.statement = con.Database_UTS_Conection.createStatement();
            ResultSet rs = con.statement.executeQuery("Select dr.Nomor_Transaksi,clr.ID_COOLER,clr.Nama_Cooler,dr.Quantity,clr.Harga "
                    + "from detail_cooler dr join cooler clr on(dr.ID_COOLER=clr.ID_COOLER) WHERE Nomor_Transaksi LIKE '" + kode + "'");
            int i = 1;
            while (rs.next()) {
                Detail_Cooler_Models d = new Detail_Cooler_Models();
                d.setNomor_Transaksi(rs.getString("Nomor_Transaksi"));
                d.setID_COOLER(rs.getString("ID_COOLER"));
                d.setNama_Cooler(rs.getString("Nama_Cooler"));
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from detail_cooler where Nomor_Transaksi = '" + nomor + "'");
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
            con.preparedStatement = con.Database_UTS_Conection.prepareStatement("insert into  detail_cooler (Nomor_Transaksi, ID_COOLER,Quantity) values (?,?,?)");
            con.preparedStatement.setString(1, getDetail_Cooler_Model().getNomor_Transaksi());
            con.preparedStatement.setString(2, getDetail_Cooler_Model().getID_COOLER());
            con.preparedStatement.setInt(3, getDetail_Cooler_Model().getQuantity());
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
            con.preparedStatement = con.Database_UTS_Conection.prepareStatement("delete from detail_cooler where Nomor_Transaksi  = ? and ID_COOLER = ?");
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
            con.preparedStatement = con.Database_UTS_Conection.prepareStatement("delete from detail_cooler where Nomor_Transaksi = ?");
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
            con.preparedStatement = con.Database_UTS_Conection.prepareStatement("update detail_cooler set ID_COOLER = ?, Quantity = ?  where  Nomor_Transaksi= ? ");
            con.preparedStatement.setString(1, getDetail_Cooler_Model().getID_COOLER());
            con.preparedStatement.setInt(2, getDetail_Cooler_Model().getQuantity());
            con.preparedStatement.setString(3, getDetail_Cooler_Model().getNomor_Transaksi());
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

    public ObservableList<Detail_Cooler_Models> Search_Detil_Cooler(String kode) {
        try {
            Total = 0;
            ObservableList<Detail_Cooler_Models> tableData;
            tableData = FXCollections.observableArrayList();
            Database_Connection con = new Database_Connection();
            con.Open_Connection();
            con.statement = con.Database_UTS_Conection.createStatement();
            ResultSet rs = con.statement.executeQuery("Select dr.Nomor_Transaksi,clr.ID_COOLER,clr.Nama_Cooler,dr.Quantity,clr.Harga "
                    + "from detail_cooler dr join cooler clr on(dr.ID_COOLER=clr.ID_COOLER) WHERE Nomor_Transaksi LIKE '" + kode + "'");
            int i = 1;
            while (rs.next()) {
                Detail_Cooler_Models d = new Detail_Cooler_Models();
                d.setNomor_Transaksi(rs.getString("Nomor_Transaksi"));
                d.setID_COOLER(rs.getString("ID_COOLER"));
                d.setNama_Cooler(rs.getString("Nama_Cooler"));
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
