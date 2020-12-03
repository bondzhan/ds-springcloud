package org.bond.yy.batch.processor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.bond.yy.batch.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


@Component
public class PersonJobComplete extends JobExecutionListenerSupport{
	private static final Logger log = LoggerFactory.getLogger(PersonJobComplete.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public PersonJobComplete(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");

			List<Person> results = jdbcTemplate.query("SELECT person_id, first_name, last_name FROM people",
					new RowMapper<Person>() {
						@Override
						public Person mapRow(ResultSet rs, int row) throws SQLException {
							return new Person(rs.getInt(1), rs.getString(2), rs.getString(3));
						}
					});

			for (Person person : results) {
				log.info("Found <" + person + "> in the database.");
			}

		}
	}
}
