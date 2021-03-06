#language: es

Característica: Listar sus incidencias asignadas por parte de un operario
	
	Escenario: Iniciamos sesión con un operario y listamos sus incidencias asignadas

		Dado el usuario del operario que va a listar las incidencias 'guille@uniovi.es'
			Y su password 'Contra'
			
		Dado una incidencia recogida en el sistema con nombre 'Ola de frio'
			Y descripcion 'Ola de frio en Madrid'
			Y etiquetas 'frio, ola, Madrid'
			Y tipo 'SENSOR_TEMPERATURA'
			Y valor 1.0
			Y latitud 40.4893538
			Y longitud -3.6827461
			Y operario 'guille@uniovi.es'
			
		Dado otra incidencia recodiga en el sistema con nombre 'Inundacion Barcelona'
			Y otra descripcion 'Gran Inundacion en Barcelona'
			Y otras etiquetas 'barcelona,inundacion'
			Y otro tipo 'SENSOR_INUNDACION'
			Y otro valor 8.4
			Y otra latitud 41.3818
			Y otra longitud 2.1685
			Y otro operario 'miguel@uniovi.es'

		Cuando introducimos las incidencias en el sistema
			Y iniciamos sesión
			Y tratamos de listar todas las incidencias

		Entonces se listan las incidencias correctamente 