package br.com.ifreire.infra;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

import com.db4o.Db4oEmbedded;
import com.db4o.EmbeddedObjectContainer;
import com.db4o.ObjectContainer;

@Component
@ApplicationScoped
public class Db4oServletListener implements ServletContextListener, ServletRequestListener
{
	public  static final String KEY_DB4O_FILE_NAME = "databaseFile";
    public  static final String KEY_DB4O_SERVER = "db4oServer";
    public  static final String KEY_DB4O_SESSION = "db4oSession";
    private static final String LOCAL_PATH = "db";

    @Override
    public void contextInitialized(ServletContextEvent event)
    {
        ServletContext context = event.getServletContext();
        //String contextPath = context.getContextPath();
        String filePath = context.getRealPath(LOCAL_PATH + "/" + context.getInitParameter(KEY_DB4O_FILE_NAME));
        //String filePath = context.getRealPath(contextPath + "/" + LOCAL_PATH + "/" + context.getInitParameter(KEY_DB4O_FILE_NAME));
        //String filePath = context.getRealPath(context.getContextPath() + "\\" + LOCAL_PATH + "\\" + context.getInitParameter(KEY_DB4O_FILE_NAME));
        EmbeddedObjectContainer rootContainer = Db4oEmbedded.openFile(filePath);
        context.setAttribute(KEY_DB4O_SERVER, rootContainer);
        context.log("db4o startup on " + filePath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event)
    {
        ServletContext context = event.getServletContext();
        ObjectContainer rootContainer = (ObjectContainer) context.getAttribute(KEY_DB4O_SERVER);
        context.removeAttribute(KEY_DB4O_SERVER);
        close(rootContainer);
        context.log("db4o shutdown");
    }

    @Override
    public void requestInitialized(ServletRequestEvent requestEvent)
    {
        EmbeddedObjectContainer rootContainer = (EmbeddedObjectContainer) requestEvent.
        																  getServletContext().
        																  getAttribute(Db4oServletListener.KEY_DB4O_SERVER);

        ObjectContainer session = rootContainer.ext().openSession();
        requestEvent.getServletRequest().setAttribute(KEY_DB4O_SESSION, session);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent requestEvent)
    {
        ObjectContainer session = (ObjectContainer) requestEvent.
        											getServletRequest().
        											getAttribute(KEY_DB4O_SESSION);

        close(session);
    }

    private void close(ObjectContainer oc)
    {
        if (oc != null)
        {
        	oc.close();
        	oc = null;
        }
    }
}