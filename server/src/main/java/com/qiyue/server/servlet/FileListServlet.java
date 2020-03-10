package com.qiyue.server.servlet;

import com.alibaba.fastjson.JSON;
import com.qiyue.server.model.FileModel;
import com.qiyue.server.service.FileService;
import com.qiyue.server.service.impl.FileServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

/**
 * 文件列表
 * @author saysky
 * @date 2020/3/1 1:29 下午
 */
@WebServlet(name = "FileListServlet", urlPatterns = "/file/list")
public class FileListServlet  extends HttpServlet {

    /**
     * 注入FileService
     */
    FileService fileService = new FileServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        OutputStream out = response.getOutputStream();
        try {
            List<FileModel> fileModelList = fileService.findAll();
            out.write(JSON.toJSONString(fileModelList).getBytes());
            out.flush();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}
