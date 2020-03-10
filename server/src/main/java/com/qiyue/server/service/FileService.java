package com.qiyue.server.service;

import com.qiyue.server.model.FileModel;

import java.sql.SQLException;
import java.util.List;

public interface FileService {

    /**
     * 获得所有的文件
     * @return
     */
    List<FileModel> findAll() throws SQLException;

    /**
     * 根据ID获取文件
     * @param id
     * @return
     */
    FileModel findById(String id) throws SQLException;

    /**
     * 添加
     * @param fileModel
     */
    void insert(FileModel fileModel) throws SQLException;
}
