package springboot_mongoDB.model;

import org.bson.types.ObjectId;  //for _id data type
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="details")
public class users {

	@Id                            // _id is auto-generated , we dont have to specify it
	private ObjectId _id;          //eg. 6013be11bdc75d335c45b0c6 = object id 
	
	@Indexed(unique=true)         //unique names are accepted.
	private String name;
	
	private String email;	
	//constructor
	public users(ObjectId _id, String name, String email) {
		super();
		this._id = _id;
		this.name = name;
		this.email = email;
	}
	
	//getters, setters
	public Object getId() {
		return _id;
	}
	public void setId(ObjectId _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
