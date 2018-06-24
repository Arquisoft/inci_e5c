#language: es

Característica: Cambiar estado de incidencia

	Escenario: Intentamos cambiar el estado de una incidencia del sistema
			
		Dado una incidencia con nombre 'Inundacion en Madrid'
			Y su descripcion 'Inundacion Madrid'
			Y sus etiquetas 'inundacion, Madrid'
			Y su tipo 'SENSOR_INUNDACION'
			Y su valor 1.0
			Y su latitud 40.4893539
			Y su longitud -3.6827460
			Y su operario 'guille@uniovi.es'
			
		Cuando tratamos de cambiar el estado de la incidencia por 'IN_PROCESS'
			
		Entonces el estado se cambia correctamente