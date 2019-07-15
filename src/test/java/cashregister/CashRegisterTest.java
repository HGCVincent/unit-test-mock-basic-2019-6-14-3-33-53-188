package cashregister;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CashRegisterTest {

    static ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();


    @BeforeAll
    static public void setup(){
        System.setOut(new PrintStream(byteArrayOutputStream));
    }

    @Test
    public void should_print_the_real_purchase_when_call_process() {
        Item item = new Item("tea",10);
        Item[] items = {item};

        Purchase purchase =new Purchase(items);
        Printer printer = new Printer();
        CashRegister cashRegister = new CashRegister(printer);
        cashRegister.process(purchase);
        purchase.asString();

        assertEquals("tea\t10.0\n",byteArrayOutputStream.toString());
    }

    @Test
    public void should_print_the_stub_purchase_when_call_process() {
        Purchase purchase = mock(Purchase.class);
        Printer printer= new Printer();
        CashRegister cashRegister = new CashRegister(printer);

        when(purchase.asString()).thenReturn("purchase...");
        cashRegister.process(purchase);

        assertEquals("purchase...",byteArrayOutputStream.toString());
    }

    @Test
    public void should_verify_with_process_call_with_mockito() {
        Purchase purchase = mock(Purchase.class);
        Printer printer= new Printer();
        CashRegister cashRegister = new CashRegister(printer);

        when(purchase.asString()).thenReturn("purchase...");
        cashRegister.process(purchase);

        verify(purchase).asString();
    }

}
