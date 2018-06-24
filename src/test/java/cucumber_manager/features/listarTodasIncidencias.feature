#language: es

Característica: Listado de todas las incidencias por parte de un operario
	
	Escenario: Iniciamos sesión con un operario y listamos todas las incidencias

		Dado el operario que va a listar las incidencias 'guille@uniovi.es'
			Y su password correcta 'Contra'
			
		Dado una incidencia que ha sido recogida en el sistema con nombre 'Ola de calor'
			Y nueva descripcion de ella 'Ola de calor en Madrid'
			Y nuevas etiquetas de ella 'calor, ola, Madrid'
			Y nuevo tipo de ella 'SENSOR_TEMPERATURA'
			Y nuevo valor de ella 45.0
			Y nueva latitud de ella 40.4893538
			Y nueva longitud de ella -3.6827461
			
		Dado otra incidencia que ha sido recogida en el sistema con nombre 'Terremoto Barcelona'
			Y otra nueva descripcion de ella 'Fuerte terremoto en Barcelona'
			Y otras nuevas etiquetas de ella 'barcelona,terremoto'
			Y otro nuevo tipo de ella 'SENSOR_SEISMO'
			Y otro nuevo valor de ella 8.4
			Y otra nueva latitud de ella 41.3818
			Y otra nueva longitud de ella 2.1685

		Cuando introducimos las incidencias en el sistema nuevamente
			Y iniciamos sesión nuevamente
			Y tratamos de listar todas las incidencias nuevamente

		Entonces se listan las incidencias correctamente nuevamente