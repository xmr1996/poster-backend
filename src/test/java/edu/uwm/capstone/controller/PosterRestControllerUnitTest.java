package edu.uwm.capstone.controller;


import edu.uwm.capstone.db.PosterDao;
import edu.uwm.capstone.model.Poster.Poster;
import edu.uwm.capstone.sql.exception.DaoException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletResponse;
import static edu.uwm.capstone.util.TestDataUtility.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static edu.uwm.capstone.util.TestDataUtility.randomAlphabetic;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PosterRestControllerUnitTest {
    /**
     * This test class uses Mockito framework to mock out all of the dependencies for the class under test and isolate it from the rest of the application.
     * This way the test suite verifies if the class under test works correctly assuming all of its dependencies are behaving as expected.
     */

    @Mock
    private PosterDao posterDao;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private PosterRestController posterRestController;

    private static final String TEST_ERROR_MESSAGE = randomAlphabetic(15);

    @Test
    public void create() throws IOException {
        Poster posterToCreate = posterWithTestValues();
        when(posterDao.create(any(Poster.class))).thenReturn(posterToCreate);

        Poster createdPoster = posterRestController.create(posterToCreate, response);
        assertEquals(posterToCreate, createdPoster);
    }

    @Test
    public void createNull() throws IOException {
        posterRestController.create(null, response);

        verify(response, times(1)).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Received null Poster object");
    }


    @Test
    public void createException() throws IOException {
        Poster posterToCreate = posterWithTestValues();
        when(posterDao.create(any(Poster.class))).thenThrow(new DaoException(TEST_ERROR_MESSAGE));

        posterRestController.create(posterToCreate, response);
        verify(response, times(1)).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, TEST_ERROR_MESSAGE);
    }

    @Test
    public void update() throws IOException {
        Poster posterToUpdate = posterWithTestValues();
        when(posterDao.read(anyString())).thenReturn(posterToUpdate);

        posterRestController.update(posterToUpdate, response);
        verify(posterDao, times(1)).update(posterToUpdate);
    }

    @Test
    public void updateException() throws IOException {
        Poster posterToUpdate = posterWithTestValues();
        when(posterDao.read(anyString())).thenReturn(posterToUpdate);
        doThrow(new DaoException(TEST_ERROR_MESSAGE)).when(posterDao).update(any(Poster.class));

        posterRestController.update(posterToUpdate, response);
        verify(response, times(1)).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, TEST_ERROR_MESSAGE);
    }

    @Test
    public void updatePreconditionFailed() throws IOException {
        posterRestController.update(posterWithTestValues(), response);
        verify(response, times(1)).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Could not update Poster - record not found.");
    }

    @Test
    public void readByIdNotFound() throws IOException {
        String posterId = randomAlphanumeric(randomInt(1, 100));
        when(posterDao.read(anyString())).thenThrow(DaoException.class);

        posterRestController.readByPosterID(posterId, response);
        verify(response, times(1)).sendError(HttpServletResponse.SC_NOT_FOUND, null);
    }

    @Test
    public void getPostersByStatus(){
        Poster poster = posterWithTestValues();
        List<Poster> postersList = new ArrayList<>();
        postersList.add(poster);
        when(posterDao.getPosterByStatus(anyString())).thenReturn(postersList);
        List<Poster> returnedPosters = posterRestController.getPostersByStatus(poster.getStatus());
        assertEquals(postersList,returnedPosters);
    }

    @Test
    public void getTop6R1(){
        Poster poster = posterWithTestValues();
        List<Poster> posterList = new ArrayList<>();
        posterList.add(poster);
        when(posterDao.getTop6R1(anyString())).thenReturn(posterList);
        List<Poster> returnedPosters = posterRestController.getTop6R1(poster.getStatus());
        assertEquals(posterList,returnedPosters);
    }

    @Test
    public void getTop6R2(){
        Poster poster = posterWithTestValues();
        List<Poster> posterList = new ArrayList<>();
        posterList.add(poster);
        when(posterDao.getTop6R2(anyString())).thenReturn(posterList);
        List<Poster> returnedPosters = posterRestController.getTop6R2(poster.getStatus());
        assertEquals(posterList,returnedPosters);
    }

    @Test
    public void delete() throws IOException {
        Poster posterToCreate = posterWithTestValues();
        posterDao.create(posterToCreate);
        posterRestController.deleteByJudgeId(posterToCreate.getPoster_id(), response);
        verify(posterDao, times(1)).delete(posterToCreate.getPoster_id());
    }

    @Test
    public void clearTable() throws IOException {
        posterRestController.clearTable( response);
        verify(posterDao, times(1)).clearTable();
    }

    @Test
    public void testImportCSV() {
        int num_of_posters = randomInt(0, 100);
        List<Poster> posters = new ArrayList<>();

        for(int i = 0; i < num_of_posters; i++){
            posters.add(posterWithTestValues());
        }

        posterRestController.importCSV(posters);
        verify(posterDao, times(num_of_posters)).create(any(Poster.class));
    }

}
