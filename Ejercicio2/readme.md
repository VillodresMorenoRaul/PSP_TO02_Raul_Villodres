# **CÓMO EVITÉ EL INTERBLOQUEO**
Cómo se nos explicó en clase, el interbloqueo sucede cuando todos los filosofos toman el palillo de su derecha, esto provoca que todos tengan un palillo y esperen indefinidamente.

Esto lo solucioné añadiendo una variable booleana llamada “zurdo”, aplicandola al primer proceso filósofo, y cambiando el método para que el filosofo zurdo empiece a tomar los palillos por el de su izquierda.

-En el método intentarCogerPalillo de la clase GestorPalillos, donde además de solicitar las varibales habituales, solicitamos también el boolean, en caso de no sea zurdo, seguimos lo que hace el programa normalmente, pero si lo es, cambiamos el orden en el que se cogen los palillos

    intentarCogerPalillos(int pos1, int pos2, boolean zurdo)
    {
        boolean seConsigue=false;

        if(!zurdo){
            if (
                    (palilloLibre[pos1])
                            &&
                            (palilloLibre[pos2]) )
            {
                palilloLibre[pos1]=false;
                palilloLibre[pos2]=false;
                seConsigue=true;
            } //Fin del if
        } else {
            if (
                    (palilloLibre[pos2])
                            &&
                            (palilloLibre[pos1])) {
                palilloLibre[pos2] = false;
                palilloLibre[pos1] = false;
                seConsigue = true;
            }
        }
En el método run también hay que realizar cambios para incorporar esto. En primer lugar crearemos un condicional que en caso de que el nombre del Thread sea “Thread-0” pondrá el booleano zurdo cómo true

            if(Thread.currentThread().getName().equals("Thread-0")){
                zurdo = true;
            } else {
                zurdo = false;
            }

En el método liberarPalillos también cambiaremos el orden en el que se sueltan
```java
    public void liberarPalillos(int pos1, int pos2, boolean zurdo){
        if (!zurdo){
            palilloLibre[pos1]=true;
            palilloLibre[pos2]=true;
        } else {
            palilloLibre[pos2]=true;
            palilloLibre[pos1]=true;
        }
    }
```

Y en el run tenemos que pasarle esta variable “zurdo” al método palillosCogidos
palillosCogidos = this.gestorPalillos.intentarCogerPalillos(posPalilloIzq, posPalilloDer, zurdo );

Y al método liberarPalillos
this.gestorPalillos.liberarPalillos(posPalilloIzq,  posPalilloDer, zurdo);

# **CÓMO EVITÉ LA INANICIÓN**
Para evitar la inanición he tratado con el setPriority de cada Thread, dentro del propio run de la clase filósofo. 

Dentro de la condición if (palillosCogidos), resetearemos el valor de prioridad a 10 con un “prioridad = 10”, y luego usaremos “Thread.currentThread().setPriority(prioridad)”, para darle dicha prioridad.

Añadimos un else a “if(palillosCogidos)”, y en este primero comprobaremos que el estado de prioridad sea menor que “1” (Para no salirnos del limite de prioridad), si lo es, reducimos número con un “prioridad--”, y cómo en el caso anterior aplicamos la prioridad en este hilo con “Thread.currentThread().setPriority(prioridad)”  

El “Thread.currentThread().setPriority(prioridad)” se compone de el currentThread(), que permite usar nuestro Thread actual, y el setPriority, que nos permite poner un número entre 1 y 10, cuando menor sea el número más prioridad para nuestro hilo
 
int prioridad;
    
```java
public void run() {
        while (true){
            if(Thread.currentThread().getName().equals("Thread-0")){
                zurdo = true;
            } else {
                zurdo = false;
            }
            palillosCogidos=
                    this.gestorPalillos.intentarCogerPalillos(
                            posPalilloIzq, posPalilloDer, zurdo );
            if (palillosCogidos){
                comer();
                this.gestorPalillos.liberarPalillos(
                        posPalilloIzq,
                        posPalilloDer, zurdo);
                dormir();
                Prioridad = 10;
                Thread.currentThread().setPriority(Prioridad);
            } else {
                if(Prioridad > 1) {
                    Prioridad--;
                    Thread.currentThread().setPriority(Prioridad);
                }
            }
        } //Fin del while true
    } //Fin del run()
```
