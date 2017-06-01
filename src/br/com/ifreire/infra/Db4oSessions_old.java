package br.com.ifreire.infra;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectServer;
import com.db4o.ObjectSet;
import com.db4o.cs.Db4oClientServer;

import java.io.File;

@Component
@SessionScoped
public class Db4oSessions_old
{
    private static final String DATABASE_FILE_NAME = "dbAgenda.db4o";

    public static void main(String[] args)
    {
        sessions();
        sessionsIsolation();
        sessionCache();
        embeddedClient();
    }

    public static void sessions()
    {
    	boolean bool;
    	bool = true;
    	
        cleanUp();
        
        if (bool)
        	bool = false;

        ObjectContainer rootContainer = Db4oEmbedded.openFile(DATABASE_FILE_NAME);
        ObjectContainer session = rootContainer.ext().openSession();
        
        try
        {
            session.store(new Person("Iuri"));
        }
        finally
        {
            session.close();
        }

        rootContainer.close();
    }

    private static void sessionsIsolation()
    {
        cleanUp();
        ObjectContainer rootContainer = Db4oEmbedded.openFile(DATABASE_FILE_NAME);

        ObjectContainer session1 = rootContainer.ext().openSession();
        ObjectContainer session2 = rootContainer.ext().openSession();

        try
        {
            session1.store(new Person("Iuri"));
            session1.store(new Person("Elinete"));
            session2.store(new Person("José Luiz"));
            
            session2.commit();
            
            printAll(session2.query(Person.class));
        	
            session1.commit();
            
            printAll(session2.query(Person.class));
        }
        finally
        {
        	session2.close();
        	session1.close();
        }

        rootContainer.close();
    }

    private static void sessionCache()
    {
        cleanUp();
        ObjectContainer rootContainer = Db4oEmbedded.openFile(DATABASE_FILE_NAME);

        ObjectContainer session1 = rootContainer.ext().openSession();
        ObjectContainer session2 = rootContainer.ext().openSession();
        
        try
        {
            storeAPerson(session1);

            Person personOnSession1 = session1.query(Person.class).get(0);
            Person personOnSession2 = session2.query(Person.class).get(0);

            personOnSession1.setName("NewName");
            session1.store(personOnSession1);
            session1.commit();

            System.out.println(personOnSession2.getName());
            session2.ext().refresh(personOnSession2, Integer.MAX_VALUE);
            System.out.println(personOnSession2.getName());
        }
        finally
        {
            session1.close();
            session2.close();
        }

        rootContainer.close();
    }

    private static void storeAPerson(ObjectContainer session1)
    {
        session1.store(new Person("Joe"));
        session1.commit();
    }

    public static void embeddedClient()
    {
        cleanUp();
        ObjectServer server = Db4oClientServer.openServer(DATABASE_FILE_NAME, 0);
        ObjectContainer container = server.openClient();
        
        try
        {
            container.store(new Person("Joe"));
        }
        finally
        {
            container.close();
        }

        server.close();
    }

    private static void printAll(ObjectSet<Person> persons)
    {
        for (Person person : persons)
        {
            System.out.println(person);
        }
    }

    private static void cleanUp()
    {
        new File(DATABASE_FILE_NAME).delete();
    }

    private static class Person
    {
        private String name;

        private Person(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        @Override
        public String toString()
        {
            return "Person{" + "name='" + name + '\'' + '}';
        }
    }
}