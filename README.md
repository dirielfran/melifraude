#Geolocalizacion 

---

Geolocalizacion es un proyecto Web desarrollado con el core de spring, renderizado basado en la tecnologia de thymeleaf y con apoyo de contenedores docker para el despliegue, creado para coordinar respuestas ante fraudes, obteniendo informacion del lugar de origen segun una IP, partiendo de esta informacion obtener estadisticas en cuanto a las distancias de las solicitudes


----


# Tecnologías 

 * Java8 
 * Git
 * Maven
 * Spring-boot
 * Spring-Data
 * Hibernate
 * Docker
 * MySql
 * thymeleaf
 * Bootstrap
 * API Rest


-------

# Instalacion

* Creacion de de contenedor con imagen de mysql, el cual se crea con una base de datos --> fraudemeli
	docker run -d -p 6033:3306 --name=docker-mysql --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=fraudemeli" mysql
* Se ingresa al contenedor creado para crear las tablas necesarias  
	docker exec -it docker-mysql bash
* Se ingresa a mysql con user -->  root y password --> root
	mysql -uroot -proot
	* Se ejecuta comando para trabajar con la base de dtos fraudemeli
	use fraudemeli;


	* Creacion de tabla
		CREATE TABLE `estadisticasip` (
		  `id` bigint NOT NULL AUTO_INCREMENT,
		  `create_at` date DEFAULT NULL,
		  `distancia` double DEFAULT NULL,
		  `fecha_modificacion` date DEFAULT NULL,
		  `ip` varchar(255) DEFAULT NULL,
		  `nombre_destino` varchar(255) DEFAULT NULL,
		  `nombre_origen` varchar(255) DEFAULT NULL,
		  PRIMARY KEY (`id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
* Se contruye el proyecto 
	mvn install 
* Se crea en la raiz del proyecto archivo Dockerfile con las siguientes caracteristicas
	FROM openjdk:8-jdk-slim
	COPY "./target/Geolocalizacion-0.0.1-SNAPSHOT.jar" "appmeli.jar"
	EXPOSE 8080
	ENTRYPOINT ["java","-jar","appmeli.jar"]
* Se contruye imagen de la aplicacion 
	docker build -f Dockerfile -t spring-meli .
* Se crea container docker partiendo de la imagen creada
	docker run -t --link docker-mysql:mysql -p 8080:8080 spring-meli


-------

# Accesos a la aplicacion Web
	http://localhost:8080/formIp

# Accesos a los servicios
	* Enponint que guarda datos de distancia correspondiente al pais de la ip de consulta y retorna datos del pais
		localhost:8080/api/geo/{ip}

		* Parametros del path
		 	Parametro: 			ip
		 	Tipo: 				Long
		 	Descripcion: 		ip que se requiere consultar

		* Contenido:
			horas: 
				Descripcion: lista de horas segun zona horaria popr pais
				tipo: List<String>
			cotizacion:
				Descripcion: Cotizacion en dolares segun codigo de moneda del pais de consulta
				tipo: Double
			distancia:
				Descripcion: Distancia estimada entre Buenos Aires y pais de consulta
				tipo: Double
			pais:
				Descripcion: Informacion del pais de consulta
				tipo: InformacionPais


	* EndPoint que consulta las estadisticas correspondientes a las ip de consulta
		localhost:8080/api/geo/estadisticas

		* Contenido:
			distPromedio: 
				Descripcion: 	Distancia promedio de los paises donde se consulto la ip
				tipo: 			Double
			distMinima: 
				Descripcion: 	Distancia minima del pais donde se consulto la ip
				tipo:			Double
			distMaxima: 
				Descripcion: 	Distancia maxima del pais donde se consulto la ip
				tipo:			Double

				





