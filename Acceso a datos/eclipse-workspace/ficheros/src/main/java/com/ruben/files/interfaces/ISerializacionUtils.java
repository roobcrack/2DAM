package com.ruben.files.interfaces;

import com.ruben.files.entidades.Alumno;

public interface ISerializacionUtils {
	public boolean serializacionObjeto(String rutaCompleta, Alumno alumno);
}
