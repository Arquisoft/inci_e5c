#language: es

Característica: Inicio de sesión con un operario válido

	Escenario: Introducimos los datos de un operario existente en el sistema
	
		Dado el email del operario es 'alvaro@uniovi.es'
			Y su password es 'Contra'
			
		Cuando tratamos de logearnos con esas credenciales
			
		Entonces el operario ingresa en el sistema