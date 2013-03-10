package com.example.video.repository;

import com.example.video.domain.Movie;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "file:src/main/webapp/WEB-INF/applicationContext-properties.xml",
        "file:src/main/webapp/WEB-INF/applicationContext.xml"
})
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Ignore("Please note that this test is breaking because of hibernate config issues.")
    @Test
    public void shouldRetrieveAllMovies() {
        List<Movie> movies = movieRepository.selectAll();
        assertThat(movies.size(), equalTo(1));
    }
}
