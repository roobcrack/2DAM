package com.ruben.WeatherApp.Entities;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class DatosController {

    @PostMapping("/procesar-datos")
    public ResponseEntity<Map<String, String>> procesarDatos(@RequestBody DatosRequest datos) {
        // Lógica para procesar los datos recibidos
        String resultadoProcesado = "Procesado: " + datos.getDato(); // Procesamiento básico

        // Devolver el resultado como respuesta
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", resultadoProcesado);

        return ResponseEntity.ok(response);
    }

    // Clase interna para recibir los datos
    public static class DatosRequest {
        private String dato;

        // Getters y Setters
        public String getDato() {
            return dato;
        }

        public void setDato(String dato) {
            this.dato = dato;
        }
    }
}
