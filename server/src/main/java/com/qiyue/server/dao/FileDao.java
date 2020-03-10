package com.qiyue.server.dao;

import com.qiyue.server.model.FileModel;
import com.qiyue.server.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FileDao {

    /**
     * 创建表
     *
     * @throws SQLException
     */
    public void createTable() throws SQLException {
        Connection conn = DBUtil.getConnection();
        String sql = "CREATE TABLE file ( " +
                "id VARCHAR(100), " +
                "name VARCHAR(100), " +
                "type VARCHAR(100), " +
                "size VARCHAR(100), " +
                "location VARCHAR(200), " +
                "envelope VARCHAR(200), " +
                "time TIMESTAMP )";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.executeUpdate();
        ptmt.close();
        conn.close();
    }

    /**
     * 删除表
     */
    public void dropTable() throws SQLException {
        Connection conn = DBUtil.getConnection();
        String sql = "drop table file";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.executeUpdate();
        ptmt.close();
        conn.close();
    }

    /**
     * 添加
     *
     * @param fileModel
     * @throws SQLException
     * @throws ParseException
     */
    public void insert(FileModel fileModel) throws SQLException {
        Connection conn = DBUtil.getConnection();
        String sql = "insert into file(id, name, type, location, size, envelope, time) values (?,?,?,?,?,?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, fileModel.getId());
        ptmt.setString(2, fileModel.getName());
        ptmt.setString(3, fileModel.getType());
        ptmt.setString(4, fileModel.getLocation());
        ptmt.setString(5, fileModel.getSize());
        ptmt.setString(6, fileModel.getEnvelope());
        ptmt.setTimestamp(7, fileModel.getTime());
        ptmt.executeUpdate();
        ptmt.close();
        conn.close();
    }


    /**
     * 查询所有
     *
     * @return
     * @throws SQLException
     */
    public List<FileModel> findAll() throws SQLException {
        List<FileModel> list = new ArrayList<FileModel>();
        Connection conn = DBUtil.getConnection();
        String sql = "select id, name, type, location,size,envelope,time from file";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        while (rs.next()) {
            FileModel fileModel = new FileModel();
            fileModel.setId(rs.getString("id"));
            fileModel.setName(rs.getString("name"));
            fileModel.setType(rs.getString("type"));
            fileModel.setLocation(rs.getString("location"));
            fileModel.setSize(rs.getString("size"));
            fileModel.setEnvelope(rs.getString("envelope"));
            fileModel.setTime(rs.getTimestamp("time"));
            list.add(fileModel);
        }
        rs.close();
        ptmt.close();
        conn.close();
        return list;
    }

    /**
     * 根据ID查询
     *
     * @return
     * @throws SQLException
     */
    public FileModel findById(String id) throws SQLException {
        Connection conn = DBUtil.getConnection();
        String sql = "select id,name, type, location,size,envelope,time from file where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, id);
        ResultSet rs = ptmt.executeQuery();
        FileModel fileModel = null;
        while (rs.next()) {
            fileModel = new FileModel();
            fileModel.setId(rs.getString("id"));
            fileModel.setName(rs.getString("name"));
            fileModel.setType(rs.getString("type"));
            fileModel.setLocation(rs.getString("location"));
            fileModel.setSize(rs.getString("size"));
            fileModel.setEnvelope(rs.getString("envelope"));
            fileModel.setTime(rs.getTimestamp("time"));
        }
        rs.close();
        ptmt.close();
        conn.close();
        return fileModel;
    }

    /**
     * 根据ID查询
     *
     * @return
     * @throws SQLException
     */
    public void deleteById(String id) throws SQLException {
        Connection conn = DBUtil.getConnection();
        String sql = "delete from file where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, id);
        ptmt.executeUpdate();
        ptmt.close();
        conn.close();
    }
}
