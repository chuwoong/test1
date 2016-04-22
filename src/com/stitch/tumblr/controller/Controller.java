package com.stitch.tumblr.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stitch.tumblr.command.CommandInterface;

/**
 * Servlet implementation class Controller
 */
@WebServlet(
		urlPatterns = { 
				"/Controller", 
				"*.do"
		}, 
		initParams = { 
				@WebInitParam(name = "propertyConfig", value = "command.properties")
		})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> commandMap = new HashMap<String, Object>();    
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		//initParams에서 propertyConfig의 값을 읽어옴
		String props = config.getInitParameter("propertyConfig");
		System.out.println("props = " + props);
		
		String realFolder = "/property"; //properties파일이 저장된 폴더
		System.out.println("realFolder = " + realFolder);
		
		//웹어플리케이션 루트 경로
		ServletContext context = config.getServletContext();
		System.out.println("context = " + context);
		
		//realFolder를 웹어플리케이션 시스템상의 절대경로로 변경
		String realPath = context.getRealPath(realFolder) +"\\"+props;
		System.out.println("realPath = " + realPath);				    
		
		//명령어와 처리클래스의 매핑정보를 저장할 Properties객체 생성
		Properties pr = new Properties();
		FileInputStream f = null;
		try{
			//command.properties파일의 내용을 읽어옴
			f = new FileInputStream(realPath);
			System.out.println("f = " + f);
			
			//command.properties의 내용을 Properties객체 pr에 저장
			pr.load(f);
			System.out.println("pr = " + pr);
			
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (f != null) try { f.close(); } catch(IOException ex) {}
		}
		//Set객체의 iterator()메소드를 사용해 Iterator객체를 얻어냄
		Iterator<?> keyIter = pr.keySet().iterator();
		System.out.println("keyIter = " + keyIter);
		
		//Iterator객체에 저장된 명령어와 처리클래스를 commandMap에 저장
		while( keyIter.hasNext() ) {
			String command = (String)keyIter.next();
//					System.out.println("command = " + command);
			
			String className = pr.getProperty(command);
//					System.out.println("className = " + className);
			
			try{
				Class<?> commandClass = Class.forName(className);
				System.out.println("commandClass = " + commandClass);
				
				Object commandInstance = commandClass.newInstance();
				System.out.println("commandInstance = " + commandInstance);
				
				commandMap.put(command, commandInstance);
//						System.out.println("commandMap = " + commandMap);
				
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}catch (InstantiationException e) {
				e.printStackTrace();
			}catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		requestPro(request, response);
	}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = null;
		CommandInterface com = null;
		try {
			String command = request.getRequestURI();
	        if(command.indexOf(request.getContextPath()) == 0)
	           command = command.substring(request.getContextPath().length());
	           System.out.println("getContextPath() : " + request.getContextPath());
	        
	        com = (CommandInterface)commandMap.get(command);  
	        view = com.requestPro(request, response);
	        System.out.println("view : " + view);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		//request.setAttribute("cont", view);
	    RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}
