/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.Slave.Detail_GPU;

//Import Data Base Connector and Detail_GPU_Models
import DataBaseConnector.Database_Connection;
import Models.Slave.Detail_GPU.Detail_GPU_Models;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mariq
 */
public class DataBase_Detail_GPU {

    public int Total;
    private Detail_GPU_Models dgpum = new Detail_GPU_Models();

    public Detail_GPU_Models getDetail_CPU_Models() {
        return (dgpum);
    }

    public void setDetail_GPU_Models(Detail_GPU_Models gus) {
        dgpum = gus;
    }

    public ObservableList<Detail_GPU_Models> Load_Data(String kode) {
        try {
            ObservableList<Detail_GPU_Models> tableData = FXCollections.observableArrayList();
            Database_Connection con = new Database_Connection();
            con.Open_Connection();
            con.statement = con.Database_UTS_Conection.createStatement();
            ResultSet rs = con.statement.executeQuery("Select dg.Nomor_Transaksi,gpu.ID_GPU,cpu.Nama_GPU,dg.Quantity,gpu.Harga "
                    + "from detail_gpu dg join gpu gpu on(dg.ID_GPU=gpu.ID_GPU) WHERE Nomor_Transaksi LIKE '" + kode + "'");
            int i = 1;
            while (rs.next()) {
                Detail_GPU_Models d = new Detail_GPU_Models();
                d.setNomor_Transaksi(rs.getString("Nomor_Transaksi"));
                d.setID_GPU(rs.getString("ID_GPU"));
                d.setNama_GPU(rs.getString("Nama_GPU"));
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from detail_gpu where Nomor_Transaksi = '" + nomor + "'");
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
            con.preparedStatement = con.Database_UTS_Conection.prepareStatement("insert into  detail_gpu (Nomor_Transaksi,ID_GPU,Quantity) values (?,?,?)");
            con.preparedStatement.setString(1, getDetail_CPU_Models().getNomor_Transaksi());
            con.preparedStatement.setString(2, getDetail_CPU_Models().getID_GPU());
            con.preparedStatement.setInt(3, getDetail_CPU_Models().getQuantity());
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
            con.preparedStatement = con.Database_UTS_Conection.prepareStatement("delete from detail_gpu where Nomor_Transaksi  = ? and ID_GPU = ?");
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
            con.preparedStatement = con.Database_UTS_Conection.prepareStatement("delete from detail_gpu where Nomor_Transaksi = ?");
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
            con.preparedStatement = con.Database_UTS_Conection.prepareStatement("update detail_gpu set ID_GPU = ?, Quantity = ?  where  Nomor_Transaksi= ? ");
            con.preparedStatement.setString(1, getDetail_CPU_Models().getID_GPU());
            con.preparedStatement.setInt(2, getDetail_CPU_Models().getQuantity());
            con.preparedStatement.setString(3, getDetail_CPU_Models().getNomor_Transaksi());
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

    public ObservableList<Detail_GPU_Models> Search_Detil_GPU(String kode) {
        try {
            Total = 0;
            ObservableList<Detail_GPU_Models> tableData;
            tableData = FXCollections.observableArrayList();
            Database_Connection con = new Database_Connection();
            con.Open_Connection();
            con.statement = con.Database_UTS_Conection.createStatement();
            ResultSet rs = con.statement.executeQuery("Select dg.Nomor_Transaksi,gpu.ID_GPU,cpu.Nama_GPU,dg.Quantity,gpu.Harga "
                    + "from detail_gpu dg join gpu gpu on(dg.ID_GPU=gpu.ID_GPU) WHERE Nomor_Transaksi LIKE '" + kode + "'");
            
            
            int i = 1;
            while (rs.next()) {
                Detail_GPU_Models d = new Detail_GPU_Models();
                d.setNomor_Transaksi(rs.getString("Nomor_Transaksi"));
                d.setID_GPU(rs.getString("ID_GPU"));
                d.setNama_GPU(rs.getString("Nama_GPU"));
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
