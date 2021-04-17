package ru.otus.spring.shell;

import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.repository.mongo.AuthorMongoRepository;
import ru.otus.spring.repository.mongo.BookMongoRepository;
import ru.otus.spring.repository.mongo.CommentMongoRepository;
import ru.otus.spring.repository.mongo.GenreMongoRepository;

import static ru.otus.spring.config.JobConfig.MIGRATION_JOB;

@ShellComponent
public class BatchComands {
    private final JobOperator jobOperator;
    private final GenreMongoRepository genreMongoRepository;
    private final AuthorMongoRepository authorMongoRepository;
    private final BookMongoRepository bookMongoRepository;
    private final CommentMongoRepository commentMongoRepository;

    public BatchComands(JobOperator jobOperator,
                      GenreMongoRepository genreMongoRepository,
                      AuthorMongoRepository authorMongoRepository,
                      BookMongoRepository bookMongoRepository,
                      CommentMongoRepository commentMongoRepository) {
        this.jobOperator = jobOperator;
        this.genreMongoRepository = genreMongoRepository;
        this.authorMongoRepository = authorMongoRepository;
        this.bookMongoRepository = bookMongoRepository;
        this.commentMongoRepository = commentMongoRepository;
    }

    @ShellMethod(value = "Start migration job", key = {"start"})
    public String startMigration() {
        try {
            return String.format("Migration job has been started with id %d", jobOperator.start(MIGRATION_JOB, ""));
        } catch (Exception e) {
            return "Error starting migration job";
        }
    }

    @ShellMethod(value = "Get all genres", key = {"genres"})
    public String getGenres() {
        return String.format("Genres: %s", genreMongoRepository.findAll());
    }

    @ShellMethod(value = "Get all authors", key = {"authors"})
    public String getAuthors() {
        return String.format("Authors: %s", authorMongoRepository.findAll());
    }

    @ShellMethod(value = "Get all books", key = {"books"})
    public String getBooks() {
        return String.format("Books: %s", bookMongoRepository.findAll());
    }

    @ShellMethod(value = "Get all comments", key = {"comments"})
    public String getComments() {
        return String.format("Comments: %s", commentMongoRepository.findAll());
    }

    @ShellMethod(value = "Get migration job", key = {"status"})
    public String getStatusMigrationJob(@ShellOption long id) {
        try {
            return String.format("Status migration job: %s", jobOperator.getSummary(id));
        } catch (Exception e) {
            return "Error status migration job";
        }
    }
}
