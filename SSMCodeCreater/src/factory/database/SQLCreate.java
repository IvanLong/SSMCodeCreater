package factory.database;

import java.util.List;

import factory.database.dao.SQLTemplate;
import factory.entity.Entity;

/**
 * �������ݿ����
 * 
 * @author huangkai
 * 
 */
public class SQLCreate {
	
	public SQLCreate() {
		// TODO Auto-generated constructor stub
	}

	public static String tableCreateSQL(Entity entity){
		return SQLTemplate.createTableSQL(entity);
	}
	
	public static List<String> tableAlterSQL(Entity entity){
		return CreateAlterSQLList.startParseAndCreateSQL(entity);
	}

}
