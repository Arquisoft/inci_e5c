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
import uo.asw.repositories.OperariosRepository;

@ContextConfiguration(classes = DashboardApplication.class, loader = SpringBootContextLoader.class)
@WebAppConfiguration
@ActiveProfiles("INTEGRATION_TEST")
public class OperarioIniciaSesionInvalidoStep 
{
	@Autowired
    OperariosRepository operariosRepository;
    
    String user, password;

    @Dado("^el email inventado \'([^\"]*)\'$")
    public void email(String user) 
    {
    	this.user = user;
    	System.out.println("El usuario del operario es " + user);
    }

    @Y("^su password inventada es \'([^\"]*)\'$")
    public void password(String password) 
    {
    	this.password = password;
    	System.out.println("La contraseña del operario es " + password);
    }

    @Cuando("^tratamos de logearnos con esas credenciales erroneas$")
    public void agente_introduce_datos_formulario() 
    {
    	assertTrue(operariosRepository.findByUsername(user)==null);
    }

    @Entonces("^el operario no ingresa en el sistema al no estar registrado$")
    public void se_envia_la_peticion() 
    {
    	System.out.println("Operario no logeado en el sistema");
    }
}
