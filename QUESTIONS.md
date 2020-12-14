# PARTE TEORICA

### Arquitecturas de UI: MVP, MVVM y MVI

#### MVVM

##### ¿En qué consiste esta arquitectura?
Consiste en organizar el código en 3 capas:  
- View: muestra el UI e informa las acciones del usuario a otras capas.  
- ViewModel: expone la información al View.  
- Model: recibe la información de la fuente de datos y la expone al ViewModel.  
La principal diferencia con MVC y MVP es que la capa de ViewModel no debe contener ninguna referencia a los Views, sólo proporciona información sin importar de quien lo consuma.


##### ¿Cuáles son sus ventajas?
- Provee un mayor nivel de abstracción facilitando así el testeo unitario. 
- Resuelve el problema de tener un controlador demasiado extenso de MVC.  
- El ViewModel permite que los datos sobrevivan a cambios de configuración, como las rotaciones de pantallas.

##### ¿Qué inconvenientes tiene?
- Demasiado complejo para aplicaciones simples.

#### MVP

##### ¿En qué consiste esta arquitectura?
Consiste en organizar el código en 3 capas:
- View: muestra el UI y escucha las acciones del usuario. Está formada típicamente por actividades, fragmentos…
- Model es la capa de datos.
- Presenter: conecta la capa modelo y la vista y realiza lo lógica de presentación.

##### ¿Cuáles son sus ventajas?
- Provee un mayor nivel de abstracción que el MVC, facilitando así el testeo unitario. 
- Resuelve el problema de tener un controlador demasiado extenso de MVC. 

##### ¿Qué inconvenientes tiene?
- Cuando la actividad se destruye en presenter asociado no se destruye sólo, hay que realizarlo manualmente.
- Hay que crear interfacez de interacción que resulta difícil de entender al principio.

#### MVI

##### ¿En qué consiste esta arquitectura?
Es muy diferente a los MVC, MVP y MVVM. Consiste en organizar el código en 3 capas:
- Model: Representa un estado. Los modelos deben ser inmutables para garantizar un flujo de datos unidireccional entre ellos y las otras capas de la arquitectura.
- Intent: Representa una intención o un deseo de realizar una acción por parte del usuario. 
- Vista: igual que la vista de MVP.


##### ¿Cuáles son sus ventajas?
- Un flujo de datos unidireccional y cíclico para su aplicación.
- Estado coherente durante todo el ciclo de vida de sus vistas.
- Modelos inmutables que proporcionan un comportamiento confiable y seguridad de subprocesos en aplicaciones grandes.

##### ¿Qué inconvenientes tiene?
- Mucho más complicado de aprender que los demás arquitecturas.

---

### Testing

#### ¿Qué tipo de tests se deberían incluir en cada parte de la pirámide de test? Pon ejemplos de librerías de testing para cada una de las partes. 
- Las pruebas pequeñas: se tratan de pruebas unitarios que validan el comportamiento una clase de la aplicación. Una librería muy utilizada es el JUnit.
- Las pruebas intermedias: se tratan de pruebas de integración que validan las interacciones entre niveles de pila dentro de un módulo o interacciones entre módulos relacionados. Una librería muy utilizada es el Roboelectric.
- Las pruebas grandes: se tratan de pruebas de UI que emulan el comportamiento del usuario y comprueban su correcto funcionamiento. Pueden usar las librerías Espresso o UIAutomator.

#### ¿Por qué los desarrolladores deben centrarse sobre todo en los Unit Tests?
Por un lado son rápidos y por otro lado, si todas las unidades individuales de código funcionan entonces al juntarlas lo más probable es que también funciona.

---

### Inyección de dependencias

#### Explica en qué consiste y por qué nos ayuda a mejorar nuestro código.
Para explicar en qué consiste la inyección de dependecias, previamente debería entender qué son las dependecias.  
Las dependecias son cuando en un objeto de una clase requiere un objeto de otra clase para su funcionamiento.  
Por ejemplo en una case Coche utiliza un objeto de otra clase Motor:  
class Car{  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    private var engine = Engine()  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    fun start(){  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;        engine.start()  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    }  
}  

fun main(args: Array){  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    val car = Car()  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    car.start()  
}  

En este caso las clases Coche y Motor estan estrechamente vinculados. Esto es un problema ya que si necesitamos otro coche con un motor diferente tendríamos que crear otra clase Coche2.  
Entonces, la inyección de dependencias consiste en pasar la dependencia desde un recurso externo en vez de crearlo dentro de la clase.  
Para ello puede hacerlo de forma manual, por ejemplo por el constructor. O bien de forma automática utilizando librerías externas como Dagger.  
De esta manera, podemos reutilizar la clase Coche, instanciando los objetos independientes cada uno con su propio motor.

#### Explica cómo se hace para aplicar inyección de dependencias de forma manual a un proyecto (sin utilizar librerías externas).
Lo puede realizar utilizando la inyección de dependencias en el constructor.  
Siguiendo el ejemplo anterior, crearíamos la clase Coche de la siguiente manera:  
class Car(private val engine: Engine){  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    fun start(){  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;        engine.start()  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    }  
}  

Y utilizarlo así:  
fun main(args: Array){  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    val engine = Engine()  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    val car = Car(engine)  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    car.start()  
}  
