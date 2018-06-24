#language: es

Característica: Listado de todas las incidencias por parte de un operario
	
	Escenario: Iniciamos sesión con un operario y listamos todas las incidencias

		Dado el usuario del operario que va a listar las incidencias guille@uniovi.es'
			Y su password 'Contra'
			
		Dado una incidencia recogida en el sistema con nombre 'Ola de calor'
			Y descripcion 'Ola de calor en Madrid'
			Y etiquetas 'calor, ola, Madrid'
			Y tipo 'SENSOR_TEMPERATURA'
			Y valor 45.0
			Y latitud 40.4893538
			Y longitud -3.6827461
			
		Dado otra incidencia recodiga en el sistema con nombre 'Terremoto Barcelona'
			Y descripcion 'Fuerte terremoto en Barcelona'
			Y etiquetas 'barcelona,terremoto'
			Y tipo 'SENSOR_SEISMO'
			Y valor 8.4
			Y latitud 41.3818
			Y longitud 2.1685

		Cuando introducimos las incidencias en el sistema
			Y iniciamos sesión
			Y tratamos de listar todas las incidencias

		Entonces se listan las incidencias correctamente 