package org.zerock.web;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import static org.imgscalr.Scalr.*;

/**
 * Servlet implementation class Web
 */
@WebServlet("/upload1")
@MultipartConfig(
	maxFileSize=1024*1024*50,      	// 50 MB
	maxRequestSize=1024*1024*100)   	// 100 MB
public class UploadEx1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadEx1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Collection<Part> parts = request.getParts();
		
		parts.stream()
		.filter(part -> part.getContentType() != null) //필터를 해줄수 있음. 널이 아닌 것들만...
		.forEach(part -> { 
			
			
			
			System.out.println(part.getContentType());
			System.out.println(part.getSubmittedFileName());
			System.out.println("---------------------------");
			
			String uploadName = UUID.randomUUID()+"_"+ part.getSubmittedFileName(); // 이름에 랜덤UUID를 줘서 같은이름도 받게 가능.
			
			try {
				InputStream in = part.getInputStream();
				FileOutputStream fos = new FileOutputStream("c:\\zzz\\upload\\" + uploadName);
				
				IOUtils.copy(in, fos);
				
				if(ExtChecker.check(uploadName)) {
					
				
				
				//Make Thumbnail image
				BufferedImage bImage = ImageIO.read(new FileInputStream("c:\\zzz\\upload\\" + uploadName));
				BufferedImage thumbnail = Scalr.resize(bImage,
                        Method.SPEED,
                        Mode.FIT_TO_WIDTH,
                        150,
                        100,
                        OP_ANTIALIAS);
				
				FileOutputStream thfos = new FileOutputStream("c:\\zzz\\upload\\s_" + uploadName);
				ImageIO.write(thumbnail, "jpg", thfos);
				thfos.close();
				}
				
				
				in.close();
				fos.close();					
				
			}catch(IOException e){
				e.printStackTrace();
			}
			
		});
		
	}

}
