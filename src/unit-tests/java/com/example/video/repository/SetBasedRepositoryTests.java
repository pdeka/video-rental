package com.example.video.repository;

import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SetBasedRepositoryTests {

    @Test
    public void shouldDeleteCollectionOfReturns(){

       SetBasedRepository<String> repo = new SetBasedRepository<String>(ImmutableSet.of("1","2","3","4"));
        repo.removeAll(ImmutableSet.of("2","3"));
        Set<String> repoRemnant = repo.selectAll();
        assertThat(repoRemnant.containsAll(ImmutableSet.of("1", "4")), is(true));
        assertThat(repoRemnant.contains("2"), is(false));
        assertThat(repoRemnant.contains("3"), is(false));
    }
}
