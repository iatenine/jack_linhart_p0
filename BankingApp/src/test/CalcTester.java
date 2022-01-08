package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalcTester {

    @BeforeAll
    public static void setup(){
        System.out.println("Before entire class is run");
    }

    @BeforeEach
    public void before(){
        System.out.println("Before each method");
    }

    @Test
    public void test(){
        int x = 1;

        // Can skip Assertions. because of static import
        assertEquals(x, 1);
    }

    @Test
    public void test2(){
        int x = 3;

        assertEquals(x, 3);
    }
}
