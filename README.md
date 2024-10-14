
# AIC 2024 - AI Coliseum 2024 - Space Bases (CAT)

## Descripció
És una competició d'intel·ligència artificial en Java. L'objectiu és implementar un codi per controlar les unitats del teu equip en un joc d'estratègia donat.

El joc d'aquest any tracta sobre dos equips competeixen per colonitzar un planeta. Els personatges principals sobre son astronautes els quals s'han de proporcionar una quantitat d'oxigen perquè puguin sobreviure en el terreny.

Si es vol més informació sobre funcionament del joc:
https://www.coliseum.ai/material?lang=ca&tournament=aic2024

## Taula de continguts 
- [Intal·lació](#instal·lació)
- [Funcionament](#Funcionament)
- [Documents](#Documents)
- [Autor](#Autor) 
- [Sobre mi](#Sobremi)
- [Aclariments dels fitxers](#Aclarimentsdelsfitxers)
- [Drets d'Autor](#Dretsd'Autor)


## Instal·lació
1. Clona el repositori:
    ```bash
    git clone https://github.com/nom_usuari/nom_del_repositori.git
    ```

2. Entra a la carpeta del projecte
    ```bash
    cd nom_del_repositori
    ```

## Funcionament 
### Class Astronauta 
Organitza tots els atributs assignats als astronautes, és a dir, quantitat d'oxigen i CarePackage que tenen quan són creats, a més per la meva part he implementat un nou atribut "tipus" el qual determina el moviment amb un patró determinat.

- Constructor per defecte de la classe Astronauta: 
    
    ``` java
    public Astronauta(int tipus, int oxigen, CarePackage) {
        this.tipus = tipus;
		this.oxigen = oxigen;
		this.carePackage = carePackage;
    }
    ```
    Inicialitza l'astronauta amb els valors dels paràmetres quan fem la crida al constructor, els quals podem veure a l'inici a l'inici de la funció *run()* de la classe **UnitPlayer**.

-  Patrons de direccions dels astronautes:
    ``` java
    private final Direction[] movementPattern = {
        Direction.NORTH, 
        ...
    }
    ```
    Són tots els patrons possibles que els astroanutes poden obtar a seguir. Això ve determinat principalment pel atribut *tipus* de la classe.

- Mètodes: Principalment són mètodes que com el nom indica fan aquella funció.
    ```java
    public void accionsPerPackage(UnitController uc, String packageName, Direction[] directions) {
        ...
    }

    ```
    Mètodes que segueixen aquells astronautes que tenen equipat una CarePackage, encara que no tots només alguns, aquells que han de construir alguna altra unitat que ajudi la base a desenvolupar-se.
    ```java
    private Direction getDireccióMésLlunyana(UnitController uc, Direction[] directions) {
        ...
    }
    ```
    Mètode utilitzat per la funció anterior, per poder determinar quin és el millor moviment per poder instal·lar la unitat, ja sigui un HYPERJUMP, un SETTLEMENT, DOME. La funció principal és determinar el moviment cap a la direcció oposada a la qual està la base pare de l'astronauta i si no es pot executar el moviment, busca la casella adjacent a l'astronauta més llunyana de la ubicació de la base pare.


### Class UnitPlayer
Funcionament de les diverses unitats que té el joc, principalment sobre les bases HQ i els astronautes, encara que en el meu cas, també s'ha implementat el funcionament de les bases Settlement

- Mètodes necessaris per al funcionament de la funció principal *run()*.

    ```
    private int getNextAstronautaType(UnitController uc) {
        ...    
    }
    ```
    A partir de la ubicació de la base HQ limitem al tipus de patró que pot aplicar-se a l'astronauta.
    ``` 
    private CarePackage getPackageType(UnitController uc, Astronauta astro) {
        ...
    }
    ```
    Depenen de la quantitat de CarePackage que la base té emmagatzemats podem decidir quin tipus i quan li podem assignar a l'astronauta un d'ells. Principalment perquè una de les maneres per desempatar en una ronda és amb la quantitat de CarePackage.
    ```
    private CarePackage getPackageTypeAttack (UnitController uc, Astronauta astro) {
        ...
    }
    ```
    Aquesta funció té el mateix funcionament que l'anterior, encara que amb un propòsit diferent, només subministra aquells CarePackage que poden ajudar a la base si detecta algun enemic dintre del seu rang de visió.

#### Funcionament general de les bases HQ i Settlement
Les dues bases funcionen de manera similar. La primera acció que executa la base és mirar si dintre del seu rang de visió hi ha algun enemic, per poder defensar-se amb la creació de nous astronautes, que tenen atributs diferents dels astronautes creats sense estar sota atac.

Si no és el cas, és a dir, si dintre del rang de visió no es troba cap enemic, la base generarà un astronauta amb 30 d'oxigen, amb el *tipus* i *CarePackage* que són determinats per funcions, anteriorment explicades.

#### Funcionament general dels astronautes
Com en les bases, la primera acció dels astronautes és mirar si hi ha algun enemic dintre del rang de visió, encara que no només astronauta enemic, sinó base enemiga, donant-li prioritat a l'estructura abans que a l'astronauta. Si el cas es dona, obtenim la direcció cap a on està l'enemic per poder atacar-lo abans que arribi més a prop del nostre terreny.

Seguidament, si no hi ha enemics dintre del perímetre, localitzem si hi ha *CarePackage* donant-li prioritat aquells amb l'etiqueta de *PLANT* i *OXYGEN_TANK* amb aquest ordre. S'hi han trobat el moviment de l'astronauta anirà cap a la direcció on està el *CarePackage*. També poden anar cap a les zones anomenades com a *HOT_ZONES*, les quals són zones on la probabilitat que hi hagi *CarePackage* és molt més elevada. En els dos casos l'astronauta va cap allà i si es troba un *CarePackage* en una de les caselles adjacents a la seva posició el recull.

Finalment, si cap de les condicions es compleixen, l'astronauta seguirà el patró de moviment determinat pel seu atribut *tipus*. El qual pot variar durant el joc, si l'astronauta es xoca contra una casella determinada com *MapObject.WATER* o la direcció a la qual vol anar està fora del mapa, el *tipus* de l'astronauta variarà aleatòriament.

Les següents accions determinades són la de recollir algun *CarePackage*, la transmissió d'oxigen a les bases *Settlement*, ja que al començament no tenen una producció passiva d'oxigen. En el cas de trobar un *Hyperjump* i si els hi queda poc oxigen terraformar la casella, la qual redueix la quantitat d'oxigen a la meitat si l'astronauta està sobre ella.


## Documents
Principalment tots els coneixements necessaris per poder participar en el projecte, en la mateixa pàgina web del concurs pots obtenir tota la documentació necessària per poder programar i participar.

En la mateixa pàgina web, on hi ha la descripció del joc, també pots obtenir tota la informació necessària, encara que hi ha alguns documents on només es pot optar en català o anglès.

Si el cas és que no has programat en Java anteriorment, també hi ha un seguiment de documents on et poden ajudar, com ha sigut el meu cas ara.

https://www.coliseum.ai/material?lang=en&tournament=aic2024


## Autor
- [@LL-OV](https://www.github.com/LL-OV)

## Sobremi
Soc una alumna de la Universitat Autònoma de Barcelona cursant el grau d'Enginyeria Informàtica de la promoció de 2022-2023. La iniciativa de participar en el concurs ha sigut pròpia, sense cap ajuda o influència de la universitat, només ha fet com a divulgador del concurs.

## Aclarimentsdelsfitxers
El codi presentat al concurs el podeu trobar en seguint els passos: AIC2024>src>Astro, el contingut de la carpeta **Astro** de la pàgina principal és el codi sense cap problema i amb l'organització del codi feta.

## Dretsd'Autor
Tot el codi i documentació que hi ha al repositori que no sigui el contingut de les dues carpetes **Astro** anomenades anteriorment és propietat oberta dels organitzadors d'AI Coliseum, amb tots els seus drets d'autor de documentació i de l'execució del joc, etc.


# AIC 2024 - AI Coliseum 2024 - Space Bases (ENG)
## Description
It is an asrtifical intelligence competition in Java. The goal is to implement a code to control the units of your aquip in a given strategy game. 

This year's game is about two teams competing to colonize a planet. The principality characters about are astronauts who must provide a quantity of oxygen so that they can survive on the ground.

If you want more information about the operation of the game:
https://www.coliseum.ai/material?lang=en&tournament=aic2024

## Table of Contents 
- [Intallation](#Installation)
- [Functionality](#Functionality)
- [Documents](#Documents)
- [Author](#Author) 
- [About me](#Aboutme)
- [File clarifications](#Fileclarifications)
- [Copyright](#Copyright)


## Installation
1. Clone repository:
    ``` bash
    git clone https://github.com/nameusuariusernamedelofdelrepository.git
    ```

2. Enter Project Folder
    ``` bash
    cd nametorirepository
    ```

## Functionality
Class Astronaut
Manages all the attributes assigned to astronauts, that is, the amount of oxygen and the CarePackage they have when they are created. Additionally, I’ve implemented a new attribute called “type” which determines the movement with a specific pattern.

- Default constructor of the Astronaut class:
    ``` java
    public Astronauta(int tipus, int oxigen, CarePackage) {
        this.tipus = tipus;
		this.oxigen = oxigen;
		this.carePackage = carePackage;
    }
    ```
    Initializes the astronaut with the parameter values when we call the constructor, which can be seen at the beginning of the *run()* function in the **UnitPlayer** class.


- Astronauts' direction patterns:
    ``` java
    private final Direction[] movementPattern = {
        Direction.NORTH, 
        ...
    }
    ```
    These are all the possible patterns that astronauts can follow. This is mainly determined by the type attribute of the class.

- Methods: These are primarily methods that, as the name suggests, perform the indicated function.
    ```java
    public void accionsPerPackage(UnitController uc, String packageName, Direction[] directions) {
        ...
    }

    ```
    This method is followed by astronauts who are equipped with a CarePackage, although not all of them, only those who need to build another unit that will help the base develop
    ```java
    private Direction getDireccióMésLlunyana(UnitController uc, Direction[] directions) {
        ...
    }
    ```
    A method used by the previous function to determine the best movement to install a unit, whether it’s a HYPERJUMP, SETTLEMENT, or DOME. The main function is to determine the movement toward the opposite direction from where the astronaut's parent base is, and if it’s not possible to execute the movement, it looks for the furthest adjacent tile from the parent base.  

### Class UnitPlayer
Functionality of the various units in the game, mainly concerning the HQ bases and astronauts, though in my case, I have also implemented functionality for Settlement bases.

- Necessary methods for the functionality of the main function *run()*.
    ```
    private int getNextAstronautType(UnitController uc) {
        ...    
    }

    ```
    Based on the location of the HQ base, we limit the type of pattern that can be applied to the astronaut.

    ```
    private CarePackage getPackageType(UnitController uc, Astronaut astro) {
        ...
    }

    ```
    Depending on the number of CarePackages stored at the base, we can decide what type and when to assign one to the astronaut. Mainly because one way to break a tie in a round is by the number of CarePackages.

    ```
    private CarePackage getPackageTypeAttack(UnitController uc, Astronaut astro) {
        ...
    }

    ```
    This function operates similarly to the previous one, though with a different purpose: it only provides CarePackages that can help the base if an enemy is detected within its vision range.

#### General operation of HQ and Settlement bases
Both bases work similarly. The first action the base executes is to check if there’s an enemy within its vision range, in order to defend itself by creating new astronauts, who have different attributes than those created when not under attack.

If that’s not the case, meaning if no enemy is found within the vision range, the base will generate an astronaut with 30 oxygen, with the *type* and *CarePackage* determined by the previously explained functions.


#### General operation of astronauts
As with the bases, the first action of the astronauts is to check if there’s an enemy within their vision range, including not only enemy astronauts but also enemy bases, prioritizing structures over astronauts. If this is the case, they will move toward the enemy to attack before it gets closer to their territory.

If no enemies are within the perimeter, they will locate a CarePackage, prioritizing those labeled as *PLANT* and *OXYGEN_TANK* in that order. If found, the astronaut will move toward the *CarePackage*. They can also go to areas called *HOT_ZONES*, where the probability of finding a *CarePackage* is much higher. In both cases, the astronaut moves toward these areas, and if a *CarePackage* is found in an adjacent tile, they will collect it.

Finally, if none of the conditions are met, the astronaut will follow the movement pattern determined by their *typ*e attribute. This can vary during the game if the astronaut collides with a tile marked as *MapObject.WATER* or if the direction they want to go is outside the map, causing the astronaut's *type* to change randomly.

The following actions are collecting a *CarePackage*, transmitting oxygen to the *Settlement* bases (since at the beginning they don’t have a passive oxygen production), and if they encounter a *Hyperjump* and have low oxygen, terraforming the tile (which halves the oxygen if the astronaut is standing on it).

## Documents
Mainly, all the necessary knowledge to participate in the project can be found on the contest's website, where you can obtain all the documentation needed to code and participate.

On the same website, where the game description is located, you can also find all the necessary information, although some documents are only available in Catalan or English.

If you haven’t programmed in Java before, there’s also a series of documents to help, as was the case for me.

https://www.coliseum.ai/material?lang=en&tournament=aic2024

## Author
- [@LL-OV](https://www.github.com/LL-OV)

## Aboutme
I am a student at the Universitat Autònoma de Barcelona pursuing a degree in Computer Engineering from the 2022-2023 class. The initiative to participate in the contest was my own, without any help or influence from the university, which only served as a contest sponsor.

## Fileclarifications
You can find the code submitted to the contest by following these steps: AIC2024>src>Astro. The content in the Astro folder on the main page is the code, without any issues, and with the code organization completed.

## Copyright
All the code and documentation in the repository, except for the content of the two Astro folders mentioned earlier, is the open property of the AI Coliseum organizers, with all rights reserved for the documentation and game execution, etc.

# AIC 2024 - AI Coliseum 2024 - Space Bases (ESP)
## Descripción

Es una competición de inteligencia artificial en Java. El objetivo es implementar un código para controlar las unidades de tu equipo en un juego de estrategia dado.

El juego de este año trata sobre dos equipos compiten para colonizar un planeta. Los personajes principales sobre su astronautas los cuales se tienen que proporcionar una cantidad de oxígeno para que puedan sobrevivir en el terreno.

Si se quiere más información sobre funcionamiento del juego:
https://www.coliseum.ai/material?lang=ca&tournament=AIC2024

## Tabla de contenido
- [Intalació](#Instalacion)
- [Funcionamiento](#Funcionamiento)
- [Documentos](#Documentos)
- [Autor](#Autor) 
- [Sobre mi](#Sobre mi)
- [Aclaraciones de los fitcheros](#Aclaraciones de los fitcheros)
- [Derechos de Autor](#Drechos de Autor)

## Instalación
1. Clonar el repositorio:
    ```bash
    git clone https://github.com/nom_usuari/nom_del_repositori.git
    ```

2. Entrar en la carpeta del proyecto
    ```bash
    cd nom_del_repositori
    ```

## Funcionamiento
### Class Astronauta 
Organiza todos los atributos asignados a los astronautas, es decir, cantidad de oxígeno y CarePackage que tienen cuando son creados, además por mi parte he implementado un nuevo atributo "tipo" el cual determina el movimiento con un patrón determinado.

- Constructor por defecto de la clase Astronauta:
    ``` java
    public Astronauta(int tipus, int oxigen, CarePackage) {
        this.tipus = tipus;
		this.oxigen = oxigen;
		this.carePackage = carePackage;
    }
    ```
    Inicializa el astronauta con los valores de los parámetros cuando hacemos el llamamiento al constructor, los cuales podemos ver al inicio al inicio de la función **run()* de la clase ***UnitPlayer**.

-  Patrones de direcciones de los astronautas:
    ``` java
    private final Direction[] movementPattern = {
        Direction.NORTH, 
        ...
    }
    ```
    Són tots els patrons possibles que els astroanutes poden obtar a seguir. Això ve determinat principalment pel atribut *tipus* de la classe.

- Mètodes: Principalment són mètodes que com el nom indica fan aquella funció.
    ```java
    public void accionsPerPackage(UnitController uc, String packageName, Direction[] directions) {
        ...
    }

    ```
    Métodos que siguen aquellos astronautas que tienen equipado una *CarePackage*, aunque no todos solo algunos, aquellos que tienen que construir alguna otra unidad que ayude la base a desarrollarse.
    ```java
    private Direction getDireccióMésLlunyana(UnitController uc, Direction[] directions) {
        ...
    }
    ```
    Método utilizado por la función anterior, para poder determinar cuál es el mejor movimiento para poder instalar la unidad, ya sea un *HYPERJUMP, un *SETTLEMENT, DOMO. La función principal es determinar el movimiento hacia la dirección opuesta a la cual está la base paro del astronauta y si no se puede ejecutar el movimiento, busca la casilla adyacente a la astronauta más lejana de la ubicación de la base padre.

### *Class *UnitPlayer
Funcionamiento de las varias unidades que tiene el juego, principalmente sobre las bases *HQ* y los astronautas, aunque en mi caso, también se ha implementado el funcionamiento de las bases *Settlement*

- Métodos necesarios para el funcionamiento de la función principal *run()*.

    ```
    private int getNextAstronautaType(UnitController uc) {
        ...    
    }
    ```
    A partir de la ubicación de la base *HQ limitamos al tipo de patrón que puede aplicarse al astronauta.
    ``` 
    private CarePackage getPackageType(UnitController uc, Astronauta astro) {
        ...
    }
    ```
    Dependen de la cantidad de *CarePackage que la base tiene almacenados podemos decidir qué tipo y cuando le podemos asignar al astronauta uno de ellos. Principalmente porque una de las maneras para desempatar en una ronda es con la cantidad de *CarePackage.
    ```
    private CarePackage getPackageTypeAttack (UnitController uc, Astronauta astro) {
        ...
    }
    ```
    Esta función tiene el mismo funcionamiento que el anterior, aunque con un propósito diferente, solo suministra aquellos CarePackage que pueden ayudar en la base si detecta algún enemigo dentro de su rango de visión.

#### Funcionamiento general de las bases *HQ y *Settlement
Las dos bases funcionan de manera similar. La primera acción que ejecuta la base es mirar si dentro de su rango de visión hay algún enemigo, para poder defenderse con la creación de nuevos astronautas, que tienen atributos diferentes de los astronautas creados sin estar bajo ataque.

Si no es el caso, es decir, si dentro del rango de visión no se encuentra ningún enemigo, la base generará un astronauta con 30 de oxígeno, con el *tipo* y **CarePackage* que son determinados por funciones, anteriormente explicadas.

#### Funcionamiento general de los astronautas
Como en las bases, la primera acción de los astronautas es mirar si hay algún enemigo dentro del rango de visión, aunque no solo astronauta enemigo, sino base enemiga, dándole prioridad a la estructura antes de que al astronauta. Si el caso se da, obtenemos la dirección hacia donde está el enemigo para poder atacarlo antes de que llegue más cerca de nuestro terreno.

Seguidamente, si no hay enemigos dentro del perímetro, localizamos si hay *CarePackage* dándole prioridad aquellos con la etiqueta de *PLANT* y *OXYGEN_*TANK* con este orden. Se han encontrado el movimiento del astronauta irá hacia la dirección donde está el *CarePackage*. También pueden ir hacia las zonas llamadas como *HOT_ZONAS*, las cuales son zonas donde la probabilidad que haya *CarePackage* es mucho más elevada. En los dos casos el astronauta va ninguno allá y si se encuentra un *CarePackage* en una de las casillas adyacentes a su posición la compilación.

Finalmente, si ninguno de las condiciones se cumplen, el astronauta seguirá el patrón de movimiento determinado por su atributo *tipo*. El cual puede variar durante el juego, si el astronauta se choca contra una casilla determinada cómo *MapObject.WATER* o la dirección a la cual quiere ir está fuera del mapa, el tipo* del astronauta variará aleatoriamente.

Las siguientes acciones determinadas son la de recoger algún *CarePackage*, la transmisión de oxígeno en las bases *Settlement*, puesto que al comienzo no tienen una producción pasiva de oxígeno. En el caso de encontrar un *Hyperjump* y si se los queda poco oxígeno terraformar la casilla, la cual reduce la cantidad de oxígeno en la mitad si el astronauta está sobre ella.

## Documentos
Principalmente todos los conocimientos necesarios para poder participar en el proyecto, en la misma página web del concurso puedes obtener toda la documentación necesaria para poder programar y participar.

En la misma página web, donde hay la descripción del juego, también puedes obtener toda la información necesaria, aunque hay algunos documentos donde solo se puede optar en catalán o inglés.

Si el caso es que no has programado en Java anteriormente, también hay un seguimiento de documentos donde te pueden ayudar, como ha sido mi caso ahora.

https://www.coliseum.ai/material?lang=en&tournament=aic2024


## Autor
- [@ll-ov](https://www.github.com/ll-ov)

## Sobre mí
Soy una alumna de la Universitat Autònoma de Barcelona cursando el grado de Ingeniería Informática de la promoción de 2022-2023. La iniciativa de participar en el concurso ha sido propia, sin ninguna ayuda o influencia de la universidad, solo ha hecho como divulgador del concurso.

## Aclaraciones de los ficheros
El código presentado al concurso lo podéis encontrar en siguiendo los pasos: AIC2024>src>Astro, el contenido de la carpeta **Astro** de la página principal es el código sin ningún problema y con la organización del código hecha.

## Derechos de Autor
Todo el código y documentación que hay al repositorio que no sea el contenido de las dos carpetas **Astro** denominadas anteriormente es propiedad abierta de los organizadores de AI Coliseum, con todos sus derechos de autor de documentación y de la ejecución del juego, etc.