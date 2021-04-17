package ru.otus.spring.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.repository.mongo.AuthorMongoRepository;
import ru.otus.spring.repository.mongo.BookMongoRepository;
import ru.otus.spring.repository.mongo.CommentMongoRepository;
import ru.otus.spring.repository.mongo.GenreMongoRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.batch.core.ExitStatus.COMPLETED;
import static ru.otus.spring.config.JobConfig.MIGRATION_JOB;

@SpringBootTest
@SpringBatchTest
class JobConfigTest {
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @Autowired
    private GenreMongoRepository genreMongoRepository;
    @Autowired
    private AuthorMongoRepository authorMongoRepository;
    @Autowired
    private BookMongoRepository bookMongoRepository;
    @Autowired
    private CommentMongoRepository commentMongoRepository;

    @BeforeEach
    void clearMetaData() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    @Test
    void jobTest() throws Exception {
        assertEquals(0, genreMongoRepository.count());
        assertEquals(0, authorMongoRepository.count());
        assertEquals(0, bookMongoRepository.count());
        assertEquals(0, commentMongoRepository.count());

        Job job = jobLauncherTestUtils.getJob();

        assertNotNull(job);
        assertEquals(MIGRATION_JOB, job.getName());

        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        assertEquals(COMPLETED, jobExecution.getExitStatus());

        assertEquals(3, genreMongoRepository.count());
        assertEquals(3, authorMongoRepository.count());
        assertEquals(3, bookMongoRepository.count());
        assertEquals(6, commentMongoRepository.count());
    }
}
