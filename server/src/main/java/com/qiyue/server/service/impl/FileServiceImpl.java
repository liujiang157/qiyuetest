package com.qiyue.server.service.impl;

import com.qiyue.server.dao.FileDao;
import com.qiyue.server.model.FileModel;
import com.qiyue.server.service.FileService;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.Collections;
import java.util.List;

public class FileServiceImpl implements FileService {
    /**
     * 注入dao层
     */
    private static FileDao fileDao = new FileDao();

    @Override
    public List<FileModel> findAll() throws SQLException {
        try {
            return fileDao.findAll();
        } catch (SQLSyntaxErrorException e) {
            e.printStackTrace();
            System.out.println("请确保创建了 file 表，可通过 new FileDao().createTable() 创建");
        }
        return Collections.emptyList();
    }

    @Override
    public FileModel findById(String id) throws SQLException {
        return fileDao.findById(id);
    }

    @Override
    public void insert(FileModel fileModel) throws SQLException {
        fileDao.insert(fileModel);
    }

}
