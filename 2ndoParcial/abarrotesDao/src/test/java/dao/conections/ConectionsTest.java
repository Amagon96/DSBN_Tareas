package dao.conections;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.logging.Logger;

public class ConectionsTest {
    @Test
    public void conecctionSingletonTest(){
        try{
            ConexionDao con = ConexionDao.getInstance();
            Assert.assertFalse(con.getConn().isClosed());
        }catch (ClassNotFoundException | SQLException ex){
            Logger.getAnonymousLogger().warning("La conexión es invalida.");
        }
    }
}
