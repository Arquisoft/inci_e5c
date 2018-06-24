#language: es

Característica: Inicio de sesión con un operario inválido

	Escenario: Introducimos los datos de un operario no existente en el sistema
	
		Dado el email inventado 'facundo@uniovi.es'
			Y su password inventada es 'Contra'
			
		Cuando tratamos de logearnos con esas credenciales erroneas
			
		Entonces el operario no ingresa en el sistema al no estar registrado