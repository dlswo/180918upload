package org.zerock.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

/**
 * Servlet implementation class GetFileServlet
 */
@WebServlet("/getFile")
public class GetFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fileName = request.getParameter("fname");
		
		String ext = 
				fileName.substring(
						fileName.lastIndexOf(".") + 1);
		
		System.out.println("---------------------------");
		System.out.println(ext);
		
		if(ext.equals("jpg")) {
			response.setContentType("image/jpeg");
		}else {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment; filename="+ fileName); // 다운로드할때 이름을 지정해줘야됨 안하면 getFile로 됨.

		}
		OutputStream os = response.getOutputStream();
		InputStream in = new FileInputStream("c:\\zzz\\upload\\" + fileName);
		
		IOUtils.copy(in, os);
		
	}

}
