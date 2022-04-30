//package pl.wszola.api.rest;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import pl.wszola.api.request.ReservationRequest;
//import pl.wszola.infrastructure.entity.Person;
//import pl.wszola.infrastructure.entity.PersonType;
//import pl.wszola.infrastructure.entity.RentItem;
//import pl.wszola.infrastructure.persistence.reservation.ReservationRepositoryJPA;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class ReservationControllerTest {
//    private static final String MAKE_RESERVATION_ENDPOINT = "/reservation/make";
//    private static final Person LESSOR = new Person(UUID.randomUUID().toString(),
//            "Jan", "Rodo", PersonType.LESSOR);
//    private static final RentItem RENT_ITEM = new RentItem(UUID.randomUUID().toString(),
//            "biuro", BigDecimal.valueOf(2700), 35.5, LESSOR);
//    private static final LocalDate RENT_START = LocalDate.of(2022, 6, 12);
//    private static final LocalDate RENT_FINISH= LocalDate.of(2022, 6, 19);
//    private static final Person RENTER = new Person(UUID.randomUUID().toString(),
//            "Anrzej", "Bokser", PersonType.RENTER);
//
//    @Autowired
//    private MockMvc mvc;
//    @Autowired
//    private ObjectMapper mapper;
//    @Autowired
//    private ReservationRepositoryJPA reservationRepositoryJPA;
//
//    @BeforeEach
//    void setUp() {
//        reservationRepositoryJPA.deleteAll();
//    }
//
//    @Test
//    public void createAccount_ShouldReturn201Status() throws Exception {
//        given
//        ReservationRequest request = new ReservationRequest(RENT_ITEM,RENT_START,RENT_FINISH,RENTER);
//
//        when then
//        createReservationRequest(request)
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }
//
//    private ResultActions createReservationRequest(ReservationRequest request) throws Exception {
//        return mvc.perform(MockMvcRequestBuilders.post(MAKE_RESERVATION_ENDPOINT)
//                .content(mapper.writeValueAsString(request))
//                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
//    }
//}