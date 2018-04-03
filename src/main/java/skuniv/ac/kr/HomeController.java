package skuniv.ac.kr;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	List<String> fileList=new ArrayList<String>();
	
	
	@RequestMapping(value = "/home")
	public String home(HttpServletRequest request,Model model) {
		
		System.out.println("home");
		return "home";
	}
	@RequestMapping(value = "/filedown")
	public String filedown(HttpServletRequest request,Model model) {
		String fileName=request.getParameter("fileName");
		System.out.println("filedown");
		request.setAttribute("fileName", fileName);
		return "filedown";
	}
	@RequestMapping(value = "/down")
	public String down(HttpServletRequest request,Model model) {
		Date today = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd-mm-ss");
        String firstFileName=date.format(today);
        String fileNameResult=firstFileName+"_result.txt";
		BufferedWriter bw=null;
		BufferedReader br1 = null;
		BufferedReader br2 = null;
		BufferedReader br3 = null;
		BufferedReader br4 = null;
		BufferedReader br5 = null;
		
		try{
			bw = new BufferedWriter(new FileWriter("C:\\Users\\gunyoungkim\\file_after\\"+fileNameResult));
			br1 = new BufferedReader(new FileReader(fileList.get(0)));
			br2 = new BufferedReader(new FileReader(fileList.get(1)));
			br3 = new BufferedReader(new FileReader(fileList.get(2)));
			br4 = new BufferedReader(new FileReader(fileList.get(3)));
			br5 = new BufferedReader(new FileReader(fileList.get(4)));
			 
	        String str1 = br1.readLine();
	        String str2 = br2.readLine();
	        String str3 = br3.readLine();
	        String str4 = br4.readLine();
	        String str5 = br5.readLine();
	        
	        String[] voltage=str1.split(",");
	        String[] degree=str1.split(",");
	        String[] period=str1.split(",");
	        String[] buffer=str1.split(",");
	        String[] battery=str1.split(",");
	        int i=0;        
	         
	       while(true) {
	    	  bw.write(voltage[i]+",");
	    	  bw.write(degree[i]+",");
	    	  bw.write(period[i]+",");
	    	  bw.write(buffer[i]+",");
	    	  bw.write(battery[i]+",");
	    	  i++;
	    	  if(i==voltage.length) break;
	    	  
	         }
			
			
		}catch(IOException ioe){
			ioe.getMessage();
		}finally{
			try{
				bw.close();
				br1.close();
				br2.close();
				br3.close();
				br4.close();
				br5.close();
				
			}catch(Exception e){			
			}
		}
		fileList.clear();
		System.out.println(fileList.size());
        
        return "redirect:/filedown?fileName="+fileNameResult;
	}
	
	public void upload(MultipartFile fn,String fileName) {
		String rootPath = "C:\\Users\\gunyoungkim";
		   
        
        File dir = new File(rootPath + File.separator + "file_before");
        
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);        
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
        String path=dir.getAbsolutePath() + File.separator + fileName;
        //path=path.replaceAll("\\", "\\");
        fileList.add(path);
	}
	
	@RequestMapping(value = "/photo", method=RequestMethod.POST)
    public String uploadImageCtlr(ModelMap model,
                                  HttpServletRequest request,
                                  MultipartRequest file
                                  ) throws IOException{
    	
		request.setCharacterEncoding("UTF-8");
		
		MultipartFile voltege_fn=file.getFile("voltage");
		MultipartFile degree_fn=file.getFile("degree");
		MultipartFile battery_fn=file.getFile("battery");
		MultipartFile buffer_fn=file.getFile("buffer");
		MultipartFile period_fn=file.getFile("period");
		
		String before_fileName;
		Date today = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd-mm-ss");
        String firstFileName=date.format(today);
        
        if(!(voltege_fn.getOriginalFilename().equals(""))) {
             upload(voltege_fn,firstFileName+"_voltage.txt" );
        }
        if(!(degree_fn.getOriginalFilename().equals(""))) {
        	 upload(degree_fn,firstFileName+"_degree.txt" );
		}
		if(!(battery_fn.getOriginalFilename().equals(""))) {
			upload(battery_fn,firstFileName+"_battery.txt" );
		}
		if(!(buffer_fn.getOriginalFilename().equals(""))) {
			upload(buffer_fn,firstFileName+"_buffer.txt" );
		}
		if(!(period_fn.getOriginalFilename().equals(""))) {
			upload(period_fn,firstFileName+"_period.txt" );
		}
		
		for(int i=0;i<fileList.size();i++) {
			System.out.println(fileList.get(i).toString());
		}
		return "home";
		

    }
	
}
