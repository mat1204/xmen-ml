X-MEN ML
========

URL: https://xmen-ml.herokuapp.com
----------------------------------

Endpoints en la Nube: 
----------
+ HTTP POST https://xmen-ml.herokuapp.com/mutant
+ HTTP GET https://xmen-ml.herokuapp.com/stats

 

Levantar Aplicacion:
--------------------
(Requerimientos: **Entorno Linux, Java 8, Docker 18.03** )

Correr test
```
./gradlew test
```




Script Para levantar BDD PostrgreSQL

```
./db/dockerPostgre.sh
```

Levantar app springBoot (enviroment "default" en puerto **8080**)
 ```
./gradlew bootRun
```