package ex7._13.jaxb;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

public class JaxbTest {
	
	@Test
	public void readSqlmap() throws JAXBException, IOException {
		String contextPath = Sqlmap.class.getPackage().getName();
		System.out.println(contextPath);

		
		JAXBContext context = JAXBContext.newInstance(contextPath);
//		System.out.println(context);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		System.out.println(getClass().getResourceAsStream("sqlmap.xml"));
		System.out.println(unmarshaller.unmarshal(getClass().getResourceAsStream("sqlmap.xml")));
		Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(getClass().getResourceAsStream("sqlmap.xml"));
		System.out.println(sqlmap);
		List<SqlType> sqlList = sqlmap.getSql();
		System.out.println(sqlList.size());
		assertThat(sqlList.size(), is(3));
		assertThat(sqlList.get(0).getKey(), is("add"));
		assertThat(sqlList.get(0).getValue(), is("insert"));
		assertThat(sqlList.get(1).getKey(), is("get"));
		assertThat(sqlList.get(1).getValue(), is("select"));
		assertThat(sqlList.get(2).getKey(), is("delete"));
		assertThat(sqlList.get(2).getValue(), is("delete"));
		
	}
}
