package factory.fileReader;

import java.io.IOException;
import java.util.List;

import factory.entity.Entity;

/**
 * �����ļ��Ľӿ�
 * 
 * @author huangkai
 * 
 */
public interface IFileReader {
	public List<Entity> readFile(String path) throws IOException;
}
