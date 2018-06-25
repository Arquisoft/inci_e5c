#language: es

Característica: Listar sus notificaciones por parte de un operario
	
	Escenario: Iniciamos sesión con un operario y listamos sus notificaciones

		Dado el usuario del operario que va a listar las notificaciones 'guille@uniovi.es'
			Y su password 'Contra'
			
		Dado una incidencia recogida en el sistema con nombre 'Ola de fuego'
			Y descripcion 'Ola de fuego en Ecuador'
			Y etiquetas 'calor, ola, Ecuador'
			Y tipo 'SENSOR_TEMPERATURA'
			Y valor 70.0
			Y latitud 40.4893538
			Y longitud -3.6827461
			Y operario 'guille@uniovi.es'
			
		Dado otra incidencia recodiga en el sistema con nombre 'Terremoto Lorca'
			Y otra descripcion 'Gran Terremoto en Murcia'
			Y otras etiquetas 'murcia,terremote'
			Y otro tipo 'SENSOR_SEISMO'
			Y otro valor 120.0
			Y otra latitud 41.3818
			Y otra longitud 2.1685
			Y otro operario 'miguel@uniovi.es'

		Cuando introducimos las incidencias en el sistema
			Y iniciamos sesión
			Y tratamos de listar las notifaciones

		Entonces se listan las notificaciones correctamente 