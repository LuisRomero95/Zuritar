
package com.encomienda.servicios;

import java.util.List;


public interface Crud <T, K>{
    
    public void Registrar (T Object);
    
    public void Editar(T Object);
    
    public void Eliminar(T Object);
    
    public T BuscarPorId (int id);
    
    public List<T> Consultar();
}
