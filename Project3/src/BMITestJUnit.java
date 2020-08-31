import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//class used for testing BMI class methods and output

class BMITestJUnit {

    static BMI bmi;

    @BeforeAll
    static void beforeAll(){
        bmi = new BMI();
    }

    @Test
    void setWeight() {
    }

    @Test
    void setHeight() {
    }

    @Test
    void setOption() {
    }

    @Test
    void getWeight() {
        assertEquals(70, bmi.getWeight());
    }

    @Test
    void getHeight() {
        assertEquals(170, bmi.getHeight());
    }

    @Test
    void getOption() {
        assertEquals(1, bmi.getOption());
    }
    @Test
    void getLastName() {
        assertEquals("Doe", bmi.getLastName());
    }

    @Test
    void getFirstName() {
        assertEquals("John", bmi.getFirstName());
    }

    @Test
    void testParameterizedConstructor(){
        BMI invalidBMI = new BMI("Frank","Smith",20,20,3);
        assertEquals(20, invalidBMI.getWeight());
        assertEquals(20, invalidBMI.getHeight());
        assertEquals(1, invalidBMI.getOption());
        assertEquals("Frank", invalidBMI.getFirstName());
        assertEquals("Smith", invalidBMI.getLastName());
    }

    @Test
    void calcBMI() {
        assertEquals(24.22,Math.round(bmi.calcBMI()*100.0)/100.0);
    }
}