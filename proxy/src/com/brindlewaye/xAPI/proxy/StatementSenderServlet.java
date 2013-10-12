package com.brindlewaye.xAPI.proxy;

import com.rusticisoftware.tincan.Statement;
import java.util.UUID;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.*;
import java.io.File;

public class StatementSenderServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request,HttpServletResponse response)
    {
        process(request, response);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response)
    {
        process(request, response);
    }

    private void process(HttpServletRequest request,HttpServletResponse response)
    {
        Logger logger = Logger.getLogger(StatementSenderServlet.class.getName());  
        FileHandler fh = null;
        try 
        {
            logger.setUseParentHandlers(false);
// Creating a 100KB log file. When filled, it should start from the beginning.
            fh  = new FileHandler(getServletContext().getRealPath(File.separator)+"StatementSenderLog.txt", 102400, 1,true);
            logger.addHandler(fh);
            
            SimpleFormatter formatter = new SimpleFormatter();  
            fh.setFormatter(formatter);  
            
            String jsonString = request.getParameter("jsontext");
            StatementSender ss = new StatementSender();
// calling ss.initializeFromClasspath() which picks the lrs.properties from the classpath
            ss.initializeFromClasspath();
            
            UUID uuid = ss.sendStatement(jsonString, null);
            Statement st = ss.retrieveStatement(uuid);
            String s = st.toJSON(true);
        
            request.setAttribute("jsonString", s);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        catch (Exception e)
        {
            logger.info(e.toString());
        }
        finally
        {
            if (fh!=null)
                    fh.close();
        }
    }
}
