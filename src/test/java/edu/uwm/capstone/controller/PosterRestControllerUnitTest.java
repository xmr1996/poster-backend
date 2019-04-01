//package edu.uwm.capstone.controller;
//
//
//import edu.uwm.capstone.db.PosterDao;
//import edu.uwm.capstone.model.Poster.Poster;
//import edu.uwm.capstone.sql.exception.DaoException;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import javax.servlet.http.HttpServletResponse;
//import static edu.uwm.capstone.util.TestDataUtility.*;
//
//import java.io.IOException;
//
//import static edu.uwm.capstone.util.TestDataUtility.randomAlphabetic;
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class PosterRestControllerUnitTest {
//    /**
//     * This test class uses Mockito framework to mock out all of the dependencies for the class under test and isolate it from the rest of the application.
//     * This way the test suite verifies if the class under test works correctly assuming all of its dependencies are behaving as expected.
//     */
//
//    @Mock
//    private PosterDao posterDao;
//
//    @Mock
//    private HttpServletResponse response;
//
//    @InjectMocks
//    private PosterRestController posterRestController;
//
//    private static final String TEST_ERROR_MESSAGE = randomAlphabetic(15);
//
//    @Test
//    public void create() throws IOException {
//        Poster posterToCreate = posterWithTestValues();
//        when(posterDao.create(any(Poster.class))).thenReturn(posterToCreate);
//
//        Poster createdPoster = posterRestController.create(posterToCreate, response);
//        assertEquals(posterToCreate, createdPoster);
//    }
//
//    @Test
//    public void createNull() throws IOException {
//        posterRestController.create(null, response);
//
//        verify(response, times(1)).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Received null Poster object");
//    }
//
//
//    @Test
//    public void createException() throws IOException {
//        Poster posterToCreate = posterWithTestValues();
//        when(posterDao.create(any(Poster.class))).thenThrow(new DaoException(TEST_ERROR_MESSAGE));
//
//        posterRestController.create(posterToCreate, response);
//        verify(response, times(1)).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, TEST_ERROR_MESSAGE);
//    }
//
//    @Test
//    public void update() throws IOException {
//        Poster posterToUpdate = posterWithTestValues();
//        when(posterDao.read(anyLong())).thenReturn(posterToUpdate);
//
//        posterRestController.update(posterToUpdate, response);
//        verify(posterDao, times(1)).update(posterToUpdate);
//    }
//
//    @Test
//    public void updateException() throws IOException {
//        Poster posterToUpdate = posterWithTestValues();
//        when(posterDao.read(anyLong())).thenReturn(posterToUpdate);
//        doThrow(new DaoException(TEST_ERROR_MESSAGE)).when(posterDao).update(any(Poster.class));
//
//        posterRestController.update(posterToUpdate, response);
//        verify(response, times(1)).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, TEST_ERROR_MESSAGE);
//    }
//
//    @Test
//    public void updatePreconditionFailed() throws IOException {
//        posterRestController.update(posterWithTestValues(), response);
//        verify(response, times(1)).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Poster Id must not be null");
//    }
//
//    @Test
//    public void readById() throws IOException {
//        Poster poster = posterWithTestValues();
//        when(posterDao.read(anyLong())).thenReturn(poster);
//
//        Poster receivedPoster = posterRestController.readByPosterID(poster.getPoster_id(), response);
//        assertEquals(poster, receivedPoster);
//    }
//
//    @Test
//    public void readByIdNotFound() throws IOException {
//        String posterId = randomAlphabetic(randomInt(1, 100));
//        when(posterDao.read(anyLong())).thenReturn(null);
//
//        posterRestController.readByPosterID(posterId, response);
//        verify(response, times(1)).sendError(HttpServletResponse.SC_NOT_FOUND, "Poster with ID: " + posterId + " not found.");
//    }
//
//}
//