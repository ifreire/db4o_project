package br.com.ifreire.infra;

import com.db4o.ObjectContainer;
//import com.db4o.ObjectServer;
//import com.db4o.cs.Db4oClientServer;
//import com.db4o.cs.config.ServerConfiguration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import java.io.Console;
import java.io.IOException;

@SuppressWarnings("serial")
public class ServletExample extends HttpServlet {

    @SuppressWarnings("unused")
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ObjectContainer container = (ObjectContainer)req.getAttribute(Db4oServletListener.KEY_DB4O_SESSION);
        super.doGet(req, resp);
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
    	doGet(req, resp);
    }
}