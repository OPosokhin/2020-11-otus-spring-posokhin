package ru.otus.spring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.domain.jpa.Author;
import ru.otus.spring.domain.jpa.Book;
import ru.otus.spring.domain.jpa.Comment;
import ru.otus.spring.domain.jpa.Genre;
import ru.otus.spring.domain.mongo.AuthorMongo;
import ru.otus.spring.domain.mongo.BookMongo;
import ru.otus.spring.domain.mongo.CommentMongo;
import ru.otus.spring.domain.mongo.GenreMongo;
import ru.otus.spring.repository.jpa.AuthorRepository;
import ru.otus.spring.repository.jpa.BookRepository;
import ru.otus.spring.repository.jpa.CommentRepository;
import ru.otus.spring.repository.jpa.GenreRepository;

import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class JobConfig {
    public static final String MIGRATION_JOB = "migration_job";

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;


    @Bean
    public Job migrationJob(Step genreMigrationStep,
                            Step authorMigrationStep,
                            Step bookMigrationStep,
                            Step commentMigrationStep) {
        return jobs.get(MIGRATION_JOB)
                .start(genreMigrationStep)
                .next(authorMigrationStep)
                .next(bookMigrationStep)
                .next(commentMigrationStep)
                .build();
    }
// Author

    @Bean
    public Step authorMigrationStep(RepositoryItemReader<Author> reader,
                                    ItemProcessor<Author, AuthorMongo> processor,
                                    MongoItemWriter<AuthorMongo> writer) {
        return steps.get("authorMigrationStep")
                .<Author, AuthorMongo>chunk(100)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @StepScope
    @Bean
    public RepositoryItemReader<Author> authorRepositoryItemReader(AuthorRepository repository) {
        return new RepositoryItemReaderBuilder<Author>()
                .sorts(Map.of("id", Sort.Direction.ASC))
                .repository(repository)
                .methodName("findAll")
                .arguments(List.of())
                .saveState(false)
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Author, AuthorMongo> authorToAuthorMongoItemProcessor() {
        return in -> {
            AuthorMongo out = new AuthorMongo();
            out.setId(String.valueOf(in.getId()));
            out.setName(in.getName());
            return out;
        };
    }

    @StepScope
    @Bean
    public MongoItemWriter<AuthorMongo> authorMongoItemWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<AuthorMongo>()
                .template(mongoTemplate)
                .collection("author")
                .build();
    }

    @Bean
    public Step genreMigrationStep(RepositoryItemReader<Genre> reader,
                                   ItemProcessor<Genre, GenreMongo> processor,
                                   MongoItemWriter<GenreMongo> writer) {
        return steps.get("genreMigrationStep")
                .<Genre, GenreMongo>chunk(100)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @StepScope
    @Bean
    public RepositoryItemReader<Genre> genreRepositoryItemReader(GenreRepository repository) {
        return new RepositoryItemReaderBuilder<Genre>()
                .sorts(Map.of("id", Sort.Direction.ASC))
                .repository(repository)
                .methodName("findAll")
                .arguments(List.of())
                .saveState(false)
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Genre, GenreMongo> genreToGenreMongoItemProcessor() {
        return in -> {
            GenreMongo out = new GenreMongo();
            out.setId(String.valueOf(in.getId()));
            out.setName(in.getName());
            return out;
        };
    }

    @StepScope
    @Bean
    public MongoItemWriter<GenreMongo> genreMongoItemWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<GenreMongo>()
                .template(mongoTemplate)
                .collection("genre")
                .build();
    }

    // Book

    @Bean
    public Step bookMigrationStep(RepositoryItemReader<Book> reader,
                                  ItemProcessor<Book, BookMongo> processor,
                                  MongoItemWriter<BookMongo> writer) {
        return steps.get("bookMigrationStep")
                .<Book, BookMongo>chunk(100)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @StepScope
    @Bean
    public RepositoryItemReader<Book> bookRepositoryItemReader(BookRepository repository) {
        return new RepositoryItemReaderBuilder<Book>()
                .sorts(Map.of("id", Sort.Direction.ASC))
                .repository(repository)
                .methodName("findAll")
                .arguments(List.of())
                .saveState(false)
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Book, BookMongo> bookToBookMongoItemProcessor() {
        return in -> {
            BookMongo out = new BookMongo();
            out.setId(String.valueOf(in.getId()));
            out.setName(in.getName());
            out.setGenreId(String.valueOf(in.getGenreId()));
            out.setAuthorId(String.valueOf(in.getAuthorId()));
            return out;
        };
    }

    @StepScope
    @Bean
    public MongoItemWriter<BookMongo> bookMongoItemWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<BookMongo>()
                .template(mongoTemplate)
                .collection("book")
                .build();
    }

    // Comments

    @Bean
    public Step commentMigrationStep(RepositoryItemReader<Comment> reader,
                                     ItemProcessor<Comment, CommentMongo> processor,
                                     MongoItemWriter<CommentMongo> writer) {
        return steps.get("commentsMigrationStep")
                .<Comment, CommentMongo>chunk(100)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @StepScope
    @Bean
    public RepositoryItemReader<Comment> commentRepositoryItemReader(CommentRepository repository) {
        return new RepositoryItemReaderBuilder<Comment>()
                .sorts(Map.of("id", Sort.Direction.ASC))
                .repository(repository)
                .methodName("findAll")
                .arguments(List.of())
                .saveState(false)
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Comment, CommentMongo> commentToCommentMongoItemProcessor() {
        return in -> {
            CommentMongo out = new CommentMongo();
            out.setId(String.valueOf(in.getId()));
            out.setComment(in.getComment());
            out.setBookId(String.valueOf(in.getBookId()));
            return out;
        };
    }

    @StepScope
    @Bean
    public MongoItemWriter<CommentMongo> commentMongoItemWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<CommentMongo>()
                .template(mongoTemplate)
                .collection("comment")
                .build();
    }
}
