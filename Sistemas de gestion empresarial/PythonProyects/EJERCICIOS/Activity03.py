#In order to save a user and a password in a list, we have to save both
#variables inside a second list, as i do in the next example:

registers = [["user1", "pass1"], ["user2, pass2"], ["user3", "pass3"]]
print(registers)

#This is slow process with a lot of info, theres a better way using dictionaries

registers = {"user1": "pass1", "user2": "pass2", "user3": "pass3"}
print(registers)




import hashlib

# Función para hashear contraseñas con SHA-256
def hash_password(password):
    return hashlib.sha256(password.encode()).hexdigest()

# --- Usando Lista (cada elemento es una tupla) ---
usuarios_lista = []

# Agregar usuarios a la lista
usuarios_lista.append(("usuario1", hash_password("clave123")))
usuarios_lista.append(("usuario2", hash_password("secreto456")))
usuarios_lista.append(("usuario3", hash_password("miPassword789")))
usuarios_lista.append(("usuario4", hash_password("12345abc")))
usuarios_lista.append(("usuario5", hash_password("passAdmin999")))

# Consultar en la lista
def buscar_usuario_lista(nombre):
    for usuario, password in usuarios_lista:
        if usuario == nombre:
            return f"Usuario encontrado: {usuario}, Hash: {password}"
    return "Usuario no encontrado."

# --- Usando Diccionario ---
usuarios_dict = {}

# Agregar usuarios al diccionario
usuarios_dict["usuario1"] = hash_password("clave123")
usuarios_dict["usuario2"] = hash_password("secreto456")
usuarios_dict["usuario3"] = hash_password("miPassword789")
usuarios_dict["usuario4"] = hash_password("12345abc")
usuarios_dict["usuario5"] = hash_password("passAdmin999")

# Consultar en el diccionario
def buscar_usuario_dict(nombre):
    if nombre in usuarios_dict:
        return f"Usuario encontrado: {nombre}, Hash: {usuarios_dict[nombre]}"
    return "Usuario no encontrado."

# --- Consultas ---
print(buscar_usuario_lista("usuario3"))  # Consulta en lista
print(buscar_usuario_lista("usuario6"))  # Usuario inexistente

print(buscar_usuario_dict("usuario5"))  # Consulta en diccionario
print(buscar_usuario_dict("usuario10"))  # Usuario inexistente
