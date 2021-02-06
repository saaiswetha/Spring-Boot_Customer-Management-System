package springboot_mongoDB.Repository;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import springboot_mongoDB.model.users;

public interface usersRepository extends MongoRepository<users,Integer>{

	public List<users> findByName(String name);
	public List<users> findByEmail(String email);
	//public void save(String x);
	
	//to find user by object id
	public Optional<users> findBy_id(ObjectId _id);
	public void deleteBy_id(ObjectId _id);
	
	@Query("{'name':?0}")
	public List<users> findName(String name);
	
}
