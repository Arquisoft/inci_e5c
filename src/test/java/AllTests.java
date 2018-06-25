
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	dominio.OperarioTest.class,
	dominio.IncidenciaTest.class,
	dominio.NotificacionesTest.class,
	cucumber_manager.CucumberTest.class
})
public class AllTests 
{
	
}
