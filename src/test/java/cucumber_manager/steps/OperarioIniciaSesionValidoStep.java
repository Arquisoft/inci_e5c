package cucumber_manager.steps;

import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import uo.asw.DashboardApplication;
import uo.asw.entities.Operario;
import uo.asw.repositories.OperariosRepository;

@ContextConfiguration(classes = DashboardApplication.class, loader = SpringBootContextLoader.class)
@WebAppConfiguration
@ActiveProfiles("INTEGRATION_TEST")
public class OperarioIniciaSesionValidoStep 
{
	@Autowired
    OperariosRepository operariosRepository;
    
    String user, password;

    @Dado("^el email del operario es \'([^\"]*)\'$")
    public void email(String user) 
    {
    	this.user = user;
    	System.out.println("El usuario del operario es " + user);
    }

    @Y("^su password es \'([^\"]*)\'$")
    public void password(String password) 
    {
    	this.password = password;
    	System.out.println("La contraseña del operario es " + password);
    }


    @Cuando("^tratamos de logearnos con esas credenciales$")
    public void login() 
    {
    	Operario operario = operariosRepository.findByUsername(user);
    	assertTrue(operario != null);
    	assertTrue(operario.getUsername().equals(user));
    	System.out.println(operario);
    }

    @Entonces("^el operario ingresa en el sistema$")
    public void log_correcto() 
    {
    	System.out.println("Operario logeado en el sistema");
    }
}
