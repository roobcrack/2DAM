####################################################
## 6. Módulos
####################################################
# Puedes importar módulos
import math

print(math.sqrt(16)) # => 4.0
# Puedes obtener funciones específicas desde un módulo CFGS.
from math import ceil, floor
print(ceil(3.7)) # => 4.0
print(floor(3.7))# => 3.0
# Puedes importar todas las funciones de un módulo
# Precaución: Esto no es recomendable
from math import *
# Puedes acortar los nombres de los módulos
import math as m
math.sqrt(16) == m.sqrt(16) # => True

# Los módulos de Python son sólo archivos ordinarios de Python.
# Puedes escribir tus propios módulos e importarlos. El nombre del módulo
# es el mismo del nombre del archivo.
# Puedes encontrar que funciones y atributos definen un módulo.
import math
print(dir(math))

from Humano import Humano
# Ahora puedes usar la clase Humano en tu script
persona = Humano("Juan",15)
print(persona.decir("Hola,\n ¿cómo estás?"))
