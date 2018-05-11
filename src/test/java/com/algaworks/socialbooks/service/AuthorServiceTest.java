package com.algaworks.socialbooks.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.algaworks.socialbooks.repository.AuthorsRepository;
import com.algaworks.socialbooks.services.AuthorsService;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceTest {

    @InjectMocks
    private AuthorsService authorsService;

    @Mock
    private AuthorsRepository authorsRepository;

    @Test
    public void findAllAuthors() {
        final UUID notificationId = UUID.randomUUID();

        when(authorsRepository.findAll()).thenReturn(Optional.of(new ArrayList<>()));

        authorsService.list();

        verify(authorsRepository, times(1)).findAll();
    }
}
