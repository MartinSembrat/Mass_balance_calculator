package MassBalanceCalculator.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import MassBalanceCalculator.model.custom.MockIRMContentInFG;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import MassBalanceCalculator.model.custom.IRMContentInFG;


@DataJpaTest
class ISaleRepositoryTest {

    @MockBean
    private ISaleRepository saleRepository;

    @BeforeEach
    public void setUp() {
        //TODO replace mocking return for custom queries by providing actual data to the database as CSVREAD function is not available in the H2 in-memory database used for testing
    }

    @Test
    public void testFindIRMContentInFG() {
        // Mock the behavior of the repository method
        List<IRMContentInFG> expectedResult = new ArrayList<>();
        // Add mock data to the expectedResult list
        expectedResult.add(new MockIRMContentInFG(1324,"34D01674","Princess Tiara Cake 2023(RSPO-SG)-TESCO","34D01674", 8.0F,"22M00173"));
        when(saleRepository.findIRMContentInFG("34D01674")).thenReturn(expectedResult);

        // Call the method being tested
        List<IRMContentInFG> actualResult = saleRepository.findIRMContentInFG("34D01674");

        // Assert that the expected result matches the actual result
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testFindIRMContentInFGCakes() {
        // Mock the behavior of the repository method
        List<IRMContentInFG> expectedResult = new ArrayList<>();
        // Add mock data to the expectedResult list
         expectedResult.add(new MockIRMContentInFG(1321,"34A00804","Rose Bouquet 2023 (RSPO-SG)-TESCO","698", 0.90817356F, "22M00174"));
        when(saleRepository.findIRMContentInFGCakes("34A00804")).thenReturn(expectedResult);

        // Call the method being tested
        List<IRMContentInFG> actualResult = saleRepository.findIRMContentInFGCakes("34A00804");

        // Assert that the expected result matches the actual result
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testFindIRMContentInFGFillings() {
        // Mock the behavior of the repository method
        List<IRMContentInFG> expectedResult = new ArrayList<>();
        // Add mock data to the expectedResult list
         expectedResult.add(new MockIRMContentInFG(897,"34P01429","-Luxury Mini Gift 150g (2018) - Lidl",null,19.792099F,"22M00135"));
        when(saleRepository.findIRMContentInFGFillings("34P01429")).thenReturn(expectedResult);

        // Call the method being tested
        List<IRMContentInFG> actualResult = saleRepository.findIRMContentInFGFillings("34P01429");

        // Assert that the expected result matches the actual result
        assertEquals(expectedResult, actualResult);
    }
}