package skuniv.ac.kr;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "/home")
	public String home(HttpServletRequest request,Model model) {
		
		System.out.println("home");
		return "home";
	}
	@RequestMapping(value = "/filedown")
	public String filedown(HttpServletRequest request,Model model) {
		String filename=request.getParameter("filename");
		System.out.println("filedown");
		request.setAttribute("filename", filename);
		return "filedown";
	}
	@RequestMapping(value = "/photo", method=RequestMethod.POST)
    public String uploadImageCtlr(ModelMap model,
                                  HttpServletRequest request,
                                  MultipartRequest file
                                  ) throws IOException{
    	
		request.setCharacterEncoding("UTF-8");
		
		//내부온도인지 전압인지 등등 받아옴 -> 이것으로 어떤 변환 로직을 할지 결정하면 된다.
		System.out.println(request.getParameter("select_way"));
		
		
		MultipartFile fn=file.getFile("photo");
    	
        String rootPath = "C:\\Users\\gunyoungkim";
        System.out.println(rootPath);
        File dir = new File(rootPath + File.separator + "file_before");
        System.out.println(dir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Date today = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd-mm-ss");

        File serverFile = new File(dir.getAbsolutePath() + File.separator + date.format(today)+"_before.txt");
        System.out.println(serverFile);
        String latestUploadPhoto = fn.getOriginalFilename();
        System.out.println(latestUploadPhoto);
        //write uploaded image to disk
        
        //우선 폴더를 두개를 만들어  before 폴더에 업로드 한다.
        try {
            try (InputStream is = fn.getInputStream();
                 BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                int i;
                while ((i = is.read()) != -1) {
                    stream.write(i);
                }
                stream.flush();
            }
        } catch (IOException e) {
            System.out.println("error : " + e.getMessage());
        }
        
        //before 폴더에 있는 파일을 열어 작업을 한다.
        StringBuilder sb=new StringBuilder();
        try {
            ////////////////////////////////////////////////////////////////
            BufferedReader in = new BufferedReader(new FileReader(dir.getAbsolutePath() + File.separator + date.format(today)+"_before.txt"));
            String s;

            while ((s = in.readLine()) != null) {
            	sb.append(s);
            }
            in.close();
            ////////////////////////////////////////////////////////////////
          } catch (IOException e) {
              System.err.println(e); // 에러가 있다면 메시지 출력
              System.exit(1);
          }
        
        //sb에 있는 값을 ","으로 StringTokenizer 혹은 split을 사용하여 변환 로직 수행!!!
        //수행한 값을 다시 파일 출력으로 만들어 준다 대신 이번엔  after 폴더로 저장 출력  
        System.out.println(sb);
        String filename=date.format(today)+"_after.txt";
        return "redirect:/filedown?filename="+filename;
        
        
    }
	
}
