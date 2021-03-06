package edu.uwm.capstone;

import edu.uwm.capstone.db.*;
import edu.uwm.capstone.sql.statement.ISqlStatementsFileLoader;
import edu.uwm.capstone.sql.statement.SqlStatementsFileLoader;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ConfigurationProperties(prefix = "service")
@EnableSwagger2
public class ApplicationConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);

    protected String dbDriverClassName;
    protected String dbDriverUrl;
    protected String dbUsername;
    protected String dbPassword;
    protected String dbMigrationLocation;
    protected int dbPoolMaxwait;
    protected boolean dbPoolRemoveabandoned;
    protected int dbPoolRemoveabandonedtimeout;
    protected boolean dbPoolLogabandoned;
    protected long dbPoolMaxage;
    protected String sqlStatementsResourceLocation;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        LOGGER.info("Loading DataSource");
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setDriverClassName(dbDriverClassName);
        poolProperties.setUrl(dbDriverUrl);
        poolProperties.setUsername(dbUsername);
        poolProperties.setPassword(dbPassword);
        poolProperties.setTestOnBorrow(true);
        poolProperties.setValidationQuery("SELECT 1");

        // Set additional pool properties
        poolProperties.setMaxWait(dbPoolMaxwait);
        poolProperties.setRemoveAbandoned(dbPoolRemoveabandoned);
        poolProperties.setRemoveAbandonedTimeout(dbPoolRemoveabandonedtimeout);
        poolProperties.setLogAbandoned(dbPoolLogabandoned);
        poolProperties.setMaxAge(dbPoolMaxage);
        poolProperties.setMaxActive(600);

        DataSource ds = new DataSource();
        ds.setPoolProperties(poolProperties);
        flyway(ds);

        return ds;
    }

    @Bean
    @Primary
    public Flyway flyway(DataSource dataSource) {
        LOGGER.info("Running database migration on {}", dbDriverUrl);
        Flyway flyway = new Flyway(Flyway.configure()
                .locations(dbMigrationLocation.split("\\s*,\\s*"))
                .outOfOrder(true)
                .dataSource(dataSource));
        flyway.repair();
        flyway.migrate();

        return flyway;
    }


    @Bean
    public ISqlStatementsFileLoader sqlStatementsFileLoader() {
        SqlStatementsFileLoader loader = new SqlStatementsFileLoader();
        loader.setStatementResourceLocation(sqlStatementsResourceLocation);
        return loader;
    }

    @Bean
    public ProfileDao profileDao() {
        ProfileDao profileDao = new ProfileDao();
        profileDao.setDataSource(dataSource());
        profileDao.setSqlStatementsFileLoader(sqlStatementsFileLoader());
        profileDao.setRowMapper(profileDaoRowMapper());
        return profileDao;
    }

    @Bean
    public ProfileDaoRowMapper profileDaoRowMapper() {
        return new ProfileDaoRowMapper();
    }

    @Bean
    public JudgeDao judgeDao() {
        JudgeDao judgeDao = new JudgeDao();
        judgeDao.setDataSource(dataSource());
        judgeDao.setSqlStatementsFileLoader(sqlStatementsFileLoader());
        judgeDao.setRowMapper(judgeDaoRowMapper());
        return judgeDao;
    }

    @Bean
    public JudgeDaoRowMapper judgeDaoRowMapper() {
        return new JudgeDaoRowMapper();
    }

    @Bean
    public ScoreDao scoreDao() {
        ScoreDao scoreDao = new ScoreDao();
        scoreDao.setDataSource(dataSource());
        scoreDao.setSqlStatementsFileLoader(sqlStatementsFileLoader());
        scoreDao.setRowMapper(scoreDaoRowMapper());
        return scoreDao;
    }

    @Bean
    public ScoreDaoRowMapper scoreDaoRowMapper() { return new ScoreDaoRowMapper(); }

    @Bean
    public AssignmentDao assignmentDao() {
        AssignmentDao assignmentDao = new AssignmentDao();
        assignmentDao.setDataSource(dataSource());
        assignmentDao.setSqlStatementsFileLoader(sqlStatementsFileLoader());
        assignmentDao.setRowMapper(assignmentDaoRowMapper());
        return assignmentDao;
    }

    @Bean
    public AssignmentDaoRowMapper assignmentDaoRowMapper() { return new AssignmentDaoRowMapper(); }

    @Bean
    public PosterScoreDao posterscoreDao() {
        PosterScoreDao posterscoreDao = new PosterScoreDao();
        posterscoreDao.setDataSource(dataSource());
        posterscoreDao.setSqlStatementsFileLoader(sqlStatementsFileLoader());
        posterscoreDao.setRowMapper(posterscoreDaoRowMapper());
        return posterscoreDao;
    }

    @Bean
    public PosterScoreRowMapper posterscoreDaoRowMapper() { return new PosterScoreRowMapper(); }

    @Bean
    public PosterDao posterDao(){
        PosterDao posterDao = new PosterDao();
        posterDao.setDataSource(dataSource());
        posterDao.setSqlStatementsFileLoader(sqlStatementsFileLoader());
        posterDao.setRowMapper(posterDaoRowMapper());
        return posterDao;
    }

    @Bean
    public PosterDaoRowMapper posterDaoRowMapper(){
        return new PosterDaoRowMapper();
    }

    @Bean
    public VoteDaoRowMapper voteDaoRowMapper() {return new VoteDaoRowMapper(); }

    @Bean
    public VoteDao voteDao(){
        VoteDao voteDao = new VoteDao();
        voteDao.setDataSource(dataSource());
        voteDao.setSqlStatementsFileLoader(sqlStatementsFileLoader());
        voteDao.setRowMapper(voteDaoRowMapper());
        return voteDao;
    }

    @Bean
    public AdminDaoRowMapper adminDaoRowMapper() { return new AdminDaoRowMapper(); }

    @Bean
    public AdminDao adminDao(){
        AdminDao adminDao = new AdminDao();
        adminDao.setDataSource(dataSource());
        adminDao.setSqlStatementsFileLoader(sqlStatementsFileLoader());
        adminDao.setRowMapper(adminDaoRowMapper());
        return adminDao;
    }

    @Bean
    public RoundDaoRowMapper roundDaoRowMapper() {return new RoundDaoRowMapper();}

    @Bean
    public RoundDao roundDao(){
        RoundDao roundDao = new RoundDao();
        roundDao.setDataSource(dataSource());
        roundDao.setSqlStatementsFileLoader(sqlStatementsFileLoader());
        roundDao.setRowMapper(roundDaoRowMapper());
        return roundDao;
    }

    public String getDbDriverClassName() {
        return dbDriverClassName;
    }

    public void setDbDriverClassName(String dbDriverClassName) {
        this.dbDriverClassName = dbDriverClassName;
    }

    public String getDbDriverUrl() {
        return dbDriverUrl;
    }

    public void setDbDriverUrl(String dbDriverUrl) {
        this.dbDriverUrl = dbDriverUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbMigrationLocation() {
        return dbMigrationLocation;
    }

    public void setDbMigrationLocation(String dbMigrationLocation) {
        this.dbMigrationLocation = dbMigrationLocation;
    }

    public int getDbPoolMaxwait() {
        return dbPoolMaxwait;
    }

    public void setDbPoolMaxwait(int dbPoolMaxwait) {
        this.dbPoolMaxwait = dbPoolMaxwait;
    }

    public boolean isDbPoolRemoveabandoned() {
        return dbPoolRemoveabandoned;
    }

    public void setDbPoolRemoveabandoned(boolean dbPoolRemoveabandoned) {
        this.dbPoolRemoveabandoned = dbPoolRemoveabandoned;
    }

    public int getDbPoolRemoveabandonedtimeout() {
        return dbPoolRemoveabandonedtimeout;
    }

    public void setDbPoolRemoveabandonedtimeout(int dbPoolRemoveabandonedtimeout) {
        this.dbPoolRemoveabandonedtimeout = dbPoolRemoveabandonedtimeout;
    }

    public boolean isDbPoolLogabandoned() {
        return dbPoolLogabandoned;
    }

    public void setDbPoolLogabandoned(boolean dbPoolLogabandoned) {
        this.dbPoolLogabandoned = dbPoolLogabandoned;
    }

    public long getDbPoolMaxage() {
        return dbPoolMaxage;
    }

    public void setDbPoolMaxage(long dbPoolMaxage) {
        this.dbPoolMaxage = dbPoolMaxage;
    }

    public String getSqlStatementsResourceLocation() {
        return sqlStatementsResourceLocation;
    }

    public void setSqlStatementsResourceLocation(String sqlStatementsResourceLocation) {
        this.sqlStatementsResourceLocation = sqlStatementsResourceLocation;
    }
}
