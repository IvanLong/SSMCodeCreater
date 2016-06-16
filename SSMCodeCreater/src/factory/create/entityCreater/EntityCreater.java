package factory.create.entityCreater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import factory.create.BaseCreater;
import factory.entity.Entity;
import factory.entity.Field;
import factory.entity.FieldType;
import factory.pathSetting.PathSetting;
import factory.stringCaseUtil.StringCaseUtil;

/**
 * Entity�� ����
 * 
 * @author huangkai
 * 
 */
public class EntityCreater extends BaseCreater {

	public EntityCreater() {
		// TODO Auto-generated constructor stub
		super.initReader();
		setPathKey("entity");
		setTemplatePath(PathSetting.entityTemplate);
	}

	@Override
	public void executeCreateTask(Entity entity) {
		// TODO Auto-generated method stub
		String className = StringCaseUtil.upcaseFirstChar(entity
				.getEntityName());
		createFile(className);
		fillData(className, entity);
	}

	@Override
	protected void fillData(String className, Entity entity) {
		// TODO Auto-generated method stub
		List<Field> fieldList = entity.getFields();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("author", entity.getAuthor());
		// ����ĸСд������
		data.put("entityNameL", StringCaseUtil.lowcaseFirstChar(className));
		// ����ĸ��д������
		data.put("entityName", className);
		data.put("package", getPackagePathWithPathKey().replaceAll("/", "."));

		// �����б�
		List<Object> attrList = new ArrayList<Object>();
		for (int i = 0; i < fieldList.size(); i++) {
			Field field = fieldList.get(i);
			Map<String, String> fieldMap = new HashMap<String, String>();
			String type = FieldType.transferFieldType(field.getFieldType());
			fieldMap.put("fieldType", type);
			fieldMap.put("fieldName", field.getFieldName());
			attrList.add(fieldMap);
		}
		data.put("attributes", attrList);
		loadTemplateAndWriteFile(className, ".java", data);
	}
}
