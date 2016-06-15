package servlet;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DaoImpl.InventoryImpl;
import Idao.InventoryIDao;
import model.*;

/**
 * Servlet implementation class InstrumentService
 */
@WebServlet("/InstrumentService")
public class SearchGuitar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchGuitar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String price=new String (request.getParameter("price").getBytes("ISO-8859-1"),"UTF-8");
		String model=new String (request.getParameter("model").getBytes("ISO-8859-1"),"UTF-8");
		String type=new String (request.getParameter("type").getBytes("ISO-8859-1"),"UTF-8");
		String backwood=new String (request.getParameter("backwood").getBytes("ISO-8859-1"),"UTF-8");
		String topwood=new String (request.getParameter("topwood").getBytes("ISO-8859-1"),"UTF-8");
		String builder = null;
		String serialnumber = null;
		//Integer inventory;
		
		initializeInventory();
	    GuitarSpec whatErinLikes = new GuitarSpec(Builder.valueOf(builder), model, Type.valueOf(type), Integer.parseInt(serialnumber), Wood.valueOf(backwood), Wood.valueOf(topwood));
	   
	    List matchingGuitars = Inventory.search(whatErinLikes);
	   
	    PrintWriter out=response.getWriter();
	    if (!matchingGuitars.isEmpty()) {
	      System.out.println("Erin, you might like these guitars:");
	      for (Iterator i = matchingGuitars.iterator();
	    		  i.hasNext(); ) {
	        Guitar guitar = (Guitar)i.next();
	        GuitarSpec spec = guitar.getSpec();
	        out.println("  We have a " +
	          spec.getBuilder() + " " + spec.getModel() + " " +
	          spec.getType() + " guitar:\n     " +
	          spec.getBackWood() + " back and sides,\n     " +
	          spec.getTopWood() + " top.\n  You can have it for only $" +
	          guitar.getPrice() + "!\n  -------------------------------------");
	      }
	    } else {
	     out.println("Sorry, Erin, we have nothing for you.");
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private static void initializeInventory(){
		//面向接口：
			InventoryIDao inventorydao = new InventoryImpl();
			
		try {
			boolean a= inventorydao.findAll();
			System.out.print(a);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}

}
