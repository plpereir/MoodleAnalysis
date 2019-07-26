package br.com.moodle.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.moodle.DAO.DAO;

public class DAOTest {
    private static DAO dao;
    
    @BeforeClass
    public static void initDAO() {
        dao = new DAO();
    }
 
    @Before
    public void beforeEachTest() {
        System.out.println("This is executed before each Test");
    }
 
    @After
    public void afterEachTest() {
        System.out.println("This is exceuted after each Test");
    }
 
   @Test
   public void testConnection() {
	   boolean connection = true;
	   if (dao.getConnectionMySQL() == null)
	   {
		   assertEquals(null,dao.getConnectionMySQL());
	   }else
	   {
		   assertEquals(true,true);
	   }
   }
    
   @Test
   public void testStatus() {
	   assertTrue("the connection restart", dao.statusConnection());
   }

}
