package br.com.ifreire.db;

import com.db4o.ObjectContainer;

import br.com.ifreire.infra.Db4oServletListener;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import br.com.caelum.vraptor.ioc.Component;
//import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
//@Component("db4o-context")
@Scope(value = "request")
public class Db4oContext
{
	//  O Spring 3.0 deveria reconhecer a Annotation @Autowired(required = true)
	//  para instanciar uma nova HttpServletRequest automaticamente.
	
    @Autowired(required = true)
    private HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

    public Db4oContext()
    { }

    public ObjectContainer objectContainer()
    {
    	ObjectContainer oc = (ObjectContainer)request.getAttribute(Db4oServletListener.KEY_DB4O_SESSION); 
    	
    	return oc;
    }
}