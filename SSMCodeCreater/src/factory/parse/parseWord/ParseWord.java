package factory.parse.parseWord;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;

import factory.entity.Entity;
import factory.entity.Field;
import factory.parse.IParse;

/**
 * ����word
 * 
 * @author huangkai
 * 
 */
public class ParseWord implements IParse {
	private boolean isError = false;
	private boolean isExit = false;
	public final String isCreated = "isCreated";
	public TableIterator it = null;

	public ParseWord(TableIterator it) {
		// TODO Auto-generated constructor stub
		this.it = it;
	}

	@Override
	public List<Entity> startParse() {
		// TODO Auto-generated method stub
		if (it == null) {
			System.out.println("����ʧ��");
			return null;
		}
		return parse();
	}

	/**
	 * �����ĵ������еı� List
	 * 
	 * @param it
	 * @return
	 */
	public List<Entity> parse() {
		System.out.println("��ʼ����ʵ��\n");
		List<Entity> list = new ArrayList<Entity>();
		// �����ĵ��еı��
		while (it.hasNext()) {
			Entity entity = new Entity();
			Table table = (Table) it.next();
			List<Field> fields = new ArrayList<Field>();
			// ������
			for (int row = 0; row < table.numRows(); row++) {
				TableRow tableRow = table.getRow(row);
				switch (row) {
				case 0:
					parseTableBaseInfomation(tableRow, entity);
					break;
				case 1:
					break;
				case 2:
					if (!isError && !isExit)
						parseTableSteate(tableRow, entity);
					break;
				case 3:
					if (!isError && !isExit)
						parseTableAuthor(tableRow, entity);
					break;
				case 4:
					break;
				default:
					if (!isError && !isExit)
						parseTableField(tableRow, fields);
					break;
				}
			}
			if (!isError && !isExit) {
				entity.setFields(fields);
				list.add(entity);
				System.out.println("����" + entity.getEntityName() + "���\n");
			}
			isError = false;
			isExit = false;
		}

		return list;
	}

	public void parseTableBaseInfomation(TableRow tableRow, Entity entity) {
		String s = getInformation(tableRow);
		if (!checkString(s)) {
			isError = true;
			System.out.println("ʵ�����ƴ���,����");
			return;
		}
		System.out.println("��ʼ����  " + s);
		entity.setEntityName(s);
	}

	public void parseTableSteate(TableRow tableRow, Entity entity) {
		String s = getInformation(tableRow);
		if (!checkString(s)) {
			s = "0";
		}
		if (s.equals(isCreated)) {
			isExit = true;
			return;
		}
		entity.setEntitySteate(Integer.parseInt(s));
	}

	public void parseTableAuthor(TableRow tableRow, Entity entity) {
		String s = getInformation(tableRow);
		entity.setAuthor(s);
	}

	public void parseTableField(TableRow tableRow, List<Field> fields) {
		Field field = new Field();
		// ������
		for (int column = 0; column < tableRow.numCells(); column++) {
			TableCell td = tableRow.getCell(column);
			Paragraph para = td.getParagraph(0);
			String s = para.text().trim();
			switch (column) {
			case 0:
				field.setFieldName(s);
				break;
			case 1:
				field.setFieldType(s);
				break;
			case 2:
				field.setFieldTypeCount(s);
				break;
			case 3:
				field.setFieldConstraint(s);
				break;
			case 4:
				field.setFieldTag(s);
				break;
			case 5:
				field.setFieldTagConstraint(s);
				break;
			case 6:
				field.setFieldDescription(s);
				break;
			default:
				break;
			}

		}
		fields.add(field);
	}

	public String getInformation(TableRow tableRow) {
		return getInformationParagraph(tableRow).text().trim();
	}

	public boolean checkString(String s) {
		if (s == "" || s.length() == 0) {
			return false;
		}
		return true;
	}

	public void startWrite(TableIterator it, List<Integer> resultList) {
		// �����ĵ��еı��
		int count = 0;
		while (it.hasNext()) {
			Table table = (Table) it.next();
			if (resultList.get(count) == 1) {
				TableRow tableRow = table.getRow(2);
				Paragraph para = getInformationParagraph(tableRow);
				para.replaceText(para.text().trim(), isCreated);
			}
			count++;
		}
	}

	private Paragraph getInformationParagraph(TableRow tableRow) {
		return tableRow.getCell(2).getParagraph(0);
	}

}
