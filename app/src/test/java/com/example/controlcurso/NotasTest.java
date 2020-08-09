package com.example.controlcurso;

import org.junit.Test;

import static org.junit.Assert.*;

public class NotasTest {

    @Test
    public void nota() {
        Notas dato = new Notas();
        assertEquals(0.15,dato.convertirPrueba("15%"));
    }
}