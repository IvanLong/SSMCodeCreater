package factory.database.dao;

import java.util.ArrayList;
import java.util.List;

import factory.entity.Entity;
import factory.entity.Field;
import factory.entity.FieldConstraint;
import factory.stringCaseUtil.StringCaseUtil;

/**
 * sql���ģ��
 * 
 * @author huangkai
 * 
 */
public class SQLTemplate {

	public SQLTemplate() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * ����ֶ�  �����Ϊ ALTER TABLE table_name ADD column_name column_type
	 * 
	 * @param tableName ����
	 * @param fieldList ������������
	 * @return
	 */
	public static String addColumnSQL(String tableName, List<Field> fieldList){
		String baseSql = "ALTER TABLE " + toLow(tableName) + " ADD COLUMN (";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < fieldList.size(); i++) {
			if (sb.length() > 0) {
				sb.append(", ");
			}
			Field field = fieldList.get(i);
			sb.append(toLow(field.getFieldName()) + " " + field.getFieldType() + checkFieldTypeCount(field.getFieldTypeCount()));
		}
		return baseSql + sb.toString() + ")";	
	}
	
	/**
	 * ����ֶ�  �����Ϊ ALTER TABLE table_name ADD column_name column_type
	 * 
	 * @param tableName ����
	 * @param field 
	 * @return
	 */
	public static String addColumnSQL(String tableName, Field field){
		String baseSql = "ALTER TABLE " + toLow(tableName) + " ADD COLUMN ";
		StringBuilder sb = new StringBuilder();
		sb.append(toLow(field.getFieldName()) + " " + field.getFieldType() + checkFieldTypeCount(field.getFieldTypeCount()));
		return baseSql + sb.toString();	
	}
	
	/**
	 * ɾ�����ֶ�
	 * 
	 * @param tableName
	 * @param field
	 * @return
	 */
	public static String dropColumnSQL(String tableName, Field field) {
		StringBuilder sb = new StringBuilder("ALTER TABLE " + toLow(tableName) + " DROP COLUMN " + toLow(field.getFieldName()));
		return sb.toString();
	}
	
	/**
	 * ������
	 * ����ALTER TABLE table_Name ADD CONSTRAINT fk_fieldName FOREIGN KEY (fieldName) REFERENCES foreignTableName(foreign_field)
	 * 
	 * @param tableName ����
	 * @param field �ֶ�
	 * @param forignInfo �����Ϣ
	 * @return
	 */
	public static String addColumnForignKeySQL(String tableName, Field field, String forignInfo){
		StringBuilder sb = new StringBuilder("ALTER TABLE " + toLow(tableName) + " ADD CONSTRAINT fk_" + toLow(field.getFieldName()) +  " FOREIGN KEY(");
		sb.append(toLow(field.getFieldName()) + ") REFERENCES " + toLow(forignInfo));
		return sb.toString();
	}
	
	/**
	 * ɾ�����
	 * 
	 * @param tableName
	 * @param field
	 * @return
	 */
	public static String dropColumnForignKeySQL(String tableName, Field field){
		StringBuilder sb = new StringBuilder("ALTER TABLE " + toLow(tableName) + " DROP CONSTRAINT fk_" + toLow(field.getFieldName()));
		return sb.toString();
	}
	
	/**
	 * Ϊ����� check 
	 * 
	 * @param tableName
	 * @param field
	 * @param checkInfo
	 * @return
	 */
	public static String addColumnCheckSQL(String tableName, Field field, String checkInfo){
		StringBuilder sb = new StringBuilder("ALTER TABLE " + toLow(tableName) + " ADD CONSTRAINT chk_" + toLow(field.getFieldName()) +"  CHECK(" + toLow(checkInfo) +  ")");
		return sb.toString();
	}
	
	/**
	 * ɾ��check
	 * 
	 * @param tableName
	 * @param field
	 * @return
	 */
	public static String dropColumnCheckSQL(String tableName, Field field){
		StringBuilder sb = new StringBuilder("ALTER TABLE " + toLow(tableName) + " DROP CONSTRAINT chk_" + toLow(field.getFieldName()));
		return sb.toString();
	}
	
	/**
	 * Ϊ���������
	 * 
	 * @param tableName
	 * @param field
	 * @return
	 */
	public static String addColumnPrimaryKeySQL(String tableName, Field field) {
		StringBuilder sb = new StringBuilder("ALTER TABLE " + toLow(tableName) + " ADD CONSTRAINT pk_" + toLow(field.getFieldName())+" PRIMARY KEY (" + toLow(field.getFieldName()) + ")");
		return sb.toString();
	}
	
	/**
	 * ɾ������
	 * 
	 * @param tableName
	 * @param field
	 * @return
	 */
	public static String dropColumnPrimaryKeySQL(String tableName, Field field) {
		StringBuilder sb = new StringBuilder("ALTER TABLE " + toLow(tableName) + " DROP CONSTRAINT pk_" + toLow(field.getFieldName()));
		return sb.toString();
	}
	
	/**
	 * ��� default
	 * 
	 * @param tableName
	 * @param field
	 * @param defaultInfo
	 * @return
	 */
	public static String addColumnDefaultSQL(String tableName, Field field, String defaultInfo) {
		StringBuilder sb = new StringBuilder("ALTER TABLE " + toLow(tableName) + " ALTER COLUMN " + toLow(field.getFieldName()) + " SET DEFAULT '" + defaultInfo + "'");
		return sb.toString();
	}
	
	/**
	 * ɾ��default
	 * 
	 * @param tableName
	 * @param field
	 * @return
	 */
	public static String dropColumnDefaultSQL(String tableName, Field field) {
		StringBuilder sb = new StringBuilder("ALTER TABLE " + toLow(tableName) + " ALTER COLUMN " + toLow(field.getFieldName()) + " DROP DEFAULT");
		return sb.toString();
	}
	
	/**
	 * ���unique
	 * 
	 * @param tableName
	 * @param field
	 * @return
	 */
	public static String addColumnUniqueSQL(String tableName, Field field) {
		StringBuilder sb = new StringBuilder("ALTER TABLE " + toLow(tableName) + " ADD CONSTRAINT un_" + toLow(field.getFieldName())+" UNIQUE(" + toLow(field.getFieldName()) + ")");
		return sb.toString();
	}
	
	/**
	 * ɾ��unique
	 * 
	 * @param tableName
	 * @param field
	 * @return
	 */
	public static String dropColumnUniqueSQL(String tableName, Field field) {
		StringBuilder sb = new StringBuilder("ALTER TABLE " + toLow(tableName) + " DROP CONSTRAINT un_" + toLow(field.getFieldName()));
		return sb.toString();
	}
	
	/**
	 * ���not null
	 * 
	 * @param tableName
	 * @param field
	 * @return
	 */
	public static String addColumnNotNullSQL(String tableName, Field field) {
		StringBuilder sb = new StringBuilder("ALTER TABLE " + toLow(tableName) + " ALTER COLUMN " + toLow(field.getFieldName()) + " NOT NULL");
		return sb.toString();
	}
	
	/**
	 * ɾ��not null
	 * 
	 * @param tableName
	 * @param field
	 * @return
	 */
	public static String dropColumnNotNullSQL(String tableName, Field field) {
		StringBuilder sb = new StringBuilder("ALTER TABLE " + toLow(tableName) + " ALTER COLUMN " + toLow(field.getFieldName()) + " NULL");
		return sb.toString();
	}
	
	/**
	 * �޸ı��ֶ�����
	 * 
	 * @param tableName
	 * @param field
	 * @return
	 */
	public static String alterColumnSQL(String tableName, Field field) {
		StringBuilder sb = new StringBuilder("ALTER TABLE " + toLow(tableName) + " ALTER COLUMN " + toLow(field.getFieldName()) + " " + field.getFieldType() + checkFieldTypeCount(field.getFieldTypeCount()));
		return sb.toString();
	}

	/**
	 * ������
	 * 
	 * @param entity 
	 * @return
	 */
	public static String createTableSQL(Entity entity){
		List<Field> primaryFieldList = new ArrayList<Field>();
		List<Field> foreignFieldList = new ArrayList<Field>();
		List<Field> uniqueFieldList = new ArrayList<Field>();
		List<Field> checkFieldList = new ArrayList<Field>();
		String base = "create table " + toLow(entity.getEntityName()) + " (";
		List<Field> fields = entity.getFields();
		int fieldCount = fields.size();
		StringBuilder sb = new StringBuilder();
		//�����ֶ�
		for (int i = 0; i < fieldCount;i++) {
			if (sb.length() > 0) {
				sb.append(",");
			}
			Field field = fields.get(i);
			String fieldName = toLow(field.getFieldName());
			String fieldType = toLow(field.getFieldType());
			String fieldTypeCount = checkFieldTypeCount(field.getFieldTypeCount());
			//������ ������Լ�������
			sb.append(fieldName + " " + fieldType + fieldTypeCount+ " ");
			//����Լ�� ��Ҫ�� not null �� default
			if (field.isNotNull()) {
				sb.append(checkFieldConstraint("n") + " ");
			}
			if (field.isDefault()) {
				sb.append(checkFieldConstraint("d") + " " + field.getDefaultCondition() + " ");
			}
			//����sql β����check primary foreign unique
			if (field.isCheck()) {
				checkFieldList.add(field);
			}
			if (field.isForignKey()) {
				foreignFieldList.add(field);
			}
			if (field.isPrimaryKey()) {
				primaryFieldList.add(field);
			}
			if (field.isUnique()) {
				uniqueFieldList.add(field);
			}
		}
		sb.append(",");
		int pSize = primaryFieldList.size();
		if (pSize > 0) {
			StringBuilder primaryKeySb = new StringBuilder(FieldConstraint.transferFieldConstraint("p") + "(");
			for (int i = 0; i < pSize; i++) {
				primaryKeySb.append(toLow(primaryFieldList.get(i).getFieldName()));
				if (i != pSize - 1) {
					primaryKeySb.append(",");
				} else {
					primaryKeySb.append(")");
				}
			}
			sb.append(primaryKeySb.toString() + ",");
		}
		
		int uSize = uniqueFieldList.size();
		if (uSize > 0) {
			StringBuilder uniqueSb = new StringBuilder(FieldConstraint.transferFieldConstraint("u") + "(");
			for (int i = 0; i < uSize; i++) {
				uniqueSb.append(toLow(uniqueFieldList.get(i).getFieldName()));
				if (i != uSize - 1) {
					uniqueSb.append(",");
				} else {
					uniqueSb.append(")");
				}
			}
			sb.append(uniqueSb.toString() + ",");
		}
		
		int fSize = foreignFieldList.size();
		if (fSize > 0) {
			for (int i = 0; i < fSize; i++) {
				Field field = foreignFieldList.get(i);
				StringBuilder foreignSb = new StringBuilder(FieldConstraint.transferFieldConstraint("f") + "(" + toLow(field.getFieldName()) + ") REFERENCES " );
				foreignSb.append(toLow(field.getForignCondition()));
				sb.append(foreignSb.toString() + ",");
			}
		}
		
		int cSize = checkFieldList.size();
		if (cSize > 0) {
			for (int i = 0; i < cSize; i++) {
				Field field = checkFieldList.get(i);
				StringBuilder checkSb = new StringBuilder(FieldConstraint.transferFieldConstraint("c") + " (");
				checkSb.append(toLow(field.getCheckCondition()));
				if (i != cSize - 1) {
					checkSb.append(",");
				}
				sb.append(checkSb.toString() + ")");
			}
		}
		String sqlStr = sb.toString();
		if (sqlStr.endsWith(",")) {
			sqlStr = sqlStr.substring(0, sqlStr.length() - 1);
		}
		return base + sqlStr + ")";
	}
	
	//helper method
	/**
	 * ȫ��תСд
	 * 
	 * @param s
	 * @return
	 */
	private static String toLow(String s) {
		return StringCaseUtil.lowcaseAll(s);
	}
	
	/**
	 * Ϊ���ȼ�����
	 * 
	 * @param s
	 * @return
	 */
	private static String checkFieldTypeCount(String s){
		if (s.equals("") || s.length() == 0) {
			return "";
		}
		return "(" + s + ")";
	}
	
	/**
	 * �ж�Լ������
	 * 
	 * @param s
	 * @return
	 */
	private static String checkFieldConstraint(String s){
		return FieldConstraint.transferFieldConstraint(s);
	}

	/*public static void main(String[] args) {
		Field f1 = new Field();
		f1.setFieldName("testName1");
		f1.setFieldType("varchar1");
		f1.setFieldTypeCount("20");
		
		Field f2 = new Field();
		f2.setFieldName("testName2");
		f2.setFieldType("varchar2");
		f2.setFieldTypeCount("20");
		Entity e = new Entity();
		e.setEntityName("tttttttt");
		List<Field> l = new ArrayList<Field>();
		l.add(f1);
		l.add(f2);
		e.setFields(l);
		System.out.println("addColumnSQL:"+addColumnSQL(e.getEntityName(), l));
		System.out.println("dropColumnSQL:"+dropColumnSQL(e.getEntityName(), f2));
		System.out.println("addColumnForignKeySQL:"+addColumnForignKeySQL(e.getEntityName(), f2,"test(id)"));
		System.out.println("dropColumnForignKeySQL:"+dropColumnForignKeySQL(e.getEntityName(), f2));
		System.out.println("addColumnCheckSQL:"+addColumnCheckSQL(e.getEntityName(), f2,"id > 0"));
		System.out.println("dropColumnCheckSQL:"+dropColumnCheckSQL(e.getEntityName(), f2));
		System.out.println("addColumnPrimaryKeySQL:"+addColumnPrimaryKeySQL(e.getEntityName(), f2));
		System.out.println("dropColumnPrimaryKeySQL:"+dropColumnPrimaryKeySQL(e.getEntityName(), f2));
		System.out.println("addColumnDefaultSQL:"+addColumnDefaultSQL(e.getEntityName(), f2,"id = 10086"));
		System.out.println("dropColumnDefaultSQL:"+dropColumnDefaultSQL(e.getEntityName(), f2));
		System.out.println("addColumnUniqueSQL:"+addColumnUniqueSQL(e.getEntityName(), f2));
		System.out.println("dropColumnUniqueSQL:"+dropColumnUniqueSQL(e.getEntityName(), f2));
		System.out.println("alterColumnSQL:"+alterColumnSQL(e.getEntityName(), f2));
	}*/
}
