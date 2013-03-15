package com.example.video.web.controller;

import com.example.video.domain.*;
import com.example.video.repository.RentalRepository;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.sun.istack.internal.Nullable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpSession;
import java.util.*;

import static com.example.video.domain.dates.Duration.ofDays;
import static com.example.video.domain.dates.LocalDate.today;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: Client
 * Date: 15/03/13
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReturnMoviesControllerTest {
    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private HttpSession httpSession;

    @Test
    public void shouldRemoveRentalForEachMovie()
    {
        Rental avatarRental;

        String[] movieNames = {"Movie2", "Movie3"};

        Customer customer = new Customer("James Madison");
        Set<Rental> rentals = new HashSet<Rental>(6);

        for(int i=0; i < 6; i++)
        {
            Period period = Period.of(today(), ofDays(10));
            Movie movie = new Movie("Movie" + i, new NewReleasePrice());
            rentals.add(new Rental(customer, movie, period));
        }

        ImmutableSet<Rental> expectedReturnedRentals =
                ImmutableSet.of(Iterables.get(rentals,1), Iterables.get(rentals,2));

        when(httpSession.getAttribute("customer")).thenReturn(customer);
        when(rentalRepository.currentRentalsFor(customer)).thenReturn(rentals);

        ReturnMoviesController controller = new ReturnMoviesController(rentalRepository);
        controller.put(movieNames, httpSession);
        ArgumentCaptor<Collection> captor = ArgumentCaptor.forClass(Collection.class);

        verify(httpSession, times(1)).getAttribute("customer");
        verify(rentalRepository, times(1)).currentRentalsFor(customer);
        verify(rentalRepository, times(1)).removeAll(captor.capture());
        Collection<Rental> capturedRentals = captor.getValue();
        Set<String> actualMovieNames = ImmutableSet.copyOf(
                Iterables.<Rental, String>transform(capturedRentals,
                        new Function<Rental, String>() {
            @Override
            public String apply(@Nullable Rental rental) {
                return rental.getMovie().getTitle();
            }
        }));

        List<String> expectedMovieNames = Arrays.asList(movieNames);
//        assertTrue(Iterables.elementsEqual(actualMovieNames, Arrays.asList(movieNames)));

        assertTrue(actualMovieNames.containsAll(expectedMovieNames));
    }
}

