package main;

import java.io.IOException;

import factory.create.CodeCreater;
import freemarker.template.TemplateException;

public class Main {
	public static void main(String[] args) throws IOException, TemplateException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		// xml�ļ�λ��
		String sourcePath = "C:/Users/Administrator/Desktop/dataExcel.xls";
		// ���λ��
		String writePath = "C:/Users/Administrator/Desktop/";
		
		CodeCreater.StartCreate(sourcePath, writePath);
	}
}
