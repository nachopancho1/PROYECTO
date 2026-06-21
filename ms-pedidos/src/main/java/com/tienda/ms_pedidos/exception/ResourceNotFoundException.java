package com.tienda.ms_pedidos.exception;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String m) { super(m); }
}