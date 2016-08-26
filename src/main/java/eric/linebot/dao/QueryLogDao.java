package eric.linebot.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import eric.linebot.dao.model.QueryLog;

@Transactional
public interface QueryLogDao extends CrudRepository<QueryLog, Long> {
	QueryLog save(QueryLog queryLog);
}
