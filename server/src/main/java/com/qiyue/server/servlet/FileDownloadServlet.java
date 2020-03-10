package com.qiyue.server.servlet;


import com.qiyue.server.model.FileModel;
import com.qiyue.server.service.FileService;
import com.qiyue.server.service.impl.FileServiceImpl;
import org.eclipse.jetty.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;

/**
 * 文件上传
 *
 * @author saysky
 * @date 2020/2/29 5:57 下午
 */
@WebServlet(name = "FileDownloadServlet", urlPatterns = "/file/download")
public class FileDownloadServlet extends HttpServlet {


    /**
     * 注入FileService
     */
    FileService fileService = new FileServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (StringUtil.isBlank(id)) {
            return;
        }
        try {
            FileModel fileModel = fileService.findById(id);
            if (fileModel != null) {
                String realPath = fileModel.getLocation();
                String fileName = fileModel.getName();
                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setContentType("text/html; charset=UTF-8");
                //获取文件输入流
                InputStream in = new FileInputStream(realPath);
                OutputStream out = response.getOutputStream();
                int nRead = 0;
                byte[] cache = new byte[1024];
                while ((nRead = in.read(cache)) != -1) {
                    out.write(cache, 0, nRead);
                    out.flush();
                }
                in.close();
                out.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
