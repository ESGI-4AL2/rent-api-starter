package rent.api;

import fr.esgi.rent.api.RentalPropertyResource;
import fr.esgi.rent.services.RentalPropertiesFileParser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static rent.samples.RentalPropertySample.rentalProperties;

@ExtendWith(MockitoExtension.class)
public class RentalPropertyResourceTest {

  @InjectMocks
  private RentalPropertyResource rentalProperty;

  @Mock
  private HttpServletRequest httpServletRequest;

  @Mock
  private HttpServletResponse httpServletResponse;

  @Mock
  private RentalPropertiesFileParser rentalPropertiesFileParser;

  @Test
  void shouldGetRentalproperties() {
    var expectedRentalProperties = rentalProperties();

    when(rentalPropertiesFileParser.parse("rentalProperties.csv")).thenReturn(expectedRentalProperties);

    var rentalProperties = rentalProperty.getRentalProperties();

    assertThat(rentalProperties).containsExactlyInAnyOrderElementsOf(expectedRentalProperties);
    verify(rentalPropertiesFileParser).parse("rentalProperties.csv");
  }

}
