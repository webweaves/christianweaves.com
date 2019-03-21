package com.christianweaves.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GetResource", urlPatterns = {"/getResource"})
public class GetResource extends HttpServlet {

	private static final long serialVersionUID = 6773115539313943807L;

	ResourceBundle bundle = ResourceBundle.getBundle("Messages");
	private String fileLocation = bundle.getString("uploadResourceFolder");
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String filename = (String) request.getParameter("file");
		if (filename == null || filename.trim().length() == 0) {
			return;
		}
		
		File file = new File(fileLocation + File.pathSeparator + filename);
		if (!file.exists()) {
			return;
		}
        response.setContentType("image/png");
        BufferedImage bi = ImageIO.read(new FileInputStream(file));
        OutputStream os = response.getOutputStream();
        ImageIO.write(bi, "png", os);
    }
}