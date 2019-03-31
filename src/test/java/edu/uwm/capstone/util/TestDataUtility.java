package edu.uwm.capstone.util;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import edu.uwm.capstone.model.Judge.Judge;
import edu.uwm.capstone.model.Poster.Poster;
import edu.uwm.capstone.model.Score.Score;
import org.apache.commons.lang3.RandomStringUtils;

import edu.uwm.capstone.model.profile.Profile;

public class TestDataUtility {

    /**
     * This is a utility class and as such it only has a private constructor.
     */
    protected TestDataUtility() {
    }

    /**
     * Generate a {@link Profile} object that is fully loaded with random values for testing purposes.
     * @return {@link Profile}
     */
    public static Profile profileWithTestValues() {
        Profile profile = new Profile();
        // intentionally left blank -- profile.setId();
        profile.setName(randomAlphabetic(randomInt(1, 100)));
        profile.setProject(randomAlphanumeric(randomInt(1, 100)));
        // intentionally left blank -- profile.setCreatedDate(randomLocalDateTime());
        // intentionally left blank -- profile.setUpdatedDate(randomLocalDateTime());
        return profile;
    }

    public static Judge judgeWithTestValues() {
        Judge judge = new Judge();
        judge.setFirst_name(randomAlphabetic(randomInt(1, 100)));
        judge.setPin(randomAlphabetic(randomInt(1, 100)));
        judge.setLast_name(randomAlphabetic(randomInt(1, 100)));
        judge.setStatus("Graduate");
        judge.setEmail(randomAlphabetic(randomInt(1, 100)));
        return judge;
    }

    public static Poster posterWithTestValues() {
        Poster poster = new Poster();
        poster.setFirst_name(randomAlphabetic(randomInt(1, 100)));
        poster.setPin(randomAlphabetic(randomInt(1, 100)));
        poster.setLast_name(randomAlphabetic(randomInt(1, 100)));
        poster.setStatus("Graduate");
        poster.setEmail(randomAlphabetic(randomInt(1, 100)));
        poster.setDepartment(randomAlphabetic(randomInt(1, 100)));
        poster.setPoster_id(randomAlphabetic(randomInt(1, 100)));
        return poster;
    }

    public static Score scoreWithTestValues() {
        Score score = new Score();
//        score.setPoster_id(randomAlphabetic(randomInt(1, 100)));
//        score.setJudge_id(randomLong(1L,100L));
        score.setRound(randomInt(0,2));
        score.setResearch_score(randomInt(1,50));
        score.setComm_score(randomInt(1,30));
        score.setPoster_score(randomInt(1,20));
        return score;
    }

    /**
     * Generate a random {@link Long} using a minimum value of 1L and a maximum value of {@link Long#MAX_VALUE}.
     * @return random {@link Long}
     */
    public static Long randomLong() {
        return randomLong(1L, Long.MAX_VALUE);
    }

    /**
     * Generate a random {@link Long} using the provided minimum and maximum values.
     * @param min {@link Long} minimum value
     * @param max {@link Long} maximum value
     * @return random {@link Long}
     */
    public static Long randomLong(Long min, Long max) {
        return new Random().longs(min, max).findAny().getAsLong();
    }

    /**
     * Generate a random {@link Integer} using a minimum value of 1 and a maximum value of {@link Integer#MAX_VALUE}.
     * @return random {@link Integer}
     */
    public static int randomInt() {
        return randomInt(1, Integer.MAX_VALUE);
    }

    /**
     * Generate a random {@link int} using the provided minimum and maximum values.
     * @param min {@link int} minimum value
     * @param max {@link int} maximum value
     * @return random {@link int}
     */
    public static int randomInt(int min, int max) {
        return new Random().ints(min, max).findAny().getAsInt();
    }

    /**
     * Generate a {@link String} that contains the provided number of random alphabetic characters.
     * @param characterCount Number of characters
     * @return random {@link String} of alphabetic characters
     */
    public static String randomAlphabetic(int characterCount) {
        return RandomStringUtils.randomAlphabetic(characterCount);
    }

    /**
     * Generate a {@link String} that contains the provided number of random alphanumeric characters.
     * @param characterCount Number of characters
     * @return random {@link String} of alphanumeric characters
     */
    public static String randomAlphanumeric(int characterCount) {
        return RandomStringUtils.randomAlphanumeric(characterCount);
    }

    public static LocalDateTime randomLocalDateTime() {
        LocalDateTime start = LocalDateTime.of(1900, randomMonth(), 1, randomInt(0, 23), randomInt(1, 59));
        long days = ChronoUnit.DAYS.between(start, LocalDateTime.now());
        return start.plusDays(new Random().nextInt((int) days + 1));
    }

    public static Month randomMonth() {
        List<Month> months = Arrays.asList(Month.values());
        int index = new Random().nextInt(months.size());
        return months.get(index);
    }

}
