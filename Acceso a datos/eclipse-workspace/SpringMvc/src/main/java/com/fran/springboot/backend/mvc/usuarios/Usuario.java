package com.fran.springboot.backend.mvc.usuarios;

import java.util.List;
import com.fran.springboot.backend.mvc.models.entity.Cliente;
import com.fran.springboot.backend.mvc.usuarios.dto.UsuarioInsertDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Usuario {
    
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	@Column(length = 150, nullable = false)
    private String nombre;
	@Column(length = 150, nullable = false, unique = true)
    private String correo;
    @Column(length = 150, nullable = false)
    private String password;    
    @Column(length = 150)
	private String avatar;    
    @OneToMany(mappedBy="creador")
    private List<Cliente> clientes;
    
    public Usuario(String nombre, String correo, String password) {
		this.nombre = nombre;
		this.correo = correo;
		this.password = password;
		this.avatar = "";
	}
    
    public Usuario(String nombre, String correo, String password, String avatar) {
		this.nombre = nombre;
		this.correo = correo;
		this.password = password;
		this.avatar = avatar;
	}

    public Usuario(UsuarioInsertDto dto) {
        this.nombre = dto.getNombre();
        this.correo = dto.getCorreo();
        this.password = dto.getPassword();
        if(dto.getAvatar()!=null) {
        	this.avatar = dto.getAvatar();
        }else {
        	this.avatar = "";
        }
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
}
