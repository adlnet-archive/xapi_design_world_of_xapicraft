package com.brindlewaye.xapi.servlet;

import com.rusticisoftware.tincan.RemoteLRS;
import com.rusticisoftware.tincan.Statement;
import com.rusticisoftware.tincan.TCAPIVersion;
import com.rusticisoftware.tincan.json.StringOfJSON;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.UUID;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * StatementSenderServlet servlet mapped with (/save.do) url
 * class retrieve json string from request parameter and
 * send and receive statement from lrs.
 */

public class StatementSenderServlet extends HttpServlet{
     RemoteLRS remote=null;
     
     /*
      * Init method get Init param of servlet defined in web.xml 
      * and initialize REmoteLRS with required attributes. 
      */
     public void init()
     {
         String endpoint= getInitParameter("endpoint");
         String username= getInitParameter("username");
         String password=getInitParameter("password");
         if(endpoint!=null && username != null && password != null)
         {
             remote=new RemoteLRS();
             try {
                 remote.setEndpoint(endpoint);
                 remote.setUsername(username);
                 remote.setPassword(password);
                 remote.setVersion(TCAPIVersion.V100);
             } catch (MalformedURLException ex) {
                 ex.printStackTrace();
             }
         }
         
     }
     
     
     
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        process(request, response);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        process(request, response);
    }

    /*
     * process() is user defined method with request & response 
     * call from doPost/doGet methods.
     */
    private void process(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        String jsonString = request.getParameter("jsontext");
        try {
            UUID uuid = sendStatement(jsonString, null);    // send statement in LRS.
            Statement statement= retrieveStatement(uuid);  // retrieve statement from LRS.
            request.setAttribute("jsonString", statement.toJSON());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            out.println("Error   "+e.getMessage());
        }
        

    }
    
    // Method for retrieve statement from LRS.
    public Statement retrieveStatement(UUID uuid) {
		if (remote == null) return null;
		try {
			Statement stmt = remote.retrieveStatement(uuid.toString());
			return stmt;
		} catch (Exception e) {
			e.printStackTrace();
                        return null;
		}
    }
    
    // method for send Statement in LRS.
    public UUID sendStatement(String statement, UUID id)  {
		StringOfJSON stringOfJSON = new StringOfJSON(statement);
		Statement stmt = null;
		UUID uuid = null;
		try {
			stmt = new Statement(stringOfJSON);
			stmt.setVersion(TCAPIVersion.V100);
			stmt.setId(id);
		} catch (IOException e) {
			e.printStackTrace();
			return uuid;
		}catch (java.net.URISyntaxException e)
                {
                    e.printStackTrace();
                    return  uuid;
                }
               	
		if (remote == null) return null;
		
		try {
			uuid = remote.saveStatement(stmt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uuid;
	}
}
