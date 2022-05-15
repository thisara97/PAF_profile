package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class profileAPI
 */
@WebServlet("/profileAPI")
public class profileAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	profile profileObj = new profile();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public profileAPI() {
        super();
        // TODO Auto-generated constructor stub
    }
    
 // Convert request parameters to a Map
 	private static Map getParasMap(HttpServletRequest request) {
 		// TODO Auto-generated method stub
 		Map<String, String> map = new HashMap<String, String>();
 		try
 		 {
 		 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
 		 String queryString = scanner.hasNext() ?
 		 scanner.useDelimiter("\\A").next() : "";
 		 scanner.close();
 		 String[] params = queryString.split("&");
 		 for (String param : params)
 		 {
 		 String[] p = param.split("=");
 		 map.put(p[0], p[1]);
 		 }
 		 }
 		catch (Exception e)
 		 {
 		 } 

 		return map;
 	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = profileObj.insertprofile(request.getParameter("uid"),
			request.getParameter("name"),
			request.getParameter("address"),
			request.getParameter("email"),
			request.getParameter("contact"));
			
			response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 Map paras = getParasMap(request);
		 String output = profileObj.updateProfile(paras.get("hididUpdate").toString(),
			paras.get("uid").toString(),
			paras.get("name").toString(),
			paras.get("address").toString(),
			paras.get("email").toString(),
			paras.get("contact").toString());
			response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		 String output = profileObj.deleteProfile(paras.get("id").toString());
		response.getWriter().write(output);
	}

}
